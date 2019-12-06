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
public class compile_update_mif extends Thread {
        private final String Project_Folder;
        private final java.awt.event.ActionEvent evt;
        private final int hdl_compilation_type;
        private final JDialog jDialog_Loading;
        private final Component parentComponent;
        private final JFileChooser jFileChooser1;
        private final JTextArea jTextArea_Output_Tab;
        
        public compile_update_mif(Component parentComponent, String Project_Folder, java.awt.event.ActionEvent evt, int hdl_compilation_type, JDialog jDialog_Loading, JFileChooser jFileChooser1, JTextArea jTextArea_Output_Tab) {
            this.Project_Folder = Project_Folder;
            this.evt = evt;
            this.hdl_compilation_type = hdl_compilation_type;
            this.jDialog_Loading = jDialog_Loading;
            this.parentComponent = parentComponent;
            this.jFileChooser1 = jFileChooser1;
            this.jTextArea_Output_Tab = jTextArea_Output_Tab;
        }

        @Override
        public void run() {
            jTextArea_Output_Tab.append("      Starting Update mif\n");
            String cmd = "/home/hossameldin/intelFPGA_lite/18.0/quartus/bin/quartus_cdb "+Project_Folder+"RV_FPGA_PLC_Potato -c "+Project_Folder+"RV_FPGA_PLC_Potato --update_mif";
            int exitValue = new execute_command().execute_command(cmd, "        ", Data.deafult_out_window, jTextArea_Output_Tab);
            if (exitValue == 0) {
                compile_assembler ca = new compile_assembler(parentComponent, Project_Folder, evt, hdl_compilation_type, jDialog_Loading, jFileChooser1, jTextArea_Output_Tab);
                ca.start();
            } else {
                Data.hdl_compilation_state = Data.NO_COMPILATION;
                jDialog_Loading.hide();
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Updating mif Not Successful", "Compile As Software", JOptionPane.OK_OPTION, icon);
                jTextArea_Output_Tab.append("  Compiling did not Finished Successfully\n");
            }
            new ProjectManagement().saveProject(parentComponent, jFileChooser1);
        }
    }
