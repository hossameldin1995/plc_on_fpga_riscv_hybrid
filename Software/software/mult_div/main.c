// The Potato Processor Benchmark Applications
// (c) Kristian Klomsten Skordal 2015 <kristian.skordal@wafflemail.net>
// Report bugs and issues on <https://github.com/skordal/potato/issues>

#include <stdint.h>
#include <string.h>
#include "platform.h"
#include "uart.h"

static struct uart uart0;

static void int2string(int n, char * s);

void exception_handler(uint32_t cause, void * epc, void * regbase)
{
	while(uart_tx_fifo_full(&uart0));
	uart_tx(&uart0, 'E');
}

int main(void)
{
	uart_initialize(&uart0, (volatile void *) PLATFORM_UART0_BASE);
	uart_set_divisor(&uart0, uart_baud2divisor(115200, PLATFORM_SYSCLK_FREQ));

	int x, y, z;
	
	while(1){
		uart_tx_string(&uart0, "\n\rx = ");
		while(uart_rx_fifo_empty(&uart0));
		x = uart_rx(&uart0) - '0';
		while(uart_tx_fifo_full(&uart0));
		uart_tx(&uart0, (x + '0'));

		uart_tx_string(&uart0, "\n\ry = ");
		while(uart_rx_fifo_empty(&uart0));
		y = uart_rx(&uart0) - '0';
		while(uart_tx_fifo_full(&uart0));
		uart_tx(&uart0, (y + '0'));

		z = x / y;

		uart_tx_string(&uart0, "\n\rx / y = ");
		while(uart_tx_fifo_full(&uart0));
		uart_tx(&uart0, (z + '0'));
		uart_tx_string(&uart0, "\n\r");
	}
	return 0;
}

static void int2string(int n, char * s)
{
	bool first = true;

	if(n == 0)
	{
		s[0] = '0';
		s[1] =  0;
		return;
	}

	if(n & (1u << 31))
	{
		n = ~n + 1;
		*(s++) = '-';
	}

	for(int i = 1000000000; i > 0; i /= 10)
	{
		if(n / i == 0 && !first)
			*(s++) = '0';
		else if(n / i != 0)
		{
			*(s++) = '0' + n / i;
			n %= i;
			first = false;
		}
	}
	*s = 0;
}

