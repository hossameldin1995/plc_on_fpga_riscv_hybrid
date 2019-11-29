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
import javax.swing.UIManager;
import rv_fpga_plc_ide.helper.Output_Tap;
import rv_fpga_plc_ide.helper.ProjectManagement;
import rv_fpga_plc_ide.helper.RV32.compile_c_file;

/**
 *
 * @author hossameldin
 */
public class download_to_SoC_thread extends Thread {
        private final String Project_Folder;
        private final JDialog jDialog_Loading;
        private final Component parentComponent;
        private final JFileChooser jFileChooser1;
        
        public download_to_SoC_thread(Component parentComponent, String Project_Folder, JDialog jDialog_Loading, JFileChooser jFileChooser1) {
            this.Project_Folder = Project_Folder;
            this.jDialog_Loading = jDialog_Loading;
            this.parentComponent = parentComponent;
            this.jFileChooser1 = jFileChooser1;
        }
        
        @Override
        public void run() {
            boolean success = new compile_c_file().compile_download_to_soc_p(Project_Folder);
            if (success) {
                jDialog_Loading.hide();
                new Output_Tap().println("Downloading Finished Successfully");
                JOptionPane.showMessageDialog(parentComponent, "Downloading Finished Successfully");
            } else {
                jDialog_Loading.hide();
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Downloading did not Finished Successfully.", "Downloading to SoC", JOptionPane.OK_OPTION, icon);
                new Output_Tap().println("Downloading did not Finished Successfully");
            }
            new ProjectManagement().saveProject(parentComponent, jFileChooser1);
        }
    }
