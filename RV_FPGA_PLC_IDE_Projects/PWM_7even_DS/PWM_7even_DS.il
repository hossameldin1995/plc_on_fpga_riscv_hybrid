VAR
    PWM_1   : PWM
    PWM_2   : PWM
    PWM_3   : PWM
    PWM_4   : PWM
    PWM_5   : PWM
    PWM_6   : PWM
    PWM_7   : PWM
END_VAR

PROGRAM PWM_7even_DS
    PWMs :
        CAL PWM_1(
            F  := 2,
            DC := 70,
            Q  => %LEDR.0)
        CAL PWM_2(
            F  := 2,
            DC := 60,
            Q  => %LEDR.1)
        CAL PWM_3(
            F  := 2,
            DC := 50,
            Q  => %LEDR.2)
        CAL PWM_4(
            F  := 2,
            DC := 40,
            Q  => %LEDR.3)
        CAL PWM_5(
            F  := 2,
            DC := 30,
            Q  => %LEDR.4)
        CAL PWM_6(
            F  := 2,
            DC := 20,
            Q  => %LEDR.5)
        CAL PWM_7(
            F  := 2,
            DC := 10,
            Q  => %LEDR.6)
END_PROGRAM
