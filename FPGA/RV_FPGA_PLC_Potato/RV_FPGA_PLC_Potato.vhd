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

			-- GPIOs:
			-- 4x LEDs        (bits 11 downto 8)
			-- 4x Switches    (bits  7 downto 4)
			-- 4x Buttons     (bits  3 downto 0)
			gpio_pins : inout std_logic_vector(11 downto 0);

			-- UART0 signals:
			uart0_txd : out std_logic;
			uart0_rxd : in  std_logic;

			-- UART1 signals:
			uart1_txd : out std_logic;
			uart1_rxd : in  std_logic
		);
	end component;
	
	signal KEY : std_logic_vector(3 DOWNTO 0);
	
	signal uart1_txd : std_logic;
	signal uart1_rxd : std_logic;
	
	signal gpio_cpu : std_logic_vector(11 downto 0);
	signal controle : std_logic;
	
	begin
		
		KEY <= not(KEY_n);
		
		controle <= '1';
		gpio_cpu(7 downto 0) <= SW(3 downto 0) & KEY when controle = '1' else (others => 'Z');
		LEDG(3 downto 0) <= gpio_cpu(11 downto 8);
		
		Potato_SoC : toplevel
		port map (
			clk     => CLOCK_125_p,
			reset_n => CPU_RESET_n,

			-- GPIOs:
			-- 4x LEDs        (bits 11 downto 8)
			-- 4x Switches    (bits  7 downto 4)
			-- 4x Buttons     (bits  3 downto 0)
			gpio_pins => gpio_cpu,

			-- UART0 signals:
			uart0_txd => UART_TX,
			uart0_rxd => UART_RX,

			-- UART1 signals:
			uart1_txd => uart1_txd,
			uart1_rxd => uart1_rxd
		);
		
end architecture;
