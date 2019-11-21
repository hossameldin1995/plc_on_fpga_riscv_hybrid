/******************************************************************************
 * @file
 * @copyright Copyright 2015 GNSS Sensor Ltd. All right reserved.
 * @author    Sergey Khabarov - sergeykhbr@gmail.com
 * @brief     AXI4 device mapping
 * @details   Don't use this address directly use Kernel interface to get
 *            detected device interface.
******************************************************************************/

#ifndef __AXI_MAPS_H__
#define __AXI_MAPS_H__

#include <inttypes.h>
#include "axi_const.h"
#include "maps/map_gptimers.h"
#include "maps/map_uart.h"
#include "maps/map_irqctrl.h"
#include "maps/i_o_peripheral.h"
#include "maps/timer_hw.h"
#include "maps/pwm_hw.h"
#include "maps/time_measurement.h"

#define ADDR_NASTI_SLAVE_FWIMAGE        0x00100000
#define ADDR_NASTI_SLAVE_SRAM           0x10000000
#define ADDR_NASTI_SLAVE_GPIO           0x80000000
#define ADDR_NASTI_SLAVE_UART1          0x80001000
#define ADDR_NASTI_SLAVE_IRQCTRL        0x80002000
#define ADDR_NASTI_SLAVE_GPTIMERS       0x80003000
#define ADDR_NASTI_SLAVE_MEASUREMENT    0x80004000
#define ADDR_NASTI_SLAVE_TON0           0x80005000
#define ADDR_NASTI_SLAVE_PWM0           0x80006000


// Interrupt pins assignemts:
#define CFG_IRQ_UNUSED      0
#define CFG_IRQ_UART1       1
#define CFG_IRQ_GPTIMERS    2
#define CFG_IRQ_TOTAL       3

#define SYS_HZ              75000000
#define PWM_HZ              50000000
#endif  // __AXI_MAPS_H__
