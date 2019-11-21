/*****************************************************************************
 * @file
 * @author   Sergey Khabarov
 * @brief    Firmware example. 
 ****************************************************************************/

#include <inttypes.h>
#include <string.h>
#include <stdio.h>
#include "axi_maps.h"

extern char _end;

/**
 * @name sbrk
 * @brief Increase program data space.
 * @details Malloc and related functions depend on this.
 */
char *sbrk(int incr) {
    return &_end;
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

void print_uart(const char *buf, int sz) {
    uart_map *uart = (uart_map *)ADDR_NASTI_SLAVE_UART1;
    for (int i = 0; i < sz; i++) {
        while (uart->status & UART_STATUS_TX_FULL) {}
        uart->data = buf[i];
    }
}

void helloWorld_gpio() {
    int T_Count, Frq;

    char ss[256];
    int ss_len;
    int x, y;
    io_per io_per_d;
    time_measurement time_measurement_d;
    timer_hw TON0;
    pwm_hw PWM0;

    time_measurement_d.registers = (volatile void *)ADDR_NASTI_SLAVE_MEASUREMENT;
    io_per_d.registers = (volatile void *)ADDR_NASTI_SLAVE_GPIO;
    TON0.registers = (volatile void *)ADDR_NASTI_SLAVE_TON0;
    PWM0.registers = (volatile void *)ADDR_NASTI_SLAVE_PWM0;

    ss_len = sprintf(ss, "Hellow World - %d!!!!\n\r", 1);
    print_uart(ss, ss_len);

    io_per_set_output(&io_per_d, LEDG, 1, LED_ON);
    io_per_set_output(&io_per_d, LEDG, 2, LED_ON);
    io_per_set_output(&io_per_d, LEDG, 7, LED_ON);

    while(1) {
        start_time(&time_measurement_d);
        io_per_set_output(&io_per_d, RWD, 0, 0);

        pwm_hw_send_frequency(&PWM0, 1);
        pwm_hw_send_duty_cycle(&PWM0, 90);
        io_per_set_output(&io_per_d, LEDR, 2, pwm_hw_recieve_Q(&PWM0));
        
        x = io_per_get_input(&io_per_d, SW, 0)*
            io_per_get_input(&io_per_d, SW, 1);
        io_per_set_output(&io_per_d, LEDR, 0, x);

        y = (
                io_per_get_input(&io_per_d, SW, 0)+
                io_per_get_input(&io_per_d, SW, 1)
            )/
            (
                io_per_get_input(&io_per_d, SW, 2)+
                io_per_get_input(&io_per_d, SW, 3)
            );
        io_per_set_output(&io_per_d, LEDR, 7, y);

        // Rung 1 :    R1 

		// TON TON_1
		int var0 = io_per_get_input(&io_per_d, SW, 0);
		timer_hw_send_preset_time(&TON0, (uint64_t)SYS_HZ);
		timer_hw_send_in(&TON0, var0);
		uint64_t E_T = timer_hw_recieve_elapsed_time(&TON0);
		io_per_set_output(&io_per_d, LEDG, 0, timer_hw_recieve_Q(&TON0));


		// Rung 2 :    R2 
		uint64_t var1 = E_T;
		io_per_set_output(&io_per_d, LEDR, 7, var1);
        stop_time(&time_measurement_d);

        stop_time(&time_measurement_d);
    }

}

