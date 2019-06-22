VAR
    Elapsed_Time_1   : TIME
    Elapsed_Time_2   : TIME
    Preset_Time_1   : TIME
    Preset_Time_2   : TIME
    TON_1   : TON
    TON_2   : TON
END_VAR

PROGRAM Timer_Test_3
    R1 :
        LD     T#1s
        ST     Preset_Time_1
        LD     T#1s
        ST     Elapsed_Time_2
    R2 :
        CAL TON_1(
            IN := %SW.0,
            PT := Preset_Time_1,
            ET => Elapsed_Time_1,
            Q  => %LEDG.0)
    R3 :
        CAL TON_2(
            IN := %LEDG.0,
            PT := Preset_Time_2,
            ET => Elapsed_Time_2,
            Q  => %LEDG.1)
END_PROGRAM
