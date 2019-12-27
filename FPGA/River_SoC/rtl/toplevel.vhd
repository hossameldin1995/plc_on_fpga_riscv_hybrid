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
			outclk_1 : out std_logic;        -- outclk0.clk
			locked   : out std_logic         --  locked.export
		);
	end component;
	
	signal system_rst				: std_logic;
	signal system_clk				: std_logic;
	signal clk_pwm					: std_logic;
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
			outclk_1	=> clk_pwm,
			locked 	=> system_clk_locked
		);
	
	riscv_soc0: entity work.riscv_soc
		port map(
			i_rst				=> system_rst,
			i_clk				=> system_clk,
			i_clk_pwm		=> clk_pwm,
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