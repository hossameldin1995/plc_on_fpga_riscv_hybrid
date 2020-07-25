/*
 *  Copyright 2019 Sergey Khabarov, sergeykhbr@gmail.com
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
#include "test_arithmetic.h"

void allocate_exception_table(void);
void test_timer(void);
void start_application(void);
uint64_t inline double2hex(double x);
uint32_t inline float2hex(float x);

uint32_t main() {
    io_per io_per_d;
    
    uart_map *uart = (uart_map *)ADDR_BUS0_XSLV_UART1;
    irqctrl_map *p_irq = (irqctrl_map *)ADDR_BUS0_XSLV_IRQCTRL;
    io_per_d.registers = (volatile void *)ADDR_BUS0_XSLV_GPIO;

    if (fw_get_cpuid() != 0) {
        while (1) {}
    }

    // mask all interrupts in interrupt controller to avoid
    // unpredictable behaviour after elf-file reloading via debug port.
    p_irq->irq_mask = 0xFFFFFFFF;
    p_irq->isr_table = 0;

    p_irq->irq_lock = 1;
    fw_malloc_init();
    
    allocate_exception_table();

    uart_isr_init();   // enable printf_uart function and Tx irq=1
    p_irq->irq_lock = 0;
 
    /* LEDG = 1*/
    io_per_set_output(&io_per_d, LEDG, 0, LED_ON);
    io_per_set_output(&io_per_d, RWD, 0, 0);
    printf_uart("*************************************************\n\r");
    printf_uart("******************** Booting ********************\n\r");
    printf_uart("*************************************************\n\r");

    printf_uart("  HARTID . . . . . %d\r\n", fw_get_cpuid());

    /* LEDG = 2*/
    io_per_set_output(&io_per_d, LEDG, 0, LED_OFF);
    io_per_set_output(&io_per_d, LEDG, 1, LED_ON);
    io_per_set_output(&io_per_d, RWD, 0, 0);

    /* LEDG = 4*/
    io_per_set_output(&io_per_d, LEDG, 1, LED_OFF);
    io_per_set_output(&io_per_d, LEDG, 2, LED_ON);
    io_per_set_output(&io_per_d, RWD, 0, 0);
    test_timer();      // Enabling timer[0] with 1 sec interrupts

    /* LEDG = 8*/
    io_per_set_output(&io_per_d, LEDG, 2, LED_OFF);
    io_per_set_output(&io_per_d, LEDG, 3, LED_ON);
    io_per_set_output(&io_per_d, RWD, 0, 0);

    printf_uart("  Done testing\n\r");


    printf_uart("*************************************************\n\r");
    printf_uart("*************** Start Appilcation ***************\n\r");
    printf_uart("*************************************************\n\r");


    /* LEDG = 0*/
    io_per_set_output(&io_per_d, LEDG, 3, LED_OFF);
    io_per_set_output(&io_per_d, RWD, 0, 0);

    start_application(); // no return

    return 0;
}

uint64_t inline double2hex(double x) {
    uint64_t *p;
    p = (void*)&x;
    return *p;
}

uint32_t inline float2hex(float x) {
    uint32_t *p;
    p = (void*)&x;
    return *p;
}

uint32_t check_arithmetic(uint32_t gpio_value, 
                          uint32_t int_mul,  uint32_t int_div, 
                          double double_mul, double double_div) {
    uint32_t error_arithmetic = 0;


    if ((int_mul - test_int_mul[gpio_value]) > 0.0000000000001) {
        error_arithmetic++;
        printf_uart("%d int_mul\n\r", gpio_value);
    }
    if ((int_div - test_int_div[gpio_value]) > 0.0000000000001) {
        error_arithmetic++;
        printf_uart("%d int_div\n\r", gpio_value);
    }
    if ((double_mul - test_double_mul[gpio_value]) > 0.0000000000001) {
        error_arithmetic++;
        printf_uart("%d double_mul\n\r", gpio_value);
        print_uart_hex(double2hex(double_mul));
        print_uart("\n\r", 2);
    }
    if ((double_div - test_double_div[gpio_value]) > 0.0000000000001) {
        error_arithmetic++;
        printf_uart("%d double_div\n\r", gpio_value);
        print_uart_hex(double2hex(double_div));
        print_uart("\n\r", 2);
    }
    

    return error_arithmetic;
}

// ***************************************
// ******* LED CODE **********************
// ***************************************
// LEDG 0      >> Application init done
//      1      >> Application init done
//      2      >>
//      3      >>
//      4      >>
//      5      >>
//      6      >>
//      7      >>
//
// LEDR 0      >> TON0 E_T
//      1      >> TON0 Q
//      2      >> PWM0 Q
//      3      >> Internal Timer 1
//      4      >> 
//      5      >> 
//      6      >> 
//      7      >> 
//      8      >> Error PID0
//      9      >> Error Arithmetic

void start_application(void) {
    uint64_t Elapsed_Time = 0;
    uint64_t Preset_Time = 0;
    uint64_t TS = (SYS_HZ/1000) * 100;  //100ms
    //uint64_t TS = 40000;
    uint32_t int_mul;
    uint32_t int_div;
    uint32_t error_arithmetic = 0;
    uint32_t error_pid = 0;
    uint32_t is_first_time = 1;
    uint32_t gpio_value;
	uint32_t timer1_is_enabled;
	uint32_t timer1_output;
    uint32_t PV0_32;
    uint32_t PV1_32;
    uint32_t PV2_32;
    uint32_t PV3_32;
    uint32_t SP_32;
    uint32_t b0_32;
    uint32_t b1_32;
    uint32_t b2_32;
    uint32_t PID_counter = 0;
    uint32_t XOUT_1;
    uint32_t XOUT_2;
    uint32_t XOUT_3;
    uint32_t XOUT_4;
    float PV0 = 20.2;
    float PV1 = 22.3;
    float PV2 = 22.4;
    float PV3 = 32.5;
    float SP = 60.7;
    float b0 = 33.2;
    float b1 = 27.8;
    float b2 = 55.72;
    double double_mul;
    double double_div;

    time_measurement time_measurement_d;
    gptimers_map *p_timer;
    io_per io_per_d;
    timer_hw TON0;
    pwm_hw PWM0;
    pid_hw PID0;

    time_measurement_d.registers = (volatile void *)ADDR_BUS0_XSLV_MEASUREMENT;
    io_per_d.registers = (volatile void *)ADDR_BUS0_XSLV_GPIO;
    TON0.registers = (volatile void *)ADDR_BUS0_XSLV_TON0;
    PWM0.registers = (volatile void *)ADDR_BUS0_XSLV_PWM0;
    PID0.registers = (volatile void *)ADDR_BUS0_XSLV_PID0;
    p_timer = (gptimers_map *)ADDR_BUS0_XSLV_GPTIMERS;

    /*p_timer->timer[1].control = TIMER_CONTROL_DIST_DISIRQ_NOOV;
    p_timer->timer[1].cur_value = 0;
    p_timer->timer[1].init_value = (uint64_t)(2 * SYS_HZ);*/
    timer1_is_enabled = TIMER_DISABLED;
	timer1_output = 0;

    io_per_set_output(&io_per_d, LEDG, 0, LED_ON);
    io_per_set_output(&io_per_d, LEDG, 1, LED_ON);
    io_per_set_output(&io_per_d, RWD, 0, 0);

    while(1) {
        start_time(&time_measurement_d);
        io_per_set_output(&io_per_d, RWD, 0, 0);

        // ***************************************
        // ******* TON0 **************************
        // ***************************************
		uint32_t var0 = io_per_get_input(&io_per_d, SW, 0);
		timer_hw_send_preset_time(&TON0, (uint64_t)SYS_HZ);
		timer_hw_send_in(&TON0, var0);
		uint64_t E_T = timer_hw_recieve_elapsed_time(&TON0);
		io_per_set_output(&io_per_d, LEDR, 1, timer_hw_recieve_Q(&TON0));
		io_per_set_output(&io_per_d, LEDR, 0, E_T);

        // ***************************************
        // ******* PWM0 **************************
        // ***************************************
        pwm_hw_send_frequency_duty_cycle(&PWM0, 1, 50);
        io_per_set_output(&io_per_d, LEDR, 2, pwm_hw_recieve_Q(&PWM0));

        // ***************************************
        // ******* PID0 **************************
        // ***************************************
        PV0_32 = float2hex(PV0);
        PV1_32 = float2hex(PV1);
        PV2_32 = float2hex(PV2);
        PV3_32 = float2hex(PV3);
        SP_32 = float2hex(SP);
        b0_32 = float2hex(b0);
        b1_32 = float2hex(b1);
        b2_32 = float2hex(b2);

        if (pid_hw_recieve_XOUT_R(&PID0) && error_pid == 0) {
            if (PID_counter == 0) {
                XOUT_1 = pid_hw_recieve_XOUT(&PID0);
                if (XOUT_1 != 0x44A81333) { // 1344.6
                    print_uart("Error XOUT_1 =", 14);
                    print_uart_hex((uint64_t) XOUT_1);
                    print_uart("\n\r", 2);
                    error_pid++;
                }
            }
            else if (PID_counter == 1) {
                XOUT_2 = pid_hw_recieve_XOUT(&PID0);
                if (XOUT_2 != 0x456A1616) { // 3745.38
                    print_uart("Error XOUT_2 =", 14);
                    print_uart_hex((uint64_t) XOUT_2);
                    print_uart("\n\r", 2);
                    error_pid++;
                }
            }
            else if (PID_counter == 2) {
                XOUT_3 = pid_hw_recieve_XOUT(&PID0);
                if (XOUT_3 != 0x4602547C) { // 8341.12
                    print_uart("Error XOUT_3 =", 14);
                    print_uart_hex((uint64_t) XOUT_3);
                    print_uart("\n\r", 2);
                    error_pid++;
                }
            }
            else if (PID_counter == 3) {
                XOUT_4 = pid_hw_recieve_XOUT(&PID0);
                if (XOUT_4 != 0x46430700) { // 12481.8
                    print_uart("Error XOUT_4 =", 14);
                    print_uart_hex((uint64_t) XOUT_4);
                    print_uart("\n\r", 2);
                    error_pid++;
                }
            }

            if (PID_counter != 4) PID_counter++;
        }

        if (PID_counter == 0) pid_hw_send_pv_sp(&PID0, PV0_32, SP_32);
        else if (PID_counter == 1) pid_hw_send_pv_sp(&PID0, PV1_32, SP_32);
        else if (PID_counter == 2) pid_hw_send_pv_sp(&PID0, PV2_32, SP_32);
        else if (PID_counter == 3) pid_hw_send_pv_sp(&PID0, PV3_32, SP_32);
        pid_hw_send_b0_b1(&PID0, b0_32, b1_32);
        pid_hw_send_b2(&PID0, b2_32);
        if (PID_counter != 4) pid_hw_send_ts(&PID0, TS);
        else pid_hw_send_ts(&PID0, 0);


        
        // ***************************************
        // ******* Internal Timer 1 **************
        // ***************************************
        uint64_t var1 = (uint64_t)SYS_HZ;
		Preset_Time = var1;
        int var2 = io_per_get_input(&io_per_d, SW, 0);
		if (var2) {
			if (timer1_is_enabled) {
				if ((p_timer->timer[1].control & 4) == 4) {
					Elapsed_Time = p_timer->timer[1].init_value;
					timer1_output = 1;
				} else {
					Elapsed_Time = p_timer->timer[1].init_value - p_timer->timer[1].cur_value;
				}
			} else {
				p_timer->timer[1].init_value = Preset_Time;
				p_timer->timer[1].cur_value = 0;
				p_timer->timer[1].control = TIMER_CONTROL_ENT_DISIRQ_NOOV;
				timer1_is_enabled = TIMER_ENABLED;
				Elapsed_Time = 0;
			}
		} else {
			p_timer->timer[1].control = TIMER_CONTROL_DIST_DISIRQ_NOOV;
			timer1_is_enabled = TIMER_DISABLED;
			Elapsed_Time = 0;
			timer1_output = 0;
		}
		io_per_set_output(&io_per_d, LEDR, 3, timer1_output);

        // ***************************************
        // ******* Print gpio_value **************
        // ***************************************
        if (io_per_get_input(&io_per_d, KEY, 0) && is_first_time) {
            printf_uart("gpio_value: %02d\n\r", gpio_value);
            is_first_time = 0;
        } else if (io_per_get_input(&io_per_d, KEY, 0) == 0){
            is_first_time = 1;
        }

        // ***************************************
        // ******* Arithmetic Test ***************
        // ***************************************
        gpio_value =  io_per_get_input(&io_per_d, SW, 0)+
                    io_per_get_input(&io_per_d, SW, 1)+
                    io_per_get_input(&io_per_d, SW, 2)+
                    io_per_get_input(&io_per_d, SW, 3)+
                    io_per_get_input(&io_per_d, SW, 4)+
                    io_per_get_input(&io_per_d, SW, 5)+
                    io_per_get_input(&io_per_d, SW, 6)+
                    io_per_get_input(&io_per_d, SW, 7)+
                    io_per_get_input(&io_per_d, SW, 8)+
                    io_per_get_input(&io_per_d, SW, 9);
                    
        int_mul = gpio_value * 3; 
        int_div = gpio_value / 3;
        double_mul = (double)gpio_value * 3.2;
        double_div = (double)gpio_value / 3.2;

        //PV = PV + double_div;

        error_arithmetic += check_arithmetic(gpio_value, int_mul, int_div, double_mul, double_div);

        if (error_arithmetic) {
            io_per_set_output(&io_per_d, LEDR, 9, LED_ON);
        }
        
        if (error_pid) {
            io_per_set_output(&io_per_d, LEDR, 8, LED_ON);
        }

        stop_time(&time_measurement_d);
    }
}