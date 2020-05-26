VAR
    Add_Res   : INT
END_VAR

PROGRAM Arithmatic_MUL
    Addition :
        LD     %SW.0
        MUL    %SW.1
        MUL    %SW.2
        MUL    %SW.3
        MUL    %SW.4
        ST     %LEDG.0
END_PROGRAM
