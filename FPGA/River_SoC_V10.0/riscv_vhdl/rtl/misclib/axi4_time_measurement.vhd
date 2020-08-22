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
--! | 0b00    | START_STOP_A      |
--! | 0b10    | MICRO_NANO_A      |
--! | 0b00    | READ_TIME_A       |
--! | 0b11    |                   |
--! |---------|-------------------|

entity axi4_time_measurement is
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
	 
	 -- HEX interface
	 HEX0			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
	 HEX1			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
	 HEX2			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
	 HEX3			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
  );
end; 
 
architecture arch_axi4_time_measurement  of axi4_time_measurement is

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
  signal wb_bus_waddr : global_addr_array_type;
  signal w_bus_we    : std_logic;
  signal wb_bus_wdata : std_logic_vector(CFG_SYSBUS_DATA_BITS-1 downto 0);
	
  
  signal Micro_Nano				: std_logic := '0'; -- Micro = 1, Nano = 0
  signal Start_Stop				: std_logic := '0'; -- Start = 1, Stop = 0
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
    o_re => open,
    o_r32 => open,
    o_radr => open,
    o_wadr => wb_bus_waddr,
    o_we => w_bus_we,
    o_wstrb => open,
    o_wdata => wb_bus_wdata
  );
  
  cfg  <= xconfig;
  
  -- registers:
  regs : process(clk, nrst)
  begin 
     if async_reset and nrst = '0' then
			
			
     elsif rising_edge(clk) then 
			if w_bus_we = '1' then --write
				IF (wb_bus_waddr(0)(4 downto 2) = "000") THEN
					if wb_bus_wdata(7 downto 0) = X"71" then
						Start_Stop <= '1';
					elsif wb_bus_wdata(7 downto 0) = X"53" then
						Start_Stop <= '0';
					end if;
				ELSIF (wb_bus_waddr(0)(4 downto 2) = "010") THEN
					if wb_bus_wdata(7 downto 0) = X"36" then
						Micro_Nano <= '1';
					elsif wb_bus_wdata(7 downto 0) = X"42" then
						Micro_Nano <= '0';
					end if;
				END IF;
			end if;
     end if;
  end process;
  
  TimeCalculation: entity work.Time_Calculation
		port map(
			clk 			=> clk,
			nrst 			=> nrst,
			
			-- Time Signals
			Time_Micro_Nano	=> open,
			Micro_Nano			=> Micro_Nano,
			Start_Stop			=> Start_Stop,
			
			-- HEX interface
			HEX0					=> HEX0,
			HEX1					=> HEX1,
			HEX2					=> HEX2,
			HEX3					=> HEX3
		);
end;
