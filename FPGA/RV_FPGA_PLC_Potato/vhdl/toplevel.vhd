-- The Potato Processor - SoC design for the Arty FPGA board
-- (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>
-- Report bugs and issues on <https://github.com/skordal/potato/issues>

library ieee;
use ieee.std_logic_1164.all;

-- This is a SoC design for the Arty development board. It has the following memory layout:
--
-- 0x00001000: UART0 (for host communication)
-- 0x00002000: Timer0
-- 0x00003000: Timer1
-- 0x00004000: IO Peripheral
-- 0x00005000: Time Measurement
-- 0x00006000: TON_0
-- 0x10000000: Interconnect control/error module
-- 0xffff8000: Application execution environment ROM (16 kB)
-- 0xffffc000: Application execution environment RAM (16 kB)
entity toplevel is
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
end entity toplevel;

architecture behaviour of toplevel is

	component clock_generator is
		port (
			refclk   : in  std_logic := '0'; --  refclk.clk
			rst      : in  std_logic := '0'; --   reset.reset
			outclk_0 : out std_logic;        -- outclk0.clk
			outclk_1 : out std_logic;        -- outclk1.clk
			locked   : out std_logic         --  locked.export
		);
	end component;
	
	-- Reset signals:
	signal reset : std_logic;

	-- Internal clock signals:
	signal system_clk : std_logic;
	signal timer_clk  : std_logic;
	signal system_clk_locked : std_logic;

	-- Interrupt indices:
	constant IRQ_TIMER0_INDEX    : natural := 0;
	constant IRQ_TIMER1_INDEX    : natural := 1;
	constant IRQ_UART0_INDEX     : natural := 2;
	constant IRQ_UART1_INDEX     : natural := 3;
	constant IRQ_BUS_ERROR_INDEX : natural := 4;

	-- Interrupt signals:
	signal irq_array : std_logic_vector(7 downto 0);
	signal timer0_irq, timer1_irq : std_logic;
	signal uart0_irq, uart1_irq   : std_logic;
	signal intercon_irq_bus_error : std_logic;

	-- Processor signals:
	signal processor_adr_out : std_logic_vector(31 downto 0);
	signal processor_sel_out : std_logic_vector(3 downto 0);
	signal processor_cyc_out : std_logic;
	signal processor_stb_out : std_logic;
	signal processor_we_out  : std_logic;
	signal processor_dat_out : std_logic_vector(31 downto 0);
	signal processor_dat_in  : std_logic_vector(31 downto 0);
	signal processor_ack_in  : std_logic;

	-- Timer0 signals:
	signal timer0_adr_in : std_logic_vector(11 downto 0);
	signal timer0_dat_in : std_logic_vector(31 downto 0);
	signal timer0_dat_out : std_logic_vector(31 downto 0);
	signal timer0_cyc_in : std_logic;
	signal timer0_stb_in : std_logic;
	signal timer0_we_in : std_logic;
	signal timer0_ack_out : std_logic;

	-- Timer1 signals:
	signal timer1_adr_in : std_logic_vector(11 downto 0);
	signal timer1_dat_in : std_logic_vector(31 downto 0);
	signal timer1_dat_out : std_logic_vector(31 downto 0);
	signal timer1_cyc_in : std_logic;
	signal timer1_stb_in : std_logic;
	signal timer1_we_in : std_logic;
	signal timer1_ack_out : std_logic;

	-- UART0 signals:
	signal uart0_adr_in  : std_logic_vector(11 downto 0);
	signal uart0_dat_in  : std_logic_vector( 7 downto 0);
	signal uart0_dat_out : std_logic_vector( 7 downto 0);
	signal uart0_cyc_in  : std_logic;
	signal uart0_stb_in  : std_logic;
	signal uart0_we_in   : std_logic;
	signal uart0_ack_out : std_logic;

	-- Interconnect control module:
	signal intercon_adr_in  : std_logic_vector(11 downto 0);
	signal intercon_dat_in  : std_logic_vector(31 downto 0);
	signal intercon_dat_out : std_logic_vector(31 downto 0);
	signal intercon_cyc_in  : std_logic;
	signal intercon_stb_in  : std_logic;
	signal intercon_we_in   : std_logic;
	signal intercon_ack_out : std_logic;

	-- Interconnect error module:
	signal error_adr_in  : std_logic_vector(31 downto 0);
	signal error_dat_in  : std_logic_vector(31 downto 0);
	signal error_dat_out : std_logic_vector(31 downto 0);
	signal error_sel_in  : std_logic_vector( 3 downto 0);
	signal error_cyc_in  : std_logic;
	signal error_stb_in  : std_logic;
	signal error_we_in   : std_logic;
	signal error_ack_out : std_logic;

	-- AEE ROM signals:
	signal aee_rom_adr_in  : std_logic_vector(13 downto 0);
	signal aee_rom_dat_out : std_logic_vector(31 downto 0);
	signal aee_rom_cyc_in  : std_logic;
	signal aee_rom_stb_in  : std_logic;
	signal aee_rom_sel_in  : std_logic_vector(3 downto 0);
	signal aee_rom_ack_out : std_logic;

	-- AEE RAM signals:
	signal aee_ram_adr_in  : std_logic_vector(13 downto 0);
	signal aee_ram_dat_in  : std_logic_vector(31 downto 0);
	signal aee_ram_dat_out : std_logic_vector(31 downto 0);
	signal aee_ram_cyc_in  : std_logic;
	signal aee_ram_stb_in  : std_logic;
	signal aee_ram_sel_in  : std_logic_vector(3 downto 0);
	signal aee_ram_we_in   : std_logic;
	signal aee_ram_ack_out : std_logic;

	-- IO_peripheral signals:
	signal io_peripheral_adr_in  : std_logic_vector(13 downto 0);
	signal io_peripheral_dat_in  : std_logic_vector(31 downto 0);
	signal io_peripheral_dat_out : std_logic_vector(31 downto 0);
	signal io_peripheral_cyc_in  : std_logic;
	signal io_peripheral_stb_in  : std_logic;
	signal io_peripheral_sel_in  : std_logic_vector(3 downto 0);
	signal io_peripheral_we_in   : std_logic;
	signal io_peripheral_ack_out : std_logic;
	
	-- Time_Measurement signals:
	signal time_measurement_adr_in  : std_logic_vector(13 downto 0);
	signal time_measurement_dat_in  : std_logic_vector(31 downto 0);
	signal time_measurement_dat_out : std_logic_vector(31 downto 0);
	signal time_measurement_cyc_in  : std_logic;
	signal time_measurement_stb_in  : std_logic;
	signal time_measurement_sel_in  : std_logic_vector(3 downto 0);
	signal time_measurement_we_in   : std_logic;
	signal time_measurement_ack_out : std_logic;
	
	-- TON_0 signals:
	signal TON_0_adr_in  : std_logic_vector(13 downto 0);
	signal TON_0_dat_in  : std_logic_vector(31 downto 0);
	signal TON_0_dat_out : std_logic_vector(31 downto 0);
	signal TON_0_cyc_in  : std_logic;
	signal TON_0_stb_in  : std_logic;
	signal TON_0_sel_in  : std_logic_vector(3 downto 0);
	signal TON_0_we_in   : std_logic;
	signal TON_0_ack_out : std_logic;
	
	-- Selected peripheral on the interconnect:
	type intercon_peripheral_type is (
		PERIPHERAL_TIMER0, PERIPHERAL_TIMER1, PERIPHERAL_IO, PERIPHERAL_TON_0, 
		PERIPHERAL_UART0, PERIPHERAL_AEE_ROM, PERIPHERAL_AEE_RAM, TIME_MEASUREMENT, 
		PERIPHERAL_INTERCON, PERIPHERAL_ERROR, PERIPHERAL_NONE);
	signal intercon_peripheral : intercon_peripheral_type := PERIPHERAL_NONE;

	-- Interconnect address decoder state:
	signal intercon_busy : boolean := false;
	
	-- HOSSAM 
	signal not_reset_n : std_logic;
begin

	not_reset_n <= not(reset_n);
	
	irq_array <= (
			IRQ_TIMER0_INDEX => timer0_irq,
			IRQ_TIMER1_INDEX => timer1_irq,
			IRQ_UART0_INDEX => uart0_irq,
			IRQ_UART1_INDEX => uart1_irq,
			IRQ_BUS_ERROR_INDEX => intercon_irq_bus_error,
			others => '0'
		);

	address_decoder: process(system_clk)
	begin
		if rising_edge(system_clk) then
			if reset = '1' then
				intercon_peripheral <= PERIPHERAL_NONE;
				intercon_busy <= false;
			else
				if not intercon_busy then
					if processor_cyc_out = '1' then
						intercon_busy <= true;

						if processor_adr_out(31 downto 28) = x"0" then -- Peripheral memory space
							case processor_adr_out(19 downto 12) is
								when x"01" =>
									intercon_peripheral <= PERIPHERAL_UART0;
								when x"02" =>
									intercon_peripheral <= PERIPHERAL_TIMER0;
								when x"03" =>
									intercon_peripheral <= PERIPHERAL_TIMER1;
								when x"04" =>
									intercon_peripheral <= PERIPHERAL_IO;
								when x"05" =>
									intercon_peripheral <= TIME_MEASUREMENT;
								when x"06" =>
									intercon_peripheral <= PERIPHERAL_TON_0;
								when others => -- Invalid address - delegated to the error peripheral
									intercon_peripheral <= PERIPHERAL_ERROR;
							end case;
						elsif processor_adr_out(31 downto 28) = x"1" then
							intercon_peripheral <= PERIPHERAL_INTERCON;
						elsif processor_adr_out(31 downto 28) = x"F" then -- Firmware memory space
							if processor_adr_out(15 downto 14) = b"10" then    -- AEE ROM
								intercon_peripheral <= PERIPHERAL_AEE_ROM;
							elsif processor_adr_out(15 downto 14) = b"11" then -- AEE RAM
								intercon_peripheral <= PERIPHERAL_AEE_RAM;
							end if;
						else
							intercon_peripheral <= PERIPHERAL_ERROR;
						end if;
					else
						intercon_peripheral <= PERIPHERAL_NONE;
					end if;
				else
					if processor_cyc_out = '0' then
						intercon_busy <= false;
						intercon_peripheral <= PERIPHERAL_NONE;
					end if;
				end if;
			end if;
		end if;
	end process address_decoder;

	processor_intercon: process(intercon_peripheral,
		timer0_ack_out, timer0_dat_out, timer1_ack_out, timer1_dat_out,
		uart0_ack_out, uart0_dat_out,
		intercon_ack_out, intercon_dat_out, error_ack_out,
		aee_rom_ack_out, aee_rom_dat_out, aee_ram_ack_out, aee_ram_dat_out,
		io_peripheral_ack_out, io_peripheral_dat_out, TON_0_ack_out, TON_0_dat_out)
	begin
		case intercon_peripheral is
			when PERIPHERAL_TIMER0 =>
				processor_ack_in <= timer0_ack_out;
				processor_dat_in <= timer0_dat_out;
			when PERIPHERAL_TIMER1 =>
				processor_ack_in <= timer1_ack_out;
				processor_dat_in <= timer1_dat_out;
			when PERIPHERAL_UART0 =>
				processor_ack_in <= uart0_ack_out;
				processor_dat_in <= x"000000" & uart0_dat_out;
			when PERIPHERAL_INTERCON =>
				processor_ack_in <= intercon_ack_out;
				processor_dat_in <= intercon_dat_out;
			when PERIPHERAL_AEE_ROM =>
				processor_ack_in <= aee_rom_ack_out;
				processor_dat_in <= aee_rom_dat_out;
			when PERIPHERAL_AEE_RAM =>
				processor_ack_in <= aee_ram_ack_out;
				processor_dat_in <= aee_ram_dat_out;
			when PERIPHERAL_IO =>
				processor_ack_in <= io_peripheral_ack_out;
				processor_dat_in <= io_peripheral_dat_out;
			when TIME_MEASUREMENT =>
				processor_ack_in <= time_measurement_ack_out;
				processor_dat_in <= time_measurement_dat_out;
			when PERIPHERAL_TON_0 =>
				processor_ack_in <= TON_0_ack_out;
				processor_dat_in <= TON_0_dat_out;
			when PERIPHERAL_ERROR =>
				processor_ack_in <= error_ack_out;
				processor_dat_in <= (others => '0');
			when PERIPHERAL_NONE =>
				processor_ack_in <= '0';
				processor_dat_in <= (others => '0');
		end case;
	end process processor_intercon;

	reset_controller: entity work.pp_soc_reset
		port map(
			clk => clk,
			reset_n => reset_n,
			reset_out => reset,
			system_clk => system_clk,
			system_clk_locked => system_clk_locked
		);
	
	clkgen: clock_generator
		port map(
			refclk => clk,
			rst => not_reset_n,
			outclk_0 => system_clk,
			outclk_1 => timer_clk,
			locked => system_clk_locked
		);

	processor: entity work.pp_potato
		generic map(
			RESET_ADDRESS => x"ffff8000",
			ICACHE_ENABLE => false,
			ICACHE_LINE_SIZE => 128,
			ICACHE_NUM_LINES => 128
		) port map(
			clk => system_clk,
			timer_clk => timer_clk,
			reset => reset,
			irq => irq_array,
			test_context_out => open,
			wb_adr_out => processor_adr_out,
			wb_dat_out => processor_dat_out,
			wb_dat_in => processor_dat_in,
			wb_sel_out => processor_sel_out,
			wb_cyc_out => processor_cyc_out,
			wb_stb_out => processor_stb_out,
			wb_we_out => processor_we_out,
			wb_ack_in => processor_ack_in
		);

	timer0: entity work.pp_soc_timer
		port map(
			clk => system_clk,
			reset => reset,
			irq => timer0_irq,
			wb_adr_in => timer0_adr_in,
			wb_dat_in => timer0_dat_in,
			wb_dat_out => timer0_dat_out,
			wb_cyc_in => timer0_cyc_in,
			wb_stb_in => timer0_stb_in,
			wb_we_in => timer0_we_in,
			wb_ack_out => timer0_ack_out
		);
	timer0_adr_in <= processor_adr_out(timer0_adr_in'range);
	timer0_dat_in <= processor_dat_out;
	timer0_we_in <= processor_we_out;
	timer0_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_TIMER0 else '0';
	timer0_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_TIMER0 else '0';

	timer1: entity work.pp_soc_timer
		port map(
			clk => system_clk,
			reset => reset,
			irq => timer1_irq,
			wb_adr_in => timer1_adr_in,
			wb_dat_in => timer1_dat_in,
			wb_dat_out => timer1_dat_out,
			wb_cyc_in => timer1_cyc_in,
			wb_stb_in => timer1_stb_in,
			wb_we_in => timer1_we_in,
			wb_ack_out => timer1_ack_out
		);
	timer1_adr_in <= processor_adr_out(timer1_adr_in'range);
	timer1_dat_in <= processor_dat_out;
	timer1_we_in  <= processor_we_out;
	timer1_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_TIMER1 else '0';
	timer1_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_TIMER1 else '0';
	
	uart0: entity work.pp_soc_uart
		generic map(
			FIFO_DEPTH => 32
		) port map(
			clk => system_clk,
			reset => reset,
			txd => uart0_txd,
			rxd => uart0_rxd,
			irq => uart0_irq,
			wb_adr_in => uart0_adr_in,
			wb_dat_in => uart0_dat_in,
			wb_dat_out => uart0_dat_out,
			wb_cyc_in => uart0_cyc_in,
			wb_stb_in => uart0_stb_in,
			wb_we_in => uart0_we_in,
			wb_ack_out => uart0_ack_out
		);
	uart0_adr_in <= processor_adr_out(uart0_adr_in'range);
	uart0_dat_in <= processor_dat_out(7 downto 0);
	uart0_we_in  <= processor_we_out;
	uart0_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_UART0 else '0';
	uart0_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_UART0 else '0';

	intercon_error: entity work.pp_soc_intercon
		port map(
			clk => system_clk,
			reset => reset,
			error_irq => intercon_irq_bus_error,
			wb_adr_in => intercon_adr_in,
			wb_dat_in => intercon_dat_in,
			wb_dat_out => intercon_dat_out,
			wb_cyc_in => intercon_cyc_in,
			wb_stb_in => intercon_stb_in,
			wb_we_in => intercon_we_in,
			wb_ack_out => intercon_ack_out,
			err_adr_in => error_adr_in,
			err_dat_in => error_dat_in,
			err_sel_in => error_sel_in,
			err_cyc_in => error_cyc_in,
			err_stb_in => error_stb_in,
			err_we_in => error_we_in,
			err_ack_out => error_ack_out
		);
	intercon_adr_in <= processor_adr_out(intercon_adr_in'range);
	intercon_dat_in <= processor_dat_out;
	intercon_we_in  <= processor_we_out;
	intercon_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_INTERCON else '0';
	intercon_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_INTERCON else '0';
	error_adr_in <= processor_adr_out;
	error_dat_in <= processor_dat_out;
	error_sel_in <= processor_sel_out;
	error_we_in  <= processor_we_out;
	error_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_ERROR else '0';
	error_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_ERROR else '0';

	aee_rom: entity work.aee_rom_wrapper
		generic map(
			MEMORY_SIZE => 16384
		) port map(
			clk => system_clk,
			reset => reset,
			wb_adr_in => aee_rom_adr_in,
			wb_dat_out => aee_rom_dat_out,
			wb_cyc_in => aee_rom_cyc_in,
			wb_stb_in => aee_rom_stb_in,
			wb_sel_in => aee_rom_sel_in,
			wb_ack_out => aee_rom_ack_out
		);
	aee_rom_adr_in <= processor_adr_out(aee_rom_adr_in'range);
	aee_rom_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_AEE_ROM else '0';
	aee_rom_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_AEE_ROM else '0';
	aee_rom_sel_in <= processor_sel_out;

	aee_ram: entity work.pp_soc_memory
		generic map(
			MEMORY_SIZE => 128 --16384
		) port map(
			clk => system_clk,
			reset => reset,
			wb_adr_in => aee_ram_adr_in(6 downto 0),
			wb_dat_in => aee_ram_dat_in,
			wb_dat_out => aee_ram_dat_out,
			wb_cyc_in => aee_ram_cyc_in,
			wb_stb_in => aee_ram_stb_in,
			wb_sel_in => aee_ram_sel_in,
			wb_we_in => aee_ram_we_in,
			wb_ack_out => aee_ram_ack_out
		);
	aee_ram_adr_in <= processor_adr_out(aee_ram_adr_in'range);
	aee_ram_dat_in <= processor_dat_out;
	aee_ram_we_in  <= processor_we_out;
	aee_ram_sel_in <= processor_sel_out;
	aee_ram_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_AEE_RAM else '0';
	aee_ram_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_AEE_RAM else '0';
	
	io_periphral: entity work.In_Out_Peripheral
		port map(
			clk 			=> system_clk,
			reset 		=> reset,
			wb_adr_in 	=> io_peripheral_adr_in(8 downto 2),
			wb_dat_in 	=> io_peripheral_dat_in,
			wb_dat_out 	=> io_peripheral_dat_out,
			wb_cyc_in 	=> io_peripheral_cyc_in,
			wb_stb_in 	=> io_peripheral_stb_in,
			wb_sel_in 	=> io_peripheral_sel_in,
			wb_we_in 	=> io_peripheral_we_in,
			wb_ack_out 	=> io_peripheral_ack_out,
			KEY			=> KEY,
			LEDR			=> LEDR,
			LEDG			=> LEDG,
			SW				=> SW,
			GPIO_OUT		=> GPIO_OUT,
			GPIO_IN		=> GPIO_IN
		);
	io_peripheral_adr_in <= processor_adr_out(io_peripheral_adr_in'range);
	io_peripheral_dat_in <= processor_dat_out;
	io_peripheral_we_in  <= processor_we_out;
	io_peripheral_sel_in <= processor_sel_out;
	io_peripheral_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_IO else '0';
	io_peripheral_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_IO else '0';
	
	time_measurement_p: entity work.Time_Measurement_Peripheral
		port map(
			clk 			=> system_clk,
			reset 		=> reset,
			wb_adr_in 	=> time_measurement_adr_in(3 downto 2),
			wb_dat_in 	=> time_measurement_dat_in,
			wb_dat_out 	=> time_measurement_dat_out,
			wb_cyc_in 	=> time_measurement_cyc_in,
			wb_stb_in 	=> time_measurement_stb_in,
			wb_sel_in 	=> time_measurement_sel_in,
			wb_we_in 	=> time_measurement_we_in,
			wb_ack_out 	=> time_measurement_ack_out,
			HEX0			=> HEX0,
			HEX1			=> HEX1,
			HEX2			=> HEX2,
			HEX3			=> HEX3
		);
	time_measurement_adr_in <= processor_adr_out(time_measurement_adr_in'range);
	time_measurement_dat_in <= processor_dat_out;
	time_measurement_we_in  <= processor_we_out;
	time_measurement_sel_in <= processor_sel_out;
	time_measurement_cyc_in <= processor_cyc_out when intercon_peripheral = TIME_MEASUREMENT else '0';
	time_measurement_stb_in <= processor_stb_out when intercon_peripheral = TIME_MEASUREMENT else '0';

	TON_0: entity work.TON_Peripheral
		port map(
			clk 			=> system_clk,
			reset 		=> reset,
			wb_adr_in 	=> TON_0_adr_in(3 downto 2),
			wb_dat_in 	=> TON_0_dat_in,
			wb_dat_out 	=> TON_0_dat_out,
			wb_cyc_in 	=> TON_0_cyc_in,
			wb_stb_in 	=> TON_0_stb_in,
			wb_sel_in 	=> TON_0_sel_in,
			wb_we_in 	=> TON_0_we_in,
			wb_ack_out 	=> TON_0_ack_out
		);
	TON_0_adr_in <= processor_adr_out(TON_0_adr_in'range);
	TON_0_dat_in <= processor_dat_out;
	TON_0_we_in  <= processor_we_out;
	TON_0_sel_in <= processor_sel_out;
	TON_0_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_TON_0 else '0';
	TON_0_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_TON_0 else '0';
	
end architecture behaviour;
