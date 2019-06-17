VAR
END_VAR

PROGRAM Test_IO
    R1 :
        LD     %SW.1
        ST     %LEDG.1
    R2 :
        LD     %KEY.1
        ST     %LEDR.1
    R3 :
        LD     %I0.1
        ST     %Q0.1
END_PROGRAM
