/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import rv_fpga_plc_ide.main.RV_FPGA_PLC_IDE;

/**
 *
 * @author hossameldin
 */
public class GeneralFunctions {
    
    public void copy_file(String From_Path, String To_Path) {
        try {
            Files.copy(Paths.get(From_Path), Paths.get(To_Path), StandardCopyOption.REPLACE_EXISTING);
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
    
    public String dec2hex_str(int number_dec, int number_of_digits) {
        return String.format("%0"+number_of_digits+"X", number_dec);
    }
    
    public void write_file(String File_Name_Path, String data) {
        FileOutputStream fileOutSt = null;
        try {
            new File(File_Name_Path).delete();
            fileOutSt = new FileOutputStream(File_Name_Path);
            fileOutSt.write(data.getBytes(), 0, data.length());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                fileOutSt.close();
            } catch (IOException ex) {
                Logger.getLogger(RV_FPGA_PLC_IDE.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int bool2int(boolean bool) {
        if (bool) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public String bool2str(boolean bool) {
        if (bool) {
            return "true";
        } else {
            return "false";
        }
    }
    
    public String convert_il_datatype_to_c_datatype(String IL_DataType) {
        String C_DataType;
        Data.is_fpu_RV64_enabeled = false;
        switch (IL_DataType) {
            case "BOOL":
                C_DataType = "uint8_t";
                break;
            case "SINT" :
                C_DataType = "int8_t";
                break;
            case "INT":
                C_DataType = "int16_t";
                break;
            case "DINT":
                C_DataType = "int32_t";
                break;
            case "LINT":
                C_DataType = "int64_t";
                break;
            case "USINT" :
                C_DataType = "uint8_t";
                break;
            case "UINT":
                C_DataType = "uint16_t";
                break;
            case "UDINT":
                C_DataType = "uint32_t";
                break;
            case "ULINT":
                C_DataType = "uint64_t";
                break;
            case "REAL":
                C_DataType = "float";
                Data.is_fpu_RV64_enabeled = true;
                Data.ALU_Support_In_Program |= Data.MASK_FPU_RV64;
                break;
            case "LREAL":
                Data.is_fpu_RV64_enabeled = true;
                Data.ALU_Support_In_Program |= Data.MASK_FPU_RV64;
                C_DataType = "double";
                break;
            case "TIME":
                C_DataType = "uint64_t";
                break;
            case "TON":
                C_DataType = "Timer";
                break;
            case "TOF":
                C_DataType = "Timer";
                break;
            case "PWM":
                C_DataType = "Timer";
                break;
            default:
                C_DataType = "NotSupported";
                break;
            }
        return C_DataType;
    }
}
