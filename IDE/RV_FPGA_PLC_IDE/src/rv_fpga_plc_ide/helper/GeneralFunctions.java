/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import rv_fpga_plc_ide.src.RV_FPGA_PLC_IDE;

/**
 *
 * @author hossameldin
 */
public class GeneralFunctions {
    
    public void copy_mif_to_q_files(String Project_Folder) {
        try {
            Files.copy(Paths.get(Project_Folder+"/c_files/bootloader.mif"), Paths.get(Project_Folder+"/q_files/bootloader.mif"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove_Spaces_Before_Strings(String[] Variables_temp, int Variables_temp_lenght) {
        for(int i = 0; i < Variables_temp_lenght; i++) {
            Variables_temp[i] = Variables_temp[i].replaceFirst("\\s+", "");
        }
    }
    
    public double getSecFromTimeFormat(String Operand) {
        Operand = Operand.replaceAll("T#", "");
        char[] Operand_c = Operand.toCharArray();
        int i_pointer = 0;
        String number;
        double time_sec = 0.0;
        for (int i = 0; i < Operand_c.length; i++) {
            switch (Operand_c[i]) {
                case 'd':
                    number = "";
                    for (int j = i_pointer; j < i; j++) {
                        number = number + Operand_c[j];
                    }   time_sec = time_sec + (Double.parseDouble(number) * 24 * 60 * 60);
                    i_pointer = i+1;
                    break;
                case 'h':
                    number = "";
                    for (int j = i_pointer; j < i; j++) {
                        number = number + Operand_c[j];
                    }   time_sec = time_sec + (Double.parseDouble(number) * 60 * 60);
                    i_pointer = i+1;
                    break;
                case 'm':
                    if ((i+1) < Operand_c.length) {
                        if (Operand_c[i+1] == 's') {
                            number = "";
                            for (int j = i_pointer; j < i; j++) {
                                number = number + Operand_c[j];
                            }
                            time_sec = time_sec + (Double.parseDouble(number) * 0.001);
                            i_pointer = i+1;
                        } else {
                            number = "";
                            for (int j = i_pointer; j < i; j++) {
                                number = number + Operand_c[j];
                            }
                            time_sec = time_sec + (Double.parseDouble(number) * 60);
                            i_pointer = i+1;
                        }
                    } else {
                        number = "";
                        for (int j = i_pointer; j < i; j++) {
                            number = number + Operand_c[j];
                        }
                        time_sec = time_sec + (Double.parseDouble(number) * 60);
                        i_pointer = i+1;
                    }   break;
                case 's':
                    if (Operand_c[i-1] != 'm') {
                        number = "";
                        for (int j = i_pointer; j < i; j++) {
                            number = number + Operand_c[j];
                        }
                        time_sec = time_sec + Double.parseDouble(number);
                        i_pointer = i+1;
                    }   break;
                default:
                    break;
            }
        }
        return time_sec;
    }
    
    public String insertStringAfter(String After, String stringToBeInserted, String originalString) {
        int index = originalString.indexOf(After) + After.length() - 1;
        // Create a new string 
        String newString = new String(); 
  
        for (int i = 0; i < originalString.length(); i++) { 
  
            // Insert the original string character 
            // into the new string 
            newString += originalString.charAt(i); 
  
            if (i == index) { 
  
                // Insert the string to be inserted 
                // into the new string 
                newString += stringToBeInserted; 
            } 
        } 
  
        // return the modified String 
        return newString;
    }
}