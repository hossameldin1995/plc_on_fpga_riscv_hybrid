VAR
    X   : INT
    Y   : INT
    Z   : INT
    TON   : TON_1
END_VAR

PROGRAM Test_Project
    R1 :
        LD     15
        ST     X
    R2 :
        LD     20
        ST     Y
    R3 :
        LD     X
        ADD    Y
        ST     Z
        CAL TON_1(
            IN := %SW.0,
            PT := X,
            ET := Z,
            Q  => %LEDG.0)
END_PROGRAM
