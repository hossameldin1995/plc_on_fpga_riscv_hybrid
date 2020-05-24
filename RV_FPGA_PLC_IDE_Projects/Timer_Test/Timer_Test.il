VAR
    Elapsed_Time   : TIME
    Preset_Time   : TIME
    Timer1   : TON
    Timer2   : TON
END_VAR

PROGRAM Timer_Test
    R1 :
        LD     T#5d14h12m18s3.5ms
        ST     Preset_Time
        CAL Timer1(
            IN := %SW.0,
            PT := Preset_Time,
            ET => Elapsed_Time,
            Q  => %LEDG.0)
        CAL Timer2(
            IN := %SW.0,
            PT := Preset_Time,
            ET => Elapsed_Time,
            Q  => %LEDG.0)
END_PROGRAM
