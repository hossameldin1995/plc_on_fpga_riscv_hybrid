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
use ieee.std_logic_arith.all;
library commonlib;
use commonlib.types_common.all;

entity DoubleMul is 
  generic (
    async_reset : boolean
  );
  port (
    i_nrst       : in std_logic;
    i_clk        : in std_logic;
    i_ena        : in std_logic;
    i_a          : in std_logic_vector(63 downto 0);
    i_b          : in std_logic_vector(63 downto 0);
    o_res        : out std_logic_vector(63 downto 0);
    o_illegal_op : out std_logic;
    o_overflow   : out std_logic;
    o_valid      : out std_logic;
    o_busy       : out std_logic
  );
end; 
 
architecture arch_DoubleMul of DoubleMul is

  component FP_MUL_64 is
    port (
      clk    : in  std_logic                     := 'X';             -- clk
      areset : in  std_logic                     := 'X';             -- reset
      a      : in  std_logic_vector(63 downto 0) := (others => 'X'); -- a
      b      : in  std_logic_vector(63 downto 0) := (others => 'X'); -- b
      q      : out std_logic_vector(63 downto 0)                     -- q
    );
  end component;
  
  signal s_o_res        : std_logic_vector(63 downto 0);
  signal counter_st     : std_logic_vector(2 downto 0);
  signal reset          : std_logic;
  signal s_o_illegal_op : std_logic;
  signal s_o_busy       : std_logic;
  
  begin
  
  reset  <= not(i_nrst);
  o_busy <= s_o_busy;
  o_res <= s_o_res;
  
  o_overflow   <= '1' when s_o_res(63 downto 52) = X"7FF" else -- Not sure of this
                  '0';
  s_o_illegal_op <= '1' when (i_a(62 downto 52) = "11111111111") or (i_b(62 downto 52) = "11111111111") else
                    '0';

  FP_MUL_COMP_64 : component FP_MUL_64
  port map (
    clk    => i_clk,
    areset => reset,
    a      => i_a,
    b      => i_b,
    q      => s_o_res
  );
  
  process(i_nrst, i_clk)
  begin
    if i_nrst = '0' then
      counter_st     <= (others => '0');
		s_o_busy       <= '0';
		o_illegal_op   <= '0';
		o_valid        <= '0';
		
	 elsif rising_edge(i_clk) then
	   if i_ena = '1' then
		  s_o_busy <= '1';
		  counter_st <= "000";
		end if;
		
		if s_o_busy = '1' then
		  counter_st <= counter_st + "001";
		end if;
		
		if counter_st = "011" then
		  o_valid  <= '1';
		  s_o_busy <= '0';
		  o_illegal_op <= s_o_illegal_op;
		elsif counter_st = "100" then
		  o_valid  <= '0';
		  counter_st <= "000";
		end if;
	 end if;
  
  end process;

end;
