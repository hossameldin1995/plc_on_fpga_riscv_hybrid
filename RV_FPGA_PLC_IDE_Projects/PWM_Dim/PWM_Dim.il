VAR
    Duty_Cycle   : INT
    PWM_1   : PWM
END_VAR

PROGRAM PWM_Dim
    Load_Sw_As_DC :
        LD     %SW.0
        ADD    %SW.1
        ADD    %SW.2
        ADD    %SW.3
        ADD    %SW.4
        ADD    %SW.5
        ADD    %SW.6
        ADD    %SW.7
        ADD    %SW.8
        ADD    %SW.9
        MUL    10
        ST     Duty_Cycle
    PWM_Function_Block :
        CAL PWM_1(
            F  := 100,
            DC := Duty_Cycle,
            Q  => %LEDG.0)
END_PROGRAM
