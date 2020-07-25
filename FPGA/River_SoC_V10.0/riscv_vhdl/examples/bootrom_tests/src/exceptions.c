/*
 *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com
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

#include <string.h>
#include "axi_maps.h"
#include "encoding.h"
#include "fw_api.h"

static const char EXCEPTION_TABLE_NAME[8] = "extbl";

int get_mcause() {
    int ret;
    asm("csrr %0, mcause" : "=r" (ret));
    return ret;
}

int get_mepc() {
    int ret;
    asm("csrr %0, mepc" : "=r" (ret));
    return ret;
}

int get_mbadaddr() {
    int ret;
    asm("csrr %0, mbadaddr" : "=r" (ret));
    return ret;
}

void exception_instr_load_fault_c() {
    uint64_t mbadaddr = get_mbadaddr();
     printf_uart("Exception >> instr load fault (mbadaddr : %x)", mbadaddr);
}

void exception_load_fault_c() {
    uint64_t mbadaddr = get_mbadaddr();
    printf_uart("Exception >> load fault (mbadaddr : %x)", mbadaddr);
}

void exception_store_fault_c() {
    uint64_t mbadaddr = get_mbadaddr();
    printf_uart("Exception >> store fault (mbadaddr : %x)", mbadaddr);
}

void exception_stack_overflow_c() {
    uint64_t sp;
    // Save current stack pointer into debug regsiter
    asm("mv %0, sp" : "=r" (sp));
    printf_uart("Exception >> stac overflow (sp : %x)", sp);
}

void exception_stack_underflow_c() {
    uint64_t sp;
    // Save current stack pointer into debug regsiter
    asm("mv %0, sp" : "=r" (sp));
    printf_uart("Exception >> stac underflow (sp : %x)", sp);
}

void exception_handler_c() {
    struct io_per io_per_d;
    IRQ_HANDLER *tbl = fw_get_ram_data(EXCEPTION_TABLE_NAME);

    io_per_d.registers = (volatile void *)ADDR_BUS0_XSLV_GPIO;

    int idx = get_mcause();
    if (tbl[idx] == 0) {
        print_uart("mcause:", 7);
        print_uart_hex(idx);
        print_uart(",mepc:", 6);
        print_uart_hex(get_mepc());
        print_uart("\r\n", 2);

        // Exception trap
        io_per_set_output(&io_per_d, LEDR, 0, LED_ON);
        io_per_set_output(&io_per_d, LEDR, 1, LED_ON);
        io_per_set_output(&io_per_d, LEDR, 2, LED_ON);
        io_per_set_output(&io_per_d, RWD, 0, 0);
        while (1) {}
    } else {
        tbl[idx]();
    }
}

void allocate_exception_table() {
    IRQ_HANDLER *extbl = (IRQ_HANDLER *)
        fw_malloc(EXCEPTION_Total * sizeof(IRQ_HANDLER));    
    fw_register_ram_data(EXCEPTION_TABLE_NAME, extbl);

    extbl[EXCEPTION_InstrFault] = exception_instr_load_fault_c;
    extbl[EXCEPTION_LoadFault] = exception_load_fault_c;
    extbl[EXCEPTION_StoreFault] = exception_store_fault_c;
    extbl[EXCEPTION_StackOverflow] = exception_stack_overflow_c;
    extbl[EXCEPTION_StackUnderflow] = exception_stack_underflow_c;
}
