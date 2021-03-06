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

typedef void (*IRQ_HANDLER)(int idx, void *args);

typedef union csr_mcause_type {
    struct bits_type {
        uint64_t code   : 63;   // 11 - Machine external interrupt
        uint64_t irq    : 1;
    } bits;
    uint64_t value;
} csr_mcause_type;

extern void print_uart(const char *buf, int sz);
extern void print_uart_hex(long val);
extern void led_set(int output);

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

int get_uepc() {
    int ret;
    asm("csrr %0, uepc" : "=r" (ret));
    return ret;
}

int get_mstatus(){
    int ret;
    asm("csrr %0, mstatus" : "=r" (ret));
    return ret;
}

void exception_handler_c() {
    print_uart("mcause:", 7);
    print_uart_hex(get_mcause());
    print_uart(",mepc:", 6);
    print_uart_hex(get_mepc());
    print_uart(",uepc:", 6);
    print_uart_hex(get_uepc());
    print_uart(",mstatus:", 9);
    print_uart_hex(get_mstatus());
    print_uart("\r\n", 2);
    struct io_per io_per_d;
    io_per_d.registers = (volatile void *)ADDR_NASTI_SLAVE_GPIO;

    /// Exception trap
    io_per_set_output(&io_per_d, LEDR, 0, LED_ON);
    io_per_set_output(&io_per_d, LEDR, 1, LED_ON);
    io_per_set_output(&io_per_d, LEDR, 2, LED_ON);
    io_per_set_output(&io_per_d, RWD, 0, 0);
    while (1) {}
}

long interrupt_handler_c(long cause, long epc, long long regs[32]) {
    /**
     * Pending interrupt bit is cleared in the crt.S file by calling:
     *      csrc mip, MIP_MSIP
     * If we woudn't do it the interrupt handler will be called infinitly
     *
     * Rise interrupt from the software maybe done sending a self-IPI:
     *      csrwi mipi, 0
     */
    irqctrl_map *p_irqctrl = (irqctrl_map *)ADDR_NASTI_SLAVE_IRQCTRL;
    IRQ_HANDLER irq_handler = (IRQ_HANDLER)p_irqctrl->isr_table;
    uint32_t pending;
    csr_mcause_type mcause;

    mcause.value = cause;
    p_irqctrl->dbg_cause = cause;
    p_irqctrl->dbg_epc = epc;

    p_irqctrl->irq_lock = 1;
    pending = p_irqctrl->irq_pending;
    p_irqctrl->irq_clear = pending;
    p_irqctrl->irq_lock = 0;

    for (int i = 0; i < CFG_IRQ_TOTAL; i++) {
        if (pending & 0x1) {
            p_irqctrl->irq_cause_idx = i;
            irq_handler(i, NULL);
        }
        pending >>= 1;
    }

    return epc;
}
