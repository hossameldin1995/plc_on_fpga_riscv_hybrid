/******************************************************************************
 * @file
 * @copyright Copyright 2017 GNSS Sensor Ltd. All right reserved.
 * @author    Sergey Khabarov - sergeykhbr@gmail.com
 * @brief     General Purpose Timers register mapping definition.
******************************************************************************/
#ifndef __MAP_GPTIMERS_H__
#define __MAP_GPTIMERS_H__

#include <inttypes.h>

#define TIMER_CONTROL_ENA      (1 << 0)
#define TIMER_CONTROL_IRQ_ENA  (1 << 1)

#define TIMER_CONTROL_ENT_DISIRQ_NOOV	0x01	// 0b001
#define TIMER_CONTROL_ENT_ENIRQ_NOOV	0x03	// 0b011
#define TIMER_CONTROL_DIST_ENIRQ_NOOV	0x02	// 0b010
#define TIMER_CONTROL_DIST_DISIRQ_NOOV	0x00	// 0b000
#define TIMER_CONTROL_ENT_DISIRQ_OV		0x05	// 0b101
#define TIMER_CONTROL_ENT_ENIRQ_OV		0x07	// 0b111
#define TIMER_CONTROL_DIST_ENIRQ_OV		0x06	// 0b110
#define TIMER_CONTROL_DIST_DISIRQ_OV	0x04	// 0b100

#define TIMER_ENABLED	1
#define TIMER_DISABLED	0

typedef struct gptimer_type {
    volatile uint32_t control;
    volatile uint32_t rsv1;
    volatile uint64_t cur_value;
    volatile uint64_t init_value;
    volatile uint32_t rsrv2[2];
} gptimer_type;


typedef struct gptimers_map {
        uint64_t highcnt;
        uint32_t pending;
        uint32_t rsvr[13];
        gptimer_type timer[2];
} gptimers_map;

#endif  // __MAP_GPTIMERS_H__
