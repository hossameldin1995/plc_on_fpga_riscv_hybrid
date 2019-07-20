# The Potato Processor Software Components
# (c) Kristian Klomsten Skordal 2015 <kristian.skordal@wafflemail.net>
# Report bugs and issues on <https://github.com/skordal/potato/issues>

.PHONY: all clean
include ../common.mk

TARGET_LDFLAGS += -Wl,-Tboot_and_test_hardware.ld -Wl,--Map,boot_and_test_hardware.map

OBJECTS := main.o start.o

all: boot_and_test_hardware.elf boot_and_test_hardware.bin boot_and_test_hardware.coe boot_and_test_hardware.mif

boot_and_test_hardware.elf: $(OBJECTS)
	$(TARGET_LD) -o boot_and_test_hardware.elf $(TARGET_LDFLAGS) $(OBJECTS)
	$(TARGET_SIZE) boot_and_test_hardware.elf

clean:
	-$(RM) $(OBJECTS)
	-$(RM) boot_and_test_hardware.elf boot_and_test_hardware.bin boot_and_test_hardware.coe bootloader.mif

# Object file rules:

main.o: main.c ../../potato.h
	$(TARGET_CC) -c -o $@ $(TARGET_CFLAGS) $<

start.o: ../start.S
	$(TARGET_CC) -DCOPY_DATA_TO_RAM -c -o $@ $(TARGET_CFLAGS) $<

