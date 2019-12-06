/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.RV32.compile_c;

/**
 *
 * @author hossameldin
 */
public class compile_c_file {
    static {
        System.load("/home/hossameldin/Documents/RV_FPGA_PLC_Project/Work/IDE/RV_FPGA_PLC_IDE/src/compile_c_file.so");
    }

    private native boolean compile_c_to_mif(String c_Folder_Path, String c_File_Path);
    
    private native boolean compile_download_to_soc(String c_Folder_Path);
    
    public boolean compile_c_to_mif_p(String c_Folder_Path, String c_File_Path) {
        return compile_c_to_mif(c_Folder_Path, c_File_Path);
    }
    
    public boolean compile_download_to_soc_p(String c_Folder_Path) {
        return compile_download_to_soc(c_Folder_Path);
    }
    
}
