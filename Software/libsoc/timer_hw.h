// The Potato SoC Library add from hossameldin
// (c) Hossameldin Bayoummy Eassa 2019 <hossameassa@gmail.com>

#ifndef LIBSOC_TIMER_HW_H
#define LIBSOC_TIMER_HW_H

#include <stdint.h>

// Timer register offsets:
#define Elapsed_Time_L	0x00
#define Elapsed_Time_H	0x04
#define Q_TON         	0x08
#define Preset_Time_L	0x00
#define Preset_Time_H	0x04
#define IN_TON        	0x08

struct timer_hw
{
	volatile uint32_t * registers;
};

static inline void timer_hw_initialize(struct timer_hw * module, volatile void * base_address)
{
	module->registers = base_address;
}

static inline void timer_hw_send_in(struct timer_hw * module, uint32_t in_data)
{
	module->registers[Q_TON >> 2] = (uint32_t) in_data;
}

static inline void timer_hw_send_preset_time(struct timer_hw * module, uint64_t preset_time)
{
	module->registers[Preset_Time_L >> 2] = (uint32_t) preset_time;
    module->registers[Preset_Time_H >> 2] = (uint32_t) (preset_time >> 32);
}

static inline uint64_t timer_hw_recieve_elapsed_time(struct timer_hw  * module)
{
    uint64_t ret = module->registers[Elapsed_Time_H >> 2];
    ret = ret << 32;
    ret = ret | (module->registers[Elapsed_Time_L >> 2]);
	return ret;
}

static inline uint32_t timer_hw_recieve_Q(struct timer_hw  * module)
{
	return module->registers[Q_TON >> 2];
}

#endif