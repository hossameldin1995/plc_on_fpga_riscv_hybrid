library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

entity Time_Calculation is
	port(
		clk 			: in std_logic;
		nrst 		: in std_logic;
		
		-- Time Signals
		Time_Micro_Nano	: out std_logic_vector(31 downto 0);
		Micro_Nano			: in std_logic; -- Micro = 1, Nano = 0
		Start_Stop			: in std_logic; -- Start = 1, Stop = 0
		
		-- HEX interface
		HEX0					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		HEX1					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		HEX2					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		HEX3					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
	);
end entity Time_Calculation;

architecture behaviour of Time_Calculation is

signal counter 					: unsigned(31 downto 0);
signal Time_Micro_Nano_S		: unsigned(31 downto 0);
signal Time_Micro_Nano_S_64	: unsigned(63 downto 0);
signal Time_Micro_Nano_S_std	: std_logic_vector(31 downto 0);
signal write_data					: std_logic;

begin
	process(clk, nrst, Micro_Nano, Start_Stop)
	begin
		if rising_edge(clk) then
			if nrst = '0' then
				counter <= (others => '0');
				Time_Micro_Nano_S <= (others => '0');
				write_data <= '1';
			else
				if Start_Stop = '1' then -- Starting or started
					write_data <= '0';
					counter <= counter + 1;
					--if Micro_Nano = '1' then -- Micro
						--Time_Micro_Nano_S	<= counter / 100;
						Time_Micro_Nano_S	<= counter; -- mult and div take time (will not used)
					--else -- Nano
						--Time_Micro_Nano_S_64	<= counter * 10;
						--Time_Micro_Nano_S		<= Time_Micro_Nano_S_64(31 downto 0);
						--Time_Micro_Nano_S	<= counter; -- mult and div take time (will not used)
					--end if;
				else -- Stopping or stop
					counter <= (others => '0');
					write_data <= '1';
				end if;
			end if;
		end if;
	end process;
	
	Time_Micro_Nano_S_std <= std_logic_vector(Time_Micro_Nano_S);
	--Time_Micro_Nano <= Time_Micro_Nano_S_std;
	
	WriteToHexa: entity work.Write_To_Hexa
		port map(
			clk					=> clk,
			nrst					=> nrst,
			
			-- Time Signals
			Time_micro_Nano	=> Time_Micro_Nano_S_std,
			write_data			=> write_data,
			
			-- HEX interface
			HEX0					=> HEX0,
			HEX1					=> HEX1,
			HEX2					=> HEX2,
			HEX3					=> HEX3
		);
		
end architecture behaviour;