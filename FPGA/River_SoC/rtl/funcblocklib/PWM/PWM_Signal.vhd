library IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_SIGNED.ALL;

--------------------------------
-- T_Count = clk_Hz / Frq
-- Comp_Count_0 = (T_Count/100)
-- Comp_Count = Comp_Count_0 * DC
--------------------------------

entity PWM_Signal is
port( nrst			: in STD_LOGIC;
		Frq			: in STD_LOGIC_VECTOR(31 downto 0);
		DC				: in STD_LOGIC_VECTOR(31 downto 0);
		T_Count		: out STD_LOGIC_VECTOR(31 downto 0);
		Comp_Count	: out STD_LOGIC_VECTOR(31 downto 0)
		);
end;

architecture RTL of PWM_Signal is
	
	signal Comp_Count_0, T_Count_T, Frq_T	: std_logic_vector(31 downto 0);
	--signal denom, numer, result_div	: std_logic_vector(31 downto 0);
	signal result			: std_logic_vector(63 downto 0);
	--signal stage			: std_logic;
	begin
	
	Comp_Count	<= result(31 downto 0);
	T_Count	<= T_Count_T;
	
	Div1: entity work.div_int_32_speed	PORT MAP(Frq_T, X"00989680", T_Count_T, open);	-- T_Count = clk_Hz / Frq
	Div2: entity work.div_int_32_speed	PORT MAP(X"00000064", T_Count_T, Comp_Count_0, open);	-- Comp_Count_0 = (T_Count/100)
	--Div: 	entity work.div_int_32_t	PORT MAP(denom, numer, result_div, open);
	Mult: entity work.mul_int_32	PORT MAP(Comp_Count_0, DC, result);							-- Comp_Count = Comp_Count_0 * DC
	
	process(Frq)
	begin
		if( Frq = X"00000000") or (nrst = '0') then
			Frq_T <= X"00989680";
		else
			Frq_T <= Frq;
		end if;
	end process;
end RTL;
