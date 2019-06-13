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
	uart_initialize(&uart0, (volatile void *) PLATFORM_UART0_BASE);
	io_per_initialize(&io_per_d, (volatile void *) PLATFORM_IO_BASE);

	uart_set_divisor(&uart0, uart_baud2divisor(115200, PLATFORM_SYSCLK_FREQ));
	uart_tx_string(&uart0, "Hi ...\n\rRun ...\n\r");

	while(1){
		io_per_set_output(&io_per_d, RWD, 0, 0);

		io_per_set_output(&io_per_d, LEDR, 0, io_per_get_input(&io_per_d, SW, 0));
		io_per_set_output(&io_per_d, LEDR, 1, io_per_get_input(&io_per_d, SW, 1));
		io_per_set_output(&io_per_d, LEDR, 2, io_per_get_input(&io_per_d, SW, 2));
		io_per_set_output(&io_per_d, LEDR, 3, io_per_get_input(&io_per_d, SW, 3));
		io_per_set_output(&io_per_d, LEDR, 4, io_per_get_input(&io_per_d, SW, 4));
		io_per_set_output(&io_per_d, LEDR, 5, io_per_get_input(&io_per_d, SW, 5));
		io_per_set_output(&io_per_d, LEDR, 6, io_per_get_input(&io_per_d, SW, 6));
		io_per_set_output(&io_per_d, LEDR, 7, io_per_get_input(&io_per_d, SW, 7));
		io_per_set_output(&io_per_d, LEDR, 8, io_per_get_input(&io_per_d, SW, 8));
		io_per_set_output(&io_per_d, LEDR, 9, io_per_get_input(&io_per_d, SW, 9));

		io_per_set_output(&io_per_d, LEDG, 0, io_per_get_input(&io_per_d, KEY, 0));
		io_per_set_output(&io_per_d, LEDG, 1, io_per_get_input(&io_per_d, KEY, 1));
		io_per_set_output(&io_per_d, LEDG, 2, io_per_get_input(&io_per_d, KEY, 2));
		io_per_set_output(&io_per_d, LEDG, 3, io_per_get_input(&io_per_d, KEY, 3));
		
		//io_per_set_output(&io_per_d, LEDR, 9, 0); // Dummy Write
		io_per_set_output(&io_per_d, RWD,  0, 0);	
	}

	return 0;
}

