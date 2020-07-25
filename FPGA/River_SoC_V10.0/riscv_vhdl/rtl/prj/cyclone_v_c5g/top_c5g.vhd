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

--! Standard library
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

--! Technology definition library.
library techmap;
--! "Virtual" PLL declaration.
use techmap.types_pll.all;

 --! Top-level implementaion library
 library target;
 use target.config_target.all;

entity top_c5g is
port
(

	--------- CLOCK ---------
		CLOCK_125_p	: in std_logic;
		CLOCK_50_B3B: in std_logic;
		CLOCK_50_B5B: in std_logic;
		CLOCK_50_B6A: in std_logic;
		CLOCK_50_B7A: in std_logic;
		CLOCK_50_B8A: in std_logic;
		
		--------- CPU ---------
		CPU_RESET_n	: in std_logic;
		
		--------- GPIO ---------
		GPIO_IN		: in std_logic_vector(17 DOWNTO 0);
		GPIO_OUT		: out std_logic_vector(17 DOWNTO 0);
		
		--------- HEX0 ---------
		HEX0			: out std_logic_vector(6 DOWNTO 0);

		--------- HEX1 ---------
		HEX1			: out std_logic_vector(6 DOWNTO 0);
		
		--------- HEX2 ---------
		HEX2			: out std_logic_vector(6 DOWNTO 0);
		
		--------- HEX3 ---------
		HEX3			: out std_logic_vector(6 DOWNTO 0);
		
		--------- KEY ---------
		KEY_n			: in std_logic_vector(3 DOWNTO 0);

		--------- LEDG ---------
		LEDG			: out std_logic_vector(7 DOWNTO 0);

		--------- LEDR ---------
		LEDR			: out std_logic_vector(9 DOWNTO 0);
		
		--------- SW ---------
		SW				: in std_logic_vector(9 DOWNTO 0);

		--------- UART ---------
		UART_RX		: in std_logic;
		UART_TX		: out std_logic
);

end entity;

architecture arch_top_c5g of top_c5g is

component riscv_soc is port 
( 
  i_rst     : in std_logic;
  i_clk     : in std_logic;
  i_clk_pwm : in std_logic;
  --! UART1 signals:
  i_uart1_ctsn : in std_logic;
  i_uart1_rd   : in std_logic;
  o_uart1_td   : out std_logic;
  o_uart1_rtsn : out std_logic;
  --! GPIO
  KEY				: in std_logic_vector(3 DOWNTO 0);
  SW				: in std_logic_vector(9 DOWNTO 0);
  LEDG			: out std_logic_vector(7 DOWNTO 0);
  LEDR			: out std_logic_vector(9 DOWNTO 0);
  GPIO_IN		: in std_logic_vector(17 DOWNTO 0);
  GPIO_OUT		: out std_logic_vector(17 DOWNTO 0);
  --! Time measurements
  HEX0			: out std_logic_vector(6 DOWNTO 0);
  HEX1			: out std_logic_vector(6 DOWNTO 0);
  HEX2			: out std_logic_vector(6 DOWNTO 0);
  HEX3			: out std_logic_vector(6 DOWNTO 0)
);
end component;

  signal i_rst     : std_logic;
  signal KEY       : std_logic_vector(3 DOWNTO 0);

  signal w_ext_reset : std_ulogic; -- External system reset or PLL unlcoked. MUST NOT USED BY DEVICES.
  signal w_clk_bus  : std_ulogic; -- bus clock from the internal PLL (100MHz virtex6/40MHz Spartan6)
  signal w_clk_pwm : std_ulogic;
  signal w_pll_lock : std_ulogic; -- PLL status signal. 0=Unlocked; 1=locked.

begin

  i_rst <= not(CPU_RESET_n);
  KEY   <= not(KEY_n);

  ------------------------------------
  -- @brief Internal PLL device instance.
  pll0 : SysPLL_tech generic map (
    tech => CFG_FABTECH
  ) port map (
    i_reset     => i_rst,
    i_clk_tcxo	=> CLOCK_125_p,
    o_clk_bus   => w_clk_bus,
    o_clk_pwm   => w_clk_pwm,
    o_locked    => w_pll_lock
  );
  w_ext_reset <= i_rst or not w_pll_lock;


  soc0 : riscv_soc port map
  ( 
    i_rst     => w_ext_reset,
    i_clk     => w_clk_bus,
    i_clk_pwm => w_clk_pwm,
    --! UART1 signals:
    i_uart1_ctsn => '0',
    i_uart1_rd   => UART_RX,
    o_uart1_td   => UART_TX,
    o_uart1_rtsn => open,
	 --! GPIO
    KEY			  => KEY,
    SW			  => SW,
    LEDG			  => LEDG,
    LEDR			  => LEDR,
    GPIO_IN		  => GPIO_IN,
    GPIO_OUT	  => GPIO_OUT,
	 --! Time measurements
    HEX0			  => HEX0,
    HEX1			  => HEX1,
    HEX2			  => HEX2,
    HEX3			  => HEX3
  );

end arch_top_c5g;

