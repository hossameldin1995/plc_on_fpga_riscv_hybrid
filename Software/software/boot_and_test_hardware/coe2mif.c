#include<stdio.h>
#include<unistd.h>     // access function
#include<string.h>

#define BOOT_SIZE 4096

void removeAll(char* word, char x); // remove all char x in word

int main() {
    printf("Converting COE to MIF ...\n");
    
    FILE *fp_in;
    FILE *fp_out;
    
    size_t len = 0;
    char *line = NULL;

    int i = 0;

    fp_in = fopen("boot_and_test_hardware.coe", "r");
    if( access("bootloader.mif", F_OK ) != -1 ) {
        fp_out = fopen("bootloader.mif", "w+");
    } else {
        fp_out = fopen("bootloader.mif", "ab+");
    }
    
    fprintf(fp_out, "WIDTH=32;\n");
    fprintf(fp_out, "DEPTH=%d;\n\n", BOOT_SIZE);
    fprintf(fp_out, "ADDRESS_RADIX=UNS;\n");
    fprintf(fp_out, "DATA_RADIX=HEX;\n\n");
    fprintf(fp_out, "CONTENT BEGIN\n");
    
    getline(&line, &len, fp_in);
    getline(&line, &len, fp_in);

    while ((getline(&line, &len, fp_in)) != -1) {   
        removeAll(line, '\n');
        if (strcmp(line, ";")) {
            fprintf(fp_out, "\t%d: %s;\n", i, line);
        }
        i++;
    }
    
    fprintf(fp_out, "[%d..%d]  :   00000000;\nEND;", i-1, (BOOT_SIZE-1));

    return 0;
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
