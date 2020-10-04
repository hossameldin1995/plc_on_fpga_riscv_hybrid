VAR
    Duty_Cycle_1   : INT
    Duty_Cycle_2   : INT
    Duty_Cycle_3   : INT
    Duty_Cycle_4   : INT
    Duty_Cycle_5   : INT
    Duty_Cycle_6   : INT
    Duty_Cycle_7   : INT
    Duty_Cycle_8   : INT
    Duty_Cycle_9   : INT
    Duty_Cycle_10  : INT
    PWM_1   : PWM
    PWM_2   : PWM
    PWM_3   : PWM
    PWM_4   : PWM
    PWM_5   : PWM
    PWM_6   : PWM
    PWM_7   : PWM
    PWM_8   : PWM
    PWM_9   : PWM
    PWM_10  : PWM
END_VAR

PROGRAM PWM_DIM_TEN
    DC :
        LD     %SW.0
        BOOL_TO_INT
        MUL    50
        ADD    50
        ST     Duty_Cycle
        LD     %SW.1
        BOOL_TO_INT
        MUL    50
        ADD    50
        ST     Duty_Cycle_2
        LD     %SW.2
        BOOL_TO_INT
        MUL    50
        ADD    50
        ST     Duty_Cycle_3
        LD     %SW.3
        BOOL_TO_INT
        MUL    50
        ADD    50
        ST     Duty_Cycle_4
        LD     %SW.4
        BOOL_TO_INT
        MUL    50
        ADD    50
        ST     Duty_Cycle_5
        LD     %SW.5
        BOOL_TO_INT
        MUL    50
        ADD    50
        ST     Duty_Cycle_6
        LD     %SW.6
        BOOL_TO_INT
        MUL    50
        ADD    50
        ST     Duty_Cycle_7
        LD     %SW.7
        BOOL_TO_INT
        MUL    50
        ADD    50
        ST     Duty_Cycle_8
        LD     %SW.8
        BOOL_TO_INT
        MUL    50
        ADD    50
        ST     Duty_Cycle_9
        LD     %SW.9
        BOOL_TO_INT
        MUL    50
        ADD    50
        ST     Duty_Cycle_10
    PWM_Function_Block :
        CAL PWM_1(
            F  := 100,
            DC := Duty_Cycle_1,
            Q  => %LEDR.0)
        CAL PWM_2(
            F  := 100,
            DC := Duty_Cycle_2,
            Q  => %LEDR.1)
        CAL PWM_3(
            F  := 100,
            DC := Duty_Cycle_3,
            Q  => %LEDR.2)
        CAL PWM_4(
            F  := 100,
            DC := Duty_Cycle_4,
            Q  => %LEDR.3)
        CAL PWM_5(
            F  := 100,
            DC := Duty_Cycle_5,
            Q  => %LEDR.4)
    CAL PWM_6(
            F  := 100,
            DC := Duty_Cycle_6,
            Q  => %LEDR.5)
    CAL PWM_7(
            F  := 100,
            DC := Duty_Cycle_7,
            Q  => %LEDR.6)
    CAL PWM_8(
            F  := 100,
            DC := Duty_Cycle_8,
            Q  => %LEDR.7)
    CAL PWM_9(
            F  := 100,
            DC := Duty_Cycle_9,
            Q  => %LEDR.8)
    CAL PWM_10(
            F  := 100,
            DC := Duty_Cycle_10,
            Q  => %LEDR.9)
END_PROGRAM
