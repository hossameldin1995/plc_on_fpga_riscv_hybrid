VAR
    X   : INT
    Y   : INT
    Z   : INT
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
        CAL TON(
            IN := %I0.3,
            PT := X,
            ET := %SW.0,
            Q  => jl)
END_PROGRAM
