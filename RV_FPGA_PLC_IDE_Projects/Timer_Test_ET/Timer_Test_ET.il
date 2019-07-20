VAR
    Timer_1   : TON
    Timer_2   : TON
    E_T_1   : TIME
    E_T_2   : TIME
END_VAR

PROGRAM Timer_Test_ET
    Timer :
        CAL Timer_1(
            IN := %SW.0,
            PT := T#10s,
            ET => E_T_1,
            Q  => %LEDR.0)
        CAL Timer_2(
            IN := %LEDR.0,
            PT := T#10s,
            ET => E_T_2,
            Q  => %LEDR.1)
    Compare_E_T_1 :
        LD     E_T_1
        LE     T#2s
        ST     %LEDG.0
        LD     E_T_1
        LE     T#4s
        ST     %LEDG.1
        LD     E_T_1
        LE     T#6s
        ST     %LEDG.2
        LD     E_T_1
        LE     T#8s
        ST     %LEDG.3
        LD     E_T_1
        LT     T#10s
        ST     %LEDG.4
    Compare_E_T_2 :
        LD     E_T_2
        LE     T#2s
        ST     %LEDR.9
        LD     E_T_2
        LE     T#4s
        ST     %LEDR.8
        LD     E_T_2
        LE     T#6s
        ST     %LEDR.7
        LD     E_T_2
        LE     T#8s
        ST     %LEDR.6
        LD     E_T_2
        LT     T#10s
        ST     %LEDR.5
END_PROGRAM
