/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.private_threads;

import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import rv_fpga_plc_ide.helper.Data;
import rv_fpga_plc_ide.helper.Output_Tap;
import rv_fpga_plc_ide.helper.ProjectManagement;
import rv_fpga_plc_ide.helper.printOutput;
import rv_fpga_plc_ide.src.RV_FPGA_PLC_IDE;

/**
 *
 * @author hossameldin
 */
public class compile_analysis_synthesis extends Thread {
        private final String Project_Folder;
        private final java.awt.event.ActionEvent evt;
        private final int hdl_compilation_type;
        private final JDialog jDialog_Loading;
        private final Component parentComponent;
        private final JFileChooser jFileChooser1;
        
        public compile_analysis_synthesis(Component parentComponent, String Project_Folder, java.awt.event.ActionEvent evt, int hdl_compilation_type, JDialog jDialog_Loading, JFileChooser jFileChooser1) {
            this.Project_Folder = Project_Folder;
            this.evt = evt;
            this.hdl_compilation_type = hdl_compilation_type;
            this.jDialog_Loading = jDialog_Loading;
            this.parentComponent = parentComponent;
            this.jFileChooser1 = jFileChooser1;
        }
        
        @Override
        public void run() {
            new Output_Tap().println("      Starting Analysis snd Synthesis");
            Runtime rt = Runtime.getRuntime();
            printOutput errorReported, outputMessage;
            try {
                String cmd = "/home/hossameldin/intelFPGA_lite/18.0/quartus/bin/quartus_map --read_settings_files=on --write_settings_files=off "+Project_Folder+"RV_FPGA_PLC_Potato -c "+Project_Folder+"RV_FPGA_PLC_Potato";
                Process proc = rt.exec(cmd);
                errorReported = new printOutput(proc.getErrorStream(), "        ");
                outputMessage = new printOutput(proc.getInputStream(), "        ");
                errorReported.start();
                outputMessage.start();
                proc.waitFor();
                if (proc.exitValue() == 0) {
                    compile_fitter cf = new compile_fitter(parentComponent, Project_Folder, evt, hdl_compilation_type, jDialog_Loading, jFileChooser1);
                    Data.hdl_compilation_state = Data.ANALYSIS_SYNTHESIS;
                    Data.hdl_compilation_type = hdl_compilation_type;
                    Data.Number_Of_Timers_Compiled = Data.Number_Of_Timers_In_Program;
                    Data.Number_Of_PWMs_Compiled = Data.Number_Of_PWMs_In_Program;
                    cf.start();
                } else {
                    jDialog_Loading.hide();
                    Data.hdl_compilation_state = Data.NO_COMPILATION;
                    Data.hdl_compilation_type = Data.NO_COMPILATION;
                    Data.Number_Of_Timers_Compiled = 0;
                    Data.Number_Of_PWMs_Compiled = 0;
                    Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                    JOptionPane.showMessageDialog(parentComponent, "Analysis Synthesis Not Successful", "Compile As Software", JOptionPane.OK_OPTION, icon);
                }
                new ProjectManagement().saveProject(parentComponent, jFileChooser1);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
