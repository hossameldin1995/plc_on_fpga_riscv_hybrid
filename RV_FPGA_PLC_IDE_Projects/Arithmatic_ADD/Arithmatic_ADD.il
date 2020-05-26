VAR
    Add_Res   : INT
END_VAR

PROGRAM Arithmatic_ADD
    Addition :
        LD     25
        ADD    16
        ST     Add_Res
    Check_Res :
        LD     Add_Res
        EQ     41
        ST     %LEDG.0
    Check_Error :
        LD     Add_Res
        NE     41
        ST     %LEDR.0
    Check_2 :
        LD     Add_Res
        LT     50
        ST     %LEDG.7
END_PROGRAM
