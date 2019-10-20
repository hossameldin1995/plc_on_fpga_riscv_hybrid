#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>     // access function

#include"compile_c_file.h"

#define BOOT_SIZE 4096

void removeAll(char* word, char x); // remove all char x in word

JNIEXPORT jboolean JNICALL Java_rv_1fpga_1plc_1ide_helper_compile_1c_1file_compile_1c_1to_1mif
  (JNIEnv * env, jobject obj, jstring c_Folder_path, jstring c_File_path){
    
    int res = 0;
    char command[1000];
    char hex_file[500];
    char mif_file[500];
    char arch_s[500];
    char compiler_path_s[500];
    
    strcpy(arch_s, "-march=rv32i -mabi=ilp32");
    strcpy(compiler_path_s, "/opt/riscv32/bin/");
    
    FILE *fp_in;
    FILE *fp_out;
    
    size_t len = 0;
    char *line = NULL;

    int i = 0;
    
    strcpy(hex_file, (*env)->GetStringUTFChars(env, c_File_path, 0));
    strcat(hex_file, ".hex");
    //strcpy(mif_file, (*env)->GetStringUTFChars(env, c_File_path, 0));
    strcpy(mif_file, (*env)->GetStringUTFChars(env, c_Folder_path, 0));
    strcat(mif_file, "/bootloader.mif");
    
    if( access(hex_file, F_OK ) != -1 ) {
        fp_out = fopen(hex_file, "w+");
    } else {
        fp_out = fopen(hex_file, "ab+");
    }
    fclose(fp_out);
    
    strcpy(command, compiler_path_s);
    strcat(command, "riscv32-unknown-elf-gcc -c -o \"");
    strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
    strcat(command, ".o\" ");
    strcat(command, arch_s);
    strcat(command, " -Wall -Os -fomit-frame-pointer -ffreestanding -fno-builtin -I");
    strcat(command, (*env)->GetStringUTFChars(env, c_Folder_path, 0));
    strcat(command, " -std=gnu99 -Werror=implicit-function-declaration -ffunction-sections -fdata-sections \"");
    strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
    strcat(command, ".c\" ");
    printf("%s\n", command);
    fflush(stdout);
    res = system(command);
    
    // ASM
    if (!res) {
        strcpy(command, compiler_path_s);
        strcat(command, "riscv32-unknown-elf-gcc ");
        strcat(command, arch_s);
        strcat(command, " -Wall -Os -fomit-frame-pointer -ffreestanding -fno-builtin -I");
        strcat(command, (*env)->GetStringUTFChars(env, c_Folder_path, 0));
        strcat(command, " -std=gnu99 -Werror=implicit-function-declaration -ffunction-sections -fdata-sections -S -o \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
        strcat(command, ".asm\" \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
        strcat(command, ".c\" ");
        printf("%s\n", command);
        fflush(stdout);
        res += system(command);
    }
    
    if (!res) {
        strcpy(command, compiler_path_s);
        strcat(command, "riscv32-unknown-elf-gcc -DCOPY_DATA_TO_RAM -c -o \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_Folder_path, 0));
        strcat(command, "/start.o\" ");
        strcat(command, arch_s);
        strcat(command, " -Os -fomit-frame-pointer -ffreestanding -fno-builtin -I");
        strcat(command, (*env)->GetStringUTFChars(env, c_Folder_path, 0));
        strcat(command, " -std=gnu99 -Wall -Werror=implicit-function-declaration -ffunction-sections -fdata-sections \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_Folder_path, 0));
        strcat(command, "/start.S\" ");
        printf("%s\n", command);
        fflush(stdout);
        res += system(command);
    }
    
    if (!res) {
        strcpy(command, compiler_path_s);
        strcat(command, "riscv32-unknown-elf-gcc -o \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
        strcat(command, ".elf\" ");
        strcat(command, arch_s);
        strcat(command, " -nostartfiles -L");
        strcat(command, (*env)->GetStringUTFChars(env, c_Folder_path, 0));
        strcat(command, " -Wl,-m,elf32lriscv --specs=nosys.specs -Wl,--no-relax -Wl,--gc-sections -Wl,-T\"");
        strcat(command, (*env)->GetStringUTFChars(env, c_Folder_path, 0));
        strcat(command, "/load.ld\" -Wl,--Map,");
        strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
        strcat(command, ".map \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
        strcat(command, ".o\" \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_Folder_path, 0));
        strcat(command, "/start.o\"");
        printf("%s\n", command);
        fflush(stdout);
        res += system(command);
    }
    
    if (!res) {
        strcpy(command, compiler_path_s);
        strcat(command, "riscv32-unknown-elf-size \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
        strcat(command, ".elf\"");
        printf("%s\n", command);
        fflush(stdout);
        res += system(command);
    }
    
    if (!res) {
        strcpy(command, compiler_path_s);
        strcat(command, "riscv32-unknown-elf-objcopy -j .text -j .data -j .rodata -O binary \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
        strcat(command, ".elf\" \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
        strcat(command, ".bin\"");
        printf("%s\n", command);
        fflush(stdout);
        res += system(command);
    }
    
    if (!res) {
        strcpy(command, "hexdump -v -e '1/4 \"%08x\\n\"' \"");
        strcat(command, (*env)->GetStringUTFChars(env, c_File_path, 0));
        strcat(command, ".bin\" >>");
        strcat(command, hex_file);
        printf("%s\n", command);
        res += system(command);
    }
    fflush(stdout);
    
    fp_in = fopen(hex_file, "r");
    if( access(mif_file, F_OK ) != -1 ) {
        fp_out = fopen(mif_file, "w+");
    } else {
        fp_out = fopen(mif_file, "ab+");
    }
    fprintf(fp_out, "WIDTH=32;\n");
    fprintf(fp_out, "DEPTH=%d;\n\n", BOOT_SIZE);
    fprintf(fp_out, "ADDRESS_RADIX=UNS;\n");
    fprintf(fp_out, "DATA_RADIX=HEX;\n\n");
    fprintf(fp_out, "CONTENT BEGIN\n");
    
    while ((getline(&line, &len, fp_in)) != -1) {   
        removeAll(line, '\n');
        if (strcmp(line, ";")) {
            fprintf(fp_out, "\t%d: %s;\n", i, line);
        }
        i++;
    }
    
    fprintf(fp_out, "[%d..%d]  :   00000000;\nEND;", i, (BOOT_SIZE-1));
    
    fclose(fp_in);
    fclose(fp_out);
    
    if (res) return 0;
    else return 1;
}

JNIEXPORT jboolean JNICALL Java_rv_1fpga_1plc_1ide_helper_compile_1c_1file_compile_1download_1to_1soc
  (JNIEnv * env, jobject obj, jstring c_Folder_path) {
    int res = 0;
    char command[1000];
    strcpy(command, "/home/hossameldin/intelFPGA_lite/18.0/quartus/bin/quartus_pgm -m jtag -c 1 -o \"p;");
    strcat(command, (*env)->GetStringUTFChars(env, c_Folder_path, 0));
    strcat(command, "/output_files/RV_FPGA_PLC_Potato.sof\"");
    printf("%s\n", command);
    res = system(command);
    fflush(stdout);
    
    if (res) return 0;
    else return 1;
}

void removeAll(char* word, char x) { // remove all char x in word
    int i, j;
    for(i=0; word[i] != 0; ++i ) {
        while(word[i] == x) { /* copy all chars, including NULL at end, over char to left */
            j=i;
            while(word[j]!=0) {
                word[j]=word[j+1];
                ++j;
            }
        }
    }   
}
