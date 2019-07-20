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
--! | 0x00 r  | Q                 |
--! | 0x04 w  | Frequency    L    |
--! | 0x08 w  | Duty Cycle        |
--! |---------|-------------------|

entity PWM_Peripheral is
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
end entity PWM_Peripheral;

architecture behaviour of PWM_Peripheral is
	type state_type is (IDLE, ACK);
	signal state : state_type;

	signal read_ack : std_logic;
	
	SIGNAL Q, EN				: std_logic;
	SIGNAL Frq, DC, Frq_T	: std_logic_vector(31 DOWNTO 0);
	
	SIGNAL T_Count, Comp_Count, Comp_Count_T	: STD_LOGIC_VECTOR(31 downto 0);
	SIGNAL Comp_Count_64								: STD_LOGIC_VECTOR(63 downto 0);
begin

	PWM : entity work.PWM_32_bit	PORT MAP(clk, reset, EN, T_Count, Comp_Count, Q);
	Div1: entity work.Div_32_bit	PORT MAP(Frq_T, X"05F5E100", T_Count, open);
	Div2: entity work.Div_32_bit	PORT MAP(X"00000064", T_Count, Comp_Count_T, open);
	Mult: entity work.Mult_32_bit	PORT MAP(Comp_Count_T, DC, Comp_Count_64);
	
	Comp_Count <= Comp_Count_64(31 DOWNTO 0);
	EN			<= '1';
	wb_ack_out <= read_ack and wb_stb_in;
	
	process(Frq)
	begin
		if( Frq = X"00000000") or (reset = '1') then
			Frq_T <= X"05F5E100";
		else
			Frq_T <= Frq;
		end if;
	end process;
	
	process(reset, wb_adr_in, wb_dat_in, wb_cyc_in, wb_stb_in, wb_sel_in, wb_we_in)
	begin
		--if rising_edge(clk) then add clk in  process and remove others
			if reset = '1' then
				read_ack <= '0';
				state <= IDLE;
				wb_dat_out	<= (OTHERS => '0');
				Frq			<= (OTHERS => '0');
				DC				<= (OTHERS => '0');
			else
				if wb_cyc_in = '1' then
					case state is
						when IDLE =>
							if wb_stb_in = '1' and wb_we_in = '1' then --write
								IF (wb_adr_in = "01") THEN
									Frq	<= wb_dat_in;
								ELSIF (wb_adr_in = "10") THEN
									DC		<= wb_dat_in;
								END IF;
								read_ack <= '1';
								state <= ACK;
							elsif wb_stb_in = '1' then -- read
								IF (wb_adr_in = "00") THEN
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
