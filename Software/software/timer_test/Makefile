# The Potato Processor Software Components
# (c) Kristian Klomsten Skordal 2015 <kristian.skordal@wafflemail.net>
# Report bugs and issues on <https://github.com/skordal/potato/issues>

.PHONY: all clean
include ../common.mk

TARGET_LDFLAGS += -Wl,-Ttimer_test.ld -Wl,--Map,timer_test.map

OBJECTS := main.o start.o

all: timer_test.elf timer_test.bin timer_test.coe timer_test.mif

timer_test.elf: $(OBJECTS)
	$(TARGET_LD) -o timer_test.elf $(TARGET_LDFLAGS) $(OBJECTS)
	$(TARGET_SIZE) timer_test.elf

clean:
	-$(RM) $(OBJECTS)
	-$(RM) timer_test.elf timer_test.bin timer_test.coe bootloader.mif

# Object file rules:

main.o: main.c ../../potato.h
	$(TARGET_CC) -c -o $@ $(TARGET_CFLAGS) $<

start.o: ../start.S
	$(TARGET_CC) -DCOPY_DATA_TO_RAM -c -o $@ $(TARGET_CFLAGS) $<

