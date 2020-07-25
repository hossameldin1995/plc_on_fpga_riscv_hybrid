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
use ieee.numeric_std.all;
library commonlib;
use commonlib.types_common.all;
--! AMBA system bus specific library.
library ambalib;
--! AXI4 configuration constants.
use ambalib.types_amba4.all;

entity axi4_gpio is
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
    o  		  	: out axi4_slave_out_type;
    KEY			: in std_logic_vector(3 DOWNTO 0);
	 SW			: in std_logic_vector(9 DOWNTO 0);
    LEDG			: out std_logic_vector(7 DOWNTO 0);
    LEDR			: out std_logic_vector(9 DOWNTO 0);
    GPIO_IN		: in std_logic_vector(17 DOWNTO 0);
    GPIO_OUT	: out std_logic_vector(17 DOWNTO 0)
  );
end; 
 
architecture arch_axi4_gpio of axi4_gpio is

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

  signal address_w		: STD_LOGIC_VECTOR(31 DOWNTO 0);
  signal wstrb				: global_addr_array_type;
  
  signal register_in		: STD_LOGIC_VECTOR(63 DOWNTO 0);   -- 32 - 0
  signal register_out	: STD_LOGIC_VECTOR(63 DOWNTO 0);   -- 36 - 0
  signal OUT_Data_1		: STD_LOGIC;
  signal OUT_Data_2		: STD_LOGIC;
	
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

  wb_dev_rdata <= "0000000000000000000000000000000" & OUT_Data_2 &"0000000000000000000000000000000" & OUT_Data_1;
  cfg  <= xconfig;
  
  wstrb(0) <= (others => wb_bus_wstrb(0));
  wstrb(1) <= (others => wb_bus_wstrb(4));
  address_w <= ((wb_bus_waddr(0) and wstrb(0)) or (wb_bus_waddr(1) and wstrb(1)));
  
  -- registers:
  regs : process(clk, nrst)
  begin 
     if not(async_reset) and nrst = '0' then
        register_in	<= (OTHERS => '0');
		  register_out	<= (OTHERS => '0');
		  GPIO_OUT		<= (OTHERS => '0');
		  LEDR			<= (OTHERS => '0');
		  LEDG			<= (OTHERS => '0');
		  OUT_Data_1	<= '0';
		  OUT_Data_2	<= '0';
     elsif rising_edge(clk) then 
        if w_bus_we = '1' then   -- write
				if address_w(8 downto 2) = "1111111" then
					register_in        <= "00000000000000000000000000000000" & KEY & SW & GPIO_IN;
					GPIO_OUT           <= register_out(17 downto 0);
					LEDR               <= register_out(27 downto 18);
					LEDG               <= register_out(35 downto 28);
				else
					register_out(to_integer(unsigned(address_w(7 downto 2)))) <= wb_bus_wdata(0);
				end if;
			elsif w_bus_re = '1' then -- read
				if wb_bus_raddr(0)(8) = '0' then   -- read inputs
					OUT_Data_1 <= register_in(to_integer(unsigned(wb_bus_raddr(0)(7 downto 2))));
				else                       -- read outputs
					OUT_Data_1 <= register_out(to_integer(unsigned(wb_bus_raddr(0)(7 downto 2))));
				end if;
				if wb_bus_raddr(1)(8) = '0' then   -- read inputs
					OUT_Data_2 <= register_in(to_integer(unsigned(wb_bus_raddr(1)(7 downto 2))));
				else                       -- read outputs
					OUT_Data_2 <= register_out(to_integer(unsigned(wb_bus_raddr(1)(7 downto 2))));
				end if;
		   end if;
     end if; 
  end process;  
  
end;
