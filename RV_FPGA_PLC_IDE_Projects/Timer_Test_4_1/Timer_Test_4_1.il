VAR
    TON_1   : TON
    E_T   : TIME
END_VAR

PROGRAM Timer_Test_4_1
    R1 :
        CAL TON_1(
            IN := %SW.0,
            PT := T#1s,
            ET => E_T,
            Q  => %LEDG.0)
    R2 :
        LD     E_T
        ST     %LEDR.0
END_PROGRAM
