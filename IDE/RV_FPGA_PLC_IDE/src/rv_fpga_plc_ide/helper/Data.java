/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper;

import java.io.File;
import javax.swing.JTextField;

/**
 *
 * @author hossa
 */
public class Data {
    public static int size_Program = 0;
    public static int[] size_Program_in_rung;
    public static int max_size_program_in_rung;
    public static int size_Vaiables = 0;
    public static int size_Rung = 0;
    
    public static boolean is_New_Project;
    public static boolean is_Saved_Project;
    public static boolean is_There_is_a_project = false;
    public static boolean Commands_Enabeled = false;
    public static boolean Compile_Enabeled = false;
    
    public static String Project_Name = "No Project";
    public static String Project_Name_temp = "No Project";
    public static String[][] Program_2D;
    public static String[] Program_1D;
    public static String[] Vaiables;
    public static String[] Rung_Name;
    
    public static int Operator_Select = -1;
    public static String Basic_Comman_IL;
    public static int Selected_Rung;
    
    public static JTextField general_JTextField;
    public static boolean is_set;
    public static boolean is_TON;
    public static boolean is_bistable;
    public static boolean is_timer;
    public static boolean is_PWM;
    public static boolean is_newVariable_timer;
    public static boolean is_newVariable_PWM;
    public static String Function_Name;
    
    public static File Project_Folder;
    
    public static int Max_Number_Of_Instructions = 256;
    
    public static final int Max_Number_Of_Timers_SW = 2;
    
    // Compill il to c
    public static String C_code;
    public static int Load_index = 0;
    public static Boolean[] Load_index_is_defined;
    public static final int MAX_LOAD_INDEX = 1000;
    public static final int F_100M = 100000000;
    public static final int F_75M  = 75000000;
    public static final int F_70M  = 70000000;
    public static final int F_50M  = 50000000;
    public static final int F_10M  = 10000000;
    public static final int F_1M   = 1000000;
    
    public static int       CPU_RV32_Freq = F_100M;
    public static int       CPU_RV32_Timer_Freq = F_100M;
    
    public static int       CPU_RV64_Freq = F_75M;
    public static int       CPU_RV64_Timer_Freq = F_75M;
    public static int       PWM_RV64_HW_Freq = F_1M;
    
    public static boolean RequestDownload = false;
    
    // Comile hdl
    public static final int NO_COMPILATION = 0;
    public static final int ANALYSIS_SYNTHESIS = 1;
    public static final int FITTER = 2;
    public static final int ASSEMBLER = 3;
    public static final int UPDATED = 4;
    public static int hdl_compilation_state_RV32_SW = NO_COMPILATION;
    public static int hdl_compilation_state_RV32_HW = NO_COMPILATION;
    public static int hdl_compilation_state_RV64_SW = NO_COMPILATION;
    public static int hdl_compilation_state_RV64_HW = NO_COMPILATION;
    
    public static final int SW_COMPILATION = 1;
    public static final int HW_COMPILATION = 2;
    
    public static int Number_Of_Timers_Compiled = 0;
    public static int Number_Of_Timers_In_Program = 0;
    public static String[] Name_of_Timers = new String[50];
    
    public static int Number_Of_PWMs_Compiled = 0;
    public static int Number_Of_PWMs_In_Program = 0;
    public static String[] Name_of_PWMs = new String[50];
    
    
    public static final int RV32 = 0;
    public static final int RV64 = 1;
    public static final int NO_CORE = 2;
    public static int core = NO_CORE;
    public static int compiled_core = NO_CORE;
    
    // Type of output from executing commands
    public static final int COMMAND_WINDOW = 0;
    public static final int OUTPUT_TAP_WINDOW = 1;
    public static final int COMMAND_OUTPUT_TAP_WINDOW = 2;
    public static final int NO_WINDOW = 3;
    public static int deafult_out_window = COMMAND_WINDOW;
    
    // Path to compilers
    public static final String R32_COMPILER_PATH = "/opt/riscv32/bin/";
    public static final String R64_COMPILER_PATH = "/opt/riscv64/bin/";
    
    public static boolean is_fpu_RV64_enabeled = false;
    public static boolean is_mul_RV64_enabeled = false;
    public static boolean is_div_RV64_enabeled = false;
    
    public static final String localVariables = "	/*************** Local Variables ****************/\n\n";
    public static final String initializeLocalVariables = "	/********** Initialize Local Variables **********/\n\n";
    
}
