--!
--! Copyright 2019 Sergey Khabarov, sergeykhbr@gmail.com
--!
--! Licensed under the Apache License, Version 2.0 (the "License");
--! you may not use this file except in compliance with the License.
--! You may obtain a copy of the License at
--!
--!     http://www.apache.org/licenses/LICENSE-2.0
--!
--! Unless required by applicable law or agreed to in writing, software
--! distributed under the License is distributed on an "AS IS" BASIS,
--! WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--! See the License for the specific language governing permissions and
--! limitations under the License.
--!

library ieee;
use ieee.std_logic_1164.all;
library commonlib;
use commonlib.types_common.all;
--! RIVER CPU specific library.
library riverlib;
--! RIVER CPU configuration constants.
use riverlib.river_cfg.all;

entity MemAccess is generic (
    async_reset : boolean
  );
  port (
    i_clk  : in std_logic;
    i_nrst : in std_logic;
    i_e_valid : in std_logic;                                         -- Execution stage outputs are valid
    i_e_pc : in std_logic_vector(BUS_ADDR_WIDTH-1 downto 0);          -- Execution stage instruction pointer
    i_e_instr : in std_logic_vector(31 downto 0);                     -- Execution stage instruction value

    i_res_addr : in std_logic_vector(5 downto 0);                     -- Register address to be written (0=no writing)
    i_res_data : in std_logic_vector(RISCV_ARCH-1 downto 0);          -- Register value to be written
    i_memop_sign_ext : in std_logic;                                  -- Load data with sign extending (if less than 8 Bytes)
    i_memop_load : in std_logic;                                      -- Load data from memory and write to i_res_addr
    i_memop_store : in std_logic;                                     -- Store i_res_data value into memory
    i_memop_size : in std_logic_vector(1 downto 0);                   -- Encoded memory transaction size in bytes: 0=1B; 1=2B; 2=4B; 3=8B
    i_memop_addr : in std_logic_vector(BUS_ADDR_WIDTH-1 downto 0);    -- Memory access address
    o_wena : out std_logic;                                           -- Write enable signal
    o_waddr : out std_logic_vector(5 downto 0);                       -- Output register address (0 = x0 = no write)
    o_wdata : out std_logic_vector(RISCV_ARCH-1 downto 0);            -- Register value

    -- Memory interface:
    i_mem_req_ready : in std_logic;
    o_mem_valid : out std_logic;                                      -- Memory request is valid
    o_mem_write : out std_logic;                                      -- Memory write request
    o_mem_sz : out std_logic_vector(1 downto 0);                      -- Encoded data size in bytes: 0=1B; 1=2B; 2=4B; 3=8B
    o_mem_addr : out std_logic_vector(BUS_ADDR_WIDTH-1 downto 0);     -- Data path requested address
    o_mem_data : out std_logic_vector(BUS_DATA_WIDTH-1 downto 0);     -- Data path requested data (write transaction)
    i_mem_data_valid : in std_logic;                                  -- Data path memory response is valid
    i_mem_data_addr : in std_logic_vector(BUS_ADDR_WIDTH-1 downto 0); -- Data path memory response address
    i_mem_data : in std_logic_vector(BUS_DATA_WIDTH-1 downto 0);      -- Data path memory response value
    o_mem_resp_ready : out std_logic;

    o_hold : out std_logic;                                           -- Memory operation is more than 1 clock
    o_valid : out std_logic;                                          -- Output is valid
    o_pc : out std_logic_vector(BUS_ADDR_WIDTH-1 downto 0);           -- Valid instruction pointer
    o_instr : out std_logic_vector(31 downto 0)                       -- Valid instruction value
  );
end; 
 
architecture arch_MemAccess of MemAccess is

  constant State_Idle : std_logic_vector(1 downto 0) := "00";
  constant State_WaitReqAccept : std_logic_vector(1 downto 0) := "01";
  constant State_WaitResponse : std_logic_vector(1 downto 0) := "10";
  constant State_RegForward : std_logic_vector(1 downto 0) := "11";

  type RegistersType is record
      state : std_logic_vector(1 downto 0);
      memop_r : std_logic;
      memop_addr : std_logic_vector(BUS_ADDR_WIDTH-1 downto 0);
      pc : std_logic_vector(BUS_ADDR_WIDTH-1 downto 0);
      instr : std_logic_vector(31 downto 0);

      res_addr : std_logic_vector(5 downto 0);
      res_data : std_logic_vector(RISCV_ARCH-1 downto 0);
      memop_sign_ext : std_logic;
      memop_size : std_logic_vector(1 downto 0);
      wena : std_logic;
  end record;
 
  constant R_RESET : RegistersType := (
    State_Idle,                              -- state
    '0', (others => '0'),                    -- memop_r, memop_addr
    (others => '0'), (others => '0'),        -- pc, instr
    (others => '0'),                         -- res_addr
    (others => '0'), '0',                    -- res_data, memop_sign_ext
    (others => '0'), '0'                     -- memop_size, wena
  );

  signal r, rin : RegistersType;

  -- TODO: move into separate module
  -- queue signals before move into separate module
  constant QUEUE_WIDTH : integer := RISCV_ARCH + 6 + 32 + BUS_ADDR_WIDTH
                                    + 2 + 1 + 1 + 1 + BUS_ADDR_WIDTH;
  constant QUEUE_DEPTH : integer := 1;

  type queue_data_type is array (0 to QUEUE_DEPTH-1) of std_logic_vector(QUEUE_WIDTH-1 downto 0);

  type QueueRegisterType is record
    wcnt : integer range 0 to QUEUE_DEPTH;
    mem : queue_data_type;
  end record;

  signal queue_we : std_logic;
  signal queue_re : std_logic;
  signal queue_data_i : std_logic_vector(QUEUE_WIDTH-1 downto 0);
  signal queue_data_o : std_logic_vector(QUEUE_WIDTH-1 downto 0);
  signal qr, qrin : QueueRegisterType;
  signal queue_nempty : std_logic;

begin

  queue_data_i <= 
    i_res_data &
    i_res_addr &
    i_e_instr &
    i_e_pc &
    i_memop_size & 
    i_memop_sign_ext &
    i_memop_store &
    (i_memop_load or i_memop_store) &
    i_memop_addr;

  queue_we <= i_e_valid;

  qproc : process (i_nrst, queue_we, queue_re, queue_data_i, qr)
    variable nempty : std_logic;
    variable vb_data_o : std_logic_vector(QUEUE_WIDTH-1 downto 0);
    variable qv : QueueRegisterType;
    variable full : std_logic;
  begin
    qv := qr;

    full := '0';
    if qr.wcnt = QUEUE_DEPTH then
        full := '1';
    end if;

    vb_data_o := qr.mem(0);
    if queue_re = '1' and queue_we = '1' then
        if qr.wcnt = 0 then
            vb_data_o := queue_data_i;
        else
            qv.mem(0) := queue_data_i;
        end if;
    elsif queue_re = '0' and queue_we = '1' then
        if full = '0' then
            qv.wcnt := qr.wcnt + 1;
            qv.mem(0) := queue_data_i;
        end if;
    elsif queue_re = '1' and queue_we = '0' then
        if qr.wcnt /= 0 then
            qv.wcnt := qr.wcnt - 1;
        end if;
    end if;

    nempty := '0';
    if queue_we = '1' or qr.wcnt /= 0 then
        nempty := '1';
    end if;
 
    if not async_reset and i_nrst = '0' then
        qv.wcnt := 0;
        for k in 0 to QUEUE_DEPTH-1 loop
            qv.mem(k) := (others => '0');
        end loop;
    end if;

    qrin <= qv;
    queue_nempty <= nempty;
    queue_data_o <= vb_data_o;
  end process;



  comb : process(i_nrst, i_mem_req_ready, i_e_valid, i_res_addr,
                i_mem_data_valid, i_mem_data_addr, i_mem_data,
                queue_data_o, queue_nempty, r)
    variable v : RegistersType;
    variable w_mem_access : std_logic;
    variable w_mem_valid : std_logic;
    variable w_mem_write : std_logic;
    variable w_mem_sign_ext : std_logic;
    variable wb_mem_sz : std_logic_vector(1 downto 0);
    variable wb_mem_addr : std_logic_vector(BUS_ADDR_WIDTH-1 downto 0);
    variable wb_mem_data : std_logic_vector(RISCV_ARCH-1 downto 0);
    variable w_hold : std_logic;
    variable w_valid : std_logic;
    variable w_queue_re : std_logic;
    variable wb_mem_data_signext : std_logic_vector(RISCV_ARCH-1 downto 0);
    variable wb_res_data : std_logic_vector(RISCV_ARCH-1 downto 0);
    variable wb_res_addr : std_logic_vector(5 downto 0);
    variable wb_e_pc : std_logic_vector(BUS_ADDR_WIDTH-1 downto 0);
    variable wb_e_instr : std_logic_vector(31 downto 0);
  begin

    v := r;

    w_mem_valid := '0';
    w_hold := '0';
    w_valid := '0';
    w_queue_re := '0';

    wb_mem_data := queue_data_o(2*BUS_ADDR_WIDTH+RISCV_ARCH+43-1 downto 2*BUS_ADDR_WIDTH+43);
    wb_res_addr := queue_data_o(2*BUS_ADDR_WIDTH+43-1 downto 2*BUS_ADDR_WIDTH+37);
    wb_e_instr := queue_data_o(2*BUS_ADDR_WIDTH+37-1 downto 2*BUS_ADDR_WIDTH+5);
    wb_e_pc := queue_data_o(2*BUS_ADDR_WIDTH+5-1 downto BUS_ADDR_WIDTH+5);
    wb_mem_sz := queue_data_o(BUS_ADDR_WIDTH+4 downto BUS_ADDR_WIDTH+3);
    w_mem_sign_ext := queue_data_o(BUS_ADDR_WIDTH+2);
    w_mem_write := queue_data_o(BUS_ADDR_WIDTH+1);
    w_mem_access := queue_data_o(BUS_ADDR_WIDTH);
    wb_mem_addr := queue_data_o(BUS_ADDR_WIDTH-1 downto 0);

    case r.state is
    when State_Idle =>
        w_queue_re := '1';
        if queue_nempty = '1' then
            if w_mem_access = '1' then
                w_mem_valid := '1';
                if i_mem_req_ready = '1' then
                    v.state := State_WaitResponse;
                else
                    w_hold := '1';
                    v.state := State_WaitReqAccept;
                end if;
            else
                v.state := State_RegForward;
            end if;
        end if;
    when State_WaitReqAccept =>
        w_mem_valid := '1';
        w_mem_write := not r.memop_r;
        wb_mem_sz := r.memop_size;
        wb_mem_addr := r.memop_addr;
        wb_mem_data := r.res_data;
        w_hold := '1';
        if i_mem_req_ready = '1' then
            v.state := State_WaitResponse;
        end if;
    when State_WaitResponse =>
        w_valid := '1';
        w_queue_re := '1';
        if i_mem_data_valid = '0' then
            w_queue_re := '0';
            w_valid := '0';
            w_hold := '1';
        elsif queue_nempty = '1' then
            if w_mem_access = '1' then
                w_mem_valid := '1';
                if i_mem_req_ready = '1' then
                    v.state := State_WaitResponse;
                else
                    w_hold := '1';
                    v.state := State_WaitReqAccept;
                end if;
            else
                v.state := State_RegForward;
            end if;
        else
            v.state := State_Idle;
        end if;
    when State_RegForward =>
        w_valid := '1';
        w_queue_re := '1';
        if queue_nempty = '1' then
            if w_mem_access = '1' then
                w_mem_valid := '1';
                if i_mem_req_ready = '1' then
                    v.state := State_WaitResponse;
                else
                    w_hold := '1';
                    v.state := State_WaitReqAccept;
                end if;
            else
                v.state := State_RegForward;
            end if;
        else
            v.state := State_Idle;
        end if;
    when others =>
    end case;

    if w_queue_re = '1' then
        v.pc := wb_e_pc;
        v.instr := wb_e_instr;
        v.res_addr := wb_res_addr;
        v.res_data := wb_mem_data;
        if wb_res_addr = "000000" then
            v.wena := '0';
        else
            v.wena := '1';
        end if;
        v.memop_addr := wb_mem_addr;
        v.memop_r := w_mem_access and not w_mem_write;
        v.memop_sign_ext := w_mem_sign_ext;
        v.memop_size := wb_mem_sz;
    end if;

    case r.memop_size is
    when MEMOP_1B =>
        wb_mem_data_signext := i_mem_data;
        if i_mem_data(7) = '1' then
            wb_mem_data_signext(63 downto 8) := (others => '1');
        end if;
    when MEMOP_2B =>
        wb_mem_data_signext := i_mem_data;
        if i_mem_data(15) = '1' then
            wb_mem_data_signext(63 downto 16) := (others => '1');
        end if;
    when MEMOP_4B =>
        wb_mem_data_signext := i_mem_data;
        if i_mem_data(31) = '1' then
            wb_mem_data_signext(63 downto 32) := (others => '1');
        end if;
    when others =>
        wb_mem_data_signext := i_mem_data;
    end case;

    if r.memop_r = '1' then
        if r.memop_sign_ext = '1' then
            wb_res_data := wb_mem_data_signext;
        else
            wb_res_data := i_mem_data;
        end if;
    else
        wb_res_data := r.res_data;
    end if;

    if not async_reset and i_nrst = '0' then
        v := R_RESET;
    end if;

    queue_re <= w_queue_re;

    o_mem_resp_ready <= '1';

    o_mem_valid <= w_mem_valid;
    o_mem_write <= w_mem_write;
    o_mem_sz <= wb_mem_sz;
    o_mem_addr <= wb_mem_addr;
    o_mem_data <= wb_mem_data;

    o_wena <= r.wena and w_valid;
    o_waddr <= r.res_addr;
    o_wdata <= wb_res_data;
    o_hold <= w_hold;
    o_valid <= w_valid;
    o_pc <= r.pc;
    o_instr <= r.instr;
    
    rin <= v;
  end process;

  -- registers:
  regs : process(i_clk, i_nrst)
  begin 
     if async_reset and i_nrst = '0' then
        r <= R_RESET;
        qr.wcnt <= 0;
        for k in 0 to QUEUE_DEPTH-1 loop
            qr.mem(k) <= (others => '0');
        end loop;
     elsif rising_edge(i_clk) then 
        r <= rin;
        qr <= qrin;
     end if; 
  end process;

end;
