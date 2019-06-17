VAR
END_VAR

PROGRAM Two_Branches_One_Output
    R1 :
        LD     %SW.1
        AND    %SW.2
        LD     %SW.3
        AND    %SW.4
        ORB
        ST     %LEDG.1
END_PROGRAM
