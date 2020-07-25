/******************************************************************************
 * @file
 * @copyright Copyright 2019 Hossameldin Eassa All right reserved.
 * @author    Hossameldin Eassa - hossameassa@gmail.com
 * @brief     Timer HW memory map.
******************************************************************************/

#ifndef LIBSOC_TIMER_HW_H
#define LIBSOC_TIMER_HW_H

#include <stdint.h>

// Timer register offsets:
#define Elapsed_Time_TON	0x00
#define Q_TON         	0x08
#define Preset_Time_TON		0x00
#define IN_TON        	0x08

typedef struct timer_hw
{
	volatile uint64_t * registers;
} timer_hw;

static inline void timer_hw_send_in(struct timer_hw * module, uint32_t in_data)
{
	module->registers[Q_TON >> 3] = in_data;
}

static inline void timer_hw_send_preset_time(struct timer_hw * module, uint64_t preset_time)
{
	module->registers[Preset_Time_TON >> 3] = preset_time;
}

static inline uint64_t timer_hw_recieve_elapsed_time(struct timer_hw  * module)
{
	return module->registers[Elapsed_Time_TON >> 3];;
}

static inline uint32_t timer_hw_recieve_Q(struct timer_hw  * module)
{
	return (uint32_t) module->registers[Q_TON >> 3];
}

#endif