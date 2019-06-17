library ieee;
use ieee.std_logic_1164.all;

entity RV_FPGA_PLC_Potato is
	port(
		--------- CLOCK ---------
		CLOCK_125_p	: in std_logic;
		CLOCK_50_B3B: in std_logic;
		CLOCK_50_B5B: in std_logic;
		CLOCK_50_B6A: in std_logic;
		CLOCK_50_B7A: in std_logic;
		CLOCK_50_B8A: in std_logic;
		
		--------- CPU ---------
		CPU_RESET_n	: in std_logic;
		
		--------- GPIO ---------
		GPIO_IN		: in std_logic_vector(17 DOWNTO 0);
		GPIO_OUT		: out std_logic_vector(17 DOWNTO 0);
		
		--------- HEX0 ---------
		HEX0			: out std_logic_vector(6 DOWNTO 0);

		--------- HEX1 ---------
		HEX1			: out std_logic_vector(6 DOWNTO 0);
		
		--------- HEX2 ---------
		HEX2			: out std_logic_vector(6 DOWNTO 0);
		
		--------- HEX3 ---------
		HEX3			: out std_logic_vector(6 DOWNTO 0);
		
		--------- KEY ---------
		KEY_n			: in std_logic_vector(3 DOWNTO 0);

		--------- LEDG ---------
		LEDG			: out std_logic_vector(7 DOWNTO 0);

		--------- LEDR ---------
		LEDR			: out std_logic_vector(9 DOWNTO 0);
		
		--------- SW ---------
		SW				: in std_logic_vector(9 DOWNTO 0);

		--------- UART ---------
		UART_RX		: in std_logic;
		UART_TX		: out std_logic
	);
end entity RV_FPGA_PLC_Potato;

architecture behaviour of RV_FPGA_PLC_Potato is

	component toplevel is
		port(
			clk     : in  std_logic;
			reset_n : in  std_logic;

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
	end component;
	
	signal KEY : std_logic_vector(3 DOWNTO 0);
	
	begin
		
		KEY <= not(KEY_n);
		
		Potato_SoC : toplevel
		port map (
			clk     		=> CLOCK_125_p,
			reset_n 		=> CPU_RESET_n,

			-- UART0 signals:
			uart0_txd 	=> UART_TX,
			uart0_rxd 	=> UART_RX,
		
			-- KEY signals
			KEY			=> KEY,
			
			-- SW signals
			SW				=> SW,
			
			-- LEDG signals
			LEDG			=> LEDG,
			
			-- LEDR signals
			LEDR			=> LEDR,
			
			-- GPIO signals
			GPIO_IN		=> GPIO_IN,
			GPIO_OUT		=> GPIO_OUT,
			
			--------- HEX0 ---------
			HEX0			=> HEX0,

			--------- HEX1 ---------
			HEX1			=> HEX1,
			
			--------- HEX2 ---------
			HEX2			=> HEX2,
			
			--------- HEX3 ---------
			HEX3			=> HEX3
		);
		
end architecture;
