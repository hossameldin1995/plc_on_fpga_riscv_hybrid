// The Potato SoC Library add from hossameldin
// (c) Hossameldin Bayoummy Eassa 2019 <hossameassa@gmail.com>

#ifndef LIBSOC_PWM_HW_H
#define LIBSOC_PWM_HW_H

#include <stdint.h>

// PWM register offsets:
#define Q_PWM				0x10
#define Frequency_Address	0x00
#define Duty_Cycle_Address	0x08

typedef struct pwm_hw
{
	volatile uint32_t * registers;
} pwm_hw;

static inline void pwm_hw_initialize(struct pwm_hw * module, volatile void * base_address)
{
	module->registers = base_address;
}

static inline void pwm_hw_send_frequency(struct pwm_hw * module, uint32_t frequency)
{
	module->registers[Frequency_Address >> 2] = (uint32_t) frequency;
}

static inline void pwm_hw_send_duty_cycle(struct pwm_hw * module, uint32_t duty_cycle)
{
	module->registers[Duty_Cycle_Address >> 2] = (uint32_t) duty_cycle;
}

static inline uint32_t pwm_hw_recieve_Q(struct pwm_hw  * module)
{
	return (uint32_t) module->registers[Q_PWM >> 2];
}

static inline uint32_t pwm_hw_recieve_Freq(struct pwm_hw  * module)
{
	return module->registers[Frequency_Address >> 2];
}

static inline uint32_t pwm_hw_recieve_DC(struct pwm_hw  * module)
{
	return module->registers[Duty_Cycle_Address >> 2];
}

#endif