--
-- VHDL Architecture Hossameldin_VHDL.Counter_Up_Down_4_bit_Cin_2_to_13.rtl
--
-- Created:
--          by - Hossameldin.UNKNOWN (HOSSAMELDIN-PC)
--          at - 13:34:57 11/20/2016
--
-- using Mentor Graphics HDL Designer(TM) 2007.1 (Build 19)
--
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
USE IEEE.STD_LOGIC_ARITH.ALL;

ENTITY Counter_Down_64_bit_Cin IS
	port(clk      : in std_logic;
		  nrst     : in std_logic;
		  En       : in std_logic;
        C_in     : in std_logic_vector(63 downto 0);
        ov       : out std_logic;    -- over flow
        C_Out    : out std_logic_vector(63 downto 0)
	); 
END ENTITY Counter_Down_64_bit_Cin;

--
ARCHITECTURE rtl OF Counter_Down_64_bit_Cin IS
signal C_Tmp, C_O_Tmp, C_in_un : unsigned(63 downto 0);
BEGIN
	process(clk, nrst, En)
	begin
		C_in_un <= unsigned(C_in);
		if En = '0' or nrst = '0' then 
			C_Tmp   <= C_in_un;
			C_O_Tmp <= (others => '0'); 
			ov <='0';
		elsif rising_edge(clk) then
			if C_Tmp < 1 then 
				C_Tmp   <= (others => '0'); 
				ov <='1';
			else 
				C_Tmp   <= C_Tmp - 1;
				C_O_Tmp <= C_O_Tmp + 1;
				ov <='0';
			end if;
		end if;
	end process;
	C_Out <= std_logic_vector(C_O_Tmp);
END ARCHITECTURE rtl;