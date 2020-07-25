-----------------------------------------------------------------------------
--! @file
--! @copyright Copyright 2016 GNSS Sensor Ltd. All right reserved.
--! @author    Sergey Khabarov - sergeykhbr@gmail.com
--! @brief     Integer multiplier.
--! @details   Implemented algorithm provides 4 clocks per instruction
------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
library commonlib;
use commonlib.types_common.all;
--! RIVER CPU specific library.
library riverlib;
--! RIVER CPU configuration constants.
use riverlib.river_cfg.all;
LIBRARY lpm;
USE lpm.all;

entity IntMulCycloneV is generic (
    async_reset : boolean
  );
  port (
    i_clk  : in std_logic;
    i_nrst : in std_logic;
    i_ena : in std_logic;                                -- Enable bit
    i_unsigned : in std_logic;                           -- Unsigned operands
    i_high : in std_logic;                               -- High multiplied bits [127:64]
    i_rv32 : in std_logic;                               -- 32-bits operands enable
    i_a1 : in std_logic_vector(RISCV_ARCH-1 downto 0);   -- Operand 1
    i_a2 : in std_logic_vector(RISCV_ARCH-1 downto 0);   -- Operand 1
    o_res : out std_logic_vector(RISCV_ARCH-1 downto 0); -- Result
    o_valid : out std_logic;                             -- Result is valid
    o_busy : out std_logic                               -- Multiclock instruction under processing
  );
end; 
 
architecture arch_IntMulCycloneV of IntMulCycloneV is

	COMPONENT lpm_mult
	GENERIC (
		lpm_hint		: STRING;
		lpm_representation		: STRING;
		lpm_type		: STRING;
		lpm_widtha		: NATURAL;
		lpm_widthb		: NATURAL;
		lpm_widthp		: NATURAL
	);
	PORT (
			dataa	: IN STD_LOGIC_VECTOR (63 DOWNTO 0);
			datab	: IN STD_LOGIC_VECTOR (63 DOWNTO 0);
			result	: OUT STD_LOGIC_VECTOR (127 DOWNTO 0)
	);
	END COMPONENT;

	signal result_s	: std_logic_vector ((RISCV_ARCH*2)-1 DOWNTO 0);
	
begin

	lpm_mult_component : lpm_mult
	GENERIC MAP (
		lpm_hint => "MAXIMIZE_SPEED=1",
		lpm_representation => "SIGNED",
		lpm_type => "LPM_MULT",
		lpm_widtha => 64,
		lpm_widthb => 64,
		lpm_widthp => 128
	)
	PORT MAP (
		dataa => i_a1,
		datab => i_a2,
		result => result_s
	);
  
	-- registers:
	regs : process(i_clk, i_nrst)
	begin 
		if async_reset and i_nrst = '0' then
			o_res <= (others => '0');
			o_valid <= '0';
		elsif rising_edge(i_clk) then
			if i_ena = '1' then
				o_res <= result_s(RISCV_ARCH-1 DOWNTO 0);
				o_valid <= '1';
			else
				o_valid <= '0';
			end if;
		end if; 
	end process;
	
	o_busy <= '0';
end;
