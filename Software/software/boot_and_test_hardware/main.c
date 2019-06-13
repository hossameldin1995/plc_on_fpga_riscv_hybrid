// The Potato Processor Benchmark Applications
// (c) Kristian Klomsten Skordal 2015 <kristian.skordal@wafflemail.net>
// Report bugs and issues on <https://github.com/skordal/potato/issues>

#include <stdint.h>
#include <string.h>
#include "platform.h"
#include "uart.h"
#include "gpio.h"
#include "i_o_peripheral.h"

static struct uart uart0;
static struct io_per io_per_d;

void exception_handler(uint32_t cause, void * epc, void * regbase)
{
	while(uart_tx_fifo_full(&uart0));
	uart_tx(&uart0, 'E');
}

int main(void)
{
	io_per_initialize(&io_per_d, (volatile void *) PLATFORM_IO_BASE);
    uart_initialize(&uart0, (volatile void *) PLATFORM_UART0_BASE);
	uart_set_divisor(&uart0, uart_baud2divisor(115200, PLATFORM_SYSCLK_FREQ));
	
	while(1) {
		io_per_set_output(&io_per_d, RWD, 0, 0);

        // Rung 0 :    Rung_Number_1 
		char var0 = io_per_get_input(&io_per_d, SW, 0);
		var0 ^= io_per_get_input(&io_per_d, SW, 1);
		io_per_set_output(&io_per_d, LEDG, 0, var0);

        char var1 = io_per_get_input(&io_per_d, SW, 2);
		var1 ^= io_per_get_input(&io_per_d, SW, 3);
		io_per_set_output(&io_per_d, LEDG, 1, var1);

	}

}

