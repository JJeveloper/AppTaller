
package com.mycompany.view;

import javax.swing.JOptionPane;

/**
 *
 * @author JJAB
 */
public class Mensajes {
    
    public static void  mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.ERROR_MESSAGE);
    }

    
    public static void mensajeOK(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.WARNING_MESSAGE);
    }
    
}
