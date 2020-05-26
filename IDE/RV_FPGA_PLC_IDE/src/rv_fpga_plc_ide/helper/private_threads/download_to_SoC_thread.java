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
public class download_to_SoC_thread extends Thread {
        private String Project_Folder;
        private final JDialog jDialog_Loading;
        private final Component parentComponent;
        private final JFileChooser jFileChooser1;
        private final JTextArea jTextArea_Output_Tab;
        
        public download_to_SoC_thread(Component parentComponent, String Project_Folder, JDialog jDialog_Loading, JFileChooser jFileChooser1, JTextArea jTextArea_Output_Tab) {
            this.Project_Folder = Project_Folder;
            this.jDialog_Loading = jDialog_Loading;
            this.parentComponent = parentComponent;
            this.jFileChooser1 = jFileChooser1;
            this.jTextArea_Output_Tab = jTextArea_Output_Tab;
        }
        
        @Override
        public void run() {
            boolean success;
            //success = new compile_c_file().compile_download_to_soc_p(Project_Folder);
            String Project_Name;
            if (Data.core == Data.RV32) {
                Project_Name = "RV_FPGA_PLC_Potato";
                if (Data.Compiling_Type == Data.SW_COMPILING) {
                    Project_Folder = Project_Folder + "/q_files_RV32_SW";
                } else if (Data.Compiling_Type == Data.HW_COMPILING) {
                    Project_Folder = Project_Folder + "/q_files_RV32_HW";
                }
            } else {
                Project_Name = "River_SoC";
                if (Data.Compiling_Type == Data.SW_COMPILING) {
                    Project_Folder = Project_Folder + "/q_files_RV64_SW";
                } else if (Data.Compiling_Type == Data.HW_COMPILING) {
                    Project_Folder = Project_Folder + "/q_files_RV64_HW";
                }
            }
            String cmd = "/home/hossameldin/intelFPGA_lite/18.0/quartus/bin/quartus_pgm -m jtag -c 1 -o \"p;"+Project_Folder+"/output_files/"+Project_Name+".sof\"";
            int exitValue = new execute_command().execute_command(cmd, "        ", Data.deafult_out_window, jTextArea_Output_Tab);
            success = (exitValue == 0);
            if (success) {
                jDialog_Loading.setVisible(false);
                jTextArea_Output_Tab.append("Downloading Finished Successfully");
                JOptionPane.showMessageDialog(parentComponent, "Downloading Finished Successfully");
            } else {
                jDialog_Loading.setVisible(false);
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Downloading did not Finished Successfully.", "Downloading to SoC", JOptionPane.OK_OPTION, icon);
                jTextArea_Output_Tab.append("Downloading did not Finished Successfully\n");
            }
            new ProjectManagement().saveProject(parentComponent, jFileChooser1);
        }
    }
