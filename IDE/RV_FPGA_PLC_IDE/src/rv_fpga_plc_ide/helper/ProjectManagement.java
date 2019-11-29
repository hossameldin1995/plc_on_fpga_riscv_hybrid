/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import rv_fpga_plc_ide.src.RV_FPGA_PLC_IDE;

/**
 *
 * @author hossameldin
 */
public class ProjectManagement {
    
    public boolean saveProject(Component parentComponent, JFileChooser jFileChooser1) {
        if(Data.is_New_Project) {
            return saveProject_As(parentComponent, jFileChooser1);
        } else {
            write_info_file(Data.Project_Folder.getPath());
            write_il_file(Data.Project_Folder.getPath());
            Data.is_New_Project = false;
            Data.is_Saved_Project = true;
            new RV_FPGA_PLC_IDE().setTitle("RV FPGA PLC IDE - " + Data.Project_Name);
            return true;
        }
    }
    
    public boolean saveProject_As(Component parentComponent, JFileChooser jFileChooser1) {
        jFileChooser1.setDialogTitle("Choose directory for new project");
        jFileChooser1.setCurrentDirectory(new File("/home/hossameldin/Documents"));
        jFileChooser1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser1.setAcceptAllFileFilterUsed(false);
        if (jFileChooser1.showOpenDialog(parentComponent) == JFileChooser.APPROVE_OPTION) {
            Data.Project_Folder = new File(jFileChooser1.getSelectedFile().getPath()+"/"+Data.Project_Name);
            Data.Project_Folder.mkdirs();
            write_info_file(Data.Project_Folder.getPath());
            write_il_file(Data.Project_Folder.getPath());
            Data.is_New_Project = false;
            Data.is_Saved_Project = true;
            new RV_FPGA_PLC_IDE().setTitle("RV FPGA PLC IDE - " + Data.Project_Name);
            return true;
        }
        return false;
    }
    
    private void write_info_file(String Project_Folder) {
        FileOutputStream project_info = null;
        try {
            new File(Project_Folder+"/"+Data.Project_Name+".rfpinfo").delete();
            project_info = new FileOutputStream(Project_Folder+"/"+Data.Project_Name+".rfpinfo");
            String Size_of_Programs = "";
            for (int i = 0; i < Data.size_Rung; i++) {
                Size_of_Programs = Size_of_Programs + "    Rung ("+(i+1)+") Size      = "+Data.size_Program_in_rung[i]+"\n";
            }
            String data = "Project Name           = "+Data.Project_Name+"\n"+
                          "Size of Program        = "+Data.size_Program+"\n"+
                          "Max Size of Program    = "+Data.max_size_program_in_rung+"\n"+
                          "Number of Rungs        = "+Data.size_Rung+"\n"+
                          Size_of_Programs+
                          "Number of Variables    = "+(Data.size_Vaiables - 2)+"\n"+
                          "Core                   = "+Data.core+"\n"+
                          "HDL Compilation State  = "+Data.hdl_compilation_state+"\n"+
                          "HDL Compilation Type   = "+Data.hdl_compilation_type+"\n"+
                          "Compiled Timers        = "+Data.Number_Of_Timers_Compiled+"\n"+
                          "Timers in Program      = "+Data.Number_Of_Timers_In_Program+"\n"+
                          "Compiled PWMs          = "+Data.Number_Of_PWMs_Compiled+"\n"+
                          "PWMs in Program        = "+Data.Number_Of_PWMs_In_Program;
            project_info.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                project_info.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void write_il_file(String Project_Folder) {
        FileOutputStream project_il = null;
        try {
            new File(Project_Folder+"/"+Data.Project_Name+".il").delete();
            project_il = new FileOutputStream(Project_Folder+"/"+Data.Project_Name+".il");
            String data = "";
            for (int i = 0; i < Data.size_Vaiables; i++) {
                data = data + Data.Vaiables[i]+"\n";
            }
            data = data + "\n";
            for (int i = 0; i < Data.size_Program; i++) {
                data = data + Data.Program_1D[i]+"\n";
            }
            project_il.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                project_il.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void read_info_file(String Project_Folder) {
        BufferedReader info_file = null;
        try {
            info_file = new BufferedReader(new FileReader(Project_Folder+"/"+Data.Project_Name+".rfpinfo"));
            info_file.readLine(); // Project Name
            Data.size_Program = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            Data.max_size_program_in_rung = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            Data.size_Rung = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            Data.size_Program_in_rung = new int[Data.size_Rung];
            for(int i = 0; i < Data.size_Rung ; i++) {
                Data.size_Program_in_rung[i] = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            }
            Data.size_Vaiables = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1])+2;
            Data.core = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            Data.hdl_compilation_state = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            Data.hdl_compilation_type = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            Data.Number_Of_Timers_Compiled = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            Data.Number_Of_Timers_In_Program = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            Data.Number_Of_PWMs_Compiled = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            Data.Number_Of_PWMs_In_Program = Integer.parseInt(info_file.readLine().replaceAll(" ", "").split("=")[1]);
            File q_files = new File(Project_Folder+"/q_files");
            if (!q_files.exists()) {
                Data.hdl_compilation_state = Data.NO_COMPILATION;
                Data.hdl_compilation_type = Data.NO_COMPILATION;
                write_info_file(Project_Folder);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                info_file.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
