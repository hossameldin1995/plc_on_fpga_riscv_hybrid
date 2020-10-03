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

-- VHDL created from FP_S2D_0002
-- VHDL created on Fri Oct  2 04:32:53 2020


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

entity FP_S2D_0002 is
    port (
        a : in std_logic_vector(31 downto 0);  -- float32_m23
        q : out std_logic_vector(63 downto 0);  -- float64_m52
        clk : in std_logic;
        areset : in std_logic
    );
end FP_S2D_0002;

architecture normal of FP_S2D_0002 is

    attribute altera_attribute : string;
    attribute altera_attribute of normal : architecture is "-name AUTO_SHIFT_REGISTER_RECOGNITION OFF; -name PHYSICAL_SYNTHESIS_REGISTER_DUPLICATION ON; -name MESSAGE_DISABLE 10036; -name MESSAGE_DISABLE 10037; -name MESSAGE_DISABLE 14130; -name MESSAGE_DISABLE 14320; -name MESSAGE_DISABLE 15400; -name MESSAGE_DISABLE 14130; -name MESSAGE_DISABLE 10036; -name MESSAGE_DISABLE 12020; -name MESSAGE_DISABLE 12030; -name MESSAGE_DISABLE 12010; -name MESSAGE_DISABLE 12110; -name MESSAGE_DISABLE 14320; -name MESSAGE_DISABLE 13410; -name MESSAGE_DISABLE 113007";
    
    signal GND_q : STD_LOGIC_VECTOR (0 downto 0);
    signal VCC_q : STD_LOGIC_VECTOR (0 downto 0);
    signal cstBiasOut_uid6_fpToFPTest_q : STD_LOGIC_VECTOR (11 downto 0);
    signal cstAllOWE_uid7_fpToFPTest_q : STD_LOGIC_VECTOR (7 downto 0);
    signal cstZeroWF_uid8_fpToFPTest_q : STD_LOGIC_VECTOR (22 downto 0);
    signal cstAllZWE_uid9_fpToFPTest_q : STD_LOGIC_VECTOR (7 downto 0);
    signal exp_x_uid10_fpToFPTest_b : STD_LOGIC_VECTOR (7 downto 0);
    signal frac_x_uid11_fpToFPTest_b : STD_LOGIC_VECTOR (22 downto 0);
    signal excZ_x_uid12_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal expXIsMax_uid13_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal fracXIsZero_uid14_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal fracXIsNotZero_uid15_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal excI_x_uid16_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal excN_x_uid17_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal invExpXIsMax_uid18_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal InvExpXIsZero_uid19_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal excR_x_uid20_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal zP_uid23_fpToFPTest_q : STD_LOGIC_VECTOR (28 downto 0);
    signal fracR_uid24_fpToFPTest_q : STD_LOGIC_VECTOR (51 downto 0);
    signal expRExt_uid25_fpToFPTest_a : STD_LOGIC_VECTOR (13 downto 0);
    signal expRExt_uid25_fpToFPTest_b : STD_LOGIC_VECTOR (13 downto 0);
    signal expRExt_uid25_fpToFPTest_o : STD_LOGIC_VECTOR (13 downto 0);
    signal expRExt_uid25_fpToFPTest_q : STD_LOGIC_VECTOR (12 downto 0);
    signal expR_uid26_fpToFPTest_in : STD_LOGIC_VECTOR (10 downto 0);
    signal expR_uid26_fpToFPTest_b : STD_LOGIC_VECTOR (10 downto 0);
    signal expUdf_uid27_fpToFPTest_a : STD_LOGIC_VECTOR (14 downto 0);
    signal expUdf_uid27_fpToFPTest_b : STD_LOGIC_VECTOR (14 downto 0);
    signal expUdf_uid27_fpToFPTest_o : STD_LOGIC_VECTOR (14 downto 0);
    signal expUdf_uid27_fpToFPTest_n : STD_LOGIC_VECTOR (0 downto 0);
    signal expWEOutAllO_uid28_fpToFPTest_q : STD_LOGIC_VECTOR (10 downto 0);
    signal expOvf_uid29_fpToFPTest_a : STD_LOGIC_VECTOR (14 downto 0);
    signal expOvf_uid29_fpToFPTest_b : STD_LOGIC_VECTOR (14 downto 0);
    signal expOvf_uid29_fpToFPTest_o : STD_LOGIC_VECTOR (14 downto 0);
    signal expOvf_uid29_fpToFPTest_n : STD_LOGIC_VECTOR (0 downto 0);
    signal inRegAndUdf_uid30_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal excRZero_uid31_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal inRegAndOvf_uid32_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal excRInf_uid33_fpToFPTest_q : STD_LOGIC_VECTOR (0 downto 0);
    signal concExc_uid34_fpToFPTest_q : STD_LOGIC_VECTOR (2 downto 0);
    signal excREnc_uid35_fpToFPTest_q : STD_LOGIC_VECTOR (1 downto 0);
    signal oneFracRPostExc2_uid36_fpToFPTest_q : STD_LOGIC_VECTOR (51 downto 0);
    signal zeroFracRPostExc_uid37_fpToFPTest_q : STD_LOGIC_VECTOR (51 downto 0);
    signal fracRPostExc_uid39_fpToFPTest_s : STD_LOGIC_VECTOR (1 downto 0);
    signal fracRPostExc_uid39_fpToFPTest_q : STD_LOGIC_VECTOR (51 downto 0);
    signal zeroExpRPostExc_uid42_fpToFPTest_q : STD_LOGIC_VECTOR (10 downto 0);
    signal expRPostExc_uid43_fpToFPTest_s : STD_LOGIC_VECTOR (1 downto 0);
    signal expRPostExc_uid43_fpToFPTest_q : STD_LOGIC_VECTOR (10 downto 0);
    signal signX_uid44_fpToFPTest_b : STD_LOGIC_VECTOR (0 downto 0);
    signal fpRes_uid45_fpToFPTest_q : STD_LOGIC_VECTOR (63 downto 0);

begin


    -- signX_uid44_fpToFPTest(BITSELECT,43)@0
    signX_uid44_fpToFPTest_b <= STD_LOGIC_VECTOR(a(31 downto 31));

    -- expWEOutAllO_uid28_fpToFPTest(CONSTANT,27)
    expWEOutAllO_uid28_fpToFPTest_q <= "11111111111";

    -- cstBiasOut_uid6_fpToFPTest(CONSTANT,5)
    cstBiasOut_uid6_fpToFPTest_q <= "001110000000";

    -- exp_x_uid10_fpToFPTest(BITSELECT,9)@0
    exp_x_uid10_fpToFPTest_b <= a(30 downto 23);

    -- expRExt_uid25_fpToFPTest(ADD,24)@0
    expRExt_uid25_fpToFPTest_a <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR("000000" & exp_x_uid10_fpToFPTest_b));
    expRExt_uid25_fpToFPTest_b <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR((13 downto 12 => cstBiasOut_uid6_fpToFPTest_q(11)) & cstBiasOut_uid6_fpToFPTest_q));
    expRExt_uid25_fpToFPTest_o <= STD_LOGIC_VECTOR(SIGNED(expRExt_uid25_fpToFPTest_a) + SIGNED(expRExt_uid25_fpToFPTest_b));
    expRExt_uid25_fpToFPTest_q <= expRExt_uid25_fpToFPTest_o(12 downto 0);

    -- expR_uid26_fpToFPTest(BITSELECT,25)@0
    expR_uid26_fpToFPTest_in <= expRExt_uid25_fpToFPTest_q(10 downto 0);
    expR_uid26_fpToFPTest_b <= expR_uid26_fpToFPTest_in(10 downto 0);

    -- zeroExpRPostExc_uid42_fpToFPTest(CONSTANT,41)
    zeroExpRPostExc_uid42_fpToFPTest_q <= "00000000000";

    -- frac_x_uid11_fpToFPTest(BITSELECT,10)@0
    frac_x_uid11_fpToFPTest_b <= a(22 downto 0);

    -- cstZeroWF_uid8_fpToFPTest(CONSTANT,7)
    cstZeroWF_uid8_fpToFPTest_q <= "00000000000000000000000";

    -- fracXIsZero_uid14_fpToFPTest(LOGICAL,13)@0
    fracXIsZero_uid14_fpToFPTest_q <= "1" WHEN cstZeroWF_uid8_fpToFPTest_q = frac_x_uid11_fpToFPTest_b ELSE "0";

    -- fracXIsNotZero_uid15_fpToFPTest(LOGICAL,14)@0
    fracXIsNotZero_uid15_fpToFPTest_q <= not (fracXIsZero_uid14_fpToFPTest_q);

    -- cstAllOWE_uid7_fpToFPTest(CONSTANT,6)
    cstAllOWE_uid7_fpToFPTest_q <= "11111111";

    -- expXIsMax_uid13_fpToFPTest(LOGICAL,12)@0
    expXIsMax_uid13_fpToFPTest_q <= "1" WHEN exp_x_uid10_fpToFPTest_b = cstAllOWE_uid7_fpToFPTest_q ELSE "0";

    -- excN_x_uid17_fpToFPTest(LOGICAL,16)@0
    excN_x_uid17_fpToFPTest_q <= expXIsMax_uid13_fpToFPTest_q and fracXIsNotZero_uid15_fpToFPTest_q;

    -- GND(CONSTANT,0)
    GND_q <= "0";

    -- expOvf_uid29_fpToFPTest(COMPARE,28)@0
    expOvf_uid29_fpToFPTest_a <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR((14 downto 13 => expRExt_uid25_fpToFPTest_q(12)) & expRExt_uid25_fpToFPTest_q));
    expOvf_uid29_fpToFPTest_b <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR("0000" & expWEOutAllO_uid28_fpToFPTest_q));
    expOvf_uid29_fpToFPTest_o <= STD_LOGIC_VECTOR(SIGNED(expOvf_uid29_fpToFPTest_a) - SIGNED(expOvf_uid29_fpToFPTest_b));
    expOvf_uid29_fpToFPTest_n(0) <= not (expOvf_uid29_fpToFPTest_o(14));

    -- invExpXIsMax_uid18_fpToFPTest(LOGICAL,17)@0
    invExpXIsMax_uid18_fpToFPTest_q <= not (expXIsMax_uid13_fpToFPTest_q);

    -- cstAllZWE_uid9_fpToFPTest(CONSTANT,8)
    cstAllZWE_uid9_fpToFPTest_q <= "00000000";

    -- excZ_x_uid12_fpToFPTest(LOGICAL,11)@0
    excZ_x_uid12_fpToFPTest_q <= "1" WHEN exp_x_uid10_fpToFPTest_b = cstAllZWE_uid9_fpToFPTest_q ELSE "0";

    -- InvExpXIsZero_uid19_fpToFPTest(LOGICAL,18)@0
    InvExpXIsZero_uid19_fpToFPTest_q <= not (excZ_x_uid12_fpToFPTest_q);

    -- excR_x_uid20_fpToFPTest(LOGICAL,19)@0
    excR_x_uid20_fpToFPTest_q <= InvExpXIsZero_uid19_fpToFPTest_q and invExpXIsMax_uid18_fpToFPTest_q;

    -- inRegAndOvf_uid32_fpToFPTest(LOGICAL,31)@0
    inRegAndOvf_uid32_fpToFPTest_q <= excR_x_uid20_fpToFPTest_q and expOvf_uid29_fpToFPTest_n;

    -- excI_x_uid16_fpToFPTest(LOGICAL,15)@0
    excI_x_uid16_fpToFPTest_q <= expXIsMax_uid13_fpToFPTest_q and fracXIsZero_uid14_fpToFPTest_q;

    -- excRInf_uid33_fpToFPTest(LOGICAL,32)@0
    excRInf_uid33_fpToFPTest_q <= excI_x_uid16_fpToFPTest_q or inRegAndOvf_uid32_fpToFPTest_q;

    -- expUdf_uid27_fpToFPTest(COMPARE,26)@0
    expUdf_uid27_fpToFPTest_a <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR("00000000000000" & GND_q));
    expUdf_uid27_fpToFPTest_b <= STD_LOGIC_VECTOR(STD_LOGIC_VECTOR((14 downto 13 => expRExt_uid25_fpToFPTest_q(12)) & expRExt_uid25_fpToFPTest_q));
    expUdf_uid27_fpToFPTest_o <= STD_LOGIC_VECTOR(SIGNED(expUdf_uid27_fpToFPTest_a) - SIGNED(expUdf_uid27_fpToFPTest_b));
    expUdf_uid27_fpToFPTest_n(0) <= not (expUdf_uid27_fpToFPTest_o(14));

    -- inRegAndUdf_uid30_fpToFPTest(LOGICAL,29)@0
    inRegAndUdf_uid30_fpToFPTest_q <= excR_x_uid20_fpToFPTest_q and expUdf_uid27_fpToFPTest_n;

    -- excRZero_uid31_fpToFPTest(LOGICAL,30)@0
    excRZero_uid31_fpToFPTest_q <= excZ_x_uid12_fpToFPTest_q or inRegAndUdf_uid30_fpToFPTest_q;

    -- concExc_uid34_fpToFPTest(BITJOIN,33)@0
    concExc_uid34_fpToFPTest_q <= excN_x_uid17_fpToFPTest_q & excRInf_uid33_fpToFPTest_q & excRZero_uid31_fpToFPTest_q;

    -- excREnc_uid35_fpToFPTest(LOOKUP,34)@0
    excREnc_uid35_fpToFPTest_combproc: PROCESS (concExc_uid34_fpToFPTest_q)
    BEGIN
        -- Begin reserved scope level
        CASE (concExc_uid34_fpToFPTest_q) IS
            WHEN "000" => excREnc_uid35_fpToFPTest_q <= "01";
            WHEN "001" => excREnc_uid35_fpToFPTest_q <= "00";
            WHEN "010" => excREnc_uid35_fpToFPTest_q <= "10";
            WHEN "011" => excREnc_uid35_fpToFPTest_q <= "00";
            WHEN "100" => excREnc_uid35_fpToFPTest_q <= "11";
            WHEN "101" => excREnc_uid35_fpToFPTest_q <= "00";
            WHEN "110" => excREnc_uid35_fpToFPTest_q <= "00";
            WHEN "111" => excREnc_uid35_fpToFPTest_q <= "00";
            WHEN OTHERS => -- unreachable
                           excREnc_uid35_fpToFPTest_q <= (others => '-');
        END CASE;
        -- End reserved scope level
    END PROCESS;

    -- VCC(CONSTANT,1)
    VCC_q <= "1";

    -- expRPostExc_uid43_fpToFPTest(MUX,42)@0
    expRPostExc_uid43_fpToFPTest_s <= excREnc_uid35_fpToFPTest_q;
    expRPostExc_uid43_fpToFPTest_combproc: PROCESS (expRPostExc_uid43_fpToFPTest_s, zeroExpRPostExc_uid42_fpToFPTest_q, expR_uid26_fpToFPTest_b, expWEOutAllO_uid28_fpToFPTest_q)
    BEGIN
        CASE (expRPostExc_uid43_fpToFPTest_s) IS
            WHEN "00" => expRPostExc_uid43_fpToFPTest_q <= zeroExpRPostExc_uid42_fpToFPTest_q;
            WHEN "01" => expRPostExc_uid43_fpToFPTest_q <= expR_uid26_fpToFPTest_b;
            WHEN "10" => expRPostExc_uid43_fpToFPTest_q <= expWEOutAllO_uid28_fpToFPTest_q;
            WHEN "11" => expRPostExc_uid43_fpToFPTest_q <= expWEOutAllO_uid28_fpToFPTest_q;
            WHEN OTHERS => expRPostExc_uid43_fpToFPTest_q <= (others => '0');
        END CASE;
    END PROCESS;

    -- oneFracRPostExc2_uid36_fpToFPTest(CONSTANT,35)
    oneFracRPostExc2_uid36_fpToFPTest_q <= "0000000000000000000000000000000000000000000000000001";

    -- zP_uid23_fpToFPTest(CONSTANT,22)
    zP_uid23_fpToFPTest_q <= "00000000000000000000000000000";

    -- fracR_uid24_fpToFPTest(BITJOIN,23)@0
    fracR_uid24_fpToFPTest_q <= frac_x_uid11_fpToFPTest_b & zP_uid23_fpToFPTest_q;

    -- zeroFracRPostExc_uid37_fpToFPTest(CONSTANT,36)
    zeroFracRPostExc_uid37_fpToFPTest_q <= "0000000000000000000000000000000000000000000000000000";

    -- fracRPostExc_uid39_fpToFPTest(MUX,38)@0
    fracRPostExc_uid39_fpToFPTest_s <= excREnc_uid35_fpToFPTest_q;
    fracRPostExc_uid39_fpToFPTest_combproc: PROCESS (fracRPostExc_uid39_fpToFPTest_s, zeroFracRPostExc_uid37_fpToFPTest_q, fracR_uid24_fpToFPTest_q, oneFracRPostExc2_uid36_fpToFPTest_q)
    BEGIN
        CASE (fracRPostExc_uid39_fpToFPTest_s) IS
            WHEN "00" => fracRPostExc_uid39_fpToFPTest_q <= zeroFracRPostExc_uid37_fpToFPTest_q;
            WHEN "01" => fracRPostExc_uid39_fpToFPTest_q <= fracR_uid24_fpToFPTest_q;
            WHEN "10" => fracRPostExc_uid39_fpToFPTest_q <= zeroFracRPostExc_uid37_fpToFPTest_q;
            WHEN "11" => fracRPostExc_uid39_fpToFPTest_q <= oneFracRPostExc2_uid36_fpToFPTest_q;
            WHEN OTHERS => fracRPostExc_uid39_fpToFPTest_q <= (others => '0');
        END CASE;
    END PROCESS;

    -- fpRes_uid45_fpToFPTest(BITJOIN,44)@0
    fpRes_uid45_fpToFPTest_q <= signX_uid44_fpToFPTest_b & expRPostExc_uid43_fpToFPTest_q & fracRPostExc_uid39_fpToFPTest_q;

    -- xOut(GPOUT,4)@0
    q <= fpRes_uid45_fpToFPTest_q;

END normal;
