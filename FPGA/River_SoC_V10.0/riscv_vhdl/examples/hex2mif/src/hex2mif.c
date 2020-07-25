#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>     // access function

#define WORD_LENGTH 64

int main(int argc, char **argv) {
    if (argc != 4) {
        printf("Error arguments\n");
    } else {
        char * hex_file_name = argv[1];
        char * mif_file_name = argv[2];
        char * size_s = argv[3];
        int size_i = atoi(size_s);
        FILE *fp_in;
        FILE *fp_out;
        size_t len = 0;
        char *line = NULL;
        int i = 0;

        fp_in = fopen(hex_file_name, "r");
        if( access(mif_file_name, F_OK ) != -1 ) {
            fp_out = fopen(mif_file_name, "w+");
        } else {
            fp_out = fopen(mif_file_name, "ab+");
        }
        fprintf(fp_out, "WIDTH=%d;\n", WORD_LENGTH);
        fprintf(fp_out, "DEPTH=%d;\n\n", size_i);
        fprintf(fp_out, "ADDRESS_RADIX=UNS;\n");
        fprintf(fp_out, "DATA_RADIX=HEX;\n\n");
        fprintf(fp_out, "CONTENT BEGIN\n");
        
        while ((getline(&line, &len, fp_in)) != -1) {   
            line[WORD_LENGTH/4] = 0;
            fprintf(fp_out, "\t%d: %s;\n", i, line);
            i++;
        }
        
        fprintf(fp_out, "\nEND;");
        
        fclose(fp_in);
        fclose(fp_out);
        }

    return 0;
}
