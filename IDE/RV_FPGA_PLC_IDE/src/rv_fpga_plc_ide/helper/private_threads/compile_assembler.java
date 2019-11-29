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
import rv_fpga_plc_ide.helper.Data;
import rv_fpga_plc_ide.helper.Output_Tap;
import rv_fpga_plc_ide.helper.ProjectManagement;
import rv_fpga_plc_ide.helper.execute_command;
import rv_fpga_plc_ide.src.RV_FPGA_PLC_IDE;

/**
 *
 * @author hossameldin
 */
public class compile_assembler extends Thread {
        private final String Project_Folder;
        private final java.awt.event.ActionEvent evt;
        private final int hdl_compilation_type;
        private final JDialog jDialog_Loading;
        private final Component parentComponent;
        private final JFileChooser jFileChooser1;
        
        public compile_assembler(Component parentComponent, String Project_Folder, java.awt.event.ActionEvent evt, int hdl_compilation_type, JDialog jDialog_Loading, JFileChooser jFileChooser1) {
            this.Project_Folder = Project_Folder;
            this.evt = evt;
            this.hdl_compilation_type = hdl_compilation_type;
            this.jDialog_Loading = jDialog_Loading;
            this.parentComponent = parentComponent;
            this.jFileChooser1 = jFileChooser1;
        }
        
        @Override
        public void run() {
            new Output_Tap().println("      Starting Assembler");
            String cmd = "/home/hossameldin/intelFPGA_lite/18.0/quartus/bin/quartus_asm --read_settings_files=on --write_settings_files=off "+Project_Folder+"RV_FPGA_PLC_Potato -c "+Project_Folder+"RV_FPGA_PLC_Potato";
            int exitValue = new execute_command().execute_command(cmd, "        ", Data.out_window);
            Data.hdl_compilation_type = hdl_compilation_type;
            Data.Number_Of_Timers_Compiled = Data.Number_Of_Timers_In_Program;
            Data.Number_Of_PWMs_Compiled = Data.Number_Of_PWMs_In_Program;
            Data.compiled_core = Data.core;
            if (exitValue == 0) {
                Data.hdl_compilation_state = Data.UPDATED;
                jDialog_Loading.hide();
                new Output_Tap().println("  Compiling Finished Successfully");
                JOptionPane.showMessageDialog(parentComponent, "Compiling Finished Successfully");
                if (Data.RequistDownload) {
                    Data.RequistDownload = false;
                    new RV_FPGA_PLC_IDE().Download_Prog_to_SoC(evt);
                }
            } else {
                jDialog_Loading.hide();
                Data.hdl_compilation_state = Data.FITTER;
                Icon icon = UIManager.getIcon("OptionPane.errorIcon");
                JOptionPane.showMessageDialog(parentComponent, "Compiling did not Finished Successfully", "Compile As Software", JOptionPane.OK_OPTION, icon);
                new Output_Tap().println("  Compiling did not Finished Successfully");
                if (Data.RequistDownload) {
                    Data.RequistDownload = false;
                    JOptionPane.showMessageDialog(parentComponent, "Downloading did not Finished Successfully.", "Downloading to SoC", JOptionPane.OK_OPTION, icon);
                }
            }
            new ProjectManagement().saveProject(parentComponent, jFileChooser1);
        }
    }
