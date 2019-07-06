library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

--! The following registers are defined:
--! |---------|-------------------|
--! | Address | Description       |
--! |---------|-------------------|
--! | 0b00    | START_STOP_A      |
--! | 0b01    | MICRO_NANO_A      |
--! | 0b10    | READ_TIME_A       |
--! | 0b11    |                   |
--! |---------|-------------------|

entity Time_Measurement_Peripheral is
	port(
		clk 			: in std_logic;
		reset 		: in std_logic;
		
		-- Wishbone interface:
		wb_adr_in 	: in  std_logic_vector(1 downto 0);
		wb_dat_in 	: in  std_logic_vector(31 downto 0);
		wb_dat_out 	: out std_logic_vector(31 downto 0) := (others => '0');
		wb_cyc_in 	: in  std_logic;
		wb_stb_in 	: in  std_logic;
		wb_sel_in 	: in  std_logic_vector( 3 downto 0);
		wb_we_in 	: in  std_logic;
		wb_ack_out 	: out std_logic;
		
		-- HEX interface
		HEX0			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		HEX1			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		HEX2			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		HEX3			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
	);
end entity Time_Measurement_Peripheral;

architecture behaviour of Time_Measurement_Peripheral is
	type state_type is (IDLE, ACK);
	signal state : state_type;
	
	--signal Time_Micro_Nano		: std_logic_vector(31 downto 0);
	--signal Time_Micro_Nano_S	: std_logic_vector(31 downto 0);
	signal Micro_Nano				: std_logic := '0'; -- Micro = 1, Nano = 0
	signal Start_Stop				: std_logic := '0'; -- Start = 1, Stop = 0
	
	signal read_ack : std_logic;

begin

	wb_ack_out <= read_ack and wb_stb_in;

	process(clk, reset, wb_adr_in, wb_stb_in, wb_we_in)
	begin
		if rising_edge(clk) then
			if reset = '1' then
				--Time_micro_Nano <= (others => '0');
				
				read_ack <= '0';
				state <= IDLE;
			else
				if wb_cyc_in = '1' then
					case state is
						when IDLE =>
							if wb_stb_in = '1' and wb_we_in = '1' then -- write
								if wb_adr_in = "00" then -- start or  stop
									if wb_dat_in(7 downto 0) = X"71" then
										Start_Stop <= '1';
									elsif wb_dat_in(7 downto 0) = X"53" then
										Start_Stop <= '0';
									end if;
								elsif wb_adr_in = "01" then
									if wb_dat_in(7 downto 0) = X"36" then
										Micro_Nano <= '1';
									elsif wb_dat_in(7 downto 0) = X"42" then
										Micro_Nano <= '0';
									end if;
								end if;
								read_ack <= '1';
								state <= ACK;
							elsif wb_stb_in = '1' then -- read
								if wb_adr_in = "10" then
									--wb_dat_out <= Time_Micro_Nano;
								end if;
								read_ack <= '1';
								state <= ACK;
							end if;
						when ACK =>
							if wb_stb_in = '0' then
								read_ack <= '0';
								state <= IDLE;
							end if;
					end case;
				else
					state <= IDLE;
					read_ack <= '0';
				end if;
			end if;
			--Time_Micro_Nano <= Time_Micro_Nano_S;
		end if;
		
	end process;

	TimeCalculation: entity work.Time_Calculation
		port map(
			clk 			=> clk,
			reset 		=> reset,
			
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
		
end architecture behaviour;