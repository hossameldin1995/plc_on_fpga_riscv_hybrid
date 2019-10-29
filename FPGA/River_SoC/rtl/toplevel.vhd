library ieee;
use ieee.std_logic_1164.all;

entity toplevel is
	port(
		clk	: in  std_logic;
		reset	: in  std_logic;

		-- UART0 signals:
		uart0_txd : out std_logic;
		uart0_rxd : in  std_logic;
		
		-- KEY signals
      KEY			: in std_logic_vector(3 DOWNTO 0);
		
		-- SW signals
      SW				: in std_logic_vector(9 DOWNTO 0);
		
		-- LEDG signals
      LEDG			: out std_logic_vector(7 DOWNTO 0);
		
		-- LEDR signals
      LEDR			: out std_logic_vector(9 DOWNTO 0);
		
		-- GPIO signals
      GPIO_IN		: in std_logic_vector(17 DOWNTO 0);
      GPIO_OUT		: out std_logic_vector(17 DOWNTO 0);
		
		--------- HEX0 ---------
		HEX0			: out std_logic_vector(6 DOWNTO 0);

		--------- HEX1 ---------
		HEX1			: out std_logic_vector(6 DOWNTO 0);
		
		--------- HEX2 ---------
		HEX2			: out std_logic_vector(6 DOWNTO 0);
		
		--------- HEX3 ---------
		HEX3			: out std_logic_vector(6 DOWNTO 0)
	);
end entity toplevel;

architecture behaviour of toplevel is

	component clock_generator is
		port (
			refclk   : in  std_logic := '0'; --  refclk.clk
			rst      : in  std_logic := '0'; --   reset.reset
			outclk_0 : out std_logic;        -- outclk0.clk
			locked   : out std_logic         --  locked.export
		);
	end component;
	
	component riscv_soc 
		port ( 
			i_rst    : in std_logic;
			i_clk  	: in std_logic;
			--! GPIO.
			--i_gpio     : in std_logic_vector(11 downto 0);
			--o_gpio     : out std_logic_vector(11 downto 0);
			--o_gpio_dir : out std_logic_vector(11 downto 0);
			--! JTAG signals:
			--i_jtag_tck : in std_logic;
			--i_jtag_ntrst : in std_logic;
			--i_jtag_tms : in std_logic;
			--i_jtag_tdi : in std_logic;
			--o_jtag_tdo : out std_logic;
			--o_jtag_vref : out std_logic;
			--! UART1 signals:
			i_uart1_ctsn : in std_logic;
			i_uart1_rd   : in std_logic;
			o_uart1_td   : out std_logic;
			o_uart1_rtsn : out std_logic;
			--! UART2 (debug port) signals:
			--i_uart2_ctsn : in std_logic;
			--i_uart2_rd   : in std_logic;
			--o_uart2_td   : out std_logic;
			--o_uart2_rtsn : out std_logic;
			--! Ethernet MAC PHY interface signals
			--i_etx_clk   : in    std_ulogic;
			--i_erx_clk   : in    std_ulogic;
			--i_erxd      : in    std_logic_vector(3 downto 0);
			--i_erx_dv    : in    std_ulogic;
			--i_erx_er    : in    std_ulogic;
			--i_erx_col   : in    std_ulogic;
			--i_erx_crs   : in    std_ulogic;
			--i_emdint    : in std_ulogic;
			--o_etxd      : out   std_logic_vector(3 downto 0);
			--o_etx_en    : out   std_ulogic;
			--o_etx_er    : out   std_ulogic;
			--o_emdc      : out   std_ulogic;
			--i_eth_mdio    : in std_logic;
			--o_eth_mdio    : out std_logic;
			--o_eth_mdio_oe : out std_logic;
			--i_eth_gtx_clk    : in std_logic;
			--i_eth_gtx_clk_90 : in std_logic;
			--o_erstn     : out   std_ulogic
			KEY			: in std_logic_vector(3 DOWNTO 0);
			SW				: in std_logic_vector(9 DOWNTO 0);
			LEDG			: out std_logic_vector(7 DOWNTO 0);
			LEDR			: out std_logic_vector(9 DOWNTO 0);
			GPIO_IN		: in std_logic_vector(17 DOWNTO 0);
			GPIO_OUT		: out std_logic_vector(17 DOWNTO 0);
			HEX0			: out std_logic_vector(6 DOWNTO 0);
			HEX1			: out std_logic_vector(6 DOWNTO 0);
			HEX2			: out std_logic_vector(6 DOWNTO 0);
			HEX3			: out std_logic_vector(6 DOWNTO 0)
		);
	end component;
	
	signal system_rst				: std_logic;
	signal system_clk				: std_logic;
	signal system_clk_locked	: std_logic;
	signal system_gnd				: std_logic;
	
begin
	
	system_rst <= reset or not(system_clk_locked);
	system_gnd <= '0';
	
	clkgen: clock_generator
		port map(
			refclk	=> clk,
			rst 		=> reset,
			outclk_0	=> system_clk,
			locked 	=> system_clk_locked
		);
	
	riscv_soc0: riscv_soc
		port map(
			i_rst				=> system_rst,
			i_clk				=> system_clk,
			i_uart1_ctsn	=> system_gnd,
			i_uart1_rd		=> uart0_rxd,
			o_uart1_td		=>	uart0_txd,
			o_uart1_rtsn	=> open,
			KEY				=> KEY,
			SW					=> SW,
			LEDG				=> LEDG,
			LEDR				=> LEDR,
			GPIO_IN			=> GPIO_IN,
			GPIO_OUT			=> GPIO_OUT,
			HEX0				=> HEX0,
			HEX1				=> HEX1,
			HEX2				=> HEX2,
			HEX3				=> HEX3
			
		);
		
end architecture behaviour;