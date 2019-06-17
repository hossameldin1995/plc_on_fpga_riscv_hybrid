LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;

ENTITY Timer_on_64_Controller IS
	PORT( 
		clk		: IN STD_LOGIC;
		reset		: IN STD_LOGIC;
		EN			: IN STD_LOGIC;
		MW			: IN STD_LOGIC;
		Address	: IN STD_LOGIC_VECTOR(1 DOWNTO 0);
		Data_In	: IN STD_LOGIC_VECTOR(31 DOWNTO 0);
		Data_Out	: OUT STD_LOGIC_VECTOR(31 DOWNTO 0)
	);
END Timer_on_64_Controller;

ARCHITECTURE RTL OF Timer_on_64_Controller IS
	COMPONENT Counter_Down_64_bit_Cin
	port(En, clk  : IN std_logic;
        C_in     : IN std_logic_vector(63 DOWNTO 0);
        ov       : OUT std_logic;    -- over flow
        C_Out    : OUT std_logic_vector(63 DOWNTO 0)
	); 
	END COMPONENT;
	
	SIGNAL IN_T, Q   : std_logic;
	SIGNAL PT, ET    : std_logic_vector(63 DOWNTO 0);
	BEGIN
	
	Counter: Counter_Down_64_bit_Cin  PORT MAP(IN_T, clk, PT, Q, ET);
	
	PROCESS(clk)
	BEGIN
		IF reset = '1' THEN
			Data_Out <= (OTHERS => '0');
			PT       <= (OTHERS => '0');
			IN_T     <= '0';
		ELSIF (rising_edge(clk) and EN='1') THEN
			IF MW='1' THEN
				IF (conv_integer(Address) = 0) THEN
					PT(31 DOWNTO 0)  <= Data_In;
				ELSIF (conv_integer(Address) = 1) THEN
					PT(63 DOWNTO 32) <= Data_In;
				ELSIF (conv_integer(Address) = 2) THEN
					IN_T <= Data_In(0);
				END IF;
			ELSIF MW='0' THEN
				IF (conv_integer(Address) = 0) THEN
					Data_Out  <= ET(31 DOWNTO 0);
				ELSIF (conv_integer(Address) = 1) THEN
					Data_Out <= ET(63 DOWNTO 32);
				ELSIF (conv_integer(Address) = 2) THEN
					Data_Out(0) <= Q;
				END IF;
			END IF;
		END IF;
	END PROCESS;
END RTL;
