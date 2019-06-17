/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hossameldin
 */
public class printOutput extends Thread{
    InputStream is = null;
    String before;
    
    public printOutput(InputStream is, String before) {
        this.is = is;
        this.before = before;
    }
    
    @Override
    public void run() {
        try {
            String s;
            
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((s = br.readLine()) != null) {
                new Output_Tap().println(before+s);
            }
        } catch (IOException ex) {
            Logger.getLogger(printOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
