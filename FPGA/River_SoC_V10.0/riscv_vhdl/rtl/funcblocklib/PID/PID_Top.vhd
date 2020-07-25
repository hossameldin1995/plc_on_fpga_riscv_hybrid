library ieee;
use ieee.std_logic_1164.all;
USE ieee.std_logic_unsigned.ALL;
USE ieee.numeric_std.ALL;

entity PID_Top is
	port(
		clk	:  in std_logic;
		nrst	:  in std_logic;
		
		TS		:  in std_logic_vector(63 DOWNTO 0);
		
		PV		:  in std_logic_vector(31 DOWNTO 0);
		SP		:  in std_logic_vector(31 DOWNTO 0);
		
		b0		:  in std_logic_vector(31 DOWNTO 0);
		b1		:  in std_logic_vector(31 DOWNTO 0);
		b2		:  in std_logic_vector(31 DOWNTO 0);
		
		XOUT	: out std_logic_vector(31 DOWNTO 0);
		XOUT_R: out std_logic								-- The output is ready
	);
end entity PID_Top;

architecture behaviour of PID_Top is
	
	component FP_ADD_32 is
		port (
			clk    : in  std_logic                     := 'X';             -- clk
			areset : in  std_logic                     := 'X';             -- reset
			a      : in  std_logic_vector(31 downto 0) := (others => 'X'); -- a
			b      : in  std_logic_vector(31 downto 0) := (others => 'X'); -- b
			q      : out std_logic_vector(31 downto 0)                     -- q
		);
	end component FP_ADD_32;
	
	component FP_SUB_32 is
		port (
			clk    : in  std_logic                     := 'X';             -- clk
			areset : in  std_logic                     := 'X';             -- reset
			a      : in  std_logic_vector(31 downto 0) := (others => 'X'); -- a
			b      : in  std_logic_vector(31 downto 0) := (others => 'X'); -- b
			q      : out std_logic_vector(31 downto 0)                     -- q
		);
	end component FP_SUB_32;
	
	component FP_MUL_32 is
		port (
			clk    : in  std_logic                     := 'X';             -- clk
			areset : in  std_logic                     := 'X';             -- reset
			a      : in  std_logic_vector(31 downto 0) := (others => 'X'); -- a
			b      : in  std_logic_vector(31 downto 0) := (others => 'X'); -- b
			q      : out std_logic_vector(31 downto 0)                     -- q
		);
	end component FP_MUL_32;

	signal T_XOUT, T_XOUT_1		: std_logic_vector(31 DOWNTO 0) := X"00000000";
	signal S_XOUT, S_XOUT_1		: std_logic_vector(31 DOWNTO 0) := X"00000000";
	
	signal T_E, T_E_1, T_E_2	: std_logic_vector(31 DOWNTO 0) := X"00000000";
	signal S_E, S_E_1, S_E_2	: std_logic_vector(31 DOWNTO 0) := X"00000000";
	
	signal MUL_1, MUL_2, MUL_3	: std_logic_vector(31 DOWNTO 0) := X"00000000";
	
	signal ADD_1, ADD_2			: std_logic_vector(31 DOWNTO 0) := X"00000000";
	
	signal Stage_Counter			: std_logic_vector(4  DOWNTO 0) := "00000";
	signal clk_Counter			: std_logic_vector(63 DOWNTO 0) := X"0000000000000000";
	signal Start_Calculating	: std_logic := '0';
	
	signal rst	: std_logic := '0';


begin

	rst <= not(nrst);
	
	--------------------------------------------------
	-- Stage 1 (Error calculation)
	--------------------------------------------------
	-- e(n) = SP - PV
	Error_Calc : FP_SUB_32
	port map (
		clk    => clk,
		areset => rst,
		a      => SP,
		b      => PV,
		q      => T_E
	);
	
	
	
	--------------------------------------------------
	-- Stage 2 (PID calculation)
	--------------------------------------------------
	-- mul_1 = b0 * e(n)
	MUL_1_Calc : FP_MUL_32
	port map (
		clk    => clk,
		areset => rst,
		a      => b0,
		b      => S_E,
		q      => MUL_1
	);
	-- mul_2 = b1 * e(n-1)
	MUL_2_Calc : FP_MUL_32
	port map (
		clk    => clk,
		areset => rst,
		a      => b1,
		b      => S_E_1,
		q      => MUL_2
	);
	-- mul_3 = b2 * e(n-2)
	MUL_3_Calc : FP_MUL_32
	port map (
		clk    => clk,
		areset => rst,
		a      => b2,
		b      => S_E_2,
		q      => MUL_3
	);
	
	
	
	--------------------------------------------------
	-- Stage 3 (Adding PID values)
	--------------------------------------------------
	-- add_1 = mul_1 + mul_2
	ADD_1_Calc : FP_ADD_32
	port map (
		clk    => clk,
		areset => rst,
		a      => MUL_1,
		b      => MUL_2,
		q      => ADD_1
	);
	-- add_2 = mul_3 + u(n-1)
	ADD_2_Calc : FP_ADD_32
	port map (
		clk    => clk,
		areset => rst,
		a      => MUL_3,
		b      => S_XOUT_1,
		q      => ADD_2
	);
	
	--------------------------------------------------
	-- Stage 4 (Adding PID values)
	--------------------------------------------------
	-- add_all = add_1 + add_2
	XOUT_Calc : FP_ADD_32
	port map (
		clk    => clk,
		areset => rst,
		a      => ADD_1,
		b      => ADD_2,
		q      => T_XOUT
	);
	
	
	
	--------------------------------------------------
	-- Stage Controller
	--------------------------------------------------
	process(clk, rst)
	begin
		if rst = '1' then
			XOUT_R	<= '0';
			S_XOUT	<= X"00000000";
			S_XOUT_1	<= X"00000000";
			
			S_E		<= X"00000000";
			S_E_1		<= X"00000000";
			S_E_2		<= X"00000000";
			
			Stage_Counter		<= "00000";
			clk_Counter			<= X"0000000000000000";
			Start_Calculating	<= '0';
		elsif rising_edge(clk) then
			if TS /=  X"0000000000000000" then
				clk_Counter		<= clk_Counter + X"0000000000000001";
			end if;
			
			if clk_Counter = TS and TS /=  X"0000000000000000" then
				Start_Calculating	<= '1';
				XOUT_R 				<= '0';
				Stage_Counter		<= "00000";
				clk_Counter			<= X"0000000000000000";
			end if;
			
			if Start_Calculating = '1' then
				Stage_Counter	<= Stage_Counter + "00001";
			end if;
				
			if Stage_Counter = "00101" then -- 5
				S_E	<= T_E;		-- e(n)
				S_E_1	<= S_E;		-- e(n-1) = e(0)
				S_E_2	<= S_E_1;	-- e(n-2) = e(n-1)
			elsif stage_counter = "10100" then -- 20
				S_XOUT	<= T_XOUT;
				S_XOUT_1	<= T_XOUT;
				XOUT_R	<= '1';
			elsif stage_counter = "10101" then -- 21
				XOUT_R	<= '0';
				Start_Calculating <= '0';
			end if;
		end if;
		
	end process;
	
	XOUT <= S_XOUT;
end architecture;