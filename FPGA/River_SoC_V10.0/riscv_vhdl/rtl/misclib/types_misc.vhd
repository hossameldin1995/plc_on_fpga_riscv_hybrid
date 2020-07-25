--!
--! Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com
--!
--! Licensed under the Apache License, Version 2.0 (the "License");
--! you may not use this file except in compliance with the License.
--! You may obtain a copy of the License at
--!
--!     http://www.apache.org/licenses/LICENSE-2.0
--! Unless required by applicable law or agreed to in writing, software
--! distributed under the License is distributed on an "AS IS" BASIS,
--! WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--! See the License for the specific language governing permissions and
--! limitations under the License.
--!

--! Standard library.
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
library commonlib;
use commonlib.types_common.all;
--! Technology definition library.
library techmap;
use techmap.gencomp.all;
--! CPU, System Bus and common peripheries library.
library ambalib;
use ambalib.types_amba4.all;
use ambalib.types_bus0.all;

--! @brief   Declaration of components visible on SoC top level.
package types_misc is

--! @defgroup irq_id_group AXI4 interrupt generic IDs.
--! @ingroup axi4_config_generic_group
--! @details Unique indentificator of the interrupt pin also used
--!          as an index in the interrupts bus.
--! @{

--! Zero interrupt index must be unused.
constant CFG_IRQ_UNUSED         : integer := 0;
--! UART_A interrupt pin.
constant CFG_IRQ_UART1          : integer := 1;
--! GP Timers interrupt pin
constant CFG_IRQ_GPTIMERS       : integer := 2;
--! Total number of used interrupts in a system
constant CFG_IRQ_TOTAL          : integer := 3;
--! @}

--! @brief SOC global reset former.
--! @details This module produces output reset signal in a case if
--!          button 'Reset' was pushed or PLL isn't a 'lock' state.
--! param[in]  inSysReset Button generated signal
--! param[in]  inSysClk Clock from the PLL. Bus clock.
--! param[out] outReset Output reset signal with active 'High' (1 = reset).
component reset_global
port (
  inSysReset  : in std_ulogic;
  inSysClk    : in std_ulogic;
  outReset    : out std_ulogic );
end component;


--! Boot ROM with AXI4 interface declaration.
component axi4_rom is
generic (
    memtech  : integer := inferred;
    async_reset : boolean := false;
    xaddr    : integer := 0;
    xmask    : integer := 16#fffff#;
    sim_hexfile : string
  );
port (
    clk  : in std_logic;
    nrst : in std_logic;
    cfg  : out axi4_slave_config_type;
    i    : in  axi4_slave_in_type;
    o    : out axi4_slave_out_type
  );
end component; 

--! Internal RAM with AXI4 interface declaration.
component axi4_sram is
  generic (
    memtech  : integer := inferred;
    async_reset : boolean := false;
    xaddr    : integer := 0;
    xmask    : integer := 16#fffff#;
    abits    : integer := 17;
    init_file : string := "" -- only for 'inferred'
  );
  port (
    clk  : in std_logic;
    nrst : in std_logic;
    cfg  : out axi4_slave_config_type;
    i    : in  axi4_slave_in_type;
    o    : out axi4_slave_out_type
  );
end component; 

--! AXI4 to SPI brdige for external Flash IC Micron M25AA1024
type spi_in_type is record
    SDI : std_logic;
end record;

type spi_out_type is record
    SDO : std_logic;
    SCK : std_logic;
    nCS : std_logic;
    nWP : std_logic;
    nHOLD : std_logic;
    RESET : std_logic;
end record;

constant spi_out_none : spi_out_type := (
  '0', '0', '1', '1', '1', '0'
);

component axi4_flashspi is
  generic (
    async_reset : boolean := false;
    xaddr   : integer := 0;
    xmask   : integer := 16#fffff#;
    wait_while_write : boolean := true  -- hold AXI bus response until end of write cycle
  );
  port (
    clk    : in  std_logic;
    nrst   : in  std_logic;
    cfg    : out axi4_slave_config_type;
    i_spi  : in  spi_in_type;
    o_spi  : out spi_out_type;
    i_axi  : in  axi4_slave_in_type;
    o_axi  : out axi4_slave_out_type  );
end component; 

--! @brief AXI4 GPIO controller
component axi4_gpio is
  generic (
    async_reset : boolean := false;
    xaddr    : integer := 0;
    xmask    : integer := 16#fffff#;
    xirq     : integer := 0
  );
  port (
    clk  : in std_logic;
    nrst : in std_logic;
    cfg  : out axi4_slave_config_type;
    i    : in  axi4_slave_in_type;
    o    : out axi4_slave_out_type;
    KEY			: in std_logic_vector(3 DOWNTO 0);
	  SW			: in std_logic_vector(9 DOWNTO 0);
    LEDG			: out std_logic_vector(7 DOWNTO 0);
    LEDR			: out std_logic_vector(9 DOWNTO 0);
    GPIO_IN		: in std_logic_vector(17 DOWNTO 0);
    GPIO_OUT	: out std_logic_vector(17 DOWNTO 0)
  );
end component; 

type uart_in_type is record
  rd   	: std_ulogic;
  cts   : std_ulogic;
end record;

type uart_out_type is record
  td   	: std_ulogic;
  rts   : std_ulogic;
end record;

--! UART with the AXI4 interface declaration.
component axi4_uart is
  generic (
    async_reset : boolean := false;
    xaddr   : integer := 0;
    xmask   : integer := 16#fffff#;
    xirq    : integer := 0;
    fifosz  : integer := 16
  );
  port (
    clk    : in  std_logic;
    nrst   : in  std_logic;
    cfg    : out axi4_slave_config_type;
    i_uart : in  uart_in_type;
    o_uart : out uart_out_type;
    i_axi  : in  axi4_slave_in_type;
    o_axi  : out axi4_slave_out_type;
    o_irq  : out std_logic);
end component;


--! @brief   Interrupt controller with the AXI4 interface declaration.
--! @details To rise interrupt on certain CPU HostIO interface is used.
component axi4_irqctrl is
  generic (
    async_reset : boolean := false;
    xaddr    : integer := 0;
    xmask    : integer := 16#fffff#
  );
  port 
 (
    clk    : in std_logic;
    nrst   : in std_logic;
    i_irqs : in std_logic_vector(CFG_IRQ_TOTAL-1 downto 1);
    o_cfg  : out axi4_slave_config_type;
    i_axi  : in axi4_slave_in_type;
    o_axi  : out axi4_slave_out_type;
    o_irq_meip : out std_logic
  );
  end component;

  --! @brief   General Purpose Timers with the AXI interface.
  --! @details This module provides high precision counter and
  --!          generic number of GP timers.
  component axi4_gptimers is
  generic (
    async_reset : boolean := false;
    xaddr   : integer := 0;
    xmask   : integer := 16#fffff#;
    xirq    : integer := 0;
    tmr_total  : integer := 2
  );
  port (
    clk    : in  std_logic;
    nrst   : in  std_logic;
    cfg    : out axi4_slave_config_type;
    i_axi  : in  axi4_slave_in_type;
    o_axi  : out axi4_slave_out_type;
    o_irq  : out std_logic
  );
  end component;

component axi4_time_measurement is
  generic (
    async_reset : boolean := false;
    xaddr    : integer := 0;
    xmask    : integer := 16#fffff#;
    xirq     : integer := 0
  );
  port (
    clk  		: in std_logic;
    nrst 		: in std_logic;
    cfg  		: out axi4_slave_config_type;
    i    		: in  axi4_slave_in_type;
    o  		  	: out axi4_slave_out_type;
	 
	 -- HEX interface
	 HEX0			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
	 HEX1			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
	 HEX2			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
	 HEX3			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0)
  );
end component;

component axi4_ton is
  generic (
    async_reset : boolean := false;
    xaddr    : integer := 0;
    xmask    : integer := 16#fffff#;
    xirq     : integer := 0
  );
  port (
    clk  		: in std_logic;
    nrst 		: in std_logic;
    cfg  		: out axi4_slave_config_type;
    i    		: in  axi4_slave_in_type;
    o  		  	: out axi4_slave_out_type
  );
end component;

component axi4_pwm is
  generic (
    async_reset : boolean := false;
    xaddr    : integer := 0;
    xmask    : integer := 16#fffff#;
    xirq     : integer := 0
  );
  port (
    clk  		: in std_logic;
    clk_pwm 	: in std_logic;
    nrst 		: in std_logic;
    cfg  		: out axi4_slave_config_type;
    i    		: in  axi4_slave_in_type;
    o  		  	: out axi4_slave_out_type
  );
end component;

component axi4_pid is
  generic (
    async_reset : boolean := false;
    xaddr    : integer := 0;
    xmask    : integer := 16#fffff#;
    xirq     : integer := 0
  );
  port (
    clk  		: in std_logic;
    nrst 		: in std_logic;
    cfg  		: out axi4_slave_config_type;
    i    		: in  axi4_slave_in_type;
    o  		  : out axi4_slave_out_type
  );
end component;

--component axi4_ is
--  generic (
  
--  );
--  port (

--  );
--end component;

end; -- package declaration
