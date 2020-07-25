/*
 *  Copyright 2020 Hossameldin Eassa, hossameassa@gmail.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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