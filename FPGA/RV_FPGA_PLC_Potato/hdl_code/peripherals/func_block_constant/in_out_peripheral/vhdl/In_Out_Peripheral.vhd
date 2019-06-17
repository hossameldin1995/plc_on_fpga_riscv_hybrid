library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

use work.pp_utilities.all;

entity In_Out_Peripheral is
	port(
		clk 			: in std_logic;
		reset 		: in std_logic;
		
		-- Wishbone interface:
		wb_adr_in 	: in  std_logic_vector(6 downto 0);
		wb_dat_in 	: in  std_logic_vector(31 downto 0);
		wb_dat_out 	: out std_logic_vector(31 downto 0);
		wb_cyc_in 	: in  std_logic;
		wb_stb_in 	: in  std_logic;
		wb_sel_in 	: in  std_logic_vector( 3 downto 0);
		wb_we_in 	: in  std_logic;
		wb_ack_out 	: out std_logic;
		
		-- I O interface:
		KEY			: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
		LEDR			: OUT STD_LOGIC_VECTOR(9 DOWNTO 0);
		LEDG			: OUT STD_LOGIC_VECTOR(7 DOWNTO 0);
		SW				: IN STD_LOGIC_VECTOR(9 DOWNTO 0);
		GPIO_OUT		: OUT STD_LOGIC_VECTOR(17 DOWNTO 0);
		GPIO_IN		: IN STD_LOGIC_VECTOR(17 DOWNTO 0)
	);
end entity In_Out_Peripheral;

architecture behaviour of In_Out_Peripheral is
	type state_type is (IDLE, ACK);
	signal state : state_type;

	signal read_ack : std_logic;
	
	signal register_in	: STD_LOGIC_VECTOR(63 DOWNTO 0);   -- 32 - 0
	signal register_out	: STD_LOGIC_VECTOR(63 DOWNTO 0);   -- 36 - 0
	signal OUT_Data_1		: STD_LOGIC;

begin

	wb_dat_out <= "0000000000000000000000000000000" & OUT_Data_1;
	wb_ack_out <= read_ack and wb_stb_in;

	process(clk, reset, wb_adr_in, KEY, SW, GPIO_IN, wb_stb_in, wb_we_in)
	begin
		if rising_edge(clk) then
			if reset = '1' then
				register_in  <= (OTHERS => '0');
				register_out <= (OTHERS => '0');
				GPIO_OUT     <= (OTHERS => '0');
				LEDR         <= (OTHERS => '0');
				LEDG         <= (OTHERS => '0');
				OUT_Data_1     <= '0';
			
				read_ack <= '0';
				state <= IDLE;
			else
				if wb_cyc_in = '1' then
					case state is
						when IDLE =>
							if wb_stb_in = '1' and wb_we_in = '1' then -- write
								if wb_adr_in = "1111111" then
									register_in        <= "00000000000000000000000000000000" & KEY & SW & GPIO_IN;
									GPIO_OUT           <= register_out(17 downto 0);
									LEDR               <= register_out(27 downto 18);
									LEDG               <= register_out(35 downto 28);
								else
									register_out(to_integer(unsigned(wb_adr_in(5 downto 0)))) <= wb_dat_in(0);
								end if;
								read_ack <= '1';
								state <= ACK;
							elsif wb_stb_in = '1' then -- read
								if wb_adr_in(6) = '0' then   -- read inputs
									OUT_Data_1 <= register_in(to_integer(unsigned(wb_adr_in(5 downto 0))));
								else                       -- read outputs
									OUT_Data_1 <= register_out(to_integer(unsigned(wb_adr_in(5 downto 0))));
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
		end if;
	end process;

end architecture behaviour;
