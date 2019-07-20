VAR
    Duty_Cycle_1   : INT
    Duty_Cycle_2   : INT
    PWM_1   : PWM
    PWM_2   : PWM
END_VAR

PROGRAM PWM_DIM_TWO
    DC :
        LD     %SW.0
        MUL    50
        ADD    50
        ST     Duty_Cycle
        LD     %SW.1
        MUL    50
        ADD    50
        ST     Duty_Cycle_2
    PWM_Function_Block :
        CAL PWM_1(
            F  := 100,
            DC := Duty_Cycle_1,
            Q  => %LEDR.0)
        CAL PWM_2(
            F  := 100,
            DC := Duty_Cycle_2,
            Q  => %LEDR.1)
END_PROGRAM
