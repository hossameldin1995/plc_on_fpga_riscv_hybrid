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
import java.util.Date;
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
    
    public String convert_il_datatype_to_c_datatype(String IL_DataType, int[] Register_Type) {
        String C_DataType;
        Data.is_fpu_RV64_enabeled = false;
        switch (IL_DataType) {
            case "BOOL":
                C_DataType = "uint8_t";
                Register_Type[0] = Data.BOOL;
                break;
            case "SINT" :
                C_DataType = "int8_t";
                Register_Type[0] = Data.INT8_T;
                break;
            case "INT":
                C_DataType = "int16_t";
                Register_Type[0] = Data.INT16_T;
                break;
            case "DINT":
                C_DataType = "int32_t";
                Register_Type[0] = Data.INT32_T;
                break;
            case "LINT":
                C_DataType = "int64_t";
                Register_Type[0] = Data.INT64_T;
                break;
            case "USINT" :
                C_DataType = "uint8_t";
                Register_Type[0] = Data.UINT8_T;
                break;
            case "UINT":
                C_DataType = "uint16_t";
                Register_Type[0] = Data.UINT16_T;
                break;
            case "UDINT":
                C_DataType = "uint32_t";
                Register_Type[0] = Data.UINT32_T;
                break;
            case "ULINT":
                C_DataType = "uint64_t";
                Register_Type[0] = Data.UINT64_T;
                break;
            case "REAL":
                C_DataType = "float";
                Register_Type[0] = Data.FLOAT;
                Data.is_fpu_RV64_enabeled = true;
                Data.ALU_Support_In_Program_RV64_SW |= Data.MASK_FPU_RV64;
                Data.ALU_Support_In_Program_RV64_HW |= Data.MASK_FPU_RV64;
                break;
            case "LREAL":
                Data.is_fpu_RV64_enabeled = true;
                Data.ALU_Support_In_Program_RV64_SW |= Data.MASK_FPU_RV64;
                Data.ALU_Support_In_Program_RV64_HW |= Data.MASK_FPU_RV64;
                C_DataType = "double";
                Register_Type[0] = Data.DOUBLE;
                break;
            case "TIME":
                C_DataType = "uint64_t";
                Register_Type[0] = Data.TIME;
                break;
            case "TON":
                C_DataType = "Timer";
                Register_Type[0] = Data.TIMER;
                break;
            case "TOF":
                C_DataType = "Timer";
                Register_Type[0] = Data.TIMER;
                break;
            case "PWM":
                C_DataType = "Timer";
                Register_Type[0] = Data.TIMER;
                break;
            default:
                C_DataType = "NotSupported";
                Register_Type[0] = Data.NAN;
                break;
            }
        return C_DataType;
    }
    
    public boolean is_contain_str_arr(String str, String[] arr) {
        for (String arr1 : arr) {
            if (str.equals(arr1)) {
                return true;
            }
        }
        return false;
    }

    public void add_conversion_type_c_command(String From_Type, String To_Type) {
        int[] From_Register_Type = new int[1];
        int[] To_Register_Type = new int[1];
        String From_Type_c = convert_il_datatype_to_c_datatype(From_Type, From_Register_Type);
        String To_Type_c = convert_il_datatype_to_c_datatype(To_Type, To_Register_Type);
        
        if (Data.Load_index_is_defined[Data.Load_index]) {
            if (Data.Current_Register_Type[Data.Load_index] == To_Register_Type[0]) {
                Data.C_code += "\t\tvar" + Data.Load_index + " = ("+ To_Type_c +") var" + Data.Load_index + ";\n";
            } else {
                Data.Current_Register_Count++;
                Data.Load_index++;
                Data.C_code += "\t\t"+To_Type_c+" var" + Data.Load_index + " = ("+ To_Type_c +") var" + (Data.Load_index-1) + ";\n";
                Data.Load_index_is_defined[Data.Load_index] = true;
                Data.Current_Register_Type[Data.Load_index] = To_Register_Type[0];
            }
        }
    }
    
    public void calculate_defference_time(String[] res) {
        Data.EndTime = new Date();
        long deff = Data.EndTime.getTime() - Data.StartTime.getTime();
        long deff_h, deff_m, deff_s;
        deff_s = deff/1000;
        deff_m = deff/60000;
        deff_h = deff/3600000;
        
        if (deff_s < 10) res[0] = "0"+deff_s; 
        else res[0] = ""+deff_s;
        
        if (deff_m < 10) res[1] = "0"+deff_m; 
        else res[1] = ""+deff_m;
        
        if (deff_h < 10) res[2] = "0"+deff_h; 
        else res[2] = ""+deff_h;
    }
    
    public void initialize_int_with_value(int[] arr, int val) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = val;
        }
    }

    public int getRegesterTypeFromStringType(String typeOfVariable) {
        int[] RegesterType = new int[1];
        convert_il_datatype_to_c_datatype(typeOfVariable, RegesterType);
        return RegesterType[0];
    }
}
