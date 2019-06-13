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
	
	volatile char x;
	volatile char y;
	volatile char z;

	while(1) {
		x = 91;    // 01011011 91  5B
		y = 49;    // 00110001 49  31
		z = x ^ y; // 01101010 106 6A
	}

}

