/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper;

/**
 *
 * @author hossameldin
 */
public class Output_Tap {

    public void println(String line_s) {
        Data.jTextArea_Output_Tab.append(line_s+"\n");
    }
    
    public void removeText() {
        Data.jTextArea_Output_Tab.setText("");
    }
    
}
