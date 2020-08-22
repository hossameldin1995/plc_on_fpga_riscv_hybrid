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

-- 0x00000000		64 KB		Boot ROM
-- 0x10000000		256KB		RAM
-- 0x80000000		4  KB		GPIO
-- 0x80001000		4  KB		UART1
-- 0x80002000		4  KB		IRQ Controller
-- 0x80003000		4  KB		GP Timers				Two general purpose timers with RTC
-- 0x80004000		4  KB		Time Measurement
-- 0x80005000		4  KB		TON0
-- 0x80006000		4  KB		PWM0
-- 0x80007000		4  KB		PID0

--! Standard library
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

--! Data transformation and math functions library
library commonlib;
use commonlib.types_common.all;

--! Technology definition library.
library techmap;
--! Technology constants definition.
use techmap.gencomp.all;

--! AMBA system bus specific library
library ambalib;
--! AXI4 configuration constants.
use ambalib.types_amba4.all;
use ambalib.types_bus0.all;
--! Misc modules library
library misclib;
use misclib.types_misc.all;

--! River CPU specific library
library riverlib;
--! River top level with AMBA interface module declaration
use riverlib.types_river.all;

library target;
use target.config_target.all;

--! @brief   SOC Top-level entity declaration.
--! @details This module implements full SOC functionality and all IO signals
--!          are available on FPGA/ASIC IO pins.
entity riscv_soc is port 
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
  --! @}

end riscv_soc;

--! @brief SOC top-level  architecture declaration.
architecture arch_riscv_soc of riscv_soc is

  signal w_glob_rst  : std_ulogic; -- Global reset active HIGH
  signal w_glob_nrst : std_ulogic; -- Global reset active LOW
  signal w_bus_nrst : std_ulogic; -- Global reset and Soft Reset active LOW
  
  signal uart1i : uart_in_type;
  signal uart1o : uart_out_type;

  --! Arbiter is switching only slaves output signal, data from noc
  --! is connected to all slaves and to the arbiter itself.
  signal aximi   : bus0_xmst_in_vector;
  signal aximo   : bus0_xmst_out_vector;
  signal axisi   : bus0_xslv_in_vector;
  signal axiso   : bus0_xslv_out_vector;
  signal slv_cfg : bus0_xslv_cfg_vector;
  signal w_ext_irq : std_logic;
  signal dport_i : dport_in_vector;

  signal irq_pins : std_logic_vector(CFG_IRQ_TOTAL-1 downto 1);
begin


 ------------------------------------
  --! @brief System Reset device instance.
  rst0 : reset_global port map (
    inSysReset  => i_rst,
    inSysClk    => i_clk,
    outReset    => w_glob_rst
  );
  w_glob_nrst <= not w_glob_rst;
  w_bus_nrst <= not w_glob_rst;

  --! @brief AXI4 controller.
  ctrl0 : axictrl_bus0 generic map (
    async_reset => CFG_ASYNC_RESET
  ) port map (
    i_clk    => i_clk,
    i_nrst   => w_glob_nrst,
    i_slvcfg => slv_cfg,
    i_slvo   => axiso,
    i_msto   => aximo,
    o_slvi   => axisi,
    o_msti   => aximi,
    o_bus_util_w => open, -- Bus write access utilization per master statistic
    o_bus_util_r => open  -- Bus read access utilization per master statistic
  );

  --! @brief RISC-V Processor core (River or Rocket).
  cpu0 : river_amba generic map (
    memtech  => CFG_MEMTECH,
    hartid => 0,
    async_reset => CFG_ASYNC_RESET
  ) port map ( 
    i_nrst   => w_bus_nrst,
    i_clk    => i_clk,
    i_msti   => aximi(CFG_BUS0_XMST_CPU0),
    o_msto   => aximo(CFG_BUS0_XMST_CPU0),
    o_mstcfg => open,
    i_dport => dport_i(0),
    o_dport => open,
    i_ext_irq => w_ext_irq
  );

  ------------------------------------
  --! @brief BOOT ROM module instance with the AXI4 interface.
  --! @details Map address:
  --!          0x00000000..0x00000000+((CFG_ROM_SIZE*1024) - 1)
  boot0 : axi4_rom generic map (
    memtech  => CFG_MEMTECH,
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#00000#,
    xmask    => CFG_ROM_MASK,
    sim_hexfile => CFG_SIM_BOOTROM_HEX
  ) port map (
    clk  => i_clk,
    nrst => w_glob_nrst,
    cfg  => slv_cfg(CFG_BUS0_XSLV_BOOTROM),
    i    => axisi(CFG_BUS0_XSLV_BOOTROM),
    o    => axiso(CFG_BUS0_XSLV_BOOTROM)
  );

  ------------------------------------
  --! Internal SRAM module instance with the AXI4 interface.
  --! @details Map address:
  --!          0x10000000..0x10000000+((CFG_RAM_SIZE*1024) - 1)
  sram0 : axi4_sram generic map (
    memtech  => CFG_MEMTECH,
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#10000#,
    xmask    => CFG_RAM_MASK,
    abits    => (10 + log2(CFG_RAM_SIZE)),
    init_file => CFG_SIM_FWIMAGE_HEX  -- Used only for inferred
  ) port map (
    clk  => i_clk,
    nrst => w_glob_nrst,
    cfg  => slv_cfg(CFG_BUS0_XSLV_SRAM),
    i    => axisi(CFG_BUS0_XSLV_SRAM),
    o    => axiso(CFG_BUS0_XSLV_SRAM)
  );


  ------------------------------------
  --! @brief Controller of the LEDs, DIPs and GPIO with the AXI4 interface.
  --! @details Map address:
  --!          0x80000000..0x80000fff (4 KB total)
  gpio0 : axi4_gpio generic map (
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#80000#,
    xmask    => 16#fffff#,
    xirq     => 0
  ) port map (
    clk   	=> i_clk,
    nrst  	=> w_glob_nrst,
    cfg   	=> slv_cfg(CFG_BUS0_XSLV_GPIO),
    i			=> axisi(CFG_BUS0_XSLV_GPIO),
    o			=> axiso(CFG_BUS0_XSLV_GPIO),
    KEY		=> KEY,
	 SW		=> SW,
	 LEDG		=> LEDG,
	 LEDR		=> LEDR,
	 GPIO_IN	=> GPIO_IN,
	 GPIO_OUT=> GPIO_OUT
  );
  
  
  ------------------------------------
  uart1i.cts   <= not i_uart1_ctsn;
  uart1i.rd    <= i_uart1_rd;

  --! @brief UART Controller with the AXI4 interface.
  --! @details Map address:
  --!          0x80001000..0x80001fff (4 KB total)
  uart1 : axi4_uart generic map (
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#80001#,
    xmask    => 16#FFFFF#,
    xirq     => CFG_IRQ_UART1,
    fifosz   => 16
  ) port map (
    nrst   => w_glob_nrst, 
    clk    => i_clk, 
    cfg    => slv_cfg(CFG_BUS0_XSLV_UART1),
    i_uart => uart1i, 
    o_uart => uart1o,
    i_axi  => axisi(CFG_BUS0_XSLV_UART1),
    o_axi  => axiso(CFG_BUS0_XSLV_UART1),
    o_irq  => irq_pins(CFG_IRQ_UART1)
  );
  o_uart1_td  <= uart1o.td;
  o_uart1_rtsn <= not uart1o.rts;


  ------------------------------------
  --! @brief Interrupt controller with the AXI4 interface.
  --! @details Map address:
  --!          0x80002000..0x80002fff (4 KB total)
  irq0 : axi4_irqctrl generic map (
    async_reset => CFG_ASYNC_RESET,
    xaddr      => 16#80002#,
    xmask      => 16#FFFFF#
  ) port map (
    clk    => i_clk,
    nrst   => w_bus_nrst,
    i_irqs => irq_pins,
    o_cfg  => slv_cfg(CFG_BUS0_XSLV_IRQCTRL),
    i_axi  => axisi(CFG_BUS0_XSLV_IRQCTRL),
    o_axi  => axiso(CFG_BUS0_XSLV_IRQCTRL),
    o_irq_meip => w_ext_irq
  );

  --! @brief Timers with the AXI4 interface.
  --! @details Map address:
  --!          0x80003000..0x80003fff (4 KB total)
  gptmr0 : axi4_gptimers  generic map (
    async_reset => CFG_ASYNC_RESET,
    xaddr     => 16#80003#,
    xmask     => 16#fffff#,
    xirq      => CFG_IRQ_GPTIMERS,
    tmr_total => 2
  ) port map (
    clk    => i_clk,
    nrst   => w_glob_nrst,
    cfg    => slv_cfg(CFG_BUS0_XSLV_GPTIMERS),
    i_axi  => axisi(CFG_BUS0_XSLV_GPTIMERS),
    o_axi  => axiso(CFG_BUS0_XSLV_GPTIMERS),
    o_irq  => irq_pins(CFG_IRQ_GPTIMERS)
  );
  
  time_measurement : axi4_time_measurement generic map (
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#80004#,
    xmask    => 16#fffff#,
    xirq     => 0
  ) port map (
    clk   	=> i_clk,
    nrst  	=> w_glob_nrst,
    cfg   	=> slv_cfg(CFG_BUS0_XSLV_TIME_MEASUREMENT),
    i			=> axisi(CFG_BUS0_XSLV_TIME_MEASUREMENT),
    o			=> axiso(CFG_BUS0_XSLV_TIME_MEASUREMENT),
	 HEX0			=> HEX0,
	 HEX1			=> HEX1,
	 HEX2			=> HEX2,
	 HEX3			=> HEX3
  );
  
  TON0 : axi4_ton generic map (
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#80005#,
    xmask    => 16#fffff#,
    xirq     => 0
  ) port map (
    clk   	=> i_clk,
    nrst  	=> w_glob_nrst,
    cfg   	=> slv_cfg(CFG_BUS0_XSLV_TON0),
    i			=> axisi(CFG_BUS0_XSLV_TON0),
    o			=> axiso(CFG_BUS0_XSLV_TON0)
  );
  
  PWM0 : axi4_pwm generic map (
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#80006#,
    xmask    => 16#fffff#,
    xirq     => 0
  ) port map (
    clk   	=> i_clk,
    clk_pwm 	=> i_clk_pwm,
    nrst  	=> w_glob_nrst,
    cfg   	=> slv_cfg(CFG_BUS0_XSLV_PWM0),
    i			=> axisi(CFG_BUS0_XSLV_PWM0),
    o			=> axiso(CFG_BUS0_XSLV_PWM0)
  );
  
  PID0 : axi4_pid generic map (
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#80007#,
    xmask    => 16#fffff#,
    xirq     => 0
  ) port map (
    clk   	=> i_clk,
    nrst  	=> w_glob_nrst,
    cfg   	=> slv_cfg(CFG_BUS0_XSLV_PID0),
    i			=> axisi(CFG_BUS0_XSLV_PID0),
    o			=> axiso(CFG_BUS0_XSLV_PID0)
  );


end arch_riscv_soc;
