-----------------------------------------------------------------------------
--! @file
--! @copyright  Copyright 2019 Hossam Eassa. All right reserved.
--! @author     Hossam Eassa - hossameassa@gmail.com
--! @brief	FPGA Cyclone V specific constants definition.
------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
--library techmap;
use work.gencomp.all;

package config_target is
-- Technology and synthesis options
  constant CFG_FABTECH : integer := cyclone_v;
  constant CFG_MEMTECH : integer := cyclone_v;
  constant CFG_PADTECH : integer := cyclone_v;
  constant CFG_JTAGTECH : integer := cyclone_v;

  constant CFG_ASYNC_RESET : boolean := true;

  constant CFG_TOPDIR : string := "./";

  --! @brief   Dual-core configuration enabling
  --! @details This config parameter used only with CPU River
  constant CFG_COMMON_DUAL_CORE_ENABLE : boolean := false;

  --! @brief   HEX-image for the initialization of the Boot ROM.
  --! @details This file is used by \e inferred ROM implementation.
  constant CFG_SIM_BOOTROM_HEX : string := 
              CFG_TOPDIR & "bootimage.hex";
  constant CFG_CYC_BOOTROM_MIF : string := 
              CFG_TOPDIR & "bootimage.mif";

  --! @brief   HEX-image for the initialization of the FwImage ROM.
  --! @details This file is used by \e inferred ROM implementation.
  constant CFG_SIM_FWIMAGE_HEX : string := 
                CFG_TOPDIR & "helloworld_gpio.hex";
  constant CFG_CYC_FWIMAGE_MIF : string := 
                CFG_TOPDIR & "helloworld_gpio.mif";
               

  --! @brief Hardware SoC Identificator.
  --!
  --! @details Read Only unique platform identificator that could be
  --!          read by firmware from the Plug'n'Play support module.
  constant CFG_HW_ID : std_logic_vector(31 downto 0) := X"20190524";

  --! @brief Enabling Ethernet MAC interface.
  --! @details By default MAC module enables support of the debug feature EDCL.
  constant CFG_ETHERNET_ENABLE : boolean := false;

  --! @brief Enable/Disable Debug Unit 
  constant CFG_DSU_ENABLE : boolean := false;
  
  constant CFG_FPU_ENABLE : boolean := false;
  constant CFG_MUL_ENABLE : boolean := false;
  constant CFG_DIV_ENABLE : boolean := false;

end;
