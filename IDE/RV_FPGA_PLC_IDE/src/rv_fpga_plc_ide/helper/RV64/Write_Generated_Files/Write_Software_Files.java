/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.RV64.Write_Generated_Files;

import java.io.File;
import rv_fpga_plc_ide.helper.Data;
import rv_fpga_plc_ide.helper.GeneralFunctions;

/**
 *
 * @author hossameldin
 */
public class Write_Software_Files {
    static String c_files = "NO_C_FILES";
    public void write_library_files(String Project_Folder, int hdl_compilation_type) {
        if (hdl_compilation_type == Data.SW_COMPILATION) {
            c_files = "c_files_RV64_SW";
        } else if (hdl_compilation_type == Data.HW_COMPILATION) {
            c_files = "c_files_RV64_HW";
        }
        generate_c_subfolders(Project_Folder);
        write_boot_files(Project_Folder+"/boot");
        write_common_files(Project_Folder+"/common");
        write_elf2rawx_files(Project_Folder+"/elf2rawx");
        write_hex2mif_files(Project_Folder+"/hex2mif");
        write_project_main_files(Project_Folder+"/"+Data.Project_Name+"_application");
    }
    
    private void generate_c_subfolders(String Project_Folder) {
        File file;
        file = new File(Project_Folder+"/boot"); file.mkdirs();
        file = new File(Project_Folder+"/boot/makefiles"); file.mkdirs();
        file = new File(Project_Folder+"/boot/src"); file.mkdirs();
        file = new File(Project_Folder+"/common"); file.mkdirs();
        file = new File(Project_Folder+"/common/maps"); file.mkdirs();
        file = new File(Project_Folder+"/elf2rawx"); file.mkdirs();
        file = new File(Project_Folder+"/elf2rawx/makefiles"); file.mkdirs();
        file = new File(Project_Folder+"/elf2rawx/src"); file.mkdirs();
        file = new File(Project_Folder+"/elf2rawx/src/common"); file.mkdirs();
        file = new File(Project_Folder+"/hex2mif"); file.mkdirs();
        file = new File(Project_Folder+"/"+Data.Project_Name+"_application"); file.mkdirs();
        file = new File(Project_Folder+"/"+Data.Project_Name+"_application/makefiles"); file.mkdirs();
        file = new File(Project_Folder+"/"+Data.Project_Name+"_application/src"); file.mkdirs();
    }
    
    private void write_boot_files(String Folder) {
        write_boot_makefile_file(Folder+"/makefiles/makefile");
        write_util_mak_file(Folder+"/makefiles/util.mak");
        write_boot_linker_ld_file(Folder+"/makefiles/boot_linker.ld");
        
        write_boot_main_c_file(Folder+"/src/main.c");
        write_trap_c_file(Folder+"/src/trap.c");
        write_crt_S_file(Folder+"/src/crt.S");
        write_encoding_h_file(Folder+"/src/encoding.h");
    }
    
    private void write_common_files(String Folder) {
        write_axi_maps_h_file(Folder+"/axi_maps.h");
        write_axi_const_h_file(Folder+"/axi_const.h");
        
        write_map_i_o_peripheral_h_file(Folder+"/maps/map_i_o_peripheral.h");
        write_map_gptimers_h_file(Folder+"/maps/map_gptimers.h");
        write_map_uart_h_file(Folder+"/maps/map_uart.h");
        write_map_pwm_hw_h_file(Folder+"/maps/map_pwm_hw.h");
        write_map_time_measurement_h_file(Folder+"/maps/map_time_measurement.h");
        write_map_timer_hw_h_file(Folder+"/maps/map_timer_hw.h");
        write_map_map_irqctrl_h_file(Folder+"/maps/map_irqctrl.h");
    }
    
    private void write_elf2rawx_files(String Folder) {
        write_elf2rawx_makefile_file(Folder+"/makefiles/makefile");
        write_util_mak_file(Folder+"/makefiles/util.mak");
        
        write_elf2rawx_main_cpp_file(Folder+"/src/main.cpp");
        write_elfreader_cpp_file(Folder+"/src/elfreader.cpp");
        write_elfreader_h_file(Folder+"/src/elfreader.h");
        write_elftypes_h_file(Folder+"/src/elftypes.h");
        
        write_attribute_cpp_file(Folder+"/src/common/attribute.cpp");
        write_autobuffer_cpp_file(Folder+"/src/common/autobuffer.cpp");
        write_attribute_h_file(Folder+"/src/common/attribute.h");
        write_autobuffer_h_file(Folder+"/src/common/autobuffer.h");
        write_iattr_h_file(Folder+"/src/common/iattr.h");
        write_iface_h_file(Folder+"/src/common/iface.h");
    }
    
    private void write_hex2mif_files(String Folder) {
        write_hex2mif_makefile_file(Folder+"/makefile");
        write_hex2mif_main_c_file(Folder+"/main.c");
    }
    
    private void write_project_main_files(String Folder) {
        write_project_main_makefile_file(Folder+"/makefiles/makefile");
        write_app_ld_file(Folder+"/makefiles/app.ld");
        write_util_mak_file(Folder+"/makefiles/makeutil.mak");
        
        write_project_main_main_c_file(Folder+"/src/main.c");
    }
    
    private void write_boot_makefile_file(String Project_Folder_File) {
        String data =   "include $(PROJECT_FOLDER)boot/makefiles/util.mak\n" +
                        "\n" +
                        "CC=/opt/riscv64/bin/riscv64-unknown-elf-gcc\n" +
                        "CPP=/opt/riscv64/bin/riscv64-unknown-elf-gcc\n" +
                        "OBJDUMP=/opt/riscv64/bin/riscv64-unknown-elf-objdump\n" +
                        "ELF2HEX=$(PROJECT_FOLDER)elf2rawx/elf/elf2rawx\n" +
                        "HEX2MIF=$(PROJECT_FOLDER)hex2mif/bin/hex2mif\n" +
                        "\n" +
                        "OBJ_DIR = $(PROJECT_FOLDER)boot/obj\n" +
                        "ELF_DIR = $(PROJECT_FOLDER)boot/bin\n" +
                        "\n" +
                        "FPU_ENABLED="+new GeneralFunctions().bool2int(Data.is_fpu_RV64_enabeled)+"\n" +
                        "\n" +
                        "CFLAGS= -c -g -static -std=gnu99 -O0 -fno-common -fno-builtin-printf\n" +
                        "ifeq ($(FPU_ENABLED), 1)\n" +
                        "  CFLAGS += -march=rv64imafd -DFPU_ENABLED\n" +
                        "else\n" +
                        "  CFLAGS += -march=rv64imac -mabi=lp64\n" +
                        "endif\n" +
                        "\n" +
                        "\n" +
                        "LDFLAGS=-T $(PROJECT_FOLDER)boot/makefiles/boot_linker.ld -nostdlib -nostartfiles\n" +
                        "ifeq ($(FPU_ENABLED), 1)\n" +
                        "else\n" +
                        "  LDFLAGS += -march=rv64imac -mabi=lp64\n" +
                        "endif\n" +
                        "\n" +
                        "\n" +
                        "INCL_KEY=-I\n" +
                        "DIR_KEY=-B\n" +
                        "\n" +
                        "\n" +
                        "# include sub-folders list\n" +
                        "INCL_PATH=\\\n" +
                        "	$(PROJECT_FOLDER)common \\\n" +
                        "	$(PROJECT_FOLDER)boot/src\n" +
                        "\n" +
                        "# source files directories list:\n" +
                        "SRC_PATH =\\\n" +
                        "	$(PROJECT_FOLDER)boot/src\n" +
                        "\n" +
                        "LIB_NAMES =\\\n" +
                        "	gcc \\\n" +
                        "	c \\\n" +
                        "	m\n" +
                        "\n" +
                        "VPATH = $(SRC_PATH)\n" +
                        "\n" +
                        "SOURCES = main \\\n" +
                        "	trap \\\n" +
                        "	crt\n" +
                        "\n" +
                        "OBJ_FILES = $(addsuffix .o,$(SOURCES))\n" +
                        "EXECUTABLE = bootimage\n" +
                        "DUMPFILE = $(EXECUTABLE).dump\n" +
                        "HEXFILE = $(EXECUTABLE).hex\n" +
                        "MIFFILE = $(EXECUTABLE).mif\n" +
                        "LSTFILE = $(EXECUTABLE).lst\n" +
                        "\n" +
                        "\n" +
                        "all: $(EXECUTABLE) $(DUMPFILE) $(HEXFILE) $(MIFFILE)\n" +
                        "\n" +
                        "$(MIFFILE): $(EXECUTABLE)\n" +
                        "	$(HEX2MIF) $(ELF_DIR)/$(HEXFILE) $(ELF_DIR)/$(MIFFILE) 4096\n" +
                        "\n" +
                        "$(HEXFILE): $(EXECUTABLE)\n" +
                        "	$(ELF2HEX) $(addprefix $(ELF_DIR)/,$<) -h -f 32768 -l 8 -o $(addprefix $(ELF_DIR)/,$(EXECUTABLE).hex)\n" +
                        "\n" +
                        "$(DUMPFILE): $(EXECUTABLE)\n" +
                        "	$(OBJDUMP) --disassemble-all --disassemble-zeroes --section=.text --section=.text.startup --section=.data $(addprefix $(ELF_DIR)/,$<) > $(addprefix $(ELF_DIR)/,$@)\n" +
                        "	$(OBJDUMP) -S $(addprefix $(ELF_DIR)/,$<) > $(addprefix $(ELF_DIR)/,$(LSTFILE))\n" +
                        "\n" +
                        "\n" +
                        "$(EXECUTABLE): $(OBJ_FILES)\n" +
                        "	$(MKDIR) $(ELF_DIR)\n" +
                        "	$(CPP) $(LDFLAGS) $(addprefix $(OBJ_DIR)/,$(OBJ_FILES)) -o $(addprefix $(ELF_DIR)/,$@) $(addprefix -l,$(LIB_NAMES))\n" +
                        "	$(ECHO) \"\\n  Boot has been built successfully.\\n\"\n" +
                        "\n" +
                        "#.cpp.o:\n" +
                        "%.o: %.cpp\n" +
                        "	$(MKDIR) $(OBJ_DIR)\n" +
                        "	$(CPP) $(CFLAGS) $(addprefix $(INCL_KEY),$(INCL_PATH)) $< -o $(addprefix $(OBJ_DIR)/,$@)\n" +
                        "\n" +
                        "#.c.o:\n" +
                        "%.o: %.c\n" +
                        "	$(MKDIR) $(OBJ_DIR)\n" +
                        "	$(CC) $(CFLAGS) $(addprefix $(INCL_KEY),$(INCL_PATH)) $< -o $(addprefix $(OBJ_DIR)/,$@)\n" +
                        "\n" +
                        "%.o: %.S\n" +
                        "	$(MKDIR) $(OBJ_DIR)\n" +
                        "	$(CC) $(CFLAGS) -D__ASSEMBLY__=1 $(addprefix $(INCL_KEY),$(INCL_PATH)) $< -o $(addprefix $(OBJ_DIR)/,$@)";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_util_mak_file(String Project_Folder_File) {
        String data =   "# mkdir: -p = --parents. No error if dir exists\n" +
                        "#        -v = --verbose. print a message for each created directory\n" +
                        "MKDIR = mkdir -pv\n" +
                        "# rm: -r = --recursive. Remove the contents of dirs recursively\n" +
                        "#     -v = --verbose. Explain what is being done\n" +
                        "#     -f = --force.Ignore nonexistent files, never prompt\n" +
                        "#     --no-preserve-root.\n" +
                        "RM = rm -rvf --no-preserve-root\n" +
                        "\n" +
                        "ECHO = echo\n" +
                        "\n" +
                        "export MKDIR RM ECHO";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_boot_linker_ld_file(String Project_Folder_File) {
        String data =   "/*----------------------------------------------------------------------*/\n" +
                        "/* Setup                                                                */\n" +
                        "/*----------------------------------------------------------------------*/\n" +
                        "\n" +
                        "/* The OUTPUT_ARCH command specifies the machine architecture where the\n" +
                        "   argument is one of the names used in the BFD library. More\n" +
                        "   specifically one of the entires in bfd/cpu-mips.c */\n" +
                        "\n" +
                        "OUTPUT_ARCH( \"riscv\" )\n" +
                        "\n" +
                        "/*----------------------------------------------------------------------*/\n" +
                        "/* Sections                                                             */\n" +
                        "/*----------------------------------------------------------------------*/\n" +
                        "\n" +
                        "SECTIONS\n" +
                        "{\n" +
                        "\n" +
                        "  /* text: test code section */\n" +
                        "  . = 0x0000;\n" +
                        "  .text : \n" +
                        "  {\n" +
                        "    KEEP(*(.isr_vector))\n" +
                        "    *(.text*)\n" +
                        "  }\n" +
                        "\n" +
                        "  /* data segment */\n" +
                        "  .data : { *(.data) }\n" +
                        "\n" +
                        "  .sdata : {\n" +
                        "    *(.srodata.cst16) *(.srodata.cst8) *(.srodata.cst4) *(.srodata.cst2) *(.srodata*)\n" +
                        "    *(.sdata .sdata.* .gnu.linkonce.s.*)\n" +
                        "  }\n" +
                        "\n" +
                        "  /* bss segment */\n" +
                        "  .sbss : {\n" +
                        "    *(.sbss .sbss.* .gnu.linkonce.sb.*)\n" +
                        "    *(.scommon)\n" +
                        "  }\n" +
                        "  .bss : { *(.bss) }\n" +
                        "\n" +
                        "  /* End of uninitalized data segement */\n" +
                        "  _end = .;\n" +
                        "}";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_boot_main_c_file(String Project_Folder_File) {
        String data =   "/*\n" +
                        " *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com\n" +
                        " *\n" +
                        " *  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        " *  you may not use this file except in compliance with the License.\n" +
                        " *  You may obtain a copy of the License at\n" +
                        " *\n" +
                        " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
                        " *\n" +
                        " *  Unless required by applicable law or agreed to in writing, software\n" +
                        " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        " *  See the License for the specific language governing permissions and\n" +
                        " *  limitations under the License.\n" +
                        " */\n" +
                        "\n" +
                        "#include <string.h>\n" +
                        "#include \"axi_maps.h\"\n" +
                        "#include \"encoding.h\"\n" +
                        "\n" +
                        "static const int FW_IMAGE_SIZE_BYTES = 1 << 16; // 64 KB\n" +
                        "\n" +
                        "int fw_get_cpuid() {\n" +
                        "    int ret;\n" +
                        "    asm(\"csrr %0, mhartid\" : \"=r\" (ret));\n" +
                        "    return ret;\n" +
                        "}\n" +
                        "\n" +
                        "void print_uart(const char *buf, int sz) {\n" +
                        "    uart_map *uart = (uart_map *)ADDR_NASTI_SLAVE_UART1;\n" +
                        "    for (int i = 0; i < sz; i++) {\n" +
                        "        while (uart->status & UART_STATUS_TX_FULL) {}\n" +
                        "        uart->data = buf[i];\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void print_uart_hex(long val) {\n" +
                        "    unsigned char t, s;\n" +
                        "    uart_map *uart = (uart_map *)ADDR_NASTI_SLAVE_UART1;\n" +
                        "    for (int i = 0; i < 16; i++) {\n" +
                        "        while (uart->status & UART_STATUS_TX_FULL) {}\n" +
                        "        \n" +
                        "        t = (unsigned char)((val >> ((15 - i) * 4)) & 0xf);\n" +
                        "        if (t < 10) {\n" +
                        "            s = t + '0';\n" +
                        "        } else {\n" +
                        "            s = (t - 10) + 'a';\n" +
                        "        }\n" +
                        "        uart->data = s;\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void copy_image() {\n" +
                        "    uint64_t *fwrom = (uint64_t *)ADDR_NASTI_SLAVE_FWIMAGE;\n" +
                        "    uint64_t *sram = (uint64_t *)ADDR_NASTI_SLAVE_SRAM;\n" +
                        "    \n" +
                        "    memcpy(sram, fwrom, FW_IMAGE_SIZE_BYTES);\n" +
                        "}\n" +
                        "\n" +
                        "/** This function will be used during video recording to show\n" +
                        " how tochange npc register value on core[1] while core[0] is running\n" +
                        " Zephyr OS\n" +
                        "*/\n" +
                        "void timestamp_output() {\n" +
                        "    gptimers_map *tmr = (gptimers_map *)ADDR_NASTI_SLAVE_GPTIMERS;\n" +
                        "    uint64_t start = tmr->highcnt;\n" +
                        "    while (1) {\n" +
                        "        if (tmr->highcnt < start || (start + SYS_HZ) < tmr->highcnt) {\n" +
                        "            start = tmr->highcnt;\n" +
                        "            print_uart(\"HIGHCNT: \", 9);\n" +
                        "            print_uart_hex(start);\n" +
                        "            print_uart(\"\\r\\n\", 2);\n" +
                        "        }\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void _init() {\n" +
                        "    uart_map *uart = (uart_map *)ADDR_NASTI_SLAVE_UART1;\n" +
                        "    irqctrl_map *p_irq = (irqctrl_map *)ADDR_NASTI_SLAVE_IRQCTRL;\n" +
                        "    io_per io_per_d;\n" +
                        "    \n" +
                        "    io_per_d.registers = (volatile void *)ADDR_NASTI_SLAVE_GPIO;\n" +
                        "\n" +
                        "    if (fw_get_cpuid() != 0) {\n" +
                        "        // TODO: waiting event or something\n" +
                        "        while(1) {}\n" +
                        "    }\n" +
                        "\n" +
                        "    // mask all interrupts in interrupt controller to avoid\n" +
                        "    // unpredictable behaviour after elf-file reloading via debug port.\n" +
                        "    p_irq->irq_mask = 0xFFFFFFFF;\n" +
                        "\n" +
                        "    // Half period of the uart = Fbus / 115200 / 2 = 70 MHz / 115200 / 2:\n" +
                        "    uart->scaler = SYS_HZ / 115200 / 2;  // 40 MHz\n" +
                        "    \n" +
                        "    io_per_set_output(&io_per_d, LEDG, 0, LED_ON); // LED = 1\n" +
                        "    io_per_set_output(&io_per_d, RWD, 0, 0);\n" +
                        "    print_uart(\"Booting . . .\\n\\r\", 15);\n" +
                        "    \n" +
                        "    io_per_set_output(&io_per_d, LEDG, 1, LED_ON); // LEDG = 2\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 0, LED_OFF);\n" +
                        "    io_per_set_output(&io_per_d, RWD, 0, 0);\n" +
                        "\n" +
                        "    copy_image();\n" +
                        "    \n" +
                        "    io_per_set_output(&io_per_d, LEDG, 2, LED_ON); // LEDG = 4\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 1, LED_OFF);\n" +
                        "    io_per_set_output(&io_per_d, RWD, 0, 0);\n" +
                        "    print_uart(\"Application image copied to RAM\\r\\n\", 33);\n" +
                        "\n" +
                        "    io_per_set_output(&io_per_d, LEDR, 9, LED_ON); // LEDR = 0x200\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 2, LED_OFF);\n" +
                        "    io_per_set_output(&io_per_d, RWD, 0, 0);\n" +
                        "    print_uart(\"Jump to Application in RAM\\r\\n\", 28);\n" +
                        "}\n" +
                        "\n" +
                        "/** Not used actually */\n" +
                        "int main() {\n" +
                        "    while (1) {}\n" +
                        "\n" +
                        "    return 0;\n" +
                        "}";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_trap_c_file(String Project_Folder_File) {
        String data =   "/*\n" +
                        " *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com\n" +
                        " *\n" +
                        " *  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        " *  you may not use this file except in compliance with the License.\n" +
                        " *  You may obtain a copy of the License at\n" +
                        " *\n" +
                        " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
                        " *\n" +
                        " *  Unless required by applicable law or agreed to in writing, software\n" +
                        " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        " *  See the License for the specific language governing permissions and\n" +
                        " *  limitations under the License.\n" +
                        " */\n" +
                        "\n" +
                        "#include <string.h>\n" +
                        "#include \"axi_maps.h\"\n" +
                        "#include \"encoding.h\"\n" +
                        "\n" +
                        "typedef void (*IRQ_HANDLER)(int idx, void *args);\n" +
                        "\n" +
                        "typedef union csr_mcause_type {\n" +
                        "    struct bits_type {\n" +
                        "        uint64_t code   : 63;   // 11 - Machine external interrupt\n" +
                        "        uint64_t irq    : 1;\n" +
                        "    } bits;\n" +
                        "    uint64_t value;\n" +
                        "} csr_mcause_type;\n" +
                        "\n" +
                        "extern void print_uart(const char *buf, int sz);\n" +
                        "extern void print_uart_hex(long val);\n" +
                        "extern void led_set(int output);\n" +
                        "\n" +
                        "int get_mcause() {\n" +
                        "    int ret;\n" +
                        "    asm(\"csrr %0, mcause\" : \"=r\" (ret));\n" +
                        "    return ret;\n" +
                        "}\n" +
                        "\n" +
                        "int get_mepc() {\n" +
                        "    int ret;\n" +
                        "    asm(\"csrr %0, mepc\" : \"=r\" (ret));\n" +
                        "    return ret;\n" +
                        "}\n" +
                        "\n" +
                        "int get_uepc() {\n" +
                        "    int ret;\n" +
                        "    asm(\"csrr %0, uepc\" : \"=r\" (ret));\n" +
                        "    return ret;\n" +
                        "}\n" +
                        "\n" +
                        "int get_mstatus(){\n" +
                        "    int ret;\n" +
                        "    asm(\"csrr %0, mstatus\" : \"=r\" (ret));\n" +
                        "    return ret;\n" +
                        "}\n" +
                        "\n" +
                        "void exception_handler_c() {\n" +
                        "    print_uart(\"mcause:\", 7);\n" +
                        "    print_uart_hex(get_mcause());\n" +
                        "    print_uart(\",mepc:\", 6);\n" +
                        "    print_uart_hex(get_mepc());\n" +
                        "    print_uart(\",uepc:\", 6);\n" +
                        "    print_uart_hex(get_uepc());\n" +
                        "    print_uart(\",mstatus:\", 9);\n" +
                        "    print_uart_hex(get_mstatus());\n" +
                        "    print_uart(\"\\r\\n\", 2);\n" +
                        "    struct io_per io_per_d;\n" +
                        "    io_per_d.registers = (volatile void *)ADDR_NASTI_SLAVE_GPIO;\n" +
                        "\n" +
                        "    /// Exception trap\n" +
                        "    io_per_set_output(&io_per_d, LEDR, 0, LED_ON);\n" +
                        "    io_per_set_output(&io_per_d, LEDR, 1, LED_ON);\n" +
                        "    io_per_set_output(&io_per_d, LEDR, 2, LED_ON);\n" +
                        "    io_per_set_output(&io_per_d, RWD, 0, 0);\n" +
                        "    while (1) {}\n" +
                        "}\n" +
                        "\n" +
                        "long interrupt_handler_c(long cause, long epc, long long regs[32]) {\n" +
                        "    /**\n" +
                        "     * Pending interrupt bit is cleared in the crt.S file by calling:\n" +
                        "     *      csrc mip, MIP_MSIP\n" +
                        "     * If we woudn't do it the interrupt handler will be called infinitly\n" +
                        "     *\n" +
                        "     * Rise interrupt from the software maybe done sending a self-IPI:\n" +
                        "     *      csrwi mipi, 0\n" +
                        "     */\n" +
                        "    irqctrl_map *p_irqctrl = (irqctrl_map *)ADDR_NASTI_SLAVE_IRQCTRL;\n" +
                        "    IRQ_HANDLER irq_handler = (IRQ_HANDLER)p_irqctrl->isr_table;\n" +
                        "    uint32_t pending;\n" +
                        "    csr_mcause_type mcause;\n" +
                        "\n" +
                        "    mcause.value = cause;\n" +
                        "    p_irqctrl->dbg_cause = cause;\n" +
                        "    p_irqctrl->dbg_epc = epc;\n" +
                        "\n" +
                        "    p_irqctrl->irq_lock = 1;\n" +
                        "    pending = p_irqctrl->irq_pending;\n" +
                        "    p_irqctrl->irq_clear = pending;\n" +
                        "    p_irqctrl->irq_lock = 0;\n" +
                        "\n" +
                        "    for (int i = 0; i < CFG_IRQ_TOTAL; i++) {\n" +
                        "        if (pending & 0x1) {\n" +
                        "            p_irqctrl->irq_cause_idx = i;\n" +
                        "            irq_handler(i, NULL);\n" +
                        "        }\n" +
                        "        pending >>= 1;\n" +
                        "    }\n" +
                        "\n" +
                        "    return epc;\n" +
                        "}";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_crt_S_file(String Project_Folder_File) {
        String data =   "##!	Register	ABI Name	Description						Saver\n" +
                        "##!	x0			zero		Hard-wired zero					�\n" +
                        "##!	x1			ra			Return address					Caller\n" +
                        "##!	x2			s0/fp		Saved register/frame pointer	Callee\n" +
                        "##!	x3:13		s1:11		Saved registers					Callee\n" +
                        "##!	x14			sp			Stack pointer					Callee\n" +
                        "##!	x15			tp			Thread pointer					Callee   \n" +
                        "##!	x16:17		v0:1		Return values					Caller\n" +
                        "##!	x18:25		a0:7		Function arguments				Caller\n" +
                        "##!	x26:30		t0:4		Temporaries						Caller\n" +
                        "##!	x31			gp			Global 	pointer					�\n" +
                        "##!	f0:15		fs0:15		FP saved registers				Callee\n" +
                        "##!	f16:17		fv0:1		FP return values				Caller\n" +
                        "##!	f18:25		fa0:7		FP arguments					Caller\n" +
                        "##!	f26:31		ft0:5		FP temporaries					Caller\n" +
                        "\n" +
                        "#include \"encoding.h\"\n" +
                        "\n" +
                        "##! Disabling the compressed code\n" +
                        "  .option norvc\n" +
                        "\n" +
                        "  .section .isr_vector, \"x\"\n" +
                        "  .align 4\n" +
                        "  .globl _start\n" +
                        "  .globl exception_handler_c\n" +
                        "  .globl interrupt_handler_c\n" +
                        "\n" +
                        "trap_table:\n" +
                        "# 8-bytes per exception entry\n" +
                        "trap_table:\n" +
                        "  j _start            # \n" +
                        "  nop\n" +
                        "  j exception_entry   # Instruction Misaligned\n" +
                        "  nop\n" +
                        "  j exception_entry   # Instruction Load Fault\n" +
                        "  nop\n" +
                        "  j exception_entry   # Instruction Illegal\n" +
                        "  nop\n" +
                        "  j exception_entry   # Breakpoint\n" +
                        "  nop\n" +
                        "  j exception_entry   # Load Misaligned\n" +
                        "  nop\n" +
                        "  j exception_entry   # Load Fault\n" +
                        "  nop\n" +
                        "  j exception_entry   # Store Misaligned\n" +
                        "  nop\n" +
                        "  j exception_entry   # Store Fault\n" +
                        "  nop\n" +
                        "  j exception_entry   # Call from U-mode\n" +
                        "  nop\n" +
                        "  j exception_entry   # Call from S-mode\n" +
                        "  nop\n" +
                        "  j exception_entry   # Call from H-mode\n" +
                        "  nop\n" +
                        "  j exception_entry   # Call from M-mode\n" +
                        "  nop\n" +
                        "\n" +
                        "bad_trap:\n" +
                        "  j bad_trap\n" +
                        "\n" +
                        "_start:\n" +
                        "  li  x1, 0\n" +
                        "  li  x2, 0\n" +
                        "  li  x3, 0\n" +
                        "  li  x4, 0\n" +
                        "  li  x5, 0\n" +
                        "  li  x6, 0\n" +
                        "  li  x7, 0\n" +
                        "  li  x8, 0\n" +
                        "  li  x9, 0\n" +
                        "  li  x10,0\n" +
                        "  li  x11,0\n" +
                        "  li  x12,0\n" +
                        "  li  x13,0\n" +
                        "  li  x14,0\n" +
                        "  li  x15,0\n" +
                        "  li  x16,0\n" +
                        "  li  x17,0\n" +
                        "  li  x18,0\n" +
                        "  li  x19,0\n" +
                        "  li  x20,0\n" +
                        "  li  x21,0\n" +
                        "  li  x22,0\n" +
                        "  li  x23,0\n" +
                        "  li  x24,0\n" +
                        "  li  x25,0\n" +
                        "  li  x26,0\n" +
                        "  li  x27,0\n" +
                        "  li  x28,0\n" +
                        "  li  x29,0\n" +
                        "  li  x30,0\n" +
                        "  li  x31,0\n" +
                        "\n" +
                        "  ##! csrs (pseudo asm instruction) - set bit   \n" +
                        "  ##! csrrs - atomic read and set bit\n" +
                        "  ##! csrc (pseudo asm instruction) - clear bit \n" +
                        "  ##! csrrc - atomic read and clear bit\n" +
                        "\n" +
                        "  li t0, 0x00001800   # MPP[12:11] = 0x3 (Previous to machine mode)\n" +
                        "  csrc mstatus, t0    # run tests in user mode = 0, by clearing bits\n" +
                        "  li t0, 0x00000008   # Enable irq in machine and user modes after execution of xRET\n" +
                        "  csrs mstatus, t0    # enable interrupts in user mode\n" +
                        "  #li t0, MSTATUS_FS;\n" +
                        "  #csrs mstatus, t0    # enable FPU\n" +
                        "  #li t0, MSTATUS_XS;   \n" +
                        "  #csrs mstatus, t0    # enable accelerator\n" +
                        "\n" +
                        "  ##! init mtvec register (see https://github.com/riscv/riscv-test-env/blob/master/p/riscv_test.h)\n" +
                        "  la t0, interrupt_entry\n" +
                        "  csrw mtvec, t0\n" +
                        "  li t0, 0x00000800\n" +
                        "  csrs mie, t0       # Enable External irq (ftom PLIC) for M mode\n" +
                        "\n" +
                        "#if 0\n" +
                        "  ##! see https://github.com/riscv/riscv-tests/benchmarks/common\n" +
                        "  csrr t0, mstatus\n" +
                        "  li t1, MSTATUS_XS\n" +
                        "  and t1, t0, t1\n" +
                        "  sw t1, have_vec, t2\n" +
                        "\n" +
                        "  ## if that didn't stick, we don't have a FPU, so don't initialize it\n" +
                        "  li t1, MSTATUS_FS\n" +
                        "  and t1, t0, t1\n" +
                        "  beqz t1, 1f\n" +
                        "#endif\n" +
                        "\n" +
                        "# intialization when HW FPU enabled\n" +
                        "#ifdef FPU_ENABLED\n" +
                        "  fssr    x0\n" +
                        "  fmv.s.x f0, x0\n" +
                        "  fmv.s.x f1, x0\n" +
                        "  fmv.s.x f2, x0\n" +
                        "  fmv.s.x f3, x0\n" +
                        "  fmv.s.x f4, x0\n" +
                        "  fmv.s.x f5, x0\n" +
                        "  fmv.s.x f6, x0\n" +
                        "  fmv.s.x f7, x0\n" +
                        "  fmv.s.x f8, x0\n" +
                        "  fmv.s.x f9, x0\n" +
                        "  fmv.s.x f10,x0\n" +
                        "  fmv.s.x f11,x0\n" +
                        "  fmv.s.x f12,x0\n" +
                        "  fmv.s.x f13,x0\n" +
                        "  fmv.s.x f14,x0\n" +
                        "  fmv.s.x f15,x0\n" +
                        "  fmv.s.x f16,x0\n" +
                        "  fmv.s.x f17,x0\n" +
                        "  fmv.s.x f18,x0\n" +
                        "  fmv.s.x f19,x0\n" +
                        "  fmv.s.x f20,x0\n" +
                        "  fmv.s.x f21,x0\n" +
                        "  fmv.s.x f22,x0\n" +
                        "  fmv.s.x f23,x0\n" +
                        "  fmv.s.x f24,x0\n" +
                        "  fmv.s.x f25,x0\n" +
                        "  fmv.s.x f26,x0\n" +
                        "  fmv.s.x f27,x0\n" +
                        "  fmv.s.x f28,x0\n" +
                        "  fmv.s.x f29,x0\n" +
                        "  fmv.s.x f30,x0\n" +
                        "  fmv.s.x f31,x0\n" +
                        "#endif\n" +
                        "\n" +
                        "  ##! initialize global pointer (no need in it)\n" +
                        "  lui gp, 0x10000\n" +
                        "\n" +
                        "  ##! get core id\n" +
                        "  csrr a0, mhartid            # a0 <= MHARTID value\n" +
                        "\n" +
                        "#define SRAM_BASE_ADDR  0x10000000\n" +
                        "#define SRAM_SIZE_BYTES (1<<18)\n" +
                        "#define STACK_CORE1_BYTES 4096\n" +
                        "\n" +
                        "  li  sp, SRAM_BASE_ADDR+SRAM_SIZE_BYTES\n" +
                        "  li  a1, 1\n" +
                        "  beq a0, a1, sp_init_core1\n" +
                        "  li  a1, 0\n" +
                        "  beq a0, a1, sp_init_core0\n" +
                        "sp_init_core1:\n" +
                        "  j sp_init_coreall\n" +
                        "sp_init_core0:\n" +
                        "  li t0,-STACK_CORE1_BYTES\n" +
                        "  add sp, sp, t0\n" +
                        "sp_init_coreall:\n" +
                        "  add tp, zero, sp            # tp = sp + 0 (mov)\n" +
                        "  ## Use tp register to save/restore registers context on task switching\n" +
                        "  addi tp,tp,-256              # tp = tp - 256 = 0x1007ff00\n" +
                        "  addi sp, sp,-264\n" +
                        "\n" +
                        "  # copy image 64 KB\n" +
                        "  jal _init\n" +
                        "\n" +
                        "  ##! jump to entry point in SRAM = 0x10000000\n" +
                        "  ##!     'meps' - Machine Exception Program Coutner\n" +
                        "  li  t0, SRAM_BASE_ADDR\n" +
                        "  csrw mepc, t0\n" +
                        "  ##! @see riscv-priv-spec-1.7.pdf. 3.2.1\n" +
                        "  ##! After handling a trap, the ERET instruction is used to return to the privilege level at which the\n" +
                        "  ##! trap occurred. In addition to manipulating the privilege stack as described in Section 3.1.5, ERET\n" +
                        "  ##! sets the pc to the value stored in the Xepc register, where X is the privilege mode (S, H, or M) in\n" +
                        "  ##! which the ERET instruction was executed.\n" +
                        "  mret\n" +
                        "\n" +
                        "exception_entry:\n" +
                        "  fence\n" +
                        "  _save_context(tp)\n" +
                        "  jal exception_handler_c\n" +
                        "  _restore_context(tp)\n" +
                        "  mret\n" +
                        "\n" +
                        "interrupt_entry:\n" +
                        "  ##! module CSRFile rises io_fatc signal that is cause of the 'ptw.invalidate'.\n" +
                        "  fence\n" +
                        "  csrw mscratch, a0;\n" +
                        "\n" +
                        "  _save_context(tp)\n" +
                        "\n" +
                        "  ## @brief Call function :\n" +
                        "  ##       long handle_trap(long cause, long epc, long long regs[32])\n" +
                        "  ##             a0 = argument 1: cause\n" +
                        "  ##             a1 = argument 2: mepc\n" +
                        "  ##             a2 = argument 3: pointer on stack\n" +
                        "  ## @return     a0 New instruction pointer offset\n" +
                        "  csrr a0, mcause\n" +
                        "  csrr a1, mepc\n" +
                        "  sd a1,COOP_REG_TP(tp)\n" +
                        "  mv a2, sp\n" +
                        "  # !!! Cannot reset external pending bits only via IrqController (page 28)\n" +
                        "  li t0, 0x00000800\n" +
                        "  csrc mip, t0      #csrc pseudo asm instruction clear CSR bit.\n" +
                        "                    #[11] MEIP: machine pending external interrupt\n" +
                        "  jal interrupt_handler_c\n" +
                        "\n" +
                        "  # tp-offset in the context array is used to save mepc value. An it may be\n" +
                        "  # modified by isr handler during preemtive task switching.\n" +
                        "  ld a1,COOP_REG_TP(tp)\n" +
                        "  csrw mepc,a1\n" +
                        "  _restore_context(tp)\n" +
                        "  mret";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    } 
    
    private void write_encoding_h_file(String Project_Folder_File) {
        String data =   "// See LICENSE for license details.\n" +
                        "\n" +
                        "#ifndef RISCV_CSR_ENCODING_H\n" +
                        "#define RISCV_CSR_ENCODING_H\n" +
                        "\n" +
                        "/** Return address */\n" +
                        "#define COOP_REG_RA         0//(0*sizeof(uint64_t))\n" +
                        "/** Saved registers */\n" +
                        "#define COOP_REG_S0         8//(1*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S1         16//(2*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S2         24//(3*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S3         32//(4*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S4         40//(5*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S5         48//(6*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S6         56//(7*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S7         64//(8*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S8         72//(9*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S9         80//(10*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S10        88//(11*sizeof(uint64_t))\n" +
                        "#define COOP_REG_S11        96//(12*sizeof(uint64_t))\n" +
                        "/** Stack pointer */\n" +
                        "#define COOP_REG_SP         104//(13*sizeof(uint64_t))\n" +
                        "/** Thread pointer */\n" +
                        "#define COOP_REG_TP         112//(14*sizeof(uint64_t))\n" +
                        "/** Return values */\n" +
                        "#define COOP_REG_V0         120//(15*sizeof(uint64_t))\n" +
                        "#define COOP_REG_V1         128//(16*sizeof(uint64_t))\n" +
                        "/** Function Arguments */\n" +
                        "#define COOP_REG_A0         136//(17*sizeof(uint64_t))\n" +
                        "#define COOP_REG_A1         144//(18*sizeof(uint64_t))\n" +
                        "#define COOP_REG_A2         152//(19*sizeof(uint64_t))\n" +
                        "#define COOP_REG_A3         160//(20*sizeof(uint64_t))\n" +
                        "#define COOP_REG_A4         168//(21*sizeof(uint64_t))\n" +
                        "#define COOP_REG_A5         176//(22*sizeof(uint64_t))\n" +
                        "#define COOP_REG_A6         184//(23*sizeof(uint64_t))\n" +
                        "#define COOP_REG_A7         192//(24*sizeof(uint64_t))\n" +
                        "/** Temporary registers */\n" +
                        "#define COOP_REG_T0         200//(25*sizeof(uint64_t))\n" +
                        "#define COOP_REG_T1         208//(26*sizeof(uint64_t))\n" +
                        "#define COOP_REG_T2         216//(27*sizeof(uint64_t))\n" +
                        "#define COOP_REG_T3         224//(28*sizeof(uint64_t))\n" +
                        "#define COOP_REG_T4         232//(29*sizeof(uint64_t))\n" +
                        "/** Global pointer */\n" +
                        "#define COOP_REG_GP         240//(30*sizeof(uint64_t))\n" +
                        "\n" +
                        "#define _save_context(TO) \\\n" +
                        "  sd ra, COOP_REG_RA(TO); \\\n" +
                        "  sd s0, COOP_REG_S0(TO); \\\n" +
                        "  sd s1, COOP_REG_S1(TO); \\\n" +
                        "  sd s2, COOP_REG_S2(TO); \\\n" +
                        "  sd s3, COOP_REG_S3(TO); \\\n" +
                        "  sd s4, COOP_REG_S4(TO); \\\n" +
                        "  sd s5, COOP_REG_S5(TO); \\\n" +
                        "  sd s6, COOP_REG_S6(TO); \\\n" +
                        "  sd s7, COOP_REG_S7(TO); \\\n" +
                        "  sd s8, COOP_REG_S8(TO); \\\n" +
                        "  sd s9, COOP_REG_S9(TO); \\\n" +
                        "  sd s10, COOP_REG_S10(TO); \\\n" +
                        "  sd s11, COOP_REG_S11(TO); \\\n" +
                        "  sd sp, COOP_REG_SP(TO); \\\n" +
                        "  sd x16, COOP_REG_V0(TO); \\\n" +
                        "  sd x17, COOP_REG_V1(TO); \\\n" +
                        "  sd a0, COOP_REG_A0(TO); \\\n" +
                        "  sd a1, COOP_REG_A1(TO); \\\n" +
                        "  sd a2, COOP_REG_A2(TO); \\\n" +
                        "  sd a3, COOP_REG_A3(TO); \\\n" +
                        "  sd a4, COOP_REG_A4(TO); \\\n" +
                        "  sd a5, COOP_REG_A5(TO); \\\n" +
                        "  sd a6, COOP_REG_A6(TO); \\\n" +
                        "  sd a7, COOP_REG_A7(TO); \\\n" +
                        "  sd t0, COOP_REG_T0(TO); \\\n" +
                        "  sd t1, COOP_REG_T1(TO); \\\n" +
                        "  sd t2, COOP_REG_T2(TO); \\\n" +
                        "  sd t3, COOP_REG_T3(TO); \\\n" +
                        "  sd t4, COOP_REG_T4(TO); \\\n" +
                        "  sd gp, COOP_REG_GP(TO);\n" +
                        "\n" +
                        "\n" +
                        "#define _restore_context(FROM) \\\n" +
                        "  ld ra, COOP_REG_RA(FROM); \\\n" +
                        "  ld s0, COOP_REG_S0(FROM); \\\n" +
                        "  ld s1, COOP_REG_S1(FROM); \\\n" +
                        "  ld s2, COOP_REG_S2(FROM); \\\n" +
                        "  ld s3, COOP_REG_S3(FROM); \\\n" +
                        "  ld s4, COOP_REG_S4(FROM); \\\n" +
                        "  ld s5, COOP_REG_S5(FROM); \\\n" +
                        "  ld s6, COOP_REG_S6(FROM); \\\n" +
                        "  ld s7, COOP_REG_S7(FROM); \\\n" +
                        "  ld s8, COOP_REG_S8(FROM); \\\n" +
                        "  ld s9, COOP_REG_S9(FROM); \\\n" +
                        "  ld s10, COOP_REG_S10(FROM); \\\n" +
                        "  ld s11, COOP_REG_S11(FROM); \\\n" +
                        "  ld sp, COOP_REG_SP(FROM); \\\n" +
                        "  ld x16, COOP_REG_V0(FROM); \\\n" +
                        "  ld x17, COOP_REG_V1(FROM); \\\n" +
                        "  ld a0, COOP_REG_A0(FROM); \\\n" +
                        "  ld a1, COOP_REG_A1(FROM); \\\n" +
                        "  ld a2, COOP_REG_A2(FROM); \\\n" +
                        "  ld a3, COOP_REG_A3(FROM); \\\n" +
                        "  ld a4, COOP_REG_A4(FROM); \\\n" +
                        "  ld a5, COOP_REG_A5(FROM); \\\n" +
                        "  ld a6, COOP_REG_A6(FROM); \\\n" +
                        "  ld a7, COOP_REG_A7(FROM); \\\n" +
                        "  ld t0, COOP_REG_T0(FROM); \\\n" +
                        "  ld t1, COOP_REG_T1(FROM); \\\n" +
                        "  ld t2, COOP_REG_T2(FROM); \\\n" +
                        "  ld t3, COOP_REG_T3(FROM); \\\n" +
                        "  ld t4, COOP_REG_T4(FROM); \\\n" +
                        "  ld gp, COOP_REG_GP(FROM);\n" +
                        "\n" +
                        "\n" +
                        "#define MSTATUS_IE          0x00000001\n" +
                        "#define MSTATUS_PRV         0x00000006\n" +
                        "#define MSTATUS_IE1         0x00000008\n" +
                        "#define MSTATUS_PRV1        0x00000030\n" +
                        "#define MSTATUS_IE2         0x00000040\n" +
                        "#define MSTATUS_PRV2        0x00000180\n" +
                        "#define MSTATUS_IE3         0x00000200\n" +
                        "#define MSTATUS_PRV3        0x00000C00\n" +
                        "#define MSTATUS_FS          0x00003000\n" +
                        "#define MSTATUS_XS          0x0000C000\n" +
                        "#define MSTATUS_MPRV        0x00010000\n" +
                        "#define MSTATUS_VM          0x003E0000\n" +
                        "#define MSTATUS32_SD        0x80000000\n" +
                        "#define MSTATUS64_SD        0x8000000000000000\n" +
                        "\n" +
                        "\n" +
                        "#endif";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_axi_maps_h_file(String Project_Folder_File) {
        String data =   "/******************************************************************************\n" +
                        " * @file\n" +
                        " * @copyright Copyright 2015 GNSS Sensor Ltd. All right reserved.\n" +
                        " * @author    Sergey Khabarov - sergeykhbr@gmail.com\n" +
                        " * @brief     AXI4 device mapping\n" +
                        " * @details   Don't use this address directly use Kernel interface to get\n" +
                        " *            detected device interface.\n" +
                        "******************************************************************************/\n" +
                        "\n" +
                        "#ifndef __AXI_MAPS_H__\n" +
                        "#define __AXI_MAPS_H__\n" +
                        "\n" +
                        "#include <inttypes.h>\n" +
                        "#include \"axi_const.h\"\n" +
                        "#include \"maps/map_gptimers.h\"\n" +
                        "#include \"maps/map_uart.h\"\n" +
                        "#include \"maps/map_irqctrl.h\"\n" +
                        "#include \"maps/map_i_o_peripheral.h\"\n" +
                        "#include \"maps/map_timer_hw.h\"\n" +
                        "#include \"maps/map_pwm_hw.h\"\n" +
                        "#include \"maps/map_time_measurement.h\"\n" +
                        "\n" +
                        "#define ADDR_NASTI_SLAVE_FWIMAGE        0x00100000\n" +
                        "#define ADDR_NASTI_SLAVE_SRAM           0x10000000\n" +
                        "#define ADDR_NASTI_SLAVE_GPIO           0x80000000\n" +
                        "#define ADDR_NASTI_SLAVE_UART1          0x80001000\n" +
                        "#define ADDR_NASTI_SLAVE_IRQCTRL        0x80002000\n" +
                        "#define ADDR_NASTI_SLAVE_GPTIMERS       0x80003000\n" +
                        "#define ADDR_NASTI_SLAVE_MEASUREMENT    0x80004000\n" +
                        "#define ADDR_NASTI_SLAVE_TON0           0x80005000\n" +
                        "#define ADDR_NASTI_SLAVE_PWM0           0x80006000\n" +
                        "\n" +
                        "\n" +
                        "// Interrupt pins assignemts:\n" +
                        "#define CFG_IRQ_UNUSED      0\n" +
                        "#define CFG_IRQ_UART1       1\n" +
                        "#define CFG_IRQ_GPTIMERS    2\n" +
                        "#define CFG_IRQ_TOTAL       3\n" +
                        "\n" +
                        "#define SYS_HZ              "+Data.CPU_RV64_Freq+"\n" +
                        "#define PWM_HZ              "+Data.PWM_RV64_HW_Freq+"\n" +
                        "#endif  // __AXI_MAPS_H__";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_axi_const_h_file(String Project_Folder_File) {
        String data =   "/******************************************************************************\n" +
                        " * @file\n" +
                        " * @copyright Copyright 2015 GNSS Sensor Ltd. All right reserved.\n" +
                        " * @author    Sergey Khabarov - sergeykhbr@gmail.com\n" +
                        " * @brief     AXI4 system bus constants definition\n" +
                        "******************************************************************************/\n" +
                        "\n" +
                        "#ifndef __AXI_CONST_H__\n" +
                        "#define __AXI_CONST_H__\n" +
                        "\n" +
                        "#include <inttypes.h>\n" +
                        "\n" +
                        "typedef uint64_t adr_type;\n" +
                        "\n" +
                        "static const int AXI4_SYSTEM_CLOCK = "+Data.CPU_RV64_Freq+";\n" +
                        "\n" +
                        "#define VENDOR_GNSSSENSOR        0x00F1\n" +
                        "\n" +
                        "#define GNSSSENSOR_EMPTY         0x5577     /// Dummy device\n" +
                        "#define GNSSSENSOR_ENGINE_STUB   0x0068     /// GNSS Engine stub\n" +
                        "#define GNSSSENSOR_FSE_V2_GPS    0x0069     /// Fast Search Engines Device ID provided by gnsslib\n" +
                        "#define GNSSSENSOR_FSE_V2_GLO    0x006A     ///\n" +
                        "#define GNSSSENSOR_FSE_V2_GAL    0x006C     ///\n" +
                        "#define GNSSSENSOR_BOOTROM       0x0071     /// Boot ROM Device ID\n" +
                        "#define GNSSSENSOR_FWIMAGE       0x0072     /// FW ROM image Device ID\n" +
                        "#define GNSSSENSOR_SRAM          0x0073     /// Internal SRAM block Device ID\n" +
                        "#define GNSSSENSOR_PNP           0x0074     /// Configuration Registers Module Device ID provided by gnsslib\n" +
                        "#define GNSSSENSOR_SPI_FLASH     0x0075     /// SD-card controller Device ID provided by gnsslib\n" +
                        "#define GNSSSENSOR_GPIO          0x0076     /// General purpose IOs Device ID provided by gnsslib\n" +
                        "#define GNSSSENSOR_RF_CONTROL    0x0077     /// RF front-end controller Device ID provided by gnsslib\n" +
                        "#define GNSSSENSOR_ENGINE        0x0078     /// GNSS Engine Device ID provided by gnsslib\n" +
                        "#define GNSSSENSOR_UART          0x007a     /// rs-232 UART Device ID\n" +
                        "#define GNSSSENSOR_ACCELEROMETER 0x007b     /// Accelerometer Device ID provided by gnsslib\n" +
                        "#define GNSSSENSOR_GYROSCOPE     0x007c     /// Gyroscope Device ID provided by gnsslib\n" +
                        "#define GNSSSENSOR_IRQCTRL       0x007d     /// Interrupt controller\n" +
                        "#define GNSSSENSOR_ETHMAC        0x007f\n" +
                        "#define GNSSSENSOR_DSU           0x0080\n" +
                        "#define GNSSSENSOR_GPTIMERS      0x0081\n" +
                        "#define GNSSSENSOR_RECORDER      0x0082\n" +
                        "#define GNSSSENSOR_OTP_8KB       0x0083\n" +
                        "\n" +
                        "#define CFG_NASTI_MASTER_CACHED     0\n" +
                        "#define CFG_NASTI_MASTER_UNCACHED   1\n" +
                        "#define CFG_NASTI_MASTER_ETHMAC     2\n" +
                        "#define CFG_NASTI_MASTER_TOTAL      3\n" +
                        "\n" +
                        "#define MST_DID_EMPTY             0x7755\n" +
                        "#define SLV_DID_EMPTY             0x5577\n" +
                        "\n" +
                        "// Masters IDs\n" +
                        "#define RISCV_CACHED_TILELINK     0x0500\n" +
                        "#define RISCV_UNCACHED_TILELINK   0x0501\n" +
                        "#define GAISLER_ETH_MAC_MASTER    0x0502\n" +
                        "#define GAISLER_ETH_EDCL_MASTER   0x0503\n" +
                        "#define RISCV_RIVER_CPU           0x0505\n" +
                        "#define GNSSSENSOR_UART_TAP       0x050a\n" +
                        "#define GNSSSENSOR_JTAG_TAP       0x050b\n" +
                        "\n" +
                        "#define PNP_CFG_TYPE_INVALID      0\n" +
                        "#define PNP_CFG_TYPE_MASTER       1\n" +
                        "#define PNP_CFG_TYPE_SLAVE        2\n" +
                        "\n" +
                        "\n" +
                        "#define TECH_INFERRED       0\n" +
                        "#define TECH_VIRTEX6        36\n" +
                        "#define TECH_KINTEX7        49\n" +
                        "\n" +
                        "#ifdef WIN32\n" +
                        "#ifdef __cplusplus\n" +
                        "extern \"C\" {\n" +
                        "#endif\n" +
                        "void WRITE32(const volatile uint32_t *adr, uint32_t val);\n" +
                        "void WRITE64(const volatile uint64_t *adr, uint64_t val);\n" +
                        "uint8_t READ8(const volatile uint8_t *adr);\n" +
                        "uint16_t READ16(const volatile uint16_t *adr);\n" +
                        "uint32_t READ32(const volatile uint32_t *adr);\n" +
                        "uint64_t READ64(const volatile uint64_t *adr);\n" +
                        "#ifdef __cplusplus\n" +
                        "}\n" +
                        "#endif\n" +
                        "\n" +
                        "#else\n" +
                        "\n" +
                        "static inline void WRITE32(const volatile uint32_t *adr, uint32_t val) {\n" +
                        "    *((volatile uint32_t *)adr) = val;\n" +
                        "}\n" +
                        "\n" +
                        "static inline void WRITE64(const volatile uint64_t *adr, uint64_t val) {\n" +
                        "    *((volatile uint64_t *)adr) = val;\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint8_t READ8(const volatile uint8_t *adr) {\n" +
                        "    return adr[0];\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint16_t READ16(const volatile uint16_t *adr) {\n" +
                        "    return adr[0];\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint32_t READ32(const volatile uint32_t *adr) {\n" +
                        "    return adr[0];\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint64_t READ64(const volatile uint64_t *adr) {\n" +
                        "    return adr[0];\n" +
                        "}\n" +
                        "\n" +
                        "#endif              \n" +
                        "\n" +
                        "#endif  // __AXI_CONST_H__";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_map_i_o_peripheral_h_file(String Project_Folder_File) {
        String data =   "/******************************************************************************\n" +
                        " * @file\n" +
                        " * @copyright Copyright 2019 Hossameldin Eassa All right reserved.\n" +
                        " * @author    Hossameldin Eassa - hossameassa@gmail.com\n" +
                        " * @brief     DIO memory map.\n" +
                        "******************************************************************************/\n" +
                        "\n" +
                        "#ifndef PAEE_I_O_PERIPHERAL_H\n" +
                        "#define PAEE_I_O_PERIPHERAL_H\n" +
                        "\n" +
                        "#include <stdbool.h>\n" +
                        "#include <stdint.h>\n" +
                        "\n" +
                        "#define GPIO_IN		 	0x000    // 0x00\n" +
                        "#define SW				0x048    // 0x12\n" +
                        "#define KEY				0x070    // 0x1c\n" +
                        "\n" +
                        "#define GPIO_OUT		0x100    // 0x40\n" +
                        "#define LEDR			0x148    // 0x52\n" +
                        "#define LEDG			0x170    // 0x5c\n" +
                        "\n" +
                        "#define RWD				0x1fc    // 0x7f\n" +
                        "\n" +
                        "#define LED_ON			0x001\n" +
                        "#define LED_OFF			0x000\n" +
                        "typedef struct io_per\n" +
                        "{\n" +
                        "	volatile uint32_t * registers;\n" +
                        "} io_per;\n" +
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
                        "#endif";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_map_gptimers_h_file(String Project_Folder_File) {
        String data =   "/******************************************************************************\n" +
                        " * @file\n" +
                        " * @copyright Copyright 2017 GNSS Sensor Ltd. All right reserved.\n" +
                        " * @author    Sergey Khabarov - sergeykhbr@gmail.com\n" +
                        " * @brief     General Purpose Timers register mapping definition.\n" +
                        "******************************************************************************/\n" +
                        "#ifndef __MAP_GPTIMERS_H__\n" +
                        "#define __MAP_GPTIMERS_H__\n" +
                        "\n" +
                        "#include <inttypes.h>\n" +
                        "\n" +
                        "#define TIMER_CONTROL_ENT_DISIRQ_NOOV	0x01	// 0b001\n" +
                        "#define TIMER_CONTROL_ENT_ENIRQ_NOOV	0x03	// 0b011\n" +
                        "#define TIMER_CONTROL_DIST_ENIRQ_NOOV	0x02	// 0b010\n" +
                        "#define TIMER_CONTROL_DIST_DISIRQ_NOOV	0x00	// 0b000\n" +
                        "#define TIMER_CONTROL_ENT_DISIRQ_OV		0x05	// 0b101\n" +
                        "#define TIMER_CONTROL_ENT_ENIRQ_OV		0x07	// 0b111\n" +
                        "#define TIMER_CONTROL_DIST_ENIRQ_OV		0x06	// 0b110\n" +
                        "#define TIMER_CONTROL_DIST_DISIRQ_OV	0x04	// 0b100\n" +
                        "\n" +
                        "#define TIMER_ENABLED	1\n" +
                        "#define TIMER_DISABLED	0\n" +
                        "\n" +
                        "typedef struct gptimer_type {\n" +
                        "    volatile uint32_t control;\n" +
                        "    volatile uint32_t rsv1;\n" +
                        "    volatile uint64_t cur_value;\n" +
                        "    volatile uint64_t init_value;\n" +
                        "} gptimer_type;\n" +
                        "\n" +
                        "\n" +
                        "typedef struct gptimers_map {\n" +
                        "        uint64_t highcnt;\n" +
                        "        uint32_t pending;\n" +
                        "        uint32_t rsvr[13];\n" +
                        "        gptimer_type timer[2];\n" +
                        "} gptimers_map;\n" +
                        "\n" +
                        "#endif  // __MAP_GPTIMERS_H__";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_map_uart_h_file(String Project_Folder_File) {
        String data =   "/******************************************************************************\n" +
                        " * @file\n" +
                        " * @copyright Copyright 2015 GNSS Sensor Ltd. All right reserved.\n" +
                        " * @author    Sergey Khabarov - sergeykhbr@gmail.com\n" +
                        " * @brief     UART register mapping definition.\n" +
                        "******************************************************************************/\n" +
                        "\n" +
                        "#ifndef __MAP_UART_H__\n" +
                        "#define __MAP_UART_H__\n" +
                        "\n" +
                        "#include <inttypes.h>\n" +
                        "\n" +
                        "static const uint32_t UART_STATUS_TX_FULL     = 0x00000001;\n" +
                        "static const uint32_t UART_STATUS_TX_EMPTY    = 0x00000002;\n" +
                        "static const uint32_t UART_STATUS_RX_FULL     = 0x00000010;\n" +
                        "static const uint32_t UART_STATUS_RX_EMPTY    = 0x00000020;\n" +
                        "static const uint32_t UART_STATUS_ERR_PARITY  = 0x00000100;\n" +
                        "static const uint32_t UART_STATUS_ERR_STOPBIT = 0x00000200;\n" +
                        "\n" +
                        "static const uint32_t UART_CONTROL_TXIRQ_ENA  = 0x00004000;\n" +
                        "\n" +
                        "typedef struct uart_map {\n" +
                        "    volatile uint32_t status;\n" +
                        "    volatile uint32_t scaler;\n" +
                        "    volatile uint32_t fwcpuid;   // use as cpu marker who's owning by uart\n" +
                        "    uint32_t rsrv[1];\n" +
                        "    volatile uint32_t data;\n" +
                        "} uart_map;\n" +
                        "\n" +
                        "#endif  // __MAP_UART_H__";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_map_pwm_hw_h_file(String Project_Folder_File) {
        String data =   "/******************************************************************************\n" +
                        " * @file\n" +
                        " * @copyright Copyright 2019 Hossameldin Eassa All right reserved.\n" +
                        " * @author    Hossameldin Eassa - hossameassa@gmail.com\n" +
                        " * @brief     PWM memory map.\n" +
                        "******************************************************************************/\n" +
                        "\n" +
                        "#ifndef LIBSOC_PWM_HW_H\n" +
                        "#define LIBSOC_PWM_HW_H\n" +
                        "\n" +
                        "#include <stdint.h>\n" +
                        "\n" +
                        "// PWM register offsets:\n" +
                        "#define Q_PWM				0x00\n" +
                        "#define Frequency_Address	0x00\n" +
                        "#define Duty_Cycle_Address	0x04\n" +
                        "\n" +
                        "typedef struct pwm_hw\n" +
                        "{\n" +
                        "	volatile uint64_t * registers;\n" +
                        "} pwm_hw;\n" +
                        "\n" +
                        "static inline void pwm_hw_send_frequency_duty_cycle(struct pwm_hw * module, uint32_t frequency,  uint32_t duty_cycle)\n" +
                        "{\n" +
                        "	uint64_t ret = ((uint64_t)duty_cycle<<32);\n" +
                        "	module->registers[Frequency_Address] = (uint64_t) (ret | frequency);\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint32_t pwm_hw_recieve_Q(struct pwm_hw  * module)\n" +
                        "{\n" +
                        "	return (uint32_t) module->registers[Q_PWM];\n" +
                        "}\n" +
                        "\n" +
                        "#endif";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_map_time_measurement_h_file(String Project_Folder_File) {
        String data =   "/******************************************************************************\n" +
                        " * @file\n" +
                        " * @copyright Copyright 2019 Hossameldin Eassa All right reserved.\n" +
                        " * @author    Hossameldin Eassa - hossameassa@gmail.com\n" +
                        " * @brief     Time Measurement memory map.\n" +
                        "******************************************************************************/\n" +
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
                        "#define MICRO_NANO_A	0x08 // Address of writing Micro Or Nano Measurements\n" +
                        "#define MICRO			0x36\n" +
                        "#define NANO			0x42\n" +
                        "\n" +
                        "#define READ_TIME_A		0x08\n" +
                        "\n" +
                        "\n" +
                        "typedef struct time_measurement\n" +
                        "{\n" +
                        "	volatile uint32_t * registers;\n" +
                        "} time_measurement;\n" +
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
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_map_timer_hw_h_file(String Project_Folder_File) {
        String data =   "/******************************************************************************\n" +
                        " * @file\n" +
                        " * @copyright Copyright 2019 Hossameldin Eassa All right reserved.\n" +
                        " * @author    Hossameldin Eassa - hossameassa@gmail.com\n" +
                        " * @brief     Timer HW memory map.\n" +
                        "******************************************************************************/\n" +
                        "\n" +
                        "#ifndef LIBSOC_TIMER_HW_H\n" +
                        "#define LIBSOC_TIMER_HW_H\n" +
                        "\n" +
                        "#include <stdint.h>\n" +
                        "\n" +
                        "// Timer register offsets:\n" +
                        "#define Elapsed_Time	0x00\n" +
                        "#define Q_TON         	0x08\n" +
                        "#define Preset_Time		0x00\n" +
                        "#define IN_TON        	0x08\n" +
                        "\n" +
                        "typedef struct timer_hw\n" +
                        "{\n" +
                        "	volatile uint64_t * registers;\n" +
                        "} timer_hw;\n" +
                        "\n" +
                        "static inline void timer_hw_send_in(struct timer_hw * module, uint32_t in_data)\n" +
                        "{\n" +
                        "	module->registers[Q_TON >> 3] = in_data;\n" +
                        "}\n" +
                        "\n" +
                        "static inline void timer_hw_send_preset_time(struct timer_hw * module, uint64_t preset_time)\n" +
                        "{\n" +
                        "	module->registers[Preset_Time >> 3] = preset_time;\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint64_t timer_hw_recieve_elapsed_time(struct timer_hw  * module)\n" +
                        "{\n" +
                        "	return module->registers[Elapsed_Time >> 3];;\n" +
                        "}\n" +
                        "\n" +
                        "static inline uint32_t timer_hw_recieve_Q(struct timer_hw  * module)\n" +
                        "{\n" +
                        "	return (uint32_t) module->registers[Q_TON >> 3];\n" +
                        "}\n" +
                        "\n" +
                        "#endif";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_map_map_irqctrl_h_file(String Project_Folder_File) {
        String data =   "/******************************************************************************\n" +
                        " * @file\n" +
                        " * @copyright Copyright 2015 GNSS Sensor Ltd. All right reserved.\n" +
                        " * @author    Sergey Khabarov - sergeykhbr@gmail.com\n" +
                        " * @brief     Interrupt Controller register mapping definition.\n" +
                        "******************************************************************************/\n" +
                        "\n" +
                        "#ifndef __MAP_IRQCTRL_H__\n" +
                        "#define __MAP_IRQCTRL_H__\n" +
                        "\n" +
                        "#include <inttypes.h>\n" +
                        "typedef void (*IRQ_TABLE_HANDLER)(int idx, void *arg);\n" +
                        "\n" +
                        "typedef struct irqctrl_map {\n" +
                        "    volatile uint32_t irq_mask;     // 0x00: [RW] 1=disable; 0=enable\n" +
                        "    volatile uint32_t irq_pending;  // 0x04: [RW]\n" +
                        "    volatile uint32_t irq_clear;    // 0x08: [WO]\n" +
                        "    volatile uint32_t irq_rise;     // 0x0C: [WO]\n" +
                        "    volatile uint64_t isr_table;    // 0x10: [RW]\n" +
                        "    volatile uint64_t dbg_cause;    // 0x18:\n" +
                        "    volatile uint64_t dbg_epc;      // 0x20:\n" +
                        "    volatile uint32_t irq_lock;     // 0x28: lock/unlock all interrupts\n" +
                        "    volatile uint32_t irq_cause_idx;// 0x2c:\n" +
                        "} irqctrl_map;\n" +
                        "\n" +
                        "#endif  // __MAP_IRQCTRL_H__";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_elf2rawx_makefile_file(String Project_Folder_File) {
        String data =   "include "+Data.Project_Folder.getPath()+"/"+c_files+"/elf2rawx/makefiles/util.mak\n" +
                        "\n" +
                        "CC=gcc\n" +
                        "CFLAGS= -c -g -O2\n" +
                        "LDFLAGS=\n" +
                        "LIBS=-lstdc++\n" +
                        "\n" +
                        "SOURCES = \\\n" +
                        "	attribute.cpp \\\n" +
                        "	autobuffer.cpp \\\n" +
                        "	elfreader.cpp \\\n" +
                        "	main.cpp\n" +
                        "OBJECTS = $(SOURCES:.cpp=.o)\n" +
                        "EXECUTABLE = elf2rawx\n" +
                        "SRC_PATH = \\\n" +
                        "	"+Data.Project_Folder.getPath()+"/"+c_files+"/elf2rawx/src \\\n" +
                        "	"+Data.Project_Folder.getPath()+"/"+c_files+"/elf2rawx/src/common\n" +
                        "\n" +
                        "VPATH = $(SRC_PATH)\n" +
                        "\n" +
                        "OBJ_DIR = "+Data.Project_Folder.getPath()+"/"+c_files+"/elf2rawx/obj\n" +
                        "ELF_DIR = "+Data.Project_Folder.getPath()+"/"+c_files+"/elf2rawx/elf\n" +
                        "\n" +
                        ".PHONY: $(EXECUTABLE)\n" +
                        "\n" +
                        "all: $(EXECUTABLE)\n" +
                        "\n" +
                        "$(EXECUTABLE): $(OBJECTS)\n" +
                        "	$(MKDIR) $(ELF_DIR)\n" +
                        "	$(CC) $(LDFLAGS) $(addprefix $(OBJ_DIR)/,$(OBJECTS)) -o $(addprefix $(ELF_DIR)/,$@) -lstdc++\n" +
                        "\n" +
                        "#.cpp.o:\n" +
                        "%.o: %.cpp\n" +
                        "	$(MKDIR) $(OBJ_DIR)\n" +
                        "	$(CC) $(CFLAGS) $< -o $(addprefix $(OBJ_DIR)/,$@)";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_elf2rawx_main_cpp_file(String Project_Folder_File) {
        String data =   "#include <stdlib.h>\n" +
                        "#include <stdio.h>\n" +
                        "#include <fstream>\n" +
                        "#include <cstring>\n" +
                        "#include \"elfreader.h\"\n" +
                        "\n" +
                        "void printHelp() {\n" +
                        "    printf(\"This tool is a property of GNSS Sensor Limited.\\n\");\n" +
                        "    printf(\"Any information maybe requested at sergeykhbr@gmail.com\\n\\n\");\n" +
                        "    printf(\"Use the following arguments:\\n\");\n" +
                        "    printf(\"    -r    generate raw image file (default)\\n\");\n" +
                        "    printf(\"    -h    generate ROM array file in HEX format\\n\");\n" +
                        "    printf(\"    -f    define fixed image size in Bytes, otherwise \"\n" +
                        "           \"the size will be computed\\n\");\n" +
                        "    printf(\"    -l    bytes per line (with -h only). Default 16 bytes/128 \"\n" +
                        "           \"bits.\\n\");\n" +
                        "    printf(\"    -o    output file name1\\n\");\n" +
                        "    printf(\"    -o    output file name0 <optional>\\n\");\n" +
                        "    printf(\"Example\\n\");\n" +
                        "    printf(\"    elf2rawx input_file_name -h -f 192168 -l 8 -o \"\n" +
                        "           \"output_file_name\\n\");\n" +
                        "}\n" +
                        "\n" +
                        "int main(int argc, char* argv[]) {\n" +
                        "    if (argc < 4) {\n" +
                        "        printHelp();\n" +
                        "        return 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    enum EOutputFormat {Format_RAW_IMAGE, Format_ROMHEX};\n" +
                        "    EOutputFormat outfmt = Format_RAW_IMAGE;\n" +
                        "    int iFixedSizeBytes = 0;\n" +
                        "    int iBytesPerLine = 16;\n" +
                        "    int infile_index = 1;\n" +
                        "    AttributeType outFiles(Attr_List);\n" +
                        "    for (int i=1; i<argc; i++) {\n" +
                        "        if (strcmp(argv[i], \"-r\") == 0) {         // generate raw image file\n" +
                        "            outfmt = Format_RAW_IMAGE;\n" +
                        "        } else if (strcmp(argv[i], \"-h\") == 0) {  // generate rom hex file\n" +
                        "            outfmt = Format_ROMHEX;\n" +
                        "        } else if (strcmp(argv[i], \"-f\") == 0) {  // fixed size in bytes\n" +
                        "            iFixedSizeBytes = strtol(argv[++i], NULL, 0);\n" +
                        "        } else if (strcmp(argv[i], \"-l\") == 0) {  // bytes per hex-line\n" +
                        "            iBytesPerLine = strtol(argv[++i], NULL, 0);\n" +
                        "        } else if (strcmp(argv[i], \"-o\") == 0) {  // output file name\n" +
                        "            AttributeType filename;\n" +
                        "            filename.make_string(argv[++i]);\n" +
                        "            outFiles.add_to_list(&filename);\n" +
                        "        } else {\n" +
                        "            infile_index = i;\n" +
                        "        }\n" +
                        "    };\n" +
                        "\n" +
                        "    std::string arg1(argv[infile_index]);\n" +
                        "    std::string in = std::string(arg1.begin(), arg1.end());\n" +
                        "\n" +
                        "    ElfReader elf(in.c_str());\n" +
                        "    if (elf.loadableSectionTotal() == 0) {\n" +
                        "        printf(\"elf2rawx error: can't load file %s\\n\", in.c_str());\n" +
                        "        return 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    switch (outfmt) {\n" +
                        "    case Format_RAW_IMAGE:\n" +
                        "        elf.writeRawImage(&outFiles, iFixedSizeBytes);\n" +
                        "        break;\n" +
                        "    case Format_ROMHEX:\n" +
                        "        elf.writeRomHexArray(&outFiles,\n" +
                        "                             iBytesPerLine, iFixedSizeBytes);\n" +
                        "        break;\n" +
                        "    default:\n" +
                        "        printHelp();\n" +
                        "    }\n" +
                        "    return 0;\n" +
                        "}";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_elfreader_cpp_file(String Project_Folder_File) {
        String data =   "#include \"elfreader.h\"\n" +
                        "#include <fstream>\n" +
                        "#include <cstring>\n" +
                        "\n" +
                        "ElfReader::ElfReader(const char *filename) {\n" +
                        "    image_ = NULL;\n" +
                        "    sectionNames_ = NULL;\n" +
                        "    symbolList_.make_list(0);\n" +
                        "    loadSectionList_.make_list(0);\n" +
                        "    sourceProc_.make_string(\"\");\n" +
                        "\n" +
                        "\n" +
                        "    FILE *fp = fopen(filename, \"rb\");\n" +
                        "    if (!fp) {\n" +
                        "        printf(\"elf2rawx error: File '%s' not found\\n\", filename);\n" +
                        "        return ;\n" +
                        "    }\n" +
                        "    fseek(fp, 0, SEEK_END);\n" +
                        "    int sz = ftell(fp);\n" +
                        "    rewind(fp);\n" +
                        "\n" +
                        "    if (image_) {\n" +
                        "        delete image_;\n" +
                        "        sectionNames_ = NULL;\n" +
                        "        sourceProc_.make_list(0);\n" +
                        "        symbolList_.make_list(0);\n" +
                        "        loadSectionList_.make_list(0);\n" +
                        "    }\n" +
                        "    image_ = new uint8_t[sz];\n" +
                        "    fread(image_, 1, sz, fp);\n" +
                        "\n" +
                        "    if (readElfHeader() != 0) {\n" +
                        "        fclose(fp);\n" +
                        "        return;\n" +
                        "    }\n" +
                        "\n" +
                        "    symbolNames_ = 0;\n" +
                        "    lastLoadAdr_ = header_->get_entry();\n" +
                        "    if (!header_->get_shoff()) {\n" +
                        "        fclose(fp);\n" +
                        "        return;\n" +
                        "    }\n" +
                        "\n" +
                        "    sh_tbl_ = new SectionHeaderType *[header_->get_shnum()];\n" +
                        "\n" +
                        "    /** Search .shstrtab section */\n" +
                        "    uint8_t *psh = &image_[header_->get_shoff()];\n" +
                        "    for (int i = 0; i < header_->get_shnum(); i++) {\n" +
                        "        sh_tbl_[i] = new SectionHeaderType(psh, header_);\n" +
                        "\n" +
                        "        sectionNames_ = reinterpret_cast<char *>(&image_[sh_tbl_[i]->get_offset()]);\n" +
                        "        if (sh_tbl_[i]->get_type() == SHT_STRTAB && \n" +
                        "            strcmp(sectionNames_ + sh_tbl_[i]->get_name(), \".shstrtab\") != 0) {\n" +
                        "            sectionNames_ = NULL;\n" +
                        "        }\n" +
                        "\n" +
                        "        if (header_->isElf32()) {\n" +
                        "            psh += sizeof(Elf32_Shdr);\n" +
                        "        } else {\n" +
                        "            psh += sizeof(Elf64_Shdr);\n" +
                        "        }\n" +
                        "    }\n" +
                        "    if (!sectionNames_) {\n" +
                        "        printf(\"elf2rawx error: section .shstrtab not found.\\n\");\n" +
                        "    }\n" +
                        "\n" +
                        "    /** Search \".strtab\" section with Debug symbols */\n" +
                        "    SectionHeaderType *sh;\n" +
                        "    for (int i = 0; i < header_->get_shnum(); i++) {\n" +
                        "        sh = sh_tbl_[i];\n" +
                        "        if (sectionNames_ == NULL || sh->get_type() != SHT_STRTAB) {\n" +
                        "            continue;\n" +
                        "        }\n" +
                        "        if (strcmp(sectionNames_ + sh->get_name(), \".strtab\")) {\n" +
                        "            continue;\n" +
                        "        }\n" +
                        "        /** \n" +
                        "            * This section holds strings, most commonly the strings that\n" +
                        "            * represent the names associated with symbol table entries. \n" +
                        "            * If the file has a loadable segment that includes the symbol\n" +
                        "            * string table, the section's attributes will include the\n" +
                        "            * SHF_ALLOC bit; otherwise, that bit will be turned off.\n" +
                        "            */\n" +
                        "        symbolNames_ = reinterpret_cast<char *>(&image_[sh->get_offset()]);\n" +
                        "    }\n" +
                        "    if (!symbolNames_) {\n" +
                        "        printf(\"elf2rawx error: section .strtab not found. No debug symbols.\\n\");\n" +
                        "    }\n" +
                        "\n" +
                        "    /** Direct loading via tap interface: */\n" +
                        "    int bytes_loaded = loadSections();\n" +
                        "    printf(\"elf2rawx: Loaded: %d B\\n\", bytes_loaded);\n" +
                        "\n" +
                        "    if (header_->get_phoff()) {\n" +
                        "        //readProgramHeader();\n" +
                        "    }\n" +
                        "\n" +
                        "    fclose(fp);\n" +
                        "}\n" +
                        "\n" +
                        "int ElfReader::readElfHeader() {\n" +
                        "    header_ = new ElfHeaderType(image_);\n" +
                        "    if (header_->isElf()) {\n" +
                        "        return 0;\n" +
                        "    }\n" +
                        "    printf(\"elf2rawx error: File format is not ELF\\n\");\n" +
                        "    return -1;\n" +
                        "}\n" +
                        "\n" +
                        "int ElfReader::loadSections() {\n" +
                        "    SectionHeaderType *sh;\n" +
                        "    uint64_t total_bytes = 0;\n" +
                        "    AttributeType tsymb;\n" +
                        "\n" +
                        "    for (int i = 0; i < header_->get_shnum(); i++) {\n" +
                        "        sh = sh_tbl_[i];\n" +
                        "\n" +
                        "        if (sh->get_size() == 0) {\n" +
                        "            continue;\n" +
                        "        }\n" +
                        "\n" +
                        "#if 0\n" +
                        "        if (sectionNames_ && (sh->get_flags() & SHF_ALLOC)) {\n" +
                        "            printf(\"elf2rawx: Reading '%s' section\\n\",\n" +
                        "                    &sectionNames_[sh->get_name()]);\n" +
                        "        }\n" +
                        "#endif\n" +
                        "\n" +
                        "        if ((sh->get_type() == SHT_PROGBITS ||\n" +
                        "             sh->get_type() == SHT_INIT_ARRAY ||\n" +
                        "             sh->get_type() == SHT_FINI_ARRAY ||\n" +
                        "             sh->get_type() == SHT_PREINIT_ARRAY) &&\n" +
                        "             (sh->get_flags() & SHF_ALLOC) != 0) {\n" +
                        "            /**\n" +
                        "             * @brief   Instructions or other processor's information\n" +
                        "             * @details This section holds information defined by the program, \n" +
                        "             *          whose format and meaning are determined solely by the\n" +
                        "             *          program.\n" +
                        "             */\n" +
                        "            AttributeType loadsec;\n" +
                        "            loadsec.make_list(LoadSh_Total);\n" +
                        "            if (sectionNames_) {\n" +
                        "                loadsec[LoadSh_name].make_string(&sectionNames_[sh->get_name()]);\n" +
                        "            } else {\n" +
                        "                loadsec[LoadSh_name].make_string(\"unknown\");\n" +
                        "            }\n" +
                        "            loadsec[LoadSh_addr].make_uint64(sh->get_addr());\n" +
                        "            loadsec[LoadSh_size].make_uint64(sh->get_size());\n" +
                        "            loadsec[LoadSh_data].make_data(static_cast<unsigned>(sh->get_size()),\n" +
                        "                                           &image_[sh->get_offset()]);\n" +
                        "            loadSectionList_.add_to_list(&loadsec);\n" +
                        "            total_bytes += sh->get_size();\n" +
                        "            uint64_t t1 = sh->get_addr() + sh->get_size();\n" +
                        "            if (t1 > lastLoadAdr_) {\n" +
                        "                lastLoadAdr_ = t1;\n" +
                        "            }\n" +
                        "        } else if (sh->get_type() == SHT_NOBITS\n" +
                        "                    && (sh->get_flags() & SHF_ALLOC) != 0) {\n" +
                        "            /**\n" +
                        "             * @brief   Initialized data\n" +
                        "             * @details A section of this type occupies no space in  the file\n" +
                        "             *          but otherwise resembles SHT_PROGBITS.  Although this\n" +
                        "             *          section contains no bytes, the sh_offset member\n" +
                        "             *          contains the conceptual file offset.\n" +
                        "             */\n" +
                        "            AttributeType loadsec;\n" +
                        "            loadsec.make_list(LoadSh_Total);\n" +
                        "            if (sectionNames_) {\n" +
                        "                loadsec[LoadSh_name].make_string(&sectionNames_[sh->get_name()]);\n" +
                        "            } else {\n" +
                        "                loadsec[LoadSh_name].make_string(\"unknown\");\n" +
                        "            }\n" +
                        "            loadsec[LoadSh_addr].make_uint64(sh->get_addr());\n" +
                        "            loadsec[LoadSh_size].make_uint64(sh->get_size());\n" +
                        "            loadsec[LoadSh_data].make_data(static_cast<unsigned>(sh->get_size()));\n" +
                        "            memset(loadsec[LoadSh_data].data(), \n" +
                        "                        0, static_cast<size_t>(sh->get_size()));\n" +
                        "            loadSectionList_.add_to_list(&loadsec);\n" +
                        "            total_bytes += sh->get_size();\n" +
                        "            uint64_t t1 = sh->get_addr() + sh->get_size();\n" +
                        "            if (t1 > lastLoadAdr_) {\n" +
                        "                lastLoadAdr_ = t1;\n" +
                        "            }\n" +
                        "        } else if (sh->get_type() == SHT_SYMTAB || sh->get_type() == SHT_DYNSYM) {\n" +
                        "            processDebugSymbol(sh);\n" +
                        "        }\n" +
                        "    }\n" +
                        "    symbolList_.sort(LoadSh_name);\n" +
                        "    return static_cast<int>(total_bytes);\n" +
                        "}\n" +
                        "\n" +
                        "void ElfReader::processDebugSymbol(SectionHeaderType *sh) {\n" +
                        "    uint64_t symbol_off = 0;\n" +
                        "    SymbolTableType *st;\n" +
                        "    AttributeType tsymb;\n" +
                        "    uint8_t st_type;\n" +
                        "    const char *symb_name;\n" +
                        "    //const char *file_name = 0;\n" +
                        "\n" +
                        "    if (!symbolNames_) {\n" +
                        "        return;\n" +
                        "    }\n" +
                        "\n" +
                        "    while (symbol_off < sh->get_size()) {\n" +
                        "        st = new SymbolTableType(&image_[sh->get_offset() + symbol_off],\n" +
                        "                                header_);\n" +
                        "        \n" +
                        "        symb_name = &symbolNames_[st->get_name()];\n" +
                        "\n" +
                        "        st_type = st->get_info() & 0xF;\n" +
                        "        if ((st_type == STT_OBJECT || st_type == STT_FUNC) && st->get_value()) {\n" +
                        "            tsymb.make_list(Symbol_Total);\n" +
                        "            tsymb[Symbol_Name].make_string(symb_name);\n" +
                        "            tsymb[Symbol_Addr].make_uint64(st->get_value());\n" +
                        "            tsymb[Symbol_Size].make_uint64(st->get_size());\n" +
                        "            if (st_type == STT_FUNC) {\n" +
                        "                tsymb[Symbol_Type].make_uint64(SYMBOL_TYPE_FUNCTION);\n" +
                        "            } else {\n" +
                        "                tsymb[Symbol_Type].make_uint64(SYMBOL_TYPE_DATA);\n" +
                        "            }\n" +
                        "            symbolList_.add_to_list(&tsymb);\n" +
                        "        } else if (st_type == STT_FILE) {\n" +
                        "            //file_name = symb_name;\n" +
                        "        }\n" +
                        "\n" +
                        "        if (sh->get_entsize()) {\n" +
                        "            // section with elements of fixed size\n" +
                        "            symbol_off += sh->get_entsize(); \n" +
                        "        } else if (st->get_size()) {\n" +
                        "            symbol_off += st->get_size();\n" +
                        "        } else {\n" +
                        "            if (header_->isElf32()) {\n" +
                        "                symbol_off += sizeof(Elf32_Sym);\n" +
                        "            } else {\n" +
                        "                symbol_off += sizeof(Elf64_Sym);\n" +
                        "            }\n" +
                        "        }\n" +
                        "        delete st;\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void ElfReader::writeRawImage(AttributeType *ofiles, uint32_t fixed_size) {\n" +
                        "#if 0\n" +
                        "    SrcElement *e;\n" +
                        "    std::ofstream osraw(file_name, std::ios::binary);\n" +
                        "    if (!osraw.is_open()) {\n" +
                        "        printf(\"elf2rawx error: can't create output file %s\\n\", file_name);\n" +
                        "        return;\n" +
                        "    }\n" +
                        "    if (fixed_size && fixed_size < 4*src_img.iSizeWords) {\n" +
                        "        printf(\"Warning: defined size %d is less than actual size %d\\n\",\n" +
                        "            fixed_size, 4*src_img.iSizeWords);\n" +
                        "    }\n" +
                        "\n" +
                        "    char ss;\n" +
                        "    for (uint32_t i = 0; i < src_img.iSizeWords; i++) {\n" +
                        "        e = &src_img.arr[i];\n" +
                        "\n" +
                        "        for (int n=0; n<4; n++) {\n" +
                        "            ss = (char)(e->val >> (24-8*n));\n" +
                        "            osraw.write(&ss, 1);\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    if (fixed_size) {\n" +
                        "        uint32_t fix_word = (fixed_size+3)/4;\n" +
                        "        for (uint32_t i = src_img.iSizeWords; i < fix_word; i++) {\n" +
                        "            uint32_t fix_zero = 0;\n" +
                        "            osraw.write((char *)&fix_zero, 4);\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    osraw.flush();\n" +
                        "\n" +
                        "    printf(\"elf2rawx: Image %d Bytes was generated\\n\", \n" +
                        "        fixed_size ? fixed_size :uiRawImageBytes);\n" +
                        "#endif\n" +
                        "}\n" +
                        "\n" +
                        "void ElfReader::writeRomHexArray(AttributeType *ofiles,\n" +
                        "                                 int bytes_per_line,\n" +
                        "                                 int fixed_size) {\n" +
                        "    bool st = true;\n" +
                        "    uint64_t entry = header_->get_entry();\n" +
                        "    uint64_t sz = lastLoadAdr_ - entry;\n" +
                        "    if (fixed_size) {\n" +
                        "        if (sz >= fixed_size) {\n" +
                        "            printf(\"elf2raw warning: trim image of Size %d B t %d B\\n\",\n" +
                        "                    static_cast<int>(sz), fixed_size);\n" +
                        "        }\n" +
                        "        sz = fixed_size;\n" +
                        "    }\n" +
                        "\n" +
                        "    FILE *fp = fopen((*ofiles)[0u].to_string(), \"wb\");\n" +
                        "    FILE *fplsb = 0;\n" +
                        "    if (!fp) {\n" +
                        "        printf(\"elf2rawx error: can't create '%s' file\\n\",\n" +
                        "                (*ofiles)[0u].to_string());\n" +
                        "        return ;\n" +
                        "    }\n" +
                        "    if (ofiles->size() == 2) {\n" +
                        "        fplsb = fopen((*ofiles)[1].to_string(), \"wb\");\n" +
                        "    }\n" +
                        "\n" +
                        "    uint8_t *img = new uint8_t[static_cast<int>(sz)];\n" +
                        "    memset(img, 0, (size_t)sz);\n" +
                        "\n" +
                        "    uint64_t sec_addr;\n" +
                        "    int sec_sz;\n" +
                        "    for (unsigned i = 0; i < loadableSectionTotal(); i++) {\n" +
                        "        sec_addr = sectionAddress(i) - entry;\n" +
                        "        sec_sz = static_cast<int>(sectionSize(i));\n" +
                        "        if (sec_addr + sec_sz <= sz) {\n" +
                        "            memcpy(&img[sec_addr], sectionData(i), sec_sz);\n" +
                        "        } else {\n" +
                        "            memcpy(&img[sec_addr], sectionData(i), (size_t)(sz - sec_addr));\n" +
                        "            break;\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    char tstr[128];\n" +
                        "    int tstr_sz;\n" +
                        "    uint32_t byte_cnt = 0;\n" +
                        "    int startidx, stopidx;\n" +
                        "    for (uint64_t i = 0; i < sz/bytes_per_line; i++) {\n" +
                        "        tstr_sz = 0;\n" +
                        "        startidx = static_cast<int>((i + 1)*bytes_per_line - 1);\n" +
                        "        stopidx = static_cast<int>(i*bytes_per_line);\n" +
                        "        if (fplsb == 0) {\n" +
                        "            /** Single file */\n" +
                        "            for (int n = startidx; n >= stopidx; n--) {\n" +
                        "                tstr_sz += sprintf(&tstr[tstr_sz], \"%02X\", img[n]);\n" +
                        "            }\n" +
                        "            tstr_sz += sprintf(&tstr[tstr_sz], \"%s\", \"\\r\\n\");\n" +
                        "            fwrite(tstr, tstr_sz, 1, fp);\n" +
                        "        } else {\n" +
                        "            /** Couple of files */\n" +
                        "            char tstr2[128];\n" +
                        "            int tstr2_sz = 0;\n" +
                        "            for (int n = startidx; n >= (stopidx+bytes_per_line/2); n--) {\n" +
                        "                tstr_sz += sprintf(&tstr[tstr_sz], \"%02X\", img[n]);\n" +
                        "                tstr2_sz += sprintf(&tstr2[tstr2_sz], \"%02X\", img[n - bytes_per_line/2]);\n" +
                        "            }\n" +
                        "            tstr_sz += sprintf(&tstr[tstr_sz], \"%s\", \"\\r\\n\");\n" +
                        "            tstr2_sz += sprintf(&tstr2[tstr2_sz], \"%s\", \"\\r\\n\");\n" +
                        "            fwrite(tstr, tstr_sz, 1, fp);\n" +
                        "            fwrite(tstr2, tstr2_sz, 1, fplsb);\n" +
                        "        }\n" +
                        "        byte_cnt++;\n" +
                        "    }\n" +
                        "    \n" +
                        "    fclose(fp);\n" +
                        "    delete [] img;\n" +
                        "\n" +
                        "    printf(\"elf2rawx: HexRom was generated: %dx%d lines\\n\", \n" +
                        "            static_cast<int>(sz/bytes_per_line), 8*bytes_per_line);\n" +
                        "}";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_elfreader_h_file(String Project_Folder_File) {
        String data =   "/*\n" +
                        " *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com\n" +
                        " *\n" +
                        " *  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        " *  you may not use this file except in compliance with the License.\n" +
                        " *  You may obtain a copy of the License at\n" +
                        " *\n" +
                        " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
                        " *\n" +
                        " *  Unless required by applicable law or agreed to in writing, software\n" +
                        " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        " *  See the License for the specific language governing permissions and\n" +
                        " *  limitations under the License.\n" +
                        " */\n" +
                        "\n" +
                        "#pragma once\n" +
                        "\n" +
                        "#include <inttypes.h>\n" +
                        "#include <vector>\n" +
                        "#include \"elftypes.h\"\n" +
                        "#include \"common/attribute.h\"\n" +
                        "\n" +
                        "class ElfReader {\n" +
                        " public:\n" +
                        "    explicit ElfReader(const char *file_name);\n" +
                        "\n" +
                        "    virtual unsigned loadableSectionTotal() {\n" +
                        "        return loadSectionList_.size();\n" +
                        "    }\n" +
                        "\n" +
                        "    virtual const char *sectionName(unsigned idx) {\n" +
                        "        return loadSectionList_[idx][LoadSh_name].to_string();\n" +
                        "    }\n" +
                        "\n" +
                        "    virtual uint64_t sectionAddress(unsigned idx)  {\n" +
                        "        return loadSectionList_[idx][LoadSh_addr].to_uint64();\n" +
                        "    }\n" +
                        "\n" +
                        "    virtual uint64_t sectionSize(unsigned idx)  {\n" +
                        "        return loadSectionList_[idx][LoadSh_size].to_uint64();\n" +
                        "    }\n" +
                        "\n" +
                        "    virtual uint8_t *sectionData(unsigned idx)  {\n" +
                        "        return loadSectionList_[idx][LoadSh_data].data();\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    void writeRawImage(AttributeType *ofiles, uint32_t fixed_size = 0);\n" +
                        "    void writeRomHexArray(AttributeType *ofiles,\n" +
                        "                          int bytes_per_line,\n" +
                        "                          int fixed_size = 0);\n" +
                        "\n" +
                        "private:\n" +
                        "    int readElfHeader();\n" +
                        "    int loadSections();\n" +
                        "    void processDebugSymbol(SectionHeaderType *sh);\n" +
                        "\n" +
                        "private:\n" +
                        "    enum ELoadSectionItem {\n" +
                        "        LoadSh_name,\n" +
                        "        LoadSh_addr,\n" +
                        "        LoadSh_size,\n" +
                        "        LoadSh_data,\n" +
                        "        LoadSh_Total,\n" +
                        "    };\n" +
                        "\n" +
                        "    enum ESymbolInfoListItem {\n" +
                        "        Symbol_Name,\n" +
                        "        Symbol_Addr,\n" +
                        "        Symbol_Size,\n" +
                        "        Symbol_Type,\n" +
                        "        Symbol_Total\n" +
                        "    };\n" +
                        "\n" +
                        "    enum ESymbolType {\n" +
                        "        SYMBOL_TYPE_FILE     = 0x01,\n" +
                        "        SYMBOL_TYPE_FUNCTION = 0x02,\n" +
                        "        SYMBOL_TYPE_DATA     = 0x04\n" +
                        "    };\n" +
                        "\n" +
                        "    enum EMode {\n" +
                        "        Mode_32bits,\n" +
                        "        Mode_64bits\n" +
                        "    } emode_;\n" +
                        "\n" +
                        "    AttributeType sourceProc_;\n" +
                        "    AttributeType symbolList_;\n" +
                        "    AttributeType loadSectionList_;\n" +
                        "\n" +
                        "    uint8_t *image_;\n" +
                        "    ElfHeaderType *header_;\n" +
                        "    SectionHeaderType **sh_tbl_;\n" +
                        "    char *sectionNames_;\n" +
                        "    char *symbolNames_;\n" +
                        "\n" +
                        "    uint64_t lastLoadAdr_;\n" +
                        "};";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_elftypes_h_file(String Project_Folder_File) {
        String data =   "/*\n" +
                        " *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com\n" +
                        " *\n" +
                        " *  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        " *  you may not use this file except in compliance with the License.\n" +
                        " *  You may obtain a copy of the License at\n" +
                        " *\n" +
                        " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
                        " *\n" +
                        " *  Unless required by applicable law or agreed to in writing, software\n" +
                        " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        " *  See the License for the specific language governing permissions and\n" +
                        " *  limitations under the License.\n" +
                        " */\n" +
                        "\n" +
                        "#pragma once\n" +
                        "\n" +
                        "#include <inttypes.h>\n" +
                        "\n" +
                        "typedef uint64_t   ElfAddr64;\n" +
                        "typedef uint64_t   ElfOff64;\n" +
                        "typedef unsigned short ElfHalf;\n" +
                        "typedef signed   int   ElfSword;\n" +
                        "typedef unsigned int   ElfWord;\n" +
                        "typedef uint64_t       ElfDWord;\n" +
                        "\n" +
                        "typedef unsigned int   ElfAddr32;\n" +
                        "typedef unsigned int   ElfOff32;\n" +
                        "\n" +
                        "static const char MAGIC_BYTES[]  = {0x7f,'E','L','F',0};\n" +
                        "\n" +
                        "enum E_EI {\n" +
                        "    EI_MAG0,\n" +
                        "    EI_MAG1,\n" +
                        "    EI_MAG2,\n" +
                        "    EI_MAG3,\n" +
                        "    EI_CLASS,\n" +
                        "    EI_DATA,\n" +
                        "    EI_VERSION,\n" +
                        "    EI_PAD,\n" +
                        "    EI_NIDENT=16\n" +
                        "};\n" +
                        "static const uint8_t ELFCLASSNONE = 0;\n" +
                        "static const uint8_t ELFCLASS32   = 1;\n" +
                        "static const uint8_t ELFCLASS64   = 2;\n" +
                        "\n" +
                        "static const uint8_t ELFDATANONE  = 0;\n" +
                        "static const uint8_t ELFDATA2LSB  = 1;\n" +
                        "static const uint8_t ELFDATA2MSB  = 2;\n" +
                        "\n" +
                        "static const uint8_t EV_NONE      = 0;          // Invalid version\n" +
                        "static const uint8_t EV_CURRENT   = 1;          // Current version\n" +
                        "//etype values:\n" +
                        "static const ElfHalf ET_NONE      = 0;       // no file type\n" +
                        "static const ElfHalf ET_REL       = 1;       // rellocatable file\n" +
                        "static const ElfHalf ET_EXEC      = 2;       // executable file\n" +
                        "static const ElfHalf ET_DYN       = 3;       // shared object file\n" +
                        "static const ElfHalf ET_CORE      = 4;       // core file\n" +
                        "static const ElfHalf ET_LOPROC    = 0xff00;  // Processor-specific\n" +
                        "static const ElfHalf ET_HIPROC    = 0xffff;  // Processor-specific\n" +
                        "//emachine values:\n" +
                        "static const ElfHalf EM_NONE      = 0;       // No machine\n" +
                        "static const ElfHalf EM_M32       = 1;       // AT&T WE 32100\n" +
                        "static const ElfHalf EM_SPARC     = 2;       // SPARC\n" +
                        "static const ElfHalf EM_386       = 3;       // Intel 386\n" +
                        "static const ElfHalf EM_68K       = 4;       // Motorola 68000\n" +
                        "static const ElfHalf EM_88K       = 5;       // Motorola 88000\n" +
                        "static const ElfHalf EM_860       = 7;       // Intel 80860\n" +
                        "static const ElfHalf EM_MIPS      = 8;       // MIPS RS3000\n" +
                        "\n" +
                        "struct Elf32_Ehdr {\n" +
                        "    unsigned char e_ident[EI_NIDENT];\n" +
                        "    ElfHalf e_type;      // Shared/Executable/Rellocalable etc\n" +
                        "    ElfHalf e_machine;   // SPARC, X86 etc\n" +
                        "    ElfWord e_version;   //\n" +
                        "    ElfAddr32 e_entry;     // entry point\n" +
                        "    ElfOff32  e_phoff;     // Program header offset\n" +
                        "    ElfOff32  e_shoff;     // Section Header offset\n" +
                        "    ElfWord e_flags;\n" +
                        "    ElfHalf e_ehsize;\n" +
                        "    ElfHalf e_phentsize; // size of one entry in the Program header. All entries are the same size\n" +
                        "    ElfHalf e_phnum;     // number of entries in a Program header\n" +
                        "    ElfHalf e_shentsize; // entry size in the section header table. all entries are the same size\n" +
                        "    ElfHalf e_shnum;     // number of section header entries\n" +
                        "    ElfHalf e_shstrndx;\n" +
                        "};\n" +
                        "\n" +
                        "struct Elf64_Ehdr {\n" +
                        "    unsigned char e_ident[EI_NIDENT];\n" +
                        "    ElfHalf e_type;      // Shared/Executable/Rellocalable etc\n" +
                        "    ElfHalf e_machine;   // SPARC, X86 etc\n" +
                        "    ElfWord e_version;   //\n" +
                        "    ElfAddr64 e_entry;     // entry point\n" +
                        "    ElfOff64  e_phoff;     // Program header offset\n" +
                        "    ElfOff64  e_shoff;     // Section Header offset\n" +
                        "    ElfWord e_flags;\n" +
                        "    ElfHalf e_ehsize;\n" +
                        "    ElfHalf e_phentsize; // size of one entry in the Program header. All entries are the same size\n" +
                        "    ElfHalf e_phnum;     // number of entries in a Program header\n" +
                        "    ElfHalf e_shentsize; // entry size in the section header table. all entries are the same size\n" +
                        "    ElfHalf e_shnum;     // number of section header entries\n" +
                        "    ElfHalf e_shstrndx;\n" +
                        "};\n" +
                        "\n" +
                        "\n" +
                        "static ElfHalf SwapBytes(ElfHalf v) {\n" +
                        "     v = ((v>>8)&0xff) | ((v&0xFF)<<8);\n" +
                        "     return v;\n" +
                        "}\n" +
                        "\n" +
                        "static ElfWord SwapBytes(ElfWord v) {\n" +
                        "    v = ((v >> 24) & 0xff) | ((v >> 8) & 0xff00) \n" +
                        "      | ((v << 8) & 0xff0000) | ((v & 0xFF) << 24);\n" +
                        "    return v;\n" +
                        "}\n" +
                        "\n" +
                        "static ElfDWord SwapBytes(ElfDWord v) {\n" +
                        "    v = ((v >> 56) & 0xffull) | ((v >> 48) & 0xff00ull) \n" +
                        "      | ((v >> 40) & 0xff0000ull) | ((v >> 32) & 0xff000000ull)\n" +
                        "      | ((v << 8) & 0xff000000ull) | ((v << 16) & 0xff0000000000ull)\n" +
                        "      | ((v << 24) & 0xff0000000000ull) | ((v << 32) & 0xff00000000000000ull);\n" +
                        "    return v;\n" +
                        "}\n" +
                        "\n" +
                        "class ElfHeaderType {\n" +
                        " public:\n" +
                        "    ElfHeaderType(uint8_t *img) {\n" +
                        "        pimg_ = img;\n" +
                        "        isElf_ = true;\n" +
                        "        for (int i = 0; i < 4; i++) {\n" +
                        "            if (pimg_[i] != MAGIC_BYTES[i]) {\n" +
                        "                isElf_ = false;\n" +
                        "            }\n" +
                        "        }\n" +
                        "        is32b_ = pimg_[EI_CLASS] == ELFCLASS32;\n" +
                        "        isMsb_ = pimg_[EI_DATA] == ELFDATA2MSB;\n" +
                        "        if (is32b_) {\n" +
                        "            Elf32_Ehdr *h = reinterpret_cast<Elf32_Ehdr *>(pimg_);\n" +
                        "            if (isMsb_) {\n" +
                        "                e_shoff_ = SwapBytes(h->e_shoff);\n" +
                        "                e_shnum_ = SwapBytes(h->e_shnum);\n" +
                        "                e_phoff_ = SwapBytes(h->e_phoff);\n" +
                        "                e_entry_ = SwapBytes(h->e_entry);\n" +
                        "            } else {\n" +
                        "                e_shoff_ = h->e_shoff;\n" +
                        "                e_shnum_ = h->e_shnum;\n" +
                        "                e_phoff_ = h->e_phoff;\n" +
                        "                e_entry_ = h->e_entry;\n" +
                        "            }\n" +
                        "        } else {\n" +
                        "            Elf64_Ehdr *h = reinterpret_cast<Elf64_Ehdr *>(pimg_);\n" +
                        "            if (isMsb_) {\n" +
                        "                e_shoff_ = SwapBytes(h->e_shoff);\n" +
                        "                e_shnum_ = SwapBytes(h->e_shnum);\n" +
                        "                e_phoff_ = SwapBytes(h->e_phoff);\n" +
                        "                e_entry_ = SwapBytes(h->e_entry);\n" +
                        "            } else {\n" +
                        "                e_shoff_ = h->e_shoff;\n" +
                        "                e_shnum_ = h->e_shnum;\n" +
                        "                e_phoff_ = h->e_phoff;\n" +
                        "                e_entry_ = h->e_entry;\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    virtual bool isElf() { return isElf_; }\n" +
                        "    virtual bool isElf32() { return is32b_; }\n" +
                        "    virtual bool isElfMsb() { return isMsb_; }\n" +
                        "    virtual uint64_t get_shoff() { return e_shoff_; }\n" +
                        "    virtual ElfHalf get_shnum() { return e_shnum_; }\n" +
                        "    virtual uint64_t get_phoff() { return e_phoff_; }\n" +
                        "    virtual uint64_t get_entry() { return e_entry_; }\n" +
                        " protected:\n" +
                        "    uint8_t *pimg_;\n" +
                        "    bool isElf_;\n" +
                        "    bool is32b_;\n" +
                        "    bool isMsb_;\n" +
                        "    uint64_t e_shoff_;\n" +
                        "    ElfHalf e_shnum_;\n" +
                        "    uint64_t e_phoff_;\n" +
                        "    uint64_t e_entry_;\n" +
                        "};\n" +
                        "\n" +
                        "   \n" +
                        "//sh_type:\n" +
                        "static const ElfWord SHT_NULL      = 0;          // section header is inactive\n" +
                        "static const ElfWord SHT_PROGBITS  = 1;          // section with CPU instructions\n" +
                        "static const ElfWord SHT_SYMTAB    = 2;          // section contains symbols table (also as SHT_DYNSIM)\n" +
                        "static const ElfWord SHT_STRTAB    = 3;          // section holds string table\n" +
                        "static const ElfWord SHT_RELA      = 4;          // section with relocation data\n" +
                        "static const ElfWord SHT_HASH      = 5;          // section with hash table. Must be for the dynamic lib\n" +
                        "static const ElfWord SHT_DYNAMIC   = 6;          // section holds information for dynamic linking.\n" +
                        "static const ElfWord SHT_NOTE      = 7;\n" +
                        "static const ElfWord SHT_NOBITS    = 8;          // section with not initialized data\n" +
                        "static const ElfWord SHT_REL       = 9;\n" +
                        "static const ElfWord SHT_SHLIB     = 10;\n" +
                        "static const ElfWord SHT_DYNSYM    = 11;         // section contains debug symbol table\n" +
                        "static const ElfWord SHT_INIT_ARRAY    = 14;\n" +
                        "static const ElfWord SHT_FINI_ARRAY    = 15;\n" +
                        "static const ElfWord SHT_PREINIT_ARRAY = 16;\n" +
                        "static const ElfWord SHT_LOPROC    = 0x70000000;\n" +
                        "static const ElfWord SHT_HIPROC    = 0x7fffffff;\n" +
                        "static const ElfWord SHT_HIUSER    = 0xffffffff;\n" +
                        "//sh_flags:\n" +
                        "static const ElfWord SHF_WRITE     = 0x1;        // section contains data that should be writable during process execution.\n" +
                        "static const ElfWord SHF_ALLOC     = 0x2;        // section occupies memory during process execution. \n" +
                        "static const ElfWord SHF_EXECINSTR = 0x4;        // section contains executable machine instructions.\n" +
                        "static const ElfWord SHF_MASKPROC  = 0xf0000000; // processor-specific sematic\n" +
                        "\n" +
                        "struct Elf32_Shdr {\n" +
                        "    ElfWord    sh_name;//Index in a header section table (gives name of section)\n" +
                        "    ElfWord    sh_type;\n" +
                        " 	ElfWord	sh_flags;	/* SHF_... */\n" +
                        " 	ElfAddr32	sh_addr;\n" +
                        " 	ElfOff32	sh_offset;\n" +
                        " 	ElfWord	sh_size;\n" +
                        "    ElfWord    sh_link;\n" +
                        "    ElfWord    sh_info;\n" +
                        "    ElfWord   sh_addralign;\n" +
                        "    ElfWord   sh_entsize;\n" +
                        "};\n" +
                        "\n" +
                        "struct Elf64_Shdr {\n" +
                        "    ElfWord    sh_name;//Index in a header section table (gives name of section)\n" +
                        "    ElfWord    sh_type;\n" +
                        "    ElfDWord   sh_flags;\n" +
                        "    ElfAddr64  sh_addr;\n" +
                        "    ElfOff64   sh_offset;\n" +
                        "    ElfDWord   sh_size;\n" +
                        "    ElfWord    sh_link;\n" +
                        "    ElfWord    sh_info;\n" +
                        "    ElfDWord   sh_addralign;\n" +
                        "    ElfDWord   sh_entsize;\n" +
                        "};\n" +
                        "\n" +
                        "class SectionHeaderType {\n" +
                        " public:\n" +
                        "    SectionHeaderType(uint8_t *img, ElfHeaderType *h) {\n" +
                        "        if (h->isElf32()) {\n" +
                        "            Elf32_Shdr *sh = reinterpret_cast<Elf32_Shdr *>(img);\n" +
                        "            if (h->isElfMsb()) {\n" +
                        "                sh_name_ = SwapBytes(sh->sh_name);\n" +
                        "                sh_type_ = SwapBytes(sh->sh_type);\n" +
                        "                sh_flags_ = SwapBytes(sh->sh_flags);\n" +
                        "                sh_addr_ = SwapBytes(sh->sh_addr);\n" +
                        "                sh_offset_ = SwapBytes(sh->sh_offset);\n" +
                        "                sh_size_ = SwapBytes(sh->sh_size);\n" +
                        "                sh_entsize_ = SwapBytes(sh->sh_entsize);\n" +
                        "            } else {\n" +
                        "                sh_name_ = sh->sh_name;\n" +
                        "                sh_type_ = sh->sh_type;\n" +
                        "                sh_flags_ = sh->sh_flags;\n" +
                        "                sh_addr_ = sh->sh_addr;\n" +
                        "                sh_offset_ = sh->sh_offset;\n" +
                        "                sh_size_ = sh->sh_size;\n" +
                        "                sh_entsize_ = sh->sh_entsize;\n" +
                        "            }\n" +
                        "        } else {\n" +
                        "            Elf64_Shdr *sh = reinterpret_cast<Elf64_Shdr *>(img);\n" +
                        "            if (h->isElfMsb()) {\n" +
                        "                sh_name_ = SwapBytes(sh->sh_name);\n" +
                        "                sh_type_ = SwapBytes(sh->sh_type);\n" +
                        "                sh_flags_ = SwapBytes(sh->sh_flags);\n" +
                        "                sh_addr_ = SwapBytes(sh->sh_addr);\n" +
                        "                sh_offset_ = SwapBytes(sh->sh_offset);\n" +
                        "                sh_size_ = SwapBytes(sh->sh_size);\n" +
                        "                sh_entsize_ = SwapBytes(sh->sh_entsize);\n" +
                        "            } else {\n" +
                        "                sh_name_ = sh->sh_name;\n" +
                        "                sh_type_ = sh->sh_type;\n" +
                        "                sh_flags_ = sh->sh_flags;\n" +
                        "                sh_addr_ = sh->sh_addr;\n" +
                        "                sh_offset_ = sh->sh_offset;\n" +
                        "                sh_size_ = sh->sh_size;\n" +
                        "                sh_entsize_ = sh->sh_entsize;\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "    virtual ElfWord get_name() { return sh_name_; }\n" +
                        "    virtual ElfWord get_type() { return sh_type_; }\n" +
                        "    virtual uint64_t get_offset() { return sh_offset_; }\n" +
                        "    virtual uint64_t get_size() { return sh_size_; }\n" +
                        "    virtual uint64_t get_addr() { return sh_addr_; }\n" +
                        "    virtual uint64_t get_flags() { return sh_flags_; }\n" +
                        "    virtual uint64_t get_entsize() { return sh_entsize_; }\n" +
                        " protected:\n" +
                        "    ElfWord sh_name_;\n" +
                        "    ElfWord sh_type_;\n" +
                        "    uint64_t sh_offset_;\n" +
                        "    uint64_t sh_size_;\n" +
                        "    uint64_t sh_addr_;\n" +
                        "    uint64_t sh_flags_;\n" +
                        "    uint64_t sh_entsize_;\n" +
                        "};\n" +
                        "\n" +
                        "\n" +
                        "#define ELF32_ST_BIND(i) ((i)>>4)\n" +
                        "#define ELF32_ST_TYPE(i) ((i)&0xf)\n" +
                        "#define ELF32_ST_INFO(b,t) (((b)<<4) + ((t)&0xf))\n" +
                        "      \n" +
                        "static const unsigned char STB_LOCAL   = 0;\n" +
                        "static const unsigned char STB_GLOBAL  = 1;\n" +
                        "static const unsigned char STB_WEAK    = 2;\n" +
                        "static const unsigned char STB_LOPROC  = 13;\n" +
                        "static const unsigned char STB_HIPROC  = 15;\n" +
                        "\n" +
                        "static const unsigned char STT_NOTYPE  = 0;\n" +
                        "static const unsigned char STT_OBJECT  = 1;\n" +
                        "static const unsigned char STT_FUNC    = 2;\n" +
                        "static const unsigned char STT_SECTION = 3;\n" +
                        "static const unsigned char STT_FILE    = 4;\n" +
                        "static const unsigned char STT_LOPROC  = 13;\n" +
                        "static const unsigned char STT_HIPROC  = 15;\n" +
                        "\n" +
                        "struct Elf32_Sym {\n" +
                        "    ElfWord    st_name;\n" +
                        " 	ElfAddr32	st_value;\n" +
                        " 	ElfWord	st_size;\n" +
                        " 	unsigned char	st_info;\n" +
                        " 	unsigned char	st_other;\n" +
                        " 	ElfHalf	st_shndx;\n" +
                        "};\n" +
                        "\n" +
                        "struct Elf64_Sym {\n" +
                        "    ElfWord    st_name;\n" +
                        "	unsigned char	st_info;\n" +
                        "	unsigned char	st_other;\n" +
                        "	ElfHalf	st_shndx;\n" +
                        "	ElfAddr64	st_value;\n" +
                        "	ElfDWord	st_size;\n" +
                        "};\n" +
                        "\n" +
                        "class SymbolTableType {\n" +
                        " public:\n" +
                        "    SymbolTableType(uint8_t *img, ElfHeaderType *h) {\n" +
                        "        if (h->isElf32()) {\n" +
                        "            Elf32_Sym *st = reinterpret_cast<Elf32_Sym *>(img);\n" +
                        "            if (h->isElfMsb()) {\n" +
                        "                st_name_ = SwapBytes(st->st_name);\n" +
                        "                st_value_ = SwapBytes(st->st_value);\n" +
                        "                st_size_ = SwapBytes(st->st_size);\n" +
                        "            } else {\n" +
                        "                st_name_ = st->st_name;\n" +
                        "                st_value_ = st->st_value;\n" +
                        "                st_size_ = st->st_size;\n" +
                        "            }\n" +
                        "            st_info_ = st->st_info;\n" +
                        "        } else {\n" +
                        "            Elf64_Sym *st = reinterpret_cast<Elf64_Sym *>(img);\n" +
                        "            if (h->isElfMsb()) {\n" +
                        "                st_name_ = SwapBytes(st->st_name);\n" +
                        "                st_value_ = SwapBytes(st->st_value);\n" +
                        "                st_size_ = SwapBytes(st->st_size);\n" +
                        "            } else {\n" +
                        "                st_name_ = st->st_name;\n" +
                        "                st_value_ = st->st_value;\n" +
                        "                st_size_ = st->st_size;\n" +
                        "            }\n" +
                        "            st_info_ = st->st_info;\n" +
                        "        }\n" +
                        "    }\n" +
                        "    virtual ~SymbolTableType() {}\n" +
                        "    virtual ElfWord get_name() { return st_name_; }\n" +
                        "    virtual uint64_t get_value() { return st_value_; }\n" +
                        "    virtual uint64_t get_size() { return st_size_; }\n" +
                        "    virtual unsigned char get_info() { return st_info_; }\n" +
                        " protected:\n" +
                        "    ElfWord    st_name_;\n" +
                        "    unsigned char st_info_;\n" +
                        "    uint64_t st_value_;\n" +
                        "    uint64_t st_size_;\n" +
                        "};\n" +
                        "\n" +
                        "\n" +
                        "//p_type:\n" +
                        "static const ElfWord PT_NULL     = 0;\n" +
                        "static const ElfWord PT_LOAD     = 1;\n" +
                        "static const ElfWord PT_DYNAMIC  = 2;\n" +
                        "static const ElfWord PT_INTERP   = 3;\n" +
                        "static const ElfWord PT_NOTE     = 4;\n" +
                        "static const ElfWord PT_SHLIB    = 5;\n" +
                        "static const ElfWord PT_PHDR     = 6;\n" +
                        "static const ElfWord PT_LOPROC   = 0x70000000;\n" +
                        "static const ElfWord PT_HIPROC   = 0x7fffffff;\n" +
                        "\n" +
                        "typedef struct ProgramHeaderType64 {\n" +
                        "    uint32_t    p_type;\n" +
                        "    uint32_t    p_offset;\n" +
                        "    uint64_t    p_vaddr;\n" +
                        "    uint64_t    p_paddr;\n" +
                        "    ElfDWord	p_filesz;\n" +
                        "    ElfDWord	p_memsz;\n" +
                        "    ElfDWord	p_flags;\n" +
                        "    ElfDWord	p_align;\n" +
                        "} ProgramHeaderType64;\n" +
                        "\n" +
                        "typedef struct ProgramHeaderType32 {\n" +
                        "    uint32_t    p_type;\n" +
                        "    uint32_t    p_offset;\n" +
                        "    ElfAddr32    p_vaddr;\n" +
                        "    ElfAddr32    p_paddr;\n" +
                        "    ElfWord	p_filesz;\n" +
                        "    ElfWord	p_memsz;\n" +
                        "    ElfWord	p_flags;\n" +
                        "    ElfWord	p_align;\n" +
                        "} ProgramHeaderType32;";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_attribute_cpp_file(String Project_Folder_File) {
        String data =   "/*\n" +
                        " *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com\n" +
                        " *\n" +
                        " *  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        " *  you may not use this file except in compliance with the License.\n" +
                        " *  You may obtain a copy of the License at\n" +
                        " *\n" +
                        " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
                        " *\n" +
                        " *  Unless required by applicable law or agreed to in writing, software\n" +
                        " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        " *  See the License for the specific language governing permissions and\n" +
                        " *  limitations under the License.\n" +
                        " */\n" +
                        "\n" +
                        "#include \"attribute.h\"\n" +
                        "#include \"autobuffer.h\"\n" +
                        "#include <stdlib.h>\n" +
                        "#include <stdio.h>\n" +
                        "#include <cstdlib>\n" +
                        "#include <string>\n" +
                        "#include <algorithm>\n" +
                        "\n" +
                        "static const int64_t MIN_ALLOC_BYTES = 1 << 12;\n" +
                        "static AttributeType NilAttribute;\n" +
                        "\n" +
                        "void attribute_to_string(const AttributeType *attr, AutoBuffer *buf);\n" +
                        "int string_to_attribute(const char *cfg, int &off, AttributeType *out);\n" +
                        "\n" +
                        "void AttributeType::allocAttrName(const char *name) {\n" +
                        "    size_t len = strlen(name) + 1;\n" +
                        "    attr_name_ = static_cast<char *>(malloc(len));\n" +
                        "    memcpy(attr_name_, name, len);\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::freeAttrName() {\n" +
                        "    if (attr_name_) {\n" +
                        "        free(attr_name_);\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::allocAttrDescription(const char *descr) {\n" +
                        "    size_t len = strlen(descr) + 1;\n" +
                        "    attr_descr_ = static_cast<char *>(malloc(len));\n" +
                        "    memcpy(attr_descr_, descr, len);\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::freeAttrDescription() {\n" +
                        "    if (attr_descr_) {\n" +
                        "        free(attr_descr_);\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::attr_free() {\n" +
                        "    if (size()) {\n" +
                        "        if (is_string()) {\n" +
                        "            free(u_.string);\n" +
                        "        } else if (is_data() && size() > 8) {\n" +
                        "            free(u_.data);\n" +
                        "        } else if (is_list()) {\n" +
                        "            for (unsigned i = 0; i < size(); i++) {\n" +
                        "                u_.list[i].attr_free();\n" +
                        "            }\n" +
                        "            free(u_.list);\n" +
                        "        } else if (is_dict()) {\n" +
                        "            for (unsigned i = 0; i < size(); i++) {\n" +
                        "                u_.dict[i].key_.attr_free();\n" +
                        "                u_.dict[i].value_.attr_free();\n" +
                        "            }\n" +
                        "            free(u_.dict);\n" +
                        "        }\n" +
                        "    }\n" +
                        "    kind_ = Attr_Invalid;\n" +
                        "    size_ = 0;\n" +
                        "    u_.integer = 0;\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::clone(const AttributeType *v) {\n" +
                        "    attr_free();\n" +
                        "\n" +
                        "    if (v->is_string()) {\n" +
                        "        this->make_string(v->to_string());\n" +
                        "    } else if (v->is_data()) {\n" +
                        "        this->make_data(v->size(), v->data());\n" +
                        "    } else if (v->is_list()) {\n" +
                        "        make_list(v->size());\n" +
                        "        for (unsigned i = 0; i < v->size(); i++) {\n" +
                        "            u_.list[i].clone(v->list(i));\n" +
                        "        }\n" +
                        "    } else if (v->is_dict()) {\n" +
                        "        make_dict();\n" +
                        "        realloc_dict(v->size());\n" +
                        "        for (unsigned i = 0; i < v->size(); i++) {\n" +
                        "            u_.dict[i].key_.make_string(v->dict_key(i)->to_string());\n" +
                        "            u_.dict[i].value_.clone(v->dict_value(i));\n" +
                        "        }\n" +
                        "    } else {\n" +
                        "        this->kind_ = v->kind_;\n" +
                        "        this->u_ = v->u_;\n" +
                        "        this->size_ = v->size_;\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "bool AttributeType::is_equal(const char *v) {\n" +
                        "    if (!is_string()) {\n" +
                        "        return false;\n" +
                        "    }\n" +
                        "    return !strcmp(to_string(), v);\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        "AttributeType &AttributeType::operator=(const AttributeType& other) {\n" +
                        "    if (&other != this) {\n" +
                        "        clone(&other);\n" +
                        "    }\n" +
                        "    return *this;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        "const AttributeType &AttributeType::operator[](unsigned idx) const {\n" +
                        "    if (is_list()) {\n" +
                        "        return u_.list[idx];\n" +
                        "    } else if (is_dict()) {\n" +
                        "        return u_.dict[idx].value_;\n" +
                        "    } else {\n" +
                        "        printf(\"%s\", \"Non-indexed attribute type\");\n" +
                        "    }\n" +
                        "    return NilAttribute;\n" +
                        "}\n" +
                        "\n" +
                        "AttributeType &AttributeType::operator[](unsigned idx) {\n" +
                        "    if (is_list()) {\n" +
                        "        return u_.list[idx];\n" +
                        "    } else if (is_dict()) {\n" +
                        "        return u_.dict[idx].value_;\n" +
                        "    } else {\n" +
                        "        printf(\"%s\", \"Non-indexed attribute type\");\n" +
                        "    }\n" +
                        "    return NilAttribute;\n" +
                        "}\n" +
                        "\n" +
                        "const AttributeType &AttributeType::operator[](const char *key) const {\n" +
                        "    for (unsigned i = 0; i < size(); i++) {\n" +
                        "        if (strcmp(key, u_.dict[i].key_.to_string()) == 0) {\n" +
                        "            return u_.dict[i].value_;\n" +
                        "        }\n" +
                        "    }\n" +
                        "    AttributeType *pthis = const_cast<AttributeType*>(this);\n" +
                        "    pthis->realloc_dict(size()+1);\n" +
                        "    pthis->u_.dict[size()-1].key_.make_string(key);\n" +
                        "    pthis->u_.dict[size()-1].value_.make_nil();\n" +
                        "    return u_.dict[size()-1].value_;\n" +
                        "}\n" +
                        "\n" +
                        "AttributeType &AttributeType::operator[](const char *key) {\n" +
                        "    for (unsigned i = 0; i < size(); i++) {\n" +
                        "        if (strcmp(key, u_.dict[i].key_.to_string()) == 0) {\n" +
                        "            return u_.dict[i].value_;\n" +
                        "        }\n" +
                        "    }\n" +
                        "    realloc_dict(size()+1);\n" +
                        "    u_.dict[size()-1].key_.make_string(key);\n" +
                        "    u_.dict[size()-1].value_.make_nil();\n" +
                        "    return u_.dict[size()-1].value_;\n" +
                        "}\n" +
                        "\n" +
                        "const uint8_t &AttributeType::operator()(unsigned idx) const {\n" +
                        "    if (idx > size()) {\n" +
                        "        printf(\"Data index '%d' out of range.\", idx);\n" +
                        "        return u_.data[0];\n" +
                        "    }\n" +
                        "    if (size_ > 8) {\n" +
                        "        return u_.data[idx];\n" +
                        "    }\n" +
                        "    return u_.data_bytes[idx];\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::make_string(const char *value) {\n" +
                        "    attr_free();\n" +
                        "    if (value) {\n" +
                        "        kind_ = Attr_String;\n" +
                        "        size_ = (unsigned)strlen(value);\n" +
                        "        u_.string = static_cast<char *>(malloc(size_ + 1));\n" +
                        "        memcpy(u_.string, value, size_ + 1);\n" +
                        "    } else {\n" +
                        "        kind_ = Attr_Nil;\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::make_data(unsigned size) {\n" +
                        "    attr_free();\n" +
                        "    kind_ = Attr_Data;\n" +
                        "    size_ = size;\n" +
                        "    if (size > 8) {\n" +
                        "        u_.data = static_cast<uint8_t *>(malloc(size_));\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::make_data(unsigned size, const void *data) {\n" +
                        "    attr_free();\n" +
                        "    kind_ = Attr_Data;\n" +
                        "    size_ = size;\n" +
                        "    if (size > 8) {\n" +
                        "        u_.data = static_cast<uint8_t *>(malloc(size_));\n" +
                        "        memcpy(u_.data, data, size);\n" +
                        "    } else {\n" +
                        "        memcpy(u_.data_bytes, data, size);\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::realloc_data(unsigned size) {\n" +
                        "    if (!is_data()) {\n" +
                        "        return;\n" +
                        "    }\n" +
                        "    if (size <= 8) {    \n" +
                        "        if (size_ > 8) {\n" +
                        "            memcpy(u_.data_bytes, u_.data, size);\n" +
                        "            free(u_.data);\n" +
                        "        }\n" +
                        "        size_ = size;\n" +
                        "        return;\n" +
                        "    }\n" +
                        "    uint8_t *pnew = static_cast<uint8_t *>(malloc(size));\n" +
                        "    unsigned sz = size;\n" +
                        "    if (size_ < sz) {\n" +
                        "        sz = size_;\n" +
                        "    }\n" +
                        "    if (sz > 8) {\n" +
                        "        memcpy(pnew, u_.data, sz);\n" +
                        "        free(u_.data);\n" +
                        "    } else {\n" +
                        "        memcpy(pnew, u_.data_bytes, sz);\n" +
                        "    }\n" +
                        "    u_.data = pnew;\n" +
                        "    size_ = size;\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::make_list(unsigned size) {\n" +
                        "    attr_free();\n" +
                        "    kind_ = Attr_List;\n" +
                        "    if (size) {\n" +
                        "        realloc_list(size);\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::realloc_list(unsigned size) {\n" +
                        "    size_t req_sz = (size * sizeof(AttributeType) + MIN_ALLOC_BYTES - 1)\n" +
                        "                   / MIN_ALLOC_BYTES;\n" +
                        "    size_t cur_sz = (size_ * sizeof(AttributeType) + MIN_ALLOC_BYTES - 1)\n" +
                        "                  / MIN_ALLOC_BYTES;\n" +
                        "    if (req_sz > cur_sz) {\n" +
                        "        AttributeType * t1 = static_cast<AttributeType *>(\n" +
                        "                malloc(MIN_ALLOC_BYTES * req_sz));\n" +
                        "        memcpy(t1, u_.list, size_ * sizeof(AttributeType));\n" +
                        "        memset(&t1[size_], 0,\n" +
                        "                (MIN_ALLOC_BYTES * req_sz) - size_ * sizeof(AttributeType));\n" +
                        "        if (size_) {\n" +
                        "            free(u_.list);\n" +
                        "        }\n" +
                        "        u_.list = t1;\n" +
                        "    }\n" +
                        "    size_ = size;\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::insert_to_list(unsigned idx, const AttributeType *item) {\n" +
                        "    if (idx > size_) {\n" +
                        "        printf(\"%s\", \"Insert index out of bound\");\n" +
                        "        return;\n" +
                        "    }\n" +
                        "    size_t new_sz = ((size_ + 1) * sizeof(AttributeType) + MIN_ALLOC_BYTES - 1)\n" +
                        "                  / MIN_ALLOC_BYTES;\n" +
                        "    AttributeType * t1 = static_cast<AttributeType *>(\n" +
                        "                malloc(MIN_ALLOC_BYTES * new_sz));\n" +
                        "    memset(t1 + idx, 0, sizeof(AttributeType));  // Fix bug request #4\n" +
                        "\n" +
                        "    memcpy(t1, u_.list, idx * sizeof(AttributeType));\n" +
                        "    t1[idx].clone(item);\n" +
                        "    memcpy(&t1[idx + 1], &u_.list[idx], (size_ - idx) * sizeof(AttributeType));\n" +
                        "    memset(&t1[size_ + 1], 0,\n" +
                        "          (MIN_ALLOC_BYTES * new_sz) - (size_ + 1) * sizeof(AttributeType));\n" +
                        "    if (size_) {\n" +
                        "        free(u_.list);\n" +
                        "    }\n" +
                        "    u_.list = t1;\n" +
                        "    size_++;\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::remove_from_list(unsigned idx) {\n" +
                        "    if (idx >= size_) {\n" +
                        "        printf(\"%s\", \"Remove index out of range\");\n" +
                        "        return;\n" +
                        "    }\n" +
                        "    (*this)[idx].attr_free();\n" +
                        "    if (idx == (size() - 1)) {\n" +
                        "        size_ -= 1;\n" +
                        "    } else if (idx < size()) {\n" +
                        "        swap_list_item(idx, size() - 1);\n" +
                        "        size_ -= 1;\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::trim_list(unsigned start, unsigned end) {\n" +
                        "    for (unsigned i = start; i < (size_ - end); i++) {\n" +
                        "        u_.list[start + i].attr_free();\n" +
                        "        u_.list[start + i] = u_.list[end + i];\n" +
                        "    }\n" +
                        "    size_ -= (end - start);\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::swap_list_item(unsigned n, unsigned m) {\n" +
                        "    if (n == m) {\n" +
                        "        return;\n" +
                        "    }\n" +
                        "    unsigned tsize = u_.list[n].size_;\n" +
                        "    KindType tkind = u_.list[n].kind_;\n" +
                        "    int64_t tinteger = u_.list[n].u_.integer;\n" +
                        "    u_.list[n].size_ = u_.list[m].size_;\n" +
                        "    u_.list[n].kind_ = u_.list[m].kind_;\n" +
                        "    u_.list[n].u_.integer = u_.list[m].u_.integer;\n" +
                        "    u_.list[m].size_ = tsize;\n" +
                        "    u_.list[m].kind_ = tkind;\n" +
                        "    u_.list[m].u_.integer = tinteger;\n" +
                        "}\n" +
                        "\n" +
                        "\n" +
                        "int partition(AttributeType *A, int lo, int hi, int lst_idx) {\n" +
                        "    AttributeType *pivot = &(*A)[hi];\n" +
                        "    bool do_swap;\n" +
                        "    int i = lo - 1;\n" +
                        "    for (int j = lo; j < hi; j++) {\n" +
                        "        AttributeType &item = (*A)[j];\n" +
                        "        do_swap = false;\n" +
                        "        if (item.is_string()) {\n" +
                        "            if (strcmp(item.to_string(), pivot->to_string()) <= 0) {\n" +
                        "                do_swap = true;\n" +
                        "            }\n" +
                        "        } else if (item.is_int64()) {\n" +
                        "            if (item.to_int64() <= pivot->to_int64()) {\n" +
                        "                do_swap = true;\n" +
                        "            }\n" +
                        "        } else if (item.is_uint64()) {\n" +
                        "            if (item.to_uint64() <= pivot->to_uint64()) {\n" +
                        "                do_swap = true;\n" +
                        "            }\n" +
                        "        } else if (item.is_list()) {\n" +
                        "            AttributeType &t1 = item[lst_idx];\n" +
                        "            if (t1.is_string() &&\n" +
                        "                strcmp(t1.to_string(), (*pivot)[lst_idx].to_string()) <= 0) {\n" +
                        "                do_swap = true;\n" +
                        "            } else if (t1.is_int64() &&\n" +
                        "                t1.to_int64() <= (*pivot)[lst_idx].to_int64()) {\n" +
                        "                do_swap = true;\n" +
                        "            } else if (t1.is_uint64() &&\n" +
                        "                t1.to_uint64() <= (*pivot)[lst_idx].to_uint64()) {\n" +
                        "                do_swap = true;\n" +
                        "            }\n" +
                        "        } else {\n" +
                        "            printf(\"%s\", \"Not supported attribute type for sorting\");\n" +
                        "            return i + 1;\n" +
                        "        }\n" +
                        "\n" +
                        "        if (do_swap) {\n" +
                        "            i = i + 1;\n" +
                        "            A->swap_list_item(i, j);\n" +
                        "        }\n" +
                        "    }\n" +
                        "    A->swap_list_item(i + 1, hi);\n" +
                        "    return i + 1;\n" +
                        "}\n" +
                        "\n" +
                        "void quicksort(AttributeType *A, int lo, int hi, int lst_idx) {\n" +
                        "    if (lo >= hi) {\n" +
                        "        return;\n" +
                        "    }\n" +
                        "    int p = partition(A, lo, hi, lst_idx);\n" +
                        "    quicksort(A, lo, p - 1, lst_idx);\n" +
                        "    quicksort(A, p + 1, hi, lst_idx);\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::sort(int idx) {\n" +
                        "    if (!is_list()) {\n" +
                        "        printf(\"%s\", \"Sort algorithm can applied only to list attribute\");\n" +
                        "    }\n" +
                        "    quicksort(this, 0, static_cast<int>(size()) - 1, idx);\n" +
                        "}\n" +
                        "\n" +
                        "bool AttributeType::has_key(const char *key) const {\n" +
                        "    for (unsigned i = 0; i < size(); i++) {\n" +
                        "        AttributePairType &pair = u_.dict[i];\n" +
                        "        if (pair.key_.is_equal(key) && !pair.value_.is_nil()) {\n" +
                        "            return true;\n" +
                        "        }\n" +
                        "    }\n" +
                        "    return false;\n" +
                        "}\n" +
                        "\n" +
                        "const AttributeType *AttributeType::dict_key(unsigned idx) const {\n" +
                        "    return &u_.dict[idx].key_;\n" +
                        "}\n" +
                        "AttributeType *AttributeType::dict_key(unsigned idx) {\n" +
                        "    return &u_.dict[idx].key_;\n" +
                        "}\n" +
                        "\n" +
                        "const AttributeType *AttributeType::dict_value(unsigned idx) const {\n" +
                        "    return &u_.dict[idx].value_;\n" +
                        "}\n" +
                        "AttributeType *AttributeType::dict_value(unsigned idx) {\n" +
                        "    return &u_.dict[idx].value_;\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::make_dict() {\n" +
                        "    attr_free();\n" +
                        "    kind_ = Attr_Dict;\n" +
                        "    size_ = 0;\n" +
                        "    u_.dict = NULL;\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::realloc_dict(unsigned size) {\n" +
                        "    size_t req_sz = (size * sizeof(AttributePairType) + MIN_ALLOC_BYTES - 1)\n" +
                        "                  / MIN_ALLOC_BYTES;\n" +
                        "    size_t cur_sz = (size_ * sizeof(AttributePairType) + MIN_ALLOC_BYTES - 1)\n" +
                        "                  / MIN_ALLOC_BYTES;\n" +
                        "    if (req_sz > cur_sz) {\n" +
                        "        AttributePairType * t1 = static_cast<AttributePairType *>(\n" +
                        "                malloc(MIN_ALLOC_BYTES * req_sz));\n" +
                        "        memcpy(t1, u_.dict, size_ * sizeof(AttributePairType));\n" +
                        "        memset(&t1[size_], 0,\n" +
                        "                (MIN_ALLOC_BYTES * req_sz) - size_ * sizeof(AttributePairType));\n" +
                        "        if (size_) {\n" +
                        "            free(u_.dict);\n" +
                        "        }\n" +
                        "        u_.dict = t1;\n" +
                        "    }\n" +
                        "    size_ = size;\n" +
                        "}\n" +
                        "\n" +
                        "const AttributeType& AttributeType::to_config() {\n" +
                        "    AutoBuffer strBuffer;\n" +
                        "    attribute_to_string(this, &strBuffer);\n" +
                        "    make_string(strBuffer.getBuffer());\n" +
                        "    return (*this);\n" +
                        "}\n" +
                        "\n" +
                        "void AttributeType::from_config(const char *str) {\n" +
                        "    int off = 0;\n" +
                        "    string_to_attribute(str, off, this);\n" +
                        "}\n" +
                        "\n" +
                        "void attribute_to_string(const AttributeType *attr, AutoBuffer *buf) {\n" +
                        "    if (attr->is_nil()) {\n" +
                        "        buf->write_string(\"None\");\n" +
                        "    } else if (attr->is_int64() || attr->is_uint64()) {\n" +
                        "        buf->write_uint64(attr->to_uint64());\n" +
                        "    } else if (attr->is_string()) {\n" +
                        "        buf->write_string('\\'');\n" +
                        "        buf->write_string(attr->to_string());\n" +
                        "        buf->write_string('\\'');\n" +
                        "    } else if (attr->is_bool()) {\n" +
                        "        if (attr->to_bool()) {\n" +
                        "            buf->write_string(\"True\");\n" +
                        "        } else {\n" +
                        "            buf->write_string(\"False\");\n" +
                        "        }\n" +
                        "    } else if (attr->is_list()) {\n" +
                        "        AttributeType list_item;\n" +
                        "        unsigned list_sz = attr->size();\n" +
                        "        buf->write_string('[');\n" +
                        "        for (unsigned i = 0; i < list_sz; i++) {\n" +
                        "            list_item = (*attr)[i];\n" +
                        "            attribute_to_string(&list_item, buf);\n" +
                        "            if (i < (list_sz - 1)) {\n" +
                        "                buf->write_string(',');\n" +
                        "            }\n" +
                        "        }\n" +
                        "        buf->write_string(']');\n" +
                        "    } else if (attr->is_dict()) {\n" +
                        "        AttributeType dict_item;\n" +
                        "        unsigned dict_sz = attr->size();;\n" +
                        "        buf->write_string('{');\n" +
                        "\n" +
                        "        for (unsigned i = 0; i < dict_sz; i++) {\n" +
                        "            buf->write_string('\\'');\n" +
                        "            buf->write_string(attr->u_.dict[i].key_.to_string());\n" +
                        "            buf->write_string('\\'');\n" +
                        "            buf->write_string(':');\n" +
                        "            const AttributeType &dict_value = (*attr)[i];\n" +
                        "            attribute_to_string(&dict_value, buf);\n" +
                        "            if (i < (dict_sz - 1)) {\n" +
                        "                buf->write_string(',');\n" +
                        "            }\n" +
                        "        }\n" +
                        "        buf->write_string('}');\n" +
                        "    } else if (attr->is_data()) {\n" +
                        "        buf->write_string('(');\n" +
                        "        if (attr->size() > 0) {\n" +
                        "            for (unsigned n = 0; n < attr->size()-1;  n++) {\n" +
                        "                buf->write_byte((*attr)(n));\n" +
                        "                buf->write_string(',');\n" +
                        "            }\n" +
                        "            buf->write_byte((*attr)(attr->size()-1));\n" +
                        "        }\n" +
                        "        buf->write_string(')');\n" +
                        "    } else if (attr->is_iface()) {\n" +
                        "        printf(\"%s\", \"Not implemented interface to dict. method\");\n" +
                        "    } else if (attr->is_floating()) {\n" +
                        "        char fstr[64];\n" +
                        "        sprintf(fstr, \"%.4f\", attr->to_float());\n" +
                        "        buf->write_string(fstr);\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "int skip_special_symbols(const char *cfg, int off) {\n" +
                        "    const char *pcur = &cfg[off];\n" +
                        "    while (*pcur == ' ' || *pcur == '\\r' || *pcur == '\\n' || *pcur == '\\t') {\n" +
                        "        pcur++;\n" +
                        "        off++;\n" +
                        "    }\n" +
                        "    return off;\n" +
                        "}\n" +
                        "\n" +
                        "int string_to_attribute(const char *cfg, int &off,\n" +
                        "                         AttributeType *out) {\n" +
                        "    off = skip_special_symbols(cfg, off);\n" +
                        "    int checkstart = off;\n" +
                        "    if (cfg[off] == '\\'' || cfg[off] == '\"') {\n" +
                        "        AutoBuffer buf;\n" +
                        "        uint8_t t1 = cfg[off];\n" +
                        "        int str_sz = 0;\n" +
                        "        const char *pcur = &cfg[++off];\n" +
                        "        while (*pcur != t1 && *pcur != '\\0') {\n" +
                        "            pcur++;\n" +
                        "            str_sz++;\n" +
                        "        }\n" +
                        "        buf.write_bin(&cfg[off], str_sz);\n" +
                        "        out->make_string(buf.getBuffer());\n" +
                        "        off += str_sz;\n" +
                        "        if (cfg[off] != t1) {\n" +
                        "            printf(\"%s\", \"JSON parser error: Wrong string format\");\n" +
                        "            out->attr_free();\n" +
                        "            return -1;\n" +
                        "        }\n" +
                        "        off = skip_special_symbols(cfg, off + 1);\n" +
                        "    } else if (cfg[off] == '[') {\n" +
                        "        off = skip_special_symbols(cfg, off + 1);\n" +
                        "        AttributeType new_item;\n" +
                        "        out->make_list(0);\n" +
                        "        while (cfg[off] != ']' && cfg[off] != '\\0') {\n" +
                        "            if (string_to_attribute(cfg, off, &new_item)) {\n" +
                        "                /* error handling */\n" +
                        "                out->attr_free();\n" +
                        "                return -1;\n" +
                        "            }\n" +
                        "            out->realloc_list(out->size() + 1);\n" +
                        "            (*out)[out->size() - 1] = new_item;\n" +
                        "\n" +
                        "            off = skip_special_symbols(cfg, off);\n" +
                        "            if (cfg[off] == ',') {\n" +
                        "                off = skip_special_symbols(cfg, off + 1);\n" +
                        "            }\n" +
                        "        }\n" +
                        "        if (cfg[off] != ']') {\n" +
                        "            printf(\"%s\", \"JSON parser error: Wrong list format\");\n" +
                        "            out->attr_free();\n" +
                        "            return -1;\n" +
                        "        }\n" +
                        "        off = skip_special_symbols(cfg, off + 1);\n" +
                        "    } else if (cfg[off] == '{') {\n" +
                        "        AttributeType new_key;\n" +
                        "        AttributeType new_value;\n" +
                        "        out->make_dict();\n" +
                        "        off = skip_special_symbols(cfg, off + 1);\n" +
                        "        while (cfg[off] != '}' && cfg[off] != '\\0') {\n" +
                        "            if (string_to_attribute(cfg, off, &new_key)) {\n" +
                        "                printf(\"%s\", \"JSON parser error: Wrong dictionary key\");\n" +
                        "                out->attr_free();\n" +
                        "                return -1;\n" +
                        "            }\n" +
                        "            off = skip_special_symbols(cfg, off);\n" +
                        "            if (cfg[off] != ':') {\n" +
                        "                out->attr_free();\n" +
                        "                printf(\"%s\", \"JSON parser error: Wrong dictionary delimiter\");\n" +
                        "                return -1;\n" +
                        "            }\n" +
                        "            off = skip_special_symbols(cfg, off + 1);\n" +
                        "            if (string_to_attribute(cfg, off, &new_value)) {\n" +
                        "                printf(\"%s\", \"JSON parser error: Wrong dictionary value\");\n" +
                        "                out->attr_free();\n" +
                        "                return -1;\n" +
                        "            }\n" +
                        "\n" +
                        "            (*out)[new_key.to_string()] = new_value;\n" +
                        "\n" +
                        "            off = skip_special_symbols(cfg, off);\n" +
                        "            if (cfg[off] == ',') {\n" +
                        "                off = skip_special_symbols(cfg, off + 1);\n" +
                        "            }\n" +
                        "        }\n" +
                        "        if (cfg[off] != '}') {\n" +
                        "            printf(\"%s\", \"JSON parser error: Wrong dictionary format\");\n" +
                        "            out->attr_free();\n" +
                        "            return -1;\n" +
                        "        }\n" +
                        "        off = skip_special_symbols(cfg, off + 1);\n" +
                        "\n" +
                        "        if (out->has_key(\"Type\")) {\n" +
                        "            printf(\"%s\", \"Not implemented string to dict. attribute\");\n" +
                        "        }\n" +
                        "    } else if (cfg[off] == '(') {\n" +
                        "        AutoBuffer buf;\n" +
                        "        char byte_value;\n" +
                        "        off = skip_special_symbols(cfg, off + 1);\n" +
                        "        while (cfg[off] != ')' && cfg[off] != '\\0') {\n" +
                        "            byte_value = 0;\n" +
                        "            for (int n = 0; n < 2; n++) {\n" +
                        "                if (cfg[off] >= 'A' && cfg[off] <= 'F') {\n" +
                        "                    byte_value = (byte_value << 4) | ((cfg[off] - 'A') + 10);\n" +
                        "                } else if (cfg[off] >= 'a' && cfg[off] <= 'f') {\n" +
                        "                    byte_value = (byte_value << 4) | ((cfg[off] - 'a') + 10);\n" +
                        "                } else {\n" +
                        "                    byte_value = (byte_value << 4) | (cfg[off] - '0');\n" +
                        "                }\n" +
                        "                off++;\n" +
                        "            }\n" +
                        "            buf.write_bin(&byte_value, 1);\n" +
                        "\n" +
                        "            off = skip_special_symbols(cfg, off);\n" +
                        "            if (cfg[off] == ')') {\n" +
                        "                break;\n" +
                        "            }\n" +
                        "            if (cfg[off] != ',') {\n" +
                        "                printf(\"%s\", \"JSON parser error: Wrong data dytes delimiter\");\n" +
                        "                out->attr_free();\n" +
                        "                return -1;\n" +
                        "            }\n" +
                        "            off = skip_special_symbols(cfg, off + 1);\n" +
                        "        }\n" +
                        "        if (cfg[off] != ')') {\n" +
                        "            printf(\"%s\", \"JSON parser error: Wrong data format\");\n" +
                        "            out->attr_free();\n" +
                        "            return -1;\n" +
                        "        }\n" +
                        "        out->make_data(buf.size(), buf.getBuffer());\n" +
                        "        off = skip_special_symbols(cfg, off + 1);\n" +
                        "    } else if (cfg[off] == 'N' && cfg[off + 1] == 'o' && cfg[off + 2] == 'n'\n" +
                        "                && cfg[off + 3] == 'e') {\n" +
                        "        out->make_nil();\n" +
                        "        off = skip_special_symbols(cfg, off + 4);\n" +
                        "    } else if ((cfg[off] == 'f' || cfg[off] == 'F') && cfg[off + 1] == 'a'\n" +
                        "            && cfg[off + 2] == 'l' && cfg[off + 3] == 's'\n" +
                        "            && cfg[off + 4] == 'e') {\n" +
                        "        out->make_boolean(false);\n" +
                        "        off = skip_special_symbols(cfg, off + 5);\n" +
                        "    } else if ((cfg[off] == 't' || cfg[off] == 'T') && cfg[off + 1] == 'r'\n" +
                        "            && cfg[off + 2] == 'u' && cfg[off + 3] == 'e') {\n" +
                        "        out->make_boolean(true);\n" +
                        "        off = skip_special_symbols(cfg, off + 4);\n" +
                        "    } else {\n" +
                        "        char digits[64] = {0};\n" +
                        "        int digits_cnt = 0;\n" +
                        "        bool negative = false;\n" +
                        "        if (cfg[off] == '0' && cfg[off + 1] == 'x') {\n" +
                        "            off += 2;\n" +
                        "            digits[digits_cnt++] = '0';\n" +
                        "            digits[digits_cnt++] = 'x';\n" +
                        "        } else if (cfg[off] == '-') {\n" +
                        "            negative = true;\n" +
                        "            off++;\n" +
                        "        }\n" +
                        "        while (digits_cnt < 63 && ((cfg[off] >= '0' && cfg[off] <= '9')\n" +
                        "            || (cfg[off] >= 'a' && cfg[off] <= 'f')\n" +
                        "            || (cfg[off] >= 'A' && cfg[off] <= 'F'))) {\n" +
                        "            digits[digits_cnt++] = cfg[off++];\n" +
                        "            digits[digits_cnt] = 0;\n" +
                        "        }\n" +
                        "        int64_t t1 = strtoull(digits, NULL, 0);\n" +
                        "        if (cfg[off] == '.') {\n" +
                        "            digits_cnt = 0;\n" +
                        "            digits[0] = 0;\n" +
                        "            double divrate = 1.0;\n" +
                        "            double d1 = static_cast<double>(t1);\n" +
                        "            off++;\n" +
                        "            bool trim_zeros = true;\n" +
                        "            while (digits_cnt < 63 && cfg[off] >= '0' && cfg[off] <= '9') {\n" +
                        "                if (trim_zeros && cfg[off] == '0') {\n" +
                        "                    off++;\n" +
                        "                    divrate *= 10;      // Fix: strtoull(0008) gives 0\n" +
                        "                    continue;\n" +
                        "                }\n" +
                        "                trim_zeros = false;\n" +
                        "                digits[digits_cnt++] = cfg[off++];\n" +
                        "                digits[digits_cnt] = 0;\n" +
                        "                divrate *= 10.0;\n" +
                        "            }\n" +
                        "            t1 = strtoull(digits, NULL, 0);\n" +
                        "            d1 += static_cast<double>(t1)/divrate;\n" +
                        "            if (negative) {\n" +
                        "                d1 = -d1;\n" +
                        "            }\n" +
                        "            out->make_floating(d1);\n" +
                        "        } else {\n" +
                        "            if (negative) {\n" +
                        "                t1 = -t1;\n" +
                        "            }\n" +
                        "            out->make_int64(t1);\n" +
                        "        }\n" +
                        "        off = skip_special_symbols(cfg, off);\n" +
                        "    }\n" +
                        "    /** Guard to skip wrong formatted string and avoid hanging: */\n" +
                        "    if (off == checkstart) {\n" +
                        "        printf(\"%s\", \"JSON parser error: Can't detect format\");\n" +
                        "        out->attr_free();\n" +
                        "        return -1;\n" +
                        "    }\n" +
                        "    return 0;\n" +
                        "}";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_autobuffer_cpp_file(String Project_Folder_File) {
        String data =   "/*\n" +
                        " *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com\n" +
                        " *\n" +
                        " *  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        " *  you may not use this file except in compliance with the License.\n" +
                        " *  You may obtain a copy of the License at\n" +
                        " *\n" +
                        " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
                        " *\n" +
                        " *  Unless required by applicable law or agreed to in writing, software\n" +
                        " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        " *  See the License for the specific language governing permissions and\n" +
                        " *  limitations under the License.\n" +
                        " */\n" +
                        "\n" +
                        "#include \"autobuffer.h\"\n" +
                        "#include <stdlib.h>\n" +
                        "#include <cstdio>\n" +
                        "#include <cstring>  // memcpy definition\n" +
                        "\n" +
                        "#if defined(_WIN32) || defined(__CYGWIN__)\n" +
                        "    #define RV_PRI64 \"I64\"\n" +
                        "#else /* Linux */\n" +
                        "    # if defined(__WORDSIZE) && (__WORDSIZE == 64)\n" +
                        "    #  define RV_PRI64 \"l\"\n" +
                        "    # else\n" +
                        "    #  define RV_PRI64 \"ll\"\n" +
                        "    # endif\n" +
                        "#endif\n" +
                        "\n" +
                        "\n" +
                        "AutoBuffer::AutoBuffer() {\n" +
                        "    buf_ = NULL;\n" +
                        "    buf_len_ = 0;\n" +
                        "    buf_size_ = 0;\n" +
                        "}\n" +
                        "\n" +
                        "AutoBuffer::~AutoBuffer() {\n" +
                        "    if (buf_) {\n" +
                        "        delete [] buf_;\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "void AutoBuffer::write_bin(const char *p, int sz) {\n" +
                        "    if (buf_len_ + sz >= buf_size_) {\n" +
                        "        if (buf_size_ == 0) {\n" +
                        "            buf_size_ = 1024;\n" +
                        "            buf_ = new char[buf_size_];\n" +
                        "        } else {\n" +
                        "            while (buf_len_ + sz >= buf_size_) {\n" +
                        "                buf_size_ <<= 1;\n" +
                        "            }\n" +
                        "            char *t1 = new char[buf_size_];\n" +
                        "            memcpy(t1, buf_, buf_len_);\n" +
                        "            delete [] buf_;\n" +
                        "            buf_ = t1;\n" +
                        "        }\n" +
                        "    }\n" +
                        "    memcpy(&buf_[buf_len_], p, sz);\n" +
                        "    buf_len_ += sz;\n" +
                        "    buf_[buf_len_] = '\\0';\n" +
                        "}\n" +
                        "\n" +
                        "void AutoBuffer::write_string(const char s) {\n" +
                        "    write_bin(&s, 1);\n" +
                        "}\n" +
                        "\n" +
                        "void AutoBuffer::write_string(const char *s) {\n" +
                        "    write_bin(s, static_cast<int>(strlen(s)));\n" +
                        "}\n" +
                        "\n" +
                        "void AutoBuffer::write_uint64(uint64_t v) {\n" +
                        "    char tmp[128];\n" +
                        "    int sz = sprintf(tmp, \"0x%\" RV_PRI64 \"x\", v);\n" +
                        "    write_bin(tmp, sz);\n" +
                        "}\n" +
                        "\n" +
                        "void AutoBuffer::write_byte(uint8_t v) {\n" +
                        "    char tmp[8];\n" +
                        "    int sz = sprintf(tmp, \"0x%02X\", v);\n" +
                        "    write_bin(tmp, sz);\n" +
                        "}";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_attribute_h_file(String Project_Folder_File) {
        String data =   "/*\n" +
                        " *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com\n" +
                        " *\n" +
                        " *  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        " *  you may not use this file except in compliance with the License.\n" +
                        " *  You may obtain a copy of the License at\n" +
                        " *\n" +
                        " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
                        " *\n" +
                        " *  Unless required by applicable law or agreed to in writing, software\n" +
                        " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        " *  See the License for the specific language governing permissions and\n" +
                        " *  limitations under the License.\n" +
                        " */\n" +
                        "\n" +
                        "#ifndef __DEBUGGER_ATTRIBUTE_H__\n" +
                        "#define __DEBUGGER_ATTRIBUTE_H__\n" +
                        "\n" +
                        "#include <stdint.h>\n" +
                        "#include <string.h>\n" +
                        "#include \"iattr.h\"\n" +
                        "\n" +
                        "enum KindType {\n" +
                        "        Attr_Invalid,\n" +
                        "        Attr_String,\n" +
                        "        Attr_Integer,\n" +
                        "        Attr_UInteger,\n" +
                        "        Attr_Floating,\n" +
                        "        Attr_List,\n" +
                        "        Attr_Data,\n" +
                        "        Attr_Nil,\n" +
                        "        Attr_Dict,\n" +
                        "        Attr_Boolean,\n" +
                        "        Attr_Interface,\n" +
                        "        Attr_PyObject,\n" +
                        "};\n" +
                        "\n" +
                        "class AttributePairType;\n" +
                        "\n" +
                        "class AttributeType : public IAttribute {\n" +
                        " public:\n" +
                        "    KindType kind_;\n" +
                        "    unsigned size_;\n" +
                        "    union {\n" +
                        "        char *string;\n" +
                        "        int64_t integer;\n" +
                        "        bool boolean;\n" +
                        "        double floating;\n" +
                        "        AttributeType *list;\n" +
                        "        AttributePairType *dict;\n" +
                        "        uint8_t *data;\n" +
                        "        uint8_t data_bytes[8];  // Data without allocation\n" +
                        "        void *py_object;\n" +
                        "        IFace *iface;\n" +
                        "        char *uobject;\n" +
                        "    } u_;\n" +
                        "\n" +
                        "    AttributeType(const AttributeType& other) {\n" +
                        "        size_ = 0;\n" +
                        "        clone(&other);\n" +
                        "    }\n" +
                        "\n" +
                        "    AttributeType() {\n" +
                        "        kind_ = Attr_Invalid;\n" +
                        "        size_ = 0;\n" +
                        "        u_.integer = 0;\n" +
                        "    }\n" +
                        "    ~AttributeType() {\n" +
                        "        attr_free();\n" +
                        "    }\n" +
                        "\n" +
                        "    /** IAttribute */\n" +
                        "    virtual void allocAttrName(const char *name);\n" +
                        "    virtual void freeAttrName();\n" +
                        "    virtual void allocAttrDescription(const char *descr);\n" +
                        "    virtual void freeAttrDescription();\n" +
                        "\n" +
                        "    void attr_free();\n" +
                        "\n" +
                        "    explicit AttributeType(const char *str) {\n" +
                        "        make_string(str);\n" +
                        "    }\n" +
                        "\n" +
                        "    explicit AttributeType(IFace *mod) {\n" +
                        "        kind_ = Attr_Interface;\n" +
                        "        u_.iface = mod;\n" +
                        "    }\n" +
                        "\n" +
                        "    explicit AttributeType(KindType type) {\n" +
                        "        kind_ = type;\n" +
                        "        size_ = 0;\n" +
                        "        u_.integer = 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    explicit AttributeType(bool val) {\n" +
                        "        kind_ = Attr_Boolean;\n" +
                        "        size_ = 0;\n" +
                        "        u_.boolean = val;\n" +
                        "    }\n" +
                        "\n" +
                        "    AttributeType(KindType type, uint64_t v) {\n" +
                        "        if (type == Attr_Integer) {\n" +
                        "            make_int64(static_cast<int64_t>(v));\n" +
                        "        } else if (type == Attr_UInteger) {\n" +
                        "            make_uint64(v);\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    unsigned size() const { return size_; }\n" +
                        "\n" +
                        "    bool is_floating() const {\n" +
                        "        return kind_ == Attr_Floating;\n" +
                        "    }\n" +
                        "\n" +
                        "    double to_float() const {\n" +
                        "        return u_.floating;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_integer() const {\n" +
                        "        return kind_ == Attr_Integer || kind_ == Attr_UInteger;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_int64() const {\n" +
                        "        return kind_ == Attr_Integer;\n" +
                        "    }\n" +
                        "\n" +
                        "    int to_int() const {\n" +
                        "        return static_cast<int>(u_.integer);\n" +
                        "    }\n" +
                        "\n" +
                        "    uint32_t to_uint32() const {\n" +
                        "        return static_cast<uint32_t>(u_.integer);\n" +
                        "    }\n" +
                        "\n" +
                        "    int64_t to_int64() const {\n" +
                        "        return u_.integer;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_uint64() const {\n" +
                        "        return kind_ == Attr_UInteger;\n" +
                        "    }\n" +
                        "\n" +
                        "    uint64_t to_uint64() const {\n" +
                        "        return u_.integer;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_bool() const {\n" +
                        "        return kind_ == Attr_Boolean;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool to_bool() const { return u_.boolean; }\n" +
                        "\n" +
                        "    bool is_string() const {\n" +
                        "        return kind_ == Attr_String;\n" +
                        "    }\n" +
                        "\n" +
                        "    const char * to_string() const {\n" +
                        "        return u_.string;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_equal(const char *v);\n" +
                        "\n" +
                        "    // capitalize letters in string;\n" +
                        "    const char * to_upper() const {\n" +
                        "        if (kind_ != Attr_String) {\n" +
                        "            return 0;\n" +
                        "        }\n" +
                        "        char *p = u_.string;\n" +
                        "        while (*p) {\n" +
                        "            if (p[0] >= 'a' && p[0] <= 'z') {\n" +
                        "                p[0] = p[0] - 'a' + 'A';\n" +
                        "            }\n" +
                        "            p++;\n" +
                        "        }\n" +
                        "        return u_.string;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_list() const {\n" +
                        "        return kind_ == Attr_List;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_dict() const {\n" +
                        "        return kind_ == Attr_Dict;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_data() const {\n" +
                        "        return kind_ == Attr_Data;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_iface() const {\n" +
                        "        return kind_ == Attr_Interface;\n" +
                        "    }\n" +
                        "\n" +
                        "    IFace *to_iface() const {\n" +
                        "        return u_.iface;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_nil() const {\n" +
                        "        return kind_ == Attr_Nil;\n" +
                        "    }\n" +
                        "\n" +
                        "    bool is_invalid() const {\n" +
                        "        return kind_ == Attr_Invalid;\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    void clone(const AttributeType *v);\n" +
                        "\n" +
                        "    void make_nil() {\n" +
                        "        kind_ = Attr_Nil;\n" +
                        "        size_ = 0;\n" +
                        "        u_.integer = 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    void make_iface(IFace *value) {\n" +
                        "        kind_ = Attr_Interface;\n" +
                        "        u_.iface = value;\n" +
                        "    }\n" +
                        "\n" +
                        "    void make_floating(double value) {\n" +
                        "        kind_ = Attr_Floating;\n" +
                        "        size_ = 0;\n" +
                        "        u_.floating = value;\n" +
                        "    }\n" +
                        "\n" +
                        "    void force_to_floating() {\n" +
                        "        kind_ = Attr_Floating;\n" +
                        "    }\n" +
                        "\n" +
                        "    void make_int64(int64_t value) {\n" +
                        "        kind_ = Attr_Integer;\n" +
                        "        size_ = 0;\n" +
                        "        u_.integer = value;\n" +
                        "    }\n" +
                        "\n" +
                        "    void make_uint64(uint64_t value) {\n" +
                        "        kind_ = Attr_UInteger;\n" +
                        "        size_ = 0;\n" +
                        "        u_.integer = value;\n" +
                        "    }\n" +
                        "\n" +
                        "    void make_boolean(bool value) {\n" +
                        "        kind_ = Attr_Boolean;\n" +
                        "        size_ = 0;\n" +
                        "        u_.boolean = value;\n" +
                        "    }\n" +
                        "\n" +
                        "    void make_string(const char *value);\n" +
                        "\n" +
                        "    void make_data(unsigned size);\n" +
                        "\n" +
                        "    void make_data(unsigned size, const void *data);\n" +
                        "\n" +
                        "    void realloc_data(unsigned size);\n" +
                        "\n" +
                        "    void make_list(unsigned size);\n" +
                        "\n" +
                        "    void add_to_list(const AttributeType *item) {\n" +
                        "        realloc_list(size()+1);\n" +
                        "        (*this)[size()-1] = (*item);\n" +
                        "    }\n" +
                        "\n" +
                        "    void insert_to_list(unsigned idx, const AttributeType *item);\n" +
                        "\n" +
                        "    void remove_from_list(unsigned idx);\n" +
                        "\n" +
                        "    void trim_list(unsigned start, unsigned end);\n" +
                        "\n" +
                        "    void swap_list_item(unsigned n, unsigned m);\n" +
                        "\n" +
                        "    void realloc_list(unsigned size);\n" +
                        "\n" +
                        "    void make_dict();\n" +
                        "    void realloc_dict(unsigned size);\n" +
                        "\n" +
                        "    // Getter:\n" +
                        "    double floating() const { return u_.floating; }\n" +
                        "\n" +
                        "    int64_t integer() const { return u_.integer; }\n" +
                        "\n" +
                        "    const char *string() const { return u_.string; }\n" +
                        "\n" +
                        "    bool boolean() const { return u_.boolean; }\n" +
                        "\n" +
                        "    const AttributeType *list(unsigned idx) const {\n" +
                        "        return &u_.list[idx];\n" +
                        "    }\n" +
                        "    AttributeType *list(unsigned idx) {\n" +
                        "        return &u_.list[idx];\n" +
                        "    }\n" +
                        "\n" +
                        "    /* Quicksort algorithm with 'list' attribute */\n" +
                        "    void sort(int idx = 0);\n" +
                        "\n" +
                        "    bool has_key(const char *key) const;\n" +
                        "\n" +
                        "    const AttributeType *dict_key(unsigned idx) const;\n" +
                        "    AttributeType *dict_key(unsigned idx);\n" +
                        "\n" +
                        "    const AttributeType *dict_value(unsigned idx) const;\n" +
                        "    AttributeType *dict_value(unsigned idx);\n" +
                        "\n" +
                        "    const uint8_t *data() const {\n" +
                        "        if (size_ > 8) {\n" +
                        "            return u_.data;\n" +
                        "        }\n" +
                        "        return u_.data_bytes;\n" +
                        "    }\n" +
                        "    uint8_t *data() {\n" +
                        "        if (size_ > 8) {\n" +
                        "            return u_.data;\n" +
                        "        }\n" +
                        "        return u_.data_bytes;\n" +
                        "    }\n" +
                        "\n" +
                        "    AttributeType& operator=(const AttributeType& other);\n" +
                        "\n" +
                        "    /**\n" +
                        "     * @brief Access to the single element of the 'list' attribute:\n" +
                        "     */\n" +
                        "    const AttributeType& operator[](unsigned idx) const;\n" +
                        "    /** @overload */\n" +
                        "    AttributeType& operator[](unsigned idx);\n" +
                        "\n" +
                        "    /**\n" +
                        "     * @brief Access to the single value attribute of the 'dictionary':\n" +
                        "     */\n" +
                        "    const AttributeType& operator[](const char *key) const;\n" +
                        "    /** @overload */\n" +
                        "    AttributeType& operator[](const char *key);\n" +
                        "\n" +
                        "    /**\n" +
                        "     * @brief Access to the single byte of the 'data' attribute:\n" +
                        "     */\n" +
                        "    const uint8_t& operator()(unsigned idx) const;\n" +
                        "\n" +
                        "    const AttributeType& to_config();\n" +
                        "    void from_config(const char *str);\n" +
                        "};\n" +
                        "\n" +
                        "class AttributePairType {\n" +
                        " public:\n" +
                        "    AttributeType key_;\n" +
                        "    AttributeType value_;\n" +
                        "};\n" +
                        "\n" +
                        "#endif  // __DEBUGGER_ATTRIBUTE_H__";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_autobuffer_h_file(String Project_Folder_File) {
        String data =   "/*\n" +
                        " *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com\n" +
                        " *\n" +
                        " *  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        " *  you may not use this file except in compliance with the License.\n" +
                        " *  You may obtain a copy of the License at\n" +
                        " *\n" +
                        " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
                        " *\n" +
                        " *  Unless required by applicable law or agreed to in writing, software\n" +
                        " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        " *  See the License for the specific language governing permissions and\n" +
                        " *  limitations under the License.\n" +
                        " */\n" +
                        "\n" +
                        "#ifndef __DEBUGGER_AUTOBUFFER_H__\n" +
                        "#define __DEBUGGER_AUTOBUFFER_H__\n" +
                        "\n" +
                        "#include <stdint.h>\n" +
                        "#include <string.h>\n" +
                        "\n" +
                        "/**\n" +
                        " * @brief String buffer declaration.\n" +
                        " * @details This buffer is used to form configuration string of the kernel.\n" +
                        " */\n" +
                        "class AutoBuffer {\n" +
                        " public:\n" +
                        "    /** Create empty string buffer. */\n" +
                        "    AutoBuffer();\n" +
                        "    ~AutoBuffer();\n" +
                        "\n" +
                        "    /**\n" +
                        "     * @brief Write input data of the specified size into buffer's memory.\n" +
                        "     * @param[in] p Pointer on input data.\n" +
                        "     * @param[in] sz Input buffer size in bytes.\n" +
                        "     */\n" +
                        "    void write_bin(const char *p, int sz);\n" +
                        "    /**\n" +
                        "     * @brief Write single symbol into buffer's memory.\n" +
                        "     * @param[in] s Input character value.\n" +
                        "     */\n" +
                        "    void write_string(const char s);\n" +
                        "    /**\n" +
                        "     * @brief Write input string into buffer's memory.\n" +
                        "     * @param[in] s Pointer on string buffer.\n" +
                        "     */\n" +
                        "    void write_string(const char *s);\n" +
                        "    /**\n" +
                        "     * @brief Write integer value as a hex string into buffer's memory.\n" +
                        "     * @param[in] s Input integer value.\n" +
                        "     */\n" +
                        "    void write_uint64(uint64_t v);\n" +
                        "    /**\n" +
                        "     * @brief Write a single byte into buffer's memory.\n" +
                        "     * @details This method is very usefull to write special characters, like\n" +
                        "     *          '\\0', '\\n' etc.\n" +
                        "     * @param[in] s Input byte value.\n" +
                        "     */\n" +
                        "    void write_byte(uint8_t v);\n" +
                        "\n" +
                        "    /**\n" +
                        "     * @brief Get allocated memory pointer.\n" +
                        "     * @return Pointer on allocated memory region.\n" +
                        "     */\n" +
                        "    char *getBuffer() { return buf_; }\n" +
                        "\n" +
                        "    /** Get total number of written symbols. */\n" +
                        "    int size() { return buf_len_; }\n" +
                        "    /** Reset buffer's value. */\n" +
                        "    void clear() {\n" +
                        "        buf_len_ = 0;\n" +
                        "        if (buf_) {\n" +
                        "            buf_[buf_len_] = 0;\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        " private:\n" +
                        "    char *buf_;\n" +
                        "    int buf_len_;\n" +
                        "    int buf_size_;\n" +
                        "};\n" +
                        "\n" +
                        "#endif  // __DEBUGGER_AUTOBUFFER_H__";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_iattr_h_file(String Project_Folder_File) {
        String data =   "/*\n" +
                        " *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com\n" +
                        " *\n" +
                        " *  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        " *  you may not use this file except in compliance with the License.\n" +
                        " *  You may obtain a copy of the License at\n" +
                        " *\n" +
                        " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
                        " *\n" +
                        " *  Unless required by applicable law or agreed to in writing, software\n" +
                        " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        " *  See the License for the specific language governing permissions and\n" +
                        " *  limitations under the License.\n" +
                        " */\n" +
                        "\n" +
                        "#ifndef __DEBUGGER_IATTRIBUTE_H__\n" +
                        "#define __DEBUGGER_IATTRIBUTE_H__\n" +
                        "\n" +
                        "#include \"iface.h\"\n" +
                        "\n" +
                        "static const char *const IFACE_ATTRIBUTE = \"IAttribute\";\n" +
                        "\n" +
                        "class IAttribute : public IFace {\n" +
                        " public:\n" +
                        "    IAttribute() :\n" +
                        "        IFace(IFACE_ATTRIBUTE), attr_name_(NULL), attr_descr_(NULL) {}\n" +
                        "\n" +
                        "    virtual void allocAttrName(const char *name) = 0;\n" +
                        "    virtual void freeAttrName() = 0;\n" +
                        "    virtual const char *getAttrName() { return attr_name_; }\n" +
                        "\n" +
                        "    virtual void allocAttrDescription(const char *descr) = 0;\n" +
                        "    virtual void freeAttrDescription() = 0;\n" +
                        "    virtual const char *getAttrDescription() { return attr_descr_; }\n" +
                        "\n" +
                        "    virtual void postinitAttribute() {}\n" +
                        "\n" +
                        " protected:\n" +
                        "    char *attr_name_;\n" +
                        "    char *attr_descr_;\n" +
                        "};\n" +
                        "\n" +
                        "#endif  // __DEBUGGER_IATTRIBUTE_H__";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_iface_h_file(String Project_Folder_File) {
        String data =   "/*\n" +
                        " *  Copyright 2018 Sergey Khabarov, sergeykhbr@gmail.com\n" +
                        " *\n" +
                        " *  Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                        " *  you may not use this file except in compliance with the License.\n" +
                        " *  You may obtain a copy of the License at\n" +
                        " *\n" +
                        " *      http://www.apache.org/licenses/LICENSE-2.0\n" +
                        " *\n" +
                        " *  Unless required by applicable law or agreed to in writing, software\n" +
                        " *  distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                        " *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                        " *  See the License for the specific language governing permissions and\n" +
                        " *  limitations under the License.\n" +
                        " */\n" +
                        "\n" +
                        "#ifndef __DEBUGGER_IFACE_H__\n" +
                        "#define __DEBUGGER_IFACE_H__\n" +
                        "\n" +
                        "class IFace {\n" +
                        " public:\n" +
                        "    explicit IFace(const char *name) : ifname_(name) {}\n" +
                        "    virtual ~IFace() {}\n" +
                        "\n" +
                        "    /** Get brief information. */\n" +
                        "    virtual const char *getBrief() { return \"Brief info not defined\"; }\n" +
                        "\n" +
                        "    /** Get detailed description. */\n" +
                        "    virtual const char *getDetail() { return \"Detail info not defined\"; }\n" +
                        "\n" +
                        "    /** Get interface name. */\n" +
                        "    const char *getFaceName() { return ifname_; }\n" +
                        "\n" +
                        " protected:\n" +
                        "    const char *ifname_;\n" +
                        "};\n" +
                        "\n" +
                        "#endif  // __DEBUGGER_IFACE_H__";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_hex2mif_makefile_file(String Project_Folder_File) {
        String data =   "all: "+Data.Project_Folder.getPath()+"/"+c_files+"/hex2mif/main.c\n" +
                        "	mkdir -pv "+Data.Project_Folder.getPath()+"/"+c_files+"/hex2mif/bin\n" +
                        "	gcc "+Data.Project_Folder.getPath()+"/"+c_files+"/hex2mif/main.c -o "+Data.Project_Folder.getPath()+"/"+c_files+"/hex2mif/bin/hex2mif\n" +
                        "\n" +
                        "clean:\n" +
                        "	rm -f "+Data.Project_Folder.getPath()+"/"+c_files+"/hex2mif/bin/hex2mif";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_hex2mif_main_c_file(String Project_Folder_File) {
        String data =   "#include <stdio.h>\n" +
                        "#include <stdlib.h>\n" +
                        "#include <string.h>\n" +
                        "#include <unistd.h>     // access function\n" +
                        "\n" +
                        "#define WORD_LENGTH 64\n" +
                        "\n" +
                        "int main(int argc, char **argv) {\n" +
                        "    if (argc != 4) {\n" +
                        "        printf(\"Error arguments\\n\");\n" +
                        "    } else {\n" +
                        "        char * hex_file_name = argv[1];\n" +
                        "        char * mif_file_name = argv[2];\n" +
                        "        char * size_s = argv[3];\n" +
                        "        int size_i = atoi(size_s);\n" +
                        "        FILE *fp_in;\n" +
                        "        FILE *fp_out;\n" +
                        "        size_t len = 0;\n" +
                        "        char *line = NULL;\n" +
                        "        int i = 0;\n" +
                        "\n" +
                        "        fp_in = fopen(hex_file_name, \"r\");\n" +
                        "        if( access(mif_file_name, F_OK ) != -1 ) {\n" +
                        "            fp_out = fopen(mif_file_name, \"w+\");\n" +
                        "        } else {\n" +
                        "            fp_out = fopen(mif_file_name, \"ab+\");\n" +
                        "        }\n" +
                        "        fprintf(fp_out, \"WIDTH=%d;\\n\", WORD_LENGTH);\n" +
                        "        fprintf(fp_out, \"DEPTH=%d;\\n\\n\", size_i);\n" +
                        "        fprintf(fp_out, \"ADDRESS_RADIX=UNS;\\n\");\n" +
                        "        fprintf(fp_out, \"DATA_RADIX=HEX;\\n\\n\");\n" +
                        "        fprintf(fp_out, \"CONTENT BEGIN\\n\");\n" +
                        "        \n" +
                        "        while ((getline(&line, &len, fp_in)) != -1) {   \n" +
                        "            line[WORD_LENGTH/4] = 0;\n" +
                        "            fprintf(fp_out, \"\\t%d: %s;\\n\", i, line);\n" +
                        "            i++;\n" +
                        "        }\n" +
                        "        \n" +
                        "        fprintf(fp_out, \"\\nEND;\");\n" +
                        "        \n" +
                        "        fclose(fp_in);\n" +
                        "        fclose(fp_out);\n" +
                        "        }\n" +
                        "\n" +
                        "    return 0;\n" +
                        "}";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void write_project_main_makefile_file(String Project_Folder_File) {
        String data =   "include $(PROJECT_FOLDER)"+Data.Project_Name+"_application/makefiles/makeutil.mak\n" +
                        "\n" +
                        "CC=/opt/riscv64/bin/riscv64-unknown-elf-gcc\n" +
                        "CPP=/opt/riscv64/bin/riscv64-unknown-elf-gcc\n" +
                        "OBJDUMP=/opt/riscv64/bin/riscv64-unknown-elf-objdump\n" +
                        "ELF2HEX=$(PROJECT_FOLDER)elf2rawx/elf/elf2rawx\n" +
                        "HEX2MIF=$(PROJECT_FOLDER)hex2mif/bin/hex2mif\n" +
                        "\n" +
                        "OBJ_DIR = $(PROJECT_FOLDER)"+Data.Project_Name+"_application/obj\n" +
                        "ELF_DIR = $(PROJECT_FOLDER)"+Data.Project_Name+"_application/bin\n" +
                        "\n" +
                        "FPU_ENABLED="+new GeneralFunctions().bool2int(Data.is_fpu_RV64_enabeled)+"\n" +
                        "\n" +
                        "CFLAGS= -c -g -static -std=gnu99 -Ofast -fno-common -fno-builtin-printf\n" +
                        "ifeq ($(FPU_ENABLED), 1)\n" +
                        "  CFLAGS += -march=rv64imafd -DFPU_ENABLED\n" +
                        "else\n" +
                        "  CFLAGS += -march=rv64imac -mabi=lp64\n" +
                        "endif\n" +
                        "\n" +
                        "\n" +
                        "LDFLAGS=-static -T $(PROJECT_FOLDER)"+Data.Project_Name+"_application/makefiles/app.ld -nostartfiles\n" +
                        "ifeq ($(FPU_ENABLED), 1)\n" +
                        "else\n" +
                        "  LDFLAGS += -march=rv64imac -mabi=lp64\n" +
                        "endif\n" +
                        "\n" +
                        "\n" +
                        "INCL_KEY=-I\n" +
                        "DIR_KEY=-B\n" +
                        "\n" +
                        "\n" +
                        "# include sub-folders list\n" +
                        "INCL_PATH=\\\n" +
                        "	$(PROJECT_FOLDER)common \\\n" +
                        "	$(PROJECT_FOLDER)"+Data.Project_Name+"_application/src\n" +
                        "\n" +
                        "# source files directories list:\n" +
                        "SRC_PATH = \\\n" +
                        "	$(PROJECT_FOLDER)"+Data.Project_Name+"_application/src\n" +
                        "\n" +
                        "LIB_NAMES = \\\n" +
                        "	gcc \\\n" +
                        "	stdc++ \\\n" +
                        "	c \\\n" +
                        "	m\n" +
                        "\n" +
                        "VPATH = $(SRC_PATH)\n" +
                        "\n" +
                        "SOURCES = \\\n" +
                        "	main \\\n" +
                        "	"+Data.Project_Name+"\n" +
                        "\n" +
                        "OBJ_FILES = $(addsuffix .o,$(SOURCES))\n" +
                        "EXECUTABLE = "+Data.Project_Name+"\n" +
                        "DUMPFILE = $(EXECUTABLE).dump\n" +
                        "HEXFILE = $(EXECUTABLE).hex\n" +
                        "MIFFILE = $(EXECUTABLE).mif\n" +
                        "LSTFILE = $(EXECUTABLE).lst\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "all: $(EXECUTABLE) $(DUMPFILE) $(HEXFILE) $(MIFFILE)\n" +
                        "\n" +
                        "$(MIFFILE): $(HEXFILE)\n" +
                        "	$(HEX2MIF) $(ELF_DIR)/$(HEXFILE) $(ELF_DIR)/$(MIFFILE) 8192\n" +
                        "\n" +
                        "$(HEXFILE): $(EXECUTABLE)\n" +
                        "	$(ELF2HEX) $(addprefix $(ELF_DIR)/,$<) -h -f 65536 -l 8 -o $(addprefix $(ELF_DIR)/,$(EXECUTABLE).hex)\n" +
                        "\n" +
                        "$(DUMPFILE): $(EXECUTABLE)\n" +
                        "	$(OBJDUMP) --disassemble-all --disassemble-zeroes --section=.text --section=.text.startup --section=.data $(addprefix $(ELF_DIR)/,$<) > $(addprefix $(ELF_DIR)/,$@)\n" +
                        "	$(OBJDUMP) -S $(addprefix $(ELF_DIR)/,$<) > $(addprefix $(ELF_DIR)/,$(LSTFILE))\n" +
                        "\n" +
                        "$(EXECUTABLE): $(OBJ_FILES)\n" +
                        "	$(MKDIR) $(ELF_DIR)\n" +
                        "	$(CPP) $(LDFLAGS) $(addprefix $(OBJ_DIR)/,$(OBJ_FILES)) -o $(addprefix $(ELF_DIR)/,$@) $(addprefix -l,$(LIB_NAMES))\n" +
                        "	$(ECHO) \"\\n  "+Data.Project_Name+" has been built successfully.\\n\"\n" +
                        "\n" +
                        "#.cpp.o:\n" +
                        "%.o: %.cpp\n" +
                        "	$(MKDIR) $(OBJ_DIR)\n" +
                        "	$(CPP) $(CFLAGS) $(addprefix $(INCL_KEY),$(INCL_PATH)) $< -o $(addprefix $(OBJ_DIR)/,$@)\n" +
                        "\n" +
                        "#.c.o:\n" +
                        "%.o: %.c\n" +
                        "	$(MKDIR) $(OBJ_DIR)\n" +
                        "	$(CC) $(CFLAGS) $(addprefix $(INCL_KEY),$(INCL_PATH)) $< -o $(addprefix $(OBJ_DIR)/,$@)\n" +
                        "\n" +
                        "%.o: %.S\n" +
                        "	$(MKDIR) $(OBJ_DIR)\n" +
                        "	$(CC) $(CFLAGS) -D__ASSEMBLY__=1 $(addprefix $(INCL_KEY),$(INCL_PATH)) $< -o $(addprefix $(OBJ_DIR)/,$@)";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_app_ld_file(String Project_Folder_File) {
        String data =   "OUTPUT_ARCH( \"riscv\" )\n" +
                        "\n" +
                        "/*----------------------------------------------------------------------*/\n" +
                        "/* Sections                                                             */\n" +
                        "/*----------------------------------------------------------------------*/\n" +
                        "SECTIONS\n" +
                        "{\n" +
                        "\n" +
                        "  /* text: test code section */\n" +
                        "  . = 0x10000000;\n" +
                        "  .text : \n" +
                        "  {\n" +
                        "    "+Data.Project_Folder.getPath()+"/"+c_files+"/"+Data.Project_Name+"_application/obj/main.o (.text.startup)\n" +
                        "    *(.text)\n" +
                        "  }\n" +
                        "\n" +
                        "  /* data segment */\n" +
                        "  .data : { *(.data) }\n" +
                        "\n" +
                        "  .sdata : {\n" +
                        "    *(.srodata.cst16) *(.srodata.cst8) *(.srodata.cst4) *(.srodata.cst2) *(.srodata*)\n" +
                        "    *(.sdata .sdata.* .gnu.linkonce.s.*)\n" +
                        "  }\n" +
                        "\n" +
                        "  /* bss segment */\n" +
                        "  .sbss : {\n" +
                        "    *(.sbss .sbss.* .gnu.linkonce.sb.*)\n" +
                        "    *(.scommon)\n" +
                        "  }\n" +
                        "  .bss : { *(.bss) }\n" +
                        "\n" +
                        "  /* thread-local data segment */\n" +
                        "  .tdata :\n" +
                        "  {\n" +
                        "    *(.tdata)\n" +
                        "  }\n" +
                        "  .tbss :\n" +
                        "  {\n" +
                        "    *(.tbss)\n" +
                        "  }\n" +
                        "\n" +
                        "  /* End of uninitalized data segement */\n" +
                        "  _end = .;\n" +
                        "\n" +
                        "}";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    private void write_project_main_main_c_file(String Project_Folder_File) {
        String data =   "/*****************************************************************************\n" +
                        " * @file\n" +
                        " * @author   Hossameldin Eassa\n" +
                        " * @brief    Main entry function for real PLC application. \n" +
                        " * @details  This file matches to linker symbol '.text.startup' and will be\n" +
                        "*            assigned to default entry point 0x10000000. See linker script.\n" +
                        " * @warning  DO NOT ADD NEW METHODS INTO THIS FILE\n" +
                        " ****************************************************************************/\n" +
                        "\n" +
                        "extern void "+Data.Project_Name+"();\n" +
                        "\n" +
                        "int main() {\n" +
                        "    "+Data.Project_Name+"();\n" +
                        "    while(1);\n" +
                        "    return 0;\n" +
                        "}";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
      
    private void generate_test_vhd_file(String Project_Folder_File) {
        String data =   "";
        new GeneralFunctions().write_file(Project_Folder_File, data);
    }
    
    public void declareAndInitializeVariables() {
        String Variable_temp;
        String typeOfVariable;
        String nameOfVariable;
        String Type;
        for (int i = 1; i < Data.size_Vaiables-1; i++) {
            Variable_temp = Data.Vaiables[i].replace(" ", "");
            nameOfVariable = Variable_temp.split(":")[0];
            typeOfVariable = Variable_temp.split(":")[1];
            switch (typeOfVariable) {
                case "INT":
                    Type = "uint32_t";
                    break;
                case "BOOL":
                    Type = "uint32_t";
                    break;
                case "REAL":
                    Type = "double";
                    break;
                case "TIME":
                    Type = "uint64_t";
                    break;
                case "TON":
                    Type = "Timer";
                    break;
                case "TOF":
                    Type = "Timer";
                    break;
                case "PWM":
                    Type = "Timer";
                    break;
                default:
                    Type = "NotSupported";
                    break;
            }
            if (!Type.equals("Timer")) Data.C_code += Type+" "+nameOfVariable+" = 0;\n";
        }
    }
}
