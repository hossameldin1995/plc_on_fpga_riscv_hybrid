VAR
END_VAR

PROGRAM Logic_Commands
    And :
        LD     %SW.0
        AND    %SW.1
        ST     %LEDR.0
    Or :
        LD     %KEY.0
        OR     %KEY.1
        ST     %LEDG.0
    Xor :
        LD     %SW.4
        XOR    %SW.5
        ST     %LEDG.4
    Not :
        LDN    %LEDG.0
        ST     %LEDG.1
END_PROGRAM
