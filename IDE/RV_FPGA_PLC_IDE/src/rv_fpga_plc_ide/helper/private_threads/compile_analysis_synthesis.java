/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.private_threads;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import rv_fpga_plc_ide.helper.Data;
import rv_fpga_plc_ide.helper.ProjectManagement;
import rv_fpga_plc_ide.helper.execute_command;

/**
 *
 * @author hossameldin
 */
public class compile_analysis_synthesis extends Thread {
        private final String Project_Folder;
        private final java.awt.event.ActionEvent evt;
        private final JDialog jDialog_Loading;
        private final Component parentComponent;
        private final JFileChooser jFileChooser1;
        private final JTextArea jTextArea_Output_Tab;
        
        int hdl_compilation_state;
        
        public compile_analysis_synthesis(Component parentComponent, String Project_Folder, java.awt.event.ActionEvent evt, JDialog jDialog_Loading, JFileChooser jFileChooser1, JTextArea jTextArea_Output_Tab) {
            this.Project_Folder = Project_Folder;
            this.evt = evt;
            this.jDialog_Loading = jDialog_Loading;
            this.parentComponent = parentComponent;
            this.jFileChooser1 = jFileChooser1;
            this.jTextArea_Output_Tab = jTextArea_Output_Tab;
        }
        
        @Override
        public void run() {
            jTextArea_Output_Tab.append("      Starting Analysis snd Synthesis\n");
            String Project_Name;
            if (Data.core == Data.RV32) {
                Project_Name = "RV_FPGA_PLC_Potato";
            } else {
                Project_Name = "River_SoC";
            }
            String cmd = "/home/hossameldin/intelFPGA_lite/18.0/quartus/bin/quartus_map --read_settings_files=on --write_settings_files=off "+Project_Folder+Project_Name+" -c "+Project_Folder+Project_Name;
            int exitValue = new execute_command().execute_command(cmd, "        ", Data.deafult_out_window, jTextArea_Output_Tab);
            if (exitValue == 0) {
                compile_fitter cf = new compile_fitter(parentComponent, Project_Folder, evt, jDialog_Loading, jFileChooser1, jTextArea_Output_Tab);
                hdl_compilation_state = Data.ANALYSIS_SYNTHESIS;
                Data.Number_Of_Timers_Compiled = Data.Number_Of_Timers_In_Program;
                Data.Number_Of_PWMs_Compiled = Data.Number_Of_PWMs_In_Program;
                Data.Number_Of_PIDs_Compiled = Data.Number_Of_PIDs_In_Program;
                Data.compiled_core = Data.core;
                cf.start();
            } else {
                jDialog_Loading.setVisible(false);
                hdl_compilation_state = Data.NO_COMPILATION;
                Data.Number_Of_Timers_Compiled = 0;
                Data.Number_Of_PWMs_Compiled = 0;
                Data.Number_Of_PIDs_Compiled = 0;
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Analysis Synthesis Not Successful", "Compile As Software", JOptionPane.OK_OPTION, icon);
            }
            
            if (Data.core == Data.RV32) {
                if (Data.Compiling_Type == Data.SW_COMPILING) {
                    Data.hdl_compilation_state_RV32_SW = hdl_compilation_state;
                } else if (Data.Compiling_Type == Data.HW_COMPILING) {
                    Data.hdl_compilation_state_RV32_HW = hdl_compilation_state;
                }
            } else {
                if (Data.Compiling_Type == Data.SW_COMPILING) {
                    Data.hdl_compilation_state_RV64_SW = hdl_compilation_state;
                    Data.ALU_Support_Compiled_RV64_SW = Data.ALU_Support_In_Program_RV64_SW;
                } else if (Data.Compiling_Type == Data.HW_COMPILING) {
                    Data.hdl_compilation_state_RV64_HW = hdl_compilation_state;
                    Data.ALU_Support_Compiled_RV64_HW = Data.ALU_Support_In_Program_RV64_HW;
                }
            }
            
            new ProjectManagement().saveProject(parentComponent, jFileChooser1);
        }
    }
