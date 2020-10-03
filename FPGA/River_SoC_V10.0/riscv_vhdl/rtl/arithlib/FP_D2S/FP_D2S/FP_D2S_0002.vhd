-- ------------------------------------------------------------------------- 
-- High Level Design Compiler for Intel(R) FPGAs Version 18.0 (Release Build #614)
-- Quartus Prime development tool and MATLAB/Simulink Interface
-- 
-- Legal Notice: Copyright 2018 Intel Corporation.  All rights reserved.
-- Your use of  Intel Corporation's design tools,  logic functions and other
-- software and  tools, and its AMPP partner logic functions, and any output
-- files any  of the foregoing (including  device programming  or simulation
-- files), and  any associated  documentation  or information  are expressly
-- subject  to the terms and  conditions of the  Intel FPGA Software License
-- Agreement, Intel MegaCore Function License Agreement, or other applicable
-- license agreement,  including,  without limitation,  that your use is for
-- the  sole  purpose of  programming  logic devices  manufactured by  Intel
-- and  sold by Intel  or its authorized  distributors. Please refer  to the
-- applicable agreement for further details.
-- ---------------------------------------------------------------------------

-- VHDL created from FP_D2S_0002
-- VHDL created on Thu Oct  1 23:50:51 2020


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.NUMERIC_STD.all;
use IEEE.MATH_REAL.all;
use std.TextIO.all;
use work.dspba_library_package.all;

LIBRARY altera_mf;
USE altera_mf.altera_mf_components.all;
LIBRARY altera_lnsim;
USE altera_lnsim.altera_lnsim_components.altera_syncram;
LIBRARY lpm;
USE lpm.lpm_components.all;

entity FP_D2S_0002 is
    port (
        a : in std_logic_vector(63 downto 0);  -- float64_m52
        q : out std_logic_vector(31 downto 0);  -- float32_m23
        clk : in std_logic;
        areset : in std_logic
    );
end FP_D2S_0002;

architecture normal of FP_D2S_0002 is

    attribute altera_attribute : string;
    attribute altera_attribute of normal : architecture is "-name AUTO_SHIFT_REGISTER_RECOGNITION OFF; -name PHYSICAL_SYNTHESIS_REGISTER_DUPLICATION ON; -name MESSAGE_DISABLE 10036; -name MESSAGE_DISABLE 10037; -name MESSAGE_DISABLE 14130; -name MESSAGE_DISABLE 14320; -name MESSAGE_DISABLE 15400; -name MESSAGE_DISABLE 14130; -name MESSAGE_DISABLE 10036; -name MESSAGE_DISABLE 12020; -name MESSAGE_DISABLE 12030; -name MESSAGE_DISABLE 12010; -name MESSAGE_DISABLE 12110; -name MESSAGE_DISABLE 14320; -name MESSAGE_DISABLE 13410; -name MESSAGE_DISABLE 113007";
    
    signal GND_q : STD_LOGIC_VECTOR (0 downto 0);
    signal VCC_q : STD_LOGIC_VECTOR (0 downto 0);
    signal cstBiasOut_uid6_fpToFPTest_q : STD_LOGIC_VECTOR (11 downto 0);
    signal cstAllOWE_uid7_fpToFPTest_q : STD_LOGIC_VECTOR (10 downto 0);
    signal cstZeroWF_uid8_fpToFPTest_q : STD_LOGIC_VECTOR (51 downto 0);
    signal cstAllZWE_uid9_fpToFPTest_q : STD_LOGIC_VECTOR (10 downto 0);
    signal exp_x_uid10_fpToFPTest_b : STD_LOGIC_VECTOR (10 downto 0);
    signal frac_x_uid11_fpToFPTest_b : STD_LOGIC_VECTOR (51 downto 0);
    signal excZ_x_uid12_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal expXIsMax_uid13_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal fracXIsZero_uid14_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal fracXIsNotZero_uid15_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal excI_x_uid16_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal excN_x_uid17_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal invExpXIsMax_uid18_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal InvExpXIsZero_uid19_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal excR_x_uid20_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal fracXWOP1_uid23_fpToFPTest_b : STD_LOGIC_VECTOR (23 downto 0);
    signal expXFracX_uid24_fpToFPTest_q : STD_LOGIC_VECTOR (34 downto 0);
    signal zeroPaddingInAddition_uid27_fpToFPTest_q : STD_LOGIC_VECTOR (22 downto 0);
    signal rndExpUpdate_uid28_fpToFPTest_q : STD_LOGIC_VECTOR (35 downto 0);
    signal expFracR_uid29_fpToFPTest_a : STD_LOGIC_VECTOR (37 downto 0);
    signal expFracR_uid29_fpToFPTest_b : STD_LOGIC_VECTOR (37 downto 0);
    signal expFracR_uid29_fpToFPTest_o : STD_LOGIC_VECTOR (37 downto 0);
    signal expFracR_uid29_fpToFPTest_q : STD_LOGIC_VECTOR (36 downto 0);
    signal fracR_uid30_fpToFPTest_in : STD_LOGIC_VECTOR (23 downto 0);
    signal fracR_uid30_fpToFPTest_b : STD_LOGIC_VECTOR (22 downto 0);
    signal expR_uid31_fpToFPTest_in : STD_LOGIC_VECTOR (31 downto 0);
    signal expR_uid31_fpToFPTest_b : STD_LOGIC_VECTOR (7 downto 0);
    signal expRExt_uid32_fpToFPTest_b : STD_LOGIC_VECTOR (12 downto 0);
    signal expUdf_uid33_fpToFPTest_a : STD_LOGIC_VECTOR (14 downto 0);
    signal expUdf_uid33_fpToFPTest_b : STD_LOGIC_VECTOR (14 downto 0);
    signal expUdf_uid33_fpToFPTest_o : STD_LOGIC_VECTOR (14 downto 0);
    signal expUdf_uid33_fpToFPTest_n : STD_LOGIC_VECTOR (0 downto 0);
    signal expWEOutAllO_uid34_fpToFPTest_q : STD_LOGIC_VECTOR (7 downto 0);
    signal expOvf_uid35_fpToFPTest_a : STD_LOGIC_VECTOR (14 downto 0);
    signal expOvf_uid35_fpToFPTest_b : STD_LOGIC_VECTOR (14 downto 0);
    signal expOvf_uid35_fpToFPTest_o : STD_LOGIC_VECTOR (14 downto 0);
    signal expOvf_uid35_fpToFPTest_n : STD_LOGIC_VECTOR (0 downto 0);
    signal inRegAndUdf_uid36_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal excRZero_uid37_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal inRegAndOvf_uid38_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal excRInf_uid39_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal concExc_uid40_fpToFPTest_q : STD_LOGIC_VECTOR (2 downto 0);
    signal excREnc_uid41_fpToFPTest_q : STD_LOGIC_VECTOR (1 downto 0);
    signal oneFracRPostExc2_uid42_fpToFPTest_q : STD_LOGIC_VECTOR (22 downto 0);
    signal fracRPostExc_uid45_fpToFPTest_s : STD_LOGIC_VECTOR (1 downto 0);
    signal fracRPostExc_uid45_fpToFPTest_q : STD_LOGIC_VECTOR (22 downto 0);
    signal zeroExpRPostExc_uid48_fpToFPTest_q : STD_LOGIC_VECTOR (7 downto 0);
    signal expRPostExc_uid49_fpToFPTest_s : STD_LOGIC_VECTOR (1 downto 0);
    signal expRPostExc_uid49_fpToFPTest_q : STD_LOGIC_VECTOR (7 downto 0);
    signal signX_uid50_fpToFPTest_b : STD_LOGIC_VECTOR (0 downto 0);
    signal fpRes_uid51_fpToFPTest_q : STD_LOGIC_VECTOR (31 downto 0);
    signal redist0_signX_uid50_fpToFPTest_b_1_q : STD_LOGIC_VECTOR (0 downto 0);
    signal redist1_expR_uid31_fpToFPTest_b_1_q : STD_LOGIC_VECTOR (7 downto 0);
    signal redist2_fracR_uid30_fpToFPTest_b_1_q : STD_LOGIC_VECTOR (22 downto 0);

begin


    -- VCC(CONSTANT,1)
    VCC_q <= "1";

    -- signX_uid50_fpToFPTest(BITSELECT,49)@0
    signX_uid50_fpToFPTest_b <= STD_LOGIC_VECTOR(a(63 downto 63));

    -- redist0_signX_uid50_fpToFPTest_b_1(DELAY,52)
    redist0_signX_uid50_fpToFPTest_b_1 : dspba_delay
    GENERIC MAP ( width => 1, depth => 1, reset_kind => "ASYNC" )
    PORT MAP ( xin => signX_uid50_fpToFPTest_b, xout => redist0_signX_uid50_fpToFPTest_b_1_q, clk => clk, aclr => areset );

    -- expWEOutAllO_uid34_fpToFPTest(CONSTANT,33)
    expWEOutAllO_uid34_fpToFPTest_q <= "11111111";

    -- cstBiasOut_uid6_fpToFPTest(CONSTANT,5)
    cstBiasOut_uid6_fpToFPTest_q <= "110010000000";

    -- zeroPaddingInAddition_uid27_fpToFPTest(CONSTANT,26)
    zeroPaddingInAddition_uid27_fpToFPTest_q <= "00000000000000000000000";

    -- rndExpUpdate_uid28_fpToFPTest(BITJOIN,27)@0
    rndExpUpdate_uid28_fpToFPTest_q <= cstBiasOut_uid6_fpToFPTest_q & zeroPaddingInAddition_uid27_fpToFPTest_q & VCC_q;

    -- exp_x_uid10_fpToFPTest(BITSELECT,9)@0
    exp_x_uid10_fpToFPTest_b <= a(62 downto 52);

    -- frac_x_uid11_fpToFPTest(BITSELECT,10)@0
    frac_x_uid11_fpToFPTest_b <= a(51 downto 0);

    -- fracXWOP1_uid23_fpToFPTest(BITSELECT,22)@0
    fracXWOP1_uid23_fpToFPTest_b <= frac_x_uid11_fpToFPTest_b(51 downto 28);

    -- expXFracX_uid24_fpToFPTest(BITJOIN,23)@0
    expXFracX_uid24_fpToFPTest_q <= exp_x_uid10_fpToFPTest_b & fracXWOP1_uid23_fpToFPTest_b;

    -- expFracR_uid29_fpToFPTest(ADD,28)@0
    expFracR_uid29_fpToFPTest_a <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR("000" & expXFracX_uid24_fpToFPTest_q));
    expFracR_uid29_fpToFPTest_b <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR((37 downto 36 => rndExpUpdate_uid28_fpToFPTest_q(35)) & rndExpUpdate_uid28_fpToFPTest_q));
    expFracR_uid29_fpToFPTest_o <= STD_LOGIC_VECTOR(SIGNED(expFracR_uid29_fpToFPTest_a) + SIGNED(expFracR_uid29_fpToFPTest_b));
    expFracR_uid29_fpToFPTest_q <= expFracR_uid29_fpToFPTest_o(36 downto 0);

    -- expR_uid31_fpToFPTest(BITSELECT,30)@0
    expR_uid31_fpToFPTest_in <= expFracR_uid29_fpToFPTest_q(31 downto 0);
    expR_uid31_fpToFPTest_b <= expR_uid31_fpToFPTest_in(31 downto 24);

    -- redist1_expR_uid31_fpToFPTest_b_1(DELAY,53)
    redist1_expR_uid31_fpToFPTest_b_1 : dspba_delay
    GENERIC MAP ( width => 8, depth => 1, reset_kind => "ASYNC" )
    PORT MAP ( xin => expR_uid31_fpToFPTest_b, xout => redist1_expR_uid31_fpToFPTest_b_1_q, clk => clk, aclr => areset );

    -- zeroExpRPostExc_uid48_fpToFPTest(CONSTANT,47)
    zeroExpRPostExc_uid48_fpToFPTest_q <= "00000000";

    -- cstZeroWF_uid8_fpToFPTest(CONSTANT,7)
    cstZeroWF_uid8_fpToFPTest_q <= "0000000000000000000000000000000000000000000000000000";

    -- fracXIsZero_uid14_fpToFPTest(LOGICAL,13)@0
    fracXIsZero_uid14_fpToFPTest_q <= "1" WHEN cstZeroWF_uid8_fpToFPTest_q = frac_x_uid11_fpToFPTest_b ELSE "0";

    -- fracXIsNotZero_uid15_fpToFPTest(LOGICAL,14)@0
    fracXIsNotZero_uid15_fpToFPTest_q <= not (fracXIsZero_uid14_fpToFPTest_q);

    -- cstAllOWE_uid7_fpToFPTest(CONSTANT,6)
    cstAllOWE_uid7_fpToFPTest_q <= "11111111111";

    -- expXIsMax_uid13_fpToFPTest(LOGICAL,12)@0
    expXIsMax_uid13_fpToFPTest_q <= "1" WHEN exp_x_uid10_fpToFPTest_b = cstAllOWE_uid7_fpToFPTest_q ELSE "0";

    -- excN_x_uid17_fpToFPTest(LOGICAL,16)@0
    excN_x_uid17_fpToFPTest_q <= expXIsMax_uid13_fpToFPTest_q and fracXIsNotZero_uid15_fpToFPTest_q;

    -- GND(CONSTANT,0)
    GND_q <= "0";

    -- expRExt_uid32_fpToFPTest(BITSELECT,31)@0
    expRExt_uid32_fpToFPTest_b <= STD_LOGIC_VECTOR(expFracR_uid29_fpToFPTest_q(36 downto 24));

    -- expOvf_uid35_fpToFPTest(COMPARE,34)@0
    expOvf_uid35_fpToFPTest_a <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR((14 downto 13 => expRExt_uid32_fpToFPTest_b(12)) & expRExt_uid32_fpToFPTest_b));
    expOvf_uid35_fpToFPTest_b <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR("0000000" & expWEOutAllO_uid34_fpToFPTest_q));
    expOvf_uid35_fpToFPTest_o <= STD_LOGIC_VECTOR(SIGNED(expOvf_uid35_fpToFPTest_a) - SIGNED(expOvf_uid35_fpToFPTest_b));
    expOvf_uid35_fpToFPTest_n(0) <= not (expOvf_uid35_fpToFPTest_o(14));

    -- invExpXIsMax_uid18_fpToFPTest(LOGICAL,17)@0
    invExpXIsMax_uid18_fpToFPTest_q <= not (expXIsMax_uid13_fpToFPTest_q);

    -- cstAllZWE_uid9_fpToFPTest(CONSTANT,8)
    cstAllZWE_uid9_fpToFPTest_q <= "00000000000";

    -- excZ_x_uid12_fpToFPTest(LOGICAL,11)@0
    excZ_x_uid12_fpToFPTest_q <= "1" WHEN exp_x_uid10_fpToFPTest_b = cstAllZWE_uid9_fpToFPTest_q ELSE "0";

    -- InvExpXIsZero_uid19_fpToFPTest(LOGICAL,18)@0
    InvExpXIsZero_uid19_fpToFPTest_q <= not (excZ_x_uid12_fpToFPTest_q);

    -- excR_x_uid20_fpToFPTest(LOGICAL,19)@0
    excR_x_uid20_fpToFPTest_q <= InvExpXIsZero_uid19_fpToFPTest_q and invExpXIsMax_uid18_fpToFPTest_q;

    -- inRegAndOvf_uid38_fpToFPTest(LOGICAL,37)@0
    inRegAndOvf_uid38_fpToFPTest_q <= excR_x_uid20_fpToFPTest_q and expOvf_uid35_fpToFPTest_n;

    -- excI_x_uid16_fpToFPTest(LOGICAL,15)@0
    excI_x_uid16_fpToFPTest_q <= expXIsMax_uid13_fpToFPTest_q and fracXIsZero_uid14_fpToFPTest_q;

    -- excRInf_uid39_fpToFPTest(LOGICAL,38)@0
    excRInf_uid39_fpToFPTest_q <= excI_x_uid16_fpToFPTest_q or inRegAndOvf_uid38_fpToFPTest_q;

    -- expUdf_uid33_fpToFPTest(COMPARE,32)@0
    expUdf_uid33_fpToFPTest_a <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR("00000000000000" & GND_q));
    expUdf_uid33_fpToFPTest_b <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR((14 downto 13 => expRExt_uid32_fpToFPTest_b(12)) & expRExt_uid32_fpToFPTest_b));
    expUdf_uid33_fpToFPTest_o <= STD_LOGIC_VECTOR(SIGNED(expUdf_uid33_fpToFPTest_a) - SIGNED(expUdf_uid33_fpToFPTest_b));
    expUdf_uid33_fpToFPTest_n(0) <= not (expUdf_uid33_fpToFPTest_o(14));

    -- inRegAndUdf_uid36_fpToFPTest(LOGICAL,35)@0
    inRegAndUdf_uid36_fpToFPTest_q <= excR_x_uid20_fpToFPTest_q and expUdf_uid33_fpToFPTest_n;

    -- excRZero_uid37_fpToFPTest(LOGICAL,36)@0
    excRZero_uid37_fpToFPTest_q <= excZ_x_uid12_fpToFPTest_q or inRegAndUdf_uid36_fpToFPTest_q;

    -- concExc_uid40_fpToFPTest(BITJOIN,39)@0
    concExc_uid40_fpToFPTest_q <= excN_x_uid17_fpToFPTest_q & excRInf_uid39_fpToFPTest_q & excRZero_uid37_fpToFPTest_q;

    -- excREnc_uid41_fpToFPTest(LOOKUP,40)@0 + 1
    excREnc_uid41_fpToFPTest_clkproc: PROCESS (clk, areset)
    BEGIN
        IF (areset = '1') THEN
            excREnc_uid41_fpToFPTest_q <= "01";
        ELSIF (clk'EVENT AND clk = '1') THEN
            CASE (concExc_uid40_fpToFPTest_q) IS
                WHEN "000" => excREnc_uid41_fpToFPTest_q <= "01";
                WHEN "001" => excREnc_uid41_fpToFPTest_q <= "00";
                WHEN "010" => excREnc_uid41_fpToFPTest_q <= "10";
                WHEN "011" => excREnc_uid41_fpToFPTest_q <= "00";
                WHEN "100" => excREnc_uid41_fpToFPTest_q <= "11";
                WHEN "101" => excREnc_uid41_fpToFPTest_q <= "00";
                WHEN "110" => excREnc_uid41_fpToFPTest_q <= "00";
                WHEN "111" => excREnc_uid41_fpToFPTest_q <= "00";
                WHEN OTHERS => -- unreachable
                               excREnc_uid41_fpToFPTest_q <= (others => '-');
            END CASE;
        END IF;
    END PROCESS;

    -- expRPostExc_uid49_fpToFPTest(MUX,48)@1
    expRPostExc_uid49_fpToFPTest_s <= excREnc_uid41_fpToFPTest_q;
    expRPostExc_uid49_fpToFPTest_combproc: PROCESS (expRPostExc_uid49_fpToFPTest_s, zeroExpRPostExc_uid48_fpToFPTest_q, redist1_expR_uid31_fpToFPTest_b_1_q, expWEOutAllO_uid34_fpToFPTest_q)
    BEGIN
        CASE (expRPostExc_uid49_fpToFPTest_s) IS
            WHEN "00" => expRPostExc_uid49_fpToFPTest_q <= zeroExpRPostExc_uid48_fpToFPTest_q;
            WHEN "01" => expRPostExc_uid49_fpToFPTest_q <= redist1_expR_uid31_fpToFPTest_b_1_q;
            WHEN "10" => expRPostExc_uid49_fpToFPTest_q <= expWEOutAllO_uid34_fpToFPTest_q;
            WHEN "11" => expRPostExc_uid49_fpToFPTest_q <= expWEOutAllO_uid34_fpToFPTest_q;
            WHEN OTHERS => expRPostExc_uid49_fpToFPTest_q <= (others => '0');
        END CASE;
    END PROCESS;

    -- oneFracRPostExc2_uid42_fpToFPTest(CONSTANT,41)
    oneFracRPostExc2_uid42_fpToFPTest_q <= "00000000000000000000001";

    -- fracR_uid30_fpToFPTest(BITSELECT,29)@0
    fracR_uid30_fpToFPTest_in <= expFracR_uid29_fpToFPTest_q(23 downto 0);
    fracR_uid30_fpToFPTest_b <= fracR_uid30_fpToFPTest_in(23 downto 1);

    -- redist2_fracR_uid30_fpToFPTest_b_1(DELAY,54)
    redist2_fracR_uid30_fpToFPTest_b_1 : dspba_delay
    GENERIC MAP ( width => 23, depth => 1, reset_kind => "ASYNC" )
    PORT MAP ( xin => fracR_uid30_fpToFPTest_b, xout => redist2_fracR_uid30_fpToFPTest_b_1_q, clk => clk, aclr => areset );

    -- fracRPostExc_uid45_fpToFPTest(MUX,44)@1
    fracRPostExc_uid45_fpToFPTest_s <= excREnc_uid41_fpToFPTest_q;
    fracRPostExc_uid45_fpToFPTest_combproc: PROCESS (fracRPostExc_uid45_fpToFPTest_s, zeroPaddingInAddition_uid27_fpToFPTest_q, redist2_fracR_uid30_fpToFPTest_b_1_q, oneFracRPostExc2_uid42_fpToFPTest_q)
    BEGIN
        CASE (fracRPostExc_uid45_fpToFPTest_s) IS
            WHEN "00" => fracRPostExc_uid45_fpToFPTest_q <= zeroPaddingInAddition_uid27_fpToFPTest_q;
            WHEN "01" => fracRPostExc_uid45_fpToFPTest_q <= redist2_fracR_uid30_fpToFPTest_b_1_q;
            WHEN "10" => fracRPostExc_uid45_fpToFPTest_q <= zeroPaddingInAddition_uid27_fpToFPTest_q;
            WHEN "11" => fracRPostExc_uid45_fpToFPTest_q <= oneFracRPostExc2_uid42_fpToFPTest_q;
            WHEN OTHERS => fracRPostExc_uid45_fpToFPTest_q <= (others => '0');
        END CASE;
    END PROCESS;

    -- fpRes_uid51_fpToFPTest(BITJOIN,50)@1
    fpRes_uid51_fpToFPTest_q <= redist0_signX_uid50_fpToFPTest_b_1_q & expRPostExc_uid49_fpToFPTest_q & fracRPostExc_uid45_fpToFPTest_q;

    -- xOut(GPOUT,4)@1
    q <= fpRes_uid51_fpToFPTest_q;

END normal;
