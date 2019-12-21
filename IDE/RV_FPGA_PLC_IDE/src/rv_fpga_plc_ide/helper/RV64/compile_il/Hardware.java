/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.RV64.compile_il;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
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
import rv_fpga_plc_ide.helper.RV64.compile_c.compile_c_file;
import rv_fpga_plc_ide.helper.compile_hld.CompileHLD;
import rv_fpga_plc_ide.helper.private_threads.LoadingDialoge;

/**
 *
 * @author hossameldin
 */
public class Hardware {
    public void compile_hardware(Component parentComponent, String Project_Folder, ActionEvent evt, JLabel JTextLableLoading, JDialog jDialog_Loading, JFileChooser jFileChooser1, JTextArea jTextArea_Output_Tab) {
        /*if (Data.hdl_compilation_state == Data.UPDATED) {
            Data.hdl_compilation_state = Data.ASSEMBLER;
        }
        LoadingDialoge loading = new LoadingDialoge("Compiling ...", JTextLableLoading, jDialog_Loading);
        loading.start();
        jTextArea_Output_Tab.setText("");
        jTextArea_Output_Tab.append("Start Compiling As Hardware.\n");
        boolean success = true;
        File c_files = new File(Project_Folder+"/c_files");
        File q_files = new File(Project_Folder+"/q_files");
        
        c_files.mkdirs();
        q_files.mkdirs();
        
        jTextArea_Output_Tab.append("  Start Compiling \"instruction list\".\n");
        success &= compill_il_file_hw(parentComponent);
        if (success) {
            jTextArea_Output_Tab.append("  Start Compiling \"c files\".\n");
            success &= new compile_c_file().compile_c_to_mif_p(c_files.getPath(), c_files.getPath()+"/"+Data.Project_Name);
        }
        if (success) {
            jTextArea_Output_Tab.append("  Start Writting Hardware Files.\n");
            new Write_Hardware_Files().generate_q_files_variables(Project_Folder);
            jTextArea_Output_Tab.append("  Start Compiling \"Quartus Project\".\n");
            new GeneralFunctions().copy_file(Project_Folder+"/c_files/bootloader.mif", Project_Folder+"/q_files/bootloader.mif");
            new CompileHLD().compile_hdl(parentComponent, Project_Folder, evt, Data.HW_COMPILATION, jDialog_Loading, jFileChooser1, jTextArea_Output_Tab);
        }
        
        if (!success) {
            Icon icon = UIManager.getIcon("OptionPane.errorIcon");
            jDialog_Loading.hide();
            JOptionPane.showMessageDialog(parentComponent, "Not Successful", "Compile As Software", JOptionPane.OK_OPTION, icon);
            jTextArea_Output_Tab.append("Compilling did not Finished Successfully\n");
        }*/
    }
}
