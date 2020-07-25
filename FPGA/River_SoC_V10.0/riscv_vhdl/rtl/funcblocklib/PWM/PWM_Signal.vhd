library IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

--------------------------------
-- T_Count = clk_Hz / Frq
-- Comp_Count_0 = (T_Count/100)
-- Comp_Count = Comp_Count_0 * DC
--------------------------------

entity PWM_Signal is
port( nrst			: in STD_LOGIC;
		Frq			: in STD_LOGIC_VECTOR(16 downto 0);
		DC				: in STD_LOGIC_VECTOR(6 downto 0);
		T_Count		: out STD_LOGIC_VECTOR(19 downto 0);
		Comp_Count	: out STD_LOGIC_VECTOR(19 downto 0)
		);
end;

architecture RTL of PWM_Signal is
	
	signal T_Count_T, Comp_Count_0	: std_logic_vector(19 downto 0);
	signal Frq_T	: std_logic_vector(16 downto 0);
	signal result			: std_logic_vector(26 downto 0);
	
	begin
	
	Comp_Count	<= result(19 downto 0);
	T_Count	<= T_Count_T;
	
	Div1: entity work.div_int_20_17_speed	PORT MAP(Frq_T, X"F4240", T_Count_T, open);	-- T_Count = clk_Hz / Frq
	Div2: entity work.div_int_20_7_speed	PORT MAP("1100100", T_Count_T, Comp_Count_0, open);	-- Comp_Count_0 = (T_Count/100)
	--Div: 	entity work.div_int_32_t	PORT MAP(denom, numer, result_div, open);
	Mult: entity work.mul_int_20_7	PORT MAP(Comp_Count_0, DC, result);							-- Comp_Count = Comp_Count_0 * DC
	
	process(Frq)
	begin
		if( Frq = "00000000000000000") or (nrst = '0') then
			Frq_T <= (others => '1');
		else
			Frq_T <= Frq;
		end if;
	end process;
end RTL;