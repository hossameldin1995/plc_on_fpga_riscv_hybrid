VAR
    PID_1   : PID
    PV_LREAL   : LREAL
    XOUT_LREAL   : LREAL
    Count   : INT
    K0   : LREAL
    K1   : LREAL
    K2   : LREAL
END_VAR

PROGRAM PID_Test_1
    Rung_PV :
        LD     %KEY.0
        BOOL_TO_LREAL
        MUL    20.7
        ST     K0
        LD     %KEY.1
        BOOL_TO_LREAL
        MUL    60.7
        ST     K1
        LD     %KEY.2
        BOOL_TO_LREAL
        MUL    80.7
        ST     K2
        LD     K0
        ADD    K1
        ADD    K2
        ST     PV_LREAL
    Rung_PID :
        CAL PID_1(
            Auto  := %SW.0,
            PV    := PV_LREAL,
            SP    := 60.7,
            X0    := 60.7,
            KP    := 1.0,
            TR    := 0.1,
            TD    := 1.0,
            Cycle := T#100ms,
            XOUT  => XOUT_LREAL)
    Rung_XOUT :
        LD     XOUT_LREAL
        GE     10.0
        ST     %LEDG.0
        LD     XOUT_LREAL
        GE     20.0
        ST     %LEDG.1
        LD     XOUT_LREAL
        GE     40.0
        ST     %LEDG.2
        LD     XOUT_LREAL
        GE     80.0
        ST     %LEDG.3
        LD     XOUT_LREAL
        GE     160.0
        ST     %LEDG.4
        LD     XOUT_LREAL
        GE     320.0
        ST     %LEDG.5
        LD     XOUT_LREAL
        GE     640.0
        ST     %LEDG.6
        LD     XOUT_LREAL
        GE     1280.0
        ST     %LEDG.7
END_PROGRAM
