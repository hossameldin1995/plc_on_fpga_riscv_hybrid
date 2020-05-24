VAR
END_VAR

PROGRAM And_Test
    Rung_Number_1 :
        LD     %SW.0
        AND    %SW.1
        AND    %SW.2
        ST     %LEDG.0
END_PROGRAM
