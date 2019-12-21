/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.RV64.compile_c;

import javax.swing.JTextArea;
import rv_fpga_plc_ide.helper.Data;
import rv_fpga_plc_ide.helper.execute_command;

/**
 *
 * @author hossameldin
 */
public class compile_c_file {
    public boolean compile_hex2mif(JTextArea jTextArea_Output_Tab, int hdl_compilation_type) {
        String cmd = "ERROR CMD HW or SW in RV64";
        if (hdl_compilation_type == Data.SW_COMPILATION) {
            cmd =    "make -f "+Data.Project_Folder.getPath()+"/c_files_RV64_SW/hex2mif/makefile";
        } else if (hdl_compilation_type == Data.HW_COMPILATION) {
            cmd =    "make -f "+Data.Project_Folder.getPath()+"/c_files_RV64_HW/hex2mif/makefile";
        }
        
        int ret_val = new execute_command().execute_command(cmd, "    ", Data.deafult_out_window, jTextArea_Output_Tab);
        
        return ret_val == 0;
    }
    
    public boolean compile_elf2rawx(JTextArea jTextArea_Output_Tab, int hdl_compilation_type) {
        String cmd = "ERROR CMD HW or SW in RV64";
        if (hdl_compilation_type == Data.SW_COMPILATION) {
            cmd =    "make -f "+Data.Project_Folder.getPath()+"/c_files_RV64_SW/elf2rawx/makefiles/makefile";
        } else if (hdl_compilation_type == Data.HW_COMPILATION) {
            cmd =    "make -f "+Data.Project_Folder.getPath()+"/c_files_RV64_HW/elf2rawx/makefiles/makefile";
        }
        
        int ret_val = new execute_command().execute_command(cmd, "    ", Data.deafult_out_window, jTextArea_Output_Tab);
        
        return ret_val == 0;
    }
    
    public boolean compile_application(JTextArea jTextArea_Output_Tab, int hdl_compilation_type) {
        String cmd = "ERROR CMD HW or SW in RV64";
        if (hdl_compilation_type == Data.SW_COMPILATION) {
            cmd =    "make -f "+Data.Project_Folder.getPath()+"/c_files_RV64_SW/"+Data.Project_Name+"_application/makefiles/makefile " +
                        "PROJECT_FOLDER="+Data.Project_Folder.getPath()+"/c_files_RV64_SW/";
        } else if (hdl_compilation_type == Data.HW_COMPILATION) {
            cmd =    "make -f "+Data.Project_Folder.getPath()+"/c_files_RV64_HW/"+Data.Project_Name+"_application/makefiles/makefile " +
                        "PROJECT_FOLDER="+Data.Project_Folder.getPath()+"/c_files_RV64_HW/";
        }
        
        int ret_val = new execute_command().execute_command(cmd, "    ", Data.deafult_out_window, jTextArea_Output_Tab);
        
        return ret_val == 0;
    }
    
    public boolean compile_boot(JTextArea jTextArea_Output_Tab, int hdl_compilation_type) {
        String cmd = "ERROR CMD HW or SW in RV64";
        if (hdl_compilation_type == Data.SW_COMPILATION) {
            cmd =    "make -f "+Data.Project_Folder.getPath()+"/c_files_RV64_SW/boot/makefiles/makefile " +
                        "PROJECT_FOLDER="+Data.Project_Folder.getPath()+"/c_files_RV64_SW/";
        } else if (hdl_compilation_type == Data.HW_COMPILATION) {
            cmd =    "make -f "+Data.Project_Folder.getPath()+"/c_files_RV64_HW/boot/makefiles/makefile " +
                        "PROJECT_FOLDER="+Data.Project_Folder.getPath()+"/c_files_RV64_HW/";
        }
        
        int ret_val = new execute_command().execute_command(cmd, "    ", Data.deafult_out_window, jTextArea_Output_Tab);
        
        return ret_val == 0;
    }
}
