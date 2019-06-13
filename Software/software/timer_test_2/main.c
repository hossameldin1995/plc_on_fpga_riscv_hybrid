#include <stdint.h>
//#include <string.h>
#include "platform.h"
#include "uart.h"
#include "timer.h"
#include "i_o_peripheral.h"

#define TIMER_ENABLED 1
#define TIMER_DISABLED 0

static struct uart uart0;
static struct io_per io_per_d;

static struct timer timer0;

void exception_handler(uint32_t cause, void * epc, void * regbase)
{
	while(uart_tx_fifo_full(&uart0));
	uart_tx(&uart0, 'E');
}

int main(void)
{
	//uart_initialize(&uart0, (volatile void *) PLATFORM_UART0_BASE);
	//uart_set_divisor(&uart0, uart_baud2divisor(115200, PLATFORM_SYSCLK_FREQ));
	io_per_initialize(&io_per_d, (volatile void *) PLATFORM_IO_BASE);

	timer_initialize(&timer0, (volatile void *) PLATFORM_TIMER0_BASE);
	timer_reset(&timer0);
	volatile uint64_t timer0_count;
	int timer0_is_enabled = TIMER_DISABLED;
	int timer0_output = 0;

	//uart_tx_string(&uart0, "Hi ...\n\rRun \"Timer_Test_2/\" ...\n\r");

	volatile uint64_t Elapsed_Time = 0;
	volatile uint64_t Preset_Time = 0;

	while(1){
		io_per_set_output(&io_per_d, RWD, 0, 0);

		// Rung 0 :    R1 
		uint64_t var0 = (uint64_t)9000000000;
		Preset_Time = var0;

		// TON Timer_ON_1
		int var1 = io_per_get_input(&io_per_d, SW, 0);
		if (var1) {
			timer_set_compare(&timer0, Preset_Time);
			if (timer0_is_enabled) {
				timer0_count = timer_get_count(&timer0);
				Elapsed_Time = timer0_count;
				if (timer0_count == Preset_Time) {
					timer0_output = 1;
				}
			} else {
				timer_reset(&timer0);
				timer_start(&timer0);
				timer0_is_enabled = TIMER_ENABLED;
				Elapsed_Time = 0;
			}
		} else {
			timer_reset(&timer0);
			timer0_is_enabled = TIMER_DISABLED;
			Elapsed_Time = 0;
			timer0_output = 0;
		}
		io_per_set_output(&io_per_d, LEDG, 0, timer0_output);

	}

	return 0;
}
