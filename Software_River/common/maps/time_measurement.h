// The Potato SoC Library add from hossameldin
// (c) Hossameldin Bayoummy Eassa 2019 <hossameassa@gmail.com>

#ifndef PAEE_TIME_MEASUREMENT_H
#define PAEE_TIME_MEASUREMENT_H

#include <stdbool.h>
#include <stdint.h>

#define START_STOP_A	0x00 // Address of writing start and stop
#define START			0x71
#define STOP			0x53

#define MICRO_NANO_A	0x08 // Address of writing Micro Or Nano Measurements
#define MICRO			0x36
#define NANO			0x42

#define READ_TIME_A		0x08


typedef struct time_measurement
{
	volatile uint32_t * registers;
} time_measurement;

static inline void time_measurement_per_initialize(struct time_measurement * module, volatile void * base)
{
	module->registers = base;
}

static inline void start_time(struct time_measurement * module)
{
	module->registers[(START_STOP_A >> 2)] = START;
}

static inline void stop_time(struct time_measurement * module)
{
	module->registers[(START_STOP_A >> 2)] = STOP;
}

static inline void set_micro(struct time_measurement * module)
{
	module->registers[(MICRO_NANO_A >> 2)] = MICRO;
}

static inline void set_nano(struct time_measurement * module)
{
	module->registers[(MICRO_NANO_A >> 2)] = NANO;
}

#endif

