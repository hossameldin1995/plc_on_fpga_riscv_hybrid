library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
library commonlib;
use commonlib.types_common.all;
--! AMBA system bus specific library.
library ambalib;
--! AXI4 configuration constants.
use ambalib.types_amba4.all;

--! The following registers are defined:
--! |---------|-------------------|
--! | Address | Description       |
--! |---------|-------------------|
--! | 0x00 r  | Elapsed Time (64) |
--! | 0x08 r  | Q                 |
--! | 0x00 w  | Preset  Time (64) |
--! | 0x08 w  | IN                |
--! |---------|-------------------|

entity axi4_ton is
  generic (
    async_reset : boolean := false;
    xaddr    : integer := 0;
    xmask    : integer := 16#fffff#;
    xirq     : integer := 0
  );
  port (
    clk  		: in std_logic;
    nrst 		: in std_logic;
    cfg  		: out axi4_slave_config_type;
    i    		: in  axi4_slave_in_type;
    o  		  	: out axi4_slave_out_type
  );
end; 
 
architecture arch_axi4_ton  of axi4_ton is

  constant xconfig : axi4_slave_config_type := (
     descrtype => PNP_CFG_TYPE_SLAVE,
     descrsize => PNP_CFG_SLAVE_DESCR_BYTES,
     irq_idx => conv_std_logic_vector(xirq, 8),
     xaddr => conv_std_logic_vector(xaddr, CFG_SYSBUS_CFG_ADDR_BITS),
     xmask => conv_std_logic_vector(xmask, CFG_SYSBUS_CFG_ADDR_BITS),
     vid => VENDOR_GNSSSENSOR,
     did => GNSSSENSOR_GPIO
  );

  signal wb_dev_rdata : std_logic_vector(CFG_SYSBUS_DATA_BITS-1 downto 0);
  signal wb_bus_raddr : global_addr_array_type;
  signal w_bus_re    : std_logic;
  signal wb_bus_waddr : global_addr_array_type;
  signal w_bus_we    : std_logic;
  signal wb_bus_wstrb : std_logic_vector(CFG_SYSBUS_DATA_BYTES-1 downto 0);
  signal wb_bus_wdata : std_logic_vector(CFG_SYSBUS_DATA_BITS-1 downto 0);
  
  signal IN_T, Q   : std_logic;
  signal PT, ET    : std_logic_vector(63 DOWNTO 0);

begin

  axi0 :  axi4_slave generic map (
    async_reset => async_reset
  ) port map (
    i_clk => clk,
    i_nrst => nrst,
    i_xcfg => xconfig, 
    i_xslvi => i,
    o_xslvo => o,
    i_ready => '1',
    i_rdata => wb_dev_rdata,
    o_re => w_bus_re,
    o_r32 => open,
    o_radr => wb_bus_raddr,
    o_wadr => wb_bus_waddr,
    o_we => w_bus_we,
    o_wstrb => wb_bus_wstrb,
    o_wdata => wb_bus_wdata
  );
  
  Counter: entity work.Counter_Down_64_bit_Cin  PORT MAP(clk, nrst, IN_T, PT, Q, ET);

  cfg  <= xconfig;
  
  -- registers:
  regs : process(clk, nrst)
  begin 
     if async_reset and nrst = '0' then
			wb_dev_rdata<= (OTHERS => '0');
			PT       <= (OTHERS => '0');
			IN_T     <= '0';
     elsif rising_edge(clk) then 
			if w_bus_we = '1' then --write
				IF (wb_bus_waddr(0)(3 downto 2) = "00") THEN
					PT  <= wb_bus_wdata;
				ELSIF (wb_bus_waddr(0)(3 downto 2) = "10") THEN
					IN_T <= wb_bus_wdata(0);
				END IF;
			elsif w_bus_re = '1' then -- read
				IF (wb_bus_raddr(0)(3 downto 2) = "00") THEN
					wb_dev_rdata  <= ET;
				ELSIF (wb_bus_raddr(0)(3 downto 2) = "10") THEN
					wb_dev_rdata(0) <= Q;
				ELSE
					wb_dev_rdata <= (others => '0');
				END IF;
			end if;
     end if;
	  
  end process;  
end;
