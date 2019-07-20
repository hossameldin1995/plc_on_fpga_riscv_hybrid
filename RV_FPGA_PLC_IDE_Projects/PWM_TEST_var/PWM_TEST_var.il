VAR
    Frequency   : INT
    Duty_Cycle   : INT
    PWM_1   : PWM
END_VAR

PROGRAM PWM_TEST_var
    Initialize_Variables :
        LD     2
        ST     Frequency
        LD     10
        ST     Duty_Cycle
    PWM_Function_Block :
        CAL PWM_1(
            F  := Frequency,
            DC := Duty_Cycle,
            Q  => %LEDG.0)
END_PROGRAM
