VAR
END_VAR

PROGRAM Two_Branches_One_Output
    R1 :
        LD     %SW.1
        AND    %SW.2
        OR     (
        LD     %SW.3
        AND    %SW.4
        )
        ST      %LEDR.1
END_PROGRAM
