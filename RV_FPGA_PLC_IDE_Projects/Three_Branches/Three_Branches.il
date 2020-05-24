VAR
END_VAR

PROGRAM Three_Branches
    R1 :
        LD     %SW.0
        AND    %SW.1
        OR     (
        LD     %SW.2
        AND    %SW.3
        OR     (
        LD     %SW.4
        AND    %SW.5
        )
        )
        ST     %LEDG.0
    R2 :
        LD     %SW.0
        AND    %SW.1
        ST     %LEDR.0
    R3 :
        LD     %SW.2
        AND    %SW.3
        ST     %LEDR.2
    R4 :
        LD     %SW.4
        AND    %SW.5
        ST     %LEDR.4
END_PROGRAM
