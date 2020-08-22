/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.RV32.Write_Generated_Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rv_fpga_plc_ide.helper.Data;
import rv_fpga_plc_ide.helper.GeneralFunctions;
import rv_fpga_plc_ide.main.RV_FPGA_PLC_IDE;

/**
 *
 * @author hossameldin
 */
public class Write_Software_Files {
    
    public void write_library_files(String Project_Folder) {
        write_header_files(Project_Folder);
        write_start_S_file(Project_Folder);
        write_load_file(Project_Folder);
    }
    
    private void write_header_files(String Folder) {
        write_i_o_peripheral_file(Folder);
        write_platform_file(Folder);
        write_uart_file(Folder);
        write_time_measurement_file(Folder);
        write_timer_file(Folder);
        write_timer_hw_file(Folder);
        write_pwm_hw_file(Folder);
    }
    
    private void write_i_o_peripheral_file(String Folder) {
        FileOutputStream i_o_peripheral_file = null;
        String data =   "// The Potato SoC Library modified from hossameldin\n" +
                        "// (c) Hossameldin Bayoummy Eassa 2019 <hossameassa@gmail.com>\n" +
                        "\n" +
                        "#ifndef PAEE_I_O_PERIPHERAL_H\n" +
                        "#define PAEE_I_O_PERIPHERAL_H\n" +
                        "\n" +
                        "#include <stdbool.h>\n" +
                        "#include <stdint.h>\n" +
                        "\n" +
                        "#define GPIO_IN_BASE 	0x000    // 0x00\n" +
                        "#define SW				0x048    // 0x12\n" +
                        "#define KEY				0x070    // 0x1c\n" +
                        "\n" +
                        "#define GPIO_OUT		0x100    // 0x40\n" +
                        "#define LEDR			0x148    // 0x52\n" +
                        "#define LEDG			0x170    // 0x5c\n" +
                        "\n" +
                        "#define RWD				0x1fc    // 0x7f\n" +
                        "\n" +
                        "struct io_per\n" +
                        "{\n" +
                        "	volatile uint32_t * registers;\n" +
                        "};\n" +
                        "\n" +
                        "/**\n" +
                        " * Initializes a io_per instance.\n" +
                        " * @param module Pointer to a io_per instance structure.\n" +
                        " * @param base   Pointer to the base address of the io_per hardware instance.\n" +
                        " */\n" +
                        "static inline void io_per_initialize(struct io_per * module, volatile void * base)\n" +
                        "{\n" +
                        "	module->registers = base;\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint32_t io_per_get_input(struct io_per * module, volatile uint32_t submodule, uint32_t index)\n" +
                        "{\n" +
                        "	return module->registers[(submodule >> 2) + index];\n" +
                        "}\n" +
                        "\n" +
                        "static inline void io_per_set_output(struct io_per * module, volatile uint32_t submodule, uint32_t index, volatile uint32_t output)\n" +
                        "{\n" +
                        "	module->registers[(submodule >> 2) + index] = output;\n" +
                        "}\n" +
                        "\n" +
                        "#endif\n";
        try {
            new File(Folder+"/i_o_peripheral.h").delete();
            i_o_peripheral_file = new FileOutputStream(Folder+"/i_o_peripheral.h");
            i_o_peripheral_file.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                i_o_peripheral_file.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void write_platform_file(String Folder) {
        FileOutputStream i_o_peripheral_file = null;
        String data =   "// The Potato Application Execution Environment (PAEE)\n" +
                        "// (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>\n" +
                        "// Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "#ifndef PAEE_PLATFORM_H\n" +
                        "#define PAEE_PLATFORM_H\n" +
                        "\n" +
                        "// This file contains information about the platform that the PAEE is running\n" +
                        "// on. The current version of the file contains information about the Arty-\n" +
                        "// based \"official\" Potato SoC.\n" +
                        "\n" +
                        "// System clock frequency:\n" +
                        "#define PLATFORM_SYSCLK_FREQ	"+Data.CPU_RV32_Freq+"U\n" +
                        "\n" +
                        "// Base addresses for peripherals:\n" +
                        "#define PLATFORM_UART0_BASE			0x00001000\n" +
                        "#define PLATFORM_TIMER0_BASE		0x00002000\n" +
                        "#define PLATFORM_TIMER1_BASE		0x00003000\n" +
                        "#define PLATFORM_IO_BASE			0x00004000\n" +
                        "#define PLATFORM_TIME_MEASUREMENT	0x00005000\n";
                        for (int i = 0; i < Data.Number_Of_Timers_In_Program; i++) {
                            data += "#define PLATFORM_TON_"+Data.Name_of_Timers[i]+"			0x000"+new GeneralFunctions().dec2hex_str(6 + i, 2)+"000\n";
                        }
                        for (int i = 0; i < Data.Number_Of_PWMs_In_Program; i++) {
                            data += "#define PLATFORM_PWM_"+Data.Name_of_PWMs[i]+"			0x000"+new GeneralFunctions().dec2hex_str(Data.Number_Of_Timers_In_Program + 6 + i, 2)+"000\n";
                        }
                data += "#define PLATFORM_ICERROR_BASE		0x10000000\n" +
                        "#define PLATFORM_PAEE_ROM_BASE		0xffff8000\n" +
                        "#define PLATFORM_PAEE_RAM_BASE		0xffffc000\n" +
                        "\n" +
                        "// Interrupts:\n" +
                        "#define PLATFORM_IRQ_TIMER0	0\n" +
                        "#define PLATFORM_IRQ_TIMER1	1\n" +
                        "#define PLATFORM_IRQ_UART0	2\n" +
                        "#define PLATFORM_IRQ_UART1	3\n" +
                        "#define PLATFORM_IRQ_BUS_ERROR	4\n" +
                        "\n" +
                        "#endif\n";
        try {
            new File(Folder+"/platform.h").delete();
            i_o_peripheral_file = new FileOutputStream(Folder+"/platform.h");
            i_o_peripheral_file.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                i_o_peripheral_file.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void write_uart_file(String Folder) {
        FileOutputStream i_o_peripheral_file = null;
        String data =   "// The Potato SoC Library\n" +
                        "// (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>\n" +
                        "// Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "#ifndef LIBSOC_UART_H\n" +
                        "#define LIBSOC_UART_H\n" +
                        "\n" +
                        "#include <stdbool.h>\n" +
                        "#include <stdint.h>\n" +
                        "\n" +
                        "#define UART_REG_TRANSMIT	0x00\n" +
                        "#define UART_REG_RECEIVE	0x04\n" +
                        "#define UART_REG_STATUS		0x08\n" +
                        "#define UART_REG_DIVISOR	0x0c\n" +
                        "#define UART_REG_INTERRUPT	0x10\n" +
                        "\n" +
                        "// Status register bit names:\n" +
                        "#define UART_STATUS_TX_FULL	3\n" +
                        "#define UART_STATUS_RX_FULL	2\n" +
                        "#define UART_STATUS_TX_EMPTY	1\n" +
                        "#define UART_STATUS_RX_EMPTY	0\n" +
                        "\n" +
                        "// Interrupt enable register bit names:\n" +
                        "#define UART_REG_INTERRUPT_TX_READY	1\n" +
                        "#define UART_REG_INTERRUPT_RECV		0\n" +
                        "\n" +
                        "struct uart\n" +
                        "{\n" +
                        "	volatile uint32_t * registers;\n" +
                        "};\n" +
                        "\n" +
                        "/**\n" +
                        " * Initializes a UART instance.\n" +
                        " * @param module       Pointer to a UART instance structure.\n" +
                        " * @param base_address Base address of the UART hardware instance.\n" +
                        " */\n" +
                        "static inline void uart_initialize(struct uart * module, volatile void * base_address)\n" +
                        "{\n" +
                        "	module->registers = base_address;\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Sets the UART divisor.\n" +
                        " * @param module  Instance object.\n" +
                        " * @param divisor Value of the divisor register. A baudrate can be converted into\n" +
                        " *                a divisor value using the @c uart_baud2divisor function.\n" +
                        " */\n" +
                        "static inline void uart_set_divisor(struct uart * module, uint32_t divisor)\n" +
                        "{\n" +
                        "	module->registers[UART_REG_DIVISOR >> 2] = divisor;\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Enables or disables UART IRQs.\n" +
                        " * @param module   Instance object.\n" +
                        " * @param tx_ready Specifies whether to enable or disable the `TX ready` interrupt.\n" +
                        " * @param recv     Specifies whether to enable or disable the `Data received` interrupt.\n" +
                        " */\n" +
                        "static inline void uart_enable_interrupt(struct uart * module, bool tx_ready, bool recv)\n" +
                        "{\n" +
                        "	module->registers[UART_REG_INTERRUPT >> 2] = 0\n" +
                        "		| (tx_ready << UART_REG_INTERRUPT_TX_READY)\n" +
                        "		| (recv << UART_REG_INTERRUPT_RECV);\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Checks if the UART transmit buffer is ready to accept more data.\n" +
                        " * @param module Instance object.\n" +
                        " * @return `true` if the UART transmit FIFO has free space, `false` otherwise.\n" +
                        " */\n" +
                        "static inline bool uart_tx_ready(struct uart * module)\n" +
                        "{\n" +
                        "	return !(module->registers[UART_REG_STATUS >> 2]  & (1 << UART_STATUS_TX_FULL));\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Checks if the UART transmit buffer is empty.\n" +
                        " * @param module Instance object.\n" +
                        " * @return `true` if the UART transmit FIFO is empty, `false` otherwise.\n" +
                        " */\n" +
                        "static inline bool uart_tx_fifo_empty(struct uart * module)\n" +
                        "{\n" +
                        "	return module->registers[UART_REG_STATUS >> 2] & (1 << UART_STATUS_TX_EMPTY);\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Checks if the UART transmit buffer is full.\n" +
                        " * @param module Instance object.\n" +
                        " * @return `true` if the UART transmit FIFO is full, `false` otherwise.\n" +
                        " */\n" +
                        "static inline bool uart_tx_fifo_full(struct uart * module)\n" +
                        "{\n" +
                        "	return module->registers[UART_REG_STATUS >> 2] & (1 << UART_STATUS_TX_FULL);\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Transmits a byte over the UART.\n" +
                        " * This function does not check if the UART buffer is full; use the @ref uart_tx_ready()\n" +
                        " * function to check if the UART can accept more data.\n" +
                        " * @param module Instance object.\n" +
                        " * @param byte   Byte to print to the UART.\n" +
                        " */\n" +
                        "static inline void uart_tx(struct uart * module, uint8_t byte)\n" +
                        "{\n" +
                        "	module->registers[UART_REG_TRANSMIT >> 2] = byte;\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Transmits an array of bytes over the UART.\n" +
                        " * This function blocks until the entire array has been queued for transfer.\n" +
                        " * @param module Instance object.\n" +
                        " * @param array  Pointer to the aray to send on the UART.\n" +
                        " * @param length Length of the array.\n" +
                        " * @see uart_tx_string()\n" +
                        " */\n" +
                        "static inline void uart_tx_array(struct uart * module, const uint8_t * array, uint32_t length)\n" +
                        "{\n" +
                        "	for(uint32_t i = 0; i < length; ++i)\n" +
                        "	{\n" +
                        "		while(uart_tx_fifo_full(module));\n" +
                        "		uart_tx(module, array[i]);\n" +
                        "	}\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Transmits a character string over the UART.\n" +
                        " * This function blocks until the entire array has been queued for transfer.\n" +
                        " * @param module Instance object.\n" +
                        " * @param string Pointer to the string to send on the UART. The string must be\n" +
                        " *               NULL-terminated.\n" +
                        " * @see uart_tx_array()\n" +
                        " */\n" +
                        "static inline void uart_tx_string(struct uart * module, const char * string)\n" +
                        "{\n" +
                        "	for(uint32_t i = 0; string[i] != 0; ++i)\n" +
                        "	{\n" +
                        "		while(uart_tx_fifo_full(module));\n" +
                        "		uart_tx(module, string[i]);\n" +
                        "	}\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Reads a byte from the UART.\n" +
                        " * This function does not check if a byte is available in the UART buffer; use the\n" +
                        " * @ref uart_rx_ready() function to check if the UART has received anything.\n" +
                        " * @param module Instance object.\n" +
                        " * @return Byte retrieved from the UART.\n" +
                        " */\n" +
                        "static inline uint8_t uart_rx(struct uart * module)\n" +
                        "{\n" +
                        "	return module->registers[UART_REG_RECEIVE >> 2];\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Checks if the UART receive buffer is empty.\n" +
                        " * @param module Instance object.\n" +
                        " * @return `true` if the UART receive FIFO is empty, `false` otherwise.\n" +
                        " */\n" +
                        "static inline bool uart_rx_fifo_empty(struct uart * module)\n" +
                        "{\n" +
                        "	return module->registers[UART_REG_STATUS >> 2] & (1 << UART_STATUS_RX_EMPTY);\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Utility function for calculating the UART baudrate divisor.\n" +
                        " * @param baudrate   The desired baudrate.\n" +
                        " * @param system_clk Frequency of the system clock in Hz.\n" +
                        " * @return The value needed for the UART divisor register to obtain the requested baudrate.\n" +
                        " */\n" +
                        "static inline uint32_t uart_baud2divisor(uint32_t baudrate, uint32_t system_clk)\n" +
                        "{\n" +
                        "	return (system_clk / (baudrate * 16)) - 1;\n" +
                        "}\n" +
                        "\n" +
                        "#endif\n";
        try {
            new File(Folder+"/uart.h").delete();
            i_o_peripheral_file = new FileOutputStream(Folder+"/uart.h");
            i_o_peripheral_file.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                i_o_peripheral_file.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void write_start_S_file(String Folder) {
        FileOutputStream i_o_peripheral_file = null;
        String data =   "# The Potato Application Execution Environment (PAEE)\n" +
                        "# (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>\n" +
                        "# Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        ".section .init\n" +
                        "\n" +
                        ".global _start\n" +
                        "_start:\n" +
                        "\n" +
                        "// Sets the exception handler address:\n" +
                        ".hidden init_mtvec\n" +
                        "init_mtvec:\n" +
                        "	la x1, _machine_exception_handler\n" +
                        "	csrw mtvec, x1\n" +
                        "\n" +
                        "// Copies the .data from ROM to RAM - this is only used by the bootloader, which runs from ROM:\n" +
                        "#ifdef COPY_DATA_TO_RAM\n" +
                        ".hidden copy_data\n" +
                        "copy_data:\n" +
                        "	la x1, __text_end	// Copy source address\n" +
                        "	la x2, __data_begin	// Copy destination address\n" +
                        "	la x3, __data_end	// Copy destination end address\n" +
                        "\n" +
                        "	beq x2, x3, 2f		// Skip if there is no data to copy\n" +
                        "\n" +
                        "1:\n" +
                        "	lb x4, (x1)\n" +
                        "	sb x4, (x2)\n" +
                        "	addi x1, x1, 1\n" +
                        "	addi x2, x2, 1\n" +
                        "\n" +
                        "	bne x2, x3, 1b		// Repeat as long as there is more data to copy\n" +
                        "2:\n" +
                        "#endif\n" +
                        "\n" +
                        "// Clears the .bss (zero initialized data) section:\n" +
                        ".hidden clear_bss\n" +
                        "clear_bss:\n" +
                        "	la x1, __bss_begin\n" +
                        "	la x2, __bss_end\n" +
                        "	beq x1, x2, 2f		// Skip if there is no .bss section\n" +
                        "\n" +
                        "1:\n" +
                        "	sw x0, (x1)\n" +
                        "	addi x1, x1, 4\n" +
                        "	bne x1, x2, 1b\n" +
                        "2:\n" +
                        "\n" +
                        "// Sets up the stack pointer:\n" +
                        ".hidden init_stack\n" +
                        "init_stack:\n" +
                        "	la sp, __stack_top\n" +
                        "\n" +
                        ".hidden call_main\n" +
                        "call_main:\n" +
                        "	call main\n" +
                        "\n" +
                        "1:\n" +
                        "	wfi\n" +
                        "	j 1b\n" +
                        "\n" +
                        ".global _machine_exception_handler\n" +
                        "_machine_exception_handler:\n" +
                        "	// Save all registers (to aid in debugging):\n" +
                        "	addi sp, sp, -124\n" +
                        "	sw x1, 0(sp)\n" +
                        "	sw x2, 4(sp)\n" +
                        "	sw x3, 8(sp)\n" +
                        "	sw x4, 12(sp)\n" +
                        "	sw x5, 16(sp)\n" +
                        "	sw x6, 20(sp)\n" +
                        "	sw x7, 24(sp)\n" +
                        "	sw x8, 28(sp)\n" +
                        "	sw x9, 32(sp)\n" +
                        "	sw x10, 36(sp)\n" +
                        "	sw x11, 40(sp)\n" +
                        "	sw x12, 44(sp)\n" +
                        "	sw x13, 48(sp)\n" +
                        "	sw x14, 52(sp)\n" +
                        "	sw x15, 56(sp)\n" +
                        "	sw x16, 60(sp)\n" +
                        "	sw x17, 64(sp)\n" +
                        "	sw x18, 68(sp)\n" +
                        "	sw x19, 72(sp)\n" +
                        "	sw x20, 76(sp)\n" +
                        "	sw x21, 80(sp)\n" +
                        "	sw x22, 84(sp)\n" +
                        "	sw x23, 88(sp)\n" +
                        "	sw x24, 92(sp)\n" +
                        "	sw x25, 96(sp)\n" +
                        "	sw x26, 100(sp)\n" +
                        "	sw x27, 104(sp)\n" +
                        "	sw x28, 108(sp)\n" +
                        "	sw x29, 112(sp)\n" +
                        "	sw x30, 116(sp)\n" +
                        "	sw x31, 120(sp)\n" +
                        "\n" +
                        "	csrr a0, mcause # First parameter: cause\n" +
                        "	csrr a1, mepc   # Second parameter: exception location\n" +
                        "	mv a2, sp	# Third parameter: start of stored register array\n" +
                        "	call exception_handler\n" +
                        "\n" +
                        ".hidden _machine_exception_return\n" +
                        "_machine_exception_return:\n" +
                        "	// Restore all registers:\n" +
                        "	lw x1, 0(sp)\n" +
                        "	# lw x2, 4(sp) <- x2 = sp, so do not load this register\n" +
                        "	lw x3, 8(sp)\n" +
                        "	lw x4, 12(sp)\n" +
                        "	lw x5, 16(sp)\n" +
                        "	lw x6, 20(sp)\n" +
                        "	lw x7, 24(sp)\n" +
                        "	lw x8, 28(sp)\n" +
                        "	lw x9, 32(sp)\n" +
                        "	lw x10, 36(sp)\n" +
                        "	lw x11, 40(sp)\n" +
                        "	lw x12, 44(sp)\n" +
                        "	lw x13, 48(sp)\n" +
                        "	lw x14, 52(sp)\n" +
                        "	lw x15, 56(sp)\n" +
                        "	lw x16, 60(sp)\n" +
                        "	lw x17, 64(sp)\n" +
                        "	lw x18, 68(sp)\n" +
                        "	lw x19, 72(sp)\n" +
                        "	lw x20, 76(sp)\n" +
                        "	lw x21, 80(sp)\n" +
                        "	lw x22, 84(sp)\n" +
                        "	lw x23, 88(sp)\n" +
                        "	lw x24, 92(sp)\n" +
                        "	lw x25, 96(sp)\n" +
                        "	lw x26, 100(sp)\n" +
                        "	lw x27, 104(sp)\n" +
                        "	lw x28, 108(sp)\n" +
                        "	lw x29, 112(sp)\n" +
                        "	lw x30, 116(sp)\n" +
                        "	lw x31, 120(sp)\n" +
                        "	addi sp, sp, 124\n" +
                        "\n" +
                        "	mret\n";
        try {
            new File(Folder+"/start.S").delete();
            i_o_peripheral_file = new FileOutputStream(Folder+"/start.S");
            i_o_peripheral_file.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                i_o_peripheral_file.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void write_load_file(String Folder) {
        FileOutputStream i_o_peripheral_file = null;
        String data =   "/* Linker script for standalone test applications for the Potato SoC\n" +
                        " * (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>\n" +
                        " * Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        " */\n" +
                        "\n" +
                        "ENTRY(_start)\n" +
                        "\n" +
                        "MEMORY\n" +
                        "{\n" +
                        "	RAM (rwx)    : ORIGIN = 0x00000000, LENGTH = 0x00020000\n" +
                        "	AEE_ROM (rx) : ORIGIN = 0xffff8000, LENGTH = 0x00004000\n" +
                        "	AEE_RAM (rw) : ORIGIN = 0xffffc000, LENGTH = 0x00004000\n" +
                        "}\n" +
                        "\n" +
                        "SECTIONS\n" +
                        "{\n" +
                        "	.text :\n" +
                        "	{\n" +
                        "		*(.init)\n" +
                        "		*(.text*)\n" +
                        "		__text_end = .;\n" +
                        "		*(.rodata*)\n" +
                        "	} > AEE_ROM\n" +
                        "\n" +
                        "	.data : AT(ADDR(.text) + SIZEOF(.text))\n" +
                        "	{\n" +
                        "		__data_begin = .;\n" +
                        "		*(.data*)\n" +
                        "		*(.eh_frame*)\n" +
                        "		__data_end = ALIGN(4);\n" +
                        "	} > AEE_RAM\n" +
                        "\n" +
                        "	.bss ALIGN(4) :\n" +
                        "	{\n" +
                        "		__bss_begin = .;\n" +
                        "		*(.bss*)\n" +
                        "		*(.sbss)\n" +
                        "		__bss_end = ALIGN(4);\n" +
                        "	} > AEE_RAM\n" +
                        "\n" +
                        "	/* Use the top of RAM and downwards for the stack: */\n" +
                        "	__stack_top = 0x00000000;\n" +
                        "\n" +
                        "	/DISCARD/ :\n" +
                        "	{\n" +
                        "		*(.comment)\n" +
                        "	}\n" +
                        "}";
        try {
            new File(Folder+"/load.ld").delete();
            i_o_peripheral_file = new FileOutputStream(Folder+"/load.ld");
            i_o_peripheral_file.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                i_o_peripheral_file.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void write_timer_file(String Folder) {
        FileOutputStream timer_file = null;
        String data =   "// The Potato SoC Library\n" +
                        "// (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>\n" +
                        "// Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "#ifndef LIBSOC_TIMER_H\n" +
                        "#define LIBSOC_TIMER_H\n" +
                        "\n" +
                        "#include <stdint.h>\n" +
                        "\n" +
                        "// Timer register offsets:\n" +
                        "#define TIMER_REG_CONTROL	0x00\n" +
                        "#define TIMER_REG_COMPARE_L	0x04\n" +
                        "#define TIMER_REG_COMPARE_H	0x08\n" +
                        "#define TIMER_REG_COUNTER_L	0x0C\n" +
                        "#define TIMER_REG_COUNTER_H	0x10\n" +
                        "\n" +
                        "// Timer control register bits:\n" +
                        "#define TIMER_CONTROL_RUN	0\n" +
                        "#define TIMER_CONTROL_CLEAR	1\n" +
                        "\n" +
                        "struct timer\n" +
                        "{\n" +
                        "	volatile uint32_t * registers;\n" +
                        "};\n" +
                        "\n" +
                        "/**\n" +
                        " * Initializes a timer instance.\n" +
                        " * @param module       Pointer to a timer instance structure.\n" +
                        " * @param base_address Base address of the timer hardware module.\n" +
                        " */\n" +
                        "static inline void timer_initialize(struct timer * module, volatile void * base_address)\n" +
                        "{\n" +
                        "	module->registers = base_address;\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Resets a timer.\n" +
                        " * This stops the timer and resets its counter value to 0.\n" +
                        " * @param module Pointer to a timer instance structure.\n" +
                        " */\n" +
                        "static inline void timer_reset(struct timer * module)\n" +
                        "{\n" +
                        "	module->registers[TIMER_REG_CONTROL >> 2] = 1 << TIMER_CONTROL_CLEAR;\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Starts a timer.\n" +
                        " * @param module Pointer to a timer instance structure.\n" +
                        " */\n" +
                        "static inline void timer_start(struct timer * module)\n" +
                        "{\n" +
                        "	module->registers[TIMER_REG_CONTROL >> 2] = 1 << TIMER_CONTROL_RUN | 1 << TIMER_CONTROL_CLEAR;\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Stops a timer.\n" +
                        " * @param module Pointer to a timer instance structure.\n" +
                        " */\n" +
                        "static inline void timer_stop(struct timer * module)\n" +
                        "{\n" +
                        "	module->registers[TIMER_REG_CONTROL >> 2] = 0;\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Clears a timer.\n" +
                        " * @param module Pointer to a timer instance structure.\n" +
                        " */\n" +
                        "static inline void timer_clear(struct timer * module)\n" +
                        "{\n" +
                        "	module->registers[TIMER_REG_CONTROL >> 2] |= 1 << TIMER_CONTROL_CLEAR;\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Sets the compare register of a timer.\n" +
                        " * @param module  Pointer to a timer instance structure.\n" +
                        " * @param compare Value to write to the timer compare register.\n" +
                        " * @warning Using this function while the timer is running could cause undefined bahviour.\n" +
                        " */\n" +
                        "static inline void timer_set_compare(struct timer * module, uint64_t compare)\n" +
                        "{\n" +
                        "	module->registers[TIMER_REG_COMPARE_L >> 2] = (uint32_t) compare;\n" +
                        "    module->registers[TIMER_REG_COMPARE_H >> 2] = (uint32_t) (compare >> 32);\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Reads the current value of a timer's counter.\n" +
                        " * @param module Pointer to a timer instance structure.\n" +
                        " * @returns The value of the timer's counter register.\n" +
                        " */\n" +
                        "static inline uint64_t timer_get_count(struct timer  * module)\n" +
                        "{\n" +
                        "\tuint64_t ret = module->registers[TIMER_REG_COUNTER_H >> 2];\n" +
                        "\tret = ret << 32;\n" +
                        "\tret = ret | (module->registers[TIMER_REG_COUNTER_L >> 2]);\n" +
                        "\treturn ret;\n" +
                        "}\n" +
                        "\n" +
                        "/**\n" +
                        " * Sets the value of a timer's counter register.\n" +
                        " * @param module  Pointer to a timer instance structure.\n" +
                        " * @param counter New value of the timer's counter register.\n" +
                        " * @warning This function should only be used when the timer is stopped to avoid undefined behaviour.\n" +
                        " */\n" +
                        " static inline void timer_set_count(struct timer * module, uint64_t counter)\n" +
                        " {\n" +
                        "	module->registers[TIMER_REG_COUNTER_L >> 2] = (uint32_t) counter;\n" +
                        "	module->registers[TIMER_REG_COUNTER_H >> 2] = (uint32_t) (counter >> 32);\n" +
                        " }\n" +
                        "\n" +
                        "#endif";
        try {
            new File(Folder+"/timer.h").delete();
            timer_file = new FileOutputStream(Folder+"/timer.h");
            timer_file.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                timer_file.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void write_timer_hw_file(String Folder) {
        FileOutputStream timer_file = null;
        String data =   "// The Potato SoC Library add from hossameldin\n" +
                        "// (c) Hossameldin Bayoummy Eassa 2019 <hossameassa@gmail.com>\n" +
                        "\n" +
                        "#ifndef LIBSOC_TIMER_HW_H\n" +
                        "#define LIBSOC_TIMER_HW_H\n" +
                        "\n" +
                        "#include <stdint.h>\n" +
                        "\n" +
                        "// Timer register offsets:\n" +
                        "#define Elapsed_Time_L	0x00\n" +
                        "#define Elapsed_Time_H	0x04\n" +
                        "#define Q_TON         	0x08\n" +
                        "#define Preset_Time_L	0x00\n" +
                        "#define Preset_Time_H	0x04\n" +
                        "#define IN_TON        	0x08\n" +
                        "\n" +
                        "struct timer_hw\n" +
                        "{\n" +
                        "	volatile uint32_t * registers;\n" +
                        "};\n" +
                        "\n" +
                        "static inline void timer_hw_initialize(struct timer_hw * module, volatile void * base_address)\n" +
                        "{\n" +
                        "	module->registers = base_address;\n" +
                        "}\n" +
                        "\n" +
                        "static inline void timer_hw_send_in(struct timer_hw * module, uint32_t in_data)\n" +
                        "{\n" +
                        "	module->registers[Q_TON >> 2] = (uint32_t) in_data;\n" +
                        "}\n" +
                        "\n" +
                        "static inline void timer_hw_send_preset_time(struct timer_hw * module, uint64_t preset_time)\n" +
                        "{\n" +
                        "	module->registers[Preset_Time_L >> 2] = (uint32_t) preset_time;\n" +
                        "    module->registers[Preset_Time_H >> 2] = (uint32_t) (preset_time >> 32);\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint64_t timer_hw_recieve_elapsed_time(struct timer_hw  * module)\n" +
                        "{\n" +
                        "    uint64_t ret = module->registers[Elapsed_Time_H >> 2];\n" +
                        "    ret = ret << 32;\n" +
                        "    ret = ret | (module->registers[Elapsed_Time_L >> 2]);\n" +
                        "	return ret;\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint32_t timer_hw_recieve_Q(struct timer_hw  * module)\n" +
                        "{\n" +
                        "	return module->registers[Q_TON >> 2];\n" +
                        "}\n" +
                        "\n" +
                        "#endif";
        try {
            new File(Folder+"/timer_hw.h").delete();
            timer_file = new FileOutputStream(Folder+"/timer_hw.h");
            timer_file.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                timer_file.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void write_pwm_hw_file(String Folder) {
        FileOutputStream timer_file = null;
        String data =   "// The Potato SoC Library add from hossameldin\n" +
                        "// (c) Hossameldin Bayoummy Eassa 2019 <hossameassa@gmail.com>\n" +
                        "\n" +
                        "#ifndef LIBSOC_PWM_HW_H\n" +
                        "#define LIBSOC_PWM_HW_H\n" +
                        "\n" +
                        "#include <stdint.h>\n" +
                        "\n" +
                        "// PWM register offsets:\n" +
                        "#define Q_PWM			0x00\n" +
                        "#define Frequency_Address      0x04\n" +
                        "#define Duty_Cycle_Address	0x08\n" +
                        "\n" +
                        "struct pwm_hw\n" +
                        "{\n" +
                        "	volatile uint32_t * registers;\n" +
                        "};\n" +
                        "\n" +
                        "static inline void pwm_hw_initialize(struct pwm_hw * module, volatile void * base_address)\n" +
                        "{\n" +
                        "	module->registers = base_address;\n" +
                        "}\n" +
                        "\n" +
                        "static inline void pwm_hw_send_frequency(struct pwm_hw * module, uint32_t frequency)\n" +
                        "{\n" +
                        "	module->registers[Frequency_Address >> 2] = (uint32_t) frequency;\n" +
                        "}\n" +
                        "\n" +
                        "static inline void pwm_hw_send_duty_cycle(struct pwm_hw * module, uint32_t duty_cycle)\n" +
                        "{\n" +
                        "	module->registers[Duty_Cycle_Address >> 2] = (uint32_t) duty_cycle;\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint32_t pwm_hw_recieve_Q(struct pwm_hw  * module)\n" +
                        "{\n" +
                        "	return module->registers[Q_PWM >> 2];\n" +
                        "}\n" +
                        "\n" +
                        "#endif";
        try {
            new File(Folder+"/pwm_hw.h").delete();
            timer_file = new FileOutputStream(Folder+"/pwm_hw.h");
            timer_file.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                timer_file.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void write_time_measurement_file(String Folder) {
        FileOutputStream time_measurement_file = null;
        String data =   "// The Potato SoC Library add from hossameldin\n" +
                        "// (c) Hossameldin Bayoummy Eassa 2019 <hossameassa@gmail.com>\n" +
                        "\n" +
                        "#ifndef PAEE_TIME_MEASUREMENT_H\n" +
                        "#define PAEE_TIME_MEASUREMENT_H\n" +
                        "\n" +
                        "#include <stdbool.h>\n" +
                        "#include <stdint.h>\n" +
                        "\n" +
                        "#define START_STOP_A	0x00 // Address of writing start and stop\n" +
                        "#define START			0x71\n" +
                        "#define STOP			0x53\n" +
                        "\n" +
                        "#define MICRO_NANO_A	0x04 // Address of writing Micro Or Nano Measurements\n" +
                        "#define MICRO			0x36\n" +
                        "#define NANO			0x42\n" +
                        "\n" +
                        "#define READ_TIME_A		0x08\n" +
                        "\n" +
                        "\n" +
                        "struct time_measurement\n" +
                        "{\n" +
                        "	volatile uint32_t * registers;\n" +
                        "};\n" +
                        "\n" +
                        "static inline void time_measurement_per_initialize(struct time_measurement * module, volatile void * base)\n" +
                        "{\n" +
                        "	module->registers = base;\n" +
                        "}\n" +
                        "\n" +
                        "static inline void start_time(struct time_measurement * module)\n" +
                        "{\n" +
                        "	module->registers[(START_STOP_A >> 2)] = START;\n" +
                        "}\n" +
                        "\n" +
                        "static inline void stop_time(struct time_measurement * module)\n" +
                        "{\n" +
                        "	module->registers[(START_STOP_A >> 2)] = STOP;\n" +
                        "}\n" +
                        "\n" +
                        "static inline void set_micro(struct time_measurement * module)\n" +
                        "{\n" +
                        "	module->registers[(MICRO_NANO_A >> 2)] = MICRO;\n" +
                        "}\n" +
                        "\n" +
                        "static inline void set_nano(struct time_measurement * module)\n" +
                        "{\n" +
                        "	module->registers[(MICRO_NANO_A >> 2)] = NANO;\n" +
                        "}\n" +
                        "\n" +
                        "#endif";
        try {
            new File(Folder+"/time_measurement.h").delete();
            time_measurement_file = new FileOutputStream(Folder+"/time_measurement.h");
            time_measurement_file.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                time_measurement_file.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void declareAndInitializeVariables() {
        String Variable_temp;
        String typeOfVariable;
        String nameOfVariable;
        String C_DataType;
        int[] Register_Type = new int[1];
        for (int i = 1; i < Data.size_Vaiables-1; i++) {
            Variable_temp = Data.Vaiables[i].replace(" ", "");
            nameOfVariable = Variable_temp.split(":")[0];
            typeOfVariable = Variable_temp.split(":")[1];
            C_DataType = new GeneralFunctions().convert_il_datatype_to_c_datatype(typeOfVariable, Register_Type);
            if (!C_DataType.equals("Timer")) Data.C_code += "\t"+C_DataType+" "+nameOfVariable+" = 0;\n";
        }
    }
}
