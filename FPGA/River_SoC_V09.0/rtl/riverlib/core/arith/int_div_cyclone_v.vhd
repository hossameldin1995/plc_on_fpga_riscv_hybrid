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
--library commonlib;
use work.types_common.all;
--! RIVER CPU specific library.
--library riverlib;
--! RIVER CPU configuration constants.
use work.river_cfg.all;


entity IntDivCyclonev is generic (
    async_reset : boolean := false
  );
  port (
    i_clk  : in std_logic;
    i_nrst : in std_logic;                               -- Reset Active LOW
    i_ena : in std_logic;                                -- Enable bit
    i_unsigned : in std_logic;                           -- Unsigned operands
    i_rv32 : in std_logic;                               -- 32-bits operands enable
    i_residual : in std_logic;                           -- Compute: 0 =division; 1=residual
    i_a1 : in std_logic_vector(RISCV_ARCH-1 downto 0);   -- Operand 1
    i_a2 : in std_logic_vector(RISCV_ARCH-1 downto 0);   -- Operand 1
    o_res : out std_logic_vector(RISCV_ARCH-1 downto 0); -- Result
    o_valid : out std_logic;                             -- Result is valid
    o_busy : out std_logic                               -- Multiclock instruction under processing
  );
end; 
 
architecture arch_IntDivCyclonev of IntDivCyclonev is

	component div_int_64 is
	port (
		numer		: in std_logic_vector (RISCV_ARCH-1 DOWNTO 0);
		denom		: in std_logic_vector (RISCV_ARCH-1 DOWNTO 0);
		quotient	: out std_logic_vector (RISCV_ARCH-1 DOWNTO 0);
		remain	: out std_logic_vector (RISCV_ARCH-1 DOWNTO 0)
	);
	end component; 
	
	signal quotient	: std_logic_vector (RISCV_ARCH-1 DOWNTO 0);
	signal remain		: std_logic_vector (RISCV_ARCH-1 DOWNTO 0);
	signal result_s	: std_logic_vector (RISCV_ARCH-1 DOWNTO 0);

begin

  divDSP0 : div_int_64 port map (
      numer  	=> i_a1,
      denom 	=> i_a2,
      quotient => quotient,
		remain	=> remain
	);
	
	with i_residual select result_s <= 
					quotient when '0',
					remain	when '1',
					(others => '0') when others;
  
	-- registers:
	regs : process(i_clk, i_nrst)
	begin 
		if async_reset and i_nrst = '0' then
			o_res <= (others => '0');
			o_valid <= '0';
		elsif rising_edge(i_clk) then
			if i_ena = '1' then
				o_res <= result_s;
				o_valid <= '1';
			else
				o_valid <= '0';
			end if;
		end if; 
	end process;
	
	o_busy <= '0';

end;
