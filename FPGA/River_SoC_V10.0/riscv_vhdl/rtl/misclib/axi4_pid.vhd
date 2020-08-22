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
--! | 0x00 W  | TS     (64)       |
--! | 0x08 W  | PV, SP (64)       |
--! | 0x10 w  | b0, b1 (64)       |
--! | 0x18 w  | b2     (32)       |
--! | 0x00 r  | XOUT   (32)       |
--! | 0x08 r  | XOUT_R (32)       |
--! |---------|-------------------|

entity axi4_pid is
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
    o  		 	: out axi4_slave_out_type
  );
end;
 
architecture arch_axi4_pid  of axi4_pid is

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
  signal wb_bus_wdata : std_logic_vector(CFG_SYSBUS_DATA_BITS-1 downto 0);
  
  signal TS		: std_logic_vector(63 DOWNTO 0) := X"0000000000989680"; -- 100 ms
  signal PV		: std_logic_vector(31 DOWNTO 0) := X"41A1999A";
  signal SP		: std_logic_vector(31 DOWNTO 0) := X"4272CCCD";
  signal b0		: std_logic_vector(31 DOWNTO 0) := X"4204CCCD";
  signal b1		: std_logic_vector(31 DOWNTO 0) := X"41DE6666";
  signal b2		: std_logic_vector(31 DOWNTO 0) := X"425EE148";
  signal XOUT		: std_logic_vector(31 DOWNTO 0) := X"00000000";
  signal XOUT_R	: std_logic;
  signal T_XOUT_R	: std_logic;

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
    o_wstrb => open,
    o_wdata => wb_bus_wdata
  );
  
  PID: entity work.PID_Top
  port map (
    clk	=> clk,
    nrst	=> nrst,

    TS	=> TS,

    PV	=> PV,
    SP	=> SP,

    b0	=> b0,
    b1	=> b1,
    b2	=> b2,

    XOUT	=> XOUT,
    XOUT_R=> XOUT_R
  );
  
  cfg  <= xconfig;
  
  -- registers:
  regs : process(clk, nrst)
  begin 
     if async_reset and nrst = '0' then
			wb_dev_rdata<= (OTHERS => '0');
			TS       <= (OTHERS => '0');
			PV       <= (OTHERS => '0');
			SP       <= (OTHERS => '0');
			b0       <= (OTHERS => '0');
			b1       <= (OTHERS => '0');
			b2       <= (OTHERS => '0');
			T_XOUT_R <= '0';
     elsif rising_edge(clk) then
			if nrst = '0' then
				wb_dev_rdata<= (OTHERS => '0');
				TS       <= (OTHERS => '0');
				PV       <= (OTHERS => '0');
				SP       <= (OTHERS => '0');
				b0       <= (OTHERS => '0');
				b1       <= (OTHERS => '0');
				b2       <= (OTHERS => '0');
				T_XOUT_R <= '0';
			else
				if w_bus_we = '1' then --write
					IF (wb_bus_waddr(0)(4 downto 3) = "00") THEN
						TS  <= wb_bus_wdata;
					ELSIF (wb_bus_waddr(0)(4 downto 3) = "01") THEN
						PV <= wb_bus_wdata(31 downto 0);
						SP <= wb_bus_wdata(63 downto 32);
					ELSIF (wb_bus_waddr(0)(4 downto 3) = "10") THEN
						b0 <= wb_bus_wdata(31 downto 0);
						b1 <= wb_bus_wdata(63 downto 32);
					ELSIF (wb_bus_waddr(0)(4 downto 3) = "11") THEN
						b2 <= wb_bus_wdata(31 downto 0);
					END IF;
				elsif w_bus_re = '1' then -- read
					IF (wb_bus_raddr(0)(3) = '0') THEN
						wb_dev_rdata(31 downto 0)  <= XOUT;
						T_XOUT_R <= '0';
					ELSIF (wb_bus_raddr(0)(3) = '1') THEN
						wb_dev_rdata(63 downto 0) <= (others => '0');
						wb_dev_rdata(0) <= T_XOUT_R;
					ELSE
						wb_dev_rdata <= (others => '0');
					END IF;
				end if;
				if XOUT_R = '1' then
					T_XOUT_R <= '1';
				end if;
			end if;
     end if;
	  
  end process;  
end;