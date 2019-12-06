/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JTextArea;

/**
 *
 * @author hossameldin
 */
public class execute_command {
    public int execute_command(String cmd, String before, int type, JTextArea jTextArea_Output_Tab) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        int exitCode = -777;
        
        //System.out.println(before+cmd);
        
        // Windows
        //processBuilder.command("cmd.exe", "/c", cmd);
        // Linux
        processBuilder.command("bash", "-c", cmd);

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            switch (type) {
                case Data.COMMAND_WINDOW:
                    while ((line = reader.readLine()) != null) {
                        System.out.println(before+line);
                    }
                    break;
                case Data.OUTPUT_TAP_WINDOW:
                    while ((line = reader.readLine()) != null) {
                        jTextArea_Output_Tab.append(before+line);
                    }
                    break;
                case Data.COMMAND_OUTPUT_TAP_WINDOW:
                    while ((line = reader.readLine()) != null) {
                        System.out.println(before+line);
                        jTextArea_Output_Tab.append(before+line);
                    }
                    break;
            }
            

            //exitCode = process.waitFor();
            //System.out.println(before+exitCode);

        } catch (IOException | InterruptedException e) {
        }
        return exitCode;
    }
}
