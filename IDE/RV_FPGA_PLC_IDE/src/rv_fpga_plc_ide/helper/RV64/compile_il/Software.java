/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.RV64.compile_il;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import rv_fpga_plc_ide.helper.Data;
import rv_fpga_plc_ide.helper.GeneralFunctions;
import rv_fpga_plc_ide.helper.RV64.Write_Generated_Files.Write_Hardware_Files;
import rv_fpga_plc_ide.helper.RV64.Write_Generated_Files.Write_Software_Files;
import rv_fpga_plc_ide.helper.RV64.compile_c.compile_c_file;
import rv_fpga_plc_ide.helper.compile_hld.CompileHLD;
import rv_fpga_plc_ide.helper.private_threads.LoadingDialoge;

/**
 *
 * @author hossameldin
 */
public class Software {
    public void compile_software(Component parentComponent, String Project_Folder, ActionEvent evt, boolean compile_all_project, JDialog jDialog_Loading, JLabel JTextLableLoading, JFileChooser jFileChooser1, JTextArea jTextArea_Output_Tab) {
        Data.is_fpu_RV64_enabeled = false;
        Data.is_mul_RV64_enabeled = false;
        Data.is_div_RV64_enabeled = false;
        Data.StartTime = new Date();
        new GeneralFunctions().initialize_int_with_value(Data.Current_Register_Type, Data.NO_TYPE);
        Data.Current_Register_Count = 0;
        

        Data.ALU_Support_In_Program_RV64_SW = 0;
        if (Data.hdl_compilation_state_RV64_SW == Data.UPDATED) {
            Data.hdl_compilation_state_RV64_SW = Data.ASSEMBLER;
        }
        LoadingDialoge loading = new LoadingDialoge("Compiling ...", JTextLableLoading, jDialog_Loading);
        loading.start();
        jTextArea_Output_Tab.setText("");
        jTextArea_Output_Tab.append("Start Compiling As Software.\n");
        boolean success = true;
        File c_files = new File(Project_Folder+"/c_files_RV64_SW");
        File q_files = new File(Project_Folder+"/q_files_RV64_SW");
        
        c_files.mkdirs();
        q_files.mkdirs();
        
        jTextArea_Output_Tab.append("  Start Compiling \"instruction list\".\n");
        success &= compill_il_file_sw(parentComponent, jDialog_Loading);
        if (success) {
            jTextArea_Output_Tab.append("  Start Compiling \"hex2mifc\".\n");
            success &= new compile_c_file().compile_hex2mif(jTextArea_Output_Tab);
        }
        if (success) {
            jTextArea_Output_Tab.append("  Start Compiling \"elf2rawx\".\n");
            success &= new compile_c_file().compile_elf2rawx(jTextArea_Output_Tab);
        }
        if (success) {
            jTextArea_Output_Tab.append("  Start Compiling \"Application\".\n");
            success &= new compile_c_file().compile_application(jTextArea_Output_Tab);
        }
        if (success) {
            jTextArea_Output_Tab.append("  Start Writting Hardware Files.\n");
            new Write_Hardware_Files().generate_q_files(Project_Folder+"/q_files_RV64_SW/");
        }
        if (success && compile_all_project) {
            jTextArea_Output_Tab.append("  Start Compiling \"Quartus Project\".\n");
            new GeneralFunctions().copy_file(Project_Folder+"/c_files_RV64_SW/"+Data.Project_Name+"_application/bin/"+Data.Project_Name+".mif", Project_Folder+"/q_files_RV64_SW/"+Data.Project_Name+".mif");
            new CompileHLD().compile_hdl(parentComponent, Project_Folder, evt, jDialog_Loading, jFileChooser1, jTextArea_Output_Tab);
        }
        
        if (success) {
            if (!compile_all_project) {
                jDialog_Loading.setVisible(false);
                JOptionPane.showMessageDialog(parentComponent, "Successful");
                String[] defference_time = new String[3];
                new GeneralFunctions().calculate_defference_time(defference_time);
                jTextArea_Output_Tab.append("Execution time for Compiling is "+defference_time[2]+":"+defference_time[1]+":"+defference_time[0]+"\n");
                jTextArea_Output_Tab.append("Compilling Finished Successfully\n");
            }
        } else {
            Icon icon = UIManager.getIcon("OptionPane.errorIcon");
            jDialog_Loading.setVisible(false);
            JOptionPane.showMessageDialog(parentComponent, "Not Successful", "Compile As Software", JOptionPane.OK_OPTION, icon);
            jTextArea_Output_Tab.append("Compilling did not Finished Successfully\n");
        }
    }
    
    private boolean compill_il_file_sw(Component parentComponent, JDialog jDialog_Loading) {
        boolean success = true;
        Data.Number_Of_Timers_In_Program_SW = 0;
        Data.C_code =   "/*****************************************************************************\n" +
                        " * @file\n" +
                        " * @author   Sergey Khabarov\n" +
                        " * @editor   Hossameldin Eassa\n" +
                        " * @brief    Generated file for RV64 Application for PLC\n" +
                        " ****************************************************************************/\n" +
                        "\n" +
                        "/*****************************************************************************\n" +
                        " * Includes\n" +
                        " ****************************************************************************/\n" +
                        "\n" +
                        "#include <string.h>\n" +
                        "#include <inttypes.h>\n" +
                        "#include <stdio.h>\n" +
                        "#include \"encoding.h\"\n" +
                        "#include \"fw_api.h\"\n" +
                        "#include \"axi_maps.h\"\n" +
                        "\n" +
                        "/*****************************************************************************\n" +
                        " * Function Declaration\n" +
                        " ****************************************************************************/\n" +
                        "\n" +
                        "void allocate_exception_table(void);\n" +
                        "void test_timer(void);\n" +
                        "void start_application_"+Data.Project_Name+"(void);\n" +
                        "uint32_t test_fpu(void);\n" +
                        "uint64_t inline double2hex(double x);\n" +
                        "uint32_t inline float2hex(float x);\n" +
                        "\n" +
                        "/*****************************************************************************\n" +
                        " * Static Functions\n" +
                        " ****************************************************************************/\n" +
                        "\n" +
                        "uint64_t inline double2hex(double x) {\n" +
                        "    uint64_t *p;\n" +
                        "    p = (void*)&x;\n" +
                        "    return *p;\n" +
                        "}\n" +
                        "\n" +
                        "uint32_t inline float2hex(float x) {\n" +
                        "    uint32_t *p;\n" +
                        "    p = (void*)&x;\n" +
                        "    return *p;\n" +
                        "}\n" +
                        "\n" +
                        "/*****************************************************************************\n" +
                        " * Main Function\n" +
                        " ****************************************************************************/\n" +
                        "\n" +
                        "uint32_t main() {\n" +
                        "    io_per io_per_d;\n" +
                        "    uint32_t err_cnt = 0;\n" +
                        "    \n" +
                        "    uart_map *uart = (uart_map *)ADDR_BUS0_XSLV_UART1;\n" +
                        "    irqctrl_map *p_irq = (irqctrl_map *)ADDR_BUS0_XSLV_IRQCTRL;\n" +
                        "    io_per_d.registers = (volatile void *)ADDR_BUS0_XSLV_GPIO;\n" +
                        "\n" +
                        "    if (fw_get_cpuid() != 0) {\n" +
                        "        while (1) {}\n" +
                        "    }\n" +
                        "\n" +
                        "    // mask all interrupts in interrupt controller to avoid\n" +
                        "    // unpredictable behaviour after elf-file reloading via debug port.\n" +
                        "    p_irq->irq_mask = 0xFFFFFFFF;\n" +
                        "    p_irq->isr_table = 0;\n" +
                        "\n" +
                        "    p_irq->irq_lock = 1;\n" +
                        "    fw_malloc_init();\n" +
                        "    \n" +
                        "    allocate_exception_table();\n" +
                        "\n" +
                        "    uart_isr_init();   // enable printf_uart function and Tx irq=1\n" +
                        "    p_irq->irq_lock = 0;\n" +
                        " \n" +
                        "    /* LEDG = 1*/\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 0, LED_ON);\n" +
                        "    io_per_set_output(&io_per_d, RWD, 0, 0);\n" +
                        "\n" +
                        "    /* LEDG = 2*/\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 0, LED_OFF);\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 1, LED_ON);\n" +
                        "    io_per_set_output(&io_per_d, RWD, 0, 0);\n" +
                        "\n" +
                        "    /* LEDG = 4*/\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 1, LED_OFF);\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 2, LED_ON);\n" +
                        "    io_per_set_output(&io_per_d, RWD, 0, 0);\n" +
                        "\n" +
                        "    /* LEDG = 8*/\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 2, LED_OFF);\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 3, LED_ON);\n" +
                        "    io_per_set_output(&io_per_d, RWD, 0, 0);\n" +
                        "\n" +
                        "    /* LEDG = 0*/\n" +
                        "    io_per_set_output(&io_per_d, LEDG, 3, LED_OFF);\n" +
                        "    io_per_set_output(&io_per_d, RWD, 0, 0);\n" +
                        "\n" +
                        "    start_application_"+Data.Project_Name+"(); // no return\n" +
                        "\n" +
                        "    return 0;\n" +
                        "}" +
                        "\n" +
                        "/*****************************************************************************\n" +
                        " * Start Application\n" +
                        " ****************************************************************************/\n" +
                        "\n";
        Data.C_code +=  "\nvoid start_application_"+Data.Project_Name+"() {\n\n" +
                        Data.localVariables;
        Data.C_code +=  "	io_per io_per_d;\n" +
                        "	time_measurement time_measurement_d;\n\n" +
                        Data.initializeLocalVariables +
                        "	io_per_d.registers = (volatile void *)ADDR_BUS0_XSLV_GPIO;\n" +
                        "	time_measurement_d.registers = (volatile void *)ADDR_BUS0_XSLV_MEASUREMENT;\n\n" +
                        Data.instructionListVariables;
                        new Write_Software_Files().declareAndInitializeVariables(1);
        Data.C_code +=  "\n\n	while(1){\n" +
                        "\n" +
                        "		start_time(&time_measurement_d);\n" +
                        "		io_per_set_output(&io_per_d, RWD, 0, 0);\n";
        
        Data.Load_index = 0;
        Data.Load_index_is_defined = new Boolean[Data.MAX_LOAD_INDEX];
        Arrays.fill(Data.Load_index_is_defined, Boolean.FALSE);
        Data.Load_index_Save = new String[Data.MAX_LOAD_INDEX][Data.SUB_INDEX];
        for (int rung_i = 0; rung_i < Data.size_Rung; rung_i++) {
            Data.C_code += "\n\t\t// Rung " + (rung_i + 1 ) + " :" + Data.Rung_Name[rung_i].replaceAll(":", "") + "\n";
            success &= compile_rung_sw(parentComponent, rung_i, jDialog_Loading);
        }
        if (Data.Load_index != Data.Current_Register_Count) {
            success = false;
            Icon icon = UIManager.getIcon("OptionPane.errorIcon");
            jDialog_Loading.setVisible(false);
            JOptionPane.showMessageDialog(parentComponent, "Missing modifier )", "Compile il", JOptionPane.OK_OPTION, icon);
        }
        
        Data.C_code += "        stop_time(&time_measurement_d);\n" +
                       "    }\n" +
                       "\n" +
                       "}";
        new Write_Software_Files().write_library_files(Data.Project_Folder.getPath()+"/c_files_RV64_SW");
        new GeneralFunctions().write_file(Data.Project_Folder.getPath()+"/c_files_RV64_SW/"+Data.Project_Name+"_application/src/main.c", Data.C_code);
        return success;
    }
    
    private boolean compile_rung_sw(Component parentComponent, int rung_i, JDialog jDialog_Loading) {
        boolean success = true;
        String il_inst;
        for (int program_i = 0; program_i < Data.size_Program_in_rung[rung_i]; program_i++) {
            il_inst = Data.Program_2D[rung_i][program_i];
            String[] il_inst_Arr = new String[1];
            il_inst_Arr[0] = il_inst;
            new GeneralFunctions().remove_Spaces_Before_Strings(il_inst_Arr, 1);
            il_inst = il_inst_Arr[0];
            if (il_inst.split(" ")[0].contains("LDN")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("LDN", "");
                success = success && add_basic_load_command(parentComponent, Operand, "~");
            } else if (il_inst.split(" ")[0].contains("STN")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("STN", "");
                success = success && add_basic_store_command(parentComponent, Operand, "~", jDialog_Loading);
            } else if (il_inst.split(" ")[0].contains("SET")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("SET", "");
                add_set_reset_c_command(parentComponent, Operand, 1);
            } else if (il_inst.split(" ")[0].contains("RST")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("RST", "");
                add_set_reset_c_command(parentComponent, Operand, 0);
            } else if (il_inst.split(" ")[0].contains("LD")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("LD", "");
                success = success && add_basic_load_command(parentComponent, Operand, "");
            } else if (il_inst.split(" ")[0].contains("ST")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("ST", "");
                success = success && add_basic_store_command(parentComponent, Operand, "", jDialog_Loading);
            } else if (il_inst.split(" ")[0].contains("ANDN")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("ANDN", "");
                success = success && add_basic_c_command(parentComponent, Operand, "&", "~", jDialog_Loading, true);
            } else if (il_inst.split(" ")[0].contains("XORN")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("XOR", "");
                success = success && add_basic_c_command(parentComponent, Operand, "^", "~", jDialog_Loading, true);
            } else if (il_inst.split(" ")[0].contains("ORN")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("OR", "");
                success = success && add_basic_c_command(parentComponent, Operand, "|", "~", jDialog_Loading, true);
            } else if (il_inst.split(" ")[0].contains("AND")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("AND", "");
                success = success && add_basic_c_command(parentComponent, Operand, "&", "", jDialog_Loading, true);
            } else if (il_inst.split(" ")[0].contains("XOR")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("XOR", "");
                success = success && add_basic_c_command(parentComponent, Operand, "^", "", jDialog_Loading, true);
            } else if (il_inst.split(" ")[0].contains("OR")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("OR", "");
                success = success && add_basic_c_command(parentComponent, Operand, "|", "", jDialog_Loading, true);
            } else if (il_inst.split(" ")[0].contains("ADD")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("ADD", "");
                success = success && add_basic_c_command(parentComponent, Operand, "+", "", jDialog_Loading, false);
            } else if (il_inst.split(" ")[0].contains("SUB")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("SUB", "");
                success = success && add_basic_c_command(parentComponent, Operand, "-", "", jDialog_Loading, false);
            } else if (il_inst.split(" ")[0].contains("MUL")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("MUL", "");
                success = success && add_basic_c_command(parentComponent, Operand, "*", "", jDialog_Loading, false);
                Data.is_mul_RV64_enabeled = true;
                Data.ALU_Support_In_Program_RV64_SW |= Data.MASK_MUL_RV64;
            } else if (il_inst.split(" ")[0].contains("DIV")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("DIV", "");
                success = success && add_basic_c_command(parentComponent, Operand, "/", "", jDialog_Loading, false);
                Data.is_div_RV64_enabeled = true;
                Data.ALU_Support_In_Program_RV64_SW |= Data.MASK_DIV_RV64;
            } else if (il_inst.split(" ")[0].contains("MOD")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("MOD", "");
                success = success && add_basic_c_command(parentComponent, Operand, "%", "", jDialog_Loading, false);
                Data.is_div_RV64_enabeled = true;
                Data.ALU_Support_In_Program_RV64_SW |= Data.MASK_DIV_RV64;
            } else if (il_inst.split(" ")[0].contains("NOT")) {
                Data.C_code += "\t\tvar"+(Data.Load_index)+" = ~var"+(Data.Load_index)+";\n";
            } else if (il_inst.split(" ")[0].contains("GT")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("GT", "");
                add_comparison_c_command(parentComponent, Operand, ">", jDialog_Loading);
            } else if (il_inst.split(" ")[0].contains("GE")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("GE", "");
                add_comparison_c_command(parentComponent, Operand, ">=", jDialog_Loading);
            } else if (il_inst.split(" ")[0].contains("EQ")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("EQ", "");
                add_comparison_c_command(parentComponent, Operand, "==", jDialog_Loading);
            } else if (il_inst.split(" ")[0].contains("NE")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("NE", "");
                add_comparison_c_command(parentComponent, Operand, "!=", jDialog_Loading);
            } else if (il_inst.split(" ")[0].contains("LT")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("LT", "");
                add_comparison_c_command(parentComponent, Operand, "<", jDialog_Loading);
            } else if (il_inst.split(" ")[0].contains("LE")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("LE", "");
                add_comparison_c_command(parentComponent, Operand, "<=", jDialog_Loading);
            } else if (il_inst.split(" ")[0].contains("_TO_")) {
                String[] Operand = il_inst.replaceAll(" ", "").split("_TO_");
                new GeneralFunctions().add_conversion_type_c_command(Operand[0], Operand[1]);
            } else if (il_inst.split(" ")[0].contains(")")) {
                if (Data.Load_index_Save[Data.Load_index-1][1].equals("C")) {
                    add_comparison_c_command(parentComponent, ")",
                            Data.Load_index_Save[Data.Load_index-1][0], jDialog_Loading);
                } else {
                    success = success && add_basic_c_command(parentComponent, ")",
                            Data.Load_index_Save[Data.Load_index-1][0],
                            Data.Load_index_Save[Data.Load_index-1][1], jDialog_Loading, 
       Boolean.parseBoolean(Data.Load_index_Save[Data.Load_index-1][2]));
                }
            } else if (il_inst.split(" ")[0].contains("CAL")) {
                String Operand = il_inst.replaceAll(" ", "").replaceAll("CAL", "").replaceAll("\\(", "");
                
                program_i++; // first operand
                il_inst = Data.Program_2D[rung_i][program_i];
                il_inst_Arr[0] = il_inst;
                new GeneralFunctions().remove_Spaces_Before_Strings(il_inst_Arr, 1);
                il_inst = il_inst_Arr[0].replaceAll(" ", "");
                il_inst = il_inst.replaceAll(",", "");
                
                String typeOfVariable = "No Type";
                for (int i = 1; i < Data.size_Vaiables-1; i++) {
                    String Variable_temp = Data.Vaiables[i].replace(" ", "");
                    if (Variable_temp.contains(Operand)) {
                        typeOfVariable = Variable_temp.split(":")[1];
                        break;
                    }
                }
                if (typeOfVariable.contains("TON")) {
                    success = TON_compile_sw(parentComponent, Operand, il_inst, rung_i, program_i, jDialog_Loading);
                    program_i = program_i + 3;
                }else if (typeOfVariable.contains("PWM")) {
                    success = PWM_compile_sw(parentComponent, Operand, il_inst, rung_i, program_i, jDialog_Loading);
                    program_i = program_i + 2;
                    Data.is_mul_RV64_enabeled = true;
                    Data.is_div_RV64_enabeled = true;
                    Data.ALU_Support_In_Program_RV64_SW |= Data.MASK_MUL_RV64;
                    Data.ALU_Support_In_Program_RV64_SW |= Data.MASK_DIV_RV64;
                } else {
                    jDialog_Loading.setVisible(false);
                    JOptionPane.showMessageDialog(parentComponent, "\""+typeOfVariable+"\" not supported yet", "Compile il", JOptionPane.OK_OPTION);
                    success = false;
                }
            } else {
                jDialog_Loading.setVisible(false);
                JOptionPane.showMessageDialog(parentComponent, "\""+il_inst+"\" not supported yet", "Compile il", JOptionPane.OK_OPTION);
                success = false;
            }
        }
        return success;
    }
    
    private boolean add_basic_load_command(Component parentComponent, String Operand, String not) {
        boolean success = true;
        int Instant_Operand;
        if (Operand.contains("%")){ // BOOL
            Operand = Operand.replaceAll("%", "");
            String offc = Operand.split("\\.")[1];
            Operand = Operand.split("\\.")[0];
            if (!Data.Load_index_is_defined[Data.Load_index]) {
                Data.C_code += "\t\tuint8_t var"+Data.Load_index+" = "+not+"io_per_get_input(&io_per_d, "+Operand+", "+offc+");\n";
                Data.Load_index_is_defined[Data.Load_index] = true;
                Data.Current_Register_Type[Data.Load_index] = Data.BOOL;
            } else {
                if (Data.Current_Register_Type[Data.Load_index] == Data.BOOL) {
                Data.C_code += "\t\tvar"+Data.Load_index+" = "+not+"io_per_get_input(&io_per_d, "+Operand+", "+offc+");\n";
                } else {
                    Data.Current_Register_Count++;
                    Data.Load_index++;
                    Data.C_code += "\t\tuint8_t var"+Data.Load_index+" = "+not+"io_per_get_input(&io_per_d, "+Operand+", "+offc+");\n";
                    Data.Load_index_is_defined[Data.Load_index] = true;
                    Data.Current_Register_Type[Data.Load_index] = Data.BOOL;
                }
            }
        } else if (Operand.contains("T#")) {
            if (not.equals("")) {
                double time_sec = new GeneralFunctions().getSecFromTimeFormat(Operand);
                long Number_of_Clocks = (long) (time_sec*(double)Data.CPU_RV64_Timer_Freq);
                if (!Data.Load_index_is_defined[Data.Load_index]) {
                    Data.C_code += "\t\tuint64_t var"+Data.Load_index+" = (uint64_t)"+Number_of_Clocks+";\n";
                    Data.Load_index_is_defined[Data.Load_index] = true;
                    Data.Current_Register_Type[Data.Load_index] = Data.TIME;
                } else {
                    if (Data.Current_Register_Type[Data.Load_index] == Data.TIME) {
                    Data.C_code += "\t\tvar"+Data.Load_index+" = (uint64_t)"+Number_of_Clocks+";\n";
                    } else {
                        Data.Current_Register_Count++;
                        Data.Load_index++;
                        Data.C_code += "\t\tuint64_t var"+Data.Load_index+" = (uint64_t)"+Number_of_Clocks+";\n";
                        Data.Load_index_is_defined[Data.Load_index] = true;
                        Data.Current_Register_Type[Data.Load_index] = Data.TIME;
                    }
                }
            } else {
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Time can't be invertes!", "Compile As Software", JOptionPane.OK_OPTION, icon);
                success = false;
            }
        } else {
            try {
                Instant_Operand = Integer.parseInt(Operand);
                if (!Data.Load_index_is_defined[Data.Load_index]) {
                    Data.C_code += "\t\tint64_t var"+Data.Load_index+" = "+not+Instant_Operand+";\n";
                    Data.Load_index_is_defined[Data.Load_index] = true;
                    Data.Current_Register_Type[Data.Load_index] = Data.INT64_T;
                } else {
                    if (Data.Current_Register_Type[Data.Load_index] == Data.INT64_T) {
                        Data.C_code += "\t\tvar"+Data.Load_index+" = "+not+Instant_Operand+";\n";
                    } else {
                        Data.Current_Register_Count++;
                        Data.Load_index++;
                        Data.C_code += "\t\tint64_t var"+Data.Load_index+" = "+not+Instant_Operand+";\n";
                        Data.Load_index_is_defined[Data.Load_index] = true;
                        Data.Current_Register_Type[Data.Load_index] = Data.INT64_T;
                    }
                }
            } catch (NumberFormatException ex) {
                String Variable_temp;
                String typeOfVariable = "Variabe Not Found";
                String nameOfVariable = "Variabe Not Found";
                String C_DataType;
                int[] Register_Type = new int[1];
                for (int i = 1; i < Data.size_Vaiables-1; i++) {
                    Variable_temp = Data.Vaiables[i].replace(" ", "");
                    if (Variable_temp.contains(Operand)) {
                        nameOfVariable = Variable_temp.split(":")[0];
                        typeOfVariable = Variable_temp.split(":")[1];
                        break;
                    }
                }
                C_DataType = new GeneralFunctions().convert_il_datatype_to_c_datatype(typeOfVariable, Register_Type);
                if (!Data.Load_index_is_defined[Data.Load_index]) {
                    Data.C_code += "\t\t"+C_DataType+" var"+Data.Load_index+" = "+not+nameOfVariable+";\n";
                    Data.Load_index_is_defined[Data.Load_index] = true;
                    Data.Current_Register_Type[Data.Load_index] = Register_Type[0];
                } else {
                    if (Data.Current_Register_Type[Data.Load_index] == Register_Type[0]) {
                        Data.C_code += "\t\tvar"+Data.Load_index+" = "+not+nameOfVariable+";\n";
                    } else {
                        Data.Current_Register_Count++;
                        Data.Load_index++;
                        Data.C_code += "\t\t"+C_DataType+" var"+Data.Load_index+" = "+not+nameOfVariable+";\n";
                        Data.Load_index_is_defined[Data.Load_index] = true;
                        Data.Current_Register_Type[Data.Load_index] = Register_Type[0];
                    }
                }
            }
        }
        return success;
    }
    
    private boolean add_basic_store_command(Component parentComponent, String Operand, String not,  JDialog jDialog_Loading) {
        boolean success = true;
        if (Operand.contains("%")){
            Operand = Operand.replaceAll("%", "");
            String offc = Operand.split("\\.")[1];
            Operand = Operand.split("\\.")[0];
            if (Data.Current_Register_Type[Data.Load_index] == Data.BOOL) {
                Data.C_code += "\t\tio_per_set_output(&io_per_d, "+Operand+", "+offc+", "+not+"var"+(Data.Load_index)+");\n";
            } else {
                jDialog_Loading.setVisible(false);
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Can not store non-BOOL type in BOOL I/O", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                success = false;
            }
        } else {
            try {
                jDialog_Loading.setVisible(false);
                Integer.parseInt(Operand);
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Can not add instanse in store command", "Compile As Software", JOptionPane.OK_OPTION, icon);
                success = false;
            } catch (NumberFormatException ex) {
                String Variable_temp;
                String nameOfVariable = "Variabe Not Found";
                String typeOfVariable = "Not Supported Type";
                for (int i = 1; i < Data.size_Vaiables-1; i++) {
                    Variable_temp = Data.Vaiables[i].replace(" ", "");
                    if (Variable_temp.contains(Operand)) {
                        nameOfVariable = Variable_temp.split(":")[0];
                        typeOfVariable = Variable_temp.split(":")[1];
                        break;
                    }
                }
                int Register_Type = new GeneralFunctions().getRegesterTypeFromStringType(typeOfVariable);
                if (Data.Current_Register_Type[Data.Load_index] == Register_Type) {
                Data.C_code += "\t\t"+nameOfVariable+" = "+not+"var"+(Data.Load_index)+";\n";
                } else {
                    jDialog_Loading.setVisible(false);
                    Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                    JOptionPane.showMessageDialog(parentComponent, "Can not store variable \""+nameOfVariable+"\"of type \""+typeOfVariable+"\"\nIncompatable types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                    success = false;
                }
            }
        }
        return success;
    }
    
    private boolean add_set_reset_c_command(Component parentComponent, String Operand, int set_reset) {
        boolean success = true;
        if (Operand.contains("%")){
            Operand = Operand.replaceAll("%", "");
            String offc = Operand.split("\\.")[1];
            Operand = Operand.split("\\.")[0];
            Data.C_code += "\t\tif (var"+(Data.Load_index)+" != 0) io_per_set_output(&io_per_d, "+Operand+", "+offc+", "+set_reset+");\n";
        } else {
            try {
                Integer.parseInt(Operand);
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Can not add instanse in store command", "Compile As Software", JOptionPane.OK_OPTION, icon);
                success = false;
            } catch (NumberFormatException ex) {
                String Variable_temp;
                String nameOfVariable = "Variabe Not Found";
                for (int i = 1; i < Data.size_Vaiables-1; i++) {
                    Variable_temp = Data.Vaiables[i].replace(" ", "");
                    if (Variable_temp.contains(Operand)) {
                        nameOfVariable = Variable_temp.split(":")[0];
                        break;
                    }
                }
                Data.C_code += "\t\tif (var"+(Data.Load_index)+" != 0) "+nameOfVariable+" = "+set_reset+";\n";
            }
        }
        return success;
    }
    
    private boolean add_basic_c_command(Component parentComponent, String Operand, String operation, String not, JDialog jDialog_Loading, boolean Support_BOOL) {
        boolean success = true;
        if (Operand.contains("(")) {
            Data.Load_index_Save[Data.Load_index][0] = operation;
            Data.Load_index_Save[Data.Load_index][1] = not;
            Data.Load_index_Save[Data.Load_index][2] = Boolean.toString(Support_BOOL);
            Data.Load_index_Save[Data.Load_index][3] = Boolean.toString(false);
            Data.Load_index++;
        } else if (Operand.contains(")")) {
            Data.Load_index--;
            if  (
                    Support_BOOL ||
                    (
                        Data.Current_Register_Type[Data.Load_index]   != Data.BOOL &&
                        Data.Current_Register_Type[Data.Load_index+1] != Data.BOOL
                    )
                ) {
                if (Data.Current_Register_Type[Data.Load_index] == Data.Current_Register_Type[Data.Load_index+1]) {
            Data.C_code += "\t\tvar"+(Data.Load_index)+" "+operation+"= "+not+"var"+(Data.Load_index+1)+";\n";
                } else {
                    jDialog_Loading.setVisible(false);
                    Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                    JOptionPane.showMessageDialog(parentComponent, "Incompatable operation ("+operation+") between types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                    success = false;
                }
            } else {
                jDialog_Loading.setVisible(false);
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Operation ("+operation+") can not be berformed between BOOL types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                success = false;
            }
        } else {
            int Instant_Operand;
            if (Operand.contains("%")){
                Operand = Operand.replaceAll("%", "");
                String offc = Operand.split("\\.")[1];
                Operand = Operand.split("\\.")[0];
                if (Support_BOOL) {
                    if (Data.Current_Register_Type[Data.Load_index] == Data.BOOL) {
                Data.C_code += "\t\tvar"+(Data.Load_index)+" "+operation+"= "+not+"io_per_get_input(&io_per_d, "+Operand+", "+offc+");\n";
                    } else {
                        jDialog_Loading.setVisible(false);
                        Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                        JOptionPane.showMessageDialog(parentComponent, "Can not make arithmetic operations between non-BOOL and BOOL type\nIncompatable operation ("+operation+") between types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                        success = false;
                    }
                } else {
                    jDialog_Loading.setVisible(false);
                    Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                    JOptionPane.showMessageDialog(parentComponent, "Operation ("+operation+") can not be berformed between BOOL types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                    success = false;
                }
            } else {
                try {
                    Instant_Operand = Integer.parseInt(Operand);
                    if  (Support_BOOL ||  Data.Current_Register_Type[Data.Load_index]   != Data.BOOL) {
                    Data.C_code += "\t\tvar"+(Data.Load_index)+" "+operation+"= "+not+Instant_Operand+";\n";
                    } else {
                        jDialog_Loading.setVisible(false);
                        Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                        JOptionPane.showMessageDialog(parentComponent, "Operation ("+operation+") can not be berformed between BOOL types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                        success = false;
                    }
                } catch (NumberFormatException ex) {
                    String Variable_temp;
                    String nameOfVariable = "Variabe Not Found";
                    String typeOfVariable = "Not Supported Type";
                    for (int i = 1; i < Data.size_Vaiables-1; i++) {
                        Variable_temp = Data.Vaiables[i].replace(" ", "");
                        if (Variable_temp.contains(Operand)) {
                            nameOfVariable = Variable_temp.split(":")[0];
                            typeOfVariable = Variable_temp.split(":")[1];
                            break;
                        }
                    }
                    int Register_Type = new GeneralFunctions().getRegesterTypeFromStringType(typeOfVariable);
                    if  (
                            Support_BOOL ||
                            (
                                Data.Current_Register_Type[Data.Load_index]   != Data.BOOL &&
                                Register_Type != Data.BOOL
                            )
                        ) {
                        if (Data.Current_Register_Type[Data.Load_index] == Register_Type) {
                            Data.C_code += "\t\tvar"+(Data.Load_index)+" "+operation+"= "+not+nameOfVariable+";\n";
                        } else {
                            jDialog_Loading.setVisible(false);
                            Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                            JOptionPane.showMessageDialog(parentComponent, "Can not make arithmetic operations on variable \""+nameOfVariable+"\"of type \""+typeOfVariable+"\"\nIncompatable types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                            success = false;
                        }
                    } else {
                        jDialog_Loading.setVisible(false);
                        Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                        JOptionPane.showMessageDialog(parentComponent, "Operation ("+operation+") can not be berformed between BOOL types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                        success = false;
                    }
                }
            }
        }
        return success;
    }

    private boolean add_comparison_c_command(Component parentComponent, String Operand, String compare, JDialog jDialog_Loading) {
        boolean success = true;
        if (Operand.contains("(")) {
            Data.Load_index_Save[Data.Load_index][0] = compare;
            Data.Load_index_Save[Data.Load_index][1] = "C";
            Data.Load_index_Save[Data.Load_index][2] = Boolean.toString(true);
            Data.Load_index_Save[Data.Load_index][3] = Boolean.toString(false);
            Data.Load_index++;
        } else if (Operand.contains(")")) {
            Data.Load_index--;
            if (Data.Current_Register_Type[Data.Load_index] == Data.Current_Register_Type[Data.Load_index+1]) {
                if (Data.Current_Register_Type[Data.Load_index] == Data.BOOL) {
                    Data.C_code += "\t\tif (var"+(Data.Load_index)+" "+compare+" var"+(Data.Load_index+1)+") var"+(Data.Load_index)+" = 1; else var"+(Data.Load_index)+" = 0;\n";
                } else {
                    Data.Current_Register_Count++;
                    Data.Load_index++;
                    Data.C_code += "\t\tif (var"+(Data.Load_index-1)+" "+compare+" var"+(Data.Load_index)+") var"+(Data.Load_index)+" = 1; else var"+(Data.Load_index)+" = 0;\n";
                    Data.Load_index_is_defined[Data.Load_index] = true;
                    Data.Current_Register_Type[Data.Load_index] = Data.BOOL;
                    Data.Load_index_Save[Data.Load_index][3] = Boolean.toString(true); // mismatch type
                }
            } else {
                jDialog_Loading.setVisible(false);
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Incompatable Comparison ("+compare+") between types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                success = false;
            }
        } else {
            int Instant_Operand;
            if (Operand.contains("%")){
                Operand = Operand.replaceAll("%", "");
                String offc = Operand.split("\\.")[1];
                Operand = Operand.split("\\.")[0];
                if (Data.Current_Register_Type[Data.Load_index] == Data.BOOL) {
                    Data.C_code += "\t\tif (var"+(Data.Load_index)+" "+compare+" io_per_get_input(&io_per_d, "+Operand+", "+offc+")) var"+(Data.Load_index)+" = 1; else var"+(Data.Load_index)+" = 0;\n";
                } else {
                    jDialog_Loading.setVisible(false);
                    Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                    JOptionPane.showMessageDialog(parentComponent, "Can not compare between non-BOOL and BOOL type\nIncompatable operation ("+compare+") between types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                    success = false;
                }
            } else if (Operand.contains("T#")) {
                double time_sec = new GeneralFunctions().getSecFromTimeFormat(Operand);
                long Number_of_Clocks = (long) (time_sec*(double)Data.CPU_RV64_Timer_Freq);
                if (Data.Current_Register_Type[Data.Load_index] == Data.TIME) {
                    Data.C_code += "\t\tif (var"+(Data.Load_index)+" "+compare+" "+Number_of_Clocks+") var"+(Data.Load_index)+" = 1; else var"+(Data.Load_index)+" = 0;\n";
                } else {
                    jDialog_Loading.setVisible(false);
                    Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                    JOptionPane.showMessageDialog(parentComponent, "Can not compare between non-TIME and TIME type\nIncompatable operation ("+compare+") between types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                    success = false;
                }
            } else {
                try {
                    Instant_Operand = Integer.parseInt(Operand);
                    if (Data.Current_Register_Type[Data.Load_index] == Data.BOOL) {
                        Data.C_code += "\t\tif (var"+(Data.Load_index)+" "+compare+" "+Instant_Operand+") var"+(Data.Load_index)+" = 1; else var"+(Data.Load_index)+" = 0;\n";
                    } else {
                        Data.Current_Register_Count++;
                        Data.Load_index++;
                        Data.C_code += "\t\tuint8_t var"+(Data.Load_index)+";\n";
                        Data.C_code += "\t\tif (var"+(Data.Load_index-1)+" "+compare+" "+Instant_Operand+") var"+(Data.Load_index)+" = 1; else var"+(Data.Load_index)+" = 0;\n";
                        Data.Load_index_is_defined[Data.Load_index] = true;
                        Data.Current_Register_Type[Data.Load_index] = Data.BOOL;
                    }
                } catch (NumberFormatException ex) {
                    String Variable_temp;
                    String nameOfVariable = "Variabe Not Found";
                    String typeOfVariable = "Not Supported Type";
                    for (int i = 1; i < Data.size_Vaiables-1; i++) {
                        Variable_temp = Data.Vaiables[i].replace(" ", "");
                        if (Variable_temp.contains(Operand)) {
                            nameOfVariable = Variable_temp.split(":")[0];
                            typeOfVariable = Variable_temp.split(":")[1];
                            break;
                        }
                    }
                    int Register_Type = new GeneralFunctions().getRegesterTypeFromStringType(typeOfVariable);
                    if (Data.Current_Register_Type[Data.Load_index] == Register_Type) {
                        if (Data.Current_Register_Type[Data.Load_index] == Data.BOOL) {
                            Data.C_code += "\t\tif (var"+(Data.Load_index)+" "+compare+" "+nameOfVariable+") var"+(Data.Load_index)+" = 1; else var"+(Data.Load_index)+" = 0;\n";
                        } else {
                            Data.Current_Register_Count++;
                            Data.Load_index++;
                            Data.C_code += "\t\tuint8_t var"+(Data.Load_index)+";\n";
                            Data.C_code += "\t\tif (var"+(Data.Load_index)+" "+compare+" "+nameOfVariable+") var"+(Data.Load_index)+" = 1; else var"+(Data.Load_index)+" = 0;\n";
                            Data.Load_index_is_defined[Data.Load_index] = true;
                            Data.Current_Register_Type[Data.Load_index] = Data.BOOL;
                        }
                    } else {
                        jDialog_Loading.setVisible(false);
                        Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                        JOptionPane.showMessageDialog(parentComponent, "Incompatable type comparison ("+compare+") between types", "Compile As Hardware", JOptionPane.OK_OPTION, icon);
                        success = false;
                    }
                }
            }
        }
        return success;
    }
    
    private boolean TON_compile_sw(Component parentComponent, String Operand, String il_inst, int rung_i, int program_i, JDialog jDialog_Loading) {
        String typeOfVariable;
        String[] il_inst_Arr = new String[1];
                
        if (Data.Number_Of_Timers_In_Program_SW < Data.MAX_NUMBER_OF_TIMERS_SW) {
            Data.Number_Of_Timers_In_Program_SW++;
            switch (Data.Number_Of_Timers_In_Program_SW) {
                case 1:
                    Data.C_code = new GeneralFunctions().insertStringAfter(Data.localVariables, "\tgptimers_map *p_timer;\n\tuint32_t timer0_is_enabled;\n\tuint32_t timer0_output;\n", Data.C_code);
                    Data.C_code = new GeneralFunctions().insertStringAfter(Data.initializeLocalVariables,   "\tp_timer = (gptimers_map *)ADDR_BUS0_XSLV_GPTIMERS;\n" +
                                                                                                            "\tp_timer->timer[0].control = TIMER_CONTROL_DIST_DISIRQ_NOOV;\n" +
                                                                                                            "\tp_timer->timer[0].cur_value = 0;\n" +
                                                                                                            "\ttimer0_is_enabled = TIMER_DISABLED;\n" +
                                                                                                            "\ttimer0_output = 0;\n", Data.C_code);
                    break;
                case 2:
                    
                    Data.C_code = new GeneralFunctions().insertStringAfter(Data.localVariables, "\tuint32_t timer1_is_enabled;\n\tuint32_t timer1_output;\n", Data.C_code);
                    Data.C_code = new GeneralFunctions().insertStringAfter("timer0_output = 0;\n", "\tp_timer->timer[1].control = TIMER_CONTROL_DIST_DISIRQ_NOOV;\n" +
                                                                                                            "\tp_timer->timer[1].cur_value = 0;\n" +
                                                                                                            "\ttimer1_is_enabled = TIMER_DISABLED;\n" +
                                                                                                            "\ttimer1_output = 0;\n", Data.C_code);
                    break;
                default:
                    jDialog_Loading.setVisible(false);
                    JOptionPane.showMessageDialog(parentComponent, "This CPU has only two timers.\nPlease compile as hardware or use optimaization algorithm.", "Compile il", JOptionPane.OK_OPTION);
                    return false;
                }
                int timer_number = (Data.Number_Of_Timers_In_Program_SW-1);
                        
                Data.C_code += "\n\t\t// TON "+Operand+"\n";
                Operand = il_inst.split(":=")[1];
                int Instant_Operand;
                if (Operand.contains("%")){
                    Operand = Operand.replaceAll("%", "");
                    String offc = Operand.split("\\.")[1];
                    Operand = Operand.split("\\.")[0];
                    if (!Data.Load_index_is_defined[Data.Load_index]) {
                        Data.C_code += "\t\tuint8_t var"+Data.Load_index+" = io_per_get_input(&io_per_d, "+Operand+", "+offc+");\n";
                        Data.Load_index_is_defined[Data.Load_index] = true;
                        Data.Current_Register_Type[Data.Load_index] = Data.BOOL;
                    } else {
                        if (Data.Current_Register_Type[Data.Load_index] == Data.BOOL) {
                            Data.C_code += "\t\tvar"+Data.Load_index+" = io_per_get_input(&io_per_d, "+Operand+", "+offc+");\n";
                        } else {
                            Data.Current_Register_Count++;
                            Data.Load_index++;
                            Data.C_code += "\t\tuint8_t var"+Data.Load_index+" = io_per_get_input(&io_per_d, "+Operand+", "+offc+");\n";
                            Data.Load_index_is_defined[Data.Load_index] = true;
                            Data.Current_Register_Type[Data.Load_index] = Data.BOOL;
                        }
                    }
                } else {
                    try {
                        Instant_Operand = Integer.parseInt(Operand);
                        if (!Data.Load_index_is_defined[Data.Load_index]) {
                            Data.C_code += "\t\tint64_t var"+Data.Load_index+" = "+Instant_Operand+";\n";
                            Data.Load_index_is_defined[Data.Load_index] = true;
                            Data.Current_Register_Type[Data.Load_index] = Data.INT64_T;
                        } else {
                            if (Data.Current_Register_Type[Data.Load_index] == Data.INT64_T) {
                                Data.C_code += "\t\tvar"+Data.Load_index+" = "+Instant_Operand+";\n";
                            } else {
                                Data.Current_Register_Count++;
                                Data.Load_index++;
                                Data.C_code += "\t\tint64_t var"+Data.Load_index+" = "+Instant_Operand+";\n";
                                Data.Load_index_is_defined[Data.Load_index] = true;
                                Data.Current_Register_Type[Data.Load_index] = Data.INT64_T;
                            }
                        }
                    } catch (NumberFormatException ex) {
                        String Variable_temp;
                        typeOfVariable = "Not Supported Type";
                        String nameOfVariable = "Variabe Not Found";
                        for (int i = 1; i < Data.size_Vaiables-1; i++) {
                            Variable_temp = Data.Vaiables[i].replace(" ", "");
                            if (Variable_temp.contains(Operand)) {
                                nameOfVariable = Variable_temp.split(":")[0];
                                typeOfVariable = Variable_temp.split(":")[1];
                                break;
                            }
                        }
                        
                        if (typeOfVariable.equals("BOOL")) {
                            if (!Data.Load_index_is_defined[Data.Load_index]) {
                                Data.C_code += "\t\tuint8_t var"+Data.Load_index+" = "+nameOfVariable+";\n";
                                Data.Load_index_is_defined[Data.Load_index] = true;
                                Data.Current_Register_Type[Data.Load_index] = Data.BOOL;
                            } else {
                                Data.C_code += "\t\tvar"+Data.Load_index+" = "+nameOfVariable+";\n";
                                Data.Current_Register_Count++;
                                
                                if (Data.Current_Register_Type[Data.Load_index] == Data.BOOL) {
                                    Data.C_code += "\t\tvar"+Data.Load_index+" = "+nameOfVariable+";\n";
                                } else {
                                    Data.Current_Register_Count++;
                                    Data.Load_index++;
                                    Data.C_code += "\t\tuint8_t var"+Data.Load_index+" = "+nameOfVariable+";\n";
                                    Data.Load_index_is_defined[Data.Load_index] = true;
                                    Data.Current_Register_Type[Data.Load_index] = Data.BOOL;
                                }
                            }
                        } else {
                            jDialog_Loading.setVisible(false);
                            JOptionPane.showMessageDialog(parentComponent, "Type of Variable\" "+nameOfVariable+"\" should be \"BOOL\".", "Compile il", JOptionPane.OK_OPTION);
                            return false;
                        }
                    }
                }
                        
                program_i++; // Preset Time (PT)
                il_inst = Data.Program_2D[rung_i][program_i];
                il_inst_Arr[0] = il_inst;
                new GeneralFunctions().remove_Spaces_Before_Strings(il_inst_Arr, 1);
                il_inst = il_inst_Arr[0].replaceAll(" ", "");
                il_inst = il_inst.replaceAll(",", "");
                Operand = il_inst.split(":=")[1];
                        
                typeOfVariable = "No Type";
                for (int i = 1; i < Data.size_Vaiables-1; i++) {
                    String Variable_temp = Data.Vaiables[i].replace(" ", "");
                    if (Variable_temp.contains(Operand)) {
                    typeOfVariable = Variable_temp.split(":")[1];
                    break;
                }
            }
                        
            String Preset_Time;
                       
            if (typeOfVariable.contains("TIME")) {
                Preset_Time = Operand;
            } else if (Operand.contains("T#")) {
                double time_sec = new GeneralFunctions().getSecFromTimeFormat(Operand);
                long Number_of_Clocks = (long) (time_sec*(double)Data.CPU_RV64_Timer_Freq);
                Preset_Time = "(uint64_t)"+Number_of_Clocks;
            } else {
                jDialog_Loading.setVisible(false);
                JOptionPane.showMessageDialog(parentComponent, "Preset time should be variable with type \"TIME\" or instant begins with T#.", "Compile il", JOptionPane.OK_OPTION);
                return false;
            }
                        
            program_i++; // Elapsed Time (ET)
            il_inst = Data.Program_2D[rung_i][program_i];
            il_inst_Arr[0] = il_inst;
            new GeneralFunctions().remove_Spaces_Before_Strings(il_inst_Arr, 1);
            il_inst = il_inst_Arr[0].replaceAll(" ", "");
            il_inst = il_inst.replaceAll(",", "");
            Operand = il_inst.split("=>")[1];
                       
            String Elapset_Time;
                       
            typeOfVariable = "No Type";
            for (int i = 1; i < Data.size_Vaiables-1; i++) {
                String Variable_temp = Data.Vaiables[i].replace(" ", "");
                if (Variable_temp.contains(Operand)) {
                    typeOfVariable = Variable_temp.split(":")[1];
                    break;
                }
            }
                        
            if (typeOfVariable.contains("TIME")) {
                Elapset_Time = Operand;
            } else {
                jDialog_Loading.setVisible(false);
                JOptionPane.showMessageDialog(parentComponent, "Elapsed time should be variable with type \"TIME\".", "Compile il", JOptionPane.OK_OPTION);
                return false;
            }
                        
            program_i++; // Q
            il_inst = Data.Program_2D[rung_i][program_i];
            il_inst_Arr[0] = il_inst;
            new GeneralFunctions().remove_Spaces_Before_Strings(il_inst_Arr, 1);
            il_inst = il_inst_Arr[0].replaceAll(" ", "");
            il_inst = il_inst.replaceAll("\\)", "");
            Operand = il_inst.split("=>")[1];
                        
            String Output_Timer;
            if (Operand.contains("%")){
                Operand = Operand.replaceAll("%", "");
                String offc = Operand.split("\\.")[1];
                Operand = Operand.split("\\.")[0];
                Output_Timer = "\t\tio_per_set_output(&io_per_d, "+Operand+", "+offc+", timer"+timer_number+"_output);\n";
            } else {
                try {
                    jDialog_Loading.setVisible(false);
                    JOptionPane.showMessageDialog(parentComponent, "Tho output of the timer shouldn't be instant value.", "Compile il", JOptionPane.OK_OPTION);
                    return false;
                } catch (NumberFormatException ex) {
                    String Variable_temp;
                    typeOfVariable = "Not Supported Type";
                    String nameOfVariable = "Variabe Not Found";
                    for (int i = 1; i < Data.size_Vaiables-1; i++) {
                        Variable_temp = Data.Vaiables[i].replace(" ", "");
                        if (Variable_temp.contains(Operand)) {
                            nameOfVariable = Variable_temp.split(":")[0];
                            typeOfVariable = Variable_temp.split(":")[1];
                            break;
                        }
                    }
                               
                    if (typeOfVariable.equals("BOOL")) {
                        Output_Timer = "\t\t"+nameOfVariable+" = timer"+timer_number+"_output;\n";
                    } else {
                        jDialog_Loading.setVisible(false);
                        JOptionPane.showMessageDialog(parentComponent, "Type of Variable\" "+nameOfVariable+"\" should be \"BOOL\".", "Compile il", JOptionPane.OK_OPTION);
                        return false;
                    }
                }
            }
                        
            Data.C_code +="\t\tif (var"+Data.Load_index+") {\n"
                        + "\t\t\tif (timer"+timer_number+"_is_enabled) {\n"
                        + "\t\t\t\tif ((p_timer->timer["+timer_number+"].control & 4) == 4) {\n"
                        + "\t\t\t\t\t"+Elapset_Time+" = p_timer->timer["+timer_number+"].init_value;\n"
                        + "\t\t\t\t\ttimer"+timer_number+"_output = 1;\n"
                        + "\t\t\t\t} else {\n"
                        + "\t\t\t\t\t"+Elapset_Time+" = p_timer->timer["+timer_number+"].init_value - p_timer->timer["+timer_number+"].cur_value;\n"
                        + "\t\t\t\t}\n"
                        + "\t\t\t} else {\n"
                        + "\t\t\t\tp_timer->timer["+timer_number+"].init_value = "+Preset_Time+";\n"
                        + "\t\t\t\tp_timer->timer["+timer_number+"].cur_value = 0;\n"
                        + "\t\t\t\tp_timer->timer["+timer_number+"].control = TIMER_CONTROL_ENT_DISIRQ_NOOV;\n"
                        + "\t\t\t\ttimer"+timer_number+"_is_enabled = TIMER_ENABLED;\n"
                        + "\t\t\t\t"+Elapset_Time+" = 0;\n"
                        + "\t\t\t}\n"
                        + "\t\t} else {\n"
                        + "\t\t\tp_timer->timer["+timer_number+"].control = TIMER_CONTROL_DIST_DISIRQ_NOOV;\n"
                        + "\t\t\ttimer"+timer_number+"_is_enabled = TIMER_DISABLED;\n"
                        + "\t\t\t"+Elapset_Time+" = 0;\n"
                        + "\t\t\ttimer"+timer_number+"_output = 0;\n"
                        + "\t\t}\n"
                        + Output_Timer
                        + "\n"
                        + "";

        } else {
            jDialog_Loading.setVisible(false);
            JOptionPane.showMessageDialog(parentComponent, "There is only two timers in the core.", "Compile il", JOptionPane.OK_OPTION);
            return false;
        }
        return true;
    }
    
    private boolean PWM_compile_sw(Component parentComponent, String Operand, String il_inst, int rung_i, int program_i, JDialog jDialog_Loading) {
        String typeOfVariable;
        String nameOfVariable;
        String Duty_Cycle;
        String[] il_inst_Arr = new String[1];
                
        if (Data.Number_Of_Timers_In_Program_SW < Data.MAX_NUMBER_OF_TIMERS_SW) {
            Data.Number_Of_Timers_In_Program_SW++;
            switch (Data.Number_Of_Timers_In_Program_SW) {
                case 1:
                    Data.C_code = new GeneralFunctions().insertStringAfter(Data.localVariables, "\tgptimers_map *p_timer;\n\tuint32_t timer0_is_enabled;\n\tuint32_t timer0_output;\n\tuint32_t pwm0_output;\n", Data.C_code);
                    Data.C_code = new GeneralFunctions().insertStringAfter(Data.initializeLocalVariables,   "\tp_timer = (gptimers_map *)ADDR_BUS0_XSLV_GPTIMERS;\n" +
                                                                                                            "\tp_timer->timer[0].control = TIMER_CONTROL_DIST_DISIRQ_NOOV;\n" +
                                                                                                            "\tp_timer->timer[0].cur_value = 0;\n" +
                                                                                                            "\ttimer0_is_enabled = TIMER_DISABLED;\n" +
                                                                                                            "\tpwm0_output = 0;\n", Data.C_code);
                    break;
                case 2:
                    
                    Data.C_code = new GeneralFunctions().insertStringAfter(Data.localVariables, "\tuint32_t timer1_is_enabled;\n\tuint32_t timer1_output;\n\tuint32_t pwm1_output;\n", Data.C_code);
                    Data.C_code = new GeneralFunctions().insertStringAfter("pwm0_output = 0;\n", "\tp_timer->timer[1].control = TIMER_CONTROL_DIST_DISIRQ_NOOV;\n" +
                                                                                                            "\tp_timer->timer[1].cur_value = 0;\n" +
                                                                                                            "\ttimer1_is_enabled = TIMER_DISABLED;\n" +
                                                                                                            "\tpwm1_output = 0;\n", Data.C_code);
                    break;
                default:
                    jDialog_Loading.setVisible(false);
                    JOptionPane.showMessageDialog(parentComponent, "This CPU has only two timers.\nPlease compile as hardware or use optimaization algorithm.", "Compile il", JOptionPane.OK_OPTION);
                    return false;
            }
            int timer_number = (Data.Number_Of_Timers_In_Program_SW-1);
                        
            Data.C_code += "\n\t\t// PWM "+Operand+"\n";
            Operand = il_inst.split(":=")[1];
            int Integer_Operand;
            if (Operand.contains("%")){
                jDialog_Loading.setVisible(false);
                JOptionPane.showMessageDialog(parentComponent, "Frequency can't be BOOL.", "Compile il SW", JOptionPane.OK_OPTION);
                return false;
            } else {
                try {
                    long Number_of_Clocks = (long) ((double)Data.CPU_RV64_Timer_Freq / Double.parseDouble(Operand));
                    if (!Data.Load_index_is_defined[Data.Load_index]) {
                        Data.C_code += "\t\tuint64_t var"+Data.Load_index+" = (uint64_t)"+Number_of_Clocks+";\n";
                        Data.Load_index_is_defined[Data.Load_index] = true;
                        Data.Current_Register_Type[Data.Load_index] = Data.UINT64_T;
                    } else {
                        if (Data.Current_Register_Type[Data.Load_index] == Data.UINT64_T) {
                            Data.C_code += "\t\tvar"+Data.Load_index+" = (uint64_t)"+Number_of_Clocks+";\n";
                        } else {
                            Data.Current_Register_Count++;
                            Data.Load_index++;
                            Data.C_code += "\t\tuint64_t var"+Data.Load_index+" = (uint64_t)"+Number_of_Clocks+";\n";
                            Data.Load_index_is_defined[Data.Load_index] = true;
                            Data.Current_Register_Type[Data.Load_index] = Data.UINT64_T;
                        }
                    }
                } catch (NumberFormatException ex) {
                    String Variable_temp;
                    typeOfVariable = "Not Supported Type";
                    nameOfVariable = "Variabe Not Found";
                    for (int i = 1; i < Data.size_Vaiables-1; i++) {
                        Variable_temp = Data.Vaiables[i].replace(" ", "");
                        if (Variable_temp.contains(Operand)) {
                            nameOfVariable = Variable_temp.split(":")[0];
                            typeOfVariable = Variable_temp.split(":")[1];
                            break;
                        }
                    }
                    if (new GeneralFunctions().is_contain_str_arr(typeOfVariable, Data.SUPPORTED_PWM_FRQ_DC)) {
                        if (!Data.Load_index_is_defined[Data.Load_index]) {
                            Data.C_code += "\t\tuint64_t var"+Data.Load_index+" = (uint64_t) ("+Data.CPU_RV64_Timer_Freq+"/"+nameOfVariable+");\n";
                            Data.Load_index_is_defined[Data.Load_index] = true;
                            Data.Current_Register_Type[Data.Load_index] = Data.UINT64_T;
                        } else {
                            if (Data.Current_Register_Type[Data.Load_index] == Data.UINT64_T) {
                                Data.C_code += "\t\tvar"+Data.Load_index+" = (uint64_t) ("+Data.CPU_RV64_Timer_Freq+"/"+nameOfVariable+");\n";
                            } else {
                                Data.Current_Register_Count++;
                                Data.Load_index++;
                                Data.C_code += "\t\tuint64_t var"+Data.Load_index+" = (uint64_t) ("+Data.CPU_RV64_Timer_Freq+"/"+nameOfVariable+");\n";
                                Data.Load_index_is_defined[Data.Load_index] = true;
                                Data.Current_Register_Type[Data.Load_index] = Data.UINT64_T;
                            }
                        }
                    } else {
                        jDialog_Loading.setVisible(false);
                        JOptionPane.showMessageDialog(parentComponent, "Type of Variable\""+nameOfVariable+"\" is not correct.\nSee supported types for frequency.", "Compile il SW", JOptionPane.OK_OPTION);
                        return false;
                    }
                }
            }
                        
            program_i++; // Duty Cycle (DC)
            il_inst = Data.Program_2D[rung_i][program_i];
            il_inst_Arr[0] = il_inst;
            new GeneralFunctions().remove_Spaces_Before_Strings(il_inst_Arr, 1);
            il_inst = il_inst_Arr[0].replaceAll(" ", "");
            il_inst = il_inst.replaceAll(",", "");
            Operand = il_inst.split(":=")[1];
                        
            typeOfVariable = "Not Supported Type";
            nameOfVariable = "Variabe Not Found";
            for (int i = 1; i < Data.size_Vaiables-1; i++) {
            String Variable_temp = Data.Vaiables[i].replace(" ", "");
                if (Variable_temp.contains(Operand)) {
                    nameOfVariable = Variable_temp.split(":")[0];
                    typeOfVariable = Variable_temp.split(":")[1];
                    break;
                }
            }
                       
            if (Operand.contains("%")){
                jDialog_Loading.setVisible(false);
                JOptionPane.showMessageDialog(parentComponent, "Duty Cycle can't be BOOL.", "Compile il SW", JOptionPane.OK_OPTION);
                return false;
            } else if (Operand.contains("T#")) {
                jDialog_Loading.setVisible(false);
                JOptionPane.showMessageDialog(parentComponent, "Duty Cycle can't be TIME.", "Compile il SW", JOptionPane.OK_OPTION);
                return false;
            } else {
                try {
                    Integer_Operand = Integer.parseInt(Operand);
                    Data.C_code += "\t\tuint64_t I_Duty_Cycle_"+timer_number+" = (uint64_t) ((var"+Data.Load_index+"/100)*"+Integer_Operand+");\n";
                    Duty_Cycle = "I_Duty_Cycle_"+timer_number;
                } catch (NumberFormatException ex) {
                    if (new GeneralFunctions().is_contain_str_arr(typeOfVariable, Data.SUPPORTED_PWM_FRQ_DC)) {
                        Data.C_code += "\t\tuint64_t I_Duty_Cycle_"+timer_number+" = (uint64_t) ((var"+Data.Load_index+"/100)*"+Operand+");\n";
                        Duty_Cycle = "I_Duty_Cycle_"+timer_number;
                    } else {
                        jDialog_Loading.setVisible(false);
                        JOptionPane.showMessageDialog(parentComponent, "Type of Variable\" "+nameOfVariable+"\" is not correct.\nSee supported types for duty cycle.", "Compile il SW", JOptionPane.OK_OPTION);
                        return false;
                    }
                }
            }
                        
            program_i++; // Q
            il_inst = Data.Program_2D[rung_i][program_i];
            il_inst_Arr[0] = il_inst;
            new GeneralFunctions().remove_Spaces_Before_Strings(il_inst_Arr, 1);
            il_inst = il_inst_Arr[0].replaceAll(" ", "");
            il_inst = il_inst.replaceAll("\\)", "");
            Operand = il_inst.split("=>")[1];
                        
            String Output_Timer;
            if (Operand.contains("%")){
                Operand = Operand.replaceAll("%", "");
                String offc = Operand.split("\\.")[1];
                Operand = Operand.split("\\.")[0];
                Output_Timer = "\t\tio_per_set_output(&io_per_d, "+Operand+", "+offc+", pwm"+timer_number+"_output);\n";
            } else {
                try {
                    Integer_Operand = Integer.parseInt(Operand);
                    jDialog_Loading.setVisible(false);
                    JOptionPane.showMessageDialog(parentComponent, "Tho output of the timer \""+Integer_Operand+"\" shouldn't be instant value.", "Compile il", JOptionPane.OK_OPTION);
                    return false;
                } catch (NumberFormatException ex) {
                    String Variable_temp;
                    typeOfVariable = "Not Supported Type";
                    nameOfVariable = "Variabe Not Found";
                    for (int i = 1; i < Data.size_Vaiables-1; i++) {
                        Variable_temp = Data.Vaiables[i].replace(" ", "");
                        if (Variable_temp.contains(Operand)) {
                            nameOfVariable = Variable_temp.split(":")[0];
                            typeOfVariable = Variable_temp.split(":")[1];
                            break;
                        }
                    }
                               
                    if (typeOfVariable.equals("BOOL")) {
                        Output_Timer = "\t\t"+nameOfVariable+" = pwm"+timer_number+"_output;\n";
                    } else {
                        jDialog_Loading.setVisible(false);
                        JOptionPane.showMessageDialog(parentComponent, "Type of Variable\""+nameOfVariable+"\" should be \"BOOL\".", "Compile il", JOptionPane.OK_OPTION);
                        return false;
                    }
                }
            }
            
            Data.C_code +="\t\tif ((timer"+timer_number+"_is_enabled) && ((p_timer->timer["+timer_number+"].control >> 2) == 0)) {\n"
                        + "\t\t\tif (p_timer->timer["+timer_number+"].cur_value >= (p_timer->timer["+timer_number+"].init_value - "+Duty_Cycle+")) {\n"
                        + "\t\t\t\tpwm"+timer_number+"_output = 1;\n"
                        + "\t\t\t} else {\n"
                        + "\t\t\t\tpwm"+timer_number+"_output = 0;\n"
                        + "\t\t\t}\n"
                        + "\t\t} else {\n"
                        + "\t\t\tp_timer->timer["+timer_number+"].init_value = var"+Data.Load_index+";\n"
                        + "\t\t\tp_timer->timer["+timer_number+"].cur_value = 0;\n"
                        + "\t\t\tp_timer->timer["+timer_number+"].control = TIMER_CONTROL_ENT_DISIRQ_NOOV;\n"
                        + "\t\t\ttimer"+timer_number+"_is_enabled = TIMER_ENABLED;\n"
                        + "\t\t}\n"
                        + Output_Timer
                        + "\n"
                        + "";

        } else {
            jDialog_Loading.setVisible(false);
            JOptionPane.showMessageDialog(parentComponent, "There is only two timers in the core.", "Compile il", JOptionPane.OK_OPTION);
            return false;
        }
        return true;
    }
}
