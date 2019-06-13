library ieee;
use ieee.std_logic_1164.all;

use work.pp_types.all;
use work.pp_utilities.all;

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
	
	-- Instruction memory signals:
	signal imem_address	: std_logic_vector(31 downto 0);
	signal imem_data    	: std_logic_vector(31 downto 0);
	signal imem_req		: std_logic;
	signal imem_ack		: std_logic;
	signal imem_sel		: std_logic_vector(3 downto 0) := (others => '1');

	-- Data memory signals:
	signal dmem_address   : std_logic_vector(31 downto 0);
	signal dmem_data_in   : std_logic_vector(31 downto 0);
	signal dmem_data_out  : std_logic_vector(31 downto 0);
	signal dmem_data_size : std_logic_vector( 1 downto 0);
	signal dmem_read_req  : std_logic;
	signal dmem_read_ack  : std_logic;
	signal dmem_write_req : std_logic;
	signal dmem_write_ack : std_logic;
	
	-- Interrupt signals:
	signal irq : std_logic_vector(7 downto 0) := "00000000";
	signal timer0_irq, timer1_irq : std_logic;
	signal uart0_irq, uart1_irq   : std_logic;
	signal intercon_irq_bus_error : std_logic;
	
	signal KEY 			: std_logic_vector(3 DOWNTO 0);
	signal CPU_RESET	: std_logic;
	
	signal system_clk				: std_logic;
	signal system_clk_locked	: std_logic;
	signal system_reset			: std_logic;
	signal clock_reset			: std_logic;
	signal timer_clk				: std_logic;
	
	-- Wishbone signals:
	signal dmem_if_inputs	: wishbone_master_inputs;
	signal dmem_if_outputs	: wishbone_master_outputs;
	
	component clock_generator is
		port (
			refclk   : in  std_logic := '0'; --  refclk.clk
			rst      : in  std_logic := '0'; --   reset.reset
			outclk_0 : out std_logic;        -- outclk0.clk
			outclk_1 : out std_logic;        -- outclk1.clk
			locked   : out std_logic         --  locked.export
		);
	end component;
	
	begin
		
		KEY <= not(KEY_n);
		CPU_RESET <= not(CPU_RESET_n);
		system_reset <= not(system_clk_locked) or CPU_RESET;
		clock_reset <= '0';
		
		clkgen: clock_generator
		port map(
			refclk => CLOCK_125_p,
			rst => clock_reset,
			outclk_0 => system_clk,
			outclk_1 => timer_clk,
			locked => system_clk_locked
		);
		
		aee_rom: entity work.aee_rom_wrapper
		generic map(
			MEMORY_SIZE => 16384
		) port map(
			clk => system_clk,
			reset => system_reset,
			wb_adr_in => imem_address(13 downto 0),
			wb_dat_out => imem_data,
			wb_cyc_in => imem_req,
			wb_stb_in => imem_req,
			wb_sel_in => imem_sel,
			wb_ack_out => imem_ack
		);
		
		aee_ram: entity work.pp_soc_memory
		generic map(
			MEMORY_SIZE => 128
		) port map(
			clk => system_clk,
			reset => system_reset,
			wb_adr_in => dmem_address(6 downto 0),
			wb_dat_in => dmem_if_outputs.dat,
			wb_dat_out => dmem_if_inputs.dat,
			wb_cyc_in => dmem_if_outputs.cyc,
			wb_stb_in => dmem_if_outputs.stb,
			wb_sel_in => dmem_if_outputs.sel,
			wb_we_in => dmem_if_outputs.we,
			wb_ack_out => dmem_if_inputs.ack
		);
		
		dmem_if: entity work.pp_wb_adapter
		port map(
			clk => system_clk,
			reset => system_reset,
			mem_address => dmem_address,
			mem_data_in => dmem_data_out,
			mem_data_out => dmem_data_in,
			mem_data_size => dmem_data_size,
			mem_read_req => dmem_read_req,
			mem_read_ack => dmem_read_ack,
			mem_write_req => dmem_write_req,
			mem_write_ack => dmem_write_ack,
			wb_inputs => dmem_if_inputs,
			wb_outputs => dmem_if_outputs
		);
		
		processor: entity work.pp_core
		generic map(
			RESET_ADDRESS => x"ffff8000"
		) port map(
			clk => system_clk,
			reset => system_reset,
			timer_clk => timer_clk,
			imem_address => imem_address,
			imem_data_in => imem_data,
			imem_req => imem_req,
			imem_ack => imem_ack,
			dmem_address => dmem_address,
			dmem_data_in => dmem_data_in,
			dmem_data_out => dmem_data_out,
			dmem_data_size => dmem_data_size,
			dmem_read_req => dmem_read_req,
			dmem_read_ack => dmem_read_ack,
			dmem_write_req => dmem_write_req,
			dmem_write_ack => dmem_write_ack,
			test_context_out => open,
			irq => irq
		);
		
end architecture;
