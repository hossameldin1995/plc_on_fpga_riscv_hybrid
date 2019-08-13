VAR
    Enable_Timer   : BOOL
    Timer_1   : TON
    ET_Timer   : TIME
    PWM_1   : PWM
    Duty_Cycle   : INT
    PWM_OUT   : BOOL
END_VAR

PROGRAM Timer_PWM_MUL_DIV
    Timer_Control :
        LD     %KEY.0
        SET    Enable_Timer
        LD     %KEY.1
        RST    Enable_Timer
        LD     %KEY.0
        ST     %LEDG.7
        LD     %KEY.1
        ST     %LEDR.0
    Calculate_Freq :
        LD     PWM_OUT
        AND    Enable_Timer
        OR     %LEDG.1
        ST     %LEDG.0
    Timer_FB :
        CAL Timer_1(
            IN := Enable_Timer,
            PT := T#10s,
            ET => ET_Timer,
            Q  => %LEDG.1)
    PWM_FB :
        CAL PWM_1(
            F  := 1,
            DC := 10,
            Q  => PWM_OUT)
END_PROGRAM
