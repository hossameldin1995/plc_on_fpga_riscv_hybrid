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

entity DoubleAdd is 
  generic (
    async_reset : boolean
  );
  port (
    i_nrst       : in std_logic;
    i_clk        : in std_logic;
    i_ena        : in std_logic;
    i_add        : in std_logic;
    i_sub        : in std_logic;
    i_eq         : in std_logic;
    i_lt         : in std_logic;
    i_le         : in std_logic;
    i_max        : in std_logic;
    i_min        : in std_logic;
    i_a          : in std_logic_vector(63 downto 0);
    i_b          : in std_logic_vector(63 downto 0);
    o_res        : out std_logic_vector(63 downto 0);
    o_illegal_op : out std_logic;
    o_overflow   : out std_logic;
    o_valid      : out std_logic;
    o_busy       : out std_logic
  );
end; 
 
architecture arch_DoubleAdd of DoubleAdd is

	component FP_ADD_SUB_64 is
		port (
			clk    : in  std_logic                     := '0';             --    clk.clk
			areset : in  std_logic                     := '0';             -- areset.reset
			a      : in  std_logic_vector(63 downto 0) := (others => '0'); --      a.a
			b      : in  std_logic_vector(63 downto 0) := (others => '0'); --      b.b
			q      : out std_logic_vector(63 downto 0);                    --      q.q
			opSel  : std_logic_vector(0 downto 0)      := (others => '0')  --  opSel.opSel
		);
	end component;
    
	component FP_EQ_64 is
		port (
			clk    : in  std_logic                     := '0';             --    clk.clk
			areset : in  std_logic                     := '0';             -- areset.reset
			a      : in  std_logic_vector(63 downto 0) := (others => '0'); --      a.a
			b      : in  std_logic_vector(63 downto 0) := (others => '0'); --      b.b
			q      : out std_logic_vector(0 downto 0)                      --      q.q
		);
	end component;
    
	component FP_LT_64 is
		port (
			clk    : in  std_logic                     := '0';             --    clk.clk
			areset : in  std_logic                     := '0';             -- areset.reset
			a      : in  std_logic_vector(63 downto 0) := (others => '0'); --      a.a
			b      : in  std_logic_vector(63 downto 0) := (others => '0'); --      b.b
			q      : out std_logic_vector(0 downto 0)                      --      q.q
		);
	end component;
    
	component FP_LE_64 is
		port (
			clk    : in  std_logic                     := '0';             --    clk.clk
			areset : in  std_logic                     := '0';             -- areset.reset
			a      : in  std_logic_vector(63 downto 0) := (others => '0'); --      a.a
			b      : in  std_logic_vector(63 downto 0) := (others => '0'); --      b.b
			q      : out std_logic_vector(0 downto 0)                      --      q.q
		);
	end component;
    
	component FP_MAX_64 is
		port (
			clk    : in  std_logic                     := '0';             --    clk.clk
			areset : in  std_logic                     := '0';             -- areset.reset
			a      : in  std_logic_vector(63 downto 0) := (others => '0'); --      a.a
			b      : in  std_logic_vector(63 downto 0) := (others => '0'); --      b.b
			q      : out std_logic_vector(63 downto 0)                     --      q.q
		);
	end component;

	signal reset       : std_logic;
	
	signal s_o_res     : std_logic_vector(63 downto 0);
	signal res_add_sub : std_logic_vector(63 downto 0);
	signal res_eq      : std_logic_vector(0 downto 0);
	signal res_lt      : std_logic_vector(0 downto 0);
	signal res_le      : std_logic_vector(0 downto 0);
	signal res_max     : std_logic_vector(63 downto 0);
	
	signal counter_st  : std_logic_vector(3 downto 0);
	
	signal en_stage    : std_logic;
	signal en_add_sub  : std_logic;
	signal en_eq       : std_logic;
	signal en_lt       : std_logic;
	signal en_le       : std_logic;
	signal en_max_min  : std_logic;
	
	signal opSel       : std_logic_vector(0 downto 0);
	
	signal s_o_illegal_op : std_logic;
	
begin

	reset   <= not(i_nrst);
	o_res   <= s_o_res;

	en_add_sub <= (en_stage and (i_add or i_sub)) or (i_ena and (i_add or i_sub));
	en_eq      <= (en_stage and i_eq) or (i_ena and i_eq);
	en_lt      <= (en_stage and i_lt) or (i_ena and i_lt);
	en_le      <= (en_stage and i_le) or (i_ena and i_le);
	en_max_min <= (en_stage and (i_max or i_min)) or (i_ena and (i_max or i_min));
    
	opSel(0) <= i_add and not(i_sub);
    
	o_overflow   <= '1' when s_o_res(63 downto 52) = X"7FF" else -- Not sure of this
                   '0';
	s_o_illegal_op <= '1' when (i_a(62 downto 52) = "11111111111") or (i_b(62 downto 52) = "11111111111") else
                     '0';

	FP_ADD_SUB_COMP_64 : FP_ADD_SUB_64 
	port map(
		clk    => i_clk,
		areset => reset,
		a      => i_a,
		b      => i_b,
		q      => res_add_sub,
		opSel  => opSel
    );
        
   FP_EQ_COMP_64 : FP_EQ_64 
	port map(
		clk    => i_clk,
		areset => reset,
		a      => i_a,
		b      => i_b,
		q      => res_eq
    );
    
   FP_LT_COMP_64 : FP_LT_64 
	port map(
		clk    => i_clk,
		areset => reset,
		a      => i_a,
		b      => i_b,
		q      => res_lt
    );
    
   FP_LE_COMP_64 : FP_LE_64 
	port map(
		clk    => i_clk,
		areset => reset,
		a      => i_a,
		b      => i_b,
		q      => res_le
    );
    
   FP_MAX_COMM_64 : FP_MAX_64 
	port map(
		clk    => i_clk,
		areset => reset,
		a      => i_a,
		b      => i_b,
		q      => res_max
	);
	
	process(i_nrst, i_clk)
	begin
		if i_nrst = '0' then
			s_o_res        <= (others => '0');
			o_valid        <= '0';
			o_busy         <= '0';
			o_illegal_op   <= '0';
			
			counter_st     <= (others => '0');
			en_stage       <= '0';
		elsif rising_edge(i_clk) then
			if i_ena = '1' then
				counter_st <= (others => '0');
				if (i_add or i_sub or en_eq or en_lt or en_le or en_max_min) = '1' then
					en_stage <= '1';
					o_busy <= '1';
				end if;
			end if;
			
			if en_stage = '1' then
				counter_st <= counter_st + "0001";
				if counter_st = "0000" then
					if en_eq = '1' then
						o_valid <= '1';
						o_illegal_op <= s_o_illegal_op;
						o_busy <= '0';
						s_o_res <= X"000000000000000"&"000"&res_eq(0);
					elsif en_lt = '1' then
						o_valid <= '1';
						o_illegal_op <= s_o_illegal_op;
						o_busy <= '0';
						s_o_res <= X"000000000000000"&"000"&res_lt(0);
					elsif en_le = '1' then
						o_valid <= '1';
						o_illegal_op <= s_o_illegal_op;
						o_busy <= '0';
						s_o_res <= X"000000000000000"&"000"&res_le(0);
					elsif en_max_min = '1' and i_max = '1' then
						o_valid <= '1';
						o_illegal_op <= s_o_illegal_op;
						o_busy <= '0';
						s_o_res <= res_max;
					elsif en_max_min = '1' and i_min = '1' then
						o_valid <= '1';
						o_illegal_op <= s_o_illegal_op;
						o_busy <= '0';
						if res_max = i_a then
							s_o_res <= i_b;
						else
							s_o_res <= i_a;
						end if;
					end if;
				elsif counter_st = "0001" and (en_eq or en_lt or en_le or en_max_min) = '1' then
					en_stage <= '0';
					o_valid <= '0';
				elsif counter_st = "1000" and en_add_sub = '1' then
					s_o_res <= res_add_sub;
					o_valid <= '1';
					o_illegal_op <= s_o_illegal_op;
					o_busy <= '0';
				elsif counter_st = "1001" and en_add_sub = '1' then
					en_stage <= '0';
					o_valid <= '0';
				end if;
			end if;
		end if;
    end process;

end;
