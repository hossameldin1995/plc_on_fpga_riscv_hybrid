LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;

--! @brief Generic Wishbone IO_Peripheral Module.
--!
--! The following registers are defined:
--! |---------|--------------------|
--! | Address | Inputs             |
--! |---------|--------------------|
--! | 0x000   | GPIO_IN (size 18)  |
--! | 0x048   | SW      (size 10)  |
--! | 0x070   | KEY     (size 04)  |
--! |---------|--------------------|
--!
--! |---------|--------------------|
--! | Address | Outputs            |
--! |---------|--------------------|
--! | 0x100   | GPIO_OUT (size 18) |
--! | 0x148   | LEDR     (size 10) |
--! | 0x170   | LEDG     (size 08) |
--! |---------|--------------------|
--!
--! |---------|--------------------|
--! | Address | Read and Write Reg |
--! |---------|--------------------|
--! | 0x1fc   |                    |
--! |---------|--------------------|

ENTITY Input_Output_Peripheral IS
	PORT( 
		clk		: IN STD_LOGIC;
		reset		: IN STD_LOGIC;
		EN			: IN STD_LOGIC;
		
		KEY		: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
		LEDR		: OUT STD_LOGIC_VECTOR(9 DOWNTO 0);
		LEDG		: OUT STD_LOGIC_VECTOR(7 DOWNTO 0);
		SW			: IN STD_LOGIC_VECTOR(9 DOWNTO 0);
		
		GPIO_OUT	: OUT STD_LOGIC_VECTOR(17 DOWNTO 0);
		GPIO_IN	: IN STD_LOGIC_VECTOR(17 DOWNTO 0);
		
		MW			: IN STD_LOGIC;
		Address	: IN STD_LOGIC_VECTOR(6 DOWNTO 0);
		IN_Data	: IN STD_LOGIC;
		OUT_Data	: OUT STD_LOGIC_VECTOR(31 DOWNTO 0)
	);
END;

ARCHITECTURE RTL OF Input_Output_Peripheral IS
	
	SIGNAL register_in	: STD_LOGIC_VECTOR(63 DOWNTO 0);   -- 32 - 0
	SIGNAL register_out	: STD_LOGIC_VECTOR(63 DOWNTO 0);   -- 36 - 0
	SIGNAL OUT_Data_1		: STD_LOGIC;
	BEGIN
	
	OUT_Data <= "0000000000000000000000000000000" & OUT_Data_1;
	
	PROCESS(clk, reset)
	BEGIN
		IF reset = '1' THEN
			register_in  <= (OTHERS => '0');
			register_out <= (OTHERS => '0');
			GPIO_OUT     <= (OTHERS => '0');
			LEDR         <= (OTHERS => '0');
			LEDG         <= (OTHERS => '0');
			OUT_Data_1     <= '0';
		ELSE
		IF (rising_edge(clk) and EN='1') THEN
				IF Address = X"7F" THEN
					register_in        <= "00000000000000000000000000000000" & KEY & SW & GPIO_IN;
					GPIO_OUT           <= register_out(17 DOWNTO 0);
					LEDR               <= register_out(27 DOWNTO 18);
					LEDG               <= register_out(35 DOWNTO 28);
				ELSE
					IF MW = '1' THEN              -- IN
						register_out(conv_integer(Address(5 DOWNTO 0))) <= IN_Data;
					ELSE                          -- OUT
						IF Address(6) = '0' THEN   -- read inputs
							OUT_Data_1 <= register_in(conv_integer(Address(5 DOWNTO 0)));
						ELSE                       -- read outputs
							OUT_Data_1 <= register_out(conv_integer(Address(5 DOWNTO 0)));
						END IF;
					END IF;
				END IF;
			END IF;
		END IF;
	END PROCESS;
END RTL;