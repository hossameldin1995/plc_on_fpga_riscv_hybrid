VAR
    Signed_Int_16   : INT
    Unsigned_Int_8   : USINT
END_VAR

PROGRAM Type_Conversion
    R1 :
        LD     -5
        ST     Signed_Int_16
    R2 :
        LD     Signed_Int_16
        INT_TO_USINT
        ST     Unsigned_Int_8
    R3 :
        LD     Signed_Int_16
        LT     0
        ST     %LEDG.0
    R4 :
        LD     Unsigned_Int_8
        GT     0
        ST     %LEDG.1
END_PROGRAM
