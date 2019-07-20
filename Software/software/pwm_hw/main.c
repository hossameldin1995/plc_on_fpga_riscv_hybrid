#include <stdint.h>
#include "platform.h"
//#include "uart.h"
#include "pwm_hw.h"
#include "time_measurement.h"
#include "i_o_peripheral.h"

//static struct uart uart0;
static struct time_measurement time_measurement_d;
static struct io_per io_per_d;
static struct pwm_hw PWM_1;

void exception_handler(uint32_t cause, void * epc, void * regbase)
{
	//while(uart_tx_fifo_full(&uart0));
	//uart_tx(&uart0, 'E');
}

int main(void)
{
	//uart_initialize(&uart0, (volatile void *) PLATFORM_UART0_BASE);
	//uart_set_divisor(&uart0, uart_baud2divisor(115200, PLATFORM_SYSCLK_FREQ));
	time_measurement_per_initialize(&time_measurement_d, (volatile void *) PLATFORM_TIME_MEASUREMENT);
	set_micro(&time_measurement_d);
	io_per_initialize(&io_per_d, (volatile void *) PLATFORM_IO_BASE);
	pwm_hw_initialize(&PWM_1, (volatile void *) PLATFORM_PWM_PWM_1);

	//uart_tx_string(&uart0, "Hi ...\n\rRun \"Timer_Test_4_1/\" ...\n\r");

	while(1){
		start_time(&time_measurement_d);
		io_per_set_output(&io_per_d, RWD, 0, 0);

		// Rung 1 :    R1 

		// PWM PWM_1
		pwm_hw_send_duty_cycle(&PWM_1, (uint32_t)50);
		pwm_hw_send_frequency(&PWM_1, (uint32_t)1);
		io_per_set_output(&io_per_d, LEDG, 0, pwm_hw_recieve_Q(&PWM_1));

        stop_time(&time_measurement_d);
    }

	return 0;
}
