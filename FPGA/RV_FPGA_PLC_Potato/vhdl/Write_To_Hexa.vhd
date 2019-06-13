library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity Write_To_Hexa is
	port(
		-- Time Signals
		Time_micro_Nano	: in std_logic_vector(31 downto 0);
		write_data			: in std_logic;
		
		-- HEX interface
		HEX0					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		HEX1					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		HEX2					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		HEX3					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
	);
end entity Write_To_Hexa;

architecture behaviour of Write_To_Hexa is

component SEG7_LUT_4 is
		port(
			iDIG	: in  std_logic_vector(31 downto 0);
			
			oSEG0	: out std_logic_vector(6 downto 0);
			oSEG1	: out std_logic_vector(6 downto 0);
			oSEG2	: out std_logic_vector(6 downto 0);
			oSEG3	: out std_logic_vector(6 downto 0)
		);
	end component;
	
signal Time_micro_Nano_S : std_logic_vector(31 downto 0);

begin
	Time_micro_Nano_S <= Time_micro_Nano when write_data = '1';
	
	lut_4: SEG7_LUT_4
		port map(
			iDIG => Time_micro_Nano_S,
			oSEG0 => HEX0,
			oSEG1 => HEX1,
			oSEG2 => HEX2,
			oSEG3 => HEX3
		);
		
end architecture behaviour;
