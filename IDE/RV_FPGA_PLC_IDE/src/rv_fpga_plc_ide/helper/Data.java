/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper;

import java.io.File;
import javax.swing.JTextArea;
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
    public static String    CPU_Freq_S = "100000000";
    public static int       CPU_Freq_I = 100000000;
    public static String    CPU_Freq_H_S = "05F5E100";   //05F5E100   02FAF080
    public static String    CPU_Timer_Freq_S ="100000000";
    public static int       CPU_Timer_Freq_I = 100000000;
    
    public static JTextArea jTextArea_Output_Tab;
    public static boolean RequistDownload = false;
    
    // Comile hdl
    public static final int NO_COMPILATION = 0;
    public static final int ANALYSIS_SYNTHESIS = 1;
    public static final int FITTER = 2;
    public static final int ASSEMBLER = 3;
    public static final int UPDATED = 4;
    public static int hdl_compilation_type = NO_COMPILATION;
    public static int hdl_compilation_state = NO_COMPILATION;
    
    public static final int SW_COMPILATION = 1;
    public static final int HW_COMPILATION = 2;
    
    public static int Number_Of_Timers_Compiled = 0;
    public static int Number_Of_Timers_In_Program = 0;
    public static String[] Name_of_Timers = new String[50];
    
    public static int Number_Of_PWMs_Compiled = 0;
    public static int Number_Of_PWMs_In_Program = 0;
    public static String[] Name_of_PWMs = new String[50];
    
    
    public static final int R32 = 0;
    public static final int R64 = 1;
    public static int core = R32;
}
