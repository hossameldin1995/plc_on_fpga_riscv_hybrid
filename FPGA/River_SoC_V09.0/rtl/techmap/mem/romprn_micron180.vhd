----------------------------------------------------------------------------
--! @file
--! @copyright  Copyright 2015 GNSS Sensor Ltd. All right reserved.
--! @author     Sergey Khabarov
--! @brief      Galileo Reference E1 codes.
------------------------------------------------------------------------------
library ieee;
use ieee.std_logic_1164.all;
--library commonlib;
use work.types_common.all;

entity RomPrn_micron180 is
  port (
    i_clk       : in std_logic;
    i_address   : in std_logic_vector(12 downto 0);
    o_data      : out std_logic_vector(31 downto 0)
  );
end;

architecture rtl of RomPrn_micron180 is

	component ROMD_8192x32m8d4_R0_M4_ns IS
	
		PORT
		(
			address		: IN STD_LOGIC_VECTOR (12 DOWNTO 0);
			clock		: IN STD_LOGIC  := '1';
			q		: OUT STD_LOGIC_VECTOR (31 DOWNTO 0)
		);
	end component;

begin

  
   m180 : ROMD_8192x32m8d4_R0_M4_ns port map
      (address	=> i_address,
		clock		=> i_clk,
		q			=> o_data); 
  
end;
