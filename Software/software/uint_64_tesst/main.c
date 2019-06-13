#include <stdint.h>

void exception_handler(uint32_t cause, void * epc, void * regbase)
{
	
}

int main(void)
{
	volatile uint64_t var0 = 0x00007001ABABABAB;
	volatile uint64_t var1 = 0x00007001ABABABAB;

	volatile uint64_t var3 = 0x0000000000000000;

    if (var0 == var1) {
        var3 = 0x00030B00AAAAAAAA;
    } else {
        var3 = 0x00030B00BBBBBBBB;
    }
    
    return 0;
}
