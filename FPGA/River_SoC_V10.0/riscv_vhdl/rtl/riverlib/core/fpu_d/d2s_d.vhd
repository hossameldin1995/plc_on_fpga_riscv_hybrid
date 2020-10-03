--!
--! Copyright 2019 Sergey Khabarov, sergeykhbr@gmail.com
--!
--! Licensed under the Apache License, Version 2.0 (the "License");
--! you may not use this file except in compliance with the License.
--! You may obtain a copy of the License at
--!
--!     http://www.apache.org/licenses/LICENSE-2.0
--!
--! Unless required by applicable law or agreed to in writing, software
--! distributed under the License is distributed on an "AS IS" BASIS,
--! WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--! See the License for the specific language governing permissions and
--! limitations under the License.
--!

library ieee;
use ieee.std_logic_1164.all;
library commonlib;
use commonlib.types_common.all;

entity Double2Single is 
  generic (
    async_reset : boolean
  );
  port (
    i_nrst       : in std_logic;
    i_clk        : in std_logic;
    i_ena        : in std_logic;
    i_signed     : in std_logic;
    i_w32        : in std_logic;
    i_a          : in std_logic_vector(63 downto 0);
    o_res        : out std_logic_vector(63 downto 0);
    o_overflow   : out std_logic;
    o_underflow  : out std_logic;
    o_valid      : out std_logic;
    o_busy       : out std_logic
  );
end; 
 
architecture arch_Double2Single of Double2Single is

	component FP_D2S is
		port (
			clk    : in  std_logic                     := '0';             --    clk.clk
			areset : in  std_logic                     := '0';             -- areset.reset
			a      : in  std_logic_vector(63 downto 0) := (others => '0'); --      a.a
			q      : out std_logic_vector(31 downto 0)                     --      q.q
		);
	end component;
	
	signal reset    : std_logic;
	signal o_res_32 : std_logic_vector(31 downto 0);

begin

	reset   <= not(i_nrst);
	o_overflow  <= '0';
	o_underflow <= '0';
	
	FP_D2S_COMP : FP_D2S 
	port map(
		clk    => i_clk,
		areset => reset,
		a      => i_a,
		q      => o_res_32
   );
	 
	o_res(31 downto 0 ) <= o_res_32;
	o_res(63 downto 32) <= (others => '0');
	o_busy              <= '0';

	process(i_nrst, i_clk)
	begin
		if i_nrst = '0' then
			o_valid <= '0';
		elsif rising_edge(i_clk) then
			if i_ena = '1' then
				o_valid <= '1';
			else
				o_valid <= '0';
			end if;
		end if;
    end process;

  

end;
