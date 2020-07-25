library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity Write_To_Hexa is
	port(
		clk					: in std_logic;
		nrst					: in std_logic;
		
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
	
signal Time_micro_Nano_S	: std_logic_vector(31 downto 0);
signal count					: unsigned(31 downto 0) := (others => '0');
signal ovf						: std_logic;

begin
	process(clk, nrst)
	begin
		if rising_edge(clk) then
			if nrst = '0' then
				Time_micro_Nano_S <= (others => '0');
			else
				if ((write_data = '1') and (ovf = '1')) then
					Time_micro_Nano_S <= Time_micro_Nano;
				end if;
				if (count >= 10000000) then
					ovf <= '1';
					if write_data = '1' then
						count <= (others => '0');
					end if;
				else
					count <= count + 1;
					ovf <= '0';
				end if;
			end if;
		end if;
	end process;
	
	lut_4: SEG7_LUT_4
		port map(
			iDIG => Time_micro_Nano_S,
			oSEG0 => HEX0,
			oSEG1 => HEX1,
			oSEG2 => HEX2,
			oSEG3 => HEX3
		);
		
end architecture behaviour;