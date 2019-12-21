/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.compile_hld;

import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import rv_fpga_plc_ide.helper.Data;
import rv_fpga_plc_ide.helper.private_threads.compile_analysis_synthesis;
import rv_fpga_plc_ide.helper.private_threads.compile_assembler;
import rv_fpga_plc_ide.helper.private_threads.compile_fitter;
import rv_fpga_plc_ide.helper.private_threads.compile_update_mif;

/**
 *
 * @author hossameldin
 */
public class CompileHLD {
    
    public void compile_hdl(Component parentComponent, String Project_Folder, java.awt.event.ActionEvent evt, int hdl_compilation_type, JDialog jDialog_Loading, JFileChooser jFileChooser1, JTextArea jTextArea_Output_Tab) {
        int hdl_compilation_state = Data.NO_COMPILATION;
        
        if (hdl_compilation_type == Data.SW_COMPILATION) {
            if (Data.core == Data.RV32) {
                Project_Folder = Project_Folder + "/q_files_RV32_SW/";
                hdl_compilation_state = Data.hdl_compilation_state_RV32_SW;
            } else {
                Project_Folder = Project_Folder + "/q_files_RV64_SW/";
                hdl_compilation_state = Data.hdl_compilation_state_RV64_SW;
            }
        } else if (hdl_compilation_type == Data.HW_COMPILATION) {
            if (Data.core == Data.RV32) {
                Project_Folder = Project_Folder + "/q_files_RV32_HW/";
                hdl_compilation_state = Data.hdl_compilation_state_RV32_HW;
            } else {
                Project_Folder = Project_Folder + "/q_files_RV64_HW/";
                hdl_compilation_state = Data.hdl_compilation_state_RV64_HW;
            }
        }
        
        boolean no_hardware_change = check_hardware_change(hdl_compilation_type);
        if (no_hardware_change) {
            switch (hdl_compilation_state) {
                case Data.NO_COMPILATION:
                    compile_analysis_synthesis cas = new compile_analysis_synthesis(parentComponent, Project_Folder, evt, hdl_compilation_type, jDialog_Loading, jFileChooser1, jTextArea_Output_Tab);
                    cas.start();
                    break;
                case Data.ANALYSIS_SYNTHESIS:
                    compile_fitter cf = new compile_fitter(parentComponent, Project_Folder, evt, hdl_compilation_type, jDialog_Loading, jFileChooser1, jTextArea_Output_Tab);
                    cf.start();
                    break;
                case Data.FITTER:
                    compile_assembler ca = new compile_assembler(parentComponent, Project_Folder, evt, hdl_compilation_type, jDialog_Loading, jFileChooser1, jTextArea_Output_Tab);
                    ca.start();
                    break;
                case Data.ASSEMBLER:
                    compile_update_mif cum = new compile_update_mif(parentComponent, Project_Folder, evt, hdl_compilation_type, jDialog_Loading, jFileChooser1, jTextArea_Output_Tab);
                    cum.start();
                    break;
                case Data.UPDATED:
                    jTextArea_Output_Tab.append("No Need for Compilling\n");
                    jDialog_Loading.hide();
                    JOptionPane.showMessageDialog(parentComponent, "No Need for Compilling");
            }
        } else {
            compile_analysis_synthesis cas = new compile_analysis_synthesis(parentComponent, Project_Folder, evt, hdl_compilation_type, jDialog_Loading, jFileChooser1, jTextArea_Output_Tab);
            cas.start();
        }
    }
    
    private boolean check_hardware_change(int hdl_compilation_type) {
        if (hdl_compilation_type == Data.HW_COMPILATION) {
            if (Data.Number_Of_Timers_Compiled != Data.Number_Of_Timers_In_Program) {
                return false;
            }
            if (Data.Number_Of_PWMs_Compiled != Data.Number_Of_PWMs_In_Program) {
                return false;
            }
        }
        
        return true;
    }
}
