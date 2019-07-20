VAR
    PWM_1   : PWM
    PWM_2   : PWM
END_VAR

PROGRAM PWM_Test_2
    R1 :
        CAL PWM_1(
            F  := 2,
            DC := 5,
            Q  => %LEDG.0)
        CAL PWM_2(
            F  := 2,
            DC := 5,
            Q  => %LEDG.1)
END_PROGRAM
