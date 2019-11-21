library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;

entity CAS is
generic(N:integer:=32);
port(In_A, In_B: in std_logic_vector(N-1 downto 0);
Quotient, Remainder: Out std_logic_vector(N-1 downto 0));
end CAS;


architecture CAS_Structure of CAS is
function Func_Sum_Out(I1, I2, I3, I4:std_logic) return std_logic is --I1 A_In, I2 B_In, I3 C_In, I4 P_In
begin 
	return (I1 xor (I2 xor I4)) xor I3;
end Func_Sum_Out;

function Func_C_Out(I1, I2, I3, I4:std_logic) return std_logic is --I1 A_In, I2 B_In, I3 C_In, I4 P_In
begin 
	return ((I1 or I3) and (I2 xor I4)) or (I1 and I3);
end func_C_Out;

function Func_nBitSum_out(A_In, B_In:std_logic_vector(N-1 downto 0)) 
return std_logic_vector is
variable Var_A_In, Var_B_In, Var_Sum_Out: std_logic_vector(N-1 downto 0);
Variable Var_C_Out:std_logic_vector(N-2 downto 0);
begin
	Var_A_In:= A_In;
	Var_B_In:= B_In;
	
	Var_Sum_Out(0):=Var_A_In(0) xor Var_B_In(0);
	Var_C_Out(0):=Var_A_In(0) and Var_B_In(0);
	
	for i in 1 to N-2 loop
		Var_Sum_Out(i):=Var_A_In(i) xor Var_B_In(i) xor Var_C_Out(i-1);
		Var_C_Out(i):= ((Var_A_In(i) and Var_B_In(i)) or (Var_A_In(i) and Var_C_Out(i-1))) or (Var_B_In(i) and Var_C_Out(i-1));
	end loop;
		Var_Sum_Out(N-1):=Var_A_In(N-1) xor Var_B_In(N-1) xor Var_C_Out(N-2);
		return Var_Sum_Out;
end Func_nBitSum_out;


procedure CAS1_Out(variable A_In, B_In: in std_logic_vector(N downto 0);
variable P_In: in std_logic;
variable C_Out: out std_logic;
variable Sum_Out: out std_logic_vector(N downto 0)) is
variable Var_A_In, Var_B_In, Var_Sum, Var_C: std_logic_vector(N downto 0);
variable Var_P: std_logic;
begin 
			Var_A_In:= A_In;
			Var_B_In:= B_In;
			Var_P:=P_In;

			Var_Sum(0):=Func_Sum_Out(Var_A_In(0), B_In(0), Var_P, Var_P);
			Var_C(0):=Func_C_Out(Var_A_In(0), B_In(0), Var_P, Var_P);
			for i in 1 to N loop	
				Var_Sum(i):=Func_Sum_Out(Var_A_In(i), B_In(i), Var_C(i-1), Var_P);
				Var_C(i):=Func_C_Out(Var_A_In(i), B_In(i), Var_C(i-1), Var_P);
			end loop;
			C_Out:=Var_C(N);
			Sum_Out:=Var_Sum;
end procedure;
begin 
	process(In_A, In_B)
		variable Var_A_In, Var_B_In, Var_Remainder: std_logic_vector(N downto 0);
		variable Var_Quotient:std_logic_vector(N-1 downto 0);
		variable Var_P: std_logic;
		begin
		Var_A_In:= (0=>In_A(N-1), others=>'0');
		Var_B_In:= '0'&In_B;
		Var_P:='1';		
		
		CAS1_Out( Var_A_In, Var_B_In, Var_P, Var_Quotient(N-1), Var_Remainder);
		
		for i in N-2 downto 0 loop
		Var_Remainder:=Var_Remainder(N-1 downto 0) & In_A(i);
		Var_P:=  Var_Quotient(i+1);
		CAS1_Out( Var_Remainder, Var_B_In, Var_P, Var_Quotient(i), Var_Remainder);
		
		end loop;

		if (Var_Quotient(0)='0') then
--			Var_Remainder(N-1 downto 0):=Var_Remainder(N-1 downto 0)+Var_B_In(N-1 downto 0);
			Var_Remainder(N-1 downto 0):=Func_nBitSum_out(Var_Remainder(N-1 downto 0), Var_B_In(N-1 downto 0));
		end if;		
		
		Quotient<=Var_Quotient;
		Remainder<=Var_Remainder(N-1 downto 0);
		
	end process;
end CAS_Structure;