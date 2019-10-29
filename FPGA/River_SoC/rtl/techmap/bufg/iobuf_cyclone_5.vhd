----------------------------------------------------------------------------
--! @file
--! @copyright  Copyright 2019 Hossam Eassa. All right reserved.
--! @author     Hossam Eassa
--! @brief      IO buffer for fpga cyclone v.
----------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;


entity iobuf_cyclone_v is
  port (
    o  : out std_logic;
    io : inout std_logic;
    i  : in std_logic;
    t  : in std_logic
  );
end; 
 
architecture rtl of iobuf_cyclone_v is

	component IOBUF_iobuf_bidir_loo IS 
		 PORT 
		 ( 
			 datain	:	IN  STD_LOGIC;
			 dataio	:	INOUT  STD_LOGIC;
			 dataout	:	OUT  STD_LOGIC;
			 oe		:	IN  STD_LOGIC
		 ); 
	end component;
	
begin


  io_inst : IOBUF_iobuf_bidir_loo port map
  (
    dataout => o,
    dataio 	=> io,
    datain 	=> i,
    oe		=> t
  );


end;
