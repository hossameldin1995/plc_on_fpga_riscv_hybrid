// The Potato Processor Benchmark Applications
// (c) Kristian Klomsten Skordal 2015 <kristian.skordal@wafflemail.net>
// Report bugs and issues on <https://github.com/skordal/potato/issues>

#include <stdint.h>
#include <string.h>

#include "platform.h"
#include "potato.h"
#include "uart.h"
#include "timer.h"
#include "i_o_peripheral.h"

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

    // Configure the UART:
    uart_initialize(&uart0, (volatile void *) PLATFORM_UART0_BASE);
	uart_set_divisor(&uart0, uart_baud2divisor(115200, PLATFORM_SYSCLK_FREQ));

    
    // Configure the IO:
	io_per_initialize(&io_per_d, (volatile void *) PLATFORM_IO_BASE);
    io_per_set_output(&io_per_d, LEDG, 7, 1);
    io_per_set_output(&io_per_d, RWD, 0, 0);

    int LEDG_Value = 0;
    uint64_t count;
    uint64_t comp = PLATFORM_SYSCLK_FREQ;

    // Set up timer0 at 1 Hz:
	timer_initialize(&timer0, (volatile void *) PLATFORM_TIMER0_BASE);
	timer_reset(&timer0);
	//timer_set_compare(&timer0, comp); //PLATFORM_SYSCLK_FREQ
	timer_start(&timer0);

    // Enable interrupts:
	// potato_enable_irq(PLATFORM_IRQ_TIMER0);
	// potato_enable_interrupts();

    while(1){
        io_per_set_output(&io_per_d, LEDG, 0, LEDG_Value);
        io_per_set_output(&io_per_d, RWD, 0, 0);

        timer_set_compare(&timer0, comp); //PLATFORM_SYSCLK_FREQ
        count = timer_get_count(&timer0);
        if (count == comp){
            LEDG_Value = ~LEDG_Value;
            timer_reset(&timer0);
            timer_start(&timer0);
        }
    }

    return 0;

}

