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

-- axi_address		Size		Peripheral				Discription
-- 0x00000000		32 KB		Boot ROM
-- 0x00100000		64 KB		Application ROM
-- 0x10000000		256KB		RAM
-- 0x80000000		4  KB		GPIO
-- 0x80001000		4  KB		UART1
-- 0x80002000		4  KB		IRQ Controller
-- 0x80003000		4  KB		GP Timers				Two general purpose timers with RTC
-- 0x80004000		4	KB		Time Measurement
-- 0x80005000		4  KB		TON0
-- 0x80006000		4  KB		PWM0

--! Standard library
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

--! Data transformation and math functions library
--library commonlib;
use work.types_common.all;

--! Technology definition library.
--library techmap;
--! Technology constants definition.
use work.gencomp.all;

--! AMBA system bus specific library
--library ambalib;
--! AXI4 configuration constants.
use work.types_amba4.all;
use work.types_bus0.all;
--! Misc modules library
--library misclib;
use work.types_misc.all;
--! Ethernet related declarations.
--library ethlib;
use work.types_eth.all;

--! River CPU specific library
--library riverlib;
--! River top level with AMBA interface module declaration
use work.types_river.all;

 --! Top-level implementaion library
library work;
--! Target dependable configuration: RTL, FPGA or ASIC.
use work.config_target.all;

--! @brief   SOC Top-level entity declaration.
--! @details This module implements full SOC functionality and all IO signals
--!          are available on FPGA/ASIC IO pins.
entity riscv_soc is port 
( 
  i_rst     : in std_logic;
  i_clk  	: in std_logic;
  i_clk_50	: in std_logic;
  --! GPIO.
  --i_gpio     : in std_logic_vector(11 downto 0);
  --o_gpio     : out std_logic_vector(11 downto 0);
  --o_gpio_dir : out std_logic_vector(11 downto 0);
  --! JTAG signals:
  --i_jtag_tck : in std_logic;
  --i_jtag_ntrst : in std_logic;
  --i_jtag_tms : in std_logic;
  --i_jtag_tdi : in std_logic;
  --o_jtag_tdo : out std_logic;
  --o_jtag_vref : out std_logic;
  --! UART1 signals:
  i_uart1_ctsn : in std_logic;
  i_uart1_rd   : in std_logic;
  o_uart1_td   : out std_logic;
  o_uart1_rtsn : out std_logic;
  --! UART2 (debug port) signals:
  --i_uart2_ctsn : in std_logic;
  --i_uart2_rd   : in std_logic;
  --o_uart2_td   : out std_logic;
  --o_uart2_rtsn : out std_logic;
  --! Ethernet MAC PHY interface signals
  --i_etx_clk   : in    std_ulogic;
  --i_erx_clk   : in    std_ulogic;
  --i_erxd      : in    std_logic_vector(3 downto 0);
  --i_erx_dv    : in    std_ulogic;
  --i_erx_er    : in    std_ulogic;
  --i_erx_col   : in    std_ulogic;
  --i_erx_crs   : in    std_ulogic;
  --i_emdint    : in std_ulogic;
  --o_etxd      : out   std_logic_vector(3 downto 0);
  --o_etx_en    : out   std_ulogic;
  --o_etx_er    : out   std_ulogic;
  --o_emdc      : out   std_ulogic;
  --i_eth_mdio    : in std_logic;
  --o_eth_mdio    : out std_logic;
  --o_eth_mdio_oe : out std_logic;
  --i_eth_gtx_clk    : in std_logic;
  --i_eth_gtx_clk_90 : in std_logic;
  --o_erstn     : out   std_ulogic
  KEY				: in std_logic_vector(3 DOWNTO 0);
  SW				: in std_logic_vector(9 DOWNTO 0);
  LEDG			: out std_logic_vector(7 DOWNTO 0);
  LEDR			: out std_logic_vector(9 DOWNTO 0);
  GPIO_IN		: in std_logic_vector(17 DOWNTO 0);
  GPIO_OUT		: out std_logic_vector(17 DOWNTO 0);
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
  signal w_soft_rst : std_ulogic; -- Software reset (acitve HIGH) from DSU
  signal w_bus_nrst : std_ulogic; -- Global reset and Soft Reset active LOW
  
  signal uart1i : uart_in_type;
  signal uart1o : uart_out_type;
  --signal uart2i : uart_in_type;
  --signal uart2o : uart_out_type;

  --! Arbiter is switching only slaves output signal, data from noc
  --! is connected to all slaves and to the arbiter itself.
  signal aximi   : bus0_xmst_in_vector;
  signal aximo   : bus0_xmst_out_vector;
  signal axisi   : bus0_xslv_in_vector;
  signal axiso   : bus0_xslv_out_vector;
  signal slv_cfg : bus0_xslv_cfg_vector;
  signal mst_cfg : bus0_xmst_cfg_vector;
  signal w_ext_irq : std_logic;
  signal dport_i : dport_in_vector;
  signal dport_o : dport_out_vector;
  signal wb_bus_util_w : std_logic_vector(CFG_BUS0_XMST_TOTAL-1 downto 0);
  signal wb_bus_util_r : std_logic_vector(CFG_BUS0_XMST_TOTAL-1 downto 0);
  
  --signal eth_i : eth_in_type;
  --signal eth_o : eth_out_type;
 
  signal irq_pins : std_logic_vector(CFG_IRQ_TOTAL-1 downto 1);
begin


  -- Nullify emty AXI-slots:  
  --axiso(CFG_BUS0_XSLV_ENGINE) <= axi4_slave_out_none;
  --slv_cfg(CFG_BUS0_XSLV_ENGINE)  <= axi4_slave_config_none;
  --irq_pins(CFG_IRQ_GNSSENGINE)      <= '0';
  --slv_cfg(CFG_BUS0_XSLV_RFCTRL) <= axi4_slave_config_none;
  --axiso(CFG_BUS0_XSLV_RFCTRL) <= axi4_slave_out_none;
  --slv_cfg(CFG_BUS0_XSLV_FSE_GPS) <= axi4_slave_config_none;
  --axiso(CFG_BUS0_XSLV_FSE_GPS) <= axi4_slave_out_none;


  ------------------------------------
  --! @brief System Reset device instance.
  rst0 : reset_global port map (
    inSysReset  => i_rst,
    inSysClk    => i_clk,
    outReset    => w_glob_rst
  );
  w_glob_nrst <= not w_glob_rst;
  w_bus_nrst <= not (w_glob_rst or w_soft_rst);

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
    o_bus_util_w => wb_bus_util_w, -- Bus write access utilization per master statistic
    o_bus_util_r => wb_bus_util_r  -- Bus read access utilization per master statistic
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
    o_mstcfg => mst_cfg(CFG_BUS0_XMST_CPU0),
    i_dport => dport_i(0),
    o_dport => dport_o(0),
    i_ext_irq => w_ext_irq
  );

  dualcore_ena : if CFG_COMMON_DUAL_CORE_ENABLE generate
      cpu1 : river_amba generic map (
        memtech  => CFG_MEMTECH,
        hartid => 1,
        async_reset => CFG_ASYNC_RESET
      ) port map ( 
        i_nrst   => w_bus_nrst,
        i_clk    => i_clk,
        i_msti   => aximi(CFG_BUS0_XMST_CPU1),
        o_msto   => aximo(CFG_BUS0_XMST_CPU1),
        o_mstcfg => mst_cfg(CFG_BUS0_XMST_CPU1),
        i_dport => dport_i(1),
        o_dport => dport_o(1),
        i_ext_irq => '0'  -- todo: 
      );
  end generate;

  dualcore_dis : if not CFG_COMMON_DUAL_CORE_ENABLE generate
      aximo(CFG_BUS0_XMST_CPU1) <= axi4_master_out_none;
      mst_cfg(CFG_BUS0_XMST_CPU1) <= axi4_master_config_none;
		dport_o(1) <= dport_out_none;
  end generate;


--dsu_ena : if CFG_DSU_ENABLE generate
  ------------------------------------
  --! @brief Debug Support Unit with access to the CSRs
  --! @details Map address:
  --!          0x80080000..0x8009ffff (128 KB total)
  --dsu0 : axi_dsu generic map (
  --  async_reset => CFG_ASYNC_RESET,
  --  xaddr    => 16#80080#,
  --  xmask    => 16#fffe0#
  --) port map (
  --  clk    => i_clk,
  --  nrst   => w_glob_nrst,
  --  o_cfg  => slv_cfg(CFG_BUS0_XSLV_DSU),
  --  i_axi  => axisi(CFG_BUS0_XSLV_DSU),
  --  o_axi  => axiso(CFG_BUS0_XSLV_DSU),
  --  o_dporti => dport_i,
  --  i_dporto => dport_o,
  --  o_soft_rst => w_soft_rst,
    -- Run time platform statistic signals (move to tracer):
  --  i_bus_util_w => wb_bus_util_w, -- Write access bus utilization per master statistic
   -- i_bus_util_r => wb_bus_util_r  -- Read access bus utilization per master statistic
  --);
--end generate;
--dsu_dis : if not CFG_DSU_ENABLE generate
  --  slv_cfg(CFG_BUS0_XSLV_DSU) <= axi4_slave_config_none;
  --  axiso(CFG_BUS0_XSLV_DSU) <= axi4_slave_out_none;
  --  dport_i <= (others => dport_in_none);
	 w_soft_rst <= '0';
--end generate;

  ------------------------------------
  -- JTAG TAP interface
  --jtag0 : tap_jtag  generic map (
  --  ainst  => 2,
  --  dinst  => 3
  --) port map (
  --  nrst   => w_glob_nrst, 
  --  clk    => i_clk, 
  --  i_tck  => i_jtag_tck,
  --  i_ntrst  => i_jtag_ntrst,
  --  i_tms  => i_jtag_tms,
  --  i_tdi  => i_jtag_tdi,
  --  o_tdo  => o_jtag_tdo,
  --  o_jtag_vref => o_jtag_vref,
  --  i_msti   => aximi(CFG_BUS0_XMST_JTAG),
  --  o_msto   => aximo(CFG_BUS0_XMST_JTAG),
  --  o_mstcfg => mst_cfg(CFG_BUS0_XMST_JTAG)
  --  );

  ------------------------------------
  --! @brief TAP via UART (debug port) with master interface.
  --uart2i.cts   <= not i_uart2_ctsn;
  --uart2i.rd    <= i_uart2_rd;
  --uart2 : uart_tap  port map (
  --  nrst   => w_glob_nrst, 
  --  clk    => i_clk, 
  --  i_uart   => uart2i,
  --  o_uart   => uart2o,
  --  i_msti   => aximi(CFG_BUS0_XMST_MSTUART),
  --  o_msto   => aximo(CFG_BUS0_XMST_MSTUART),
  --  o_mstcfg => mst_cfg(CFG_BUS0_XMST_MSTUART)
  --);
  --o_uart2_td  <= uart2o.td;
  --o_uart2_rtsn <= not uart2o.rts;

  ------------------------------------
  --! @brief BOOT ROM module instance with the AXI4 interface.
  --! @details Map address:
  --!          0x00000000..0x00007fff (32 KB total)
  boot0 : axi4_rom generic map (
    memtech  => CFG_MEMTECH,
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#00000#,
    xmask    => 16#ffff8#,
    sim_hexfile => CFG_SIM_BOOTROM_HEX,
    cyc_miffile => CFG_CYC_BOOTROM_MIF
  ) port map (
    clk  => i_clk,
    nrst => w_glob_nrst,
    cfg  => slv_cfg(CFG_BUS0_XSLV_BOOTROM),
    i    => axisi(CFG_BUS0_XSLV_BOOTROM),
    o    => axiso(CFG_BUS0_XSLV_BOOTROM)
  );

  ------------------------------------
  --! @brief Firmware Image ROM with the AXI4 interface.
  --! @details Map address:
  --!          0x00100000..0x0010ffff (64 KB total)
  --! @warning Don't forget to change ROM_ADDR_WIDTH in rom implementation
  img0 : axi4_rom generic map (
    memtech  => CFG_MEMTECH,
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#00100#,
    xmask    => 16#FFFF0#,
    sim_hexfile => CFG_SIM_FWIMAGE_HEX,
    cyc_miffile => CFG_CYC_FWIMAGE_MIF
  ) port map (
    clk  => i_clk,
    nrst => w_glob_nrst,
    cfg  => slv_cfg(CFG_BUS0_XSLV_ROMIMAGE),
    i    => axisi(CFG_BUS0_XSLV_ROMIMAGE),
    o    => axiso(CFG_BUS0_XSLV_ROMIMAGE)
  );

  ------------------------------------
  --! Internal SRAM module instance with the AXI4 interface.
  --! @details Map address:
  --!          0x10000000..0x1003ffff (256 KB total)
  ram0 : axi4_sram generic map (
    memtech  => CFG_MEMTECH,
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#10000#,
    xmask    => 16#fffc0#,            -- 256 KB mask -- old (256) fff80
    abits    => (10 + log2(256)),     -- 256 KB address
    init_file => ""  -- Used only for inferred
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


  --! @brief Ethernet MAC with the AXI4 interface.
  --! @details Map address:
  --!          0x80040000..0x8007ffff (256 KB total)
  --!          EDCL IP: 192.168.1.51 = C0.A8.01.33
  --eth0_ena : if CFG_ETHERNET_ENABLE generate 
  
  --  eth_i.tx_clk <= i_etx_clk;
  --  eth_i.rx_clk <= i_erx_clk;
  --  eth_i.rxd <= i_erxd;
  --  eth_i.rx_dv <= i_erx_dv;
  --  eth_i.rx_er <= i_erx_er;
  --  eth_i.rx_col <= i_erx_col;
  --  eth_i.rx_crs <= i_erx_crs;
  --  eth_i.mdint <= i_emdint;
--	 eth_i.mdio_i <= i_eth_mdio;
--	 eth_i.gtx_clk <= i_eth_gtx_clk;
    
  --  mac0 : grethaxi generic map (
   --   xaddr => 16#80040#,
   --   xmask => 16#FFFC0#,
   --   xirq => CFG_IRQ_ETHMAC,
   --   memtech => CFG_MEMTECH,
   --   mdcscaler => 60,  --! System Bus clock in MHz
   --   enable_mdio => 1,
   --   fifosize => 16,
   --   nsync => 1,
   --   edcl => 1,
   --   edclbufsz => 16,
   --   macaddrh => 16#20789#,
   --   macaddrl => 16#123#,
   --   ipaddrh => 16#C0A8#,
   --   ipaddrl => 16#0033#,
   --   phyrstadr => 7,
   --   enable_mdint => 1,
   --   maxsize => 1518
   --) port map (
   --   rst => w_glob_nrst,
   --   clk => i_clk,
   --   msti => aximi(CFG_BUS0_XMST_ETHMAC),
   --   msto => aximo(CFG_BUS0_XMST_ETHMAC),
   --   mstcfg => mst_cfg(CFG_BUS0_XMST_ETHMAC),
   --   msto2 => open,    -- EDCL separate access is disabled
   --   mstcfg2 => open,  -- EDCL separate access is disabled
   --   slvi => axisi(CFG_BUS0_XSLV_ETHMAC),
   --   slvo => axiso(CFG_BUS0_XSLV_ETHMAC),
   --   slvcfg => slv_cfg(CFG_BUS0_XSLV_ETHMAC),
   --   ethi => eth_i,
   --   etho => eth_o,
   --   irq => irq_pins(CFG_IRQ_ETHMAC)
   -- );
	 
  --end generate;
  
  --! Ethernet disabled
  --eth0_dis : if not CFG_ETHERNET_ENABLE generate 
  --    slv_cfg(CFG_BUS0_XSLV_ETHMAC) <= axi4_slave_config_none;
  --    axiso(CFG_BUS0_XSLV_ETHMAC) <= axi4_slave_out_none;
  --    mst_cfg(CFG_BUS0_XMST_ETHMAC) <= axi4_master_config_none;
  --    aximo(CFG_BUS0_XMST_ETHMAC) <= axi4_master_out_none;
  --    irq_pins(CFG_IRQ_ETHMAC) <= '0';
	--	eth_i.gtx_clk <= '0';
   --   eth_o   <= eth_out_none;
  --end generate;

  --o_etxd <= eth_o.txd;
  --o_etx_en <= eth_o.tx_en;
  --o_etx_er <= eth_o.tx_er;
  --o_emdc <= eth_o.mdc;
  --o_eth_mdio <= eth_o.mdio_o;
  --o_eth_mdio_oe <= eth_o.mdio_oe;
  --o_erstn <= w_glob_nrst;


  --! @brief Plug'n'Play controller of the current configuration with the
  --!        AXI4 interface.
  --! @details Map address:
  --!          0xfffff000..0xffffffff (4 KB total)
  --pnp0 : axi4_pnp generic map (
  --  async_reset => CFG_ASYNC_RESET,
  --  xaddr   => 16#fffff#,
  --  xmask   => 16#fffff#,
  --  tech    => CFG_MEMTECH,
  --  hw_id   => CFG_HW_ID
  --) port map (
  --  sys_clk => i_clk, 
  --  adc_clk => '0',
  --  nrst   => w_glob_nrst,
  --  mstcfg => mst_cfg,
  --  slvcfg => slv_cfg,
  --  cfg    => slv_cfg(CFG_BUS0_XSLV_PNP),
  --  i      => axisi(CFG_BUS0_XSLV_PNP),
  --  o      => axiso(CFG_BUS0_XSLV_PNP)
  --);

  time_measurement : entity work.axi4_time_measurement generic map (
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

  TON0 : entity work.axi4_ton generic map (
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
  
  PWM0 : entity work.axi4_pwm generic map (
    async_reset => CFG_ASYNC_RESET,
    xaddr    => 16#80006#,
    xmask    => 16#fffff#,
    xirq     => 0
  ) port map (
    clk   	=> i_clk,
    clk_50 	=> i_clk_50,
    nrst  	=> w_glob_nrst,
    cfg   	=> slv_cfg(CFG_BUS0_XSLV_PWM0),
    i			=> axisi(CFG_BUS0_XSLV_PWM0),
    o			=> axiso(CFG_BUS0_XSLV_PWM0)
  );
end arch_riscv_soc;
