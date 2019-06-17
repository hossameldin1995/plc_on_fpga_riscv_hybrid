VAR
    res   : INT
END_VAR

PROGRAM Add_Bool
    Add :
        LD     %SW.0
        ADD    %SW.1
        ADD    %SW.2
        ADD    %SW.3
        ADD    %SW.4
        ADD    %SW.5
        ADD    %SW.6
        ADD    %SW.7
        ST     res
    Check_res :
        LD     res
        LT     3
        ST     %LEDR.8
END_PROGRAM
