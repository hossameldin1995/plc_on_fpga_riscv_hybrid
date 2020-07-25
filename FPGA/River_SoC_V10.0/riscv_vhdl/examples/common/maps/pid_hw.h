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

#ifndef LIBSOC_PID_HW_H
#define LIBSOC_PID_HW_H

#include <stdint.h>

#define ADDR_64 3
#define ADDR_32 2

// Write
#define TS_PID		 	(0x00 >> ADDR_64)
#define PV_SP_PID		(0x08 >> ADDR_64)
#define b0_b1_PID   	(0x10 >> ADDR_64)
#define b2_PID			(0x18 >> ADDR_64)

// Read
#define XOUT_PID		(0x00 >> ADDR_64)
#define XOUT_R_PID		(0x08 >> ADDR_64)

typedef struct pid_hw
{
	volatile uint64_t * registers;
} pid_hw;

static inline void pid_hw_send_ts(struct pid_hw * module,  uint64_t ts)
{
	module->registers[TS_PID] = (uint64_t)ts;
}

static inline void pid_hw_send_pv_sp(struct pid_hw * module,  uint32_t pv, uint32_t sp)
{
	uint64_t ret = ((uint64_t)sp<<32);
	module->registers[PV_SP_PID] = (uint64_t) (ret | pv);
}

static inline void pid_hw_send_b0_b1(struct pid_hw * module,  uint32_t b0, uint32_t b1)
{
	uint64_t ret = ((uint64_t)b1<<32);
	module->registers[b0_b1_PID] = (uint64_t) (ret | b0);
}

static inline void pid_hw_send_b2(struct pid_hw * module,  uint32_t b2)
{
	module->registers[b2_PID] = b2;
}

static inline uint32_t pid_hw_recieve_XOUT(struct pid_hw  * module)
{
	return (uint32_t) module->registers[XOUT_PID];
}

static inline uint32_t pid_hw_recieve_XOUT_R(struct pid_hw  * module)
{
	return (uint32_t) module->registers[XOUT_R_PID];
}

#endif