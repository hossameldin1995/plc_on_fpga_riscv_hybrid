all: src/hex2mif.c
	gcc src/hex2mif.c -o bin/hex2mif
	hex2mif ./examples/bootimage.hex ./examples/bootimage.mif 4096

clean:
	rm -f bin/hex2mif