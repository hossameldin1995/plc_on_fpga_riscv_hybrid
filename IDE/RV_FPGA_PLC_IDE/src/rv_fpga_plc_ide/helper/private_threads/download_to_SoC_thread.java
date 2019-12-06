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
import rv_fpga_plc_ide.helper.ProjectManagement;
import rv_fpga_plc_ide.helper.RV32.compile_c.compile_c_file;

/**
 *
 * @author hossameldin
 */
public class download_to_SoC_thread extends Thread {
        private final String Project_Folder;
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
            boolean success = new compile_c_file().compile_download_to_soc_p(Project_Folder);
            if (success) {
                jDialog_Loading.hide();
                jTextArea_Output_Tab.append("Downloading Finished Successfully");
                JOptionPane.showMessageDialog(parentComponent, "Downloading Finished Successfully");
            } else {
                jDialog_Loading.hide();
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Downloading did not Finished Successfully.", "Downloading to SoC", JOptionPane.OK_OPTION, icon);
                jTextArea_Output_Tab.append("Downloading did not Finished Successfully\n");
            }
            new ProjectManagement().saveProject(parentComponent, jFileChooser1);
        }
    }
