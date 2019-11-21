// The Potato SoC Library add from hossameldin
// (c) Hossameldin Bayoummy Eassa 2019 <hossameassa@gmail.com>

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

/**
 * Initializes a io_per instance.
 * @param module Pointer to a io_per instance structure.
 * @param base   Pointer to the base address of the io_per hardware instance.
 */
static inline void io_per_initialize(struct io_per * module, volatile void * base)
{
	module->registers = base;
}

static inline uint32_t io_per_get_input(struct io_per * module, volatile uint32_t submodule, uint32_t index)
{
	return module->registers[(submodule >> 2) + index];
}

static inline void io_per_set_output(struct io_per * module, volatile uint32_t submodule, uint32_t index, volatile uint32_t output)
{
	module->registers[(submodule >> 2) + index] = output;
}

#endif

