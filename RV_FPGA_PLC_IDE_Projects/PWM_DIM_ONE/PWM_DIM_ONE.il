VAR
    Duty_Cycle   : INT
    PWM_1   : PWM
END_VAR

PROGRAM PWM_DIM_ONE
    DC :
        LD     %SW.0
        MUL    50
        ADD    50
        ST     Duty_Cycle
    PWM_Function_Block :
        CAL PWM_1(
            F  := 100,
            DC := Duty_Cycle,
            Q  => %LEDR.0)
END_PROGRAM
