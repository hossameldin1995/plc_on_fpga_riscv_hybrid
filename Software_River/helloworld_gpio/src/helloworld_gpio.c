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
    gptimers_map *p_timer;
    timer_hw TON0;
    pwm_hw PWM0;

    time_measurement_d.registers = (volatile void *)ADDR_NASTI_SLAVE_MEASUREMENT;
    io_per_d.registers = (volatile void *)ADDR_NASTI_SLAVE_GPIO;
    TON0.registers = (volatile void *)ADDR_NASTI_SLAVE_TON0;
    PWM0.registers = (volatile void *)ADDR_NASTI_SLAVE_PWM0;
    p_timer = (gptimers_map *)ADDR_NASTI_SLAVE_GPTIMERS;

    p_timer->timer[0].control = TIMER_CONTROL_DIST_DISIRQ_OV;
    p_timer->timer[0].cur_value = 0;
    p_timer->timer[0].init_value = (uint64_t)(2 * SYS_HZ);

    ss_len = sprintf(ss, "Hellow World - %d!!!!\n\r", 1);
    print_uart(ss, ss_len);

    io_per_set_output(&io_per_d, LEDG, 0, LED_ON);
    io_per_set_output(&io_per_d, LEDG, 7, LED_ON);

    while(1) {
        start_time(&time_measurement_d);
        io_per_set_output(&io_per_d, RWD, 0, 0);

        // PWM PWM0
        pwm_hw_send_frequency_duty_cycle(&PWM0, 1, 50);
        io_per_set_output(&io_per_d, LEDR, 2, pwm_hw_recieve_Q(&PWM0));

		// TON TON0
		int var0 = io_per_get_input(&io_per_d, SW, 0);
		timer_hw_send_preset_time(&TON0, (uint64_t)SYS_HZ);
		timer_hw_send_in(&TON0, var0);
		uint64_t E_T = timer_hw_recieve_elapsed_time(&TON0);
		io_per_set_output(&io_per_d, LEDR, 1, timer_hw_recieve_Q(&TON0));
		io_per_set_output(&io_per_d, LEDR, 0, E_T);

        // CPU Timer
        if ((p_timer->timer[0].control >> 2) == 1) {
            io_per_set_output(&io_per_d, LEDG, 1, LED_OFF);
            p_timer->timer[0].cur_value = 0;
            if (io_per_get_input(&io_per_d, KEY, 0)) {
                p_timer->timer[0].control = TIMER_CONTROL_ENT_DISIRQ_NOOV;
            }
        } else {
            io_per_set_output(&io_per_d, LEDG, 1, LED_ON);
        }

        // CPU PWM
        /*
        uint64_t var1 = (uint64_t)1000000;
		uint64_t Duty_Cycle_0 = (uint64_t) ((var1/100)*Duty_Cycle);
		timer_set_compare(&timer0, var1);
		if (timer0_is_enabled) {
			timer0_count = timer_get_count(&timer0);
			if (timer0_count == var1) {
				timer_reset(&timer0);
				timer_start(&timer0);
			} else if (timer0_count < Duty_Cycle_0) {
				pwm0_output = 1;
			} else {
				pwm0_output = 0;
			}
		} else {
			timer_reset(&timer0);
			timer_start(&timer0);
			timer0_is_enabled = TIMER_ENABLED;
		}
		io_per_set_output(&io_per_d, LEDR, 0, pwm0_output);
        */

        // MUL and DIV
        int var8 =  io_per_get_input(&io_per_d, SW, 1)+
                    io_per_get_input(&io_per_d, SW, 2)+
                    io_per_get_input(&io_per_d, SW, 3)+
                    io_per_get_input(&io_per_d, SW, 4)+
                    io_per_get_input(&io_per_d, SW, 5)+
                    io_per_get_input(&io_per_d, SW, 6)+
                    io_per_get_input(&io_per_d, SW, 7)+
                    io_per_get_input(&io_per_d, SW, 8)+
                    io_per_get_input(&io_per_d, SW, 9);
        int mul = var8 * 3; 
        int div = var8 / 3;
        print_uart("mul=", 4);
        print_uart_hex(mul);
        print_uart(" - div=", 7);
        print_uart_hex(div);
        print_uart("\n\r", 2);

        stop_time(&time_measurement_d);
    }

}

