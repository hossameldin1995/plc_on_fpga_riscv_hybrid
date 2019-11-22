// The Potato SoC Library add from hossameldin
// (c) Hossameldin Bayoummy Eassa 2019 <hossameassa@gmail.com>

#ifndef LIBSOC_PWM_HW_H
#define LIBSOC_PWM_HW_H

#include <stdint.h>

// PWM register offsets:
#define Q_PWM				0x00
#define Frequency_Address	0x00
#define Duty_Cycle_Address	0x04

typedef struct pwm_hw
{
	volatile uint64_t * registers;
} pwm_hw;

static inline void pwm_hw_send_frequency_duty_cycle(struct pwm_hw * module, uint32_t frequency,  uint32_t duty_cycle)
{
	uint64_t ret = ((uint64_t)duty_cycle<<32);
	module->registers[Frequency_Address] = (uint64_t) (ret | frequency);
}

static inline uint32_t pwm_hw_recieve_Q(struct pwm_hw  * module)
{
	return (uint32_t) module->registers[Q_PWM];
}

#endif