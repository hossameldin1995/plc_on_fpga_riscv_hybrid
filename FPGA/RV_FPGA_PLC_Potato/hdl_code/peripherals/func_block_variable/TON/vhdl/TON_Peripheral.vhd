-- The Potato Processor - A simple processor for FPGAs
-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>
-- Report bugs and issues on <https://github.com/skordal/potato/issues>

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

--! @brief Simple memory module for use in Wishbone-based systems.
--!
--! The following registers are defined:
--! |---------|-------------------|
--! | Address | Description       |
--! |---------|-------------------|
--! | 0x00 r  | Elapsed Time L    |
--! | 0x04 r  | Elapsed Time H    |
--! | 0x08 r  | Q                 |
--! | 0x00 w  | Preset  Time L    |
--! | 0x04 w  | Preset  Time H    |
--! | 0x08 w  | IN                |
--! |---------|-------------------|

entity TON_Peripheral is
	port(
		clk : in std_logic;
		reset : in std_logic;

		-- Wishbone interface:
		wb_adr_in  : in  std_logic_vector(1 downto 0);
		wb_dat_in  : in  std_logic_vector(31 downto 0);
		wb_dat_out : out std_logic_vector(31 downto 0);
		wb_cyc_in  : in  std_logic;
		wb_stb_in  : in  std_logic;
		wb_sel_in  : in  std_logic_vector( 3 downto 0);
		wb_we_in   : in  std_logic;
		wb_ack_out : out std_logic
	);
end entity TON_Peripheral;

architecture behaviour of TON_Peripheral is
	type state_type is (IDLE, ACK);
	signal state : state_type;

	signal read_ack : std_logic;
	
	SIGNAL IN_T, Q   : std_logic;
	SIGNAL PT, ET    : std_logic_vector(63 DOWNTO 0);
	
begin

	Counter: entity work.Counter_Down_64_bit_Cin  PORT MAP(IN_T, clk, PT, Q, ET);
	
	wb_ack_out <= read_ack and wb_stb_in;

	process(reset, wb_adr_in, wb_dat_in, wb_cyc_in, wb_stb_in, wb_sel_in, wb_we_in)
	begin
		--if rising_edge(clk) then add clk in  process and remove others
			if reset = '1' then
				read_ack <= '0';
				state <= IDLE;
				wb_dat_out <= (OTHERS => '0');
				PT       <= (OTHERS => '0');
				IN_T     <= '0';
			else
				if wb_cyc_in = '1' then
					case state is
						when IDLE =>
							if wb_stb_in = '1' and wb_we_in = '1' then --write
								IF (wb_adr_in = "00") THEN
									PT(31 DOWNTO 0)  <= wb_dat_in;
								ELSIF (wb_adr_in = "01") THEN
									PT(63 DOWNTO 32) <= wb_dat_in;
								ELSIF (wb_adr_in = "10") THEN
									IN_T <= wb_dat_in(0);
								END IF;
								read_ack <= '1';
								state <= ACK;
							elsif wb_stb_in = '1' then -- read
								IF (wb_adr_in = "00") THEN
									wb_dat_out  <= ET(31 DOWNTO 0);
								ELSIF (wb_adr_in = "01") THEN
									wb_dat_out <= ET(63 DOWNTO 32);
								ELSIF (wb_adr_in = "10") THEN
									wb_dat_out(0) <= Q;
								ELSE
									wb_dat_out <= (others => '0');
								END IF;
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
		--end if;
	end process;

end architecture behaviour;