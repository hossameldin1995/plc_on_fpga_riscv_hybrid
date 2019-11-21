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

static const int FW_IMAGE_SIZE_BYTES = 1 << 16; // 64 KB

int fw_get_cpuid() {
    int ret;
    asm("csrr %0, mhartid" : "=r" (ret));
    return ret;
}

void print_uart(const char *buf, int sz) {
    uart_map *uart = (uart_map *)ADDR_NASTI_SLAVE_UART1;
    for (int i = 0; i < sz; i++) {
        while (uart->status & UART_STATUS_TX_FULL) {}
        uart->data = buf[i];
    }
}

void print_uart_hex(long val) {
    unsigned char t, s;
    uart_map *uart = (uart_map *)ADDR_NASTI_SLAVE_UART1;
    for (int i = 0; i < 16; i++) {
        while (uart->status & UART_STATUS_TX_FULL) {}
        
        t = (unsigned char)((val >> ((15 - i) * 4)) & 0xf);
        if (t < 10) {
            s = t + '0';
        } else {
            s = (t - 10) + 'a';
        }
        uart->data = s;
    }
}

void copy_image() {
    uint64_t *fwrom = (uint64_t *)ADDR_NASTI_SLAVE_FWIMAGE;
    uint64_t *sram = (uint64_t *)ADDR_NASTI_SLAVE_SRAM;
    
    memcpy(sram, fwrom, FW_IMAGE_SIZE_BYTES);
}

/** This function will be used during video recording to show
 how tochange npc register value on core[1] while core[0] is running
 Zephyr OS
*/
void timestamp_output() {
    gptimers_map *tmr = (gptimers_map *)ADDR_NASTI_SLAVE_GPTIMERS;
    uint64_t start = tmr->highcnt;
    while (1) {
        if (tmr->highcnt < start || (start + SYS_HZ) < tmr->highcnt) {
            start = tmr->highcnt;
            print_uart("HIGHCNT: ", 9);
            print_uart_hex(start);
            print_uart("\r\n", 2);
        }
    }
}

void _init() {
    uart_map *uart = (uart_map *)ADDR_NASTI_SLAVE_UART1;
    irqctrl_map *p_irq = (irqctrl_map *)ADDR_NASTI_SLAVE_IRQCTRL;
    io_per io_per_d;
    
    io_per_d.registers = (volatile void *)ADDR_NASTI_SLAVE_GPIO;

    if (fw_get_cpuid() != 0) {
        // TODO: waiting event or something
        while(1) {}
    }

    // mask all interrupts in interrupt controller to avoid
    // unpredictable behaviour after elf-file reloading via debug port.
    p_irq->irq_mask = 0xFFFFFFFF;

    // Half period of the uart = Fbus / 115200 / 2 = 70 MHz / 115200 / 2:
    uart->scaler = SYS_HZ / 115200 / 2;  // 40 MHz
    
    io_per_set_output(&io_per_d, LEDG, 0, LED_ON); // LED = 1
    io_per_set_output(&io_per_d, RWD, 0, 0);
    print_uart("Booting . . .\n\r", 15);
    
    io_per_set_output(&io_per_d, LEDG, 1, LED_ON); // LEDG = 2
    io_per_set_output(&io_per_d, LEDG, 0, LED_OFF);
    io_per_set_output(&io_per_d, RWD, 0, 0);

    copy_image();
    
    io_per_set_output(&io_per_d, LEDG, 2, LED_ON); // LEDG = 4
    io_per_set_output(&io_per_d, LEDG, 1, LED_OFF);
    io_per_set_output(&io_per_d, RWD, 0, 0);
    print_uart("Application image copied to RAM\r\n", 33);

    io_per_set_output(&io_per_d, LEDR, 9, LED_ON); // LEDR = 0x200
    io_per_set_output(&io_per_d, LEDG, 2, LED_OFF);
    io_per_set_output(&io_per_d, RWD, 0, 0);
    print_uart("Jump to Application in RAM\r\n", 28);
}

/** Not used actually */
int main() {
    while (1) {}

    return 0;
}
