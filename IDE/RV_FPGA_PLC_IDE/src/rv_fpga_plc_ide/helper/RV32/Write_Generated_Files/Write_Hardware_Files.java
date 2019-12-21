/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.RV32.Write_Generated_Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rv_fpga_plc_ide.helper.Data;
import rv_fpga_plc_ide.helper.GeneralFunctions;
import rv_fpga_plc_ide.main.RV_FPGA_PLC_IDE;

/**
 *
 * @author hossameldin
 */
public class Write_Hardware_Files {
    public void generate_q_files(String Project_Folder) {
        generate_q_subfolders(Project_Folder+"hdl_code/");
        generate_q_hdl_toplevel_for_sw_comp_files(Project_Folder);
        generate_q_hdl_basic_files(Project_Folder);
    }
    
    public void generate_q_files_variables(String Project_Folder) {
        generate_q_subfolders(Project_Folder+"hdl_code/");
        generate_q_hdl_toplevel_for_hw_comp_files(Project_Folder);
        generate_q_hdl_basic_files(Project_Folder);
    }
    
    private void generate_q_subfolders(String Project_Folder) {
        File file;
        file = new File(Project_Folder+"potato_processor/vhdl"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/processor/vhdl"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/func_block_constant/time_measurement/vhdl"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/func_block_constant/time_measurement/verilog"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/func_block_constant/in_out_peripheral/vhdl"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/func_block_constant/clock_generator"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/func_block_constant/aee_rom/vhdl"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/func_block_constant/clock_generator/clock_generator"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/func_block_variable/TON/vhdl"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/func_block_variable/PWM/vhdl"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/multi_blocks/DIV"); file.mkdirs();
        file = new File(Project_Folder+"peripherals/multi_blocks/MULT"); file.mkdirs();
    }
    
    private void generate_q_hdl_toplevel_for_sw_comp_files(String Project_Folder){
        generate_toplevel_vhd_file_sw(Project_Folder+"hdl_code/");
        generate_qsf_file_sw(Project_Folder);
    }
    
    private void generate_q_hdl_toplevel_for_hw_comp_files(String Project_Folder){
        generate_toplevel_vhd_file_hw(Project_Folder+"hdl_code/");
        generate_qsf_file_sw(Project_Folder); // TODO need to be converted to hw
    }

    private void generate_q_hdl_basic_files(String Project_Folder) {
        generate_qpf_file(Project_Folder);
        generate_RV_FPGA_PLC_Potato_vhd_file(Project_Folder);
        generate_pp_soc_reset_vhd_file(Project_Folder);
        generate_pp_utilities_vhd_file(Project_Folder);
        generate_pp_types_vhd_file(Project_Folder);
        generate_pp_constants_vhd_file(Project_Folder);
        generate_pp_potato_vhd_file(Project_Folder);
        generate_pp_soc_timer_vhd_file(Project_Folder);
        generate_pp_soc_uart_vhd_file(Project_Folder);
        generate_pp_soc_intercon_vhd_file(Project_Folder);
        generate_aee_rom_wrapper_vhd_file(Project_Folder);
        generate_pp_soc_memory_vhd_file(Project_Folder);
        generate_In_Out_Peripheral_vhd_file(Project_Folder);
        generate_Time_Measurement_Peripheral_vhd_file(Project_Folder);
        generate_pp_fifo_vhd_file(Project_Folder);
        generate_pp_core_vhd_file(Project_Folder);
        generate_pp_csr_vhd_file(Project_Folder);
        generate_pp_icache_vhd_file(Project_Folder);
        generate_pp_wb_adapter_vhd_file(Project_Folder);
        generate_pp_wb_arbiter_vhd_file(Project_Folder);
        generate_pp_csr_unit_vhd_file(Project_Folder);
        generate_pp_counter_vhd_file(Project_Folder);
        generate_pp_register_file_vhd_file(Project_Folder);
        generate_pp_fetch_vhd_file(Project_Folder);
        generate_pp_decode_vhd_file(Project_Folder);
        generate_pp_imm_decoder_vhd_file(Project_Folder);
        generate_pp_control_unit_vhd_file(Project_Folder);
        generate_pp_execute_vhd_file(Project_Folder);
        generate_pp_alu_mux_vhd_file(Project_Folder);
        generate_pp_comparator_vhd_file(Project_Folder);
        generate_pp_alu_vhd_file(Project_Folder);
        generate_pp_csr_alu_vhd_file(Project_Folder);
        generate_pp_memory_vhd_file(Project_Folder);
        generate_pp_writeback_vhd_file(Project_Folder);
        generate_pp_alu_control_unit_vhd_file(Project_Folder);
        generate_Time_Calculation_vhd_file(Project_Folder);
        generate_Write_To_Hexa_vhd_file(Project_Folder);
        generate_clock_generator_vhd_file(Project_Folder);
        generate_clock_generator_vho_file(Project_Folder);
        generate_clock_generator_002_v_file(Project_Folder);
        generate_clock_generator_002_qip_file(Project_Folder);
        generate_clock_generator_sip_file(Project_Folder);
        generate_clock_generator_qip_file(Project_Folder);
        generate_aee_rom_vhd_file(Project_Folder);
        generate_SEG7_LUT_4_v_file(Project_Folder);
        generate_SEG7_LUT_v_file(Project_Folder);
        generate_TON_Peripheral_vhd_file(Project_Folder);
        //generate_Timer_on_64_Controller_vhd_file(Project_Folder);
        generate_Counter_Down_64_bit_Cin_vhd_file(Project_Folder);
        generate_RV_FPGA_PLC_Potato_sdc_file(Project_Folder);
        generate_aee_rom_qip_file(Project_Folder);
        generate_aee_rom_cmp_file(Project_Folder);
        generate_PWM_Peripheral_vhd_file(Project_Folder);
        generate_PWM_32_bit_vhd_file(Project_Folder);
        generate_Div_32_bit_vhd_file(Project_Folder);
        generate_Div_32_bit_qip_file(Project_Folder);
        generate_Mult_32_bit_vhd_file(Project_Folder);
        generate_Mult_32_bit_qip_file(Project_Folder);
    }

    private void generate_qpf_file(String Project_Folder) {
        FileOutputStream fileOutSt = null;
        String data =   "# -------------------------------------------------------------------------- #\n" +
                        "#\n" +
                        "# Copyright (C) 2018  Intel Corporation. All rights reserved.\n" +
                        "# Your use of Intel Corporation's design tools, logic functions \n" +
                        "# and other software and tools, and its AMPP partner logic \n" +
                        "# functions, and any output files from any of the foregoing \n" +
                        "# (including device programming or simulation files), and any \n" +
                        "# associated documentation or information are expressly subject \n" +
                        "# to the terms and conditions of the Intel Program License \n" +
                        "# Subscription Agreement, the Intel Quartus Prime License Agreement,\n" +
                        "# the Intel FPGA IP License Agreement, or other applicable license\n" +
                        "# agreement, including, without limitation, that your use is for\n" +
                        "# the sole purpose of programming logic devices manufactured by\n" +
                        "# Intel and sold by Intel or its authorized distributors.  Please\n" +
                        "# refer to the applicable agreement for further details.\n" +
                        "#\n" +
                        "# -------------------------------------------------------------------------- #\n" +
                        "#\n" +
                        "# Quartus Prime\n" +
                        "# Version 18.0.0 Build 614 04/24/2018 SJ Lite Edition\n" +
                        "# Date created = 17:35:10  February 03, 2019\n" +
                        "#\n" +
                        "# -------------------------------------------------------------------------- #\n" +
                        "\n" +
                        "QUARTUS_VERSION = \"18.0\"\n" +
                        "DATE = \"17:35:10  February 03, 2019\"\n" +
                        "\n" +
                        "# Revisions\n" +
                        "\n" +
                        "PROJECT_REVISION = \"RV_FPGA_PLC_Potato\"";
        try {
            new File(Project_Folder+"RV_FPGA_PLC_Potato.qpf").delete();
            fileOutSt = new FileOutputStream(Project_Folder+"RV_FPGA_PLC_Potato.qpf");
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_qsf_file_sw(String Project_Folder) {
        FileOutputStream fileOutSt = null;
        String data =   "# -------------------------------------------------------------------------- #\n" +
                        "#\n" +
                        "# Copyright (C) 2018  Intel Corporation. All rights reserved.\n" +
                        "# Your use of Intel Corporation's design tools, logic functions \n" +
                        "# and other software and tools, and its AMPP partner logic \n" +
                        "# functions, and any output files from any of the foregoing \n" +
                        "# (including device programming or simulation files), and any \n" +
                        "# associated documentation or information are expressly subject \n" +
                        "# to the terms and conditions of the Intel Program License \n" +
                        "# Subscription Agreement, the Intel Quartus Prime License Agreement,\n" +
                        "# the Intel FPGA IP License Agreement, or other applicable license\n" +
                        "# agreement, including, without limitation, that your use is for\n" +
                        "# the sole purpose of programming logic devices manufactured by\n" +
                        "# Intel and sold by Intel or its authorized distributors.  Please\n" +
                        "# refer to the applicable agreement for further details.\n" +
                        "#\n" +
                        "# -------------------------------------------------------------------------- #\n" +
                        "#\n" +
                        "# Quartus Prime\n" +
                        "# Version 18.0.0 Build 614 04/24/2018 SJ Lite Edition\n" +
                        "# Date created = 17:35:10  February 03, 2019\n" +
                        "#\n" +
                        "# -------------------------------------------------------------------------- #\n" +
                        "#\n" +
                        "# Notes:\n" +
                        "#\n" +
                        "# 1) The default values for assignments are stored in the file:\n" +
                        "#		Potato_SoC_assignment_defaults.qdf\n" +
                        "#    If this file doesn't exist, see file:\n" +
                        "#		assignment_defaults.qdf\n" +
                        "#\n" +
                        "# 2) Altera recommends that you do not modify this file. This\n" +
                        "#    file is updated automatically by the Quartus Prime software\n" +
                        "#    and any changes you make may be lost or overwritten.\n" +
                        "#\n" +
                        "# -------------------------------------------------------------------------- #\n" +
                        "\n" +
                        "\n" +
                        "set_global_assignment -name FAMILY \"Cyclone V\"\n" +
                        "set_global_assignment -name DEVICE 5CGXFC5C6F27C7\n" +
                        "set_global_assignment -name ORIGINAL_QUARTUS_VERSION 18.0.0\n" +
                        "set_global_assignment -name PROJECT_CREATION_TIME_DATE \"17:35:10  FEBRUARY 03, 2019\"\n" +
                        "set_global_assignment -name LAST_QUARTUS_VERSION \"18.0.0 Lite Edition\"\n" +
                        "set_global_assignment -name PROJECT_OUTPUT_DIRECTORY output_files\n" +
                        "set_global_assignment -name MIN_CORE_JUNCTION_TEMP 0\n" +
                        "set_global_assignment -name MAX_CORE_JUNCTION_TEMP 85\n" +
                        "set_global_assignment -name ERROR_CHECK_FREQUENCY_DIVISOR 256\n" +
                        "set_global_assignment -name EDA_SIMULATION_TOOL \"ModelSim-Altera (VHDL)\"\n" +
                        "set_global_assignment -name EDA_TIME_SCALE \"1 ps\" -section_id eda_simulation\n" +
                        "set_global_assignment -name EDA_OUTPUT_DATA_FORMAT VHDL -section_id eda_simulation\n" +
                        "set_global_assignment -name POWER_PRESET_COOLING_SOLUTION \"23 MM HEAT SINK WITH 200 LFPM AIRFLOW\"\n" +
                        "set_global_assignment -name POWER_BOARD_THERMAL_MODEL \"NONE (CONSERVATIVE)\"\n" +
                        "set_global_assignment -name NUM_PARALLEL_PROCESSORS \"4\"" +
                        "\n" +
                        "set_location_assignment PIN_L9 -to UART_TX\n" +
                        "set_location_assignment PIN_M9 -to UART_RX\n" +
                        "set_location_assignment PIN_AC9 -to SW[0]\n" +
                        "set_location_assignment PIN_AE10 -to SW[1]\n" +
                        "set_location_assignment PIN_AD13 -to SW[2]\n" +
                        "set_location_assignment PIN_AC8 -to SW[3]\n" +
                        "set_location_assignment PIN_W11 -to SW[4]\n" +
                        "set_location_assignment PIN_AB10 -to SW[5]\n" +
                        "set_location_assignment PIN_V10 -to SW[6]\n" +
                        "set_location_assignment PIN_AC10 -to SW[7]\n" +
                        "set_location_assignment PIN_Y11 -to SW[8]\n" +
                        "set_location_assignment PIN_AE19 -to SW[9]\n" +
                        "set_location_assignment PIN_F7 -to LEDR[0]\n" +
                        "set_location_assignment PIN_F6 -to LEDR[1]\n" +
                        "set_location_assignment PIN_G6 -to LEDR[2]\n" +
                        "set_location_assignment PIN_G7 -to LEDR[3]\n" +
                        "set_location_assignment PIN_J8 -to LEDR[4]\n" +
                        "set_location_assignment PIN_J7 -to LEDR[5]\n" +
                        "set_location_assignment PIN_K10 -to LEDR[6]\n" +
                        "set_location_assignment PIN_K8 -to LEDR[7]\n" +
                        "set_location_assignment PIN_H7 -to LEDR[8]\n" +
                        "set_location_assignment PIN_J10 -to LEDR[9]\n" +
                        "set_location_assignment PIN_L7 -to LEDG[0]\n" +
                        "set_location_assignment PIN_K6 -to LEDG[1]\n" +
                        "set_location_assignment PIN_D8 -to LEDG[2]\n" +
                        "set_location_assignment PIN_E9 -to LEDG[3]\n" +
                        "set_location_assignment PIN_A5 -to LEDG[4]\n" +
                        "set_location_assignment PIN_B6 -to LEDG[5]\n" +
                        "set_location_assignment PIN_H8 -to LEDG[6]\n" +
                        "set_location_assignment PIN_H9 -to LEDG[7]\n" +
                        "set_location_assignment PIN_P11 -to KEY_n[0]\n" +
                        "set_location_assignment PIN_P12 -to KEY_n[1]\n" +
                        "set_location_assignment PIN_Y15 -to KEY_n[2]\n" +
                        "set_location_assignment PIN_Y16 -to KEY_n[3]\n" +
                        "set_location_assignment PIN_AA18 -to HEX1[0]\n" +
                        "set_location_assignment PIN_AD26 -to HEX1[1]\n" +
                        "set_location_assignment PIN_AB19 -to HEX1[2]\n" +
                        "set_location_assignment PIN_AE26 -to HEX1[3]\n" +
                        "set_location_assignment PIN_AE25 -to HEX1[4]\n" +
                        "set_location_assignment PIN_AC19 -to HEX1[5]\n" +
                        "set_location_assignment PIN_AF24 -to HEX1[6]\n" +
                        "set_location_assignment PIN_V19 -to HEX0[0]\n" +
                        "set_location_assignment PIN_V18 -to HEX0[1]\n" +
                        "set_location_assignment PIN_V17 -to HEX0[2]\n" +
                        "set_location_assignment PIN_W18 -to HEX0[3]\n" +
                        "set_location_assignment PIN_Y20 -to HEX0[4]\n" +
                        "set_location_assignment PIN_Y19 -to HEX0[5]\n" +
                        "set_location_assignment PIN_Y18 -to HEX0[6]\n" +
                        "set_location_assignment PIN_T13 -to CLOCK_50_B3B\n" +
                        "set_location_assignment PIN_R20 -to CLOCK_50_B5B\n" +
                        "set_location_assignment PIN_N20 -to CLOCK_50_B6A\n" +
                        "set_location_assignment PIN_H12 -to CLOCK_50_B7A\n" +
                        "set_location_assignment PIN_M10 -to CLOCK_50_B8A\n" +
                        "set_location_assignment PIN_U12 -to CLOCK_125_p\n" +
                        "set_location_assignment PIN_AB24 -to CPU_RESET_n\n" +
                        "set_location_assignment PIN_V12 -to \"CLOCK_125_p(n)\"\n" +
                        "set_location_assignment PIN_T21 -to GPIO_IN[0]\n" +
                        "set_location_assignment PIN_D26 -to GPIO_IN[1]\n" +
                        "set_location_assignment PIN_Y9 -to GPIO_IN[17]\n" +
                        "set_location_assignment PIN_F26 -to GPIO_IN[16]\n" +
                        "set_location_assignment PIN_R10 -to GPIO_IN[15]\n" +
                        "set_location_assignment PIN_R9 -to GPIO_IN[14]\n" +
                        "set_location_assignment PIN_R8 -to GPIO_IN[13]\n" +
                        "set_location_assignment PIN_P8 -to GPIO_IN[12]\n" +
                        "set_location_assignment PIN_U22 -to GPIO_IN[11]\n" +
                        "set_location_assignment PIN_U19 -to GPIO_IN[10]\n" +
                        "set_location_assignment PIN_K25 -to GPIO_IN[2]\n" +
                        "set_location_assignment PIN_K26 -to GPIO_IN[4]\n" +
                        "set_location_assignment PIN_E26 -to GPIO_IN[3]\n" +
                        "set_location_assignment PIN_M26 -to GPIO_IN[5]\n" +
                        "set_location_assignment PIN_M21 -to GPIO_IN[6]\n" +
                        "set_location_assignment PIN_P20 -to GPIO_IN[7]\n" +
                        "set_location_assignment PIN_T22 -to GPIO_IN[8]\n" +
                        "set_location_assignment PIN_T19 -to GPIO_IN[9]\n" +
                        "set_location_assignment PIN_Y8 -to GPIO_OUT[1]\n" +
                        "set_location_assignment PIN_AA7 -to GPIO_OUT[2]\n" +
                        "set_location_assignment PIN_G26 -to GPIO_OUT[0]\n" +
                        "set_location_assignment PIN_AA6 -to GPIO_OUT[3]\n" +
                        "\n" +
                        "set_global_assignment -name TOP_LEVEL_ENTITY RV_FPGA_PLC_Potato\n" +
                        "\n" +
                        "set_global_assignment -name PARTITION_NETLIST_TYPE SOURCE -section_id Top\n" +
                        "set_global_assignment -name PARTITION_FITTER_PRESERVATION_LEVEL PLACEMENT_AND_ROUTING -section_id Top\n" +
                        "set_global_assignment -name PARTITION_COLOR 16764057 -section_id Top\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to CLOCK_50_B3B\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to CLOCK_50_B5B\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to CLOCK_50_B6A\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to CLOCK_50_B7A\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to CLOCK_50_B8A\n" +
                        "set_instance_assignment -name IO_STANDARD LVDS -to CLOCK_125_p\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to CPU_RESET_n\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX0[0]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX0[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX0[2]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX0[3]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX0[4]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX0[5]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX0[6]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX1[0]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX1[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX1[2]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX1[3]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX1[4]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX1[5]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to HEX1[6]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to KEY_n[3]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to KEY_n[2]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to KEY_n[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to KEY_n[0]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDR[9]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDR[8]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDR[7]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDR[6]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDR[5]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDR[4]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDR[3]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDR[2]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDR[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDR[0]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDG[7]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDG[6]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDG[5]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDG[4]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDG[3]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDG[2]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDG[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to LEDG[0]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to SW[9]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to SW[8]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to SW[7]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to SW[6]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to SW[5]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to SW[4]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to SW[3]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to SW[2]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to SW[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"1.2 V\" -to SW[0]\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to UART_TX\n" +
                        "set_instance_assignment -name IO_STANDARD \"2.5 V\" -to UART_RX\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[0]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[17]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[16]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[15]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[14]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[13]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[12]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[11]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[10]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[2]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[4]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[3]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[5]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[6]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[7]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[8]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_IN[9]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[2]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[0]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[3]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[4]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[5]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[6]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[7]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[8]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[9]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[10]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[11]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[12]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[13]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[14]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[15]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[16]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to GPIO_OUT[17]\n" +
                        "set_location_assignment PIN_AD7 -to HEX2[0]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX2[0]\n" +
                        "set_location_assignment PIN_AD6 -to HEX2[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX2[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX2[2]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX2[3]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX2[4]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX2[5]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX2[6]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX3[0]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX3[1]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX3[2]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX3[3]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX3[4]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX3[5]\n" +
                        "set_instance_assignment -name IO_STANDARD \"3.3-V LVTTL\" -to HEX3[6]\n" +
                        "set_location_assignment PIN_U20 -to HEX2[2]\n" +
                        "set_location_assignment PIN_V22 -to HEX2[3]\n" +
                        "set_location_assignment PIN_V20 -to HEX2[4]\n" +
                        "set_location_assignment PIN_W21 -to HEX2[5]\n" +
                        "set_location_assignment PIN_W20 -to HEX2[6]\n" +
                        "set_location_assignment PIN_Y24 -to HEX3[0]\n" +
                        "set_location_assignment PIN_Y23 -to HEX3[1]\n" +
                        "set_location_assignment PIN_AA23 -to HEX3[2]\n" +
                        "set_location_assignment PIN_AA22 -to HEX3[3]\n" +
                        "set_location_assignment PIN_AC24 -to HEX3[4]\n" +
                        "set_location_assignment PIN_AC23 -to HEX3[5]\n" +
                        "set_location_assignment PIN_AC22 -to HEX3[6]\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/func_block_variable/PWM/vhdl/PWM_Peripheral.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/toplevel.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_writeback.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_wb_arbiter.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_wb_adapter.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_utilities.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_types.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_register_file.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_potato.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_memory.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_imm_decoder.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_icache.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_fifo.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_fetch.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_execute.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_decode.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_csr_unit.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_csr_alu.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_csr.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_counter.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_core.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_control_unit.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_constants.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_comparator.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_alu_mux.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_alu_control_unit.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/potato_processor/vhdl/pp_alu.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/processor/vhdl/pp_soc_uart.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/processor/vhdl/pp_soc_timer.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/processor/vhdl/pp_soc_reset.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/processor/vhdl/pp_soc_memory.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/processor/vhdl/pp_soc_intercon.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/processor/vhdl/aee_rom_wrapper.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/func_block_variable/TON/vhdl/TON_Peripheral.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/func_block_variable/TON/vhdl/Counter_Down_64_bit_Cin.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/func_block_constant/time_measurement/vhdl/Write_To_Hexa.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/func_block_constant/time_measurement/vhdl/Time_Measurement_Peripheral.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/func_block_constant/time_measurement/vhdl/Time_Calculation.vhd\n" +
                        "set_global_assignment -name VERILOG_FILE hdl_code/peripherals/func_block_constant/time_measurement/verilog/SEG7_LUT_4.v\n" +
                        "set_global_assignment -name VERILOG_FILE hdl_code/peripherals/func_block_constant/time_measurement/verilog/SEG7_LUT.v\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/func_block_constant/in_out_peripheral/vhdl/In_Out_Peripheral.vhd\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/func_block_constant/clock_generator/clock_generator.vhd -library clock_generator\n" +
                        "set_global_assignment -name QIP_FILE hdl_code/peripherals/func_block_constant/clock_generator/clock_generator.qip\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/func_block_constant/aee_rom/vhdl/aee_rom.vhd\n" +
                        "set_global_assignment -name QIP_FILE hdl_code/peripherals/func_block_constant/aee_rom/vhdl/aee_rom.qip\n" +
                        "set_global_assignment -name VHDL_FILE hdl_code/peripherals/func_block_variable/PWM/vhdl/PWM_32_bit.vhd\n" +
                        "set_global_assignment -name QIP_FILE hdl_code/peripherals/multi_blocks/DIV/Div_32_bit.qip\n" +
                        "set_global_assignment -name QIP_FILE hdl_code/peripherals/multi_blocks/MULT/Mult_32_bit.qip\n" +
                        "set_instance_assignment -name PARTITION_HIERARCHY root_partition -to | -section_id Top";
        try {
            new File(Project_Folder+"RV_FPGA_PLC_Potato.qsf").delete();
            fileOutSt = new FileOutputStream(Project_Folder+"RV_FPGA_PLC_Potato.qsf");
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_RV_FPGA_PLC_Potato_vhd_file(String Project_Folder) {
        FileOutputStream fileOutSt = null;
        String data =   "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "entity RV_FPGA_PLC_Potato is\n" +
                        "	port(\n" +
                        "		--------- CLOCK ---------\n" +
                        "		CLOCK_125_p	: in std_logic;\n" +
                        "		CLOCK_50_B3B: in std_logic;\n" +
                        "		CLOCK_50_B5B: in std_logic;\n" +
                        "		CLOCK_50_B6A: in std_logic;\n" +
                        "		CLOCK_50_B7A: in std_logic;\n" +
                        "		CLOCK_50_B8A: in std_logic;\n" +
                        "		\n" +
                        "		--------- CPU ---------\n" +
                        "		CPU_RESET_n	: in std_logic;\n" +
                        "		\n" +
                        "		--------- GPIO ---------\n" +
                        "		GPIO_IN		: in std_logic_vector(17 DOWNTO 0);\n" +
                        "		GPIO_OUT		: out std_logic_vector(17 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- HEX0 ---------\n" +
                        "		HEX0			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "\n" +
                        "		--------- HEX1 ---------\n" +
                        "		HEX1			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- HEX2 ---------\n" +
                        "		HEX2			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- HEX3 ---------\n" +
                        "		HEX3			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- KEY ---------\n" +
                        "		KEY_n			: in std_logic_vector(3 DOWNTO 0);\n" +
                        "\n" +
                        "		--------- LEDG ---------\n" +
                        "		LEDG			: out std_logic_vector(7 DOWNTO 0);\n" +
                        "\n" +
                        "		--------- LEDR ---------\n" +
                        "		LEDR			: out std_logic_vector(9 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- SW ---------\n" +
                        "		SW				: in std_logic_vector(9 DOWNTO 0);\n" +
                        "\n" +
                        "		--------- UART ---------\n" +
                        "		UART_RX		: in std_logic;\n" +
                        "		UART_TX		: out std_logic\n" +
                        "	);\n" +
                        "end entity RV_FPGA_PLC_Potato;\n" +
                        "\n" +
                        "architecture behaviour of RV_FPGA_PLC_Potato is\n" +
                        "\n" +
                        "	component toplevel is\n" +
                        "		port(\n" +
                        "			clk     : in  std_logic;\n" +
                        "			reset_n : in  std_logic;\n" +
                        "\n" +
                        "			-- UART0 signals:\n" +
                        "			uart0_txd : out std_logic;\n" +
                        "			uart0_rxd : in  std_logic;\n" +
                        "		\n" +
                        "			-- KEY signals\n" +
                        "			KEY			: in std_logic_vector(3 DOWNTO 0);\n" +
                        "			\n" +
                        "			-- SW signals\n" +
                        "			SW				: in std_logic_vector(9 DOWNTO 0);\n" +
                        "			\n" +
                        "			-- LEDG signals\n" +
                        "			LEDG			: out std_logic_vector(7 DOWNTO 0);\n" +
                        "			\n" +
                        "			-- LEDR signals\n" +
                        "			LEDR			: out std_logic_vector(9 DOWNTO 0);\n" +
                        "			\n" +
                        "			-- GPIO signals\n" +
                        "			GPIO_IN		: in std_logic_vector(17 DOWNTO 0);\n" +
                        "			GPIO_OUT		: out std_logic_vector(17 DOWNTO 0);\n" +
                        "			\n" +
                        "			--------- HEX0 ---------\n" +
                        "			HEX0			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "\n" +
                        "			--------- HEX1 ---------\n" +
                        "			HEX1			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "			\n" +
                        "			--------- HEX2 ---------\n" +
                        "			HEX2			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "			\n" +
                        "			--------- HEX3 ---------\n" +
                        "			HEX3			: out std_logic_vector(6 DOWNTO 0)\n" +
                        "		);\n" +
                        "	end component;\n" +
                        "	\n" +
                        "	signal KEY : std_logic_vector(3 DOWNTO 0);\n" +
                        "	\n" +
                        "	begin\n" +
                        "		\n" +
                        "		KEY <= not(KEY_n);\n" +
                        "		\n" +
                        "		Potato_SoC : toplevel\n" +
                        "		port map (\n" +
                        "			clk     		=> CLOCK_125_p,\n" +
                        "			reset_n 		=> CPU_RESET_n,\n" +
                        "\n" +
                        "			-- UART0 signals:\n" +
                        "			uart0_txd 	=> UART_TX,\n" +
                        "			uart0_rxd 	=> UART_RX,\n" +
                        "		\n" +
                        "			-- KEY signals\n" +
                        "			KEY			=> KEY,\n" +
                        "			\n" +
                        "			-- SW signals\n" +
                        "			SW				=> SW,\n" +
                        "			\n" +
                        "			-- LEDG signals\n" +
                        "			LEDG			=> LEDG,\n" +
                        "			\n" +
                        "			-- LEDR signals\n" +
                        "			LEDR			=> LEDR,\n" +
                        "			\n" +
                        "			-- GPIO signals\n" +
                        "			GPIO_IN		=> GPIO_IN,\n" +
                        "			GPIO_OUT		=> GPIO_OUT,\n" +
                        "			\n" +
                        "			--------- HEX0 ---------\n" +
                        "			HEX0			=> HEX0,\n" +
                        "\n" +
                        "			--------- HEX1 ---------\n" +
                        "			HEX1			=> HEX1,\n" +
                        "			\n" +
                        "			--------- HEX2 ---------\n" +
                        "			HEX2			=> HEX2,\n" +
                        "			\n" +
                        "			--------- HEX3 ---------\n" +
                        "			HEX3			=> HEX3\n" +
                        "		);\n" +
                        "		\n" +
                        "end architecture;";
        try {
            new File(Project_Folder+"RV_FPGA_PLC_Potato.vhd").delete();
            fileOutSt = new FileOutputStream(Project_Folder+"RV_FPGA_PLC_Potato.vhd");
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_toplevel_vhd_file_sw(String Project_Folder) {
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - SoC design for the Arty FPGA board\n" +
                        "-- (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "-- This is a SoC design for the Arty development board. It has the following memory layout:\n" +
                        "--\n" +
                        "-- 0x00001000: UART0 (for host communication)\n" +
                        "-- 0x00002000: Timer0\n" +
                        "-- 0x00003000: Timer1\n" +
                        "-- 0x00004000: IO Peripheral\n" +
                        "-- 0x00005000: Time Measurement\n" +
                        "-- 0x10000000: Interconnect control/error module\n" +
                        "-- 0xffff8000: Application execution environment ROM (16 kB)\n" +
                        "-- 0xffffc000: Application execution environment RAM (16 kB)\n" +
                        "entity toplevel is\n" +
                        "	port(\n" +
                        "		clk     : in  std_logic;\n" +
                        "		reset_n : in  std_logic;\n" +
                        "\n" +
                        "		-- UART0 signals:\n" +
                        "		uart0_txd : out std_logic;\n" +
                        "		uart0_rxd : in  std_logic;\n" +
                        "		\n" +
                        "		-- KEY signals\n" +
                        "      KEY			: in std_logic_vector(3 DOWNTO 0);\n" +
                        "		\n" +
                        "		-- SW signals\n" +
                        "      SW				: in std_logic_vector(9 DOWNTO 0);\n" +
                        "		\n" +
                        "		-- LEDG signals\n" +
                        "      LEDG			: out std_logic_vector(7 DOWNTO 0);\n" +
                        "		\n" +
                        "		-- LEDR signals\n" +
                        "      LEDR			: out std_logic_vector(9 DOWNTO 0);\n" +
                        "		\n" +
                        "		-- GPIO signals\n" +
                        "      GPIO_IN		: in std_logic_vector(17 DOWNTO 0);\n" +
                        "      GPIO_OUT		: out std_logic_vector(17 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- HEX0 ---------\n" +
                        "		HEX0			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "\n" +
                        "		--------- HEX1 ---------\n" +
                        "		HEX1			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- HEX2 ---------\n" +
                        "		HEX2			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- HEX3 ---------\n" +
                        "		HEX3			: out std_logic_vector(6 DOWNTO 0)\n" +
                        "	);\n" +
                        "end entity toplevel;\n" +
                        "\n" +
                        "architecture behaviour of toplevel is\n" +
                        "\n" +
                        "	component clock_generator is\n" +
                        "		port (\n" +
                        "			refclk   : in  std_logic := '0'; --  refclk.clk\n" +
                        "			rst      : in  std_logic := '0'; --   reset.reset\n" +
                        "			outclk_0 : out std_logic;        -- outclk0.clk\n" +
                        "			outclk_1 : out std_logic;        -- outclk1.clk\n" +
                        "			locked   : out std_logic         --  locked.export\n" +
                        "		);\n" +
                        "	end component;\n" +
                        "	\n" +
                        "	-- Reset signals:\n" +
                        "	signal reset : std_logic;\n" +
                        "\n" +
                        "	-- Internal clock signals:\n" +
                        "	signal system_clk : std_logic;\n" +
                        "	signal timer_clk  : std_logic;\n" +
                        "	signal system_clk_locked : std_logic;\n" +
                        "\n" +
                        "	-- Interrupt indices:\n" +
                        "	constant IRQ_TIMER0_INDEX    : natural := 0;\n" +
                        "	constant IRQ_TIMER1_INDEX    : natural := 1;\n" +
                        "	constant IRQ_UART0_INDEX     : natural := 2;\n" +
                        "	constant IRQ_UART1_INDEX     : natural := 3;\n" +
                        "	constant IRQ_BUS_ERROR_INDEX : natural := 4;\n" +
                        "\n" +
                        "	-- Interrupt signals:\n" +
                        "	signal irq_array : std_logic_vector(7 downto 0);\n" +
                        "	signal timer0_irq, timer1_irq : std_logic;\n" +
                        "	signal uart0_irq, uart1_irq   : std_logic;\n" +
                        "	signal intercon_irq_bus_error : std_logic;\n" +
                        "\n" +
                        "	-- Processor signals:\n" +
                        "	signal processor_adr_out : std_logic_vector(31 downto 0);\n" +
                        "	signal processor_sel_out : std_logic_vector(3 downto 0);\n" +
                        "	signal processor_cyc_out : std_logic;\n" +
                        "	signal processor_stb_out : std_logic;\n" +
                        "	signal processor_we_out  : std_logic;\n" +
                        "	signal processor_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal processor_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal processor_ack_in  : std_logic;\n" +
                        "\n" +
                        "	-- Timer0 signals:\n" +
                        "	signal timer0_adr_in : std_logic_vector(11 downto 0);\n" +
                        "	signal timer0_dat_in : std_logic_vector(31 downto 0);\n" +
                        "	signal timer0_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal timer0_cyc_in : std_logic;\n" +
                        "	signal timer0_stb_in : std_logic;\n" +
                        "	signal timer0_we_in : std_logic;\n" +
                        "	signal timer0_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- Timer1 signals:\n" +
                        "	signal timer1_adr_in : std_logic_vector(11 downto 0);\n" +
                        "	signal timer1_dat_in : std_logic_vector(31 downto 0);\n" +
                        "	signal timer1_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal timer1_cyc_in : std_logic;\n" +
                        "	signal timer1_stb_in : std_logic;\n" +
                        "	signal timer1_we_in : std_logic;\n" +
                        "	signal timer1_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- UART0 signals:\n" +
                        "	signal uart0_adr_in  : std_logic_vector(11 downto 0);\n" +
                        "	signal uart0_dat_in  : std_logic_vector( 7 downto 0);\n" +
                        "	signal uart0_dat_out : std_logic_vector( 7 downto 0);\n" +
                        "	signal uart0_cyc_in  : std_logic;\n" +
                        "	signal uart0_stb_in  : std_logic;\n" +
                        "	signal uart0_we_in   : std_logic;\n" +
                        "	signal uart0_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- Interconnect control module:\n" +
                        "	signal intercon_adr_in  : std_logic_vector(11 downto 0);\n" +
                        "	signal intercon_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal intercon_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal intercon_cyc_in  : std_logic;\n" +
                        "	signal intercon_stb_in  : std_logic;\n" +
                        "	signal intercon_we_in   : std_logic;\n" +
                        "	signal intercon_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- Interconnect error module:\n" +
                        "	signal error_adr_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal error_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal error_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal error_sel_in  : std_logic_vector( 3 downto 0);\n" +
                        "	signal error_cyc_in  : std_logic;\n" +
                        "	signal error_stb_in  : std_logic;\n" +
                        "	signal error_we_in   : std_logic;\n" +
                        "	signal error_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- AEE ROM signals:\n" +
                        "	signal aee_rom_adr_in  : std_logic_vector(13 downto 0);\n" +
                        "	signal aee_rom_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal aee_rom_cyc_in  : std_logic;\n" +
                        "	signal aee_rom_stb_in  : std_logic;\n" +
                        "	signal aee_rom_sel_in  : std_logic_vector(3 downto 0);\n" +
                        "	signal aee_rom_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- AEE RAM signals:\n" +
                        "	signal aee_ram_adr_in  : std_logic_vector(13 downto 0);\n" +
                        "	signal aee_ram_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal aee_ram_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal aee_ram_cyc_in  : std_logic;\n" +
                        "	signal aee_ram_stb_in  : std_logic;\n" +
                        "	signal aee_ram_sel_in  : std_logic_vector(3 downto 0);\n" +
                        "	signal aee_ram_we_in   : std_logic;\n" +
                        "	signal aee_ram_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- IO_peripheral signals:\n" +
                        "	signal io_peripheral_adr_in  : std_logic_vector(13 downto 0);\n" +
                        "	signal io_peripheral_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal io_peripheral_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal io_peripheral_cyc_in  : std_logic;\n" +
                        "	signal io_peripheral_stb_in  : std_logic;\n" +
                        "	signal io_peripheral_sel_in  : std_logic_vector(3 downto 0);\n" +
                        "	signal io_peripheral_we_in   : std_logic;\n" +
                        "	signal io_peripheral_ack_out : std_logic;\n" +
                        "	\n" +
                        "	-- Time_Measurement signals:\n" +
                        "	signal time_measurement_adr_in  : std_logic_vector(13 downto 0);\n" +
                        "	signal time_measurement_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal time_measurement_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal time_measurement_cyc_in  : std_logic;\n" +
                        "	signal time_measurement_stb_in  : std_logic;\n" +
                        "	signal time_measurement_sel_in  : std_logic_vector(3 downto 0);\n" +
                        "	signal time_measurement_we_in   : std_logic;\n" +
                        "	signal time_measurement_ack_out : std_logic;\n" +
                        "	\n" +
                        "	-- Selected peripheral on the interconnect:\n" +
                        "	type intercon_peripheral_type is (\n" +
                        "		PERIPHERAL_TIMER0, PERIPHERAL_TIMER1, PERIPHERAL_IO, \n" +
                        "		PERIPHERAL_UART0, PERIPHERAL_AEE_ROM, PERIPHERAL_AEE_RAM, TIME_MEASUREMENT, \n" +
                        "		PERIPHERAL_INTERCON, PERIPHERAL_ERROR, PERIPHERAL_NONE);\n" +
                        "	signal intercon_peripheral : intercon_peripheral_type := PERIPHERAL_NONE;\n" +
                        "\n" +
                        "	-- Interconnect address decoder state:\n" +
                        "	signal intercon_busy : boolean := false;\n" +
                        "	\n" +
                        "	-- HOSSAM \n" +
                        "	signal not_reset_n : std_logic;\n" +
                        "	\n" +
                        "	-- Output Signal From Register\n" +
                        "	signal GPIO_IN_O	: STD_LOGIC_VECTOR(17 DOWNTO 0);\n" +
                        "	signal SW_O			: STD_LOGIC_VECTOR(9 DOWNTO 0);\n" +
                        "	signal KEY_O		: STD_LOGIC_VECTOR(3 DOWNTO 0);\n" +
                        "begin\n" +
                        "\n" +
                        "	not_reset_n <= not(reset_n);\n" +
                        "	\n" +
                        "	irq_array <= (\n" +
                        "			IRQ_TIMER0_INDEX => timer0_irq,\n" +
                        "			IRQ_TIMER1_INDEX => timer1_irq,\n" +
                        "			IRQ_UART0_INDEX => uart0_irq,\n" +
                        "			IRQ_UART1_INDEX => uart1_irq,\n" +
                        "			IRQ_BUS_ERROR_INDEX => intercon_irq_bus_error,\n" +
                        "			others => '0'\n" +
                        "		);\n" +
                        "\n" +
                        "	address_decoder: process(system_clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(system_clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				intercon_peripheral <= PERIPHERAL_NONE;\n" +
                        "				intercon_busy <= false;\n" +
                        "			else\n" +
                        "				if not intercon_busy then\n" +
                        "					if processor_cyc_out = '1' then\n" +
                        "						intercon_busy <= true;\n" +
                        "\n" +
                        "						if processor_adr_out(31 downto 28) = x\"0\" then -- Peripheral memory space\n" +
                        "							case processor_adr_out(19 downto 12) is\n" +
                        "								when x\"01\" =>\n" +
                        "									intercon_peripheral <= PERIPHERAL_UART0;\n" +
                        "								when x\"02\" =>\n" +
                        "									intercon_peripheral <= PERIPHERAL_TIMER0;\n" +
                        "								when x\"03\" =>\n" +
                        "									intercon_peripheral <= PERIPHERAL_TIMER1;\n" +
                        "								when x\"04\" =>\n" +
                        "									intercon_peripheral <= PERIPHERAL_IO;\n" +
                        "								when x\"05\" =>\n" +
                        "									intercon_peripheral <= TIME_MEASUREMENT;\n" +
                        "								when others => -- Invalid address - delegated to the error peripheral\n" +
                        "									intercon_peripheral <= PERIPHERAL_ERROR;\n" +
                        "							end case;\n" +
                        "						elsif processor_adr_out(31 downto 28) = x\"1\" then\n" +
                        "							intercon_peripheral <= PERIPHERAL_INTERCON;\n" +
                        "						elsif processor_adr_out(31 downto 28) = x\"F\" then -- Firmware memory space\n" +
                        "							if processor_adr_out(15 downto 14) = b\"10\" then    -- AEE ROM\n" +
                        "								intercon_peripheral <= PERIPHERAL_AEE_ROM;\n" +
                        "							elsif processor_adr_out(15 downto 14) = b\"11\" then -- AEE RAM\n" +
                        "								intercon_peripheral <= PERIPHERAL_AEE_RAM;\n" +
                        "							end if;\n" +
                        "						else\n" +
                        "							intercon_peripheral <= PERIPHERAL_ERROR;\n" +
                        "						end if;\n" +
                        "					else\n" +
                        "						intercon_peripheral <= PERIPHERAL_NONE;\n" +
                        "					end if;\n" +
                        "				else\n" +
                        "					if processor_cyc_out = '0' then\n" +
                        "						intercon_busy <= false;\n" +
                        "						intercon_peripheral <= PERIPHERAL_NONE;\n" +
                        "					end if;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process address_decoder;\n" +
                        "\n" +
                        "	processor_intercon: process(intercon_peripheral,\n" +
                        "		timer0_ack_out, timer0_dat_out, timer1_ack_out, timer1_dat_out,\n" +
                        "		uart0_ack_out, uart0_dat_out,\n" +
                        "		intercon_ack_out, intercon_dat_out, error_ack_out,\n" +
                        "		aee_rom_ack_out, aee_rom_dat_out, aee_ram_ack_out, aee_ram_dat_out,\n" +
                        "		time_measurement_ack_out, time_measurement_dat_out,\n" +
                        "		io_peripheral_ack_out, io_peripheral_dat_out)\n" +
                        "	begin\n" +
                        "		case intercon_peripheral is\n" +
                        "			when PERIPHERAL_TIMER0 =>\n" +
                        "				processor_ack_in <= timer0_ack_out;\n" +
                        "				processor_dat_in <= timer0_dat_out;\n" +
                        "			when PERIPHERAL_TIMER1 =>\n" +
                        "				processor_ack_in <= timer1_ack_out;\n" +
                        "				processor_dat_in <= timer1_dat_out;\n" +
                        "			when PERIPHERAL_UART0 =>\n" +
                        "				processor_ack_in <= uart0_ack_out;\n" +
                        "				processor_dat_in <= x\"000000\" & uart0_dat_out;\n" +
                        "			when PERIPHERAL_INTERCON =>\n" +
                        "				processor_ack_in <= intercon_ack_out;\n" +
                        "				processor_dat_in <= intercon_dat_out;\n" +
                        "			when PERIPHERAL_AEE_ROM =>\n" +
                        "				processor_ack_in <= aee_rom_ack_out;\n" +
                        "				processor_dat_in <= aee_rom_dat_out;\n" +
                        "			when PERIPHERAL_AEE_RAM =>\n" +
                        "				processor_ack_in <= aee_ram_ack_out;\n" +
                        "				processor_dat_in <= aee_ram_dat_out;\n" +
                        "			when PERIPHERAL_IO =>\n" +
                        "				processor_ack_in <= io_peripheral_ack_out;\n" +
                        "				processor_dat_in <= io_peripheral_dat_out;\n" +
                        "			when TIME_MEASUREMENT =>\n" +
                        "				processor_ack_in <= time_measurement_ack_out;\n" +
                        "				processor_dat_in <= time_measurement_dat_out;\n" +
                        "			when PERIPHERAL_ERROR =>\n" +
                        "				processor_ack_in <= error_ack_out;\n" +
                        "				processor_dat_in <= (others => '0');\n" +
                        "			when PERIPHERAL_NONE =>\n" +
                        "				processor_ack_in <= '0';\n" +
                        "				processor_dat_in <= (others => '0');\n" +
                        "		end case;\n" +
                        "	end process processor_intercon;\n" +
                        "\n" +
                        "	reset_controller: entity work.pp_soc_reset\n" +
                        "		port map(\n" +
                        "			clk => clk,\n" +
                        "			reset_n => reset_n,\n" +
                        "			reset_out => reset,\n" +
                        "			system_clk => system_clk,\n" +
                        "			system_clk_locked => system_clk_locked\n" +
                        "		);\n" +
                        "	\n" +
                        "	clkgen: clock_generator\n" +
                        "		port map(\n" +
                        "			refclk => clk,\n" +
                        "			rst => not_reset_n,\n" +
                        "			outclk_0 => system_clk,\n" +
                        "			outclk_1 => timer_clk,\n" +
                        "			locked => system_clk_locked\n" +
                        "		);\n" +
                        "\n" +
                        "	processor: entity work.pp_potato\n" +
                        "		generic map(\n" +
                        "			RESET_ADDRESS => x\"ffff8000\",\n" +
                        "			ICACHE_ENABLE => false,\n" +
                        "			ICACHE_LINE_SIZE => 128,\n" +
                        "			ICACHE_NUM_LINES => 128\n" +
                        "		) port map(\n" +
                        "			clk => system_clk,\n" +
                        "			timer_clk => timer_clk,\n" +
                        "			reset => reset,\n" +
                        "			irq => irq_array,\n" +
                        "			test_context_out => open,\n" +
                        "			wb_adr_out => processor_adr_out,\n" +
                        "			wb_dat_out => processor_dat_out,\n" +
                        "			wb_dat_in => processor_dat_in,\n" +
                        "			wb_sel_out => processor_sel_out,\n" +
                        "			wb_cyc_out => processor_cyc_out,\n" +
                        "			wb_stb_out => processor_stb_out,\n" +
                        "			wb_we_out => processor_we_out,\n" +
                        "			wb_ack_in => processor_ack_in\n" +
                        "		);\n" +
                        "\n" +
                        "	timer0: entity work.pp_soc_timer\n" +
                        "		port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			irq => timer0_irq,\n" +
                        "			wb_adr_in => timer0_adr_in,\n" +
                        "			wb_dat_in => timer0_dat_in,\n" +
                        "			wb_dat_out => timer0_dat_out,\n" +
                        "			wb_cyc_in => timer0_cyc_in,\n" +
                        "			wb_stb_in => timer0_stb_in,\n" +
                        "			wb_we_in => timer0_we_in,\n" +
                        "			wb_ack_out => timer0_ack_out\n" +
                        "		);\n" +
                        "	timer0_adr_in <= processor_adr_out(timer0_adr_in'range);\n" +
                        "	timer0_dat_in <= processor_dat_out;\n" +
                        "	timer0_we_in <= processor_we_out;\n" +
                        "	timer0_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_TIMER0 else '0';\n" +
                        "	timer0_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_TIMER0 else '0';\n" +
                        "\n" +
                        "	timer1: entity work.pp_soc_timer\n" +
                        "		port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			irq => timer1_irq,\n" +
                        "			wb_adr_in => timer1_adr_in,\n" +
                        "			wb_dat_in => timer1_dat_in,\n" +
                        "			wb_dat_out => timer1_dat_out,\n" +
                        "			wb_cyc_in => timer1_cyc_in,\n" +
                        "			wb_stb_in => timer1_stb_in,\n" +
                        "			wb_we_in => timer1_we_in,\n" +
                        "			wb_ack_out => timer1_ack_out\n" +
                        "		);\n" +
                        "	timer1_adr_in <= processor_adr_out(timer1_adr_in'range);\n" +
                        "	timer1_dat_in <= processor_dat_out;\n" +
                        "	timer1_we_in  <= processor_we_out;\n" +
                        "	timer1_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_TIMER1 else '0';\n" +
                        "	timer1_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_TIMER1 else '0';\n" +
                        "	\n" +
                        "	uart0: entity work.pp_soc_uart\n" +
                        "		generic map(\n" +
                        "			FIFO_DEPTH => 32\n" +
                        "		) port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			txd => uart0_txd,\n" +
                        "			rxd => uart0_rxd,\n" +
                        "			irq => uart0_irq,\n" +
                        "			wb_adr_in => uart0_adr_in,\n" +
                        "			wb_dat_in => uart0_dat_in,\n" +
                        "			wb_dat_out => uart0_dat_out,\n" +
                        "			wb_cyc_in => uart0_cyc_in,\n" +
                        "			wb_stb_in => uart0_stb_in,\n" +
                        "			wb_we_in => uart0_we_in,\n" +
                        "			wb_ack_out => uart0_ack_out\n" +
                        "		);\n" +
                        "	uart0_adr_in <= processor_adr_out(uart0_adr_in'range);\n" +
                        "	uart0_dat_in <= processor_dat_out(7 downto 0);\n" +
                        "	uart0_we_in  <= processor_we_out;\n" +
                        "	uart0_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_UART0 else '0';\n" +
                        "	uart0_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_UART0 else '0';\n" +
                        "\n" +
                        "	intercon_error: entity work.pp_soc_intercon\n" +
                        "		port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			error_irq => intercon_irq_bus_error,\n" +
                        "			wb_adr_in => intercon_adr_in,\n" +
                        "			wb_dat_in => intercon_dat_in,\n" +
                        "			wb_dat_out => intercon_dat_out,\n" +
                        "			wb_cyc_in => intercon_cyc_in,\n" +
                        "			wb_stb_in => intercon_stb_in,\n" +
                        "			wb_we_in => intercon_we_in,\n" +
                        "			wb_ack_out => intercon_ack_out,\n" +
                        "			err_adr_in => error_adr_in,\n" +
                        "			err_dat_in => error_dat_in,\n" +
                        "			err_sel_in => error_sel_in,\n" +
                        "			err_cyc_in => error_cyc_in,\n" +
                        "			err_stb_in => error_stb_in,\n" +
                        "			err_we_in => error_we_in,\n" +
                        "			err_ack_out => error_ack_out\n" +
                        "		);\n" +
                        "	intercon_adr_in <= processor_adr_out(intercon_adr_in'range);\n" +
                        "	intercon_dat_in <= processor_dat_out;\n" +
                        "	intercon_we_in  <= processor_we_out;\n" +
                        "	intercon_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_INTERCON else '0';\n" +
                        "	intercon_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_INTERCON else '0';\n" +
                        "	error_adr_in <= processor_adr_out;\n" +
                        "	error_dat_in <= processor_dat_out;\n" +
                        "	error_sel_in <= processor_sel_out;\n" +
                        "	error_we_in  <= processor_we_out;\n" +
                        "	error_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_ERROR else '0';\n" +
                        "	error_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_ERROR else '0';\n" +
                        "\n" +
                        "	aee_rom: entity work.aee_rom_wrapper\n" +
                        "		generic map(\n" +
                        "			MEMORY_SIZE => 16384\n" +
                        "		) port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			wb_adr_in => aee_rom_adr_in,\n" +
                        "			wb_dat_out => aee_rom_dat_out,\n" +
                        "			wb_cyc_in => aee_rom_cyc_in,\n" +
                        "			wb_stb_in => aee_rom_stb_in,\n" +
                        "			wb_sel_in => aee_rom_sel_in,\n" +
                        "			wb_ack_out => aee_rom_ack_out\n" +
                        "		);\n" +
                        "	aee_rom_adr_in <= processor_adr_out(aee_rom_adr_in'range);\n" +
                        "	aee_rom_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_AEE_ROM else '0';\n" +
                        "	aee_rom_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_AEE_ROM else '0';\n" +
                        "	aee_rom_sel_in <= processor_sel_out;\n" +
                        "\n" +
                        "	aee_ram: entity work.pp_soc_memory\n" +
                        "		generic map(\n" +
                        "			MEMORY_SIZE => 128 --16384\n" +
                        "		) port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			wb_adr_in => aee_ram_adr_in(6 downto 0),\n" +
                        "			wb_dat_in => aee_ram_dat_in,\n" +
                        "			wb_dat_out => aee_ram_dat_out,\n" +
                        "			wb_cyc_in => aee_ram_cyc_in,\n" +
                        "			wb_stb_in => aee_ram_stb_in,\n" +
                        "			wb_sel_in => aee_ram_sel_in,\n" +
                        "			wb_we_in => aee_ram_we_in,\n" +
                        "			wb_ack_out => aee_ram_ack_out\n" +
                        "		);\n" +
                        "	aee_ram_adr_in <= processor_adr_out(aee_ram_adr_in'range);\n" +
                        "	aee_ram_dat_in <= processor_dat_out;\n" +
                        "	aee_ram_we_in  <= processor_we_out;\n" +
                        "	aee_ram_sel_in <= processor_sel_out;\n" +
                        "	aee_ram_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_AEE_RAM else '0';\n" +
                        "	aee_ram_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_AEE_RAM else '0';\n" +
                        "	\n" +
                        "	io_periphral: entity work.In_Out_Peripheral\n" +
                        "		port map(\n" +
                        "			clk 			=> system_clk,\n" +
                        "			reset 		=> reset,\n" +
                        "			wb_adr_in 	=> io_peripheral_adr_in(8 downto 2),\n" +
                        "			wb_dat_in 	=> io_peripheral_dat_in,\n" +
                        "			wb_dat_out 	=> io_peripheral_dat_out,\n" +
                        "			wb_cyc_in 	=> io_peripheral_cyc_in,\n" +
                        "			wb_stb_in 	=> io_peripheral_stb_in,\n" +
                        "			wb_sel_in 	=> io_peripheral_sel_in,\n" +
                        "			wb_we_in 	=> io_peripheral_we_in,\n" +
                        "			wb_ack_out 	=> io_peripheral_ack_out,\n" +
                        "			KEY			=> KEY,\n" +
                        "			LEDR			=> LEDR,\n" +
                        "			LEDG			=> LEDG,\n" +
                        "			SW				=> SW,\n" +
                        "			GPIO_OUT		=> GPIO_OUT,\n" +
                        "			GPIO_IN		=> GPIO_IN,\n" +
                        "			GPIO_IN_O	=> GPIO_IN_O,\n" +
                        "			SW_O			=> SW_O,\n" +
                        "			KEY_O			=> KEY_O\n" +
                        "		);\n" +
                        "	io_peripheral_adr_in <= processor_adr_out(io_peripheral_adr_in'range);\n" +
                        "	io_peripheral_dat_in <= processor_dat_out;\n" +
                        "	io_peripheral_we_in  <= processor_we_out;\n" +
                        "	io_peripheral_sel_in <= processor_sel_out;\n" +
                        "	io_peripheral_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_IO else '0';\n" +
                        "	io_peripheral_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_IO else '0';\n" +
                        "	\n" +
                        "	time_measurement_p: entity work.Time_Measurement_Peripheral\n" +
                        "		port map(\n" +
                        "			clk 			=> system_clk,\n" +
                        "			reset 		=> reset,\n" +
                        "			wb_adr_in 	=> time_measurement_adr_in(3 downto 2),\n" +
                        "			wb_dat_in 	=> time_measurement_dat_in,\n" +
                        "			wb_dat_out 	=> time_measurement_dat_out,\n" +
                        "			wb_cyc_in 	=> time_measurement_cyc_in,\n" +
                        "			wb_stb_in 	=> time_measurement_stb_in,\n" +
                        "			wb_sel_in 	=> time_measurement_sel_in,\n" +
                        "			wb_we_in 	=> time_measurement_we_in,\n" +
                        "			wb_ack_out 	=> time_measurement_ack_out,\n" +
                        "			HEX0			=> HEX0,\n" +
                        "			HEX1			=> HEX1,\n" +
                        "			HEX2			=> HEX2,\n" +
                        "			HEX3			=> HEX3\n" +
                        "		);\n" +
                        "	time_measurement_adr_in <= processor_adr_out(time_measurement_adr_in'range);\n" +
                        "	time_measurement_dat_in <= processor_dat_out;\n" +
                        "	time_measurement_we_in  <= processor_we_out;\n" +
                        "	time_measurement_sel_in <= processor_sel_out;\n" +
                        "	time_measurement_cyc_in <= processor_cyc_out when intercon_peripheral = TIME_MEASUREMENT else '0';\n" +
                        "	time_measurement_stb_in <= processor_stb_out when intercon_peripheral = TIME_MEASUREMENT else '0';\n" +
                        "	\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder+"toplevel.vhd").delete();
            fileOutSt = new FileOutputStream(Project_Folder+"toplevel.vhd");
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_toplevel_vhd_file_hw(String Project_Folder_File) {
        String data =   "-- The Potato Processor - SoC design for the Arty FPGA board\n" +
                        "-- (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "-- This is a SoC design for the Arty development board. It has the following memory layout:\n" +
                        "--\n" +
                        "-- 0x00001000: UART0 (for host communication)\n" +
                        "-- 0x00002000: Timer0\n" +
                        "-- 0x00003000: Timer1\n" +
                        "-- 0x00004000: IO Peripheral\n" +
                        "-- 0x00005000: Time Measurement\n";
                        for (int i = 0; i < Data.Number_Of_Timers_In_Program; i++) {
                            data += "-- 0x000"+new GeneralFunctions().dec2hex_str(6 + i, 2)+"000: TON "+Data.Name_of_Timers[i]+"\n";
                        }
                        for (int i = 0; i < Data.Number_Of_PWMs_In_Program; i++) {
                            data += "-- 0x000"+new GeneralFunctions().dec2hex_str(Data.Number_Of_Timers_In_Program + 6 + i, 2)+"000: PWM "+Data.Name_of_PWMs[i]+"\n";
                        }
                data += "-- 0x10000000: Interconnect control/error module\n" +
                        "-- 0xffff8000: Application execution environment ROM (16 kB)\n" +
                        "-- 0xffffc000: Application execution environment RAM (16 kB)\n" +
                        "entity toplevel is\n" +
                        "	port(\n" +
                        "		clk     : in  std_logic;\n" +
                        "		reset_n : in  std_logic;\n" +
                        "\n" +
                        "		-- UART0 signals:\n" +
                        "		uart0_txd : out std_logic;\n" +
                        "		uart0_rxd : in  std_logic;\n" +
                        "		\n" +
                        "		-- KEY signals\n" +
                        "      KEY			: in std_logic_vector(3 DOWNTO 0);\n" +
                        "		\n" +
                        "		-- SW signals\n" +
                        "      SW				: in std_logic_vector(9 DOWNTO 0);\n" +
                        "		\n" +
                        "		-- LEDG signals\n" +
                        "      LEDG			: out std_logic_vector(7 DOWNTO 0);\n" +
                        "		\n" +
                        "		-- LEDR signals\n" +
                        "      LEDR			: out std_logic_vector(9 DOWNTO 0);\n" +
                        "		\n" +
                        "		-- GPIO signals\n" +
                        "      GPIO_IN		: in std_logic_vector(17 DOWNTO 0);\n" +
                        "      GPIO_OUT		: out std_logic_vector(17 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- HEX0 ---------\n" +
                        "		HEX0			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "\n" +
                        "		--------- HEX1 ---------\n" +
                        "		HEX1			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- HEX2 ---------\n" +
                        "		HEX2			: out std_logic_vector(6 DOWNTO 0);\n" +
                        "		\n" +
                        "		--------- HEX3 ---------\n" +
                        "		HEX3			: out std_logic_vector(6 DOWNTO 0)\n" +
                        "	);\n" +
                        "end entity toplevel;\n" +
                        "\n" +
                        "architecture behaviour of toplevel is\n" +
                        "\n" +
                        "	component clock_generator is\n" +
                        "		port (\n" +
                        "			refclk   : in  std_logic := '0'; --  refclk.clk\n" +
                        "			rst      : in  std_logic := '0'; --   reset.reset\n" +
                        "			outclk_0 : out std_logic;        -- outclk0.clk\n" +
                        "			outclk_1 : out std_logic;        -- outclk1.clk\n" +
                        "			locked   : out std_logic         --  locked.export\n" +
                        "		);\n" +
                        "	end component;\n" +
                        "	\n" +
                        "	-- Reset signals:\n" +
                        "	signal reset : std_logic;\n" +
                        "\n" +
                        "	-- Internal clock signals:\n" +
                        "	signal system_clk : std_logic;\n" +
                        "	signal timer_clk  : std_logic;\n" +
                        "	signal system_clk_locked : std_logic;\n" +
                        "\n" +
                        "	-- Interrupt indices:\n" +
                        "	constant IRQ_TIMER0_INDEX    : natural := 0;\n" +
                        "	constant IRQ_TIMER1_INDEX    : natural := 1;\n" +
                        "	constant IRQ_UART0_INDEX     : natural := 2;\n" +
                        "	constant IRQ_UART1_INDEX     : natural := 3;\n" +
                        "	constant IRQ_BUS_ERROR_INDEX : natural := 4;\n" +
                        "\n" +
                        "	-- Interrupt signals:\n" +
                        "	signal irq_array : std_logic_vector(7 downto 0);\n" +
                        "	signal timer0_irq, timer1_irq : std_logic;\n" +
                        "	signal uart0_irq, uart1_irq   : std_logic;\n" +
                        "	signal intercon_irq_bus_error : std_logic;\n" +
                        "\n" +
                        "	-- Processor signals:\n" +
                        "	signal processor_adr_out : std_logic_vector(31 downto 0);\n" +
                        "	signal processor_sel_out : std_logic_vector(3 downto 0);\n" +
                        "	signal processor_cyc_out : std_logic;\n" +
                        "	signal processor_stb_out : std_logic;\n" +
                        "	signal processor_we_out  : std_logic;\n" +
                        "	signal processor_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal processor_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal processor_ack_in  : std_logic;\n" +
                        "\n" +
                        "	-- Timer0 signals:\n" +
                        "	signal timer0_adr_in : std_logic_vector(11 downto 0);\n" +
                        "	signal timer0_dat_in : std_logic_vector(31 downto 0);\n" +
                        "	signal timer0_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal timer0_cyc_in : std_logic;\n" +
                        "	signal timer0_stb_in : std_logic;\n" +
                        "	signal timer0_we_in : std_logic;\n" +
                        "	signal timer0_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- Timer1 signals:\n" +
                        "	signal timer1_adr_in : std_logic_vector(11 downto 0);\n" +
                        "	signal timer1_dat_in : std_logic_vector(31 downto 0);\n" +
                        "	signal timer1_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal timer1_cyc_in : std_logic;\n" +
                        "	signal timer1_stb_in : std_logic;\n" +
                        "	signal timer1_we_in : std_logic;\n" +
                        "	signal timer1_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- UART0 signals:\n" +
                        "	signal uart0_adr_in  : std_logic_vector(11 downto 0);\n" +
                        "	signal uart0_dat_in  : std_logic_vector( 7 downto 0);\n" +
                        "	signal uart0_dat_out : std_logic_vector( 7 downto 0);\n" +
                        "	signal uart0_cyc_in  : std_logic;\n" +
                        "	signal uart0_stb_in  : std_logic;\n" +
                        "	signal uart0_we_in   : std_logic;\n" +
                        "	signal uart0_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- Interconnect control module:\n" +
                        "	signal intercon_adr_in  : std_logic_vector(11 downto 0);\n" +
                        "	signal intercon_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal intercon_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal intercon_cyc_in  : std_logic;\n" +
                        "	signal intercon_stb_in  : std_logic;\n" +
                        "	signal intercon_we_in   : std_logic;\n" +
                        "	signal intercon_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- Interconnect error module:\n" +
                        "	signal error_adr_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal error_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal error_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal error_sel_in  : std_logic_vector( 3 downto 0);\n" +
                        "	signal error_cyc_in  : std_logic;\n" +
                        "	signal error_stb_in  : std_logic;\n" +
                        "	signal error_we_in   : std_logic;\n" +
                        "	signal error_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- AEE ROM signals:\n" +
                        "	signal aee_rom_adr_in  : std_logic_vector(13 downto 0);\n" +
                        "	signal aee_rom_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal aee_rom_cyc_in  : std_logic;\n" +
                        "	signal aee_rom_stb_in  : std_logic;\n" +
                        "	signal aee_rom_sel_in  : std_logic_vector(3 downto 0);\n" +
                        "	signal aee_rom_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- AEE RAM signals:\n" +
                        "	signal aee_ram_adr_in  : std_logic_vector(13 downto 0);\n" +
                        "	signal aee_ram_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal aee_ram_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal aee_ram_cyc_in  : std_logic;\n" +
                        "	signal aee_ram_stb_in  : std_logic;\n" +
                        "	signal aee_ram_sel_in  : std_logic_vector(3 downto 0);\n" +
                        "	signal aee_ram_we_in   : std_logic;\n" +
                        "	signal aee_ram_ack_out : std_logic;\n" +
                        "\n" +
                        "	-- IO_peripheral signals:\n" +
                        "	signal io_peripheral_adr_in  : std_logic_vector(13 downto 0);\n" +
                        "	signal io_peripheral_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal io_peripheral_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal io_peripheral_cyc_in  : std_logic;\n" +
                        "	signal io_peripheral_stb_in  : std_logic;\n" +
                        "	signal io_peripheral_sel_in  : std_logic_vector(3 downto 0);\n" +
                        "	signal io_peripheral_we_in   : std_logic;\n" +
                        "	signal io_peripheral_ack_out : std_logic;\n" +
                        "	\n" +
                        "	-- Time_Measurement signals:\n" +
                        "	signal time_measurement_adr_in  : std_logic_vector(13 downto 0);\n" +
                        "	signal time_measurement_dat_in  : std_logic_vector(31 downto 0);\n" +
                        "	signal time_measurement_dat_out : std_logic_vector(31 downto 0);\n" +
                        "	signal time_measurement_cyc_in  : std_logic;\n" +
                        "	signal time_measurement_stb_in  : std_logic;\n" +
                        "	signal time_measurement_sel_in  : std_logic_vector(3 downto 0);\n" +
                        "	signal time_measurement_we_in   : std_logic;\n" +
                        "	signal time_measurement_ack_out : std_logic;\n" +
                        "	\n";
                        for (int i = 0; i < Data.Number_Of_Timers_In_Program; i++) {
                            data += "	-- TON "+Data.Name_of_Timers[i]+" signals:\n" +
                                    "	signal "+Data.Name_of_Timers[i]+"_adr_in  : std_logic_vector(13 downto 0);\n" +
                                    "	signal "+Data.Name_of_Timers[i]+"_dat_in  : std_logic_vector(31 downto 0);\n" +
                                    "	signal "+Data.Name_of_Timers[i]+"_dat_out : std_logic_vector(31 downto 0);\n" +
                                    "	signal "+Data.Name_of_Timers[i]+"_cyc_in  : std_logic;\n" +
                                    "	signal "+Data.Name_of_Timers[i]+"_stb_in  : std_logic;\n" +
                                    "	signal "+Data.Name_of_Timers[i]+"_sel_in  : std_logic_vector(3 downto 0);\n" +
                                    "	signal "+Data.Name_of_Timers[i]+"_we_in   : std_logic;\n" +
                                    "	signal "+Data.Name_of_Timers[i]+"_ack_out : std_logic;\n" +
                                    "	\n";
                        }
                        for (int i = 0; i < Data.Number_Of_PWMs_In_Program; i++) {
                            data += "	-- PWM "+Data.Name_of_PWMs[i]+" signals:\n" +
                                    "	signal "+Data.Name_of_PWMs[i]+"_adr_in  : std_logic_vector(13 downto 0);\n" +
                                    "	signal "+Data.Name_of_PWMs[i]+"_dat_in  : std_logic_vector(31 downto 0);\n" +
                                    "	signal "+Data.Name_of_PWMs[i]+"_dat_out : std_logic_vector(31 downto 0);\n" +
                                    "	signal "+Data.Name_of_PWMs[i]+"_cyc_in  : std_logic;\n" +
                                    "	signal "+Data.Name_of_PWMs[i]+"_stb_in  : std_logic;\n" +
                                    "	signal "+Data.Name_of_PWMs[i]+"_sel_in  : std_logic_vector(3 downto 0);\n" +
                                    "	signal "+Data.Name_of_PWMs[i]+"_we_in   : std_logic;\n" +
                                    "	signal "+Data.Name_of_PWMs[i]+"_ack_out : std_logic;\n" +
                                    "	\n";
                        }
                data += "	-- Selected peripheral on the interconnect:\n" +
                        "	type intercon_peripheral_type is (\n" +
                        "		PERIPHERAL_TIMER0, PERIPHERAL_TIMER1, PERIPHERAL_IO, \n" +
                        "		PERIPHERAL_UART0, PERIPHERAL_AEE_ROM, PERIPHERAL_AEE_RAM, TIME_MEASUREMENT, \n" +
                        "		PERIPHERAL_INTERCON, PERIPHERAL_ERROR, PERIPHERAL_NONE";
                        for (int i = 0; i < Data.Number_Of_Timers_In_Program; i++) {
                            if (i == 0) data += "\n		";
                            data += ", PERIPHERAL_"+Data.Name_of_Timers[i];
                        }
                        for (int i = 0; i < Data.Number_Of_PWMs_In_Program; i++) {
                            if (i == 0) data += "\n		";
                            data += ", PERIPHERAL_"+Data.Name_of_PWMs[i];
                        }
                data += ");\n" +
                        "	signal intercon_peripheral : intercon_peripheral_type := PERIPHERAL_NONE;\n" +
                        "\n" +
                        "	-- Interconnect address decoder state:\n" +
                        "	signal intercon_busy : boolean := false;\n" +
                        "	\n" +
                        "	-- HOSSAM \n" +
                        "	signal not_reset_n : std_logic;\n" +
                        "	\n" +
                        "	-- Output Signal From Register\n" +
                        "	signal GPIO_IN_O	: STD_LOGIC_VECTOR(17 DOWNTO 0);\n" +
                        "	signal SW_O			: STD_LOGIC_VECTOR(9 DOWNTO 0);\n" +
                        "	signal KEY_O		: STD_LOGIC_VECTOR(3 DOWNTO 0);\n" +
                        "begin\n" +
                        "\n" +
                        "	not_reset_n <= not(reset_n);\n" +
                        "	\n" +
                        "	irq_array <= (\n" +
                        "			IRQ_TIMER0_INDEX => timer0_irq,\n" +
                        "			IRQ_TIMER1_INDEX => timer1_irq,\n" +
                        "			IRQ_UART0_INDEX => uart0_irq,\n" +
                        "			IRQ_UART1_INDEX => uart1_irq,\n" +
                        "			IRQ_BUS_ERROR_INDEX => intercon_irq_bus_error,\n" +
                        "			others => '0'\n" +
                        "		);\n" +
                        "\n" +
                        "	address_decoder: process(system_clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(system_clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				intercon_peripheral <= PERIPHERAL_NONE;\n" +
                        "				intercon_busy <= false;\n" +
                        "			else\n" +
                        "				if not intercon_busy then\n" +
                        "					if processor_cyc_out = '1' then\n" +
                        "						intercon_busy <= true;\n" +
                        "\n" +
                        "						if processor_adr_out(31 downto 28) = x\"0\" then -- Peripheral memory space\n" +
                        "							case processor_adr_out(19 downto 12) is\n" +
                        "								when x\"01\" =>\n" +
                        "									intercon_peripheral <= PERIPHERAL_UART0;\n" +
                        "								when x\"02\" =>\n" +
                        "									intercon_peripheral <= PERIPHERAL_TIMER0;\n" +
                        "								when x\"03\" =>\n" +
                        "									intercon_peripheral <= PERIPHERAL_TIMER1;\n" +
                        "								when x\"04\" =>\n" +
                        "									intercon_peripheral <= PERIPHERAL_IO;\n" +
                        "								when x\"05\" =>\n" +
                        "									intercon_peripheral <= TIME_MEASUREMENT;\n";
                        for (int i = 0; i < Data.Number_Of_Timers_In_Program; i++) {
                            data += "								when x\""+new GeneralFunctions().dec2hex_str(6 + i, 2)+"\" =>\n" +
                                    "									intercon_peripheral <= PERIPHERAL_"+Data.Name_of_Timers[i]+";\n";
                        }
                        for (int i = 0; i < Data.Number_Of_PWMs_In_Program; i++) {
                            data += "								when x\""+new GeneralFunctions().dec2hex_str(Data.Number_Of_Timers_In_Program + 6 + i, 2)+"\" =>\n" +
                                    "									intercon_peripheral <= PERIPHERAL_"+Data.Name_of_PWMs[i]+";\n";
                        }
                data += "								when others => -- Invalid address - delegated to the error peripheral\n" +
                        "									intercon_peripheral <= PERIPHERAL_ERROR;\n" +
                        "							end case;\n" +
                        "						elsif processor_adr_out(31 downto 28) = x\"1\" then\n" +
                        "							intercon_peripheral <= PERIPHERAL_INTERCON;\n" +
                        "						elsif processor_adr_out(31 downto 28) = x\"F\" then -- Firmware memory space\n" +
                        "							if processor_adr_out(15 downto 14) = b\"10\" then    -- AEE ROM\n" +
                        "								intercon_peripheral <= PERIPHERAL_AEE_ROM;\n" +
                        "							elsif processor_adr_out(15 downto 14) = b\"11\" then -- AEE RAM\n" +
                        "								intercon_peripheral <= PERIPHERAL_AEE_RAM;\n" +
                        "							end if;\n" +
                        "						else\n" +
                        "							intercon_peripheral <= PERIPHERAL_ERROR;\n" +
                        "						end if;\n" +
                        "					else\n" +
                        "						intercon_peripheral <= PERIPHERAL_NONE;\n" +
                        "					end if;\n" +
                        "				else\n" +
                        "					if processor_cyc_out = '0' then\n" +
                        "						intercon_busy <= false;\n" +
                        "						intercon_peripheral <= PERIPHERAL_NONE;\n" +
                        "					end if;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process address_decoder;\n" +
                        "\n" +
                        "	processor_intercon: process(intercon_peripheral,\n" +
                        "		timer0_ack_out, timer0_dat_out, timer1_ack_out, timer1_dat_out,\n" +
                        "		uart0_ack_out, uart0_dat_out,\n" +
                        "		intercon_ack_out, intercon_dat_out, error_ack_out,\n" +
                        "		aee_rom_ack_out, aee_rom_dat_out, aee_ram_ack_out, aee_ram_dat_out,\n" +
                        "		time_measurement_ack_out, time_measurement_dat_out,\n" +
                        "		io_peripheral_ack_out, io_peripheral_dat_out";
                        for (int i = 0; i < Data.Number_Of_Timers_In_Program; i++) {
                            if (i == 0) data += ",\n";
                            if (i != (Data.Number_Of_Timers_In_Program - 1)) data += "		"+Data.Name_of_Timers[i]+"_ack_out, "+Data.Name_of_Timers[i]+"_ack_out,\n";
                            else data += "		"+Data.Name_of_Timers[i]+"_ack_out, "+Data.Name_of_Timers[i]+"_dat_out";
                        }
                        for (int i = 0; i < Data.Number_Of_PWMs_In_Program; i++) {
                            if (i == 0) data += ",\n";
                            if (i != (Data.Number_Of_PWMs_In_Program - 1)) data += "		"+Data.Name_of_PWMs[i]+"_ack_out, "+Data.Name_of_PWMs[i]+"_ack_out,\n";
                            else data += "		"+Data.Name_of_PWMs[i]+"_ack_out, "+Data.Name_of_PWMs[i]+"_dat_out";
                        }
                data += ")\n" +
                        "	begin\n" +
                        "		case intercon_peripheral is\n" +
                        "			when PERIPHERAL_TIMER0 =>\n" +
                        "				processor_ack_in <= timer0_ack_out;\n" +
                        "				processor_dat_in <= timer0_dat_out;\n" +
                        "			when PERIPHERAL_TIMER1 =>\n" +
                        "				processor_ack_in <= timer1_ack_out;\n" +
                        "				processor_dat_in <= timer1_dat_out;\n" +
                        "			when PERIPHERAL_UART0 =>\n" +
                        "				processor_ack_in <= uart0_ack_out;\n" +
                        "				processor_dat_in <= x\"000000\" & uart0_dat_out;\n" +
                        "			when PERIPHERAL_INTERCON =>\n" +
                        "				processor_ack_in <= intercon_ack_out;\n" +
                        "				processor_dat_in <= intercon_dat_out;\n" +
                        "			when PERIPHERAL_AEE_ROM =>\n" +
                        "				processor_ack_in <= aee_rom_ack_out;\n" +
                        "				processor_dat_in <= aee_rom_dat_out;\n" +
                        "			when PERIPHERAL_AEE_RAM =>\n" +
                        "				processor_ack_in <= aee_ram_ack_out;\n" +
                        "				processor_dat_in <= aee_ram_dat_out;\n" +
                        "			when PERIPHERAL_IO =>\n" +
                        "				processor_ack_in <= io_peripheral_ack_out;\n" +
                        "				processor_dat_in <= io_peripheral_dat_out;\n" +
                        "			when TIME_MEASUREMENT =>\n" +
                        "				processor_ack_in <= time_measurement_ack_out;\n" +
                        "				processor_dat_in <= time_measurement_dat_out;\n";
                        for (int i = 0; i < Data.Number_Of_Timers_In_Program; i++) {
                            data += "			when PERIPHERAL_"+Data.Name_of_Timers[i]+" =>\n" +
                                    "				processor_ack_in <= "+Data.Name_of_Timers[i]+"_ack_out;\n" +
                                    "				processor_dat_in <= "+Data.Name_of_Timers[i]+"_dat_out;\n";
                        }
                        for (int i = 0; i < Data.Number_Of_PWMs_In_Program; i++) {
                            data += "			when PERIPHERAL_"+Data.Name_of_PWMs[i]+" =>\n" +
                                    "				processor_ack_in <= "+Data.Name_of_PWMs[i]+"_ack_out;\n" +
                                    "				processor_dat_in <= "+Data.Name_of_PWMs[i]+"_dat_out;\n";
                        }
                data += "			when PERIPHERAL_ERROR =>\n" +
                        "				processor_ack_in <= error_ack_out;\n" +
                        "				processor_dat_in <= (others => '0');\n" +
                        "			when PERIPHERAL_NONE =>\n" +
                        "				processor_ack_in <= '0';\n" +
                        "				processor_dat_in <= (others => '0');\n" +
                        "		end case;\n" +
                        "	end process processor_intercon;\n" +
                        "\n" +
                        "	reset_controller: entity work.pp_soc_reset\n" +
                        "		port map(\n" +
                        "			clk => clk,\n" +
                        "			reset_n => reset_n,\n" +
                        "			reset_out => reset,\n" +
                        "			system_clk => system_clk,\n" +
                        "			system_clk_locked => system_clk_locked\n" +
                        "		);\n" +
                        "	\n" +
                        "	clkgen: clock_generator\n" +
                        "		port map(\n" +
                        "			refclk => clk,\n" +
                        "			rst => not_reset_n,\n" +
                        "			outclk_0 => system_clk,\n" +
                        "			outclk_1 => timer_clk,\n" +
                        "			locked => system_clk_locked\n" +
                        "		);\n" +
                        "\n" +
                        "	processor: entity work.pp_potato\n" +
                        "		generic map(\n" +
                        "			RESET_ADDRESS => x\"ffff8000\",\n" +
                        "			ICACHE_ENABLE => false,\n" +
                        "			ICACHE_LINE_SIZE => 128,\n" +
                        "			ICACHE_NUM_LINES => 128\n" +
                        "		) port map(\n" +
                        "			clk => system_clk,\n" +
                        "			timer_clk => timer_clk,\n" +
                        "			reset => reset,\n" +
                        "			irq => irq_array,\n" +
                        "			test_context_out => open,\n" +
                        "			wb_adr_out => processor_adr_out,\n" +
                        "			wb_dat_out => processor_dat_out,\n" +
                        "			wb_dat_in => processor_dat_in,\n" +
                        "			wb_sel_out => processor_sel_out,\n" +
                        "			wb_cyc_out => processor_cyc_out,\n" +
                        "			wb_stb_out => processor_stb_out,\n" +
                        "			wb_we_out => processor_we_out,\n" +
                        "			wb_ack_in => processor_ack_in\n" +
                        "		);\n" +
                        "\n" +
                        "	timer0: entity work.pp_soc_timer\n" +
                        "		port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			irq => timer0_irq,\n" +
                        "			wb_adr_in => timer0_adr_in,\n" +
                        "			wb_dat_in => timer0_dat_in,\n" +
                        "			wb_dat_out => timer0_dat_out,\n" +
                        "			wb_cyc_in => timer0_cyc_in,\n" +
                        "			wb_stb_in => timer0_stb_in,\n" +
                        "			wb_we_in => timer0_we_in,\n" +
                        "			wb_ack_out => timer0_ack_out\n" +
                        "		);\n" +
                        "	timer0_adr_in <= processor_adr_out(timer0_adr_in'range);\n" +
                        "	timer0_dat_in <= processor_dat_out;\n" +
                        "	timer0_we_in <= processor_we_out;\n" +
                        "	timer0_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_TIMER0 else '0';\n" +
                        "	timer0_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_TIMER0 else '0';\n" +
                        "\n" +
                        "	timer1: entity work.pp_soc_timer\n" +
                        "		port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			irq => timer1_irq,\n" +
                        "			wb_adr_in => timer1_adr_in,\n" +
                        "			wb_dat_in => timer1_dat_in,\n" +
                        "			wb_dat_out => timer1_dat_out,\n" +
                        "			wb_cyc_in => timer1_cyc_in,\n" +
                        "			wb_stb_in => timer1_stb_in,\n" +
                        "			wb_we_in => timer1_we_in,\n" +
                        "			wb_ack_out => timer1_ack_out\n" +
                        "		);\n" +
                        "	timer1_adr_in <= processor_adr_out(timer1_adr_in'range);\n" +
                        "	timer1_dat_in <= processor_dat_out;\n" +
                        "	timer1_we_in  <= processor_we_out;\n" +
                        "	timer1_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_TIMER1 else '0';\n" +
                        "	timer1_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_TIMER1 else '0';\n" +
                        "	\n" +
                        "	uart0: entity work.pp_soc_uart\n" +
                        "		generic map(\n" +
                        "			FIFO_DEPTH => 32\n" +
                        "		) port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			txd => uart0_txd,\n" +
                        "			rxd => uart0_rxd,\n" +
                        "			irq => uart0_irq,\n" +
                        "			wb_adr_in => uart0_adr_in,\n" +
                        "			wb_dat_in => uart0_dat_in,\n" +
                        "			wb_dat_out => uart0_dat_out,\n" +
                        "			wb_cyc_in => uart0_cyc_in,\n" +
                        "			wb_stb_in => uart0_stb_in,\n" +
                        "			wb_we_in => uart0_we_in,\n" +
                        "			wb_ack_out => uart0_ack_out\n" +
                        "		);\n" +
                        "	uart0_adr_in <= processor_adr_out(uart0_adr_in'range);\n" +
                        "	uart0_dat_in <= processor_dat_out(7 downto 0);\n" +
                        "	uart0_we_in  <= processor_we_out;\n" +
                        "	uart0_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_UART0 else '0';\n" +
                        "	uart0_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_UART0 else '0';\n" +
                        "\n" +
                        "	intercon_error: entity work.pp_soc_intercon\n" +
                        "		port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			error_irq => intercon_irq_bus_error,\n" +
                        "			wb_adr_in => intercon_adr_in,\n" +
                        "			wb_dat_in => intercon_dat_in,\n" +
                        "			wb_dat_out => intercon_dat_out,\n" +
                        "			wb_cyc_in => intercon_cyc_in,\n" +
                        "			wb_stb_in => intercon_stb_in,\n" +
                        "			wb_we_in => intercon_we_in,\n" +
                        "			wb_ack_out => intercon_ack_out,\n" +
                        "			err_adr_in => error_adr_in,\n" +
                        "			err_dat_in => error_dat_in,\n" +
                        "			err_sel_in => error_sel_in,\n" +
                        "			err_cyc_in => error_cyc_in,\n" +
                        "			err_stb_in => error_stb_in,\n" +
                        "			err_we_in => error_we_in,\n" +
                        "			err_ack_out => error_ack_out\n" +
                        "		);\n" +
                        "	intercon_adr_in <= processor_adr_out(intercon_adr_in'range);\n" +
                        "	intercon_dat_in <= processor_dat_out;\n" +
                        "	intercon_we_in  <= processor_we_out;\n" +
                        "	intercon_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_INTERCON else '0';\n" +
                        "	intercon_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_INTERCON else '0';\n" +
                        "	error_adr_in <= processor_adr_out;\n" +
                        "	error_dat_in <= processor_dat_out;\n" +
                        "	error_sel_in <= processor_sel_out;\n" +
                        "	error_we_in  <= processor_we_out;\n" +
                        "	error_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_ERROR else '0';\n" +
                        "	error_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_ERROR else '0';\n" +
                        "\n" +
                        "	aee_rom: entity work.aee_rom_wrapper\n" +
                        "		generic map(\n" +
                        "			MEMORY_SIZE => 16384\n" +
                        "		) port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			wb_adr_in => aee_rom_adr_in,\n" +
                        "			wb_dat_out => aee_rom_dat_out,\n" +
                        "			wb_cyc_in => aee_rom_cyc_in,\n" +
                        "			wb_stb_in => aee_rom_stb_in,\n" +
                        "			wb_sel_in => aee_rom_sel_in,\n" +
                        "			wb_ack_out => aee_rom_ack_out\n" +
                        "		);\n" +
                        "	aee_rom_adr_in <= processor_adr_out(aee_rom_adr_in'range);\n" +
                        "	aee_rom_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_AEE_ROM else '0';\n" +
                        "	aee_rom_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_AEE_ROM else '0';\n" +
                        "	aee_rom_sel_in <= processor_sel_out;\n" +
                        "\n" +
                        "	aee_ram: entity work.pp_soc_memory\n" +
                        "		generic map(\n" +
                        "			MEMORY_SIZE => 128 --16384\n" +
                        "		) port map(\n" +
                        "			clk => system_clk,\n" +
                        "			reset => reset,\n" +
                        "			wb_adr_in => aee_ram_adr_in(6 downto 0),\n" +
                        "			wb_dat_in => aee_ram_dat_in,\n" +
                        "			wb_dat_out => aee_ram_dat_out,\n" +
                        "			wb_cyc_in => aee_ram_cyc_in,\n" +
                        "			wb_stb_in => aee_ram_stb_in,\n" +
                        "			wb_sel_in => aee_ram_sel_in,\n" +
                        "			wb_we_in => aee_ram_we_in,\n" +
                        "			wb_ack_out => aee_ram_ack_out\n" +
                        "		);\n" +
                        "	aee_ram_adr_in <= processor_adr_out(aee_ram_adr_in'range);\n" +
                        "	aee_ram_dat_in <= processor_dat_out;\n" +
                        "	aee_ram_we_in  <= processor_we_out;\n" +
                        "	aee_ram_sel_in <= processor_sel_out;\n" +
                        "	aee_ram_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_AEE_RAM else '0';\n" +
                        "	aee_ram_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_AEE_RAM else '0';\n" +
                        "	\n" +
                        "	io_periphral: entity work.In_Out_Peripheral\n" +
                        "		port map(\n" +
                        "			clk 			=> system_clk,\n" +
                        "			reset 		=> reset,\n" +
                        "			wb_adr_in 	=> io_peripheral_adr_in(8 downto 2),\n" +
                        "			wb_dat_in 	=> io_peripheral_dat_in,\n" +
                        "			wb_dat_out 	=> io_peripheral_dat_out,\n" +
                        "			wb_cyc_in 	=> io_peripheral_cyc_in,\n" +
                        "			wb_stb_in 	=> io_peripheral_stb_in,\n" +
                        "			wb_sel_in 	=> io_peripheral_sel_in,\n" +
                        "			wb_we_in 	=> io_peripheral_we_in,\n" +
                        "			wb_ack_out 	=> io_peripheral_ack_out,\n" +
                        "			KEY			=> KEY,\n" +
                        "			LEDR			=> LEDR,\n" +
                        "			LEDG			=> LEDG,\n" +
                        "			SW				=> SW,\n" +
                        "			GPIO_OUT		=> GPIO_OUT,\n" +
                        "			GPIO_IN		=> GPIO_IN,\n" +
                        "			GPIO_IN_O	=> GPIO_IN_O,\n" +
                        "			SW_O			=> SW_O,\n" +
                        "			KEY_O			=> KEY_O\n" +
                        "		);\n" +
                        "	io_peripheral_adr_in <= processor_adr_out(io_peripheral_adr_in'range);\n" +
                        "	io_peripheral_dat_in <= processor_dat_out;\n" +
                        "	io_peripheral_we_in  <= processor_we_out;\n" +
                        "	io_peripheral_sel_in <= processor_sel_out;\n" +
                        "	io_peripheral_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_IO else '0';\n" +
                        "	io_peripheral_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_IO else '0';\n" +
                        "	\n" +
                        "	time_measurement_p: entity work.Time_Measurement_Peripheral\n" +
                        "		port map(\n" +
                        "			clk 			=> system_clk,\n" +
                        "			reset 		=> reset,\n" +
                        "			wb_adr_in 	=> time_measurement_adr_in(3 downto 2),\n" +
                        "			wb_dat_in 	=> time_measurement_dat_in,\n" +
                        "			wb_dat_out 	=> time_measurement_dat_out,\n" +
                        "			wb_cyc_in 	=> time_measurement_cyc_in,\n" +
                        "			wb_stb_in 	=> time_measurement_stb_in,\n" +
                        "			wb_sel_in 	=> time_measurement_sel_in,\n" +
                        "			wb_we_in 	=> time_measurement_we_in,\n" +
                        "			wb_ack_out 	=> time_measurement_ack_out,\n" +
                        "			HEX0			=> HEX0,\n" +
                        "			HEX1			=> HEX1,\n" +
                        "			HEX2			=> HEX2,\n" +
                        "			HEX3			=> HEX3\n" +
                        "		);\n" +
                        "	time_measurement_adr_in <= processor_adr_out(time_measurement_adr_in'range);\n" +
                        "	time_measurement_dat_in <= processor_dat_out;\n" +
                        "	time_measurement_we_in  <= processor_we_out;\n" +
                        "	time_measurement_sel_in <= processor_sel_out;\n" +
                        "	time_measurement_cyc_in <= processor_cyc_out when intercon_peripheral = TIME_MEASUREMENT else '0';\n" +
                        "	time_measurement_stb_in <= processor_stb_out when intercon_peripheral = TIME_MEASUREMENT else '0';\n" +
                        "	\n";
                        for (int i = 0; i < Data.Number_Of_Timers_In_Program; i++) {
                            data += "	"+Data.Name_of_Timers[i]+": entity work.TON_Peripheral\n" +
                                    "		port map(\n" +
                                    "			clk 		=> system_clk,\n" +
                                    "			reset		=> reset,\n" +
                                    "			wb_adr_in	=> "+Data.Name_of_Timers[i]+"_adr_in(3 downto 2),\n" +
                                    "			wb_dat_in	=> "+Data.Name_of_Timers[i]+"_dat_in,\n" +
                                    "			wb_dat_out	=> "+Data.Name_of_Timers[i]+"_dat_out,\n" +
                                    "			wb_cyc_in	=> "+Data.Name_of_Timers[i]+"_cyc_in,\n" +
                                    "			wb_stb_in	=> "+Data.Name_of_Timers[i]+"_stb_in,\n" +
                                    "			wb_sel_in	=> "+Data.Name_of_Timers[i]+"_sel_in,\n" +
                                    "			wb_we_in	=> "+Data.Name_of_Timers[i]+"_we_in,\n" +
                                    "			wb_ack_out	=> "+Data.Name_of_Timers[i]+"_ack_out\n" +
                                    "		);\n" +
                                    "	"+Data.Name_of_Timers[i]+"_adr_in <= processor_adr_out("+Data.Name_of_Timers[i]+"_adr_in'range);\n" +
                                    "	"+Data.Name_of_Timers[i]+"_dat_in <= processor_dat_out;\n" +
                                    "	"+Data.Name_of_Timers[i]+"_we_in  <= processor_we_out;\n" +
                                    "	"+Data.Name_of_Timers[i]+"_sel_in <= processor_sel_out;\n" +
                                    "	"+Data.Name_of_Timers[i]+"_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_"+Data.Name_of_Timers[i]+" else '0';\n" +
                                    "	"+Data.Name_of_Timers[i]+"_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_"+Data.Name_of_Timers[i]+" else '0';\n\n";
                        }
                        for (int i = 0; i < Data.Number_Of_PWMs_In_Program; i++) {
                            data += "	"+Data.Name_of_PWMs[i]+": entity work.PWM_Peripheral\n" +
                                    "		port map(\n" +
                                    "			clk 		=> system_clk,\n" +
                                    "			reset		=> reset,\n" +
                                    "			wb_adr_in	=> "+Data.Name_of_PWMs[i]+"_adr_in(3 downto 2),\n" +
                                    "			wb_dat_in	=> "+Data.Name_of_PWMs[i]+"_dat_in,\n" +
                                    "			wb_dat_out	=> "+Data.Name_of_PWMs[i]+"_dat_out,\n" +
                                    "			wb_cyc_in	=> "+Data.Name_of_PWMs[i]+"_cyc_in,\n" +
                                    "			wb_stb_in	=> "+Data.Name_of_PWMs[i]+"_stb_in,\n" +
                                    "			wb_sel_in	=> "+Data.Name_of_PWMs[i]+"_sel_in,\n" +
                                    "			wb_we_in	=> "+Data.Name_of_PWMs[i]+"_we_in,\n" +
                                    "			wb_ack_out	=> "+Data.Name_of_PWMs[i]+"_ack_out\n" +
                                    "		);\n" +
                                    "	"+Data.Name_of_PWMs[i]+"_adr_in <= processor_adr_out("+Data.Name_of_PWMs[i]+"_adr_in'range);\n" +
                                    "	"+Data.Name_of_PWMs[i]+"_dat_in <= processor_dat_out;\n" +
                                    "	"+Data.Name_of_PWMs[i]+"_we_in  <= processor_we_out;\n" +
                                    "	"+Data.Name_of_PWMs[i]+"_sel_in <= processor_sel_out;\n" +
                                    "	"+Data.Name_of_PWMs[i]+"_cyc_in <= processor_cyc_out when intercon_peripheral = PERIPHERAL_"+Data.Name_of_PWMs[i]+" else '0';\n" +
                                    "	"+Data.Name_of_PWMs[i]+"_stb_in <= processor_stb_out when intercon_peripheral = PERIPHERAL_"+Data.Name_of_PWMs[i]+" else '0';\n\n";
                        }
                data += "end architecture behaviour;";
        new GeneralFunctions().write_file(Project_Folder_File + "toplevel.vhd", data);
    }
    
    private void generate_pp_soc_reset_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/processor/vhdl/pp_soc_reset.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2018 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "--! @brief System reset unit.\n" +
                        "--! Because most resets in the processor core are synchronous, at least one\n" +
                        "--! clock pulse has to be given to the processor while the reset signal is\n" +
                        "--! asserted. However, if the clock generator is being reset at the same time,\n" +
                        "--! the system clock might not run during reset, preventing the processor from\n" +
                        "--! properly resetting.\n" +
                        "entity pp_soc_reset is\n" +
                        "	generic(\n" +
                        "		RESET_CYCLE_COUNT : natural := 1\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		clk : in std_logic;\n" +
                        "\n" +
                        "		reset_n   : in  std_logic;\n" +
                        "		reset_out : out std_logic;\n" +
                        "\n" +
                        "		system_clk        : in std_logic;\n" +
                        "		system_clk_locked : in std_logic\n" +
                        "	);\n" +
                        "end entity pp_soc_reset;\n" +
                        "\n" +
                        "architecture behaviour of pp_soc_reset is\n" +
                        "\n" +
                        "	subtype counter_type is natural range 0 to RESET_CYCLE_COUNT;\n" +
                        "	signal counter : counter_type;\n" +
                        "\n" +
                        "	signal fast_reset : std_logic := '0';\n" +
                        "	signal slow_reset : std_logic := '1';\n" +
                        "begin\n" +
                        "\n" +
                        "	reset_out <= fast_reset or slow_reset;\n" +
                        "\n" +
                        "	process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset_n = '0' then\n" +
                        "				fast_reset <= '1';\n" +
                        "			elsif system_clk_locked = '1' then\n" +
                        "				if fast_reset = '1' and slow_reset = '1' then\n" +
                        "					fast_reset <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process;\n" +
                        "\n" +
                        "	process(system_clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(system_clk) then\n" +
                        "			if fast_reset = '1' then\n" +
                        "				slow_reset <= '1';\n" +
                        "				counter <= RESET_CYCLE_COUNT;\n" +
                        "			else\n" +
                        "				if counter = 0 then\n" +
                        "					slow_reset <= '0';\n" +
                        "				else\n" +
                        "					counter <= counter - 1;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_utilities_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_utilities.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_constants.all;\n" +
                        "\n" +
                        "package pp_utilities is\n" +
                        "\n" +
                        "	--! Converts a boolean to an std_logic.\n" +
                        "	function to_std_logic(input : in boolean) return std_logic;\n" +
                        "\n" +
                        "	-- Checks if a number is 2^n:\n" +
                        "	function is_pow2(input : in natural) return boolean;\n" +
                        "\n" +
                        "	--! Calculates log2 with integers.\n" +
                        "	function log2(input : in natural) return natural;\n" +
                        "\n" +
                        "	-- Gets the value of the sel signals to the wishbone interconnect for the specified\n" +
                        "	-- operand size and address.\n" +
                        "	function wb_get_data_sel(size : in std_logic_vector(1 downto 0); address : in std_logic_vector)\n" +
                        "		return std_logic_vector;\n" +
                        "\n" +
                        "end package pp_utilities;\n" +
                        "\n" +
                        "package body pp_utilities is\n" +
                        "\n" +
                        "	function to_std_logic(input : in boolean) return std_logic is\n" +
                        "	begin\n" +
                        "		if input then\n" +
                        "			return '1';\n" +
                        "		else\n" +
                        "			return '0';\n" +
                        "		end if;\n" +
                        "	end function to_std_logic;\n" +
                        "\n" +
                        "	function is_pow2(input : in natural) return boolean is\n" +
                        "		variable c : natural := 1;\n" +
                        "	begin\n" +
                        "		for i in 0 to 31 loop\n" +
                        "			if input = c then\n" +
                        "				return true;\n" +
                        "			end if;\n" +
                        "\n" +
                        "			c := c * 2;\n" +
                        "		end loop;\n" +
                        "\n" +
                        "		return false;\n" +
                        "	end function is_pow2;\n" +
                        "\n" +
                        "	function log2(input : in natural) return natural is\n" +
                        "		variable retval : natural := 0;\n" +
                        "		variable temp   : natural := input;\n" +
                        "	begin\n" +
                        "		while temp > 1 loop\n" +
                        "			retval := retval + 1;\n" +
                        "			temp := temp / 2;\n" +
                        "		end loop;\n" +
                        "\n" +
                        "		return retval;\n" +
                        "	end function log2;\n" +
                        "\n" +
                        "	function wb_get_data_sel(size : in std_logic_vector(1 downto 0); address : in std_logic_vector)\n" +
                        "		return std_logic_vector is\n" +
                        "	begin\n" +
                        "		case size is\n" +
                        "			when b\"01\" =>\n" +
                        "				case address(1 downto 0) is\n" +
                        "					when b\"00\" =>\n" +
                        "						return b\"0001\";\n" +
                        "					when b\"01\" =>\n" +
                        "						return b\"0010\";\n" +
                        "					when b\"10\" =>\n" +
                        "						return b\"0100\";\n" +
                        "					when b\"11\" =>\n" +
                        "						return b\"1000\";\n" +
                        "					when others =>\n" +
                        "						return b\"0001\";\n" +
                        "				end case;\n" +
                        "			when b\"10\" =>\n" +
                        "				if address(1) = '0' then\n" +
                        "					return b\"0011\";\n" +
                        "				else\n" +
                        "					return b\"1100\";\n" +
                        "				end if;\n" +
                        "			when others =>\n" +
                        "				return b\"1111\";\n" +
                        "		end case;\n" +
                        "	end function wb_get_data_sel;\n" +
                        "\n" +
                        "end package body pp_utilities;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_types_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_types.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "package pp_types is\n" +
                        "\n" +
                        "	--! Type used for register addresses.\n" +
                        "	subtype register_address is std_logic_vector(4 downto 0);\n" +
                        "\n" +
                        "	--! The available ALU operations.\n" +
                        "	type alu_operation is (\n" +
                        "			ALU_AND, ALU_OR, ALU_XOR,\n" +
                        "			ALU_SLT, ALU_SLTU,\n" +
                        "			ALU_ADD, ALU_SUB,\n" +
                        "			ALU_SRL, ALU_SLL, ALU_SRA,\n" +
                        "			ALU_NOP, ALU_INVALID\n" +
                        "		);\n" +
                        "\n" +
                        "	--! Types of branches.\n" +
                        "	type branch_type is (\n" +
                        "			BRANCH_NONE, BRANCH_JUMP, BRANCH_JUMP_INDIRECT, BRANCH_CONDITIONAL, BRANCH_SRET\n" +
                        "		);\n" +
                        "\n" +
                        "	--! Source of an ALU operand.\n" +
                        "	type alu_operand_source is (\n" +
                        "			ALU_SRC_REG, ALU_SRC_IMM, ALU_SRC_SHAMT, ALU_SRC_PC, ALU_SRC_PC_NEXT, ALU_SRC_NULL, ALU_SRC_CSR\n" +
                        "		);\n" +
                        "\n" +
                        "	--! Type of memory operation:\n" +
                        "	type memory_operation_type is (\n" +
                        "			MEMOP_TYPE_NONE, MEMOP_TYPE_INVALID, MEMOP_TYPE_LOAD, MEMOP_TYPE_LOAD_UNSIGNED, MEMOP_TYPE_STORE\n" +
                        "		);\n" +
                        "\n" +
                        "	-- Determines if a memory operation is a load:\n" +
                        "	function memop_is_load(input : in memory_operation_type) return boolean;\n" +
                        "\n" +
                        "	--! Size of a memory operation:\n" +
                        "	type memory_operation_size is (\n" +
                        "			MEMOP_SIZE_BYTE, MEMOP_SIZE_HALFWORD, MEMOP_SIZE_WORD\n" +
                        "		);\n" +
                        "\n" +
                        "	--! Wishbone master output signals:\n" +
                        "	type wishbone_master_outputs is record	\n" +
                        "			adr : std_logic_vector(31 downto 0);\n" +
                        "			sel : std_logic_vector( 3 downto 0);\n" +
                        "			cyc : std_logic;\n" +
                        "			stb : std_logic;\n" +
                        "			we  : std_logic;\n" +
                        "			dat : std_logic_vector(31 downto 0);\n" +
                        "		end record; \n" +
                        "\n" +
                        "	--! Wishbone master input signals:\n" +
                        "	type wishbone_master_inputs is record\n" +
                        "			dat : std_logic_vector(31 downto 0);\n" +
                        "			ack : std_logic;\n" +
                        "		end record;\n" +
                        "\n" +
                        "	--! State of the currently running test:\n" +
                        "	type test_state is (TEST_IDLE, TEST_RUNNING, TEST_FAILED, TEST_PASSED);\n" +
                        "\n" +
                        "	--! Current test context:\n" +
                        "	type test_context is record\n" +
                        "			state  : test_state;\n" +
                        "			number : std_logic_vector(29 downto 0);\n" +
                        "		end record;\n" +
                        "\n" +
                        "	--! Converts a test context to an std_logic_vector:\n" +
                        "	function test_context_to_std_logic(input : in test_context) return std_logic_vector;\n" +
                        "\n" +
                        "	--! Converts an std_logic_vector to a test context:\n" +
                        "	function std_logic_to_test_context(input : in std_logic_vector(31 downto 0)) return test_context;\n" +
                        "\n" +
                        "end package pp_types;\n" +
                        "\n" +
                        "package body pp_types is\n" +
                        "\n" +
                        "	function memop_is_load(input : in memory_operation_type) return boolean is\n" +
                        "	begin\n" +
                        "		return (input = MEMOP_TYPE_LOAD or input = MEMOP_TYPE_LOAD_UNSIGNED);\n" +
                        "	end function memop_is_load;\n" +
                        "\n" +
                        "	function test_context_to_std_logic(input : in test_context) return std_logic_vector is\n" +
                        "		variable retval : std_logic_vector(31 downto 0);\n" +
                        "	begin\n" +
                        "		case input.state is\n" +
                        "			when TEST_IDLE =>\n" +
                        "				retval(1 downto 0) := b\"00\";\n" +
                        "			when TEST_RUNNING =>\n" +
                        "				retval(1 downto 0) := b\"01\";\n" +
                        "			when TEST_FAILED =>\n" +
                        "				retval(1 downto 0) := b\"10\";\n" +
                        "			when TEST_PASSED =>\n" +
                        "				retval(1 downto 0) := b\"11\";\n" +
                        "		end case;\n" +
                        "\n" +
                        "		retval(31 downto 2) := input.number;\n" +
                        "		return retval;\n" +
                        "	end function test_context_to_std_logic;\n" +
                        "\n" +
                        "	function std_logic_to_test_context(input : in std_logic_vector(31 downto 0)) return test_context is\n" +
                        "		variable retval : test_context;\n" +
                        "	begin\n" +
                        "		case input(1 downto 0) is\n" +
                        "			when b\"00\" =>\n" +
                        "				retval.state := TEST_IDLE;\n" +
                        "			when b\"01\" =>\n" +
                        "				retval.state := TEST_RUNNING;\n" +
                        "			when b\"10\" =>\n" +
                        "				retval.state := TEST_FAILED;\n" +
                        "			when b\"11\" =>\n" +
                        "				retval.state := TEST_PASSED;\n" +
                        "			when others =>\n" +
                        "				retval.state := TEST_FAILED;\n" +
                        "		end case;\n" +
                        "\n" +
                        "		retval.number := input(31 downto 2);\n" +
                        "		return retval;\n" +
                        "	end function std_logic_to_test_context;\n" +
                        "\n" +
                        "end package body pp_types;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_constants_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "/hdl_code/potato_processor/vhdl/pp_constants.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "\n" +
                        "package pp_constants is\n" +
                        "\n" +
                        "	--! No-operation instruction, addi x0, x0, 0.\n" +
                        "	constant RISCV_NOP : std_logic_vector(31 downto 0) := (31 downto 5 => '0') & b\"10011\"; --! ADDI x0, x0, 0.\n" +
                        "\n" +
                        "end package pp_constants;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_potato_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_potato.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "--! @brief The Potato Processor.\n" +
                        "--! This file provides a Wishbone-compatible interface to the Potato processor.\n" +
                        "entity pp_potato is\n" +
                        "	generic(\n" +
                        "		PROCESSOR_ID           : std_logic_vector(31 downto 0) := x\"00000000\"; --! Processor ID.\n" +
                        "		RESET_ADDRESS          : std_logic_vector(31 downto 0) := x\"00000000\"; --! Address of the first instruction to execute.\n" +
                        "		MTIME_DIVIDER          : positive                      := 5;           --! Divider for the clock driving the MTIME counter.\n" +
                        "		ICACHE_ENABLE          : boolean                       := true;        --! Whether to enable the instruction cache.\n" +
                        "		ICACHE_LINE_SIZE       : natural                       := 4;           --! Number of words per instruction cache line.\n" +
                        "		ICACHE_NUM_LINES       : natural                       := 128          --! Number of cache lines in the instruction cache.\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		clk       : in std_logic;\n" +
                        "		timer_clk : in std_logic;\n" +
                        "		reset     : in std_logic;\n" +
                        "\n" +
                        "		-- Interrupts:\n" +
                        "		irq : in std_logic_vector(7 downto 0);\n" +
                        "\n" +
                        "		-- Test interface:\n" +
                        "		test_context_out : out test_context;\n" +
                        "\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_adr_out : out std_logic_vector(31 downto 0);\n" +
                        "		wb_sel_out : out std_logic_vector( 3 downto 0);\n" +
                        "		wb_cyc_out : out std_logic;\n" +
                        "		wb_stb_out : out std_logic;\n" +
                        "		wb_we_out  : out std_logic;\n" +
                        "		wb_dat_out : out std_logic_vector(31 downto 0);\n" +
                        "		wb_dat_in  : in  std_logic_vector(31 downto 0);\n" +
                        "		wb_ack_in  : in  std_logic\n" +
                        "	);\n" +
                        "end entity pp_potato;\n" +
                        "\n" +
                        "architecture behaviour of pp_potato is\n" +
                        "\n" +
                        "	-- Instruction memory signals:\n" +
                        "	signal imem_address : std_logic_vector(31 downto 0);\n" +
                        "	signal imem_data    : std_logic_vector(31 downto 0);\n" +
                        "	signal imem_req, imem_ack : std_logic;\n" +
                        "\n" +
                        "	-- Data memory signals:\n" +
                        "	signal dmem_address   : std_logic_vector(31 downto 0);\n" +
                        "	signal dmem_data_in   : std_logic_vector(31 downto 0);\n" +
                        "	signal dmem_data_out  : std_logic_vector(31 downto 0);\n" +
                        "	signal dmem_data_size : std_logic_vector( 1 downto 0);\n" +
                        "	signal dmem_read_req  : std_logic;\n" +
                        "	signal dmem_read_ack  : std_logic;\n" +
                        "	signal dmem_write_req : std_logic;\n" +
                        "	signal dmem_write_ack : std_logic;\n" +
                        "\n" +
                        "	-- Wishbone signals:\n" +
                        "	signal icache_inputs, dmem_if_inputs   : wishbone_master_inputs;\n" +
                        "	signal icache_outputs, dmem_if_outputs : wishbone_master_outputs;\n" +
                        "\n" +
                        "    -- Arbiter signals:\n" +
                        "	signal m1_inputs, m2_inputs   : wishbone_master_inputs;\n" +
                        "	signal m1_outputs, m2_outputs : wishbone_master_outputs;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	processor: entity work.pp_core\n" +
                        "		generic map(\n" +
                        "			PROCESSOR_ID => PROCESSOR_ID,\n" +
                        "			RESET_ADDRESS => RESET_ADDRESS\n" +
                        "		) port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			timer_clk => timer_clk,\n" +
                        "			imem_address => imem_address,\n" +
                        "			imem_data_in => imem_data,\n" +
                        "			imem_req => imem_req,\n" +
                        "			imem_ack => imem_ack,\n" +
                        "			dmem_address => dmem_address,\n" +
                        "			dmem_data_in => dmem_data_in,\n" +
                        "			dmem_data_out => dmem_data_out,\n" +
                        "			dmem_data_size => dmem_data_size,\n" +
                        "			dmem_read_req => dmem_read_req,\n" +
                        "			dmem_read_ack => dmem_read_ack,\n" +
                        "			dmem_write_req => dmem_write_req,\n" +
                        "			dmem_write_ack => dmem_write_ack,\n" +
                        "			test_context_out => test_context_out,\n" +
                        "			irq => irq\n" +
                        "		);\n" +
                        "\n" +
                        "	icache_enabled: if ICACHE_ENABLE\n" +
                        "	generate\n" +
                        "		icache: entity work.pp_icache\n" +
                        "			generic map(\n" +
                        "				LINE_SIZE => ICACHE_LINE_SIZE,\n" +
                        "				NUM_LINES => ICACHE_NUM_LINES\n" +
                        "			) port map(\n" +
                        "				clk => clk,\n" +
                        "				reset => reset,\n" +
                        "				mem_address_in => imem_address,\n" +
                        "				mem_data_out => imem_data,\n" +
                        "				mem_read_req => imem_req,\n" +
                        "				mem_read_ack => imem_ack,\n" +
                        "				wb_inputs => icache_inputs,\n" +
                        "				wb_outputs => icache_outputs\n" +
                        "			);\n" +
                        "\n" +
                        "		icache_inputs <= m1_inputs;\n" +
                        "		m1_outputs <= icache_outputs;\n" +
                        "\n" +
                        "		dmem_if_inputs <= m2_inputs;\n" +
                        "		m2_outputs <= dmem_if_outputs;\n" +
                        "	end generate icache_enabled;\n" +
                        "\n" +
                        "	icache_disabled: if not ICACHE_ENABLE\n" +
                        "	generate\n" +
                        "		imem_if: entity work.pp_wb_adapter\n" +
                        "			port map(\n" +
                        "				clk => clk,\n" +
                        "				reset => reset,\n" +
                        "				mem_address => imem_address,\n" +
                        "				mem_data_in => (others => '0'),\n" +
                        "				mem_data_out => imem_data,\n" +
                        "				mem_data_size => (others => '0'),\n" +
                        "				mem_read_req => imem_req,\n" +
                        "				mem_read_ack => imem_ack,\n" +
                        "				mem_write_req => '0',\n" +
                        "				mem_write_ack => open,\n" +
                        "				wb_inputs => icache_inputs,\n" +
                        "				wb_outputs => icache_outputs\n" +
                        "			);\n" +
                        "\n" +
                        "		dmem_if_inputs <= m1_inputs;\n" +
                        "		m1_outputs <= dmem_if_outputs;\n" +
                        "\n" +
                        "		icache_inputs <= m2_inputs;\n" +
                        "		m2_outputs <= icache_outputs;\n" +
                        "	end generate icache_disabled;\n" +
                        "\n" +
                        "	dmem_if: entity work.pp_wb_adapter\n" +
                        "		port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			mem_address => dmem_address,\n" +
                        "			mem_data_in => dmem_data_out,\n" +
                        "			mem_data_out => dmem_data_in,\n" +
                        "			mem_data_size => dmem_data_size,\n" +
                        "			mem_read_req => dmem_read_req,\n" +
                        "			mem_read_ack => dmem_read_ack,\n" +
                        "			mem_write_req => dmem_write_req,\n" +
                        "			mem_write_ack => dmem_write_ack,\n" +
                        "			wb_inputs => dmem_if_inputs,\n" +
                        "			wb_outputs => dmem_if_outputs\n" +
                        "		);\n" +
                        "\n" +
                        "	arbiter: entity work.pp_wb_arbiter\n" +
                        "		port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			m1_inputs => m1_inputs,\n" +
                        "			m1_outputs => m1_outputs,\n" +
                        "			m2_inputs => m2_inputs,\n" +
                        "			m2_outputs => m2_outputs,\n" +
                        "			wb_adr_out => wb_adr_out,\n" +
                        "			wb_sel_out => wb_sel_out,\n" +
                        "			wb_cyc_out => wb_cyc_out,\n" +
                        "			wb_stb_out => wb_stb_out,\n" +
                        "			wb_we_out => wb_we_out,\n" +
                        "			wb_dat_out => wb_dat_out,\n" +
                        "			wb_dat_in => wb_dat_in,\n" +
                        "			wb_ack_in => wb_ack_in\n" +
                        "		);\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_soc_timer_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/processor/vhdl/pp_soc_timer.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2016 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "--! @brief Simple timer module for generating periodic interrupts.\n" +
                        "--!\n" +
                        "--! The following registers are defined:\n" +
                        "--! |---------|-------------------|\n" +
                        "--! | Address | Description       |\n" +
                        "--! |---------|-------------------|\n" +
                        "--! | 0x00    | Control register  |\n" +
                        "--! | 0x04    | CompareL register |\n" +
                        "--! | 0x08    | CompareH register |\n" +
                        "--! | 0x0C    | CounterL register |\n" +
                        "--! | 0x10    | CounterH register |\n" +
                        "--! |---------|-------------------|\n" +
                        "--!\n" +
                        "--! The bits for the control register are:\n" +
                        "--! - 0: Run - set to '1' to enable the counter.\n" +
                        "--! - 1: Clear - set to '1' to clear the counter after a compare interrupt or to reset it.\n" +
                        "entity pp_soc_timer is\n" +
                        "	port(\n" +
                        "		clk   : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- Timer interrupt:\n" +
                        "		irq : out std_logic;\n" +
                        "\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_adr_in  : in  std_logic_vector(11 downto 0);\n" +
                        "		wb_dat_in  : in  std_logic_vector(31 downto 0);\n" +
                        "		wb_dat_out : out std_logic_vector(31 downto 0);\n" +
                        "		wb_cyc_in  : in  std_logic;\n" +
                        "		wb_stb_in  : in  std_logic;\n" +
                        "		wb_we_in   : in  std_logic;\n" +
                        "		wb_ack_out : out std_logic\n" +
                        "	);\n" +
                        "end entity;\n" +
                        "\n" +
                        "architecture behaviour of pp_soc_timer is\n" +
                        "	signal ctrl_run : std_logic;\n" +
                        "\n" +
                        "	signal counter : std_logic_vector(63 downto 0);\n" +
                        "	signal compare : std_logic_vector(63 downto 0);\n" +
                        "\n" +
                        "	-- Wishbone acknowledge signal:\n" +
                        "	signal ack : std_logic;\n" +
                        "begin\n" +
                        "\n" +
                        "	wb_ack_out <= ack and wb_cyc_in and wb_stb_in;\n" +
                        "	irq <= '1' when counter = compare else '0';\n" +
                        "\n" +
                        "	timer: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				wb_dat_out <= (others => '0');\n" +
                        "				ack <= '0';\n" +
                        "\n" +
                        "				ctrl_run <= '0';\n" +
                        "				counter <= (others => '0');\n" +
                        "				compare <= (others => '1');\n" +
                        "			else\n" +
                        "				if ctrl_run = '1' and counter /= compare then\n" +
                        "					counter <= std_logic_vector(unsigned(counter) + 1);\n" +
                        "				end if;\n" +
                        "\n" +
                        "				if wb_cyc_in = '1' and wb_stb_in = '1' and ack = '0' then\n" +
                        "					if wb_we_in = '1' then\n" +
                        "						case wb_adr_in is\n" +
                        "							when x\"000\" => -- Write control register\n" +
                        "								ctrl_run <= wb_dat_in(0);\n" +
                        "								if wb_dat_in(1) = '1' then\n" +
                        "									counter <= (others => '0');\n" +
                        "								end if;\n" +
                        "							when x\"004\" => -- Write compare register\n" +
                        "								compare(31 downto 0) <= wb_dat_in;\n" +
                        "							when x\"008\" => -- Write count register\n" +
                        "								compare(63 downto 32) <= wb_dat_in;\n" +
                        "							when x\"00C\" => -- Write count register\n" +
                        "								counter(31 downto 0) <= wb_dat_in;\n" +
                        "							when x\"010\" => -- Write count register\n" +
                        "								counter(63 downto 32) <= wb_dat_in;\n" +
                        "							when others =>\n" +
                        "						end case;\n" +
                        "						ack <= '1';\n" +
                        "					else\n" +
                        "						case wb_adr_in is\n" +
                        "							when x\"000\" => -- Read control register\n" +
                        "								wb_dat_out <= (0 => ctrl_run, others => '0');\n" +
                        "							when x\"004\" => -- Read compare register\n" +
                        "								wb_dat_out <= compare(31 downto 0);\n" +
                        "							when x\"008\" => -- Read count register\n" +
                        "								wb_dat_out <= compare(63 downto 32);\n" +
                        "							when x\"00C\" => -- Read count register\n" +
                        "								wb_dat_out <= counter(31 downto 0);\n" +
                        "							when x\"010\" => -- Read count register\n" +
                        "								wb_dat_out <= counter(63 downto 32);\n" +
                        "							when others =>\n" +
                        "								wb_dat_out <= (others => '0');\n" +
                        "						end case;\n" +
                        "						ack <= '1';\n" +
                        "					end if;\n" +
                        "				elsif wb_stb_in = '0' then\n" +
                        "					ack <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process timer;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_soc_uart_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/processor/vhdl/pp_soc_uart.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2016 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "--! @brief Simple UART module.\n" +
                        "--! The following registers are defined:\n" +
                        "--! |--------------------|--------------------------------------------|\n" +
                        "--! | Address            | Description                                |\n" +
                        "--! |--------------------|--------------------------------------------|\n" +
                        "--! | 0x00               | Transmit register (write-only)             |\n" +
                        "--! | 0x04               | Receive register (read-only)               |\n" +
                        "--! | 0x08               | Status register (read-only)                |\n" +
                        "--! | 0x0c               | Sample clock divisor register (read/write) |\n" +
                        "--! | 0x10               | Interrupt enable register (read/write)     |\n" +
                        "--! |--------------------|--------------------------------------------|\n" +
                        "--!\n" +
                        "--! The status register contains the following bits:\n" +
                        "--! - Bit 0: receive buffer empty\n" +
                        "--! - Bit 1: transmit buffer empty\n" +
                        "--! - Bit 2: receive buffer full\n" +
                        "--! - Bit 3: transmit buffer full\n" +
                        "--!\n" +
                        "--! The sample clock divisor should be set according to the formula:\n" +
                        "--! sample_clk = (f_clk / (baudrate * 16)) - 1\n" +
                        "--!\n" +
                        "--! If the sample clock divisor register is set to 0, the sample clock\n" +
                        "--! is stopped.\n" +
                        "--!\n" +
                        "--! Interrupts are enabled by setting the corresponding bit in the interrupt\n" +
                        "--! enable register. The following bits are available:\n" +
                        "--! - Bit 0: data received (receive buffer not empty)\n" +
                        "--! - Bit 1: ready to send data (transmit buffer empty)\n" +
                        "entity pp_soc_uart is\n" +
                        "	generic(\n" +
                        "		FIFO_DEPTH : natural := 64 --! Depth of the input and output FIFOs.\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		clk : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- UART ports:\n" +
                        "		txd : out std_logic;\n" +
                        "		rxd : in  std_logic;\n" +
                        "\n" +
                        "		-- Interrupt signal:\n" +
                        "		irq : out std_logic;\n" +
                        "\n" +
                        "		-- Wishbone ports:\n" +
                        "		wb_adr_in  : in  std_logic_vector(11 downto 0);\n" +
                        "		wb_dat_in  : in  std_logic_vector( 7 downto 0);\n" +
                        "		wb_dat_out : out std_logic_vector( 7 downto 0);\n" +
                        "		wb_we_in   : in  std_logic;\n" +
                        "		wb_cyc_in  : in  std_logic;\n" +
                        "		wb_stb_in  : in  std_logic;\n" +
                        "		wb_ack_out : out std_logic\n" +
                        "	);\n" +
                        "end entity pp_soc_uart;\n" +
                        "\n" +
                        "architecture behaviour of pp_soc_uart is\n" +
                        "\n" +
                        "	subtype bitnumber is natural range 0 to 7; --! Type representing the index of a bit.\n" +
                        "\n" +
                        "	-- UART sample clock signals:\n" +
                        "	signal sample_clk         : std_logic;\n" +
                        "	signal sample_clk_divisor : std_logic_vector(7 downto 0);\n" +
                        "	signal sample_clk_counter : std_logic_vector(sample_clk_divisor'range);\n" +
                        "\n" +
                        "	-- UART receive process signals:\n" +
                        "	type rx_state_type is (IDLE, RECEIVE, STARTBIT, STOPBIT);\n" +
                        "	signal rx_state : rx_state_type;\n" +
                        "	signal rx_byte : std_logic_vector(7 downto 0);\n" +
                        "	signal rx_current_bit : bitnumber;\n" +
                        "\n" +
                        "	subtype rx_sample_counter_type is natural range 0 to 15;\n" +
                        "	signal rx_sample_counter : rx_sample_counter_type;\n" +
                        "	signal rx_sample_value   : rx_sample_counter_type;\n" +
                        "\n" +
                        "	subtype rx_sample_delay_type is natural range 0 to 7;\n" +
                        "	signal rx_sample_delay   : rx_sample_delay_type;\n" +
                        "\n" +
                        "	-- UART transmit process signals:\n" +
                        "	type tx_state_type is (IDLE, TRANSMIT, STOPBIT);\n" +
                        "	signal tx_state : tx_state_type;\n" +
                        "	signal tx_byte : std_logic_vector(7 downto 0);\n" +
                        "	signal tx_current_bit : bitnumber;\n" +
                        "\n" +
                        "	-- UART transmit clock:\n" +
                        "	subtype uart_tx_counter_type is natural range 0 to 15;\n" +
                        "	signal uart_tx_counter : uart_tx_counter_type := 0;\n" +
                        "	signal uart_tx_clk : std_logic;\n" +
                        "\n" +
                        "	-- Buffer signals:\n" +
                        "	signal send_buffer_full, send_buffer_empty   : std_logic;\n" +
                        "	signal recv_buffer_full, recv_buffer_empty   : std_logic;\n" +
                        "	signal send_buffer_input, send_buffer_output : std_logic_vector(7 downto 0);\n" +
                        "	signal recv_buffer_input, recv_buffer_output : std_logic_vector(7 downto 0);\n" +
                        "	signal send_buffer_push, send_buffer_pop     : std_logic := '0';\n" +
                        "	signal recv_buffer_push, recv_buffer_pop     : std_logic := '0';\n" +
                        "\n" +
                        "	-- IRQ enable signals:\n" +
                        "	signal irq_recv_enable, irq_tx_ready_enable : std_logic := '0';\n" +
                        "\n" +
                        "	-- Wishbone signals:\n" +
                        "	type wb_state_type is (IDLE, WRITE_ACK, READ_ACK);\n" +
                        "	signal wb_state : wb_state_type;\n" +
                        "\n" +
                        "	signal wb_ack : std_logic; --! Wishbone acknowledge signal\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	irq <= (irq_recv_enable and (not recv_buffer_empty))\n" +
                        "		or (irq_tx_ready_enable and send_buffer_empty);\n" +
                        "\n" +
                        "	---------- UART receive ----------\n" +
                        "\n" +
                        "	recv_buffer_input <= rx_byte;\n" +
                        "\n" +
                        "	uart_receive: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				rx_state <= IDLE;\n" +
                        "				recv_buffer_push <= '0';\n" +
                        "			else\n" +
                        "				case rx_state is\n" +
                        "					when IDLE =>\n" +
                        "						if recv_buffer_push = '1' then\n" +
                        "							recv_buffer_push <= '0';\n" +
                        "						end if;\n" +
                        "\n" +
                        "						if sample_clk = '1' and rxd = '0' then\n" +
                        "							rx_sample_value <= rx_sample_counter;\n" +
                        "							rx_sample_delay <= 0;\n" +
                        "							rx_current_bit <= 0;\n" +
                        "							rx_state <= STARTBIT;\n" +
                        "						end if;\n" +
                        "					when STARTBIT =>\n" +
                        "						if sample_clk = '1' then\n" +
                        "							if rx_sample_delay = 7 then\n" +
                        "								rx_state <= RECEIVE;\n" +
                        "								rx_sample_value <= rx_sample_counter;\n" +
                        "								rx_sample_delay <= 0;\n" +
                        "							else\n" +
                        "								rx_sample_delay <= rx_sample_delay + 1;\n" +
                        "							end if;\n" +
                        "						end if;\n" +
                        "					when RECEIVE =>\n" +
                        "						if sample_clk = '1' and rx_sample_counter = rx_sample_value then\n" +
                        "							if rx_current_bit /= 7 then\n" +
                        "								rx_byte(rx_current_bit) <= rxd;\n" +
                        "								rx_current_bit <= rx_current_bit + 1;\n" +
                        "							else\n" +
                        "								rx_byte(rx_current_bit) <= rxd;\n" +
                        "								rx_state <= STOPBIT;\n" +
                        "							end if;\n" +
                        "						end if;\n" +
                        "					when STOPBIT =>\n" +
                        "						if sample_clk = '1' and rx_sample_counter = rx_sample_value then\n" +
                        "							rx_state <= IDLE;\n" +
                        "\n" +
                        "							if recv_buffer_full = '0' then\n" +
                        "								recv_buffer_push <= '1';\n" +
                        "							end if;\n" +
                        "						end if;\n" +
                        "				end case;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process uart_receive;\n" +
                        "\n" +
                        "	sample_counter: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				rx_sample_counter <= 0;\n" +
                        "			elsif sample_clk = '1' then\n" +
                        "				if rx_sample_counter = 15 then\n" +
                        "					rx_sample_counter <= 0;\n" +
                        "				else\n" +
                        "					rx_sample_counter <= rx_sample_counter + 1;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process sample_counter;\n" +
                        "\n" +
                        "	---------- UART transmit ----------\n" +
                        "\n" +
                        "	tx_byte <= send_buffer_output;\n" +
                        "\n" +
                        "	uart_transmit: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				txd <= '1';\n" +
                        "				tx_state <= IDLE;\n" +
                        "				send_buffer_pop <= '0';\n" +
                        "				tx_current_bit <= 0;\n" +
                        "			else\n" +
                        "				case tx_state is\n" +
                        "					when IDLE =>\n" +
                        "						if send_buffer_empty = '0' and uart_tx_clk = '1' then\n" +
                        "							txd <= '0';\n" +
                        "							send_buffer_pop <= '1';\n" +
                        "							tx_current_bit <= 0;\n" +
                        "							tx_state <= TRANSMIT;\n" +
                        "						elsif uart_tx_clk = '1' then\n" +
                        "							txd <= '1';\n" +
                        "						end if;\n" +
                        "					when TRANSMIT =>\n" +
                        "						if send_buffer_pop = '1' then\n" +
                        "							send_buffer_pop <= '0';\n" +
                        "						elsif uart_tx_clk = '1' and tx_current_bit = 7 then\n" +
                        "							txd <= tx_byte(tx_current_bit);\n" +
                        "							tx_state <= STOPBIT;\n" +
                        "						elsif uart_tx_clk = '1' then\n" +
                        "							txd <= tx_byte(tx_current_bit);\n" +
                        "							tx_current_bit <= tx_current_bit + 1;\n" +
                        "						end if;\n" +
                        "					when STOPBIT =>\n" +
                        "						if uart_tx_clk = '1' then\n" +
                        "							txd <= '1';\n" +
                        "							tx_state <= IDLE;\n" +
                        "						end if;\n" +
                        "				end case;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process uart_transmit;\n" +
                        "\n" +
                        "	uart_tx_clock_generator: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				uart_tx_counter <= 0;\n" +
                        "				uart_tx_clk <= '0';\n" +
                        "			else\n" +
                        "				if sample_clk = '1' then\n" +
                        "					if uart_tx_counter = 15 then\n" +
                        "						uart_tx_counter <= 0;\n" +
                        "						uart_tx_clk <= '1';\n" +
                        "					else\n" +
                        "						uart_tx_counter <= uart_tx_counter + 1;\n" +
                        "						uart_tx_clk <= '0';\n" +
                        "					end if;\n" +
                        "				else\n" +
                        "					uart_tx_clk <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process uart_tx_clock_generator;\n" +
                        "\n" +
                        "	---------- Sample clock generator ----------\n" +
                        "\n" +
                        "	sample_clock_generator: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				sample_clk_counter <= (others => '0');\n" +
                        "				sample_clk <= '0';\n" +
                        "			else\n" +
                        "				if sample_clk_divisor /= x\"00\" then\n" +
                        "					if sample_clk_counter = sample_clk_divisor then\n" +
                        "						sample_clk_counter <= (others => '0');\n" +
                        "						sample_clk <= '1';\n" +
                        "					else\n" +
                        "						sample_clk_counter <= std_logic_vector(unsigned(sample_clk_counter) + 1);\n" +
                        "						sample_clk <= '0';\n" +
                        "					end if;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process sample_clock_generator;\n" +
                        "\n" +
                        "	---------- Data Buffers ----------\n" +
                        "\n" +
                        "	send_buffer: entity work.pp_fifo\n" +
                        "		generic map(\n" +
                        "			DEPTH => FIFO_DEPTH,\n" +
                        "			WIDTH => 8\n" +
                        "		) port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			full => send_buffer_full,\n" +
                        "			empty => send_buffer_empty,\n" +
                        "			data_in => send_buffer_input,\n" +
                        "			data_out => send_buffer_output,\n" +
                        "			push => send_buffer_push,\n" +
                        "			pop => send_buffer_pop\n" +
                        "		);\n" +
                        "\n" +
                        "	recv_buffer: entity work.pp_fifo\n" +
                        "		generic map(\n" +
                        "			DEPTH => FIFO_DEPTH,\n" +
                        "			WIDTH => 8\n" +
                        "		) port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			full => recv_buffer_full,\n" +
                        "			empty => recv_buffer_empty,\n" +
                        "			data_in => recv_buffer_input,\n" +
                        "			data_out => recv_buffer_output,\n" +
                        "			push => recv_buffer_push,\n" +
                        "			pop => recv_buffer_pop\n" +
                        "		);\n" +
                        "\n" +
                        "	---------- Wishbone Interface ---------- \n" +
                        "\n" +
                        "	wb_ack_out <= wb_ack and wb_cyc_in and wb_stb_in;\n" +
                        "\n" +
                        "	wishbone: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				wb_ack <= '0';\n" +
                        "				wb_state <= IDLE;\n" +
                        "				send_buffer_push <= '0';\n" +
                        "				recv_buffer_pop <= '0';\n" +
                        "				sample_clk_divisor <= (others => '0');\n" +
                        "				irq_recv_enable <= '0';\n" +
                        "				irq_tx_ready_enable <= '0';\n" +
                        "			else\n" +
                        "				case wb_state is\n" +
                        "					when IDLE =>\n" +
                        "						if wb_cyc_in = '1' and wb_stb_in = '1' then\n" +
                        "							if wb_we_in = '1' then -- Write to register\n" +
                        "								if wb_adr_in = x\"000\" then\n" +
                        "									send_buffer_input <= wb_dat_in;\n" +
                        "									send_buffer_push <= '1';\n" +
                        "								elsif wb_adr_in = x\"00c\" then\n" +
                        "									sample_clk_divisor <= wb_dat_in;\n" +
                        "								elsif wb_adr_in = x\"010\" then\n" +
                        "									irq_recv_enable <= wb_dat_in(0);\n" +
                        "									irq_tx_ready_enable <= wb_dat_in(1);\n" +
                        "								end if;\n" +
                        "\n" +
                        "								-- Invalid writes are acked and ignored.\n" +
                        "\n" +
                        "								wb_ack <= '1';\n" +
                        "								wb_state <= WRITE_ACK;\n" +
                        "							else -- Read from register\n" +
                        "								if wb_adr_in = x\"004\" then\n" +
                        "									recv_buffer_pop <= '1';\n" +
                        "								elsif wb_adr_in = x\"008\" then\n" +
                        "									wb_dat_out <= x\"0\" & send_buffer_full & recv_buffer_full & send_buffer_empty & recv_buffer_empty;\n" +
                        "									wb_ack <= '1';\n" +
                        "								elsif wb_adr_in = x\"00c\" then\n" +
                        "									wb_dat_out <= sample_clk_divisor;\n" +
                        "									wb_ack <= '1';\n" +
                        "								elsif wb_adr_in = x\"010\" then\n" +
                        "									wb_dat_out <= (0 => irq_recv_enable, 1 => irq_tx_ready_enable, others => '0');\n" +
                        "									wb_ack <= '1';\n" +
                        "								else\n" +
                        "									wb_dat_out <= (others => '0');\n" +
                        "									wb_ack <= '1';\n" +
                        "								end if;\n" +
                        "								wb_state <= READ_ACK;\n" +
                        "							end if;\n" +
                        "						end if;\n" +
                        "					when WRITE_ACK =>\n" +
                        "						send_buffer_push <= '0';\n" +
                        "\n" +
                        "						if wb_stb_in = '0' then\n" +
                        "							wb_ack <= '0';\n" +
                        "							wb_state <= IDLE;\n" +
                        "						end if;\n" +
                        "					when READ_ACK =>\n" +
                        "						if recv_buffer_pop = '1' then\n" +
                        "							recv_buffer_pop <= '0';\n" +
                        "						else\n" +
                        "							wb_dat_out <= recv_buffer_output;\n" +
                        "							wb_ack <= '1';\n" +
                        "						end if;\n" +
                        "\n" +
                        "						if wb_stb_in = '0' then\n" +
                        "							wb_ack <= '0';\n" +
                        "							wb_state <= IDLE;\n" +
                        "						end if;\n" +
                        "				end case;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process wishbone;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_soc_intercon_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/processor/vhdl/pp_soc_intercon.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "--! @brief Module for registering and retrieving information about bus errors.\n" +
                        "--!\n" +
                        "--! The following registers are available:\n" +
                        "--! |---------|------------------------------------------|\n" +
                        "--! | Address | Description                              |\n" +
                        "--! |---------|------------------------------------------|\n" +
                        "--! | 0x00    | Status/control register                  |\n" +
                        "--! | 0x04    | Read error address                       |\n" +
                        "--! | 0x08    | Read error mask (SEL-bits for transfer)  |\n" +
                        "--! | 0x0c    | Write error address                      |\n" +
                        "--! | 0x10    | Write error mask (SEL-bits for transfer) |\n" +
                        "--! | 0x14    | Write error data                         |\n" +
                        "--! |---------|------------------------------------------|\n" +
                        "--!\n" +
                        "--! The bits in the status/control register have the following\n" +
                        "--! meanings:\n" +
                        "--! - Bit 0: IRQ status (read) / IRQ reset (write)\n" +
                        "--! - Bit 1: Read error (read-only) - the previous erroneous access was a read access\n" +
                        "--! - Bit 2: Write error (read-only) - the previous erroneous access was a write access\n" +
                        "--!\n" +
                        "--! Invalid bus accesses are registered using a dedicated Wishbone interface; the SoC\n" +
                        "--! interconnect has to make sure that erroneous accesses are routed to this interface in\n" +
                        "--! order for the details to be registered.\n" +
                        "entity pp_soc_intercon is\n" +
                        "	port(\n" +
                        "		clk   : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- Bus error interrupt:\n" +
                        "		error_irq : out std_logic;\n" +
                        "\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_adr_in  : in  std_logic_vector(11 downto 0);\n" +
                        "		wb_dat_in  : in  std_logic_vector(31 downto 0);\n" +
                        "		wb_dat_out : out std_logic_vector(31 downto 0);\n" +
                        "		wb_cyc_in  : in  std_logic;\n" +
                        "		wb_stb_in  : in  std_logic;\n" +
                        "		wb_we_in   : in  std_logic;\n" +
                        "		wb_ack_out : out std_logic;\n" +
                        "\n" +
                        "		-- Interface for registering bus errors:\n" +
                        "		err_adr_in  : in  std_logic_vector(31 downto 0);\n" +
                        "		err_dat_in  : in  std_logic_vector(31 downto 0);\n" +
                        "		err_sel_in  : in  std_logic_vector( 3 downto 0);\n" +
                        "		err_cyc_in  : in  std_logic;\n" +
                        "		err_stb_in  : in  std_logic;\n" +
                        "		err_we_in   : in  std_logic;\n" +
                        "		err_ack_out : out std_logic\n" +
                        "	);\n" +
                        "end entity pp_soc_intercon;\n" +
                        "\n" +
                        "architecture behaviour of pp_soc_intercon is\n" +
                        "\n" +
                        "	-- Previous erroneous bus access:\n" +
                        "	type error_access_type is (ACCESS_READ, ACCESS_WRITE, ACCESS_NONE);\n" +
                        "	signal prev_error_access : error_access_type;\n" +
                        "\n" +
                        "	-- Read error details:\n" +
                        "	signal read_error_address : std_logic_vector(31 downto 0);\n" +
                        "	signal read_error_sel     : std_logic_vector( 3 downto 0);\n" +
                        "\n" +
                        "	-- Write error details:\n" +
                        "	signal write_error_address : std_logic_vector(31 downto 0);\n" +
                        "	signal write_error_sel     : std_logic_vector( 3 downto 0);\n" +
                        "	signal write_error_data    : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "	signal irq_reset : std_logic := '0';\n" +
                        "	signal ack : std_logic;\n" +
                        "	signal error_ack : std_logic;\n" +
                        "\n" +
                        "begin\n" +
                        "	wb_ack_out <= ack and wb_cyc_in and wb_stb_in;\n" +
                        "	err_ack_out <= error_ack and err_cyc_in and err_stb_in;\n" +
                        "\n" +
                        "	error_irq <= '1' when prev_error_access /= ACCESS_NONE else '0';\n" +
                        "\n" +
                        "	wishbone: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				wb_dat_out <= (others => '0');\n" +
                        "				ack <= '0';\n" +
                        "				irq_reset <= '0';\n" +
                        "			else\n" +
                        "				if irq_reset = '1' then\n" +
                        "					irq_reset <= '0';\n" +
                        "				end if;\n" +
                        "\n" +
                        "				if wb_cyc_in = '1' and wb_stb_in = '1' and ack = '0' then\n" +
                        "					if wb_we_in = '1' then -- Write\n" +
                        "						case wb_adr_in is\n" +
                        "							when x\"000\" => -- Status/control register\n" +
                        "								if wb_dat_in(0) = '1' then\n" +
                        "									irq_reset <= '1';\n" +
                        "								end if;\n" +
                        "							when others =>\n" +
                        "								-- Ignore invalid writes\n" +
                        "						end case;\n" +
                        "						ack <= '1';\n" +
                        "					else -- Read\n" +
                        "						case wb_adr_in is\n" +
                        "							when x\"000\" =>\n" +
                        "								wb_dat_out(31 downto 3) <= (others => '0');\n" +
                        "								case prev_error_access is\n" +
                        "									when ACCESS_READ =>\n" +
                        "										wb_dat_out(2 downto 1) <= b\"01\";\n" +
                        "									when ACCESS_WRITE =>\n" +
                        "										wb_dat_out(2 downto 1) <= b\"10\";\n" +
                        "									when ACCESS_NONE =>\n" +
                        "										wb_dat_out(2 downto 1) <= b\"00\";\n" +
                        "								end case;\n" +
                        "\n" +
                        "								if prev_error_access /= ACCESS_NONE then\n" +
                        "									wb_dat_out(0) <= '1';\n" +
                        "								else\n" +
                        "									wb_dat_out(0) <= '0';\n" +
                        "								end if;\n" +
                        "							when x\"004\" =>\n" +
                        "								wb_dat_out <= read_error_address;\n" +
                        "							when x\"008\" =>\n" +
                        "								wb_dat_out <= std_logic_vector(resize(unsigned(read_error_sel), wb_dat_out'length));\n" +
                        "							when x\"00c\" =>\n" +
                        "								wb_dat_out <= write_error_address;\n" +
                        "							when x\"010\" =>\n" +
                        "								wb_dat_out <= std_logic_vector(resize(unsigned(write_error_sel), wb_dat_out'length));\n" +
                        "							when x\"014\" =>\n" +
                        "								wb_dat_out <= write_error_data;\n" +
                        "							when others =>\n" +
                        "								wb_dat_out <= (others => '0');\n" +
                        "						end case;\n" +
                        "						ack <= '1';\n" +
                        "					end if;\n" +
                        "				elsif wb_stb_in = '0' then\n" +
                        "					ack <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process wishbone;\n" +
                        "	\n" +
                        "	error_if: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				error_ack <= '0';\n" +
                        "\n" +
                        "				prev_error_access <= ACCESS_NONE;\n" +
                        "				read_error_address <= (others => '0');\n" +
                        "				read_error_sel <= (others => '0');\n" +
                        "				write_error_address <= (others => '0');\n" +
                        "				write_error_sel <= (others => '0');\n" +
                        "				write_error_data <= (others => '0');\n" +
                        "			elsif irq_reset = '1' then\n" +
                        "				prev_error_access <= ACCESS_NONE;\n" +
                        "			else\n" +
                        "				if err_cyc_in = '1' and err_stb_in = '1' and error_ack = '0' then\n" +
                        "					if err_we_in = '1' then -- Write\n" +
                        "						prev_error_access <= ACCESS_WRITE;\n" +
                        "						write_error_address <= err_adr_in;\n" +
                        "						write_error_sel <= err_sel_in;\n" +
                        "						write_error_data <= err_dat_in;\n" +
                        "					else -- Read\n" +
                        "						prev_error_access <= ACCESS_READ;\n" +
                        "						read_error_address <= err_adr_in;\n" +
                        "						read_error_sel <= err_sel_in;\n" +
                        "					end if;\n" +
                        "					error_ack <= '1';\n" +
                        "				elsif wb_stb_in = '0' then\n" +
                        "					error_ack <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process error_if;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_aee_rom_wrapper_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/processor/vhdl/aee_rom_wrapper.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - SoC design for the Arty FPGA board\n" +
                        "-- (c) Kristian Klomsten Skordal 2016 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "entity aee_rom_wrapper is\n" +
                        "	generic(\n" +
                        "		MEMORY_SIZE : natural := 4096 --! Memory size in bytes.\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		clk   : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_adr_in  : in  std_logic_vector(log2(MEMORY_SIZE) - 1 downto 0);\n" +
                        "		wb_dat_out : out std_logic_vector(31 downto 0);\n" +
                        "		wb_cyc_in  : in  std_logic;\n" +
                        "		wb_stb_in  : in  std_logic;\n" +
                        "		wb_sel_in  : in  std_logic_vector(3 downto 0);\n" +
                        "		wb_ack_out : out std_logic\n" +
                        "	);\n" +
                        "end entity aee_rom_wrapper;\n" +
                        "\n" +
                        "architecture behaviour of aee_rom_wrapper is\n" +
                        "\n" +
                        "	component aee_rom is\n" +
                        "		PORT\n" +
                        "		(\n" +
                        "			address		: IN STD_LOGIC_VECTOR (11 DOWNTO 0);\n" +
                        "			clock		: IN STD_LOGIC  := '1';\n" +
                        "			q		: OUT STD_LOGIC_VECTOR (31 DOWNTO 0)\n" +
                        "		);\n" +
                        "	end component;\n" +
                        "	signal ack : std_logic;\n" +
                        "\n" +
                        "	signal read_data : std_logic_vector(31 downto 0);\n" +
                        "	signal data_mask : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	rom: aee_rom\n" +
                        "		port map(\n" +
                        "			clock => clk,\n" +
                        "			address => wb_adr_in(log2(MEMORY_SIZE) - 1 downto 2),\n" +
                        "			q => read_data\n" +
                        "		);\n" +
                        "\n" +
                        "	data_mask <= (31 downto 24 => wb_sel_in(3), 23 downto 16 => wb_sel_in(2),\n" +
                        "		15 downto 8 => wb_sel_in(1), 7 downto 0 => wb_sel_in(0));\n" +
                        "\n" +
                        "	wb_dat_out <= read_data and data_mask;\n" +
                        "\n" +
                        "	--wb_ack_out <= ack and wb_cyc_in and wb_stb_in;\n" +
                        "	wb_ack_out <= wb_cyc_in and wb_stb_in;\n" +
                        "\n" +
                        "	--wishbone: process(clk)\n" +
                        "	--begin\n" +
                        "	--	if rising_edge(clk) then\n" +
                        "	--		if reset = '1' then\n" +
                        "	--			ack <= '0';\n" +
                        "	--		else\n" +
                        "	--			if wb_cyc_in = '1' and wb_stb_in = '1' then\n" +
                        "	--				ack <= '1';\n" +
                        "	--			else\n" +
                        "	--				ack <= '0';\n" +
                        "	--			end if;\n" +
                        "	--		end if;\n" +
                        "	--	end if;\n" +
                        "	--end process wishbone;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_soc_memory_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/processor/vhdl/pp_soc_memory.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "--! @brief Simple memory module for use in Wishbone-based systems.\n" +
                        "entity pp_soc_memory is\n" +
                        "	generic(\n" +
                        "		MEMORY_SIZE : natural := 4096 --! Memory size in bytes.\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		clk : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_adr_in  : in  std_logic_vector(log2(MEMORY_SIZE) - 1 downto 0);\n" +
                        "		wb_dat_in  : in  std_logic_vector(31 downto 0);\n" +
                        "		wb_dat_out : out std_logic_vector(31 downto 0);\n" +
                        "		wb_cyc_in  : in  std_logic;\n" +
                        "		wb_stb_in  : in  std_logic;\n" +
                        "		wb_sel_in  : in  std_logic_vector( 3 downto 0);\n" +
                        "		wb_we_in   : in  std_logic;\n" +
                        "		wb_ack_out : out std_logic\n" +
                        "	);\n" +
                        "end entity pp_soc_memory;\n" +
                        "\n" +
                        "architecture behaviour of pp_soc_memory is\n" +
                        "	type memory_array is array(0 to (MEMORY_SIZE / 4) - 1) of std_logic_vector(31 downto 0);\n" +
                        "	signal memory : memory_array := (others => (others => '0'));\n" +
                        "\n" +
                        "	attribute ram_style : string;\n" +
                        "	attribute ram_style of memory : signal is \"block\";\n" +
                        "\n" +
                        "	type state_type is (IDLE, ACK);\n" +
                        "	signal state : state_type;\n" +
                        "\n" +
                        "	signal read_ack : std_logic;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	wb_ack_out <= read_ack and wb_stb_in;\n" +
                        "\n" +
                        "	process(reset, wb_adr_in, wb_dat_in, wb_cyc_in, wb_stb_in, wb_sel_in, wb_we_in)\n" +
                        "	begin\n" +
                        "		--if rising_edge(clk) then add clk and remove others in process\n" +
                        "			if reset = '1' then\n" +
                        "				read_ack <= '0';\n" +
                        "				state <= IDLE;\n" +
                        "			else\n" +
                        "				if wb_cyc_in = '1' then\n" +
                        "					case state is\n" +
                        "						when IDLE =>\n" +
                        "							if wb_stb_in = '1' and wb_we_in = '1' then\n" +
                        "								 for i in 0 to 3 loop\n" +
                        "								 	if wb_sel_in(i) = '1' then\n" +
                        "								 		memory(to_integer(unsigned(wb_adr_in(wb_adr_in'left downto 2))))(((i + 1) * 8) - 1 downto i * 8)\n" +
                        "								 			<= wb_dat_in(((i + 1) * 8) - 1 downto i * 8);\n" +
                        "								 	end if;\n" +
                        "								 end loop;\n" +
                        "								 read_ack <= '1';\n" +
                        "								 state <= ACK;\n" +
                        "							elsif wb_stb_in = '1' then\n" +
                        "								wb_dat_out <= memory(to_integer(unsigned(wb_adr_in(wb_adr_in'left downto 2))));\n" +
                        "								read_ack <= '1';\n" +
                        "								state <= ACK;\n" +
                        "							end if;\n" +
                        "						when ACK =>\n" +
                        "							if wb_stb_in = '0' then\n" +
                        "								read_ack <= '0';\n" +
                        "								state <= IDLE;\n" +
                        "							end if;\n" +
                        "					end case;\n" +
                        "				else\n" +
                        "					state <= IDLE;\n" +
                        "					read_ack <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		--end if;\n" +
                        "	end process;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_In_Out_Peripheral_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/in_out_peripheral/vhdl/In_Out_Peripheral.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "entity In_Out_Peripheral is\n" +
                        "	port(\n" +
                        "		clk 			: in std_logic;\n" +
                        "		reset 		: in std_logic;\n" +
                        "		\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_adr_in 	: in  std_logic_vector(6 downto 0);\n" +
                        "		wb_dat_in 	: in  std_logic_vector(31 downto 0);\n" +
                        "		wb_dat_out 	: out std_logic_vector(31 downto 0);\n" +
                        "		wb_cyc_in 	: in  std_logic;\n" +
                        "		wb_stb_in 	: in  std_logic;\n" +
                        "		wb_sel_in 	: in  std_logic_vector( 3 downto 0);\n" +
                        "		wb_we_in 	: in  std_logic;\n" +
                        "		wb_ack_out 	: out std_logic;\n" +
                        "		\n" +
                        "		-- I O interface:\n" +
                        "		KEY			: IN STD_LOGIC_VECTOR(3 DOWNTO 0);\n" +
                        "		LEDR			: OUT STD_LOGIC_VECTOR(9 DOWNTO 0);\n" +
                        "		LEDG			: OUT STD_LOGIC_VECTOR(7 DOWNTO 0);\n" +
                        "		SW				: IN STD_LOGIC_VECTOR(9 DOWNTO 0);\n" +
                        "		GPIO_OUT		: OUT STD_LOGIC_VECTOR(17 DOWNTO 0);\n" +
                        "		GPIO_IN		: IN STD_LOGIC_VECTOR(17 DOWNTO 0);\n" +
                        "		\n" +
                        "		-- Output Signal From Register\n" +
                        "		GPIO_IN_O	: OUT STD_LOGIC_VECTOR(17 DOWNTO 0);\n" +
                        "		SW_O			: OUT STD_LOGIC_VECTOR(9 DOWNTO 0);\n" +
                        "		KEY_O			: OUT STD_LOGIC_VECTOR(3 DOWNTO 0)\n" +
                        "	);\n" +
                        "end entity In_Out_Peripheral;\n" +
                        "\n" +
                        "architecture behaviour of In_Out_Peripheral is\n" +
                        "	type state_type is (IDLE, ACK);\n" +
                        "	signal state : state_type;\n" +
                        "\n" +
                        "	signal read_ack : std_logic;\n" +
                        "	\n" +
                        "	signal register_in	: STD_LOGIC_VECTOR(63 DOWNTO 0);   -- 32 - 0\n" +
                        "	signal register_out	: STD_LOGIC_VECTOR(63 DOWNTO 0);   -- 36 - 0\n" +
                        "	signal OUT_Data_1		: STD_LOGIC;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	wb_dat_out <= \"0000000000000000000000000000000\" & OUT_Data_1;\n" +
                        "	wb_ack_out <= read_ack and wb_stb_in;\n" +
                        "\n" +
                        "	process(clk, reset, wb_we_in, wb_adr_in, KEY, SW, GPIO_IN, wb_stb_in, wb_we_in, wb_cyc_in, wb_dat_in, wb_sel_in)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "		\n" +
                        "			GPIO_IN_O 	<= register_in(17 DOWNTO 0);\n" +
                        "			SW_O			<= register_in(27 DOWNTO 18);\n" +
                        "			KEY_O			<= register_in(31 DOWNTO 28);\n" +
                        "			\n" +
                        "			if reset = '1' then\n" +
                        "				register_in  <= (OTHERS => '0');\n" +
                        "				register_out <= (OTHERS => '0');\n" +
                        "				GPIO_OUT     <= (OTHERS => '0');\n" +
                        "				LEDR         <= (OTHERS => '0');\n" +
                        "				LEDG         <= (OTHERS => '0');\n" +
                        "				OUT_Data_1     <= '0';\n" +
                        "			\n" +
                        "				read_ack <= '0';\n" +
                        "				state <= IDLE;\n" +
                        "			else\n" +
                        "				if wb_cyc_in = '1' then\n" +
                        "					case state is\n" +
                        "						when IDLE =>\n" +
                        "							if wb_stb_in = '1' and wb_we_in = '1' then -- write\n" +
                        "								if wb_adr_in = \"1111111\" then\n" +
                        "									register_in        <= \"00000000000000000000000000000000\" & KEY & SW & GPIO_IN;\n" +
                        "									GPIO_OUT           <= register_out(17 downto 0);\n" +
                        "									LEDR               <= register_out(27 downto 18);\n" +
                        "									LEDG               <= register_out(35 downto 28);\n" +
                        "								else\n" +
                        "									register_out(to_integer(unsigned(wb_adr_in(5 downto 0)))) <= wb_dat_in(0);\n" +
                        "								end if;\n" +
                        "								read_ack <= '1';\n" +
                        "								state <= ACK;\n" +
                        "							elsif wb_stb_in = '1' then -- read\n" +
                        "								if wb_adr_in(6) = '0' then   -- read inputs\n" +
                        "									OUT_Data_1 <= register_in(to_integer(unsigned(wb_adr_in(5 downto 0))));\n" +
                        "								else                       -- read outputs\n" +
                        "									OUT_Data_1 <= register_out(to_integer(unsigned(wb_adr_in(5 downto 0))));\n" +
                        "								end if;\n" +
                        "								read_ack <= '1';\n" +
                        "								state <= ACK;\n" +
                        "							end if;\n" +
                        "						when ACK =>\n" +
                        "							if wb_stb_in = '0' then\n" +
                        "								read_ack <= '0';\n" +
                        "								state <= IDLE;\n" +
                        "							end if;\n" +
                        "					end case;\n" +
                        "				else\n" +
                        "					state <= IDLE;\n" +
                        "					read_ack <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_Time_Measurement_Peripheral_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/time_measurement/vhdl/Time_Measurement_Peripheral.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "--! The following registers are defined:\n" +
                        "--! |---------|-------------------|\n" +
                        "--! | Address | Description       |\n" +
                        "--! |---------|-------------------|\n" +
                        "--! | 0b00    | START_STOP_A      |\n" +
                        "--! | 0b01    | MICRO_NANO_A      |\n" +
                        "--! | 0b10    | READ_TIME_A       |\n" +
                        "--! | 0b11    |                   |\n" +
                        "--! |---------|-------------------|\n" +
                        "\n" +
                        "entity Time_Measurement_Peripheral is\n" +
                        "	port(\n" +
                        "		clk 			: in std_logic;\n" +
                        "		reset 		: in std_logic;\n" +
                        "		\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_adr_in 	: in  std_logic_vector(1 downto 0);\n" +
                        "		wb_dat_in 	: in  std_logic_vector(31 downto 0);\n" +
                        "		wb_dat_out 	: out std_logic_vector(31 downto 0) := (others => '0');\n" +
                        "		wb_cyc_in 	: in  std_logic;\n" +
                        "		wb_stb_in 	: in  std_logic;\n" +
                        "		wb_sel_in 	: in  std_logic_vector( 3 downto 0);\n" +
                        "		wb_we_in 	: in  std_logic;\n" +
                        "		wb_ack_out 	: out std_logic;\n" +
                        "		\n" +
                        "		-- HEX interface\n" +
                        "		HEX0			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);\n" +
                        "		HEX1			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);\n" +
                        "		HEX2			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);\n" +
                        "		HEX3			: OUT STD_LOGIC_VECTOR(6 DOWNTO 0)\n" +
                        "	);\n" +
                        "end entity Time_Measurement_Peripheral;\n" +
                        "\n" +
                        "architecture behaviour of Time_Measurement_Peripheral is\n" +
                        "	type state_type is (IDLE, ACK);\n" +
                        "	signal state : state_type;\n" +
                        "	\n" +
                        "	--signal Time_Micro_Nano		: std_logic_vector(31 downto 0);\n" +
                        "	--signal Time_Micro_Nano_S	: std_logic_vector(31 downto 0);\n" +
                        "	signal Micro_Nano				: std_logic := '0'; -- Micro = 1, Nano = 0\n" +
                        "	signal Start_Stop				: std_logic := '0'; -- Start = 1, Stop = 0\n" +
                        "	\n" +
                        "	signal read_ack : std_logic;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	wb_ack_out <= read_ack and wb_stb_in;\n" +
                        "\n" +
                        "	process(clk, reset, wb_adr_in, wb_stb_in, wb_we_in)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				--Time_micro_Nano <= (others => '0');\n" +
                        "				\n" +
                        "				read_ack <= '0';\n" +
                        "				state <= IDLE;\n" +
                        "			else\n" +
                        "				if wb_cyc_in = '1' then\n" +
                        "					case state is\n" +
                        "						when IDLE =>\n" +
                        "							if wb_stb_in = '1' and wb_we_in = '1' then -- write\n" +
                        "								if wb_adr_in = \"00\" then -- start or  stop\n" +
                        "									if wb_dat_in(7 downto 0) = X\"71\" then\n" +
                        "										Start_Stop <= '1';\n" +
                        "									elsif wb_dat_in(7 downto 0) = X\"53\" then\n" +
                        "										Start_Stop <= '0';\n" +
                        "									end if;\n" +
                        "								elsif wb_adr_in = \"01\" then\n" +
                        "									if wb_dat_in(7 downto 0) = X\"36\" then\n" +
                        "										Micro_Nano <= '1';\n" +
                        "									elsif wb_dat_in(7 downto 0) = X\"42\" then\n" +
                        "										Micro_Nano <= '0';\n" +
                        "									end if;\n" +
                        "								end if;\n" +
                        "								read_ack <= '1';\n" +
                        "								state <= ACK;\n" +
                        "							elsif wb_stb_in = '1' then -- read\n" +
                        "								if wb_adr_in = \"10\" then\n" +
                        "									--wb_dat_out <= Time_Micro_Nano;\n" +
                        "								end if;\n" +
                        "								read_ack <= '1';\n" +
                        "								state <= ACK;\n" +
                        "							end if;\n" +
                        "						when ACK =>\n" +
                        "							if wb_stb_in = '0' then\n" +
                        "								read_ack <= '0';\n" +
                        "								state <= IDLE;\n" +
                        "							end if;\n" +
                        "					end case;\n" +
                        "				else\n" +
                        "					state <= IDLE;\n" +
                        "					read_ack <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "			--Time_Micro_Nano <= Time_Micro_Nano_S;\n" +
                        "		end if;\n" +
                        "		\n" +
                        "	end process;\n" +
                        "\n" +
                        "	TimeCalculation: entity work.Time_Calculation\n" +
                        "		port map(\n" +
                        "			clk 			=> clk,\n" +
                        "			reset 		=> reset,\n" +
                        "			\n" +
                        "			-- Time Signals\n" +
                        "			Time_Micro_Nano	=> open,\n" +
                        "			Micro_Nano			=> Micro_Nano,\n" +
                        "			Start_Stop			=> Start_Stop,\n" +
                        "			\n" +
                        "			-- HEX interface\n" +
                        "			HEX0					=> HEX0,\n" +
                        "			HEX1					=> HEX1,\n" +
                        "			HEX2					=> HEX2,\n" +
                        "			HEX3					=> HEX3\n" +
                        "		);\n" +
                        "		\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_fifo_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_fifo.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "--! @brief A generic FIFO module.\n" +
                        "--! Adopted from the FIFO module in <https://github.com/skordal/smallthings>.\n" +
                        "entity pp_fifo is\n" +
                        "	generic(\n" +
                        "		DEPTH : natural := 64;\n" +
                        "		WIDTH : natural := 32\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		-- Control lines:\n" +
                        "		clk   : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- Status lines:\n" +
                        "		full  : out std_logic;\n" +
                        "		empty : out std_logic;\n" +
                        "\n" +
                        "		-- Data in:\n" +
                        "		data_in   : in  std_logic_vector(WIDTH - 1 downto 0);\n" +
                        "		data_out  : out std_logic_vector(WIDTH - 1 downto 0);\n" +
                        "		push, pop : in std_logic\n" +
                        "	);\n" +
                        "end entity pp_fifo;\n" +
                        "\n" +
                        "architecture behaviour of pp_fifo is\n" +
                        "\n" +
                        "	type memory_array is array(0 to DEPTH - 1) of std_logic_vector(WIDTH - 1 downto 0);\n" +
                        "	shared variable memory : memory_array := (others => (others => '0'));\n" +
                        "\n" +
                        "	subtype index_type is integer range 0 to DEPTH - 1;\n" +
                        "	signal top, bottom : index_type;\n" +
                        "\n" +
                        "	type fifo_op is (FIFO_POP, FIFO_PUSH);\n" +
                        "	signal prev_op : fifo_op := FIFO_POP;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	empty <= '1' when top = bottom and prev_op = FIFO_POP else '0';\n" +
                        "	full <= '1' when top = bottom and prev_op = FIFO_PUSH else '0';\n" +
                        "\n" +
                        "	read: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				bottom <= 0;\n" +
                        "			else\n" +
                        "				if pop = '1' then\n" +
                        "					data_out <= memory(bottom);\n" +
                        "					bottom <= (bottom + 1) mod DEPTH;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process read;\n" +
                        "\n" +
                        "	write: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				top <= 0;\n" +
                        "			else\n" +
                        "				if push = '1' then\n" +
                        "					memory(top) := data_in;\n" +
                        "					top <= (top + 1) mod DEPTH;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process write;\n" +
                        "\n" +
                        "	set_prev_op: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				prev_op <= FIFO_POP;\n" +
                        "			else\n" +
                        "				if push = '1' and pop = '1' then\n" +
                        "					prev_op <= FIFO_POP;\n" +
                        "				elsif push = '1' then\n" +
                        "					prev_op <= FIFO_PUSH;\n" +
                        "				elsif pop = '1' then\n" +
                        "					prev_op <= FIFO_POP;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process set_prev_op;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_core_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_core.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_constants.all;\n" +
                        "use work.pp_utilities.all;\n" +
                        "use work.pp_csr.all;\n" +
                        "\n" +
                        "--! @brief The Potato Processor is a simple processor core for use in FPGAs.\n" +
                        "entity pp_core is\n" +
                        "	generic(\n" +
                        "		PROCESSOR_ID           : std_logic_vector(31 downto 0) := x\"00000000\"; --! Processor ID.\n" +
                        "		RESET_ADDRESS          : std_logic_vector(31 downto 0) := x\"00000000\"; --! Address of the first instruction to execute.\n" +
                        "		MTIME_DIVIDER          : positive := 5                                 --! Divider for the clock driving the MTIME counter\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		-- Control inputs:\n" +
                        "		clk       : in std_logic; --! Processor clock\n" +
                        "		reset     : in std_logic; --! Reset signal\n" +
                        "\n" +
                        "		timer_clk : in std_logic; --! Clock used for the timer/counter\n" +
                        "\n" +
                        "		-- Instruction memory interface:\n" +
                        "		imem_address : out std_logic_vector(31 downto 0); --! Address of the next instruction\n" +
                        "		imem_data_in : in  std_logic_vector(31 downto 0); --! Instruction input\n" +
                        "		imem_req     : out std_logic;\n" +
                        "		imem_ack     : in  std_logic;\n" +
                        "\n" +
                        "		-- Data memory interface:\n" +
                        "		dmem_address   : out std_logic_vector(31 downto 0); --! Data address\n" +
                        "		dmem_data_in   : in  std_logic_vector(31 downto 0); --! Input from the data memory\n" +
                        "		dmem_data_out  : out std_logic_vector(31 downto 0); --! Ouptut to the data memory\n" +
                        "		dmem_data_size : out std_logic_vector( 1 downto 0);  --! Size of the data, 1 = 8 bits, 2 = 16 bits, 0 = 32 bits. \n" +
                        "		dmem_read_req  : out std_logic;                      --! Data memory read request\n" +
                        "		dmem_read_ack  : in  std_logic;                      --! Data memory read acknowledge\n" +
                        "		dmem_write_req : out std_logic;                      --! Data memory write request\n" +
                        "		dmem_write_ack : in  std_logic;                      --! Data memory write acknowledge\n" +
                        "\n" +
                        "		-- Test interface:\n" +
                        "		test_context_out : out test_context;                 --! Test context output.\n" +
                        "\n" +
                        "		-- External interrupt input:\n" +
                        "		irq : in std_logic_vector(7 downto 0) --! IRQ inputs.\n" +
                        "	);\n" +
                        "end entity pp_core;\n" +
                        "\n" +
                        "architecture behaviour of pp_core is\n" +
                        "\n" +
                        "	------- Flush signals -------\n" +
                        "	signal flush_if, flush_id, flush_ex : std_logic;\n" +
                        "\n" +
                        "	------- Stall signals -------\n" +
                        "	signal stall_if, stall_id, stall_ex, stall_mem : std_logic;\n" +
                        "\n" +
                        "	-- Signals used to determine if an instruction should be counted\n" +
                        "	-- by the instret counter:\n" +
                        "	signal if_count_instruction, id_count_instruction  : std_logic;\n" +
                        "	signal ex_count_instruction, mem_count_instruction : std_logic;\n" +
                        "	signal wb_count_instruction : std_logic;\n" +
                        "\n" +
                        "	-- CSR read port signals:\n" +
                        "	signal csr_read_data      : std_logic_vector(31 downto 0);\n" +
                        "	signal csr_read_address, csr_read_address_p : csr_address;\n" +
                        "\n" +
                        "	-- Status register outputs:\n" +
                        "	signal mtvec   : std_logic_vector(31 downto 0);\n" +
                        "	signal mie     : std_logic_vector(31 downto 0);\n" +
                        "	signal ie, ie1 : std_logic;\n" +
                        "\n" +
                        "	-- Internal interrupt signals:\n" +
                        "	signal software_interrupt, timer_interrupt : std_logic;\n" +
                        "\n" +
                        "	-- Hazard detected in the execute stage:\n" +
                        "	signal hazard_detected : std_logic;\n" +
                        "\n" +
                        "	-- Branch targets:\n" +
                        "	signal exception_target, branch_target : std_logic_vector(31 downto 0);\n" +
                        "	signal branch_taken, exception_taken   : std_logic;\n" +
                        "\n" +
                        "	-- Register file read ports:\n" +
                        "	signal rs1_address_p, rs2_address_p : register_address;\n" +
                        "	signal rs1_address, rs2_address     : register_address;\n" +
                        "	signal rs1_data, rs2_data           : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "	-- Data memory signals:\n" +
                        "	signal dmem_address_p   : std_logic_vector(31 downto 0);\n" +
                        "	signal dmem_data_size_p : std_logic_vector(1 downto 0);\n" +
                        "	signal dmem_data_out_p  : std_logic_vector(31 downto 0);\n" +
                        "	signal dmem_read_req_p  : std_logic;\n" +
                        "	signal dmem_write_req_p : std_logic;\n" +
                        "\n" +
                        "	-- Fetch stage signals:\n" +
                        "	signal if_instruction, if_pc : std_logic_vector(31 downto 0);\n" +
                        "	signal if_instruction_ready  : std_logic;\n" +
                        "\n" +
                        "	-- Decode stage signals:\n" +
                        "	signal id_funct3          : std_logic_vector(2 downto 0);\n" +
                        "	signal id_rd_address      : register_address;\n" +
                        "	signal id_rd_write        : std_logic;\n" +
                        "	signal id_rs1_address     : register_address;\n" +
                        "	signal id_rs2_address     : register_address;\n" +
                        "	signal id_csr_address     : csr_address;\n" +
                        "	signal id_csr_write       : csr_write_mode;\n" +
                        "	signal id_csr_use_immediate : std_logic;\n" +
                        "	signal id_shamt           : std_logic_vector(4 downto 0);\n" +
                        "	signal id_immediate       : std_logic_vector(31 downto 0);\n" +
                        "	signal id_branch          : branch_type;\n" +
                        "	signal id_alu_x_src, id_alu_y_src : alu_operand_source;\n" +
                        "	signal id_alu_op          : alu_operation;\n" +
                        "	signal id_mem_op          : memory_operation_type;\n" +
                        "	signal id_mem_size        : memory_operation_size;\n" +
                        "	signal id_pc              : std_logic_vector(31 downto 0);\n" +
                        "	signal id_exception       : std_logic;\n" +
                        "	signal id_exception_cause : csr_exception_cause;\n" +
                        "\n" +
                        "	-- Execute stage signals:\n" +
                        "	signal ex_dmem_address   : std_logic_vector(31 downto 0);\n" +
                        "	signal ex_dmem_data_size : std_logic_vector(1 downto 0);\n" +
                        "	signal ex_dmem_data_out  : std_logic_vector(31 downto 0);\n" +
                        "	signal ex_dmem_read_req  : std_logic;\n" +
                        "	signal ex_dmem_write_req : std_logic;\n" +
                        "	signal ex_rd_address     : register_address;\n" +
                        "	signal ex_rd_data        : std_logic_vector(31 downto 0);\n" +
                        "	signal ex_rd_write       : std_logic;\n" +
                        "	signal ex_pc             : std_logic_vector(31 downto 0);\n" +
                        "	signal ex_csr_address    : csr_address;\n" +
                        "	signal ex_csr_write      : csr_write_mode;\n" +
                        "	signal ex_csr_data       : std_logic_vector(31 downto 0);\n" +
                        "	signal ex_branch         : branch_type;\n" +
                        "	signal ex_mem_op         : memory_operation_type;\n" +
                        "	signal ex_mem_size       : memory_operation_size;\n" +
                        "	signal ex_exception_context : csr_exception_context;\n" +
                        "\n" +
                        "	-- Memory stage signals:\n" +
                        "	signal mem_rd_write    : std_logic;\n" +
                        "	signal mem_rd_address  : register_address;\n" +
                        "	signal mem_rd_data     : std_logic_vector(31 downto 0);\n" +
                        "	signal mem_csr_address : csr_address;\n" +
                        "	signal mem_csr_write   : csr_write_mode;\n" +
                        "	signal mem_csr_data    : std_logic_vector(31 downto 0);\n" +
                        "	signal mem_mem_op      : memory_operation_type;\n" +
                        "\n" +
                        "	signal mem_exception         : std_logic;\n" +
                        "	signal mem_exception_context : csr_exception_context;\n" +
                        "\n" +
                        "	-- Writeback signals:\n" +
                        "	signal wb_rd_address  : register_address;\n" +
                        "	signal wb_rd_data     : std_logic_vector(31 downto 0);\n" +
                        "	signal wb_rd_write    : std_logic;\n" +
                        "	signal wb_csr_address : csr_address;\n" +
                        "	signal wb_csr_write   : csr_write_mode;\n" +
                        "	signal wb_csr_data    : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "	signal wb_exception         : std_logic;\n" +
                        "	signal wb_exception_context : csr_exception_context;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	stall_if <= stall_id;\n" +
                        "	stall_id <= stall_ex;\n" +
                        "	stall_ex <= hazard_detected or stall_mem;\n" +
                        "	stall_mem <= to_std_logic(memop_is_load(mem_mem_op) and dmem_read_ack = '0')\n" +
                        "		or to_std_logic(mem_mem_op = MEMOP_TYPE_STORE and dmem_write_ack = '0');\n" +
                        "\n" +
                        "	flush_if <= (branch_taken or exception_taken) and not stall_if;\n" +
                        "	flush_id <= (branch_taken or exception_taken) and not stall_id;\n" +
                        "	flush_ex <= (branch_taken or exception_taken) and not stall_ex;\n" +
                        "\n" +
                        "	------- Control and status module -------\n" +
                        "	csr_unit: entity work.pp_csr_unit\n" +
                        "			generic map(\n" +
                        "				PROCESSOR_ID => PROCESSOR_ID\n" +
                        "			) port map(\n" +
                        "				clk => clk,\n" +
                        "				reset => reset,\n" +
                        "				timer_clk => timer_clk,\n" +
                        "				irq => irq,\n" +
                        "				count_instruction => wb_count_instruction,\n" +
                        "				test_context_out => test_context_out,\n" +
                        "				read_address => csr_read_address,\n" +
                        "				read_data_out => csr_read_data,\n" +
                        "				write_address => wb_csr_address,\n" +
                        "				write_data_in => wb_csr_data,\n" +
                        "				write_mode => wb_csr_write,\n" +
                        "				exception_context => wb_exception_context,\n" +
                        "				exception_context_write => wb_exception,\n" +
                        "				mie_out => mie,\n" +
                        "				mtvec_out => mtvec,\n" +
                        "				ie_out => ie,\n" +
                        "				ie1_out => ie1,\n" +
                        "				software_interrupt_out => software_interrupt,\n" +
                        "				timer_interrupt_out => timer_interrupt\n" +
                        "			);\n" +
                        "\n" +
                        "	csr_read_address <= id_csr_address when stall_ex = '0' else csr_read_address_p;\n" +
                        "	store_previous_csr_addr: process(clk, stall_ex)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) and stall_ex = '0' then\n" +
                        "			csr_read_address_p <= id_csr_address;\n" +
                        "		end if;\n" +
                        "	end process store_previous_csr_addr;\n" +
                        "\n" +
                        "	------- Register file -------\n" +
                        "	regfile: entity work.pp_register_file\n" +
                        "			port map(\n" +
                        "				clk => clk,\n" +
                        "				rs1_addr => rs1_address,\n" +
                        "				rs2_addr => rs2_address,\n" +
                        "				rs1_data => rs1_data,\n" +
                        "				rs2_data => rs2_data,\n" +
                        "				rd_addr => wb_rd_address,\n" +
                        "				rd_data => wb_rd_data,\n" +
                        "				rd_write => wb_rd_write\n" +
                        "			);\n" +
                        "\n" +
                        "	rs1_address <= id_rs1_address when stall_ex = '0' else rs1_address_p;\n" +
                        "	rs2_address <= id_rs2_address when stall_ex = '0' else rs2_address_p;\n" +
                        "\n" +
                        "	store_previous_rsaddr: process(clk, stall_ex)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) and stall_ex = '0' then\n" +
                        "			rs1_address_p <= id_rs1_address;\n" +
                        "			rs2_address_p <= id_rs2_address;\n" +
                        "		end if;\n" +
                        "	end process store_previous_rsaddr;\n" +
                        "\n" +
                        "	------- Instruction Fetch (IF) Stage -------\n" +
                        "	fetch: entity work.pp_fetch\n" +
                        "		generic map(\n" +
                        "			RESET_ADDRESS => RESET_ADDRESS\n" +
                        "		) port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			imem_address => imem_address,\n" +
                        "			imem_data_in => imem_data_in,\n" +
                        "			imem_req => imem_req,\n" +
                        "			imem_ack => imem_ack,\n" +
                        "			stall => stall_if,\n" +
                        "			flush => flush_if,\n" +
                        "			branch => branch_taken,\n" +
                        "			exception => exception_taken,\n" +
                        "			branch_target => branch_target,\n" +
                        "			evec => exception_target,\n" +
                        "			instruction_data => if_instruction,\n" +
                        "			instruction_address => if_pc,\n" +
                        "			instruction_ready => if_instruction_ready\n" +
                        "		);\n" +
                        "	if_count_instruction <= if_instruction_ready;\n" +
                        "\n" +
                        "	------- Instruction Decode (ID) Stage -------\n" +
                        "	decode: entity work.pp_decode\n" +
                        "		generic map(\n" +
                        "			RESET_ADDRESS => RESET_ADDRESS,\n" +
                        "			PROCESSOR_ID => PROCESSOR_ID\n" +
                        "		) port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			flush => flush_id,\n" +
                        "			stall => stall_id,\n" +
                        "			instruction_data => if_instruction,\n" +
                        "			instruction_address => if_pc,\n" +
                        "			instruction_ready => if_instruction_ready,\n" +
                        "			instruction_count => if_count_instruction,\n" +
                        "			funct3 => id_funct3,\n" +
                        "			rs1_addr => id_rs1_address,\n" +
                        "			rs2_addr => id_rs2_address,\n" +
                        "			rd_addr => id_rd_address,\n" +
                        "			csr_addr => id_csr_address,\n" +
                        "			shamt => id_shamt,\n" +
                        "			immediate => id_immediate,\n" +
                        "			rd_write => id_rd_write,\n" +
                        "			branch => id_branch,\n" +
                        "			alu_x_src => id_alu_x_src,\n" +
                        "			alu_y_src => id_alu_y_src,\n" +
                        "			alu_op => id_alu_op,\n" +
                        "			mem_op => id_mem_op,\n" +
                        "			mem_size => id_mem_size,\n" +
                        "			count_instruction => id_count_instruction,\n" +
                        "			pc => id_pc,\n" +
                        "			csr_write => id_csr_write,\n" +
                        "			csr_use_imm => id_csr_use_immediate,\n" +
                        "			decode_exception => id_exception,\n" +
                        "			decode_exception_cause => id_exception_cause\n" +
                        "		);\n" +
                        "\n" +
                        "	------- Execute (EX) Stage -------\n" +
                        "	execute: entity work.pp_execute\n" +
                        "		port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			stall => stall_ex,\n" +
                        "			flush => flush_ex,\n" +
                        "			irq => irq,\n" +
                        "			software_interrupt => software_interrupt,\n" +
                        "			timer_interrupt => timer_interrupt,\n" +
                        "			dmem_address => ex_dmem_address,\n" +
                        "			dmem_data_size => ex_dmem_data_size,\n" +
                        "			dmem_data_out => ex_dmem_data_out,\n" +
                        "			dmem_read_req => ex_dmem_read_req,\n" +
                        "			dmem_write_req => ex_dmem_write_req,\n" +
                        "			rs1_addr_in => rs1_address,\n" +
                        "			rs2_addr_in => rs2_address,\n" +
                        "			rd_addr_in => id_rd_address,\n" +
                        "			rd_addr_out => ex_rd_address,\n" +
                        "			rs1_data_in => rs1_data,\n" +
                        "			rs2_data_in => rs2_data,\n" +
                        "			shamt_in => id_shamt,\n" +
                        "			immediate_in => id_immediate,\n" +
                        "			funct3_in => id_funct3,\n" +
                        "			pc_in => id_pc,\n" +
                        "			pc_out => ex_pc,\n" +
                        "			csr_addr_in => csr_read_address,\n" +
                        "			csr_addr_out => ex_csr_address,\n" +
                        "			csr_write_in => id_csr_write,\n" +
                        "			csr_write_out => ex_csr_write,\n" +
                        "			csr_value_in => csr_read_data,\n" +
                        "			csr_value_out => ex_csr_data,\n" +
                        "			csr_use_immediate_in => id_csr_use_immediate,\n" +
                        "			alu_op_in => id_alu_op,\n" +
                        "			alu_x_src_in => id_alu_x_src,\n" +
                        "			alu_y_src_in => id_alu_y_src,\n" +
                        "			rd_write_in => id_rd_write,\n" +
                        "			rd_write_out => ex_rd_write,\n" +
                        "			rd_data_out => ex_rd_data,\n" +
                        "			branch_in => id_branch,\n" +
                        "			branch_out => ex_branch,\n" +
                        "			mem_op_in => id_mem_op,\n" +
                        "			mem_op_out => ex_mem_op,\n" +
                        "			mem_size_in => id_mem_size,\n" +
                        "			mem_size_out => ex_mem_size,\n" +
                        "			count_instruction_in => id_count_instruction,\n" +
                        "			count_instruction_out => ex_count_instruction,\n" +
                        "			ie_in => ie,\n" +
                        "			ie1_in => ie1,\n" +
                        "			mie_in => mie,\n" +
                        "			mtvec_in => mtvec,\n" +
                        "			mtvec_out => exception_target,\n" +
                        "			decode_exception_in => id_exception,\n" +
                        "			decode_exception_cause_in => id_exception_cause,\n" +
                        "			exception_out => exception_taken,\n" +
                        "			exception_context_out => ex_exception_context,\n" +
                        "			jump_out => branch_taken,\n" +
                        "			jump_target_out => branch_target,\n" +
                        "			mem_rd_write => mem_rd_write,\n" +
                        "			mem_rd_addr => mem_rd_address,\n" +
                        "			mem_rd_value => mem_rd_data,\n" +
                        "			mem_csr_addr => mem_csr_address,\n" +
                        "			mem_csr_write => mem_csr_write,\n" +
                        "			mem_exception => mem_exception,\n" +
                        "			wb_rd_write => wb_rd_write,\n" +
                        "			wb_rd_addr => wb_rd_address,\n" +
                        "			wb_rd_value => wb_rd_data,\n" +
                        "			wb_csr_addr => wb_csr_address,\n" +
                        "			wb_csr_write => wb_csr_write,\n" +
                        "			wb_exception => wb_exception,\n" +
                        "			mem_mem_op => mem_mem_op,\n" +
                        "			hazard_detected => hazard_detected\n" +
                        "		);\n" +
                        "\n" +
                        "	dmem_address <= ex_dmem_address when stall_mem = '0' else dmem_address_p;\n" +
                        "	dmem_data_size <= ex_dmem_data_size when stall_mem = '0' else dmem_data_size_p;\n" +
                        "	dmem_data_out <= ex_dmem_data_out when stall_mem = '0' else dmem_data_out_p;\n" +
                        "	dmem_read_req <= ex_dmem_read_req when stall_mem = '0' else dmem_read_req_p;\n" +
                        "	dmem_write_req <= ex_dmem_write_req when stall_mem = '0' else dmem_write_req_p;\n" +
                        "\n" +
                        "	store_previous_dmem_address: process(clk, stall_mem)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) and stall_mem = '0' then\n" +
                        "			dmem_address_p <= ex_dmem_address;\n" +
                        "			dmem_data_size_p <= ex_dmem_data_size;\n" +
                        "			dmem_data_out_p <= ex_dmem_data_out;\n" +
                        "			dmem_read_req_p <= ex_dmem_read_req;\n" +
                        "			dmem_write_req_p <= ex_dmem_write_req;\n" +
                        "		end if;\n" +
                        "	end process store_previous_dmem_address;\n" +
                        "\n" +
                        "	------- Memory (MEM) Stage -------\n" +
                        "	memory: entity work.pp_memory\n" +
                        "		port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			stall => stall_mem,\n" +
                        "			dmem_data_in => dmem_data_in,\n" +
                        "			dmem_read_ack => dmem_read_ack,\n" +
                        "			dmem_write_ack => dmem_write_ack,\n" +
                        "			pc => ex_pc,\n" +
                        "			rd_write_in => ex_rd_write,\n" +
                        "			rd_write_out => mem_rd_write,\n" +
                        "			rd_data_in => ex_rd_data,\n" +
                        "			rd_data_out => mem_rd_data,\n" +
                        "			rd_addr_in => ex_rd_address,\n" +
                        "			rd_addr_out => mem_rd_address,\n" +
                        "			branch => ex_branch,\n" +
                        "			mem_op_in => ex_mem_op,\n" +
                        "			mem_op_out => mem_mem_op,\n" +
                        "			mem_size_in => ex_mem_size,\n" +
                        "			count_instr_in => ex_count_instruction,\n" +
                        "			count_instr_out => mem_count_instruction,\n" +
                        "			exception_in => exception_taken,\n" +
                        "			exception_out => mem_exception, \n" +
                        "			exception_context_in => ex_exception_context,\n" +
                        "			exception_context_out => mem_exception_context,\n" +
                        "			csr_addr_in => ex_csr_address,\n" +
                        "			csr_addr_out => mem_csr_address,\n" +
                        "			csr_write_in => ex_csr_write,\n" +
                        "			csr_write_out => mem_csr_write,\n" +
                        "			csr_data_in => ex_csr_data,\n" +
                        "			csr_data_out => mem_csr_data\n" +
                        "		);\n" +
                        "\n" +
                        "	------- Writeback (WB) Stage -------\n" +
                        "	writeback: entity work.pp_writeback\n" +
                        "		port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			count_instr_in => mem_count_instruction,\n" +
                        "			count_instr_out => wb_count_instruction,\n" +
                        "			exception_ctx_in => mem_exception_context,\n" +
                        "			exception_ctx_out => wb_exception_context,\n" +
                        "			exception_in => mem_exception,\n" +
                        "			exception_out => wb_exception,\n" +
                        "			csr_write_in => mem_csr_write,\n" +
                        "			csr_write_out => wb_csr_write,\n" +
                        "			csr_data_in => mem_csr_data,\n" +
                        "			csr_data_out => wb_csr_data,\n" +
                        "			csr_addr_in => mem_csr_address,\n" +
                        "			csr_addr_out => wb_csr_address,\n" +
                        "			rd_addr_in => mem_rd_address,\n" +
                        "			rd_addr_out => wb_rd_address,\n" +
                        "			rd_write_in => mem_rd_write,\n" +
                        "			rd_write_out => wb_rd_write,\n" +
                        "			rd_data_in => mem_rd_data,\n" +
                        "			rd_data_out => wb_rd_data\n" +
                        "		);\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_csr_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_csr.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "--! @brief Package containing constants and utility functions relating to status and control registers.\n" +
                        "package pp_csr is\n" +
                        "\n" +
                        "	--! Type used for specifying control and status register addresses.\n" +
                        "	subtype csr_address is std_logic_vector(11 downto 0);\n" +
                        "\n" +
                        "	--! Type used for exception cause values.\n" +
                        "	subtype csr_exception_cause is std_logic_vector(5 downto 0); -- Upper bit is the interrupt bit\n" +
                        "\n" +
                        "	--! Converts an exception cause to a std_logic_vector.\n" +
                        "	function to_std_logic_vector(input : in csr_exception_cause) return std_logic_vector;\n" +
                        "\n" +
                        "	--! Control/status register write mode:\n" +
                        "	type csr_write_mode is (\n" +
                        "			CSR_WRITE_NONE, CSR_WRITE_SET, CSR_WRITE_CLEAR, CSR_WRITE_REPLACE\n" +
                        "		);\n" +
                        "\n" +
                        "	-- Exception cause values:\n" +
                        "	constant CSR_CAUSE_INSTR_MISALIGN : csr_exception_cause := b\"000000\";\n" +
                        "	constant CSR_CAUSE_INSTR_FETCH    : csr_exception_cause := b\"000001\";\n" +
                        "	constant CSR_CAUSE_INVALID_INSTR  : csr_exception_cause := b\"000010\";\n" +
                        "	constant CSR_CAUSE_BREAKPOINT     : csr_exception_cause := b\"000011\";\n" +
                        "	constant CSR_CAUSE_LOAD_MISALIGN  : csr_exception_cause := b\"000100\";\n" +
                        "	constant CSR_CAUSE_LOAD_ERROR     : csr_exception_cause := b\"000101\";\n" +
                        "	constant CSR_CAUSE_STORE_MISALIGN : csr_exception_cause := b\"000110\";\n" +
                        "	constant CSR_CAUSE_STORE_ERROR    : csr_exception_cause := b\"000111\";\n" +
                        "	constant CSR_CAUSE_ECALL          : csr_exception_cause := b\"001011\";\n" +
                        "	constant CSR_CAUSE_NONE           : csr_exception_cause := b\"011111\";\n" +
                        "\n" +
                        "	constant CSR_CAUSE_SOFTWARE_INT   : csr_exception_cause := b\"100000\";\n" +
                        "	constant CSR_CAUSE_TIMER_INT      : csr_exception_cause := b\"100001\";\n" +
                        "	constant CSR_CAUSE_IRQ_BASE       : csr_exception_cause := b\"110000\";\n" +
                        "\n" +
                        "	-- Control register IDs, specified in the immediate field of csr* instructions:\n" +
                        "	constant CSR_CYCLE    : csr_address := x\"c00\";\n" +
                        "	constant CSR_CYCLEH   : csr_address := x\"c80\";\n" +
                        "	constant CSR_TIME     : csr_address := x\"c01\";\n" +
                        "	constant CSR_TIMEH    : csr_address := x\"c81\";\n" +
                        "	constant CSR_INSTRET  : csr_address := x\"c02\";\n" +
                        "	constant CSR_INSTRETH : csr_address := x\"c82\";\n" +
                        "\n" +
                        "	constant CSR_MVENDORID : csr_address := x\"f11\";\n" +
                        "	constant CSR_MARCHID   : csr_address := x\"f12\";\n" +
                        "	constant CSR_MIMPID    : csr_address := x\"f13\";\n" +
                        "	constant CSR_MHARTID   : csr_address := x\"f14\";\n" +
                        "\n" +
                        "	constant CSR_MSTATUS  : csr_address := x\"300\";\n" +
                        "	constant CSR_MISA     : csr_address := x\"301\";\n" +
                        "	constant CSR_MTVEC    : csr_address := x\"305\";\n" +
                        "	constant CSR_MTDELEG  : csr_address := x\"302\";\n" +
                        "	constant CSR_MIE      : csr_address := x\"304\";\n" +
                        "\n" +
                        "	constant CSR_MTIMECMP : csr_address := x\"321\";\n" +
                        "	constant CSR_MTIME    : csr_address := x\"701\";\n" +
                        "\n" +
                        "	constant CSR_MSCRATCH : csr_address := x\"340\";\n" +
                        "	constant CSR_MEPC     : csr_address := x\"341\";\n" +
                        "	constant CSR_MCAUSE   : csr_address := x\"342\";\n" +
                        "	constant CSR_MBADADDR : csr_address := x\"343\";\n" +
                        "	constant CSR_MIP      : csr_address := x\"344\";\n" +
                        "\n" +
                        "	constant CSR_TEST : csr_address := x\"bf0\";\n" +
                        "\n" +
                        "	-- Values used as control register IDs in ERET:\n" +
                        "	constant CSR_EPC_MRET   : csr_address := x\"302\";\n" +
                        "\n" +
                        "	-- Status register bit indices:\n" +
                        "	constant CSR_SR_MIE_INDEX  : natural := 3;\n" +
                        "	constant CSR_SR_MPIE_INDEX : natural := 7;\n" +
                        "\n" +
                        "	-- MIE and MIP register bit indices:\n" +
                        "	constant CSR_MIE_MSIE : natural := 3;\n" +
                        "	constant CSR_MIE_MTIE : natural := 7;\n" +
                        "	constant CSR_MIP_MSIP : natural := CSR_MIE_MSIE;\n" +
                        "	constant CSR_MIP_MTIP : natural := CSR_MIE_MTIE;\n" +
                        "\n" +
                        "	-- Exception context; this record contains all state that can be manipulated\n" +
                        "	-- when an exception is taken.\n" +
                        "	type csr_exception_context is\n" +
                        "		record\n" +
                        "			ie, ie1 : std_logic; -- Enable Interrupt bits\n" +
                        "			cause   : csr_exception_cause;\n" +
                        "			badaddr : std_logic_vector(31 downto 0);\n" +
                        "		end record;\n" +
                        "\n" +
                        "	--! Creates the value of the mstatus registe from the EI and EI1 bits.\n" +
                        "	function csr_make_mstatus(mie, mpie : in std_logic) return std_logic_vector;\n" +
                        "\n" +
                        "end package pp_csr;\n" +
                        "\n" +
                        "package body pp_csr is\n" +
                        "\n" +
                        "	function to_std_logic_vector(input : in csr_exception_cause)\n" +
                        "		return std_logic_vector is\n" +
                        "	begin\n" +
                        "		return (31 => input(5), 30 downto 5 => '0') & input(4 downto 0);\n" +
                        "	end function to_std_logic_vector;\n" +
                        "\n" +
                        "	function csr_make_mstatus(mie, mpie : in std_logic) return std_logic_vector is\n" +
                        "		variable retval : std_logic_vector(31 downto 0);\n" +
                        "	begin\n" +
                        "		retval := (\n" +
                        "			CSR_SR_MIE_INDEX => mie,\n" +
                        "			CSR_SR_MPIE_INDEX => mpie,\n" +
                        "			others => '0');\n" +
                        "		return retval;\n" +
                        "	end function csr_make_mstatus;\n" +
                        "\n" +
                        "end package body pp_csr;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_icache_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_icache.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "--! @brief Simple read-only direct-mapped instruction cache.\n" +
                        "entity pp_icache is\n" +
                        "	generic(\n" +
                        "		LINE_SIZE    : natural := 4;   --! Number of words per cache line\n" +
                        "		NUM_LINES    : natural := 128  --! Number of lines in the cache\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		clk   : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- Memory interface:\n" +
                        "		mem_address_in   : in  std_logic_vector(31 downto 0);\n" +
                        "		mem_data_out     : out std_logic_vector(31 downto 0);\n" +
                        "		mem_read_req     : in  std_logic;\n" +
                        "		mem_read_ack     : out std_logic;\n" +
                        "\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_inputs  : in wishbone_master_inputs;\n" +
                        "		wb_outputs : out wishbone_master_outputs\n" +
                        "	);\n" +
                        "end entity pp_icache;\n" +
                        "\n" +
                        "architecture behaviour of pp_icache is\n" +
                        "\n" +
                        "	-- Counter types:\n" +
                        "	subtype line_counter_type is natural range 0 to NUM_LINES;\n" +
                        "	subtype word_counter_type is natural range 0 to LINE_SIZE; \n" +
                        "\n" +
                        "	-- Cache line types:\n" +
                        "	subtype cache_line_type is std_logic_vector((LINE_SIZE * 32) - 1 downto 0);\n" +
                        "	type cache_line_word_array is array(0 to LINE_SIZE - 1) of std_logic_vector(31 downto 0); \n" +
                        "	type cache_line_array is array(0 to NUM_LINES - 1) of cache_line_type;\n" +
                        "\n" +
                        "	-- Cache tag type:\n" +
                        "	subtype cache_tag_type is std_logic_vector(31 - log2(LINE_SIZE * 4) - log2(NUM_LINES) downto 0);\n" +
                        "	type cache_tag_array is array(0 to NUM_LINES - 1) of cache_tag_type;\n" +
                        "\n" +
                        "	-- Cache memories:\n" +
                        "	signal cache_memory : cache_line_array;\n" +
                        "	signal tag_memory   : cache_tag_array;\n" +
                        "	signal valid        : std_logic_vector(NUM_LINES - 1 downto 0) := (others => '0');\n" +
                        "\n" +
                        "	attribute ram_style : string;\n" +
                        "	attribute ram_style of cache_memory: signal is \"block\";\n" +
                        "\n" +
                        "	-- Cache controller signals:\n" +
                        "	type state_type is (IDLE, CACHE_READ_STALL,\n" +
                        "		LOAD_CACHELINE_START, LOAD_CACHELINE_WAIT_ACK, LOAD_CACHELINE_FINISH);\n" +
                        "	signal state : state_type := IDLE;\n" +
                        "\n" +
                        "	-- Input address components:\n" +
                        "	signal input_address_line : std_logic_vector(log2(NUM_LINES) - 1 downto 0);\n" +
                        "	signal input_address_word : std_logic_vector(log2(LINE_SIZE) - 1 downto 0);\n" +
                        "	signal input_address_tag  : cache_tag_type;\n" +
                        "\n" +
                        "	-- Cacheline matching the current input address:\n" +
                        "	signal current_cache_line, cache_lookup : cache_line_type;\n" +
                        "	signal current_cache_line_words         : cache_line_word_array;\n" +
                        "\n" +
                        "	-- Base address to load a cacheline from:\n" +
                        "	signal cl_load_address  : std_logic_vector(31 downto log2(LINE_SIZE * 4));\n" +
                        "	-- Cache line to load:\n" +
                        "	signal cl_current_line : line_counter_type;\n" +
                        "	-- Current word being loaded:\n" +
                        "	signal cl_current_word  : word_counter_type;\n" +
                        "\n" +
                        "	-- Buffer for holding a cache line while loading:\n" +
                        "	signal load_buffer     : cache_line_type;\n" +
                        "	signal load_buffer_tag : cache_tag_type;\n" +
                        "\n" +
                        "	-- Causes a cache line to be stored in the cache memory:\n" +
                        "	signal store_cache_line : std_logic;\n" +
                        "\n" +
                        "	-- Set when the current input address matches a cache line:\n" +
                        "	signal cache_hit : std_logic;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	assert is_pow2(LINE_SIZE) report \"Cache line size must be a power of 2!\" severity FAILURE;\n" +
                        "	assert is_pow2(NUM_LINES) report \"Number of cache lines must be a power of 2!\" severity FAILURE;\n" +
                        "\n" +
                        "	mem_data_out <= current_cache_line_words(to_integer(unsigned(input_address_word)));\n" +
                        "	mem_read_ack <= (cache_hit and mem_read_req) when state = IDLE or state = CACHE_READ_STALL else '0';\n" +
                        "\n" +
                        "	input_address_line <= mem_address_in(log2(LINE_SIZE * 4) + log2(NUM_LINES) - 1 downto log2(LINE_SIZE * 4));\n" +
                        "	input_address_tag  <= mem_address_in(31 downto log2(LINE_SIZE * 4) + log2(NUM_LINES));\n" +
                        "\n" +
                        "	decompose_cache_line: for i in 0 to LINE_SIZE - 1 generate\n" +
                        "		current_cache_line_words(i) <= current_cache_line(32 * i + 31 downto 32 * i);\n" +
                        "	end generate decompose_cache_line;\n" +
                        "\n" +
                        "	find_indices: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			input_address_word <= mem_address_in(log2(LINE_SIZE * 4) - 1 downto 2);\n" +
                        "		end if;\n" +
                        "	end process find_indices;\n" +
                        "\n" +
                        "	cacheline_lookup: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if store_cache_line = '1' then\n" +
                        "				cache_memory(cl_current_line) <= load_buffer;\n" +
                        "			end if;\n" +
                        "\n" +
                        "			current_cache_line <= cache_memory(to_integer(unsigned(input_address_line)));\n" +
                        "		end if;\n" +
                        "	end process cacheline_lookup;\n" +
                        "\n" +
                        "	tag_lookup: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if store_cache_line = '1' then\n" +
                        "				tag_memory(cl_current_line) <= load_buffer_tag;\n" +
                        "			end if;\n" +
                        "\n" +
                        "			cache_hit <= valid(to_integer(unsigned(input_address_line)))\n" +
                        "				and to_std_logic(tag_memory(to_integer(unsigned(input_address_line))) = input_address_tag);\n" +
                        "		end if;\n" +
                        "	end process tag_lookup;\n" +
                        "\n" +
                        "	controller: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				state <= IDLE;\n" +
                        "				wb_outputs.cyc <= '0';\n" +
                        "				wb_outputs.stb <= '0';\n" +
                        "				store_cache_line <= '0';\n" +
                        "				valid <= (others => '0');\n" +
                        "			else\n" +
                        "				case state is\n" +
                        "					when IDLE =>\n" +
                        "						if mem_read_req = '1' and cache_hit = '0' then\n" +
                        "							wb_outputs.adr <= mem_address_in(31 downto log2(LINE_SIZE * 4)) & (log2(LINE_SIZE * 4) - 1 downto 0 => '0');\n" +
                        "							wb_outputs.cyc <= '1';\n" +
                        "							wb_outputs.we <= '0';\n" +
                        "							wb_outputs.sel <= (others => '1');\n" +
                        "							load_buffer_tag <= input_address_tag;\n" +
                        "							cl_load_address <= mem_address_in(31 downto log2(LINE_SIZE * 4));\n" +
                        "							cl_current_line <= to_integer(unsigned(input_address_line));\n" +
                        "							cl_current_word <= 0;\n" +
                        "							state <= LOAD_CACHELINE_START;\n" +
                        "						end if;\n" +
                        "					when CACHE_READ_STALL =>\n" +
                        "						state <= IDLE;\n" +
                        "					when LOAD_CACHELINE_START =>\n" +
                        "						wb_outputs.stb <= '1';\n" +
                        "						wb_outputs.we <= '0';\n" +
                        "						wb_outputs.adr <= cl_load_address & std_logic_vector(to_unsigned(cl_current_word, log2(LINE_SIZE))) & b\"00\";\n" +
                        "						state <= LOAD_CACHELINE_WAIT_ACK;\n" +
                        "					when LOAD_CACHELINE_WAIT_ACK =>\n" +
                        "						if wb_inputs.ack = '1' then\n" +
                        "							wb_outputs.stb <= '0';\n" +
                        "							load_buffer(cl_current_word * 32 + 31 downto cl_current_word * 32) <= wb_inputs.dat;\n" +
                        "							if natural(cl_current_word) = LINE_SIZE - 1 then\n" +
                        "								wb_outputs.cyc <= '0';\n" +
                        "								store_cache_line <= '1';\n" +
                        "								state <= LOAD_CACHELINE_FINISH;\n" +
                        "							else\n" +
                        "								cl_current_word <= cl_current_word + 1;\n" +
                        "								state <= LOAD_CACHELINE_START;\n" +
                        "							end if;\n" +
                        "						end if;\n" +
                        "					when LOAD_CACHELINE_FINISH =>\n" +
                        "						store_cache_line <= '0';\n" +
                        "						valid(cl_current_line) <= '1';\n" +
                        "						state <= CACHE_READ_STALL;\n" +
                        "				end case;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process controller;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_wb_adapter_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_wb_adapter.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "--! @brief Wishbone adapter, for connecting the processor to a Wishbone bus when not using caches.\n" +
                        "entity pp_wb_adapter is\n" +
                        "	port(\n" +
                        "		clk   : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- Processor data memory signals:\n" +
                        "		signal mem_address   : in  std_logic_vector(31 downto 0);\n" +
                        "		signal mem_data_in   : in  std_logic_vector(31 downto 0); -- Data in from the bus\n" +
                        "		signal mem_data_out  : out std_logic_vector(31 downto 0); -- Data out to the bus\n" +
                        "		signal mem_data_size : in  std_logic_vector( 1 downto 0);\n" +
                        "		signal mem_read_req  : in  std_logic;\n" +
                        "		signal mem_read_ack  : out std_logic;\n" +
                        "		signal mem_write_req : in  std_logic;\n" +
                        "		signal mem_write_ack : out std_logic;\n" +
                        "\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_inputs  : in wishbone_master_inputs;\n" +
                        "		wb_outputs : out wishbone_master_outputs\n" +
                        "	);\n" +
                        "end entity pp_wb_adapter;\n" +
                        "\n" +
                        "architecture behaviour of pp_wb_adapter is\n" +
                        "\n" +
                        "	type states is (IDLE, READ_WAIT_ACK, WRITE_WAIT_ACK);\n" +
                        "	signal state : states;\n" +
                        "\n" +
                        "	signal mem_r_ack : std_logic;\n" +
                        "\n" +
                        "	function get_data_shift(size : in std_logic_vector(1 downto 0); address : in std_logic_vector)\n" +
                        "		return natural is\n" +
                        "	begin\n" +
                        "		case size is\n" +
                        "			when b\"01\" =>\n" +
                        "				case address(1 downto 0) is\n" +
                        "					when b\"00\" =>\n" +
                        "						return 0;\n" +
                        "					when b\"01\" =>\n" +
                        "						return 8;\n" +
                        "					when b\"10\" =>\n" +
                        "						return 16;\n" +
                        "					when b\"11\" =>\n" +
                        "						return 24;\n" +
                        "					when others =>\n" +
                        "						return 0;\n" +
                        "				end case;\n" +
                        "			when b\"10\" =>\n" +
                        "				if address(1) = '0' then\n" +
                        "					return 0;\n" +
                        "				else\n" +
                        "					return 16;\n" +
                        "				end if;\n" +
                        "			when others =>\n" +
                        "				return 0;\n" +
                        "		end case;\n" +
                        "	end function get_data_shift;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	mem_write_ack <= '1' when state = WRITE_WAIT_ACK and wb_inputs.ack = '1' else '0';\n" +
                        "	mem_read_ack <= mem_r_ack;\n" +
                        "\n" +
                        "	wishbone: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				state <= IDLE;\n" +
                        "				wb_outputs.cyc <= '0';\n" +
                        "				wb_outputs.stb <= '0';\n" +
                        "				mem_r_ack <= '0';\n" +
                        "			else\n" +
                        "				case state is\n" +
                        "					when IDLE =>\n" +
                        "						mem_r_ack <= '0';\n" +
                        "\n" +
                        "						-- Prioritize requests from the data memory:\n" +
                        "						if mem_write_req = '1' then\n" +
                        "							wb_outputs.adr <= mem_address;\n" +
                        "							wb_outputs.dat <= std_logic_vector(shift_left(unsigned(mem_data_in),\n" +
                        "								get_data_shift(mem_data_size, mem_address)));\n" +
                        "							wb_outputs.sel <= wb_get_data_sel(mem_data_size, mem_address);\n" +
                        "							wb_outputs.cyc <= '1';\n" +
                        "							wb_outputs.stb <= '1';\n" +
                        "							wb_outputs.we <= '1';\n" +
                        "							state <= WRITE_WAIT_ACK;\n" +
                        "						elsif mem_read_req = '1' then\n" +
                        "							wb_outputs.adr <= mem_address;\n" +
                        "							wb_outputs.sel <= wb_get_data_sel(mem_data_size, mem_address);\n" +
                        "							wb_outputs.cyc <= '1';\n" +
                        "							wb_outputs.stb <= '1';\n" +
                        "							wb_outputs.we <= '0';\n" +
                        "							state <= READ_WAIT_ACK;\n" +
                        "						end if;\n" +
                        "					when READ_WAIT_ACK =>\n" +
                        "						if wb_inputs.ack = '1' then\n" +
                        "							mem_data_out <= std_logic_vector(shift_right(unsigned(wb_inputs.dat),\n" +
                        "								get_data_shift(mem_data_size, mem_address)));\n" +
                        "							wb_outputs.cyc <= '0';\n" +
                        "							wb_outputs.stb <= '0';\n" +
                        "							mem_r_ack <= '1';\n" +
                        "							state <= IDLE;\n" +
                        "						end if;\n" +
                        "					when WRITE_WAIT_ACK =>\n" +
                        "						if wb_inputs.ack = '1' then\n" +
                        "							wb_outputs.cyc <= '0';\n" +
                        "							wb_outputs.stb <= '0';\n" +
                        "							wb_outputs.we <= '0';\n" +
                        "							state <= IDLE;\n" +
                        "						end if;\n" +
                        "				end case;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process wishbone;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_wb_arbiter_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_wb_arbiter.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "\n" +
                        "--! @brief Simple priority-based wishbone arbiter.\n" +
                        "--! This module is used as an arbiter between the instruction and data caches.\n" +
                        "entity pp_wb_arbiter is\n" +
                        "	port(\n" +
                        "		clk   : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- Wishbone input 1:\n" +
                        "		m1_inputs  : out wishbone_master_inputs;\n" +
                        "		m1_outputs : in  wishbone_master_outputs;\n" +
                        "\n" +
                        "		-- Wishbone input 2:\n" +
                        "		m2_inputs  : out wishbone_master_inputs;\n" +
                        "		m2_outputs : in  wishbone_master_outputs;\n" +
                        "\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_adr_out : out std_logic_vector(31 downto 0);\n" +
                        "		wb_sel_out : out std_logic_vector( 3 downto 0);\n" +
                        "		wb_cyc_out : out std_logic;\n" +
                        "		wb_stb_out : out std_logic;\n" +
                        "		wb_we_out  : out std_logic;\n" +
                        "		wb_dat_out : out std_logic_vector(31 downto 0);\n" +
                        "		wb_dat_in  : in  std_logic_vector(31 downto 0);\n" +
                        "		wb_ack_in  : in  std_logic\n" +
                        "	);\n" +
                        "end entity pp_wb_arbiter;\n" +
                        "\n" +
                        "architecture behaviour of pp_wb_arbiter is\n" +
                        "\n" +
                        "	type state_type is (IDLE, M1_BUSY, M2_BUSY);\n" +
                        "	signal state : state_type := IDLE;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	m1_inputs <= (ack => wb_ack_in, dat => wb_dat_in) when state = M1_BUSY else (ack => '0', dat => (others => '0'));\n" +
                        "	m2_inputs <= (ack => wb_ack_in, dat => wb_dat_in) when state = M2_BUSY else (ack => '0', dat => (others => '0'));\n" +
                        "\n" +
                        "	output_mux: process(state, m1_outputs, m2_outputs)\n" +
                        "	begin\n" +
                        "		case state is\n" +
                        "			when IDLE =>\n" +
                        "				wb_adr_out <= (others => '0');\n" +
                        "				wb_sel_out <= (others => '0');\n" +
                        "				wb_dat_out <= (others => '0');\n" +
                        "				wb_cyc_out <= '0';\n" +
                        "				wb_stb_out <= '0';\n" +
                        "				wb_we_out <= '0';\n" +
                        "			when M1_BUSY =>\n" +
                        "				wb_adr_out <= m1_outputs.adr;\n" +
                        "				wb_sel_out <= m1_outputs.sel;\n" +
                        "				wb_dat_out <= m1_outputs.dat;\n" +
                        "				wb_cyc_out <= m1_outputs.cyc;\n" +
                        "				wb_stb_out <= m1_outputs.stb;\n" +
                        "				wb_we_out <= m1_outputs.we;\n" +
                        "			when M2_BUSY =>\n" +
                        "				wb_adr_out <= m2_outputs.adr;\n" +
                        "				wb_sel_out <= m2_outputs.sel;\n" +
                        "				wb_dat_out <= m2_outputs.dat;\n" +
                        "				wb_cyc_out <= m2_outputs.cyc;\n" +
                        "				wb_stb_out <= m2_outputs.stb;\n" +
                        "				wb_we_out <= m2_outputs.we;\n" +
                        "		end case;\n" +
                        "	end process output_mux;\n" +
                        "\n" +
                        "	controller: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				state <= IDLE;\n" +
                        "			else\n" +
                        "				case state is\n" +
                        "					when IDLE =>\n" +
                        "						if m1_outputs.cyc = '1' then\n" +
                        "							state <= M1_BUSY;\n" +
                        "						elsif m2_outputs.cyc = '1' then\n" +
                        "							state <= M2_BUSY;\n" +
                        "						end if;\n" +
                        "					when M1_BUSY =>\n" +
                        "						if m1_outputs.cyc = '0' then\n" +
                        "							state <= IDLE;\n" +
                        "						end if;\n" +
                        "					when M2_BUSY =>\n" +
                        "						if m2_outputs.cyc = '0' then\n" +
                        "							state <= IDLE;\n" +
                        "						end if;\n" +
                        "				end case;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process controller;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_csr_unit_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_csr_unit.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_csr.all;\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "--! @brief Control and status unit.\n" +
                        "entity pp_csr_unit is\n" +
                        "	generic(\n" +
                        "		PROCESSOR_ID  : std_logic_vector(31 downto 0);\n" +
                        "		MTIME_DIVIDER : positive := 5 --! Divider for the clock driving the MTIME counter.\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		clk       : in std_logic;\n" +
                        "		timer_clk : in std_logic;\n" +
                        "		reset     : in std_logic;\n" +
                        "\n" +
                        "		-- IRQ signals:\n" +
                        "		irq : in std_logic_vector(7 downto 0);\n" +
                        "\n" +
                        "		-- Count retired instruction:\n" +
                        "		count_instruction : in std_logic;\n" +
                        "\n" +
                        "		-- Test interface:\n" +
                        "		test_context_out : out test_context;\n" +
                        "\n" +
                        "		-- Read port:\n" +
                        "		read_address   : in csr_address;\n" +
                        "		read_data_out  : out std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Write port:\n" +
                        "		write_address : in csr_address;\n" +
                        "		write_data_in : in std_logic_vector(31 downto 0);\n" +
                        "		write_mode    : in csr_write_mode;\n" +
                        "\n" +
                        "		-- Exception context write port:\n" +
                        "		exception_context       : in csr_exception_context;\n" +
                        "		exception_context_write : in std_logic;\n" +
                        "\n" +
                        "		-- Interrupts originating from this unit:\n" +
                        "		software_interrupt_out : out std_logic;\n" +
                        "		timer_interrupt_out    : out std_logic;\n" +
                        "\n" +
                        "		-- Registers needed for exception handling, always read:\n" +
                        "		mie_out         : out std_logic_vector(31 downto 0);\n" +
                        "		mtvec_out       : out std_logic_vector(31 downto 0);\n" +
                        "		ie_out, ie1_out : out std_logic\n" +
                        "	);\n" +
                        "end entity pp_csr_unit;\n" +
                        "\n" +
                        "architecture behaviour of pp_csr_unit is\n" +
                        "\n" +
                        "	-- Counters:\n" +
                        "	signal counter_time    : std_logic_vector(63 downto 0);\n" +
                        "	signal counter_cycle   : std_logic_vector(63 downto 0);\n" +
                        "	signal counter_instret : std_logic_vector(63 downto 0);\n" +
                        "\n" +
                        "	-- Machine time counter:\n" +
                        "	signal mtime_clock_counter : natural := 0;\n" +
                        "	signal counter_mtime       : std_logic_vector(31 downto 0);\n" +
                        "	signal mtime_compare       : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "	-- Machine-mode registers:\n" +
                        "	signal mcause   : csr_exception_cause;\n" +
                        "	signal mbadaddr : std_logic_vector(31 downto 0);\n" +
                        "	signal mscratch : std_logic_vector(31 downto 0);\n" +
                        "	signal mepc     : std_logic_vector(31 downto 0);\n" +
                        "	signal mtvec    : std_logic_vector(31 downto 2) := (others => '0');\n" +
                        "	signal mie      : std_logic_vector(31 downto 0) := (others => '0');\n" +
                        "\n" +
                        "	-- Interrupt enable bits:\n" +
                        "	signal ie, ie1    : std_logic;\n" +
                        "\n" +
                        "	-- Test and debug register:\n" +
                        "	signal test_register : test_context;\n" +
                        "\n" +
                        "	-- Interrupt signals:\n" +
                        "	signal timer_interrupt    : std_logic;\n" +
                        "	signal software_interrupt : std_logic;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	-- Interrupt signals:\n" +
                        "	software_interrupt_out <= software_interrupt;\n" +
                        "	timer_interrupt_out <= timer_interrupt;\n" +
                        "	ie_out <= ie;\n" +
                        "	ie1_out <= ie1;\n" +
                        "	mie_out <= mie;\n" +
                        "\n" +
                        "	--! Output the current test state:\n" +
                        "	test_context_out <= test_register;\n" +
                        "\n" +
                        "	mtime_counter: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				mtime_clock_counter <= 0;\n" +
                        "				counter_mtime <= (others => '0');\n" +
                        "			else\n" +
                        "				if mtime_clock_counter = MTIME_DIVIDER - 1 then\n" +
                        "					mtime_clock_counter <= 0;\n" +
                        "					counter_mtime <= std_logic_vector(unsigned(counter_mtime) + 1);\n" +
                        "				else\n" +
                        "					mtime_clock_counter <= mtime_clock_counter + 1;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process mtime_counter;\n" +
                        "\n" +
                        "	mtime_interrupt: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				timer_interrupt <= '0';\n" +
                        "			else\n" +
                        "				if write_mode /= CSR_WRITE_NONE and write_address = CSR_MTIMECMP then\n" +
                        "					timer_interrupt <= '0';\n" +
                        "				elsif counter_mtime = mtime_compare then\n" +
                        "					timer_interrupt <= '1';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process mtime_interrupt;\n" +
                        "\n" +
                        "	write: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				software_interrupt <= '0';\n" +
                        "				mtvec <= (others => '0');\n" +
                        "				mepc <= (others => '0');\n" +
                        "				mie <= (others => '0');\n" +
                        "				ie <= '0';\n" +
                        "				ie1 <= '0';\n" +
                        "				test_register <= (TEST_IDLE, (others => '0'));\n" +
                        "			else\n" +
                        "				if exception_context_write = '1' then\n" +
                        "					ie <= exception_context.ie;\n" +
                        "					ie1 <= exception_context.ie1;\n" +
                        "					mcause <= exception_context.cause;\n" +
                        "					mbadaddr <= exception_context.badaddr;\n" +
                        "				end if;\n" +
                        "\n" +
                        "				if write_mode /= CSR_WRITE_NONE then\n" +
                        "					case write_address is\n" +
                        "						when CSR_MSTATUS => -- Status register\n" +
                        "							ie1 <= write_data_in(CSR_SR_MPIE_INDEX);\n" +
                        "							ie <= write_data_in(CSR_SR_MIE_INDEX);\n" +
                        "						when CSR_MSCRATCH => -- Scratch register\n" +
                        "							mscratch <= write_data_in;\n" +
                        "						when CSR_MEPC => -- Exception return address\n" +
                        "							mepc <= write_data_in;\n" +
                        "						--when CSR_MCAUSE => -- Exception cause\n" +
                        "						--	mcause <= write_data_in(31) & write_data_in(4 downto 0);\n" +
                        "						when CSR_MTVEC => -- Exception vector address\n" +
                        "							mtvec <= write_data_in(31 downto 2);\n" +
                        "						when CSR_MTIMECMP => -- Time compare register\n" +
                        "							mtime_compare <= write_data_in;\n" +
                        "						when CSR_MIE => -- Interrupt enable register:\n" +
                        "							mie <= write_data_in;\n" +
                        "						when CSR_MIP => -- Interrupt pending register:\n" +
                        "							software_interrupt <= write_data_in(CSR_MIP_MSIP);\n" +
                        "						when CSR_TEST => -- Test and debug register:\n" +
                        "							test_register <= std_logic_to_test_context(write_data_in);\n" +
                        "						when others =>\n" +
                        "							-- Ignore writes to invalid or read-only registers\n" +
                        "					end case;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process write;\n" +
                        "\n" +
                        "	read: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "\n" +
                        "			if write_mode /= CSR_WRITE_NONE and write_address = CSR_MTVEC then\n" +
                        "				mtvec_out <= write_data_in(31 downto 2) & b\"00\";\n" +
                        "			else\n" +
                        "				mtvec_out <= mtvec & b\"00\";\n" +
                        "			end if;\n" +
                        "\n" +
                        "			if write_mode /= CSR_WRITE_NONE and write_address = read_address then\n" +
                        "				read_data_out <= write_data_in;\n" +
                        "			else\n" +
                        "				case read_address is\n" +
                        "\n" +
                        "					-- Machine mode registers:\n" +
                        "					when CSR_MSTATUS => -- Status register\n" +
                        "						read_data_out <= csr_make_mstatus(ie, ie1);\n" +
                        "					when CSR_MSCRATCH => -- Scratch register\n" +
                        "						read_data_out <= mscratch;\n" +
                        "					when CSR_MEPC => -- Exception PC value\n" +
                        "						read_data_out <= mepc;\n" +
                        "					when CSR_MTVEC => -- Exception vector address\n" +
                        "						read_data_out <= mtvec & b\"00\";\n" +
                        "					when CSR_MTDELEG => -- Exception vector delegation register, unsupported\n" +
                        "						read_data_out <= (others => '0');\n" +
                        "					when CSR_MIP => -- Interrupt pending\n" +
                        "						read_data_out <= irq & (CSR_MIP_MTIP => timer_interrupt, CSR_MIP_MSIP => software_interrupt,\n" +
                        "							23 downto 8 => '0', 6 downto 4 => '0', 2 downto 0 => '0');\n" +
                        "					when CSR_MIE => -- Interrupt enable register\n" +
                        "						read_data_out <= mie;\n" +
                        "					when CSR_MBADADDR => -- Bad memory address\n" +
                        "						read_data_out <= mbadaddr;\n" +
                        "					when CSR_MCAUSE => -- Exception cause\n" +
                        "						read_data_out <= mcause(5) & (30 downto 5 => '0') & mcause(4 downto 0); --to_std_logic_vector(mcause);\n" +
                        "\n" +
                        "					-- Machine information registers:\n" +
                        "					when CSR_MISA => -- ISA features register\n" +
                        "						read_data_out <= (\n" +
                        "								30 => '1', -- Set the MXL0 bit, indicating XLEN = 32\n" +
                        "								 8 => '1', -- Set the bit corresponding to I (RV32I)\n" +
                        "								others => '0');\n" +
                        "					when CSR_MVENDORID => -- Vendor ID\n" +
                        "						read_data_out <= (others => '0'); -- Use 0 to indicate a non-commercial implementation\n" +
                        "					when CSR_MARCHID => -- Architecture ID\n" +
                        "						read_data_out <= (others => '0'); -- Use 0 to indicate an unsupported field.\n" +
                        "					when CSR_MIMPID => -- Implementation ID\n" +
                        "						read_data_out <= x\"47495400\"; -- Source of this implementation: 'G', 'I', 'T', 0\n" +
                        "					when CSR_MHARTID => -- Hardware thread ID\n" +
                        "						read_data_out <= PROCESSOR_ID;\n" +
                        "\n" +
                        "					-- Timers and counters:\n" +
                        "					when CSR_MTIME => -- Machine time counter register\n" +
                        "						read_data_out <= counter_mtime;\n" +
                        "					when CSR_MTIMECMP => -- Machine time compare register\n" +
                        "						read_data_out <= mtime_compare;\n" +
                        "\n" +
                        "					when CSR_TIME =>\n" +
                        "						read_data_out <= counter_time(31 downto 0);\n" +
                        "					when CSR_TIMEH =>\n" +
                        "						read_data_out <= counter_time(63 downto 32);\n" +
                        "					when CSR_CYCLE =>\n" +
                        "						read_data_out <= counter_cycle(31 downto 0);\n" +
                        "					when CSR_CYCLEH =>\n" +
                        "						read_data_out <= counter_cycle(63 downto 32);\n" +
                        "					when CSR_INSTRET =>\n" +
                        "						read_data_out <= counter_instret(31 downto 0);\n" +
                        "					when CSR_INSTRETH =>\n" +
                        "						read_data_out <= counter_instret(63 downto 32);\n" +
                        "\n" +
                        "					-- Potato extensions:\n" +
                        "					when CSR_TEST =>\n" +
                        "						read_data_out <= test_context_to_std_logic(test_register);\n" +
                        "\n" +
                        "					-- Return zero from write-only registers and invalid register addresses:\n" +
                        "					when others =>\n" +
                        "						read_data_out <= (others => '0');\n" +
                        "				end case;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process read;\n" +
                        "\n" +
                        "	timer_counter: entity work.pp_counter\n" +
                        "		port map(\n" +
                        "			clk => timer_clk,\n" +
                        "			reset => reset,\n" +
                        "			count => counter_time,\n" +
                        "			increment => '1'\n" +
                        "		);\n" +
                        "\n" +
                        "	cycle_counter: entity work.pp_counter\n" +
                        "		port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			count => counter_cycle,\n" +
                        "			increment => '1'\n" +
                        "		);\n" +
                        "\n" +
                        "	instret_counter: entity work.pp_counter\n" +
                        "		port map(\n" +
                        "			clk => clk,\n" +
                        "			reset => reset,\n" +
                        "			count => counter_instret,\n" +
                        "			increment => count_instruction\n" +
                        "		);\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void generate_pp_counter_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_counter.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 -2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "entity pp_counter is\n" +
                        "	generic(\n" +
                        "		COUNTER_WIDTH : natural := 64;\n" +
                        "		COUNTER_STEP  : natural :=  1\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		clk   : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "		\n" +
                        "		count     : out std_logic_vector(COUNTER_WIDTH - 1 downto 0);\n" +
                        "		increment : in std_logic\n" +
                        "	);\n" +
                        "end entity pp_counter;\n" +
                        "\n" +
                        "architecture behaviour of pp_counter is\n" +
                        "	signal current_count : std_logic_vector(COUNTER_WIDTH - 1 downto 0);\n" +
                        "begin\n" +
                        "\n" +
                        "	count <= current_count;\n" +
                        "\n" +
                        "	counter: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				current_count <= (others => '0');\n" +
                        "			elsif increment = '1' then\n" +
                        "				current_count <= std_logic_vector(unsigned(current_count) + COUNTER_STEP);\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process counter;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_register_file_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_register_file.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "--! @brief 32-bit RISC-V register file.\n" +
                        "entity pp_register_file is\n" +
                        "	port(\n" +
                        "		clk    : in std_logic;\n" +
                        "\n" +
                        "		-- Read port 1:\n" +
                        "		rs1_addr : in  register_address;\n" +
                        "		rs1_data : out std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Read port 2:\n" +
                        "		rs2_addr : in  register_address;\n" +
                        "		rs2_data : out std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Write port:\n" +
                        "		rd_addr  : in register_address;\n" +
                        "		rd_data  : in std_logic_vector(31 downto 0);\n" +
                        "		rd_write : in std_logic\n" +
                        "	);\n" +
                        "end entity pp_register_file;\n" +
                        "\n" +
                        "architecture behaviour of pp_register_file is\n" +
                        "\n" +
                        "	--! Register array type.\n" +
                        "	type regfile_array is array(0 to 31) of std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	regfile: process(clk)\n" +
                        "		variable registers : regfile_array := (others => (others => '0'));\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "				if rd_write = '1' and rd_addr /= b\"00000\" then\n" +
                        "					registers(to_integer(unsigned(rd_addr))) := rd_data;\n" +
                        "				end if;\n" +
                        "\n" +
                        "				rs1_data <= registers(to_integer(unsigned(rs1_addr)));\n" +
                        "				rs2_data <= registers(to_integer(unsigned(rs2_addr)));\n" +
                        "		end if;\n" +
                        "	end process regfile;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_fetch_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_fetch.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_constants.all;\n" +
                        "\n" +
                        "--! @brief Instruction fetch unit.\n" +
                        "entity pp_fetch is\n" +
                        "	generic(\n" +
                        "		RESET_ADDRESS : std_logic_vector(31 downto 0)\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		clk    : in std_logic;\n" +
                        "		reset  : in std_logic;\n" +
                        "\n" +
                        "		-- Instruction memory connections:\n" +
                        "		imem_address : out std_logic_vector(31 downto 0);\n" +
                        "		imem_data_in : in  std_logic_vector(31 downto 0);\n" +
                        "		imem_req     : out std_logic;\n" +
                        "		imem_ack     : in  std_logic;\n" +
                        "\n" +
                        "		-- Control inputs:\n" +
                        "		stall     : in std_logic;\n" +
                        "		flush     : in std_logic;\n" +
                        "		branch    : in std_logic;\n" +
                        "		exception : in std_logic;\n" +
                        "\n" +
                        "		branch_target : in std_logic_vector(31 downto 0);\n" +
                        "		evec          : in std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Outputs to the instruction decode unit:\n" +
                        "		instruction_data    : out std_logic_vector(31 downto 0);\n" +
                        "		instruction_address : out std_logic_vector(31 downto 0);\n" +
                        "		instruction_ready   : out std_logic\n" +
                        "	);\n" +
                        "end entity pp_fetch;\n" +
                        "\n" +
                        "architecture behaviour of pp_fetch is\n" +
                        "	signal pc           : std_logic_vector(31 downto 0);\n" +
                        "	signal pc_next      : std_logic_vector(31 downto 0);\n" +
                        "	signal cancel_fetch : std_logic;\n" +
                        "begin\n" +
                        "\n" +
                        "	imem_address <= pc_next when cancel_fetch = '0' else pc;\n" +
                        "\n" +
                        "	instruction_data <= imem_data_in;\n" +
                        "	instruction_ready <= imem_ack and (not stall) and (not cancel_fetch);\n" +
                        "	instruction_address <= pc;\n" +
                        "\n" +
                        "	imem_req <= not reset;\n" +
                        "\n" +
                        "	set_pc: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				pc <= RESET_ADDRESS;\n" +
                        "				cancel_fetch <= '0';\n" +
                        "			else\n" +
                        "				if (exception = '1' or branch = '1') and imem_ack = '0' then\n" +
                        "					cancel_fetch <= '1';\n" +
                        "					pc <= pc_next;\n" +
                        "				elsif cancel_fetch = '1' and imem_ack = '1' then\n" +
                        "					cancel_fetch <= '0';\n" +
                        "				else\n" +
                        "					pc <= pc_next;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process set_pc;\n" +
                        "\n" +
                        "	calc_next_pc: process(reset, stall, branch, exception, imem_ack, branch_target, evec, pc, cancel_fetch)\n" +
                        "	begin\n" +
                        "		if exception = '1' then\n" +
                        "			pc_next <= evec;\n" +
                        "		elsif branch = '1' then\n" +
                        "			pc_next <= branch_target;\n" +
                        "		elsif imem_ack = '1' and stall = '0' and cancel_fetch = '0' then\n" +
                        "			pc_next <= std_logic_vector(unsigned(pc) + 4);\n" +
                        "		else\n" +
                        "			pc_next <= pc;\n" +
                        "		end if;\n" +
                        "	end process calc_next_pc;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_decode_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_decode.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_constants.all;\n" +
                        "use work.pp_csr.all;\n" +
                        "\n" +
                        "--! @brief Instruction decode unit.\n" +
                        "entity pp_decode is\n" +
                        "	generic(\n" +
                        "		RESET_ADDRESS : std_logic_vector(31 downto 0);\n" +
                        "		PROCESSOR_ID  : std_logic_vector(31 downto 0)\n" +
                        "	);\n" +
                        "	port(\n" +
                        "		clk    : in std_logic;\n" +
                        "		reset  : in std_logic;\n" +
                        "\n" +
                        "		flush : in std_logic;\n" +
                        "		stall : in std_logic;\n" +
                        "\n" +
                        "		-- Instruction input:\n" +
                        "		instruction_data    : in std_logic_vector(31 downto 0);\n" +
                        "		instruction_address : in std_logic_vector(31 downto 0);\n" +
                        "		instruction_ready   : in std_logic;\n" +
                        "		instruction_count   : in std_logic;\n" +
                        "\n" +
                        "		-- Register addresses:\n" +
                        "		rs1_addr, rs2_addr, rd_addr : out register_address;\n" +
                        "		csr_addr : out csr_address;\n" +
                        "\n" +
                        "		-- Shamt value for shift operations:\n" +
                        "		shamt  : out std_logic_vector(4 downto 0);\n" +
                        "		funct3 : out std_logic_vector(2 downto 0);\n" +
                        "\n" +
                        "		-- Immediate value for immediate instructions:\n" +
                        "		immediate : out std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Control signals:\n" +
                        "		rd_write          : out std_logic;\n" +
                        "		branch            : out branch_type;\n" +
                        "		alu_x_src         : out alu_operand_source;\n" +
                        "		alu_y_src         : out alu_operand_source;\n" +
                        "		alu_op            : out alu_operation;\n" +
                        "		mem_op            : out memory_operation_type;\n" +
                        "		mem_size          : out memory_operation_size;\n" +
                        "		count_instruction : out std_logic;\n" +
                        "\n" +
                        "		-- Instruction address:\n" +
                        "		pc : out std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- CSR control signals:\n" +
                        "		csr_write   : out csr_write_mode;\n" +
                        "		csr_use_imm : out std_logic;\n" +
                        "\n" +
                        "		-- Exception output signals:\n" +
                        "		decode_exception       : out std_logic;\n" +
                        "		decode_exception_cause : out csr_exception_cause\n" +
                        "	);\n" +
                        "\n" +
                        "end entity pp_decode;\n" +
                        "\n" +
                        "architecture behaviour of pp_decode is\n" +
                        "	signal instruction     : std_logic_vector(31 downto 0);\n" +
                        "	signal immediate_value : std_logic_vector(31 downto 0);\n" +
                        "begin\n" +
                        "\n" +
                        "	immediate <= immediate_value;\n" +
                        "\n" +
                        "	get_instruction: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				instruction <= RISCV_NOP;\n" +
                        "				pc <= RESET_ADDRESS;\n" +
                        "				count_instruction <= '0';\n" +
                        "			elsif stall = '1' then\n" +
                        "				count_instruction <= '0';\n" +
                        "			elsif flush = '1' or instruction_ready = '0' then\n" +
                        "				instruction <= RISCV_NOP;\n" +
                        "				count_instruction <= '0';\n" +
                        "			else\n" +
                        "				instruction <= instruction_data;\n" +
                        "				count_instruction <= instruction_count;\n" +
                        "				pc <= instruction_address;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process get_instruction;\n" +
                        "\n" +
                        "--	-- Extract register addresses from the instruction word:\n" +
                        "	rs1_addr <= instruction(19 downto 15);\n" +
                        "	rs2_addr <= instruction(24 downto 20);\n" +
                        "	rd_addr  <= instruction(11 downto  7);\n" +
                        "\n" +
                        "	-- Extract the shamt value from the instruction word:\n" +
                        "	shamt    <= instruction(24 downto 20);\n" +
                        "\n" +
                        "	-- Extract the value specifying which comparison to do in branch instructions:\n" +
                        "	funct3 <= instruction(14 downto 12);\n" +
                        "\n" +
                        "	-- Extract the immediate value from the instruction word:\n" +
                        "	immediate_decoder: entity work.pp_imm_decoder\n" +
                        "		port map(\n" +
                        "			instruction => instruction(31 downto 2),\n" +
                        "			immediate => immediate_value\n" +
                        "		);\n" +
                        "\n" +
                        "	decode_csr_addr: process(immediate_value)\n" +
                        "	begin\n" +
                        "		if immediate_value(11 downto 0) = CSR_EPC_MRET then\n" +
                        "			csr_addr <= CSR_MEPC;\n" +
                        "		else\n" +
                        "			csr_addr <= immediate_value(11 downto 0);\n" +
                        "		end if;\n" +
                        "	end process decode_csr_addr;\n" +
                        "\n" +
                        "	control_unit: entity work.pp_control_unit\n" +
                        "		port map(\n" +
                        "			opcode => instruction(6 downto 2),\n" +
                        "			funct3 => instruction(14 downto 12),\n" +
                        "			funct7 => instruction(31 downto 25),\n" +
                        "			funct12 => instruction(31 downto 20),\n" +
                        "			rd_write => rd_write,\n" +
                        "			branch => branch,\n" +
                        "			alu_x_src => alu_x_src,\n" +
                        "			alu_y_src => alu_y_src,\n" +
                        "			alu_op => alu_op,\n" +
                        "			mem_op => mem_op,\n" +
                        "			mem_size => mem_size,\n" +
                        "			decode_exception => decode_exception,\n" +
                        "			decode_exception_cause => decode_exception_cause,\n" +
                        "			csr_write => csr_write,\n" +
                        "			csr_imm => csr_use_imm\n" +
                        "		);\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_imm_decoder_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_imm_decoder.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "--! @brief Module decoding immediate values from instruction words.\n" +
                        "entity pp_imm_decoder is\n" +
                        "	port(\n" +
                        "		instruction : in  std_logic_vector(31 downto 2);\n" +
                        "		immediate   : out std_logic_vector(31 downto 0)\n" +
                        "	);\n" +
                        "end entity pp_imm_decoder;\n" +
                        "\n" +
                        "architecture behaviour of pp_imm_decoder is\n" +
                        "begin\n" +
                        "	decode: process(instruction)\n" +
                        "	begin\n" +
                        "		case instruction(6 downto 2) is\n" +
                        "			when b\"01101\" | b\"00101\" => -- U type\n" +
                        "				immediate <= instruction(31 downto 12) & (11 downto 0 => '0');\n" +
                        "			when b\"11011\" => -- J type\n" +
                        "				immediate <= (31 downto 20 => instruction(31)) & instruction(19 downto 12) & instruction(20) & instruction(30 downto 21) & '0';\n" +
                        "			when b\"11001\" | b\"00000\" | b\"00100\"  | b\"11100\"=> -- I type\n" +
                        "				immediate <= (31 downto 11 => instruction(31)) & instruction(30 downto 20);\n" +
                        "			when b\"11000\" => -- B type\n" +
                        "				immediate <= (31 downto 12 => instruction(31)) & instruction(7) & instruction(30 downto 25) & instruction(11 downto 8) & '0';\n" +
                        "			when b\"01000\" => -- S type\n" +
                        "				immediate <= (31 downto 11 => instruction(31)) & instruction(30 downto 25) & instruction(11 downto 7);\n" +
                        "			when others =>\n" +
                        "				immediate <= (others => '0');\n" +
                        "		end case;\n" +
                        "	end process decode;\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_control_unit_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_control_unit.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "use work.pp_constants.all;\n" +
                        "use work.pp_csr.all;\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "--! @brief\n" +
                        "--! 	Instruction decoding and control unit.\n" +
                        "--! @details\n" +
                        "--!	Decodes incoming instructions and sets control signals accordingly.\n" +
                        "--!	Unknown or otherwise invalid instructions will cause an exception to\n" +
                        "--!	be signaled.\n" +
                        "entity pp_control_unit is\n" +
                        "	port(\n" +
                        "		-- Inputs, indices correspond to instruction word indices:\n" +
                        "		opcode  : in std_logic_vector( 4 downto 0); --! Instruction opcode field.\n" +
                        "		funct3  : in std_logic_vector( 2 downto 0); --! Instruction @c funct3 field.\n" +
                        "		funct7  : in std_logic_vector( 6 downto 0); --! Instruction @c funct7 field.\n" +
                        "		funct12 : in std_logic_vector(11 downto 0); --! Instruction @c funct12 field.\n" +
                        "\n" +
                        "		-- Control signals:\n" +
                        "		rd_write            : out std_logic;   --! Signals that the instruction writes to a destination register.\n" +
                        "		branch              : out branch_type; --! Signals that the instruction is a branch.\n" +
                        "\n" +
                        "		-- Exception signals:\n" +
                        "		decode_exception       : out std_logic;           --! Signals an instruction decode exception.\n" +
                        "		decode_exception_cause : out csr_exception_cause; --! Specifies the cause of a decode exception.\n" +
                        "\n" +
                        "		-- Control register signals:\n" +
                        "		csr_write : out csr_write_mode; --! Write mode for instructions accessing CSRs.\n" +
                        "		csr_imm   : out std_logic;      --! Indicates an immediate variant of a CSR instruction.\n" +
                        "\n" +
                        "		-- Sources of operands to the ALU:\n" +
                        "		alu_x_src, alu_y_src : out alu_operand_source; --! ALU operand source.\n" +
                        "\n" +
                        "		-- ALU operation:\n" +
                        "		alu_op : out alu_operation; --! ALU operation to perform for the instruction.\n" +
                        "\n" +
                        "		-- Memory transaction parameters:\n" +
                        "		mem_op   : out memory_operation_type; --! Memory operation to perform for the instruction.\n" +
                        "		mem_size : out memory_operation_size  --! Size of the memory operation to perform.\n" +
                        "	);\n" +
                        "end entity pp_control_unit;\n" +
                        "\n" +
                        "--! @brief Behavioural description of the instruction decoding and control unit.\n" +
                        "architecture behaviour of pp_control_unit is\n" +
                        "	signal exception       : std_logic;\n" +
                        "	signal exception_cause : csr_exception_cause;\n" +
                        "	signal alu_op_temp     : alu_operation;\n" +
                        "begin\n" +
                        "\n" +
                        "	csr_imm <= funct3(2);\n" +
                        "	alu_op <= alu_op_temp;\n" +
                        "\n" +
                        "	decode_exception <= exception or to_std_logic(alu_op_temp = ALU_INVALID);\n" +
                        "	decode_exception_cause <= exception_cause when alu_op_temp /= ALU_INVALID\n" +
                        "		else CSR_CAUSE_INVALID_INSTR;\n" +
                        "\n" +
                        "	--! @brief   ALU control unit.\n" +
                        "	--! @details Decodes arithmetic and logic instructions and sets the\n" +
                        "	--!          control signals relating to the ALU.\n" +
                        "	alu_control: entity work.pp_alu_control_unit\n" +
                        "		port map(\n" +
                        "			opcode => opcode,\n" +
                        "			funct3 => funct3,\n" +
                        "			funct7 => funct7,\n" +
                        "			alu_x_src => alu_x_src,\n" +
                        "			alu_y_src => alu_y_src,\n" +
                        "			alu_op => alu_op_temp\n" +
                        "		);\n" +
                        "\n" +
                        "	--! @brief Decodes instructions.\n" +
                        "	decode_ctrl: process(opcode, funct3, funct12)\n" +
                        "	begin\n" +
                        "		case opcode is\n" +
                        "			when b\"01101\" => -- Load upper immediate\n" +
                        "				rd_write <= '1';\n" +
                        "				exception <= '0';\n" +
                        "				exception_cause <= CSR_CAUSE_NONE;\n" +
                        "				branch <= BRANCH_NONE;\n" +
                        "			when b\"00101\" => -- Add upper immediate to PC\n" +
                        "				rd_write <= '1';\n" +
                        "				exception <= '0';\n" +
                        "				exception_cause <= CSR_CAUSE_NONE;\n" +
                        "				branch <= BRANCH_NONE;\n" +
                        "			when b\"11011\" => -- Jump and link\n" +
                        "				rd_write <= '1';\n" +
                        "				exception <= '0';\n" +
                        "				exception_cause <= CSR_CAUSE_NONE;\n" +
                        "				branch <= BRANCH_JUMP;\n" +
                        "			when b\"11001\" => -- Jump and link register\n" +
                        "				rd_write <= '1';\n" +
                        "				exception <= '0';\n" +
                        "				exception_cause <= CSR_CAUSE_NONE;\n" +
                        "				branch <= BRANCH_JUMP_INDIRECT;\n" +
                        "			when b\"11000\" => -- Branch operations\n" +
                        "				rd_write <= '0';\n" +
                        "				exception <= '0';\n" +
                        "				exception_cause <= CSR_CAUSE_NONE;\n" +
                        "				branch <= BRANCH_CONDITIONAL;\n" +
                        "			when b\"00000\" => -- Load instructions\n" +
                        "				rd_write <= '1';\n" +
                        "				exception <= '0';\n" +
                        "				exception_cause <= CSR_CAUSE_NONE;\n" +
                        "				branch <= BRANCH_NONE;\n" +
                        "			when b\"01000\" => -- Store instructions\n" +
                        "				rd_write <= '0';\n" +
                        "				exception <= '0';\n" +
                        "				exception_cause <= CSR_CAUSE_NONE;\n" +
                        "				branch <= BRANCH_NONE;\n" +
                        "			when b\"00100\" => -- Register-immediate operations\n" +
                        "				rd_write <= '1';\n" +
                        "				exception <= '0';\n" +
                        "				exception_cause <= CSR_CAUSE_NONE;\n" +
                        "				branch <= BRANCH_NONE;\n" +
                        "			when b\"01100\" => -- Register-register operations\n" +
                        "				rd_write <= '1';\n" +
                        "				exception <= '0';\n" +
                        "				exception_cause <= CSR_CAUSE_NONE;\n" +
                        "				branch <= BRANCH_NONE;\n" +
                        "			when b\"00011\" => -- Fence instructions, ignored\n" +
                        "				rd_write <= '0';\n" +
                        "				exception <= '0';\n" +
                        "				exception_cause <= CSR_CAUSE_NONE;\n" +
                        "				branch <= BRANCH_NONE;\n" +
                        "			when b\"11100\" => -- System instructions\n" +
                        "				if funct3 = b\"000\" then\n" +
                        "					rd_write <= '0';\n" +
                        "\n" +
                        "					if funct12 = x\"000\" then\n" +
                        "						exception <= '1';\n" +
                        "						exception_cause <= CSR_CAUSE_ECALL;\n" +
                        "						branch <= BRANCH_NONE;\n" +
                        "					elsif funct12 = x\"001\" then\n" +
                        "						exception <= '1';\n" +
                        "						exception_cause <= CSR_CAUSE_BREAKPOINT;\n" +
                        "						branch <= BRANCH_NONE;\n" +
                        "					elsif funct12 = CSR_EPC_MRET then\n" +
                        "						exception <= '0';\n" +
                        "						exception_cause <= CSR_CAUSE_NONE;\n" +
                        "						branch <= BRANCH_SRET;\n" +
                        "					elsif funct12 = x\"105\" then -- WFI, currently ignored\n" +
                        "						exception <= '0';\n" +
                        "						exception_cause <= CSR_CAUSE_NONE;\n" +
                        "						branch <= BRANCH_NONE;\n" +
                        "					else\n" +
                        "						exception <= '1';\n" +
                        "						exception_cause <= CSR_CAUSE_INVALID_INSTR;\n" +
                        "						branch <= BRANCH_NONE;\n" +
                        "					end if;\n" +
                        "				else\n" +
                        "					rd_write <= '1';\n" +
                        "					exception <= '0';\n" +
                        "					exception_cause <= CSR_CAUSE_NONE;\n" +
                        "					branch <= BRANCH_NONE;\n" +
                        "				end if;\n" +
                        "			when others =>\n" +
                        "				rd_write <= '0';\n" +
                        "				exception <= '1';\n" +
                        "				exception_cause <= CSR_CAUSE_INVALID_INSTR;\n" +
                        "				branch <= BRANCH_NONE;\n" +
                        "		end case;\n" +
                        "	end process decode_ctrl;\n" +
                        "\n" +
                        "	--! @brief Determines the write mode of instructions accessing the CSR registers.\n" +
                        "	decode_csr: process(opcode, funct3)\n" +
                        "	begin\n" +
                        "		if opcode = b\"11100\" then\n" +
                        "			case funct3 is\n" +
                        "				when b\"001\" | b\"101\" => -- csrrw/i\n" +
                        "					csr_write <= CSR_WRITE_REPLACE;\n" +
                        "				when b\"010\" | b\"110\" => -- csrrs/i\n" +
                        "					csr_write <= CSR_WRITE_SET;\n" +
                        "				when b\"011\" | b\"111\" => -- csrrc/i\n" +
                        "					csr_write <= CSR_WRITE_CLEAR;\n" +
                        "				when others =>\n" +
                        "					csr_write <= CSR_WRITE_NONE;\n" +
                        "			end case;\n" +
                        "		else\n" +
                        "			csr_write <= CSR_WRITE_NONE;\n" +
                        "		end if;\n" +
                        "	end process decode_csr;\n" +
                        "\n" +
                        "	--! @brief Decodes the memory operation for instructions accessing memory.\n" +
                        "	decode_mem: process(opcode, funct3)\n" +
                        "	begin\n" +
                        "		case opcode is\n" +
                        "			when b\"00000\" => -- Load instructions\n" +
                        "				case funct3 is\n" +
                        "					when b\"000\" => -- lw\n" +
                        "						mem_size <= MEMOP_SIZE_BYTE;\n" +
                        "						mem_op <= MEMOP_TYPE_LOAD;\n" +
                        "					when b\"001\" => -- lh\n" +
                        "						mem_size <= MEMOP_SIZE_HALFWORD;\n" +
                        "						mem_op <= MEMOP_TYPE_LOAD;\n" +
                        "					when b\"010\" | b\"110\" => -- lw\n" +
                        "						mem_size <= MEMOP_SIZE_WORD;\n" +
                        "						mem_op <= MEMOP_TYPE_LOAD;\n" +
                        "					when b\"100\" => -- lbu\n" +
                        "						mem_size <= MEMOP_SIZE_BYTE;\n" +
                        "						mem_op <= MEMOP_TYPE_LOAD_UNSIGNED;\n" +
                        "					when b\"101\" => -- lhu\n" +
                        "						mem_size <= MEMOP_SIZE_HALFWORD;\n" +
                        "						mem_op <= MEMOP_TYPE_LOAD_UNSIGNED;\n" +
                        "					when others => -- FIXME: Treat others as lw.\n" +
                        "						mem_size <= MEMOP_SIZE_WORD;\n" +
                        "						mem_op <= MEMOP_TYPE_INVALID;\n" +
                        "				end case;\n" +
                        "			when b\"01000\" => -- Store instructions\n" +
                        "				case funct3 is\n" +
                        "					when b\"000\" =>\n" +
                        "						mem_op <= MEMOP_TYPE_STORE;\n" +
                        "						mem_size <= MEMOP_SIZE_BYTE;\n" +
                        "					when b\"001\" =>\n" +
                        "						mem_op <= MEMOP_TYPE_STORE;\n" +
                        "						mem_size <= MEMOP_SIZE_HALFWORD;\n" +
                        "					when b\"010\" =>\n" +
                        "						mem_op <= MEMOP_TYPE_STORE;\n" +
                        "						mem_size <= MEMOP_SIZE_WORD;\n" +
                        "					when others =>\n" +
                        "						mem_op <= MEMOP_TYPE_INVALID;\n" +
                        "						mem_size <= MEMOP_SIZE_WORD;\n" +
                        "				end case;\n" +
                        "			when others =>\n" +
                        "				mem_op <= MEMOP_TYPE_NONE;\n" +
                        "				mem_size <= MEMOP_SIZE_WORD;\n" +
                        "		end case;\n" +
                        "	end process decode_mem;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_execute_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_execute.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_csr.all;\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "entity pp_execute is\n" +
                        "	port(\n" +
                        "		clk    : in std_logic;\n" +
                        "		reset  : in std_logic;\n" +
                        "\n" +
                        "		stall, flush : in std_logic;\n" +
                        "\n" +
                        "		-- Interrupt inputs:\n" +
                        "		irq : in std_logic_vector(7 downto 0);\n" +
                        "		software_interrupt, timer_interrupt : in std_logic;\n" +
                        "\n" +
                        "		-- Data memory outputs:\n" +
                        "		dmem_address   : out std_logic_vector(31 downto 0);\n" +
                        "		dmem_data_out  : out std_logic_vector(31 downto 0);\n" +
                        "		dmem_data_size : out std_logic_vector( 1 downto 0);\n" +
                        "		dmem_read_req  : out std_logic;\n" +
                        "		dmem_write_req : out std_logic;\n" +
                        "\n" +
                        "		-- Register addresses:\n" +
                        "		rs1_addr_in, rs2_addr_in, rd_addr_in : in  register_address;\n" +
                        "		rd_addr_out                          : out register_address;\n" +
                        "\n" +
                        "		-- Register values:\n" +
                        "		rs1_data_in, rs2_data_in : in std_logic_vector(31 downto 0);\n" +
                        "		rd_data_out              : out std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Constant values:\n" +
                        "		shamt_in     : in std_logic_vector(4 downto 0);\n" +
                        "		immediate_in : in std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Instruction address:\n" +
                        "		pc_in     : in  std_logic_vector(31 downto 0);\n" +
                        "		pc_out    : out std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Funct3 value from the instruction, used to choose which comparison\n" +
                        "		-- is used when branching:\n" +
                        "		funct3_in : in std_logic_vector(2 downto 0);\n" +
                        "\n" +
                        "		-- CSR signals:\n" +
                        "		csr_addr_in          : in  csr_address;\n" +
                        "		csr_addr_out         : out csr_address;\n" +
                        "		csr_write_in         : in  csr_write_mode;\n" +
                        "		csr_write_out        : out csr_write_mode;\n" +
                        "		csr_value_in         : in  std_logic_vector(31 downto 0);\n" +
                        "		csr_value_out        : out std_logic_vector(31 downto 0);\n" +
                        "		csr_use_immediate_in : in  std_logic; \n" +
                        "\n" +
                        "		-- Control signals:\n" +
                        "		alu_op_in    : in  alu_operation;\n" +
                        "		alu_x_src_in : in  alu_operand_source;\n" +
                        "		alu_y_src_in : in  alu_operand_source;\n" +
                        "		rd_write_in  : in  std_logic;\n" +
                        "		rd_write_out : out std_logic;\n" +
                        "		branch_in    : in  branch_type;\n" +
                        "		branch_out   : out branch_type;\n" +
                        "\n" +
                        "		-- Memory control signals:\n" +
                        "		mem_op_in    : in  memory_operation_type;\n" +
                        "		mem_op_out   : out memory_operation_type;\n" +
                        "		mem_size_in  : in  memory_operation_size;\n" +
                        "		mem_size_out : out memory_operation_size;\n" +
                        "\n" +
                        "		-- Whether the instruction should be counted:\n" +
                        "		count_instruction_in  : in  std_logic;\n" +
                        "		count_instruction_out : out std_logic;\n" +
                        "\n" +
                        "		-- Exception control registers:\n" +
                        "		ie_in, ie1_in : in  std_logic;\n" +
                        "		mie_in        : in  std_logic_vector(31 downto 0);\n" +
                        "		mtvec_in      : in  std_logic_vector(31 downto 0);\n" +
                        "		mtvec_out     : out std_logic_vector(31 downto 0);\n" +
                        "		--mepc_in       : in  std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Exception signals:\n" +
                        "		decode_exception_in       : in std_logic;\n" +
                        "		decode_exception_cause_in : in csr_exception_cause;\n" +
                        "\n" +
                        "		-- Exception outputs:\n" +
                        "		exception_out         : out std_logic;\n" +
                        "		exception_context_out : out csr_exception_context;\n" +
                        "\n" +
                        "		-- Control outputs:\n" +
                        "		jump_out        : out std_logic;\n" +
                        "		jump_target_out : out std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Inputs to the forwarding logic from the MEM stage:\n" +
                        "		mem_rd_write          : in std_logic;\n" +
                        "		mem_rd_addr           : in register_address;\n" +
                        "		mem_rd_value          : in std_logic_vector(31 downto 0);\n" +
                        "		mem_csr_addr          : in csr_address;\n" +
                        "		mem_csr_write         : in csr_write_mode;\n" +
                        "		mem_exception         : in std_logic;\n" +
                        "\n" +
                        "		-- Inputs to the forwarding logic from the WB stage:\n" +
                        "		wb_rd_write          : in std_logic;\n" +
                        "		wb_rd_addr           : in register_address;\n" +
                        "		wb_rd_value          : in std_logic_vector(31 downto 0);\n" +
                        "		wb_csr_addr          : in csr_address;\n" +
                        "		wb_csr_write         : in csr_write_mode;\n" +
                        "		wb_exception         : in std_logic;\n" +
                        "\n" +
                        "		-- Hazard detection unit signals:\n" +
                        "		mem_mem_op      : in  memory_operation_type;\n" +
                        "		hazard_detected : out std_logic\n" +
                        "	);\n" +
                        "end entity pp_execute;\n" +
                        "\n" +
                        "architecture behaviour of pp_execute is \n" +
                        "	signal alu_op : alu_operation;\n" +
                        "	signal alu_x_src, alu_y_src : alu_operand_source;\n" +
                        "\n" +
                        "	signal alu_x, alu_y, alu_result : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "	signal rs1_addr, rs2_addr : register_address;\n" +
                        "	signal rs1_data, rs2_data : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "	signal mem_op : memory_operation_type;\n" +
                        "	signal mem_size : memory_operation_size;\n" +
                        "\n" +
                        "	signal pc        : std_logic_vector(31 downto 0);\n" +
                        "	signal immediate : std_logic_vector(31 downto 0);\n" +
                        "	signal shamt     : std_logic_vector( 4 downto 0);\n" +
                        "	signal funct3    : std_logic_vector( 2 downto 0);\n" +
                        "\n" +
                        "	signal rs1_forwarded, rs2_forwarded : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "	signal branch : branch_type;\n" +
                        "	signal branch_condition : std_logic;\n" +
                        "	signal do_jump : std_logic;\n" +
                        "	signal jump_target : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "	signal mie, mtvec : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "	signal csr_write : csr_write_mode;\n" +
                        "	signal csr_addr  : csr_address;\n" +
                        "	signal csr_use_immediate : std_logic;\n" +
                        "\n" +
                        "	signal csr_value : std_logic_vector(31 downto 0);\n" +
                        "	\n" +
                        "	signal decode_exception : std_logic;\n" +
                        "	signal decode_exception_cause : csr_exception_cause;\n" +
                        "\n" +
                        "	signal exception_taken : std_logic;\n" +
                        "	signal exception_cause : csr_exception_cause;\n" +
                        "	signal exception_addr  : std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "	signal data_misaligned, instr_misaligned : std_logic;\n" +
                        "\n" +
                        "	signal irq_asserted : std_logic;\n" +
                        "	signal irq_asserted_num : std_logic_vector(3 downto 0);\n" +
                        "\n" +
                        "	signal load_hazard_detected, csr_hazard_detected : std_logic;\n" +
                        "begin\n" +
                        "\n" +
                        "	-- Register values should not be latched in by a clocked process,\n" +
                        "	-- this is already done in the register files.\n" +
                        "	csr_value <= csr_value_in;\n" +
                        "	rd_data_out <= alu_result;\n" +
                        "\n" +
                        "	branch_out <= branch;\n" +
                        "\n" +
                        "	mem_op_out <= mem_op;\n" +
                        "	mem_size_out <= mem_size;\n" +
                        "\n" +
                        "	csr_write_out <= csr_write;\n" +
                        "	csr_addr_out  <= csr_addr;\n" +
                        "\n" +
                        "	pc_out <= pc;\n" +
                        "	hazard_detected <= load_hazard_detected or csr_hazard_detected;\n" +
                        "	exception_out <= exception_taken;\n" +
                        "	exception_context_out <= (\n" +
                        "				ie => ie_in,\n" +
                        "				ie1 => ie1_in,\n" +
                        "				cause => exception_cause,\n" +
                        "				badaddr => exception_addr);\n" +
                        "\n" +
                        "	do_jump <= (to_std_logic(branch = BRANCH_JUMP or branch = BRANCH_JUMP_INDIRECT)\n" +
                        "		or (to_std_logic(branch = BRANCH_CONDITIONAL) and branch_condition)\n" +
                        "		or to_std_logic(branch = BRANCH_SRET)) and not stall;\n" +
                        "	jump_out <= do_jump;\n" +
                        "	jump_target_out <= jump_target;\n" +
                        "\n" +
                        "	mtvec_out <= std_logic_vector(unsigned(mtvec));\n" +
                        "	exception_taken <= not stall and (decode_exception or to_std_logic(exception_cause /= CSR_CAUSE_NONE)); \n" +
                        "\n" +
                        "	irq_asserted <= to_std_logic(ie_in = '1' and (irq and mie(31 downto 24)) /= x\"00\");\n" +
                        "\n" +
                        "	rs1_data <= rs1_data_in;\n" +
                        "	rs2_data <= rs2_data_in;\n" +
                        "\n" +
                        "	dmem_address <= alu_result when (mem_op /= MEMOP_TYPE_NONE and mem_op /= MEMOP_TYPE_INVALID) and exception_taken = '0'\n" +
                        "		else (others => '0');\n" +
                        "	dmem_data_out <= rs2_forwarded;\n" +
                        "	dmem_write_req <= '1' when mem_op = MEMOP_TYPE_STORE and exception_taken = '0' else '0';\n" +
                        "	dmem_read_req <= '1' when memop_is_load(mem_op) and exception_taken = '0' else '0';\n" +
                        "\n" +
                        "	pipeline_register: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' or flush = '1' then\n" +
                        "				rd_write_out <= '0';\n" +
                        "				branch <= BRANCH_NONE;\n" +
                        "				csr_write <= CSR_WRITE_NONE;\n" +
                        "				mem_op <= MEMOP_TYPE_NONE;\n" +
                        "				decode_exception <= '0';\n" +
                        "				count_instruction_out <= '0';\n" +
                        "			elsif stall = '1' then\n" +
                        "				csr_write <= CSR_WRITE_NONE;\n" +
                        "			elsif stall = '0' then\n" +
                        "				pc <= pc_in;\n" +
                        "				count_instruction_out <= count_instruction_in;\n" +
                        "\n" +
                        "				-- Register signals:\n" +
                        "				rd_write_out <= rd_write_in;\n" +
                        "				rd_addr_out <= rd_addr_in;\n" +
                        "				rs1_addr <= rs1_addr_in;\n" +
                        "				rs2_addr <= rs2_addr_in;\n" +
                        "\n" +
                        "				-- ALU signals:\n" +
                        "				alu_op <= alu_op_in;\n" +
                        "				alu_x_src <= alu_x_src_in;\n" +
                        "				alu_y_src <= alu_y_src_in;\n" +
                        "\n" +
                        "				-- Control signals:\n" +
                        "				branch <= branch_in;\n" +
                        "				mem_op <= mem_op_in;\n" +
                        "				mem_size <= mem_size_in;\n" +
                        "\n" +
                        "				-- Constant values:\n" +
                        "				immediate <= immediate_in;\n" +
                        "				shamt <= shamt_in;\n" +
                        "				funct3 <= funct3_in;\n" +
                        "\n" +
                        "				-- CSR signals:\n" +
                        "				csr_write <= csr_write_in;\n" +
                        "				csr_addr <= csr_addr_in;\n" +
                        "				csr_use_immediate <= csr_use_immediate_in;\n" +
                        "\n" +
                        "				-- Exception vector base:\n" +
                        "				mtvec <= mtvec_in;\n" +
                        "				mie <= mie_in;\n" +
                        "\n" +
                        "				-- Instruction decoder exceptions:\n" +
                        "				decode_exception <= decode_exception_in;\n" +
                        "				decode_exception_cause <= decode_exception_cause_in;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process pipeline_register;\n" +
                        "\n" +
                        "	set_data_size: process(mem_size)\n" +
                        "	begin\n" +
                        "		case mem_size is\n" +
                        "			when MEMOP_SIZE_BYTE =>\n" +
                        "				dmem_data_size <= b\"01\";\n" +
                        "			when MEMOP_SIZE_HALFWORD =>\n" +
                        "				dmem_data_size <= b\"10\";\n" +
                        "			when MEMOP_SIZE_WORD =>\n" +
                        "				dmem_data_size <= b\"00\";\n" +
                        "			when others =>\n" +
                        "				dmem_data_size <= b\"11\";\n" +
                        "		end case;\n" +
                        "	end process set_data_size;\n" +
                        "\n" +
                        "	get_irq_num: process(irq, mie)\n" +
                        "		variable temp : std_logic_vector(3 downto 0);\n" +
                        "	begin\n" +
                        "		temp := (others => '0');\n" +
                        "\n" +
                        "		for i in 0 to 7 loop\n" +
                        "			if irq(i) = '1' and mie(24 + i) = '1' then\n" +
                        "				temp := std_logic_vector(to_unsigned(i, temp'length));\n" +
                        "				exit;\n" +
                        "			end if;\n" +
                        "		end loop;\n" +
                        "\n" +
                        "		irq_asserted_num <= temp;\n" +
                        "	end process get_irq_num;\n" +
                        "\n" +
                        "	data_misalign_check: process(mem_size, alu_result)\n" +
                        "	begin\n" +
                        "		case mem_size is\n" +
                        "			when MEMOP_SIZE_HALFWORD =>\n" +
                        "				if alu_result(0) /= '0' then\n" +
                        "					data_misaligned <= '1';\n" +
                        "				else\n" +
                        "					data_misaligned <= '0';\n" +
                        "				end if;\n" +
                        "			when MEMOP_SIZE_WORD =>\n" +
                        "				if alu_result(1 downto 0) /= b\"00\" then\n" +
                        "					data_misaligned <= '1';\n" +
                        "				else\n" +
                        "					data_misaligned <= '0';\n" +
                        "				end if;\n" +
                        "			when others =>\n" +
                        "				data_misaligned <= '0';\n" +
                        "		end case;\n" +
                        "	end process data_misalign_check;\n" +
                        "\n" +
                        "	instr_misalign_check: process(jump_target, branch, branch_condition, do_jump)\n" +
                        "	begin\n" +
                        "		if jump_target(1 downto 0) /= b\"00\" and do_jump = '1' then\n" +
                        "			instr_misaligned <= '1';\n" +
                        "		else\n" +
                        "			instr_misaligned <= '0';\n" +
                        "		end if;\n" +
                        "	end process instr_misalign_check;\n" +
                        "\n" +
                        "	find_exception_cause: process(decode_exception, decode_exception_cause, mem_op,\n" +
                        "		data_misaligned, instr_misaligned, irq_asserted, irq_asserted_num, mie,\n" +
                        "		software_interrupt, timer_interrupt, ie_in)\n" +
                        "	begin\n" +
                        "		if irq_asserted = '1' then\n" +
                        "			exception_cause <= std_logic_vector(unsigned(CSR_CAUSE_IRQ_BASE) + unsigned(irq_asserted_num));\n" +
                        "		elsif software_interrupt = '1' and mie(CSR_MIE_MSIE) = '1' and ie_in = '1' then\n" +
                        "			exception_cause <= CSR_CAUSE_SOFTWARE_INT;\n" +
                        "		elsif timer_interrupt = '1' and mie(CSR_MIE_MTIE) = '1' and ie_in = '1' then\n" +
                        "			exception_cause <= CSR_CAUSE_TIMER_INT;\n" +
                        "		elsif decode_exception = '1' then\n" +
                        "			exception_cause <= decode_exception_cause;\n" +
                        "		elsif mem_op = MEMOP_TYPE_INVALID then\n" +
                        "			exception_cause <= CSR_CAUSE_INVALID_INSTR;\n" +
                        "		elsif instr_misaligned = '1' then\n" +
                        "			exception_cause <= CSR_CAUSE_INSTR_MISALIGN;\n" +
                        "		elsif data_misaligned = '1' and mem_op = MEMOP_TYPE_STORE then\n" +
                        "			exception_cause <= CSR_CAUSE_STORE_MISALIGN;\n" +
                        "		elsif data_misaligned = '1' and memop_is_load(mem_op) then\n" +
                        "			exception_cause <= CSR_CAUSE_LOAD_MISALIGN;\n" +
                        "		else\n" +
                        "			exception_cause <= CSR_CAUSE_NONE;\n" +
                        "		end if;\n" +
                        "	end process find_exception_cause;\n" +
                        "\n" +
                        "	find_exception_addr: process(instr_misaligned, data_misaligned, jump_target, alu_result)\n" +
                        "	begin\n" +
                        "		if instr_misaligned = '1' then\n" +
                        "			exception_addr <= jump_target;\n" +
                        "		elsif data_misaligned = '1' then\n" +
                        "			exception_addr <= alu_result;\n" +
                        "		else\n" +
                        "			exception_addr <= (others => '0');\n" +
                        "		end if;\n" +
                        "	end process find_exception_addr;\n" +
                        "\n" +
                        "	calc_jump_tgt: process(branch, pc, rs1_forwarded, immediate, csr_value)\n" +
                        "	begin\n" +
                        "		case branch is\n" +
                        "			when BRANCH_JUMP | BRANCH_CONDITIONAL =>\n" +
                        "				jump_target <= std_logic_vector(unsigned(pc) + unsigned(immediate));\n" +
                        "			when BRANCH_JUMP_INDIRECT =>\n" +
                        "				jump_target <= std_logic_vector(unsigned(rs1_forwarded) + unsigned(immediate));\n" +
                        "			when BRANCH_SRET =>\n" +
                        "				jump_target <= csr_value;\n" +
                        "			when others =>\n" +
                        "				jump_target <= (others => '0');\n" +
                        "		end case;\n" +
                        "	end process calc_jump_tgt;\n" +
                        "\n" +
                        "	alu_x_mux: entity work.pp_alu_mux\n" +
                        "		port map(\n" +
                        "			source => alu_x_src,\n" +
                        "			register_value => rs1_forwarded,\n" +
                        "			immediate_value => immediate,\n" +
                        "			shamt_value => shamt,\n" +
                        "			pc_value => pc,\n" +
                        "			csr_value => csr_value,\n" +
                        "			output => alu_x\n" +
                        "		);\n" +
                        "\n" +
                        "	alu_y_mux: entity work.pp_alu_mux\n" +
                        "		port map(\n" +
                        "			source => alu_y_src,\n" +
                        "			register_value => rs2_forwarded,\n" +
                        "			immediate_value => immediate,\n" +
                        "			shamt_value => shamt,\n" +
                        "			pc_value => pc,\n" +
                        "			csr_value => csr_value,\n" +
                        "			output => alu_y\n" +
                        "		);\n" +
                        "\n" +
                        "	alu_x_forward: process(mem_rd_write, mem_rd_value, mem_rd_addr, rs1_addr,\n" +
                        "		rs1_data, wb_rd_write, wb_rd_addr, wb_rd_value)\n" +
                        "	begin\n" +
                        "		if mem_rd_write = '1' and mem_rd_addr = rs1_addr and mem_rd_addr /= b\"00000\" then\n" +
                        "			rs1_forwarded <= mem_rd_value;\n" +
                        "		elsif wb_rd_write = '1' and wb_rd_addr = rs1_addr and wb_rd_addr /= b\"00000\" then\n" +
                        "			rs1_forwarded <= wb_rd_value;\n" +
                        "		else\n" +
                        "			rs1_forwarded <= rs1_data;\n" +
                        "		end if;\n" +
                        "	end process alu_x_forward;\n" +
                        "\n" +
                        "	alu_y_forward: process(mem_rd_write, mem_rd_value, mem_rd_addr, rs2_addr,\n" +
                        "		rs2_data, wb_rd_write, wb_rd_addr, wb_rd_value)\n" +
                        "	begin\n" +
                        "		if mem_rd_write = '1' and mem_rd_addr = rs2_addr and mem_rd_addr /= b\"00000\" then\n" +
                        "			rs2_forwarded <= mem_rd_value;\n" +
                        "		elsif wb_rd_write = '1' and wb_rd_addr = rs2_addr and wb_rd_addr /= b\"00000\" then\n" +
                        "			rs2_forwarded <= wb_rd_value;\n" +
                        "		else\n" +
                        "			rs2_forwarded <= rs2_data;\n" +
                        "		end if;\n" +
                        "	end process alu_y_forward;\n" +
                        "\n" +
                        "	detect_csr_hazard: process(mem_csr_write, wb_csr_write, mem_exception, wb_exception)\n" +
                        "	begin\n" +
                        "		if mem_csr_write /= CSR_WRITE_NONE or wb_csr_write /= CSR_WRITE_NONE\n" +
                        "			or mem_exception = '1' or wb_exception = '1' then\n" +
                        "			csr_hazard_detected <= '1';\n" +
                        "		else\n" +
                        "			csr_hazard_detected <= '0';\n" +
                        "		end if;\n" +
                        "	end process detect_csr_hazard;\n" +
                        "\n" +
                        "	detect_load_hazard: process(mem_mem_op, mem_rd_addr, rs1_addr, rs2_addr,\n" +
                        "		alu_x_src, alu_y_src)\n" +
                        "	begin\n" +
                        "		if (mem_mem_op = MEMOP_TYPE_LOAD or mem_mem_op = MEMOP_TYPE_LOAD_UNSIGNED) and\n" +
                        "				((alu_x_src = ALU_SRC_REG and mem_rd_addr = rs1_addr and rs1_addr /= b\"00000\")\n" +
                        "			or\n" +
                        "				(alu_y_src = ALU_SRC_REG and mem_rd_addr = rs2_addr and rs2_addr /= b\"00000\"))\n" +
                        "		then\n" +
                        "			load_hazard_detected <= '1';\n" +
                        "		else\n" +
                        "			load_hazard_detected <= '0';\n" +
                        "		end if;\n" +
                        "	end process detect_load_hazard;\n" +
                        "\n" +
                        "	branch_comparator: entity work.pp_comparator\n" +
                        "		port map(\n" +
                        "			funct3 => funct3,\n" +
                        "			rs1 => rs1_forwarded,\n" +
                        "			rs2 => rs2_forwarded,\n" +
                        "			result => branch_condition\n" +
                        "		);\n" +
                        "\n" +
                        "	alu_instance: entity work.pp_alu\n" +
                        "		port map(\n" +
                        "			result => alu_result,\n" +
                        "			x => alu_x,\n" +
                        "			y => alu_y,\n" +
                        "			operation => alu_op\n" +
                        "		);\n" +
                        "\n" +
                        "	csr_alu_instance: entity work.pp_csr_alu\n" +
                        "		port map(\n" +
                        "			x => csr_value,\n" +
                        "			y => rs1_forwarded,\n" +
                        "			result => csr_value_out,\n" +
                        "			immediate => rs1_addr,\n" +
                        "			use_immediate => csr_use_immediate,\n" +
                        "			write_mode => csr_write\n" +
                        "		);\n" +
                        "\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_alu_mux_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_alu_mux.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "\n" +
                        "--! @brief Multiplexer used to choose between ALU inputs.\n" +
                        "entity pp_alu_mux is\n" +
                        "	port(\n" +
                        "		source : in alu_operand_source;\n" +
                        "\n" +
                        "		register_value  : in std_logic_vector(31 downto 0);\n" +
                        "		immediate_value : in std_logic_vector(31 downto 0);\n" +
                        "		shamt_value     : in std_logic_vector( 4 downto 0);\n" +
                        "		pc_value        : in std_logic_vector(31 downto 0);\n" +
                        "		csr_value       : in std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		output : out std_logic_vector(31 downto 0)\n" +
                        "	);\n" +
                        "end entity pp_alu_mux;\n" +
                        "\n" +
                        "architecture behaviour of pp_alu_mux is\n" +
                        "begin\n" +
                        "\n" +
                        "	mux: process(source, register_value, immediate_value, shamt_value, pc_value, csr_value)\n" +
                        "	begin\n" +
                        "		case source is\n" +
                        "			when ALU_SRC_REG =>\n" +
                        "				output <= register_value;\n" +
                        "			when ALU_SRC_IMM =>\n" +
                        "				output <= immediate_value;\n" +
                        "			when ALU_SRC_PC =>\n" +
                        "				output <= pc_value;\n" +
                        "			when ALU_SRC_PC_NEXT =>\n" +
                        "				output <= std_logic_vector(unsigned(pc_value) + 4);\n" +
                        "			when ALU_SRC_CSR =>\n" +
                        "				output <= csr_value;\n" +
                        "			when ALU_SRC_SHAMT =>\n" +
                        "				output <= (31 downto 5 => '0') & shamt_value;\n" +
                        "			when ALU_SRC_NULL =>\n" +
                        "				output <= (others => '0');\n" +
                        "		end case;\n" +
                        "	end process mux;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_comparator_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_comparator.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "--! @brief Component for comparing two registers in the ID stage whens branching.\n" +
                        "entity pp_comparator is\n" +
                        "	port(\n" +
                        "		funct3   : in  std_logic_vector(14 downto 12);\n" +
                        "		rs1, rs2 : in  std_logic_vector(31 downto 0);\n" +
                        "		result   : out std_logic --! Result of the comparison.\n" +
                        "	);\n" +
                        "end entity pp_comparator;\n" +
                        "\n" +
                        "architecture behaviour of pp_comparator is\n" +
                        "begin\n" +
                        "\n" +
                        "	compare: process(funct3, rs1, rs2)\n" +
                        "	begin\n" +
                        "		case funct3 is\n" +
                        "			when b\"000\" => -- EQ\n" +
                        "				result <= to_std_logic(rs1 = rs2);\n" +
                        "			when b\"001\" => -- NE\n" +
                        "				result <= to_std_logic(rs1 /= rs2);\n" +
                        "			when b\"100\" => -- LT\n" +
                        "				result <= to_std_logic(signed(rs1) < signed(rs2));\n" +
                        "			when b\"101\" => -- GE\n" +
                        "				result <= to_std_logic(signed(rs1) >= signed(rs2));\n" +
                        "			when b\"110\" => -- LTU\n" +
                        "				result <= to_std_logic(unsigned(rs1) < unsigned(rs2));\n" +
                        "			when b\"111\" => -- GEU\n" +
                        "				result <= to_std_logic(unsigned(rs1) >= unsigned(rs2));\n" +
                        "			when others =>\n" +
                        "				result <= '0';\n" +
                        "		end case;\n" +
                        "	end process compare;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_alu_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_alu.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "\n" +
                        "--! @brief\n" +
                        "--!	Arithmetic Logic Unit (ALU).\n" +
                        "--!\n" +
                        "--! @details\n" +
                        "--! 	Performs logic and arithmetic calculations. The operation to perform\n" +
                        "--!	is specified by the user of the module.\n" +
                        "entity pp_alu is\n" +
                        "	port(\n" +
                        "		x, y      : in  std_logic_vector(31 downto 0); --! Input operand.\n" +
                        "		result    : out std_logic_vector(31 downto 0); --! Operation result.\n" +
                        "		operation : in alu_operation                   --! Operation type.\n" +
                        "	);\n" +
                        "end entity pp_alu;\n" +
                        "\n" +
                        "--! @brief Behavioural description of the ALU.\n" +
                        "architecture behaviour of pp_alu is\n" +
                        "begin\n" +
                        "\n" +
                        "	--! Performs the ALU calculation.\n" +
                        "	calculate: process(operation, x, y)\n" +
                        "	begin\n" +
                        "		case operation is\n" +
                        "			when ALU_AND =>\n" +
                        "				result <= x and y;\n" +
                        "			when ALU_OR =>\n" +
                        "				result <= x or y;\n" +
                        "			when ALU_XOR =>\n" +
                        "				result <= x xor y;\n" +
                        "			when ALU_SLT =>\n" +
                        "				if signed(x) < signed(y) then\n" +
                        "					result <= (0 => '1', others => '0');\n" +
                        "				else\n" +
                        "					result <= (others => '0');\n" +
                        "				end if;\n" +
                        "			when ALU_SLTU =>\n" +
                        "				if unsigned(x) < unsigned(y) then\n" +
                        "					result <= (0 => '1', others => '0');\n" +
                        "				else\n" +
                        "					result <= (others => '0');\n" +
                        "				end if;\n" +
                        "			when ALU_ADD =>\n" +
                        "				result <= std_logic_vector(unsigned(x) + unsigned(y));\n" +
                        "			when ALU_SUB =>\n" +
                        "				result <= std_logic_vector(unsigned(x) - unsigned(y));\n" +
                        "			when ALU_SRL =>\n" +
                        "				result <= std_logic_vector(shift_right(unsigned(x), to_integer(unsigned(y(4 downto 0)))));\n" +
                        "			when ALU_SLL =>\n" +
                        "				result <= std_logic_vector(shift_left(unsigned(x), to_integer(unsigned(y(4 downto 0)))));\n" +
                        "			when ALU_SRA =>\n" +
                        "				result <= std_logic_vector(shift_right(signed(x), to_integer(unsigned(y(4 downto 0)))));\n" +
                        "			when others =>\n" +
                        "				result <= (others => '0');\n" +
                        "		end case;\n" +
                        "	end process calculate;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_csr_alu_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_csr_alu.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_csr.all;\n" +
                        "\n" +
                        "--! @brief ALU used for calculating new values of control and status registers.\n" +
                        "entity pp_csr_alu is\n" +
                        "	port(\n" +
                        "		x, y          : in  std_logic_vector(31 downto 0);\n" +
                        "		result        : out std_logic_vector(31 downto 0);\n" +
                        "		immediate     : in  std_logic_vector(4 downto 0);\n" +
                        "		use_immediate : in  std_logic;\n" +
                        "		write_mode    : in  csr_write_mode\n" +
                        "	);\n" +
                        "end entity pp_csr_alu;\n" +
                        "\n" +
                        "architecture behaviour of pp_csr_alu is\n" +
                        "	signal a, b : std_logic_vector(31 downto 0);\n" +
                        "begin\n" +
                        "\n" +
                        "	a <= x;\n" +
                        "	b <= y when use_immediate = '0' else std_logic_vector(resize(unsigned(immediate), b'length));\n" +
                        "\n" +
                        "	calculate: process(a, b, write_mode)\n" +
                        "	begin\n" +
                        "		case write_mode is\n" +
                        "			when CSR_WRITE_NONE =>\n" +
                        "				result <= a;\n" +
                        "			when CSR_WRITE_SET =>\n" +
                        "				result <= a or b;\n" +
                        "			when CSR_WRITE_CLEAR =>\n" +
                        "				result <= a and (not b);\n" +
                        "			when CSR_WRITE_REPLACE =>\n" +
                        "				result <= b;\n" +
                        "		end case;\n" +
                        "	end process calculate;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_memory_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_memory.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_csr.all;\n" +
                        "use work.pp_utilities.all;\n" +
                        "\n" +
                        "entity pp_memory is\n" +
                        "	port(\n" +
                        "		clk    : in std_logic;\n" +
                        "		reset  : in std_logic;\n" +
                        "		stall  : in std_logic;\n" +
                        "\n" +
                        "		-- Data memory inputs:\n" +
                        "		dmem_read_ack  : in std_logic;\n" +
                        "		dmem_write_ack : in std_logic;\n" +
                        "		dmem_data_in   : in std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Current PC value:\n" +
                        "		pc : in std_logic_vector(31 downto 0);\n" +
                        "\n" +
                        "		-- Destination register signals:\n" +
                        "		rd_write_in  : in  std_logic;\n" +
                        "		rd_write_out : out std_logic;\n" +
                        "		rd_data_in   : in  std_logic_vector(31 downto 0);\n" +
                        "		rd_data_out  : out std_logic_vector(31 downto 0);\n" +
                        "		rd_addr_in   : in  register_address;\n" +
                        "		rd_addr_out  : out register_address;\n" +
                        "\n" +
                        "		-- Control signals:\n" +
                        "		branch         : in  branch_type;\n" +
                        "		mem_op_in      : in  memory_operation_type;\n" +
                        "		mem_size_in    : in  memory_operation_size;\n" +
                        "		mem_op_out     : out memory_operation_type;\n" +
                        "\n" +
                        "		-- Whether the instruction should be counted:\n" +
                        "		count_instr_in  : in  std_logic;\n" +
                        "		count_instr_out : out std_logic;\n" +
                        "\n" +
                        "		-- Exception signals:\n" +
                        "		exception_in          : in std_logic;\n" +
                        "		exception_out         : out std_logic;\n" +
                        "		exception_context_in  : in  csr_exception_context;\n" +
                        "		exception_context_out : out csr_exception_context;\n" +
                        "\n" +
                        "		-- CSR signals:\n" +
                        "		csr_addr_in   : in  csr_address;\n" +
                        "		csr_addr_out  : out csr_address;\n" +
                        "		csr_write_in  : in  csr_write_mode;\n" +
                        "		csr_write_out : out csr_write_mode;\n" +
                        "		csr_data_in   : in  std_logic_vector(31 downto 0);\n" +
                        "		csr_data_out  : out std_logic_vector(31 downto 0)\n" +
                        "	);\n" +
                        "end entity pp_memory;\n" +
                        "\n" +
                        "architecture behaviour of pp_memory is\n" +
                        "	signal mem_op   : memory_operation_type;\n" +
                        "	signal mem_size : memory_operation_size;\n" +
                        "\n" +
                        "	signal rd_data : std_logic_vector(31 downto 0);\n" +
                        "begin\n" +
                        "\n" +
                        "	mem_op_out <= mem_op;\n" +
                        "\n" +
                        "	pipeline_register: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				rd_write_out <= '0';\n" +
                        "				csr_write_out <= CSR_WRITE_NONE;\n" +
                        "				count_instr_out <= '0';\n" +
                        "				mem_op <= MEMOP_TYPE_NONE;\n" +
                        "			elsif stall = '0' then\n" +
                        "				mem_size <= mem_size_in;\n" +
                        "				rd_data <= rd_data_in;\n" +
                        "				rd_addr_out <= rd_addr_in;\n" +
                        "\n" +
                        "				if exception_in = '1' then\n" +
                        "					mem_op <= MEMOP_TYPE_NONE;\n" +
                        "					rd_write_out <= '0';\n" +
                        "					csr_write_out <= CSR_WRITE_REPLACE;\n" +
                        "					csr_addr_out <= CSR_MEPC;\n" +
                        "					csr_data_out <= pc;\n" +
                        "					count_instr_out <= '0';\n" +
                        "				else\n" +
                        "					mem_op <= mem_op_in;\n" +
                        "					rd_write_out <= rd_write_in;\n" +
                        "					csr_write_out <= csr_write_in;\n" +
                        "					csr_addr_out <= csr_addr_in;\n" +
                        "					csr_data_out <= csr_data_in;\n" +
                        "					count_instr_out <= count_instr_in;\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process pipeline_register;\n" +
                        "\n" +
                        "	update_exception_context: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				exception_out <= '0';\n" +
                        "			else\n" +
                        "				exception_out <= exception_in or to_std_logic(branch = BRANCH_SRET);\n" +
                        "\n" +
                        "				if exception_in = '1' then\n" +
                        "					exception_context_out.ie <= '0';\n" +
                        "					exception_context_out.ie1 <= exception_context_in.ie;\n" +
                        "					exception_context_out.cause <= exception_context_in.cause;\n" +
                        "					exception_context_out.badaddr <= exception_context_in.badaddr;\n" +
                        "				elsif branch = BRANCH_SRET then\n" +
                        "					exception_context_out.ie <= exception_context_in.ie1;\n" +
                        "					exception_context_out.ie1 <= exception_context_in.ie;\n" +
                        "					exception_context_out.cause <= CSR_CAUSE_NONE;\n" +
                        "					exception_context_out.badaddr <= (others => '0');\n" +
                        "				else\n" +
                        "					exception_context_out.ie <= exception_context_in.ie;\n" +
                        "					exception_context_out.ie1 <= exception_context_in.ie1;\n" +
                        "					exception_context_out.cause <= CSR_CAUSE_NONE;\n" +
                        "					exception_context_out.badaddr <= (others => '0');\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process update_exception_context;\n" +
                        "\n" +
                        "	rd_data_mux: process(rd_data, dmem_data_in, mem_op, mem_size)\n" +
                        "	begin\n" +
                        "		if mem_op = MEMOP_TYPE_LOAD or mem_op = MEMOP_TYPE_LOAD_UNSIGNED then\n" +
                        "			case mem_size is\n" +
                        "				when MEMOP_SIZE_BYTE =>\n" +
                        "					if mem_op = MEMOP_TYPE_LOAD_UNSIGNED then\n" +
                        "						rd_data_out <= std_logic_vector(resize(unsigned(dmem_data_in(7 downto 0)), rd_data_out'length));\n" +
                        "					else\n" +
                        "						rd_data_out <= std_logic_vector(resize(signed(dmem_data_in(7 downto 0)), rd_data_out'length));\n" +
                        "					end if;\n" +
                        "				when MEMOP_SIZE_HALFWORD =>\n" +
                        "					if mem_op = MEMOP_TYPE_LOAD_UNSIGNED then\n" +
                        "						rd_data_out <= std_logic_vector(resize(unsigned(dmem_data_in(15 downto 0)), rd_data_out'length));\n" +
                        "					else\n" +
                        "						rd_data_out <= std_logic_vector(resize(signed(dmem_data_in(15 downto 0)), rd_data_out'length));\n" +
                        "					end if;\n" +
                        "				when MEMOP_SIZE_WORD =>\n" +
                        "					rd_data_out <= dmem_data_in;\n" +
                        "			end case;\n" +
                        "		else\n" +
                        "			rd_data_out <= rd_data;\n" +
                        "		end if;\n" +
                        "	end process rd_data_mux;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_writeback_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_writeback.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_csr.all;\n" +
                        "\n" +
                        "entity pp_writeback is\n" +
                        "	port(\n" +
                        "		clk    : in std_logic;\n" +
                        "		reset  : in std_logic;\n" +
                        "\n" +
                        "		-- Count instruction:\n" +
                        "		count_instr_in  : in std_logic;\n" +
                        "		count_instr_out : out std_logic;\n" +
                        "\n" +
                        "		-- Exception signals:\n" +
                        "		exception_ctx_in  : in  csr_exception_context;\n" +
                        "		exception_in      : in  std_logic;\n" +
                        "		exception_ctx_out : out csr_exception_context;\n" +
                        "		exception_out     : out std_logic;\n" +
                        "\n" +
                        "		-- CSR signals:\n" +
                        "		csr_write_in  : in  csr_write_mode;\n" +
                        "		csr_write_out : out csr_write_mode;\n" +
                        "		csr_data_in   : in  std_logic_vector(31 downto 0);\n" +
                        "		csr_data_out  : out std_logic_vector(31 downto 0);\n" +
                        "		csr_addr_in   : in  csr_address;\n" +
                        "		csr_addr_out  : out csr_address;\n" +
                        "\n" +
                        "		-- Destination register interface:\n" +
                        "		rd_addr_in   : in  register_address;\n" +
                        "		rd_addr_out  : out register_address;\n" +
                        "		rd_write_in  : in  std_logic;\n" +
                        "		rd_write_out : out std_logic;\n" +
                        "		rd_data_in   : in  std_logic_vector(31 downto 0);\n" +
                        "		rd_data_out  : out std_logic_vector(31 downto 0)\n" +
                        "	);\n" +
                        "end entity pp_writeback;\n" +
                        "\n" +
                        "architecture behaviour of pp_writeback is\n" +
                        "begin\n" +
                        "\n" +
                        "	pipeline_register: process(clk)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				rd_write_out <= '0';\n" +
                        "				exception_out <= '0';\n" +
                        "				count_instr_out <= '0';\n" +
                        "			else\n" +
                        "				count_instr_out <= count_instr_in;\n" +
                        "				rd_data_out <= rd_data_in;\n" +
                        "				rd_write_out <= rd_write_in;\n" +
                        "				rd_addr_out <= rd_addr_in;\n" +
                        "\n" +
                        "				exception_out <= exception_in;\n" +
                        "				exception_ctx_out <= exception_ctx_in;\n" +
                        "\n" +
                        "				csr_write_out <= csr_write_in;\n" +
                        "				csr_data_out <= csr_data_in;\n" +
                        "				csr_addr_out <= csr_addr_in;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process pipeline_register;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_pp_alu_control_unit_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/potato_processor/vhdl/pp_alu_control_unit.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "use work.pp_types.all;\n" +
                        "use work.pp_constants.all;\n" +
                        "\n" +
                        "entity pp_alu_control_unit is\n" +
                        "	port(\n" +
                        "		opcode  : in std_logic_vector( 4 downto 0);\n" +
                        "		funct3  : in std_logic_vector( 2 downto 0);\n" +
                        "		funct7  : in std_logic_vector( 6 downto 0);\n" +
                        "		\n" +
                        "		-- Sources of ALU operands:\n" +
                        "		alu_x_src, alu_y_src : out alu_operand_source;\n" +
                        "\n" +
                        "		-- ALU operation:\n" +
                        "		alu_op : out alu_operation\n" +
                        "	);\n" +
                        "end entity pp_alu_control_unit;\n" +
                        "\n" +
                        "architecture behaviour of pp_alu_control_unit is\n" +
                        "begin\n" +
                        "\n" +
                        "	decode_alu: process(opcode, funct3, funct7)\n" +
                        "	begin\n" +
                        "		case opcode is\n" +
                        "			when b\"01101\" => -- Load upper immediate\n" +
                        "				alu_x_src <= ALU_SRC_NULL;\n" +
                        "				alu_y_src <= ALU_SRC_IMM;\n" +
                        "				alu_op <= ALU_ADD;\n" +
                        "			when b\"00101\" => -- Add upper immediate to PC\n" +
                        "				alu_x_src <= ALU_SRC_PC;\n" +
                        "				alu_y_src <= ALU_SRC_IMM;\n" +
                        "				alu_op <= ALU_ADD;\n" +
                        "			when b\"11011\" => -- Jump and link\n" +
                        "				alu_x_src <= ALU_SRC_PC_NEXT;\n" +
                        "				alu_y_src <= ALU_SRC_NULL;\n" +
                        "				alu_op <= ALU_ADD;\n" +
                        "			when b\"11001\" => -- Jump and link register\n" +
                        "				alu_x_src <= ALU_SRC_PC_NEXT;\n" +
                        "				alu_y_src <= ALU_SRC_NULL;\n" +
                        "				alu_op <= ALU_ADD;\n" +
                        "			when b\"11000\" => -- Branch operations\n" +
                        "				-- The funct3 field decides which type of branch comparison is\n" +
                        "				-- done; this is decoded in the branch comparator module.\n" +
                        "				alu_x_src <= ALU_SRC_NULL;\n" +
                        "				alu_y_src <= ALU_SRC_NULL;\n" +
                        "				alu_op <= ALU_NOP;\n" +
                        "			when b\"00000\" => -- Load instruction\n" +
                        "				alu_x_src <= ALU_SRC_REG;\n" +
                        "				alu_y_src <= ALU_SRC_IMM;\n" +
                        "				alu_op <= ALU_ADD;\n" +
                        "			when b\"01000\" => -- Store instruction\n" +
                        "				alu_x_src <= ALU_SRC_REG;\n" +
                        "				alu_y_src <= ALU_SRC_IMM;\n" +
                        "				alu_op <= ALU_ADD;\n" +
                        "			when b\"00100\" => -- Register-immediate operations\n" +
                        "				alu_x_src <= ALU_SRC_REG;\n" +
                        "\n" +
                        "				if funct3 = b\"001\" or funct3 = b\"101\" then\n" +
                        "					alu_y_src <= ALU_SRC_SHAMT;\n" +
                        "				else\n" +
                        "					alu_y_src <= ALU_SRC_IMM;\n" +
                        "				end if;\n" +
                        "\n" +
                        "				case funct3 is\n" +
                        "					when b\"000\" =>\n" +
                        "						alu_op <= ALU_ADD;\n" +
                        "					when b\"001\" =>\n" +
                        "						alu_op <= ALU_SLL;\n" +
                        "					when b\"010\" =>\n" +
                        "						alu_op <= ALU_SLT;\n" +
                        "					when b\"011\" =>\n" +
                        "						alu_op <= ALU_SLTU;\n" +
                        "					when b\"100\" =>\n" +
                        "						alu_op <= ALU_XOR;\n" +
                        "					when b\"101\" =>\n" +
                        "						if funct7 = b\"0000000\" then\n" +
                        "							alu_op <= ALU_SRL;\n" +
                        "						else\n" +
                        "							alu_op <= ALU_SRA;\n" +
                        "						end if;\n" +
                        "					when b\"110\" =>\n" +
                        "						alu_op <= ALU_OR;\n" +
                        "					when b\"111\" =>\n" +
                        "						alu_op <= ALU_AND;\n" +
                        "					when others =>\n" +
                        "						alu_op <= ALU_INVALID;\n" +
                        "				end case; \n" +
                        "			when b\"01100\" => -- Register-register operations\n" +
                        "				alu_x_src <= ALU_SRC_REG;\n" +
                        "				alu_y_src <= ALU_SRC_REG;\n" +
                        "\n" +
                        "				case funct3 is\n" +
                        "					when b\"000\" =>\n" +
                        "						if funct7 = b\"0000000\" then\n" +
                        "							alu_op <= ALU_ADD;\n" +
                        "						else\n" +
                        "							alu_op <= ALU_SUB;\n" +
                        "						end if;\n" +
                        "					when b\"001\" =>\n" +
                        "						alu_op <= ALU_SLL;\n" +
                        "					when b\"010\" =>\n" +
                        "						alu_op <= ALU_SLT;\n" +
                        "					when b\"011\" =>\n" +
                        "						alu_op <= ALU_SLTU;\n" +
                        "					when b\"100\" =>\n" +
                        "						alu_op <= ALU_XOR;\n" +
                        "					when b\"101\" =>\n" +
                        "						if funct7 = b\"0000000\" then\n" +
                        "							alu_op <= ALU_SRL;\n" +
                        "						else\n" +
                        "							alu_op <= ALU_SRA;\n" +
                        "						end if;\n" +
                        "					when b\"110\" =>\n" +
                        "						alu_op <= ALU_OR;\n" +
                        "					when b\"111\" =>\n" +
                        "						alu_op <= ALU_AND;\n" +
                        "					when others =>\n" +
                        "						alu_op <= ALU_INVALID;\n" +
                        "				end case;\n" +
                        "			when b\"00011\" => -- Fence instructions, ignored\n" +
                        "				alu_x_src <= ALU_SRC_REG;\n" +
                        "				alu_y_src <= ALU_SRC_REG;\n" +
                        "				alu_op <= ALU_NOP;\n" +
                        "			when b\"11100\" => -- System instructions\n" +
                        "				alu_x_src <= ALU_SRC_CSR;\n" +
                        "				alu_y_src <= ALU_SRC_NULL;\n" +
                        "				alu_op <= ALU_ADD;\n" +
                        "			when others =>\n" +
                        "				alu_x_src <= ALU_SRC_REG;\n" +
                        "				alu_y_src <= ALU_SRC_REG;\n" +
                        "				alu_op <= ALU_INVALID;\n" +
                        "		end case;\n" +
                        "	end process decode_alu;\n" +
                        "\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_Time_Calculation_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/time_measurement/vhdl/Time_Calculation.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "entity Time_Calculation is\n" +
                        "	port(\n" +
                        "		clk 			: in std_logic;\n" +
                        "		reset 		: in std_logic;\n" +
                        "		\n" +
                        "		-- Time Signals\n" +
                        "		Time_Micro_Nano	: out std_logic_vector(31 downto 0);\n" +
                        "		Micro_Nano			: in std_logic; -- Micro = 1, Nano = 0\n" +
                        "		Start_Stop			: in std_logic; -- Start = 1, Stop = 0\n" +
                        "		\n" +
                        "		-- HEX interface\n" +
                        "		HEX0					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);\n" +
                        "		HEX1					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);\n" +
                        "		HEX2					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);\n" +
                        "		HEX3					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0)\n" +
                        "	);\n" +
                        "end entity Time_Calculation;\n" +
                        "\n" +
                        "architecture behaviour of Time_Calculation is\n" +
                        "\n" +
                        "signal counter 					: unsigned(31 downto 0);\n" +
                        "signal Time_Micro_Nano_S		: unsigned(31 downto 0);\n" +
                        "signal Time_Micro_Nano_S_64	: unsigned(63 downto 0);\n" +
                        "signal Time_Micro_Nano_S_std	: std_logic_vector(31 downto 0);\n" +
                        "signal write_data					: std_logic;\n" +
                        "\n" +
                        "begin\n" +
                        "	process(clk, reset, Micro_Nano, Start_Stop)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				counter <= (others => '0');\n" +
                        "				Time_Micro_Nano_S <= (others => '0');\n" +
                        "				write_data <= '1';\n" +
                        "			else\n" +
                        "				if Start_Stop = '1' then -- Starting or started\n" +
                        "					write_data <= '0';\n" +
                        "					counter <= counter + 1;\n" +
                        "					--if Micro_Nano = '1' then -- Micro\n" +
                        "						--Time_Micro_Nano_S	<= counter / 100;\n" +
                        "						Time_Micro_Nano_S	<= counter; -- mult and div take time (will not used)\n" +
                        "					--else -- Nano\n" +
                        "						--Time_Micro_Nano_S_64	<= counter * 10;\n" +
                        "						--Time_Micro_Nano_S		<= Time_Micro_Nano_S_64(31 downto 0);\n" +
                        "						--Time_Micro_Nano_S	<= counter; -- mult and div take time (will not used)\n" +
                        "					--end if;\n" +
                        "				else -- Stopping or stop\n" +
                        "					counter <= (others => '0');\n" +
                        "					write_data <= '1';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process;\n" +
                        "	\n" +
                        "	Time_Micro_Nano_S_std <= std_logic_vector(Time_Micro_Nano_S);\n" +
                        "	--Time_Micro_Nano <= Time_Micro_Nano_S_std;\n" +
                        "	\n" +
                        "	WriteToHexa: entity work.Write_To_Hexa\n" +
                        "		port map(\n" +
                        "			clk					=> clk,\n" +
                        "			reset					=> reset,\n" +
                        "			\n" +
                        "			-- Time Signals\n" +
                        "			Time_micro_Nano	=> Time_Micro_Nano_S_std,\n" +
                        "			write_data			=> write_data,\n" +
                        "			\n" +
                        "			-- HEX interface\n" +
                        "			HEX0					=> HEX0,\n" +
                        "			HEX1					=> HEX1,\n" +
                        "			HEX2					=> HEX2,\n" +
                        "			HEX3					=> HEX3\n" +
                        "		);\n" +
                        "		\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_Write_To_Hexa_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/time_measurement/vhdl/Write_To_Hexa.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "entity Write_To_Hexa is\n" +
                        "	port(\n" +
                        "		clk					: in std_logic;\n" +
                        "		reset					: in std_logic;\n" +
                        "		\n" +
                        "		-- Time Signals\n" +
                        "		Time_micro_Nano	: in std_logic_vector(31 downto 0);\n" +
                        "		write_data			: in std_logic;\n" +
                        "		\n" +
                        "		-- HEX interface\n" +
                        "		HEX0					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);\n" +
                        "		HEX1					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);\n" +
                        "		HEX2					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0);\n" +
                        "		HEX3					: OUT STD_LOGIC_VECTOR(6 DOWNTO 0)\n" +
                        "	);\n" +
                        "end entity Write_To_Hexa;\n" +
                        "\n" +
                        "architecture behaviour of Write_To_Hexa is\n" +
                        "\n" +
                        "component SEG7_LUT_4 is\n" +
                        "		port(\n" +
                        "			iDIG	: in  std_logic_vector(31 downto 0);\n" +
                        "			\n" +
                        "			oSEG0	: out std_logic_vector(6 downto 0);\n" +
                        "			oSEG1	: out std_logic_vector(6 downto 0);\n" +
                        "			oSEG2	: out std_logic_vector(6 downto 0);\n" +
                        "			oSEG3	: out std_logic_vector(6 downto 0)\n" +
                        "		);\n" +
                        "	end component;\n" +
                        "	\n" +
                        "signal Time_micro_Nano_S	: std_logic_vector(31 downto 0);\n" +
                        "signal count					: unsigned(31 downto 0) := (others => '0');\n" +
                        "signal ovf						: std_logic;\n" +
                        "\n" +
                        "begin\n" +
                        "	process(clk, reset)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				Time_micro_Nano_S <= (others => '0');\n" +
                        "			else\n" +
                        "				if ((write_data = '1') and (ovf = '1')) then\n" +
                        "					Time_micro_Nano_S <= Time_micro_Nano;\n" +
                        "				end if;\n" +
                        "				if (count >= 10000000) then\n" +
                        "					ovf <= '1';\n" +
                        "					if write_data = '1' then\n" +
                        "						count <= (others => '0');\n" +
                        "					end if;\n" +
                        "				else\n" +
                        "					count <= count + 1;\n" +
                        "					ovf <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process;\n" +
                        "	\n" +
                        "	lut_4: SEG7_LUT_4\n" +
                        "		port map(\n" +
                        "			iDIG => Time_micro_Nano_S,\n" +
                        "			oSEG0 => HEX0,\n" +
                        "			oSEG1 => HEX1,\n" +
                        "			oSEG2 => HEX2,\n" +
                        "			oSEG3 => HEX3\n" +
                        "		);\n" +
                        "		\n" +
                        "end architecture behaviour;";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_clock_generator_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/clock_generator/clock_generator.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- megafunction wizard: %PLL Intel FPGA IP v18.0%\n" +
                        "-- GENERATION: XML\n" +
                        "-- clock_generator.vhd\n" +
                        "\n" +
                        "-- Generated using ACDS version 18.0 614\n" +
                        "\n" +
                        "library IEEE;\n" +
                        "use IEEE.std_logic_1164.all;\n" +
                        "use IEEE.numeric_std.all;\n" +
                        "\n" +
                        "entity clock_generator is\n" +
                        "	port (\n" +
                        "		refclk   : in  std_logic := '0'; --  refclk.clk\n" +
                        "		rst      : in  std_logic := '0'; --   reset.reset\n" +
                        "		outclk_0 : out std_logic;        -- outclk0.clk\n" +
                        "		outclk_1 : out std_logic;        -- outclk1.clk\n" +
                        "		locked   : out std_logic         --  locked.export\n" +
                        "	);\n" +
                        "end entity clock_generator;\n" +
                        "\n" +
                        "architecture rtl of clock_generator is\n" +
                        "	component clock_generator_0002 is\n" +
                        "		port (\n" +
                        "			refclk   : in  std_logic := 'X'; -- clk\n" +
                        "			rst      : in  std_logic := 'X'; -- reset\n" +
                        "			outclk_0 : out std_logic;        -- clk\n" +
                        "			outclk_1 : out std_logic;        -- clk\n" +
                        "			locked   : out std_logic         -- export\n" +
                        "		);\n" +
                        "	end component clock_generator_0002;\n" +
                        "\n" +
                        "begin\n" +
                        "\n" +
                        "	clock_generator_inst : component clock_generator_0002\n" +
                        "		port map (\n" +
                        "			refclk   => refclk,   --  refclk.clk\n" +
                        "			rst      => rst,      --   reset.reset\n" +
                        "			outclk_0 => outclk_0, -- outclk0.clk\n" +
                        "			outclk_1 => outclk_1, -- outclk1.clk\n" +
                        "			locked   => locked    --  locked.export\n" +
                        "		);\n" +
                        "\n" +
                        "end architecture rtl; -- of clock_generator\n" +
                        "-- Retrieval info: <?xml version=\"1.0\"?>\n" +
                        "--<!--\n" +
                        "--	Generated by Altera MegaWizard Launcher Utility version 1.0\n" +
                        "--	************************************************************\n" +
                        "--	THIS IS A WIZARD-GENERATED FILE. DO NOT EDIT THIS FILE!\n" +
                        "--	************************************************************\n" +
                        "--	Copyright (C) 1991-2019 Altera Corporation\n" +
                        "--	Any megafunction design, and related net list (encrypted or decrypted),\n" +
                        "--	support information, device programming or simulation file, and any other\n" +
                        "--	associated documentation or information provided by Altera or a partner\n" +
                        "--	under Altera's Megafunction Partnership Program may be used only to\n" +
                        "--	program PLD devices (but not masked PLD devices) from Altera.  Any other\n" +
                        "--	use of such megafunction design, net list, support information, device\n" +
                        "--	programming or simulation file, or any other related documentation or\n" +
                        "--	information is prohibited for any other purpose, including, but not\n" +
                        "--	limited to modification, reverse engineering, de-compiling, or use with\n" +
                        "--	any other silicon devices, unless such use is explicitly licensed under\n" +
                        "--	a separate agreement with Altera or a megafunction partner.  Title to\n" +
                        "--	the intellectual property, including patents, copyrights, trademarks,\n" +
                        "--	trade secrets, or maskworks, embodied in any such megafunction design,\n" +
                        "--	net list, support information, device programming or simulation file, or\n" +
                        "--	any other related documentation or information provided by Altera or a\n" +
                        "--	megafunction partner, remains with Altera, the megafunction partner, or\n" +
                        "--	their respective licensors.  No other licenses, including any licenses\n" +
                        "--	needed under any third party's intellectual property, are provided herein.\n" +
                        "---->\n" +
                        "-- Retrieval info: <instance entity-name=\"altera_pll\" version=\"18.0\" >\n" +
                        "-- Retrieval info: 	<generic name=\"debug_print_output\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"debug_use_rbc_taf_method\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"device_family\" value=\"Cyclone V\" />\n" +
                        "-- Retrieval info: 	<generic name=\"device\" value=\"5CEBA2F17A7\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_device_speed_grade\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_pll_mode\" value=\"Integer-N PLL\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_reference_clock_frequency\" value=\"125.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_channel_spacing\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_operation_mode\" value=\"direct\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_feedback_clock\" value=\"Global Clock\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_fractional_cout\" value=\"32\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_dsm_out_sel\" value=\"1st_order\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_use_locked\" value=\"true\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_en_adv_params\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_number_of_clocks\" value=\"2\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_multiply_factor\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_frac_multiply_factor\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_n\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter0\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency0\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c0\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency0\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units0\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift0\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg0\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift0\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle0\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter1\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency1\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c1\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency1\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units1\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift1\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg1\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift1\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle1\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter2\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency2\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c2\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency2\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units2\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift2\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg2\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift2\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle2\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter3\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency3\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c3\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency3\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units3\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift3\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg3\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift3\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle3\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter4\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency4\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c4\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency4\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units4\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift4\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg4\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift4\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle4\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter5\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency5\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c5\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency5\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units5\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift5\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg5\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift5\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle5\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter6\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency6\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c6\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency6\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units6\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift6\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg6\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift6\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle6\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter7\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency7\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c7\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency7\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units7\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift7\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg7\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift7\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle7\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter8\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency8\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c8\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency8\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units8\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift8\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg8\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift8\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle8\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter9\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency9\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c9\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency9\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units9\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift9\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg9\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift9\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle9\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter10\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency10\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c10\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency10\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units10\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift10\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg10\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift10\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle10\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter11\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency11\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c11\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency11\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units11\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift11\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg11\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift11\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle11\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter12\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency12\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c12\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency12\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units12\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift12\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg12\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift12\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle12\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter13\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency13\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c13\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency13\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units13\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift13\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg13\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift13\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle13\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter14\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency14\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c14\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency14\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units14\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift14\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg14\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift14\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle14\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter15\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency15\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c15\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency15\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units15\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift15\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg15\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift15\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle15\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter16\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency16\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c16\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency16\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units16\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift16\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg16\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift16\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle16\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_counter17\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_output_clock_frequency17\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_divide_factor_c17\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_output_clock_frequency17\" value=\"0 MHz\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_ps_units17\" value=\"ps\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift17\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phase_shift_deg17\" value=\"0.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_actual_phase_shift17\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_duty_cycle17\" value=\"50\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_pll_auto_reset\" value=\"Off\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_pll_bandwidth_preset\" value=\"Auto\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_en_reconf\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_en_dps_ports\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_en_phout_ports\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_phout_division\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_mif_generate\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_enable_mif_dps\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_dps_cntr\" value=\"C0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_dps_num\" value=\"1\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_dps_dir\" value=\"Positive\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_refclk_switch\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_refclk1_frequency\" value=\"100.0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_switchover_mode\" value=\"Automatic Switchover\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_switchover_delay\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_active_clk\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_clk_bad\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_enable_cascade_out\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_cascade_outclk_index\" value=\"0\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_enable_cascade_in\" value=\"false\" />\n" +
                        "-- Retrieval info: 	<generic name=\"gui_pll_cascading_mode\" value=\"Create an adjpllin signal to connect with an upstream PLL\" />\n" +
                        "-- Retrieval info: </instance>\n" +
                        "-- IPFS_FILES : clock_generator.vho\n" +
                        "-- RELATED_FILES: clock_generator.vhd, clock_generator_0002.v";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_clock_generator_vho_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/clock_generator/clock_generator.vho";
        FileOutputStream fileOutSt = null;
        String data =   "--IP Functional Simulation Model\n" +
                        "--VERSION_BEGIN 18.0 cbx_mgl 2018:04:18:07:37:08:SJ cbx_simgen 2018:04:18:06:50:44:SJ  VERSION_END\n" +
                        "\n" +
                        "\n" +
                        "-- Copyright (C) 2018  Intel Corporation. All rights reserved.\n" +
                        "-- Your use of Intel Corporation's design tools, logic functions \n" +
                        "-- and other software and tools, and its AMPP partner logic \n" +
                        "-- functions, and any output files from any of the foregoing \n" +
                        "-- (including device programming or simulation files), and any \n" +
                        "-- associated documentation or information are expressly subject \n" +
                        "-- to the terms and conditions of the Intel Program License \n" +
                        "-- Subscription Agreement, the Intel Quartus Prime License Agreement,\n" +
                        "-- the Intel FPGA IP License Agreement, or other applicable license\n" +
                        "-- agreement, including, without limitation, that your use is for\n" +
                        "-- the sole purpose of programming logic devices manufactured by\n" +
                        "-- Intel and sold by Intel or its authorized distributors.  Please\n" +
                        "-- refer to the applicable agreement for further details.\n" +
                        "\n" +
                        "-- You may only use these simulation model output files for simulation\n" +
                        "-- purposes and expressly not for synthesis or any other purposes (in which\n" +
                        "-- event Intel disclaims all warranties of any kind).\n" +
                        "\n" +
                        "\n" +
                        "--synopsys translate_off\n" +
                        "\n" +
                        " LIBRARY altera_lnsim;\n" +
                        " USE altera_lnsim.altera_lnsim_components.all;\n" +
                        "\n" +
                        "--synthesis_resources = altera_pll 1 \n" +
                        " LIBRARY ieee;\n" +
                        " USE ieee.std_logic_1164.all;\n" +
                        "\n" +
                        " ENTITY  clock_generator IS \n" +
                        "	 PORT \n" +
                        "	 ( \n" +
                        "		 locked	:	OUT  STD_LOGIC;\n" +
                        "		 outclk_0	:	OUT  STD_LOGIC;\n" +
                        "		 outclk_1	:	OUT  STD_LOGIC;\n" +
                        "		 refclk	:	IN  STD_LOGIC;\n" +
                        "		 rst	:	IN  STD_LOGIC\n" +
                        "	 ); \n" +
                        " END clock_generator;\n" +
                        "\n" +
                        " ARCHITECTURE RTL OF clock_generator IS\n" +
                        "\n" +
                        "	 ATTRIBUTE synthesis_clearbox : natural;\n" +
                        "	 ATTRIBUTE synthesis_clearbox OF RTL : ARCHITECTURE IS 1;\n" +
                        "	 SIGNAL  wire_gnd	:	STD_LOGIC;\n" +
                        "	 SIGNAL  wire_clock_generator_altera_pll_altera_pll_i_1098_locked	:	STD_LOGIC;\n" +
                        "	 SIGNAL  wire_clock_generator_altera_pll_altera_pll_i_1098_outclk	:	STD_LOGIC_VECTOR (1 DOWNTO 0);\n" +
                        " BEGIN\n" +
                        "\n" +
                        "	wire_gnd <= '0';\n" +
                        "	locked <= wire_clock_generator_altera_pll_altera_pll_i_1098_locked;\n" +
                        "	outclk_0 <= wire_clock_generator_altera_pll_altera_pll_i_1098_outclk(0);\n" +
                        "	outclk_1 <= wire_clock_generator_altera_pll_altera_pll_i_1098_outclk(1);\n" +
                        "	clock_generator_altera_pll_altera_pll_i_1098 :  altera_pll\n" +
                        "	  GENERIC MAP (\n" +
                        "		c_cnt_bypass_en0 => \"false\",\n" +
                        "		c_cnt_bypass_en1 => \"false\",\n" +
                        "		c_cnt_bypass_en10 => \"false\",\n" +
                        "		c_cnt_bypass_en11 => \"false\",\n" +
                        "		c_cnt_bypass_en12 => \"false\",\n" +
                        "		c_cnt_bypass_en13 => \"false\",\n" +
                        "		c_cnt_bypass_en14 => \"false\",\n" +
                        "		c_cnt_bypass_en15 => \"false\",\n" +
                        "		c_cnt_bypass_en16 => \"false\",\n" +
                        "		c_cnt_bypass_en17 => \"false\",\n" +
                        "		c_cnt_bypass_en2 => \"false\",\n" +
                        "		c_cnt_bypass_en3 => \"false\",\n" +
                        "		c_cnt_bypass_en4 => \"false\",\n" +
                        "		c_cnt_bypass_en5 => \"false\",\n" +
                        "		c_cnt_bypass_en6 => \"false\",\n" +
                        "		c_cnt_bypass_en7 => \"false\",\n" +
                        "		c_cnt_bypass_en8 => \"false\",\n" +
                        "		c_cnt_bypass_en9 => \"false\",\n" +
                        "		c_cnt_hi_div0 => 1,\n" +
                        "		c_cnt_hi_div1 => 1,\n" +
                        "		c_cnt_hi_div10 => 1,\n" +
                        "		c_cnt_hi_div11 => 1,\n" +
                        "		c_cnt_hi_div12 => 1,\n" +
                        "		c_cnt_hi_div13 => 1,\n" +
                        "		c_cnt_hi_div14 => 1,\n" +
                        "		c_cnt_hi_div15 => 1,\n" +
                        "		c_cnt_hi_div16 => 1,\n" +
                        "		c_cnt_hi_div17 => 1,\n" +
                        "		c_cnt_hi_div2 => 1,\n" +
                        "		c_cnt_hi_div3 => 1,\n" +
                        "		c_cnt_hi_div4 => 1,\n" +
                        "		c_cnt_hi_div5 => 1,\n" +
                        "		c_cnt_hi_div6 => 1,\n" +
                        "		c_cnt_hi_div7 => 1,\n" +
                        "		c_cnt_hi_div8 => 1,\n" +
                        "		c_cnt_hi_div9 => 1,\n" +
                        "		c_cnt_in_src0 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src1 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src10 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src11 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src12 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src13 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src14 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src15 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src16 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src17 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src2 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src3 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src4 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src5 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src6 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src7 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src8 => \"ph_mux_clk\",\n" +
                        "		c_cnt_in_src9 => \"ph_mux_clk\",\n" +
                        "		c_cnt_lo_div0 => 1,\n" +
                        "		c_cnt_lo_div1 => 1,\n" +
                        "		c_cnt_lo_div10 => 1,\n" +
                        "		c_cnt_lo_div11 => 1,\n" +
                        "		c_cnt_lo_div12 => 1,\n" +
                        "		c_cnt_lo_div13 => 1,\n" +
                        "		c_cnt_lo_div14 => 1,\n" +
                        "		c_cnt_lo_div15 => 1,\n" +
                        "		c_cnt_lo_div16 => 1,\n" +
                        "		c_cnt_lo_div17 => 1,\n" +
                        "		c_cnt_lo_div2 => 1,\n" +
                        "		c_cnt_lo_div3 => 1,\n" +
                        "		c_cnt_lo_div4 => 1,\n" +
                        "		c_cnt_lo_div5 => 1,\n" +
                        "		c_cnt_lo_div6 => 1,\n" +
                        "		c_cnt_lo_div7 => 1,\n" +
                        "		c_cnt_lo_div8 => 1,\n" +
                        "		c_cnt_lo_div9 => 1,\n" +
                        "		c_cnt_odd_div_duty_en0 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en1 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en10 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en11 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en12 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en13 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en14 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en15 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en16 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en17 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en2 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en3 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en4 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en5 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en6 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en7 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en8 => \"false\",\n" +
                        "		c_cnt_odd_div_duty_en9 => \"false\",\n" +
                        "		c_cnt_ph_mux_prst0 => 0,\n" +
                        "		c_cnt_ph_mux_prst1 => 0,\n" +
                        "		c_cnt_ph_mux_prst10 => 0,\n" +
                        "		c_cnt_ph_mux_prst11 => 0,\n" +
                        "		c_cnt_ph_mux_prst12 => 0,\n" +
                        "		c_cnt_ph_mux_prst13 => 0,\n" +
                        "		c_cnt_ph_mux_prst14 => 0,\n" +
                        "		c_cnt_ph_mux_prst15 => 0,\n" +
                        "		c_cnt_ph_mux_prst16 => 0,\n" +
                        "		c_cnt_ph_mux_prst17 => 0,\n" +
                        "		c_cnt_ph_mux_prst2 => 0,\n" +
                        "		c_cnt_ph_mux_prst3 => 0,\n" +
                        "		c_cnt_ph_mux_prst4 => 0,\n" +
                        "		c_cnt_ph_mux_prst5 => 0,\n" +
                        "		c_cnt_ph_mux_prst6 => 0,\n" +
                        "		c_cnt_ph_mux_prst7 => 0,\n" +
                        "		c_cnt_ph_mux_prst8 => 0,\n" +
                        "		c_cnt_ph_mux_prst9 => 0,\n" +
                        "		c_cnt_prst0 => 1,\n" +
                        "		c_cnt_prst1 => 1,\n" +
                        "		c_cnt_prst10 => 1,\n" +
                        "		c_cnt_prst11 => 1,\n" +
                        "		c_cnt_prst12 => 1,\n" +
                        "		c_cnt_prst13 => 1,\n" +
                        "		c_cnt_prst14 => 1,\n" +
                        "		c_cnt_prst15 => 1,\n" +
                        "		c_cnt_prst16 => 1,\n" +
                        "		c_cnt_prst17 => 1,\n" +
                        "		c_cnt_prst2 => 1,\n" +
                        "		c_cnt_prst3 => 1,\n" +
                        "		c_cnt_prst4 => 1,\n" +
                        "		c_cnt_prst5 => 1,\n" +
                        "		c_cnt_prst6 => 1,\n" +
                        "		c_cnt_prst7 => 1,\n" +
                        "		c_cnt_prst8 => 1,\n" +
                        "		c_cnt_prst9 => 1,\n" +
                        "		clock_name_0 => \"UNUSED\",\n" +
                        "		clock_name_1 => \"UNUSED\",\n" +
                        "		clock_name_2 => \"UNUSED\",\n" +
                        "		clock_name_3 => \"UNUSED\",\n" +
                        "		clock_name_4 => \"UNUSED\",\n" +
                        "		clock_name_5 => \"UNUSED\",\n" +
                        "		clock_name_6 => \"UNUSED\",\n" +
                        "		clock_name_7 => \"UNUSED\",\n" +
                        "		clock_name_8 => \"UNUSED\",\n" +
                        "		clock_name_global_0 => \"false\",\n" +
                        "		clock_name_global_1 => \"false\",\n" +
                        "		clock_name_global_2 => \"false\",\n" +
                        "		clock_name_global_3 => \"false\",\n" +
                        "		clock_name_global_4 => \"false\",\n" +
                        "		clock_name_global_5 => \"false\",\n" +
                        "		clock_name_global_6 => \"false\",\n" +
                        "		clock_name_global_7 => \"false\",\n" +
                        "		clock_name_global_8 => \"false\",\n" +
                        "		data_rate => 0,\n" +
                        "		deserialization_factor => 4,\n" +
                        "		duty_cycle0 => 50,\n" +
                        "		duty_cycle1 => 50,\n" +
                        "		duty_cycle10 => 50,\n" +
                        "		duty_cycle11 => 50,\n" +
                        "		duty_cycle12 => 50,\n" +
                        "		duty_cycle13 => 50,\n" +
                        "		duty_cycle14 => 50,\n" +
                        "		duty_cycle15 => 50,\n" +
                        "		duty_cycle16 => 50,\n" +
                        "		duty_cycle17 => 50,\n" +
                        "		duty_cycle2 => 50,\n" +
                        "		duty_cycle3 => 50,\n" +
                        "		duty_cycle4 => 50,\n" +
                        "		duty_cycle5 => 50,\n" +
                        "		duty_cycle6 => 50,\n" +
                        "		duty_cycle7 => 50,\n" +
                        "		duty_cycle8 => 50,\n" +
                        "		duty_cycle9 => 50,\n" +
                        "		fractional_vco_multiplier => \"false\",\n" +
                        "		m_cnt_bypass_en => \"false\",\n" +
                        "		m_cnt_hi_div => 1,\n" +
                        "		m_cnt_lo_div => 1,\n" +
                        "		m_cnt_odd_div_duty_en => \"false\",\n" +
                        "		mimic_fbclk_type => \"gclk\",\n" +
                        "		n_cnt_bypass_en => \"false\",\n" +
                        "		n_cnt_hi_div => 1,\n" +
                        "		n_cnt_lo_div => 1,\n" +
                        "		n_cnt_odd_div_duty_en => \"false\",\n" +
                        "		number_of_clocks => 2,\n" +
                        "		operation_mode => \"direct\",\n" +
                        "		output_clock_frequency0 => \"100.000000 MHz\",\n" +
                        "		output_clock_frequency1 => \"100.000000 MHz\",\n" +
                        "		output_clock_frequency10 => \"0 MHz\",\n" +
                        "		output_clock_frequency11 => \"0 MHz\",\n" +
                        "		output_clock_frequency12 => \"0 MHz\",\n" +
                        "		output_clock_frequency13 => \"0 MHz\",\n" +
                        "		output_clock_frequency14 => \"0 MHz\",\n" +
                        "		output_clock_frequency15 => \"0 MHz\",\n" +
                        "		output_clock_frequency16 => \"0 MHz\",\n" +
                        "		output_clock_frequency17 => \"0 MHz\",\n" +
                        "		output_clock_frequency2 => \"0 MHz\",\n" +
                        "		output_clock_frequency3 => \"0 MHz\",\n" +
                        "		output_clock_frequency4 => \"0 MHz\",\n" +
                        "		output_clock_frequency5 => \"0 MHz\",\n" +
                        "		output_clock_frequency6 => \"0 MHz\",\n" +
                        "		output_clock_frequency7 => \"0 MHz\",\n" +
                        "		output_clock_frequency8 => \"0 MHz\",\n" +
                        "		output_clock_frequency9 => \"0 MHz\",\n" +
                        "		phase_shift0 => \"0 ps\",\n" +
                        "		phase_shift1 => \"0 ps\",\n" +
                        "		phase_shift10 => \"0 ps\",\n" +
                        "		phase_shift11 => \"0 ps\",\n" +
                        "		phase_shift12 => \"0 ps\",\n" +
                        "		phase_shift13 => \"0 ps\",\n" +
                        "		phase_shift14 => \"0 ps\",\n" +
                        "		phase_shift15 => \"0 ps\",\n" +
                        "		phase_shift16 => \"0 ps\",\n" +
                        "		phase_shift17 => \"0 ps\",\n" +
                        "		phase_shift2 => \"0 ps\",\n" +
                        "		phase_shift3 => \"0 ps\",\n" +
                        "		phase_shift4 => \"0 ps\",\n" +
                        "		phase_shift5 => \"0 ps\",\n" +
                        "		phase_shift6 => \"0 ps\",\n" +
                        "		phase_shift7 => \"0 ps\",\n" +
                        "		phase_shift8 => \"0 ps\",\n" +
                        "		phase_shift9 => \"0 ps\",\n" +
                        "		pll_auto_clk_sw_en => \"false\",\n" +
                        "		pll_bw_sel => \"low\",\n" +
                        "		pll_bwctrl => 0,\n" +
                        "		pll_clk_loss_sw_en => \"false\",\n" +
                        "		pll_clk_sw_dly => 0,\n" +
                        "		pll_clkin_0_src => \"clk_0\",\n" +
                        "		pll_clkin_1_src => \"clk_0\",\n" +
                        "		pll_cp_current => 0,\n" +
                        "		pll_dsm_out_sel => \"1st_order\",\n" +
                        "		pll_extclk_0_cnt_src => \"pll_extclk_cnt_src_vss\",\n" +
                        "		pll_extclk_1_cnt_src => \"pll_extclk_cnt_src_vss\",\n" +
                        "		pll_fbclk_mux_1 => \"glb\",\n" +
                        "		pll_fbclk_mux_2 => \"fb_1\",\n" +
                        "		pll_fractional_cout => 24,\n" +
                        "		pll_fractional_division => 1,\n" +
                        "		pll_m_cnt_in_src => \"ph_mux_clk\",\n" +
                        "		pll_manu_clk_sw_en => \"false\",\n" +
                        "		pll_output_clk_frequency => \"0 MHz\",\n" +
                        "		pll_slf_rst => \"false\",\n" +
                        "		pll_subtype => \"General\",\n" +
                        "		pll_type => \"General\",\n" +
                        "		pll_vco_div => 1,\n" +
                        "		pll_vcoph_div => 1,\n" +
                        "		refclk1_frequency => \"0 MHz\",\n" +
                        "		reference_clock_frequency => \"125.0 MHz\",\n" +
                        "		sim_additional_refclk_cycles_to_lock => 0\n" +
                        "	  )\n" +
                        "	  PORT MAP ( \n" +
                        "		fbclk => wire_gnd,\n" +
                        "		locked => wire_clock_generator_altera_pll_altera_pll_i_1098_locked,\n" +
                        "		outclk => wire_clock_generator_altera_pll_altera_pll_i_1098_outclk,\n" +
                        "		refclk => refclk,\n" +
                        "		rst => rst\n" +
                        "	  );\n" +
                        "\n" +
                        " END RTL; --clock_generator\n" +
                        "--synopsys translate_on\n" +
                        "--VALID FILE";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_clock_generator_002_v_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/clock_generator/clock_generator/clock_generator_0002.v";
        FileOutputStream fileOutSt = null;
        String data =   "`timescale 1ns/10ps\n" +
                        "module  clock_generator_0002(\n" +
                        "\n" +
                        "	// interface 'refclk'\n" +
                        "	input wire refclk,\n" +
                        "\n" +
                        "	// interface 'reset'\n" +
                        "	input wire rst,\n" +
                        "\n" +
                        "	// interface 'outclk0'\n" +
                        "	output wire outclk_0,\n" +
                        "\n" +
                        "	// interface 'outclk1'\n" +
                        "	output wire outclk_1,\n" +
                        "\n" +
                        "	// interface 'locked'\n" +
                        "	output wire locked\n" +
                        ");\n" +
                        "\n" +
                        "	altera_pll #(\n" +
                        "		.fractional_vco_multiplier(\"false\"),\n" +
                        "		.reference_clock_frequency(\"125.0 MHz\"),\n" +
                        "		.operation_mode(\"direct\"),\n" +
                        "		.number_of_clocks(2),\n" +
                        "		.output_clock_frequency0(\""+(Data.CPU_RV32_Freq/1000000)+".000000 MHz\"),\n" +
                        "		.phase_shift0(\"0 ps\"),\n" +
                        "		.duty_cycle0(50),\n" +
                        "		.output_clock_frequency1(\""+(Data.CPU_RV32_Timer_Freq/1000000)+".000000 MHz\"),\n" +
                        "		.phase_shift1(\"0 ps\"),\n" +
                        "		.duty_cycle1(50),\n" +
                        "		.output_clock_frequency2(\"0 MHz\"),\n" +
                        "		.phase_shift2(\"0 ps\"),\n" +
                        "		.duty_cycle2(50),\n" +
                        "		.output_clock_frequency3(\"0 MHz\"),\n" +
                        "		.phase_shift3(\"0 ps\"),\n" +
                        "		.duty_cycle3(50),\n" +
                        "		.output_clock_frequency4(\"0 MHz\"),\n" +
                        "		.phase_shift4(\"0 ps\"),\n" +
                        "		.duty_cycle4(50),\n" +
                        "		.output_clock_frequency5(\"0 MHz\"),\n" +
                        "		.phase_shift5(\"0 ps\"),\n" +
                        "		.duty_cycle5(50),\n" +
                        "		.output_clock_frequency6(\"0 MHz\"),\n" +
                        "		.phase_shift6(\"0 ps\"),\n" +
                        "		.duty_cycle6(50),\n" +
                        "		.output_clock_frequency7(\"0 MHz\"),\n" +
                        "		.phase_shift7(\"0 ps\"),\n" +
                        "		.duty_cycle7(50),\n" +
                        "		.output_clock_frequency8(\"0 MHz\"),\n" +
                        "		.phase_shift8(\"0 ps\"),\n" +
                        "		.duty_cycle8(50),\n" +
                        "		.output_clock_frequency9(\"0 MHz\"),\n" +
                        "		.phase_shift9(\"0 ps\"),\n" +
                        "		.duty_cycle9(50),\n" +
                        "		.output_clock_frequency10(\"0 MHz\"),\n" +
                        "		.phase_shift10(\"0 ps\"),\n" +
                        "		.duty_cycle10(50),\n" +
                        "		.output_clock_frequency11(\"0 MHz\"),\n" +
                        "		.phase_shift11(\"0 ps\"),\n" +
                        "		.duty_cycle11(50),\n" +
                        "		.output_clock_frequency12(\"0 MHz\"),\n" +
                        "		.phase_shift12(\"0 ps\"),\n" +
                        "		.duty_cycle12(50),\n" +
                        "		.output_clock_frequency13(\"0 MHz\"),\n" +
                        "		.phase_shift13(\"0 ps\"),\n" +
                        "		.duty_cycle13(50),\n" +
                        "		.output_clock_frequency14(\"0 MHz\"),\n" +
                        "		.phase_shift14(\"0 ps\"),\n" +
                        "		.duty_cycle14(50),\n" +
                        "		.output_clock_frequency15(\"0 MHz\"),\n" +
                        "		.phase_shift15(\"0 ps\"),\n" +
                        "		.duty_cycle15(50),\n" +
                        "		.output_clock_frequency16(\"0 MHz\"),\n" +
                        "		.phase_shift16(\"0 ps\"),\n" +
                        "		.duty_cycle16(50),\n" +
                        "		.output_clock_frequency17(\"0 MHz\"),\n" +
                        "		.phase_shift17(\"0 ps\"),\n" +
                        "		.duty_cycle17(50),\n" +
                        "		.pll_type(\"General\"),\n" +
                        "		.pll_subtype(\"General\")\n" +
                        "	) altera_pll_i (\n" +
                        "		.rst	(rst),\n" +
                        "		.outclk	({outclk_1, outclk_0}),\n" +
                        "		.locked	(locked),\n" +
                        "		.fboutclk	( ),\n" +
                        "		.fbclk	(1'b0),\n" +
                        "		.refclk	(refclk)\n" +
                        "	);\n" +
                        "endmodule";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_clock_generator_002_qip_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/clock_generator/clock_generator/clock_generator_0002.qip";
        FileOutputStream fileOutSt = null;
        String data =   "set_instance_assignment -name PLL_COMPENSATION_MODE DIRECT -to \"*clock_generator_0002*|altera_pll:altera_pll_i*|*\"\n" +
                        " \n" +
                        "set_instance_assignment -name PLL_AUTO_RESET OFF -to \"*clock_generator_0002*|altera_pll:altera_pll_i*|*\"\n" +
                        "set_instance_assignment -name PLL_BANDWIDTH_PRESET AUTO -to \"*clock_generator_0002*|altera_pll:altera_pll_i*|*\"";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_clock_generator_sip_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/clock_generator/clock_generator.sip";
        FileOutputStream fileOutSt = null;
        String data =   "set_global_assignment -entity \"clock_generator\" -library \"lib_clock_generator\" -name IP_TOOL_NAME \"altera_pll\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"lib_clock_generator\" -name IP_TOOL_VERSION \"18.0\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"lib_clock_generator\" -name IP_TOOL_ENV \"mwpim\"\n" +
                        "set_global_assignment -library \"lib_clock_generator\" -name SPD_FILE [file join $::quartus(sip_path) \"clock_generator.spd\"]\n" +
                        "\n" +
                        "set_global_assignment -library \"lib_clock_generator\" -name MISC_FILE [file join $::quartus(sip_path) \"clock_generator_sim/clock_generator.vho\"]";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_clock_generator_qip_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/clock_generator/clock_generator.qip";
        FileOutputStream fileOutSt = null;
        String data =   "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_TOOL_NAME \"altera_pll\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_TOOL_VERSION \"18.0\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_TOOL_ENV \"mwpim\"\n" +
                        "set_global_assignment -library \"clock_generator\" -name MISC_FILE [file join $::quartus(qip_path) \"clock_generator.cmp\"]\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_TARGETED_DEVICE_FAMILY \"Cyclone V\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_GENERATED_DEVICE_FAMILY \"{Cyclone V}\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_QSYS_MODE \"UNKNOWN\"\n" +
                        "set_global_assignment -name SYNTHESIS_ONLY_QIP ON\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_COMPONENT_NAME \"Y2xvY2tfZ2VuZXJhdG9y\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_COMPONENT_DISPLAY_NAME \"UExMIEludGVsIEZQR0EgSVA=\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_COMPONENT_REPORT_HIERARCHY \"Off\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_COMPONENT_INTERNAL \"Off\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_COMPONENT_AUTHOR \"SW50ZWwgQ29ycG9yYXRpb24=\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_COMPONENT_VERSION \"MTguMA==\"\n" +
                        "set_global_assignment -entity \"clock_generator\" -library \"clock_generator\" -name IP_COMPONENT_DESCRIPTION \"SW50ZWwgUGhhc2UtTG9ja2VkIExvb3A=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_NAME \"Y2xvY2tfZ2VuZXJhdG9yXzAwMDI=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_DISPLAY_NAME \"UExMIEludGVsIEZQR0EgSVA=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_REPORT_HIERARCHY \"Off\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_INTERNAL \"Off\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_AUTHOR \"SW50ZWwgQ29ycG9yYXRpb24=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_VERSION \"MTguMA==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_DESCRIPTION \"SW50ZWwgUGhhc2UtTG9ja2VkIExvb3A=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZGVidWdfcHJpbnRfb3V0cHV0::ZmFsc2U=::ZGVidWdfcHJpbnRfb3V0cHV0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZGVidWdfdXNlX3JiY190YWZfbWV0aG9k::ZmFsc2U=::ZGVidWdfdXNlX3JiY190YWZfbWV0aG9k\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZGV2aWNl::NUNFQkEyRjE3QTc=::ZGV2aWNl\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BsbF9tb2Rl::SW50ZWdlci1OIFBMTA==::UExMIE1vZGU=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZnJhY3Rpb25hbF92Y29fbXVsdGlwbGllcg==::ZmFsc2U=::ZnJhY3Rpb25hbF92Y29fbXVsdGlwbGllcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3JlZmVyZW5jZV9jbG9ja19mcmVxdWVuY3k=::MTI1LjA=::UmVmZXJlbmNlIENsb2NrIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cmVmZXJlbmNlX2Nsb2NrX2ZyZXF1ZW5jeQ==::MTI1LjAgTUh6::cmVmZXJlbmNlX2Nsb2NrX2ZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2NoYW5uZWxfc3BhY2luZw==::MC4w::Q2hhbm5lbCBTcGFjaW5n\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX29wZXJhdGlvbl9tb2Rl::ZGlyZWN0::T3BlcmF0aW9uIE1vZGU=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2ZlZWRiYWNrX2Nsb2Nr::R2xvYmFsIENsb2Nr::RmVlZGJhY2sgQ2xvY2s=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2ZyYWN0aW9uYWxfY291dA==::MzI=::RnJhY3Rpb25hbCBjYXJyeSBvdXQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RzbV9vdXRfc2Vs::MXN0X29yZGVy::RFNNIE9yZGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3BlcmF0aW9uX21vZGU=::ZGlyZWN0::b3BlcmF0aW9uX21vZGU=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3VzZV9sb2NrZWQ=::dHJ1ZQ==::RW5hYmxlIGxvY2tlZCBvdXRwdXQgcG9ydA==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2VuX2Fkdl9wYXJhbXM=::ZmFsc2U=::RW5hYmxlIHBoeXNpY2FsIG91dHB1dCBjbG9jayBwYXJhbWV0ZXJz\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX251bWJlcl9vZl9jbG9ja3M=::Mg==::TnVtYmVyIE9mIENsb2Nrcw==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"bnVtYmVyX29mX2Nsb2Nrcw==::Mg==::bnVtYmVyX29mX2Nsb2Nrcw==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX211bHRpcGx5X2ZhY3Rvcg==::MQ==::TXVsdGlwbHkgRmFjdG9yIChNLUNvdW50ZXIp\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2ZyYWNfbXVsdGlwbHlfZmFjdG9y::MQ==::RnJhY3Rpb25hbCBNdWx0aXBseSBGYWN0b3IgKEsp\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3Jfbg==::MQ==::RGl2aWRlIEZhY3RvciAoTi1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjA=::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kw::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzA=::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3Iw::MTI=::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjA=::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMA==::MTU=::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5MA==::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMA==::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0MA==::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzA=::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDA=::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUw::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjE=::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kx::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzE=::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3Ix::MTI=::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjE=::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMQ==::MTU=::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5MQ==::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMQ==::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0MQ==::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzE=::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDE=::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUx::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjI=::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3ky::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzI=::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3Iy::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjI=::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMg==::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5Mg==::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMg==::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0Mg==::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzI=::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDI=::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUy::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjM=::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kz::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzM=::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3Iz::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjM=::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMw==::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5Mw==::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMw==::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0Mw==::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzM=::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDM=::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUz::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjQ=::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3k0::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzQ=::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3I0::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjQ=::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yNA==::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5NA==::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzNA==::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0NA==::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzQ=::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDQ=::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGU0::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjU=::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3k1::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzU=::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3I1::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjU=::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yNQ==::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5NQ==::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzNQ==::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0NQ==::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzU=::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDU=::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGU1::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjY=::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3k2::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzY=::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3I2::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjY=::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yNg==::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5Ng==::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzNg==::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0Ng==::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzY=::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDY=::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGU2::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjc=::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3k3::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzc=::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3I3::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3Rvcjc=::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yNw==::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5Nw==::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzNw==::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0Nw==::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzc=::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDc=::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGU3::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjg=::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3k4::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzg=::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3I4::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3Rvcjg=::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yOA==::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5OA==::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzOA==::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0OA==::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzg=::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDg=::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGU4::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjk=::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3k5::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzk=::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3I5::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3Rvcjk=::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yOQ==::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5OQ==::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzOQ==::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0OQ==::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzk=::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDk=::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGU5::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjEw::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kxMA==::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzEw::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3IxMA==::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjEw::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMTA=::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5MTA=::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMTA=::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0MTA=::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzEw::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDEw::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUxMA==::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjEx::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kxMQ==::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzEx::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3IxMQ==::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjEx::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMTE=::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5MTE=::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMTE=::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0MTE=::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzEx::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDEx::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUxMQ==::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjEy::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kxMg==::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzEy::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3IxMg==::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjEy::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMTI=::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5MTI=::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMTI=::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0MTI=::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzEy::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDEy::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUxMg==::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjEz::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kxMw==::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzEz::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3IxMw==::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjEz::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMTM=::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5MTM=::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMTM=::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0MTM=::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzEz::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDEz::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUxMw==::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjE0::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kxNA==::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzE0::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3IxNA==::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjE0::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMTQ=::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5MTQ=::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMTQ=::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0MTQ=::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzE0::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDE0::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUxNA==::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjE1::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kxNQ==::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzE1::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3IxNQ==::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjE1::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMTU=::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5MTU=::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMTU=::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0MTU=::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzE1::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDE1::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUxNQ==::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjE2::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kxNg==::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzE2::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3IxNg==::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjE2::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMTY=::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5MTY=::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMTY=::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0MTY=::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzE2::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDE2::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUxNg==::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Nhc2NhZGVfY291bnRlcjE3::ZmFsc2U=::TWFrZSB0aGlzIGEgY2FzY2FkZSBjb3VudGVy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX291dHB1dF9jbG9ja19mcmVxdWVuY3kxNw==::MTAwLjA=::RGVzaXJlZCBGcmVxdWVuY3k=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2RpdmlkZV9mYWN0b3JfYzE3::MQ==::RGl2aWRlIEZhY3RvciAoQy1Db3VudGVyKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9tdWx0aXBseV9mYWN0b3IxNw==::MQ==::QWN0dWFsIE11bHRpcGx5IEZhY3Rvcg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9mcmFjX211bHRpcGx5X2ZhY3RvcjE3::MQ==::QWN0dWFsIEZyYWN0aW9uYWwgTXVsdGlwbHkgRmFjdG9yIChLKQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9kaXZpZGVfZmFjdG9yMTc=::MQ==::QWN0dWFsIERpdmlkZSBGYWN0b3I=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9vdXRwdXRfY2xvY2tfZnJlcXVlbmN5MTc=::MCBNSHo=::QWN0dWFsIEZyZXF1ZW5jeQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BzX3VuaXRzMTc=::cHM=::UGhhc2UgU2hpZnQgdW5pdHM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0MTc=::MA==::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BoYXNlX3NoaWZ0X2RlZzE3::MC4w::UGhhc2UgU2hpZnQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2FjdHVhbF9waGFzZV9zaGlmdDE3::MA==::QWN0dWFsIFBoYXNlIFNoaWZ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2R1dHlfY3ljbGUxNw==::NTA=::RHV0eSBDeWNsZQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTA=::MTAwLjAwMDAwMCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTA=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQw::MCBwcw==::cGhhc2Vfc2hpZnQw\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTA=::NTA=::ZHV0eV9jeWNsZTA=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTE=::MTAwLjAwMDAwMCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTE=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQx::MCBwcw==::cGhhc2Vfc2hpZnQx\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTE=::NTA=::ZHV0eV9jeWNsZTE=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTI=::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTI=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQy::MCBwcw==::cGhhc2Vfc2hpZnQy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTI=::NTA=::ZHV0eV9jeWNsZTI=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTM=::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQz::MCBwcw==::cGhhc2Vfc2hpZnQz\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTM=::NTA=::ZHV0eV9jeWNsZTM=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTQ=::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQ0::MCBwcw==::cGhhc2Vfc2hpZnQ0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTQ=::NTA=::ZHV0eV9jeWNsZTQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTU=::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTU=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQ1::MCBwcw==::cGhhc2Vfc2hpZnQ1\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTU=::NTA=::ZHV0eV9jeWNsZTU=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTY=::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTY=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQ2::MCBwcw==::cGhhc2Vfc2hpZnQ2\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTY=::NTA=::ZHV0eV9jeWNsZTY=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTc=::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTc=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQ3::MCBwcw==::cGhhc2Vfc2hpZnQ3\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTc=::NTA=::ZHV0eV9jeWNsZTc=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTg=::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTg=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQ4::MCBwcw==::cGhhc2Vfc2hpZnQ4\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTg=::NTA=::ZHV0eV9jeWNsZTg=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTk=::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTk=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQ5::MCBwcw==::cGhhc2Vfc2hpZnQ5\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTk=::NTA=::ZHV0eV9jeWNsZTk=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTEw::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTEw\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQxMA==::MCBwcw==::cGhhc2Vfc2hpZnQxMA==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTEw::NTA=::ZHV0eV9jeWNsZTEw\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTEx::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTEx\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQxMQ==::MCBwcw==::cGhhc2Vfc2hpZnQxMQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTEx::NTA=::ZHV0eV9jeWNsZTEx\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTEy::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTEy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQxMg==::MCBwcw==::cGhhc2Vfc2hpZnQxMg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTEy::NTA=::ZHV0eV9jeWNsZTEy\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTEz::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTEz\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQxMw==::MCBwcw==::cGhhc2Vfc2hpZnQxMw==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTEz::NTA=::ZHV0eV9jeWNsZTEz\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTE0::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTE0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQxNA==::MCBwcw==::cGhhc2Vfc2hpZnQxNA==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTE0::NTA=::ZHV0eV9jeWNsZTE0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTE1::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTE1\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQxNQ==::MCBwcw==::cGhhc2Vfc2hpZnQxNQ==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTE1::NTA=::ZHV0eV9jeWNsZTE1\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTE2::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTE2\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQxNg==::MCBwcw==::cGhhc2Vfc2hpZnQxNg==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTE2::NTA=::ZHV0eV9jeWNsZTE2\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTE3::MCBNSHo=::b3V0cHV0X2Nsb2NrX2ZyZXF1ZW5jeTE3\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGhhc2Vfc2hpZnQxNw==::MCBwcw==::cGhhc2Vfc2hpZnQxNw==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"ZHV0eV9jeWNsZTE3::NTA=::ZHV0eV9jeWNsZTE3\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BsbF9hdXRvX3Jlc2V0::T2Zm::UExMIEF1dG8gUmVzZXQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BsbF9iYW5kd2lkdGhfcHJlc2V0::QXV0bw==::UExMIEJhbmR3aWR0aCBQcmVzZXQ=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2VuX3JlY29uZg==::ZmFsc2U=::RW5hYmxlIGR5bmFtaWMgcmVjb25maWd1cmF0aW9uIG9mIFBMTA==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2VuX2Rwc19wb3J0cw==::ZmFsc2U=::RW5hYmxlIGFjY2VzcyB0byBkeW5hbWljIHBoYXNlIHNoaWZ0IHBvcnRz\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2VuX3Bob3V0X3BvcnRz::ZmFsc2U=::RW5hYmxlIGFjY2VzcyB0byBQTEwgRFBBIG91dHB1dCBwb3J0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGxsX3R5cGU=::R2VuZXJhbA==::UExMIFRZUEU=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"cGxsX3N1YnR5cGU=::R2VuZXJhbA==::UExMIFNVQlRZUEU=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BhcmFtZXRlcl9saXN0::TS1Db3VudGVyIEhpIERpdmlkZSxNLUNvdW50ZXIgTG93IERpdmlkZSxOLUNvdW50ZXIgSGkgRGl2aWRlLE4tQ291bnRlciBMb3cgRGl2aWRlLE0tQ291bnRlciBCeXBhc3MgRW5hYmxlLE4tQ291bnRlciBCeXBhc3MgRW5hYmxlLE0tQ291bnRlciBPZGQgRGl2aWRlIEVuYWJsZSxOLUNvdW50ZXIgT2RkIERpdmlkZSBFbmFibGUsQy1Db3VudGVyLTAgSGkgRGl2aWRlLEMtQ291bnRlci0wIExvdyBEaXZpZGUsQy1Db3VudGVyLTAgQ29hcnNlIFBoYXNlIFNoaWZ0LEMtQ291bnRlci0wIFZDTyBQaGFzZSBUYXAsQy1Db3VudGVyLTAgSW5wdXQgU291cmNlLEMtQ291bnRlci0wIEJ5cGFzcyBFbmFibGUsQy1Db3VudGVyLTAgT2RkIERpdmlkZSBFbmFibGUsQy1Db3VudGVyLTEgSGkgRGl2aWRlLEMtQ291bnRlci0xIExvdyBEaXZpZGUsQy1Db3VudGVyLTEgQ29hcnNlIFBoYXNlIFNoaWZ0LEMtQ291bnRlci0xIFZDTyBQaGFzZSBUYXAsQy1Db3VudGVyLTEgSW5wdXQgU291cmNlLEMtQ291bnRlci0xIEJ5cGFzcyBFbmFibGUsQy1Db3VudGVyLTEgT2RkIERpdmlkZSBFbmFibGUsVkNPIFBvc3QgRGl2aWRlIENvdW50ZXIgRW5hYmxlLENoYXJnZSBQdW1wIGN1cnJlbnQgKHVBKSxMb29wIEZpbHRlciBCYW5kd2lkdGggUmVzaXN0b3IgKE9obXMpICxQTEwgT3V0cHV0IFZDTyBGcmVxdWVuY3ksSy1GcmFjdGlvbmFsIERpdmlzaW9uIFZhbHVlIChEU00pLEZlZWRiYWNrIENsb2NrIFR5cGUsRmVlZGJhY2sgQ2xvY2sgTVVYIDEsRmVlZGJhY2sgQ2xvY2sgTVVYIDIsTSBDb3VudGVyIFNvdXJjZSBNVVgsUExMIEF1dG8gUmVzZXQ=::UGFyYW1ldGVyIE5hbWVz\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3BhcmFtZXRlcl92YWx1ZXM=::Niw2LDMsMixmYWxzZSxmYWxzZSxmYWxzZSx0cnVlLDIsMSwxLDAscGhfbXV4X2NsayxmYWxzZSx0cnVlLDIsMSwxLDAscGhfbXV4X2NsayxmYWxzZSx0cnVlLDIsMjAsNDAwMCwzMDAuMCBNSHosMSxub25lLGdsYixtX2NudCxwaF9tdXhfY2xrLGZhbHNl::UGFyYW1ldGVyIFZhbHVlcw==\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX21pZl9nZW5lcmF0ZQ==::ZmFsc2U=::R2VuZXJhdGUgTUlGIGZpbGU=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2VuYWJsZV9taWZfZHBz::ZmFsc2U=::RW5hYmxlIER5bmFtaWMgUGhhc2UgU2hpZnQgZm9yIE1JRiBzdHJlYW1pbmc=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Rwc19jbnRy::QzA=::RFBTIENvdW50ZXIgU2VsZWN0aW9u\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Rwc19udW0=::MQ==::TnVtYmVyIG9mIER5bmFtaWMgUGhhc2UgU2hpZnRz\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2Rwc19kaXI=::UG9zaXRpdmU=::RHluYW1pYyBQaGFzZSBTaGlmdCBEaXJlY3Rpb24=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX3JlZmNsa19zd2l0Y2g=::ZmFsc2U=::Q3JlYXRlIGEgc2Vjb25kIGlucHV0IGNsayAncmVmY2xrMSc=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2VuYWJsZV9jYXNjYWRlX291dA==::ZmFsc2U=::Q3JlYXRlIGEgJ2Nhc2NhZGVfb3V0JyBzaWduYWwgdG8gY29ubmVjdCB3aXRoIGEgZG93bnN0cmVhbSBQTEw=\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_COMPONENT_PARAMETER \"Z3VpX2VuYWJsZV9jYXNjYWRlX2lu::ZmFsc2U=::Q3JlYXRlIGFuIGFkanBsbGluIG9yIGNjbGsgc2lnbmFsIHRvIGNvbm5lY3Qgd2l0aCBhbiB1cHN0cmVhbSBQTEw=\"\n" +
                        "\n" +
                        "set_global_assignment -library \"clock_generator\" -name VHDL_FILE [file join $::quartus(qip_path) \"clock_generator.vhd\"]\n" +
                        "set_global_assignment -library \"clock_generator\" -name VERILOG_FILE [file join $::quartus(qip_path) \"clock_generator/clock_generator_0002.v\"]\n" +
                        "set_global_assignment -library \"clock_generator\" -name QIP_FILE [file join $::quartus(qip_path) \"clock_generator/clock_generator_0002.qip\"]\n" +
                        "\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_TOOL_NAME \"altera_pll\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_TOOL_VERSION \"18.0\"\n" +
                        "set_global_assignment -entity \"clock_generator_0002\" -library \"clock_generator\" -name IP_TOOL_ENV \"mwpim\"";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_aee_rom_vhd_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/aee_rom/vhdl/aee_rom.vhd";
        FileOutputStream fileOutSt = null;
        String data =   "-- megafunction wizard: %ROM: 1-PORT%\n" +
                        "-- GENERATION: STANDARD\n" +
                        "-- VERSION: WM1.0\n" +
                        "-- MODULE: altsyncram \n" +
                        "\n" +
                        "-- ============================================================\n" +
                        "-- File Name: aee_rom.vhd\n" +
                        "-- Megafunction Name(s):\n" +
                        "-- 			altsyncram\n" +
                        "--\n" +
                        "-- Simulation Library Files(s):\n" +
                        "-- 			altera_mf\n" +
                        "-- ============================================================\n" +
                        "-- ************************************************************\n" +
                        "-- THIS IS A WIZARD-GENERATED FILE. DO NOT EDIT THIS FILE!\n" +
                        "--\n" +
                        "-- 18.0.0 Build 614 04/24/2018 SJ Lite Edition\n" +
                        "-- ************************************************************\n" +
                        "\n" +
                        "\n" +
                        "--Copyright (C) 2018  Intel Corporation. All rights reserved.\n" +
                        "--Your use of Intel Corporation's design tools, logic functions \n" +
                        "--and other software and tools, and its AMPP partner logic \n" +
                        "--functions, and any output files from any of the foregoing \n" +
                        "--(including device programming or simulation files), and any \n" +
                        "--associated documentation or information are expressly subject \n" +
                        "--to the terms and conditions of the Intel Program License \n" +
                        "--Subscription Agreement, the Intel Quartus Prime License Agreement,\n" +
                        "--the Intel FPGA IP License Agreement, or other applicable license\n" +
                        "--agreement, including, without limitation, that your use is for\n" +
                        "--the sole purpose of programming logic devices manufactured by\n" +
                        "--Intel and sold by Intel or its authorized distributors.  Please\n" +
                        "--refer to the applicable agreement for further details.\n" +
                        "\n" +
                        "\n" +
                        "LIBRARY ieee;\n" +
                        "USE ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "LIBRARY altera_mf;\n" +
                        "USE altera_mf.altera_mf_components.all;\n" +
                        "\n" +
                        "ENTITY aee_rom IS\n" +
                        "	PORT\n" +
                        "	(\n" +
                        "		address		: IN STD_LOGIC_VECTOR (11 DOWNTO 0);\n" +
                        "		clock		: IN STD_LOGIC  := '1';\n" +
                        "		q		: OUT STD_LOGIC_VECTOR (31 DOWNTO 0)\n" +
                        "	);\n" +
                        "END aee_rom;\n" +
                        "\n" +
                        "\n" +
                        "ARCHITECTURE SYN OF aee_rom IS\n" +
                        "\n" +
                        "	SIGNAL sub_wire0	: STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "\n" +
                        "BEGIN\n" +
                        "	q    <= sub_wire0(31 DOWNTO 0);\n" +
                        "\n" +
                        "	altsyncram_component : altsyncram\n" +
                        "	GENERIC MAP (\n" +
                        "		address_aclr_a => \"NONE\",\n" +
                        "		clock_enable_input_a => \"BYPASS\",\n" +
                        "		clock_enable_output_a => \"BYPASS\",\n" +
                        "		init_file => \"bootloader.mif\",\n" +
                        "		intended_device_family => \"Cyclone V\",\n" +
                        "		lpm_hint => \"ENABLE_RUNTIME_MOD=NO\",\n" +
                        "		lpm_type => \"altsyncram\",\n" +
                        "		numwords_a => 4096,\n" +
                        "		operation_mode => \"ROM\",\n" +
                        "		outdata_aclr_a => \"NONE\",\n" +
                        "		outdata_reg_a => \"UNREGISTERED\",\n" +
                        "		widthad_a => 12,\n" +
                        "		width_a => 32,\n" +
                        "		width_byteena_a => 1\n" +
                        "	)\n" +
                        "	PORT MAP (\n" +
                        "		address_a => address,\n" +
                        "		clock0 => clock,\n" +
                        "		q_a => sub_wire0\n" +
                        "	);\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "END SYN;\n" +
                        "\n" +
                        "-- ============================================================\n" +
                        "-- CNX file retrieval info\n" +
                        "-- ============================================================\n" +
                        "-- Retrieval info: PRIVATE: ADDRESSSTALL_A NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: AclrAddr NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: AclrByte NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: AclrOutput NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: BYTE_ENABLE NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: BYTE_SIZE NUMERIC \"8\"\n" +
                        "-- Retrieval info: PRIVATE: BlankMemory NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: CLOCK_ENABLE_INPUT_A NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: CLOCK_ENABLE_OUTPUT_A NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: Clken NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: IMPLEMENT_IN_LES NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: INIT_FILE_LAYOUT STRING \"PORT_A\"\n" +
                        "-- Retrieval info: PRIVATE: INIT_TO_SIM_X NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: INTENDED_DEVICE_FAMILY STRING \"Cyclone V\"\n" +
                        "-- Retrieval info: PRIVATE: JTAG_ENABLED NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: JTAG_ID STRING \"NONE\"\n" +
                        "-- Retrieval info: PRIVATE: MAXIMUM_DEPTH NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: MIFfilename STRING \"bootloader.mif\"\n" +
                        "-- Retrieval info: PRIVATE: NUMWORDS_A NUMERIC \"4096\"\n" +
                        "-- Retrieval info: PRIVATE: RAM_BLOCK_TYPE NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: RegAddr NUMERIC \"1\"\n" +
                        "-- Retrieval info: PRIVATE: RegOutput NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: SYNTH_WRAPPER_GEN_POSTFIX STRING \"0\"\n" +
                        "-- Retrieval info: PRIVATE: SingleClock NUMERIC \"1\"\n" +
                        "-- Retrieval info: PRIVATE: UseDQRAM NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: WidthAddr NUMERIC \"12\"\n" +
                        "-- Retrieval info: PRIVATE: WidthData NUMERIC \"32\"\n" +
                        "-- Retrieval info: PRIVATE: rden NUMERIC \"0\"\n" +
                        "-- Retrieval info: LIBRARY: altera_mf altera_mf.altera_mf_components.all\n" +
                        "-- Retrieval info: CONSTANT: ADDRESS_ACLR_A STRING \"NONE\"\n" +
                        "-- Retrieval info: CONSTANT: CLOCK_ENABLE_INPUT_A STRING \"BYPASS\"\n" +
                        "-- Retrieval info: CONSTANT: CLOCK_ENABLE_OUTPUT_A STRING \"BYPASS\"\n" +
                        "-- Retrieval info: CONSTANT: INIT_FILE STRING \"bootloader.mif\"\n" +
                        "-- Retrieval info: CONSTANT: INTENDED_DEVICE_FAMILY STRING \"Cyclone V\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_HINT STRING \"ENABLE_RUNTIME_MOD=NO\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_TYPE STRING \"altsyncram\"\n" +
                        "-- Retrieval info: CONSTANT: NUMWORDS_A NUMERIC \"4096\"\n" +
                        "-- Retrieval info: CONSTANT: OPERATION_MODE STRING \"ROM\"\n" +
                        "-- Retrieval info: CONSTANT: OUTDATA_ACLR_A STRING \"NONE\"\n" +
                        "-- Retrieval info: CONSTANT: OUTDATA_REG_A STRING \"UNREGISTERED\"\n" +
                        "-- Retrieval info: CONSTANT: WIDTHAD_A NUMERIC \"12\"\n" +
                        "-- Retrieval info: CONSTANT: WIDTH_A NUMERIC \"32\"\n" +
                        "-- Retrieval info: CONSTANT: WIDTH_BYTEENA_A NUMERIC \"1\"\n" +
                        "-- Retrieval info: USED_PORT: address 0 0 12 0 INPUT NODEFVAL \"address[11..0]\"\n" +
                        "-- Retrieval info: USED_PORT: clock 0 0 0 0 INPUT VCC \"clock\"\n" +
                        "-- Retrieval info: USED_PORT: q 0 0 32 0 OUTPUT NODEFVAL \"q[31..0]\"\n" +
                        "-- Retrieval info: CONNECT: @address_a 0 0 12 0 address 0 0 12 0\n" +
                        "-- Retrieval info: CONNECT: @clock0 0 0 0 0 clock 0 0 0 0\n" +
                        "-- Retrieval info: CONNECT: q 0 0 32 0 @q_a 0 0 32 0\n" +
                        "-- Retrieval info: GEN_FILE: TYPE_NORMAL aee_rom.vhd TRUE\n" +
                        "-- Retrieval info: GEN_FILE: TYPE_NORMAL aee_rom.inc FALSE\n" +
                        "-- Retrieval info: GEN_FILE: TYPE_NORMAL aee_rom.cmp TRUE\n" +
                        "-- Retrieval info: GEN_FILE: TYPE_NORMAL aee_rom.bsf FALSE\n" +
                        "-- Retrieval info: GEN_FILE: TYPE_NORMAL aee_rom_inst.vhd FALSE\n" +
                        "-- Retrieval info: LIB_FILE: altera_mf";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
       
    private void generate_SEG7_LUT_4_v_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/time_measurement/verilog/SEG7_LUT_4.v";
        FileOutputStream fileOutSt = null;
        String data =   "module SEG7_LUT_4 (	oSEG0,oSEG1,oSEG2,oSEG3,iDIG );\n" +
                        "input	[31:0]	iDIG;\n" +
                        "output	[6:0]	oSEG0,oSEG1,oSEG2,oSEG3;\n" +
                        "\n" +
                        "SEG7_LUT	u0	(	oSEG0,iDIG[3:0]		);\n" +
                        "SEG7_LUT	u1	(	oSEG1,iDIG[7:4]		);\n" +
                        "SEG7_LUT	u2	(	oSEG2,iDIG[11:8]	);\n" +
                        "SEG7_LUT	u3	(	oSEG3,iDIG[15:12]	);\n" +
                        "\n" +
                        "\n" +
                        "endmodule";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_SEG7_LUT_v_file(String Project_Folder_File) {
        Project_Folder_File = Project_Folder_File + "hdl_code/peripherals/func_block_constant/time_measurement/verilog/SEG7_LUT.v";
        FileOutputStream fileOutSt = null;
        String data =   "module SEG7_LUT	(	oSEG,iDIG	);\n" +
                        "input	[3:0]	iDIG;\n" +
                        "output	[6:0]	oSEG;\n" +
                        "reg		[6:0]	oSEG;\n" +
                        "\n" +
                        "always @(iDIG)\n" +
                        "begin\n" +
                        "		case(iDIG)\n" +
                        "		4'h1: oSEG = 7'b1111001;	// ---t----\n" +
                        "		4'h2: oSEG = 7'b0100100; 	// |	  |\n" +
                        "		4'h3: oSEG = 7'b0110000; 	// lt	 rt\n" +
                        "		4'h4: oSEG = 7'b0011001; 	// |	  |\n" +
                        "		4'h5: oSEG = 7'b0010010; 	// ---m----\n" +
                        "		4'h6: oSEG = 7'b0000010; 	// |	  |\n" +
                        "		4'h7: oSEG = 7'b1111000; 	// lb	 rb\n" +
                        "		4'h8: oSEG = 7'b0000000; 	// |	  |\n" +
                        "		4'h9: oSEG = 7'b0011000; 	// ---b----\n" +
                        "		4'ha: oSEG = 7'b0001000;\n" +
                        "		4'hb: oSEG = 7'b0000011;\n" +
                        "		4'hc: oSEG = 7'b1000110;\n" +
                        "		4'hd: oSEG = 7'b0100001;\n" +
                        "		4'he: oSEG = 7'b0000110;\n" +
                        "		4'hf: oSEG = 7'b0001110;\n" +
                        "		4'h0: oSEG = 7'b1000000;\n" +
                        "		endcase\n" +
                        "end\n" +
                        "\n" +
                        "endmodule";
        try {
            new File(Project_Folder_File).delete();
            fileOutSt = new FileOutputStream(Project_Folder_File);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void generate_TON_Peripheral_vhd_file(String Project_Folder_File) {
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "--! @brief Simple memory module for use in Wishbone-based systems.\n" +
                        "--!\n" +
                        "--! The following registers are defined:\n" +
                        "--! |---------|-------------------|\n" +
                        "--! | Address | Description       |\n" +
                        "--! |---------|-------------------|\n" +
                        "--! | 0x00 r  | Elapsed Time L    |\n" +
                        "--! | 0x04 r  | Elapsed Time H    |\n" +
                        "--! | 0x08 r  | Q                 |\n" +
                        "--! | 0x00 w  | Preset  Time L    |\n" +
                        "--! | 0x04 w  | Preset  Time H    |\n" +
                        "--! | 0x08 w  | IN                |\n" +
                        "--! |---------|-------------------|\n" +
                        "\n" +
                        "entity TON_Peripheral is\n" +
                        "	port(\n" +
                        "		clk : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_adr_in  : in  std_logic_vector(1 downto 0);\n" +
                        "		wb_dat_in  : in  std_logic_vector(31 downto 0);\n" +
                        "		wb_dat_out : out std_logic_vector(31 downto 0);\n" +
                        "		wb_cyc_in  : in  std_logic;\n" +
                        "		wb_stb_in  : in  std_logic;\n" +
                        "		wb_sel_in  : in  std_logic_vector( 3 downto 0);\n" +
                        "		wb_we_in   : in  std_logic;\n" +
                        "		wb_ack_out : out std_logic\n" +
                        "	);\n" +
                        "end entity TON_Peripheral;\n" +
                        "\n" +
                        "architecture behaviour of TON_Peripheral is\n" +
                        "	type state_type is (IDLE, ACK);\n" +
                        "	signal state : state_type;\n" +
                        "\n" +
                        "	signal read_ack : std_logic;\n" +
                        "	\n" +
                        "	SIGNAL IN_T, Q   : std_logic;\n" +
                        "	SIGNAL PT, ET    : std_logic_vector(63 DOWNTO 0);\n" +
                        "	\n" +
                        "begin\n" +
                        "\n" +
                        "	Counter: entity work.Counter_Down_64_bit_Cin  PORT MAP(IN_T, clk, PT, Q, ET);\n" +
                        "	\n" +
                        "	wb_ack_out <= read_ack and wb_stb_in;\n" +
                        "\n" +
                        "	process(reset, wb_adr_in, wb_dat_in, wb_cyc_in, wb_stb_in, wb_sel_in, wb_we_in)\n" +
                        "	begin\n" +
                        "		--if rising_edge(clk) then add clk in  process and remove others\n" +
                        "			if reset = '1' then\n" +
                        "				read_ack <= '0';\n" +
                        "				state <= IDLE;\n" +
                        "				wb_dat_out <= (OTHERS => '0');\n" +
                        "				PT       <= (OTHERS => '0');\n" +
                        "				IN_T     <= '0';\n" +
                        "			else\n" +
                        "				if wb_cyc_in = '1' then\n" +
                        "					case state is\n" +
                        "						when IDLE =>\n" +
                        "							if wb_stb_in = '1' and wb_we_in = '1' then --write\n" +
                        "								IF (wb_adr_in = \"00\") THEN\n" +
                        "									PT(31 DOWNTO 0)  <= wb_dat_in;\n" +
                        "								ELSIF (wb_adr_in = \"01\") THEN\n" +
                        "									PT(63 DOWNTO 32) <= wb_dat_in;\n" +
                        "								ELSIF (wb_adr_in = \"10\") THEN\n" +
                        "									IN_T <= wb_dat_in(0);\n" +
                        "								END IF;\n" +
                        "								read_ack <= '1';\n" +
                        "								state <= ACK;\n" +
                        "							elsif wb_stb_in = '1' then -- read\n" +
                        "								IF (wb_adr_in = \"00\") THEN\n" +
                        "									wb_dat_out  <= ET(31 DOWNTO 0);\n" +
                        "								ELSIF (wb_adr_in = \"01\") THEN\n" +
                        "									wb_dat_out <= ET(63 DOWNTO 32);\n" +
                        "								ELSIF (wb_adr_in = \"10\") THEN\n" +
                        "									wb_dat_out(0) <= Q;\n" +
                        "								ELSE\n" +
                        "									wb_dat_out <= (others => '0');\n" +
                        "								END IF;\n" +
                        "								read_ack <= '1';\n" +
                        "								state <= ACK;\n" +
                        "							end if;\n" +
                        "						when ACK =>\n" +
                        "							if wb_stb_in = '0' then\n" +
                        "								read_ack <= '0';\n" +
                        "								state <= IDLE;\n" +
                        "							end if;\n" +
                        "					end case;\n" +
                        "				else\n" +
                        "					state <= IDLE;\n" +
                        "					read_ack <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		--end if;\n" +
                        "	end process;\n" +
                        "\n" +
                        "end architecture behaviour;";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/func_block_variable/TON/vhdl/TON_Peripheral.vhd", data);
    }
    
    private void generate_Timer_on_64_Controller_vhd_file(String Project_Folder_File) {
        String data =   "LIBRARY IEEE;\n" +
                        "USE IEEE.STD_LOGIC_1164.ALL;\n" +
                        "USE IEEE.STD_LOGIC_UNSIGNED.ALL;\n" +
                        "\n" +
                        "ENTITY Timer_on_64_Controller IS\n" +
                        "	PORT( \n" +
                        "		clk		: IN STD_LOGIC;\n" +
                        "		reset		: IN STD_LOGIC;\n" +
                        "		EN			: IN STD_LOGIC;\n" +
                        "		MW			: IN STD_LOGIC;\n" +
                        "		Address	: IN STD_LOGIC_VECTOR(1 DOWNTO 0);\n" +
                        "		Data_In	: IN STD_LOGIC_VECTOR(31 DOWNTO 0);\n" +
                        "		Data_Out	: OUT STD_LOGIC_VECTOR(31 DOWNTO 0)\n" +
                        "	);\n" +
                        "END Timer_on_64_Controller;\n" +
                        "\n" +
                        "ARCHITECTURE RTL OF Timer_on_64_Controller IS\n" +
                        "	COMPONENT Counter_Down_64_bit_Cin\n" +
                        "	port(En, clk  : IN std_logic;\n" +
                        "        C_in     : IN std_logic_vector(63 DOWNTO 0);\n" +
                        "        ov       : OUT std_logic;    -- over flow\n" +
                        "        C_Out    : OUT std_logic_vector(63 DOWNTO 0)\n" +
                        "	); \n" +
                        "	END COMPONENT;\n" +
                        "	\n" +
                        "	SIGNAL IN_T, Q   : std_logic;\n" +
                        "	SIGNAL PT, ET    : std_logic_vector(63 DOWNTO 0);\n" +
                        "	BEGIN\n" +
                        "	\n" +
                        "	Counter: Counter_Down_64_bit_Cin  PORT MAP(IN_T, clk, PT, Q, ET);\n" +
                        "	\n" +
                        "	PROCESS(clk)\n" +
                        "	BEGIN\n" +
                        "		IF reset = '1' THEN\n" +
                        "			Data_Out <= (OTHERS => '0');\n" +
                        "			PT       <= (OTHERS => '0');\n" +
                        "			IN_T     <= '0';\n" +
                        "		ELSIF (rising_edge(clk) and EN='1') THEN\n" +
                        "			IF MW='1' THEN\n" +
                        "				IF (conv_integer(Address) = 0) THEN\n" +
                        "					PT(31 DOWNTO 0)  <= Data_In;\n" +
                        "				ELSIF (conv_integer(Address) = 1) THEN\n" +
                        "					PT(63 DOWNTO 32) <= Data_In;\n" +
                        "				ELSIF (conv_integer(Address) = 2) THEN\n" +
                        "					IN_T <= Data_In(0);\n" +
                        "				END IF;\n" +
                        "			ELSIF MW='0' THEN\n" +
                        "				IF (conv_integer(Address) = 0) THEN\n" +
                        "					Data_Out  <= ET(31 DOWNTO 0);\n" +
                        "				ELSIF (conv_integer(Address) = 1) THEN\n" +
                        "					Data_Out <= ET(63 DOWNTO 32);\n" +
                        "				ELSIF (conv_integer(Address) = 2) THEN\n" +
                        "					Data_Out(0) <= Q;\n" +
                        "				END IF;\n" +
                        "			END IF;\n" +
                        "		END IF;\n" +
                        "	END PROCESS;\n" +
                        "END RTL;";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/func_block_variable/TON/vhdl/Timer_on_64_Controller.vhd", data);
    }
    
    private void generate_Counter_Down_64_bit_Cin_vhd_file(String Project_Folder_File) {
        String data =   "--\n" +
                        "-- VHDL Architecture Hossameldin_VHDL.Counter_Up_Down_4_bit_Cin_2_to_13.rtl\n" +
                        "--\n" +
                        "-- Created:\n" +
                        "--          by - Hossameldin.UNKNOWN (HOSSAMELDIN-PC)\n" +
                        "--          at - 13:34:57 11/20/2016\n" +
                        "--\n" +
                        "-- using Mentor Graphics HDL Designer(TM) 2007.1 (Build 19)\n" +
                        "--\n" +
                        "LIBRARY ieee;\n" +
                        "USE ieee.std_logic_1164.ALL;\n" +
                        "USE IEEE.STD_LOGIC_ARITH.ALL;\n" +
                        "\n" +
                        "ENTITY Counter_Down_64_bit_Cin IS\n" +
                        "	port(En, clk  : in std_logic;\n" +
                        "        C_in     : in std_logic_vector(63 downto 0);\n" +
                        "        ov       : out std_logic;    -- over flow\n" +
                        "        C_Out    : out std_logic_vector(63 downto 0)\n" +
                        "	); \n" +
                        "END ENTITY Counter_Down_64_bit_Cin;\n" +
                        "\n" +
                        "--\n" +
                        "ARCHITECTURE rtl OF Counter_Down_64_bit_Cin IS\n" +
                        "signal C_Tmp, C_O_Tmp, C_in_un : unsigned(63 downto 0);\n" +
                        "BEGIN\n" +
                        "	process(clk, En)\n" +
                        "	begin\n" +
                        "		C_in_un <= unsigned(C_in);\n" +
                        "		if En = '0' then \n" +
                        "			C_Tmp   <= C_in_un;\n" +
                        "			C_O_Tmp <= (others => '0'); \n" +
                        "			ov <='0';\n" +
                        "		elsif rising_edge(clk) then\n" +
                        "			if C_Tmp < 1 then \n" +
                        "				C_Tmp   <= (others => '0'); \n" +
                        "				ov <='1';\n" +
                        "			else \n" +
                        "				C_Tmp   <= C_Tmp - 1;\n" +
                        "				C_O_Tmp <= C_O_Tmp + 1;\n" +
                        "				ov <='0';\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process;\n" +
                        "	C_Out <= std_logic_vector(C_O_Tmp);\n" +
                        "END ARCHITECTURE rtl;";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/func_block_variable/TON/vhdl/Counter_Down_64_bit_Cin.vhd", data);
    }
    
    private void generate_RV_FPGA_PLC_Potato_sdc_file(String Project_Folder_File) {
        String data =   "#**************************************************************\n" +
                        "# This .sdc file is created by Terasic Tool.\n" +
                        "# Users are recommended to modify this file to match users logic.\n" +
                        "#**************************************************************\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Create Clock\n" +
                        "#**************************************************************\n" +
                        "create_clock -period 8 [get_ports CLOCK_125_p]\n" +
                        "create_clock -period 20 [get_ports CLOCK_50_B5B]\n" +
                        "create_clock -period 20 [get_ports CLOCK_50_B6A]\n" +
                        "create_clock -period 20 [get_ports CLOCK_50_B7A]\n" +
                        "create_clock -period 20 [get_ports CLOCK_50_B8A]\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Create Generated Clock\n" +
                        "#**************************************************************\n" +
                        "derive_pll_clocks\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set Clock Latency\n" +
                        "#**************************************************************\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set Clock Uncertainty\n" +
                        "#**************************************************************\n" +
                        "derive_clock_uncertainty\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set Input Delay\n" +
                        "#**************************************************************\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set Output Delay\n" +
                        "#**************************************************************\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set Clock Groups\n" +
                        "#**************************************************************\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set False Path\n" +
                        "#**************************************************************\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set Multicycle Path\n" +
                        "#**************************************************************\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set Maximum Delay\n" +
                        "#**************************************************************\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set Minimum Delay\n" +
                        "#**************************************************************\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set Input Transition\n" +
                        "#**************************************************************\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "#**************************************************************\n" +
                        "# Set Load\n" +
                        "#**************************************************************";
        new GeneralFunctions().write_file(Project_Folder_File + "RV_FPGA_PLC_Potato.sdc", data);
    }
    
    private void generate_aee_rom_qip_file(String Project_Folder_File) {
        String data =   "set_global_assignment -name IP_TOOL_NAME \"ROM: 1-PORT\"\n" +
                        "set_global_assignment -name IP_TOOL_VERSION \"18.0\"\n" +
                        "set_global_assignment -name IP_GENERATED_DEVICE_FAMILY \"{Cyclone V}\"\n" +
                        "set_global_assignment -name VHDL_FILE [file join $::quartus(qip_path) \"aee_rom.vhd\"]\n" +
                        "set_global_assignment -name MISC_FILE [file join $::quartus(qip_path) \"aee_rom.cmp\"]";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/func_block_constant/aee_rom/vhdl/aee_rom.qip", data);
    }
    
    private void generate_aee_rom_cmp_file(String Project_Folder_File) {
        String data =   "--Copyright (C) 2018  Intel Corporation. All rights reserved.\n" +
                        "--Your use of Intel Corporation's design tools, logic functions \n" +
                        "--and other software and tools, and its AMPP partner logic \n" +
                        "--functions, and any output files from any of the foregoing \n" +
                        "--(including device programming or simulation files), and any \n" +
                        "--associated documentation or information are expressly subject \n" +
                        "--to the terms and conditions of the Intel Program License \n" +
                        "--Subscription Agreement, the Intel Quartus Prime License Agreement,\n" +
                        "--the Intel FPGA IP License Agreement, or other applicable license\n" +
                        "--agreement, including, without limitation, that your use is for\n" +
                        "--the sole purpose of programming logic devices manufactured by\n" +
                        "--Intel and sold by Intel or its authorized distributors.  Please\n" +
                        "--refer to the applicable agreement for further details.\n" +
                        "\n" +
                        "\n" +
                        "component aee_rom\n" +
                        "	PORT\n" +
                        "	(\n" +
                        "		address		: IN STD_LOGIC_VECTOR (11 DOWNTO 0);\n" +
                        "		clock		: IN STD_LOGIC  := '1';\n" +
                        "		q		: OUT STD_LOGIC_VECTOR (31 DOWNTO 0)\n" +
                        "	);\n" +
                        "end component;";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/func_block_constant/aee_rom/vhdl/aee_rom.cmp", data);
    }
    
    private void generate_PWM_Peripheral_vhd_file(String Project_Folder_File) {
        String data =   "-- The Potato Processor - A simple processor for FPGAs\n" +
                        "-- (c) Kristian Klomsten Skordal 2014 - 2015 <kristian.skordal@wafflemail.net>\n" +
                        "-- Report bugs and issues on <https://github.com/skordal/potato/issues>\n" +
                        "\n" +
                        "library ieee;\n" +
                        "use ieee.std_logic_1164.all;\n" +
                        "use ieee.numeric_std.all;\n" +
                        "\n" +
                        "--! @brief Simple memory module for use in Wishbone-based systems.\n" +
                        "--!\n" +
                        "--! The following registers are defined:\n" +
                        "--! |---------|-------------------|\n" +
                        "--! | Address | Description       |\n" +
                        "--! |---------|-------------------|\n" +
                        "--! | 0x00 r  | Q                 |\n" +
                        "--! | 0x04 w  | Frequency    L    |\n" +
                        "--! | 0x08 w  | Duty Cycle        |\n" +
                        "--! |---------|-------------------|\n" +
                        "\n" +
                        "entity PWM_Peripheral is\n" +
                        "	port(\n" +
                        "		clk : in std_logic;\n" +
                        "		reset : in std_logic;\n" +
                        "\n" +
                        "		-- Wishbone interface:\n" +
                        "		wb_adr_in  : in  std_logic_vector(1 downto 0);\n" +
                        "		wb_dat_in  : in  std_logic_vector(31 downto 0);\n" +
                        "		wb_dat_out : out std_logic_vector(31 downto 0);\n" +
                        "		wb_cyc_in  : in  std_logic;\n" +
                        "		wb_stb_in  : in  std_logic;\n" +
                        "		wb_sel_in  : in  std_logic_vector( 3 downto 0);\n" +
                        "		wb_we_in   : in  std_logic;\n" +
                        "		wb_ack_out : out std_logic\n" +
                        "	);\n" +
                        "end entity PWM_Peripheral;\n" +
                        "\n" +
                        "architecture behaviour of PWM_Peripheral is\n" +
                        "	type state_type is (IDLE, ACK);\n" +
                        "	signal state : state_type;\n" +
                        "\n" +
                        "	signal read_ack : std_logic;\n" +
                        "	\n" +
                        "	SIGNAL Q, EN				: std_logic;\n" +
                        "	SIGNAL Frq, DC, Frq_T	: std_logic_vector(31 DOWNTO 0);\n" +
                        "	\n" +
                        "	SIGNAL T_Count, Comp_Count, Comp_Count_T	: STD_LOGIC_VECTOR(31 downto 0);\n" +
                        "	SIGNAL Comp_Count_64								: STD_LOGIC_VECTOR(63 downto 0);\n" +
                        "begin\n" +
                        "\n" +
                        "	PWM : entity work.PWM_32_bit	PORT MAP(clk, reset, EN, T_Count, Comp_Count, Q);\n" +
                        "	Div1: entity work.Div_32_bit	PORT MAP(Frq_T, X\""+new GeneralFunctions().dec2hex_str(Data.CPU_RV32_Freq, 8)+"\", T_Count, open);\n" +
                        "	Div2: entity work.Div_32_bit	PORT MAP(X\"00000064\", T_Count, Comp_Count_T, open);\n" +
                        "	Mult: entity work.Mult_32_bit	PORT MAP(Comp_Count_T, DC, Comp_Count_64);\n" +
                        "	\n" +
                        "	Comp_Count <= Comp_Count_64(31 DOWNTO 0);\n" +
                        "	EN			<= '1';\n" +
                        "	wb_ack_out <= read_ack and wb_stb_in;\n" +
                        "	\n" +
                        "	process(Frq)\n" +
                        "	begin\n" +
                        "		if( Frq = X\"00000000\") or (reset = '1') then\n" +
                        "			Frq_T <= X\""+new GeneralFunctions().dec2hex_str(Data.CPU_RV32_Freq, 8)+"\";\n" +
                        "		else\n" +
                        "			Frq_T <= Frq;\n" +
                        "		end if;\n" +
                        "	end process;\n" +
                        "	\n" +
                        "	process(clk, reset, wb_adr_in, wb_dat_in, wb_cyc_in, wb_stb_in, wb_sel_in, wb_we_in)\n" +
                        "	begin\n" +
                        "		if rising_edge(clk) then\n" +
                        "			if reset = '1' then\n" +
                        "				read_ack <= '0';\n" +
                        "				state <= IDLE;\n" +
                        "				wb_dat_out	<= (OTHERS => '0');\n" +
                        "				Frq			<= (OTHERS => '0');\n" +
                        "				DC				<= (OTHERS => '0');\n" +
                        "			else\n" +
                        "				if wb_cyc_in = '1' then\n" +
                        "					case state is\n" +
                        "						when IDLE =>\n" +
                        "							if wb_stb_in = '1' and wb_we_in = '1' then --write\n" +
                        "								IF (wb_adr_in = \"01\") THEN\n" +
                        "									Frq	<= wb_dat_in;\n" +
                        "								ELSIF (wb_adr_in = \"10\") THEN\n" +
                        "									DC		<= wb_dat_in;\n" +
                        "								END IF;\n" +
                        "								read_ack <= '1';\n" +
                        "								state <= ACK;\n" +
                        "							elsif wb_stb_in = '1' then -- read\n" +
                        "								IF (wb_adr_in = \"00\") THEN\n" +
                        "									wb_dat_out(0) <= Q;\n" +
                        "								ELSE\n" +
                        "									wb_dat_out <= (others => '0');\n" +
                        "								END IF;\n" +
                        "								read_ack <= '1';\n" +
                        "								state <= ACK;\n" +
                        "							end if;\n" +
                        "						when ACK =>\n" +
                        "							if wb_stb_in = '0' then\n" +
                        "								read_ack <= '0';\n" +
                        "								state <= IDLE;\n" +
                        "							end if;\n" +
                        "					end case;\n" +
                        "				else\n" +
                        "					state <= IDLE;\n" +
                        "					read_ack <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process;\n" +
                        "\n" +
                        "end architecture behaviour;";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/func_block_variable/PWM/vhdl/PWM_Peripheral.vhd", data);
    }
    
    private void generate_PWM_32_bit_vhd_file(String Project_Folder_File) {
        String data =   "library IEEE;\n" +
                        "USE IEEE.STD_LOGIC_1164.ALL;\n" +
                        "USE IEEE.STD_LOGIC_SIGNED.ALL;\n" +
                        "\n" +
                        "entity PWM_32_bit is\n" +
                        "port( clk, reset	: in STD_LOGIC;\n" +
                        "		EN				: in STD_LOGIC;\n" +
                        "		T_Count		: in STD_LOGIC_VECTOR(31 downto 0);\n" +
                        "		Comp_Count	: in STD_LOGIC_VECTOR(31 downto 0);\n" +
                        "		Y_OUT			: out STD_LOGIC\n" +
                        "		);\n" +
                        "end;\n" +
                        "\n" +
                        "architecture RTL of PWM_32_bit is\n" +
                        "	SIGNAL T_Count_T, Comp_Count_T	: STD_LOGIC_VECTOR(31 downto 0);\n" +
                        "	\n" +
                        "	begin\n" +
                        "	\n" +
                        "	process(clk, reset)\n" +
                        "		begin\n" +
                        "		if (rising_edge(clk) and EN = '1') then\n" +
                        "			if reset = '1' then \n" +
                        "				T_Count_T		<= (others => '0');\n" +
                        "				Comp_Count_T	<= (others => '0');\n" +
                        "				Y_OUT				<= '0';\n" +
                        "			elsif T_Count_T = X\"00000000\" then\n" +
                        "				T_Count_T		<= T_Count;\n" +
                        "				Comp_Count_T	<= Comp_Count;\n" +
                        "				Y_OUT				<= '0';\n" +
                        "			else\n" +
                        "				T_Count_T	<= T_Count_T - X\"00000001\";\n" +
                        "				if T_Count_T < Comp_Count_T then\n" +
                        "					Y_OUT <= '1';\n" +
                        "				else\n" +
                        "					Y_OUT <= '0';\n" +
                        "				end if;\n" +
                        "			end if;\n" +
                        "		end if;\n" +
                        "	end process;\n" +
                        "	\n" +
                        "end RTL;";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/func_block_variable/PWM/vhdl/PWM_32_bit.vhd", data);
    }
    
    private void generate_Div_32_bit_vhd_file(String Project_Folder_File) {
        String data =   "-- megafunction wizard: %LPM_DIVIDE%\n" +
                        "-- GENERATION: STANDARD\n" +
                        "-- VERSION: WM1.0\n" +
                        "-- MODULE: LPM_DIVIDE \n" +
                        "\n" +
                        "-- ============================================================\n" +
                        "-- File Name: Div_32_bit.vhd\n" +
                        "-- Megafunction Name(s):\n" +
                        "-- 			LPM_DIVIDE\n" +
                        "--\n" +
                        "-- Simulation Library Files(s):\n" +
                        "-- 			lpm\n" +
                        "-- ============================================================\n" +
                        "-- ************************************************************\n" +
                        "-- THIS IS A WIZARD-GENERATED FILE. DO NOT EDIT THIS FILE!\n" +
                        "--\n" +
                        "-- 18.0.0 Build 614 04/24/2018 SJ Lite Edition\n" +
                        "-- ************************************************************\n" +
                        "\n" +
                        "\n" +
                        "--Copyright (C) 2018  Intel Corporation. All rights reserved.\n" +
                        "--Your use of Intel Corporation's design tools, logic functions \n" +
                        "--and other software and tools, and its AMPP partner logic \n" +
                        "--functions, and any output files from any of the foregoing \n" +
                        "--(including device programming or simulation files), and any \n" +
                        "--associated documentation or information are expressly subject \n" +
                        "--to the terms and conditions of the Intel Program License \n" +
                        "--Subscription Agreement, the Intel Quartus Prime License Agreement,\n" +
                        "--the Intel FPGA IP License Agreement, or other applicable license\n" +
                        "--agreement, including, without limitation, that your use is for\n" +
                        "--the sole purpose of programming logic devices manufactured by\n" +
                        "--Intel and sold by Intel or its authorized distributors.  Please\n" +
                        "--refer to the applicable agreement for further details.\n" +
                        "\n" +
                        "\n" +
                        "LIBRARY ieee;\n" +
                        "USE ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "LIBRARY lpm;\n" +
                        "USE lpm.all;\n" +
                        "\n" +
                        "ENTITY Div_32_bit IS\n" +
                        "	PORT\n" +
                        "	(\n" +
                        "		denom		: IN STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "		numer		: IN STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "		quotient		: OUT STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "		remain		: OUT STD_LOGIC_VECTOR (31 DOWNTO 0)\n" +
                        "	);\n" +
                        "END Div_32_bit;\n" +
                        "\n" +
                        "\n" +
                        "ARCHITECTURE SYN OF div_32_bit IS\n" +
                        "\n" +
                        "	SIGNAL sub_wire0	: STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "	SIGNAL sub_wire1	: STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "	COMPONENT lpm_divide\n" +
                        "	GENERIC (\n" +
                        "		lpm_drepresentation		: STRING;\n" +
                        "		lpm_hint		: STRING;\n" +
                        "		lpm_nrepresentation		: STRING;\n" +
                        "		lpm_type		: STRING;\n" +
                        "		lpm_widthd		: NATURAL;\n" +
                        "		lpm_widthn		: NATURAL\n" +
                        "	);\n" +
                        "	PORT (\n" +
                        "			denom	: IN STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "			numer	: IN STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "			quotient	: OUT STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "			remain	: OUT STD_LOGIC_VECTOR (31 DOWNTO 0)\n" +
                        "	);\n" +
                        "	END COMPONENT;\n" +
                        "\n" +
                        "BEGIN\n" +
                        "	quotient    <= sub_wire0(31 DOWNTO 0);\n" +
                        "	remain    <= sub_wire1(31 DOWNTO 0);\n" +
                        "\n" +
                        "	LPM_DIVIDE_component : LPM_DIVIDE\n" +
                        "	GENERIC MAP (\n" +
                        "		lpm_drepresentation => \"UNSIGNED\",\n" +
                        "		lpm_hint => \"MAXIMIZE_SPEED=6,LPM_REMAINDERPOSITIVE=TRUE\",\n" +
                        "		lpm_nrepresentation => \"UNSIGNED\",\n" +
                        "		lpm_type => \"LPM_DIVIDE\",\n" +
                        "		lpm_widthd => 32,\n" +
                        "		lpm_widthn => 32\n" +
                        "	)\n" +
                        "	PORT MAP (\n" +
                        "		denom => denom,\n" +
                        "		numer => numer,\n" +
                        "		quotient => sub_wire0,\n" +
                        "		remain => sub_wire1\n" +
                        "	);\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "END SYN;\n" +
                        "\n" +
                        "-- ============================================================\n" +
                        "-- CNX file retrieval info\n" +
                        "-- ============================================================\n" +
                        "-- Retrieval info: PRIVATE: INTENDED_DEVICE_FAMILY STRING \"Cyclone V\"\n" +
                        "-- Retrieval info: PRIVATE: PRIVATE_LPM_REMAINDERPOSITIVE STRING \"TRUE\"\n" +
                        "-- Retrieval info: PRIVATE: PRIVATE_MAXIMIZE_SPEED NUMERIC \"6\"\n" +
                        "-- Retrieval info: PRIVATE: SYNTH_WRAPPER_GEN_POSTFIX STRING \"0\"\n" +
                        "-- Retrieval info: PRIVATE: USING_PIPELINE NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: VERSION_NUMBER NUMERIC \"2\"\n" +
                        "-- Retrieval info: PRIVATE: new_diagram STRING \"1\"\n" +
                        "-- Retrieval info: LIBRARY: lpm lpm.lpm_components.all\n" +
                        "-- Retrieval info: CONSTANT: LPM_DREPRESENTATION STRING \"UNSIGNED\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_HINT STRING \"MAXIMIZE_SPEED=6,LPM_REMAINDERPOSITIVE=TRUE\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_NREPRESENTATION STRING \"UNSIGNED\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_TYPE STRING \"LPM_DIVIDE\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_WIDTHD NUMERIC \"32\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_WIDTHN NUMERIC \"32\"\n" +
                        "-- Retrieval info: USED_PORT: denom 0 0 32 0 INPUT NODEFVAL \"denom[31..0]\"\n" +
                        "-- Retrieval info: USED_PORT: numer 0 0 32 0 INPUT NODEFVAL \"numer[31..0]\"\n" +
                        "-- Retrieval info: USED_PORT: quotient 0 0 32 0 OUTPUT NODEFVAL \"quotient[31..0]\"\n" +
                        "-- Retrieval info: USED_PORT: remain 0 0 32 0 OUTPUT NODEFVAL \"remain[31..0]\"\n" +
                        "-- Retrieval info: CONNECT: @denom 0 0 32 0 denom 0 0 32 0\n" +
                        "-- Retrieval info: CONNECT: @numer 0 0 32 0 numer 0 0 32 0\n" +
                        "-- Retrieval info: CONNECT: quotient 0 0 32 0 @quotient 0 0 32 0\n" +
                        "-- Retrieval info: CONNECT: remain 0 0 32 0 @remain 0 0 32 0\n" +
                        "-- Retrieval info: LIB_FILE: lpm";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/multi_blocks/DIV/Div_32_bit.vhd", data);
    }
    
    private void generate_Div_32_bit_qip_file(String Project_Folder_File) {
        String data =   "set_global_assignment -name IP_TOOL_NAME \"LPM_DIVIDE\"\n" +
                        "set_global_assignment -name IP_TOOL_VERSION \"18.0\"\n" +
                        "set_global_assignment -name IP_GENERATED_DEVICE_FAMILY \"{Cyclone V}\"\n" +
                        "set_global_assignment -name VHDL_FILE [file join $::quartus(qip_path) \"Div_32_bit.vhd\"]";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/multi_blocks/DIV/Div_32_bit.qip", data);
    }
    
    private void generate_Mult_32_bit_vhd_file(String Project_Folder_File) {
        String data =   "-- megafunction wizard: %LPM_MULT%\n" +
                        "-- GENERATION: STANDARD\n" +
                        "-- VERSION: WM1.0\n" +
                        "-- MODULE: lpm_mult \n" +
                        "\n" +
                        "-- ============================================================\n" +
                        "-- File Name: Mult_32_bit.vhd\n" +
                        "-- Megafunction Name(s):\n" +
                        "-- 			lpm_mult\n" +
                        "--\n" +
                        "-- Simulation Library Files(s):\n" +
                        "-- 			lpm\n" +
                        "-- ============================================================\n" +
                        "-- ************************************************************\n" +
                        "-- THIS IS A WIZARD-GENERATED FILE. DO NOT EDIT THIS FILE!\n" +
                        "--\n" +
                        "-- 18.0.0 Build 614 04/24/2018 SJ Lite Edition\n" +
                        "-- ************************************************************\n" +
                        "\n" +
                        "\n" +
                        "--Copyright (C) 2018  Intel Corporation. All rights reserved.\n" +
                        "--Your use of Intel Corporation's design tools, logic functions \n" +
                        "--and other software and tools, and its AMPP partner logic \n" +
                        "--functions, and any output files from any of the foregoing \n" +
                        "--(including device programming or simulation files), and any \n" +
                        "--associated documentation or information are expressly subject \n" +
                        "--to the terms and conditions of the Intel Program License \n" +
                        "--Subscription Agreement, the Intel Quartus Prime License Agreement,\n" +
                        "--the Intel FPGA IP License Agreement, or other applicable license\n" +
                        "--agreement, including, without limitation, that your use is for\n" +
                        "--the sole purpose of programming logic devices manufactured by\n" +
                        "--Intel and sold by Intel or its authorized distributors.  Please\n" +
                        "--refer to the applicable agreement for further details.\n" +
                        "\n" +
                        "\n" +
                        "LIBRARY ieee;\n" +
                        "USE ieee.std_logic_1164.all;\n" +
                        "\n" +
                        "LIBRARY lpm;\n" +
                        "USE lpm.all;\n" +
                        "\n" +
                        "ENTITY Mult_32_bit IS\n" +
                        "	PORT\n" +
                        "	(\n" +
                        "		dataa		: IN STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "		datab		: IN STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "		result		: OUT STD_LOGIC_VECTOR (63 DOWNTO 0)\n" +
                        "	);\n" +
                        "END Mult_32_bit;\n" +
                        "\n" +
                        "\n" +
                        "ARCHITECTURE SYN OF mult_32_bit IS\n" +
                        "\n" +
                        "	SIGNAL sub_wire0	: STD_LOGIC_VECTOR (63 DOWNTO 0);\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "	COMPONENT lpm_mult\n" +
                        "	GENERIC (\n" +
                        "		lpm_hint		: STRING;\n" +
                        "		lpm_representation		: STRING;\n" +
                        "		lpm_type		: STRING;\n" +
                        "		lpm_widtha		: NATURAL;\n" +
                        "		lpm_widthb		: NATURAL;\n" +
                        "		lpm_widthp		: NATURAL\n" +
                        "	);\n" +
                        "	PORT (\n" +
                        "			dataa	: IN STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "			datab	: IN STD_LOGIC_VECTOR (31 DOWNTO 0);\n" +
                        "			result	: OUT STD_LOGIC_VECTOR (63 DOWNTO 0)\n" +
                        "	);\n" +
                        "	END COMPONENT;\n" +
                        "\n" +
                        "BEGIN\n" +
                        "	result    <= sub_wire0(63 DOWNTO 0);\n" +
                        "\n" +
                        "	lpm_mult_component : lpm_mult\n" +
                        "	GENERIC MAP (\n" +
                        "		lpm_hint => \"DEDICATED_MULTIPLIER_CIRCUITRY=YES,MAXIMIZE_SPEED=9\",\n" +
                        "		lpm_representation => \"UNSIGNED\",\n" +
                        "		lpm_type => \"LPM_MULT\",\n" +
                        "		lpm_widtha => 32,\n" +
                        "		lpm_widthb => 32,\n" +
                        "		lpm_widthp => 64\n" +
                        "	)\n" +
                        "	PORT MAP (\n" +
                        "		dataa => dataa,\n" +
                        "		datab => datab,\n" +
                        "		result => sub_wire0\n" +
                        "	);\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "END SYN;\n" +
                        "\n" +
                        "-- ============================================================\n" +
                        "-- CNX file retrieval info\n" +
                        "-- ============================================================\n" +
                        "-- Retrieval info: PRIVATE: AutoSizeResult NUMERIC \"1\"\n" +
                        "-- Retrieval info: PRIVATE: B_isConstant NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: ConstantB NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: INTENDED_DEVICE_FAMILY STRING \"Cyclone V\"\n" +
                        "-- Retrieval info: PRIVATE: LPM_PIPELINE NUMERIC \"5\"\n" +
                        "-- Retrieval info: PRIVATE: Latency NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: SYNTH_WRAPPER_GEN_POSTFIX STRING \"0\"\n" +
                        "-- Retrieval info: PRIVATE: SignedMult NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: USE_MULT NUMERIC \"1\"\n" +
                        "-- Retrieval info: PRIVATE: ValidConstant NUMERIC \"1\"\n" +
                        "-- Retrieval info: PRIVATE: WidthA NUMERIC \"32\"\n" +
                        "-- Retrieval info: PRIVATE: WidthB NUMERIC \"32\"\n" +
                        "-- Retrieval info: PRIVATE: WidthP NUMERIC \"64\"\n" +
                        "-- Retrieval info: PRIVATE: aclr NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: clken NUMERIC \"0\"\n" +
                        "-- Retrieval info: PRIVATE: new_diagram STRING \"1\"\n" +
                        "-- Retrieval info: PRIVATE: optimize NUMERIC \"1\"\n" +
                        "-- Retrieval info: LIBRARY: lpm lpm.lpm_components.all\n" +
                        "-- Retrieval info: CONSTANT: LPM_HINT STRING \"DEDICATED_MULTIPLIER_CIRCUITRY=YES,MAXIMIZE_SPEED=9\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_REPRESENTATION STRING \"UNSIGNED\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_TYPE STRING \"LPM_MULT\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_WIDTHA NUMERIC \"32\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_WIDTHB NUMERIC \"32\"\n" +
                        "-- Retrieval info: CONSTANT: LPM_WIDTHP NUMERIC \"64\"\n" +
                        "-- Retrieval info: USED_PORT: dataa 0 0 32 0 INPUT NODEFVAL \"dataa[31..0]\"\n" +
                        "-- Retrieval info: USED_PORT: datab 0 0 32 0 INPUT NODEFVAL \"datab[31..0]\"\n" +
                        "-- Retrieval info: USED_PORT: result 0 0 64 0 OUTPUT NODEFVAL \"result[63..0]\"\n" +
                        "-- Retrieval info: CONNECT: @dataa 0 0 32 0 dataa 0 0 32 0\n" +
                        "-- Retrieval info: CONNECT: @datab 0 0 32 0 datab 0 0 32 0\n" +
                        "-- Retrieval info: CONNECT: result 0 0 64 0 @result 0 0 64 0\n" +
                        "-- Retrieval info: LIB_FILE: lpm";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/multi_blocks/MULT/Mult_32_bit.vhd", data);
    }
    
    private void generate_Mult_32_bit_qip_file(String Project_Folder_File) {
        String data =   "set_global_assignment -name IP_TOOL_NAME \"LPM_MULT\"\n" +
                        "set_global_assignment -name IP_TOOL_VERSION \"18.0\"\n" +
                        "set_global_assignment -name IP_GENERATED_DEVICE_FAMILY \"{Cyclone V}\"\n" +
                        "set_global_assignment -name VHDL_FILE [file join $::quartus(qip_path) \"Mult_32_bit.vhd\"]";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code/peripherals/multi_blocks/MULT/Mult_32_bit.qip", data);
    }
    
    private void generate_test_vhd_file(String Project_Folder_File) {
        String data =   "";
        new GeneralFunctions().write_file(Project_Folder_File + "hdl_code", data);
    }
}
