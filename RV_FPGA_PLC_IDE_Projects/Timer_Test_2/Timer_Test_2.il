VAR
    Elapsed_Time   : TIME
    Preset_Time   : TIME
    TON_0   : TON
END_VAR

PROGRAM Timer_Test_2
    R1 :
        LD     T#1500ms
        ST     Preset_Time
        CAL TON_0(
            IN := %SW.0,
            PT := Preset_Time,
            ET => Elapsed_Time,
            Q  => %LEDR.0)
END_PROGRAM
