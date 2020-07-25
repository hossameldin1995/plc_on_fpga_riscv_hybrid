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
--! | 0x00 r  | Q                 |
--! | 0x00 w  | Frequency         |
--! | 0x04 w  | Duty Cycle        |
--! |---------|-------------------|

entity axi4_pwm is
  generic (
    async_reset : boolean := false;
    xaddr    : integer := 0;
    xmask    : integer := 16#fffff#;
    xirq     : integer := 0
  );
  port (
    clk  		: in std_logic;
    clk_pwm 	: in std_logic;
    nrst 		: in std_logic;
    cfg  		: out axi4_slave_config_type;
    i    		: in  axi4_slave_in_type;
    o  		  	: out axi4_slave_out_type
  );
end; 
 
architecture arch_axi4_pwm  of axi4_pwm is

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
  
  SIGNAL EN, Q		: std_logic;
  SIGNAL DC			: std_logic_vector(6 DOWNTO 0);
  SIGNAL Frq		: std_logic_vector(16 DOWNTO 0);
	
  SIGNAL T_Count, Comp_Count	: STD_LOGIC_VECTOR(19 downto 0);

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
  
  PWM			: entity work.PWM_32_bit	PORT MAP(clk_pwm, nrst, EN, T_Count, Comp_Count, Q);
  PWM_Sig	: entity work.PWM_Signal	PORT MAP(nrst, Frq, DC, T_Count, Comp_Count);

  EN			<= '1';
  cfg  <= xconfig;
  
  -- registers:
  regs : process(clk, nrst)
  begin 
     if async_reset and nrst = '0' then
			wb_dev_rdata<= (OTHERS => '0');
			Frq			<= (OTHERS => '1');
			DC				<= (OTHERS => '0');
     elsif rising_edge(clk) then 
         if nrst = '0' then
				wb_dev_rdata<= (OTHERS => '0');
				Frq			<= (OTHERS => '1');
				DC				<= (OTHERS => '0');
			else
				if w_bus_we = '1' then --write
					Frq	<= wb_bus_wdata(16 downto 0);
					DC		<= wb_bus_wdata(38 downto 32);
				elsif w_bus_re = '1' then -- read
					wb_dev_rdata(0) <= Q;
				end if;
			end if;
     end if;
  end process;
end;
