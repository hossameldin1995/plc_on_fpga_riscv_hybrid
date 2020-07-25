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


#ifndef PAEE_I_O_PERIPHERAL_H
#define PAEE_I_O_PERIPHERAL_H

#include <stdbool.h>
#include <stdint.h>

#define GPIO_IN		 	0x000    // 0x00
#define SW				0x048    // 0x12
#define KEY				0x070    // 0x1c

#define GPIO_OUT		0x100    // 0x40
#define LEDR			0x148    // 0x52
#define LEDG			0x170    // 0x5c

#define RWD				0x1fc    // 0x7f

#define LED_ON			0x001
#define LED_OFF			0x000
typedef struct io_per
{
	volatile uint32_t * registers;
} io_per;

static inline uint32_t io_per_get_input(struct io_per * module, volatile uint32_t submodule, uint32_t index)
{
	return module->registers[(submodule >> 2) + index];
}

static inline void io_per_set_output(struct io_per * module, volatile uint32_t submodule, uint32_t index, volatile uint32_t output)
{
	module->registers[(submodule >> 2) + index] = output;
}

#endif

