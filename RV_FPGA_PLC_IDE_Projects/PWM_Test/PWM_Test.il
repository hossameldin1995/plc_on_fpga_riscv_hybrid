VAR
    PWM_1   : PWM
END_VAR

PROGRAM PWM_Test
    R1 :
        CAL PWM_1(
            F  := 2,
            DC := 80,
            Q  => %LEDG.0)
END_PROGRAM
