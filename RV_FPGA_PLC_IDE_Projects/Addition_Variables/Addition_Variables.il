VAR
    X   : INT
    Y   : INT
    Z   : INT
END_VAR

PROGRAM Addition_Variables
    Rung_1 :
        LD     %SW.0
        BOOL_TO_INT
        ST     X
        LD     %SW.1
        BOOL_TO_INT
        ADD    X
        ST     X
    Rung_2 :
        LD     %SW.2
        BOOL_TO_INT
        ST     Y
        LD     %SW.3
        BOOL_TO_INT
        ADD    Y
        ST     Y
    Rung_3 :
        LD     X
        ADD    Y
        ST     Z
    Compare_Z_0 :
        LD     Z
        GT     0
        ST     %LEDG.0
    Compare_Z_1 :
        LD     Z
        GT     1
        ST     %LEDG.1
    Compare_Z_2 :
        LD     Z
        GT     2
        ST     %LEDG.2
    Compare_Z_3 :
        LD     Z
        GT     3
        ST     %LEDG.3
END_PROGRAM
