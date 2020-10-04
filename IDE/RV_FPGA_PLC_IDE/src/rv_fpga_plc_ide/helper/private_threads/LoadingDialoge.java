/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rv_fpga_plc_ide.helper.private_threads;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author hossameldin
 */
public class LoadingDialoge extends Thread {
        private final String loading_text;
        private final JLabel JTextLableLoading;
        private final JDialog jDialog_Loading;
        
        public LoadingDialoge(String loading_text, JLabel JTextLableLoading, JDialog jDialog_Loading) {
            this.loading_text = loading_text;
            this.JTextLableLoading = JTextLableLoading;
            this.jDialog_Loading = jDialog_Loading;
        }
        
        @Override
        public void run() {
            JTextLableLoading.setText(loading_text);
            jDialog_Loading.setVisible(true);
            System.out.println("***************************************");
            System.out.println("* Loading Started *********************");
            System.out.println("***************************************");
        }
    }
