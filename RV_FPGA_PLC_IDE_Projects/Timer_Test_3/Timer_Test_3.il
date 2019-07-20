VAR
    Elapsed_Time_1   : TIME
    Elapsed_Time_2   : TIME
    TON_1   : TON
    TON_2   : TON
END_VAR

PROGRAM Timer_Test_3
    R1 :
        CAL TON_1(
            IN := %SW.0,
            PT := T#1s,
            ET => Elapsed_Time_1,
            Q  => %LEDG.0)
    R2 :
        CAL TON_2(
            IN := %LEDG.0,
            PT := T#1s,
            ET => Elapsed_Time_2,
            Q  => %LEDG.1)
    R3 :
        LD     Elapsed_Time_1
        ST     %LEDR.0
        LD     Elapsed_Time_2
        ST     %LEDR.1
END_PROGRAM
