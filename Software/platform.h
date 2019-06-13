// The Potato Application Execution Environment (PAEE)
// (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>
// Report bugs and issues on <https://github.com/skordal/potato/issues>

#ifndef PAEE_PLATFORM_H
#define PAEE_PLATFORM_H

// This file contains information about the platform that the PAEE is running
// on. The current version of the file contains information about the Arty-
// based "official" Potato SoC.

// System clock frequency:
#define PLATFORM_SYSCLK_FREQ	80000000U

// Base addresses for peripherals:
#define PLATFORM_UART0_BASE	    0x00001000
#define PLATFORM_TIMER0_BASE	0x00002000
#define PLATFORM_TIMER1_BASE	0x00003000
#define PLATFORM_IO_BASE        0x00004000
#define PLATFORM_ICERROR_BASE	0x10000000
#define PLATFORM_PAEE_ROM_BASE	0xffff8000
#define PLATFORM_PAEE_RAM_BASE	0xffffc000

// Interrupts:
#define PLATFORM_IRQ_TIMER0	0
#define PLATFORM_IRQ_TIMER1	1
#define PLATFORM_IRQ_UART0	2
#define PLATFORM_IRQ_UART1	3
#define PLATFORM_IRQ_BUS_ERROR	4

#endif

