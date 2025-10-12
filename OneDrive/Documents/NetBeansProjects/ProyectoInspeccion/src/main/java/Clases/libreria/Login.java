package Clases.libreria;

import javax.swing.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class Login {

    
    public static void main(String args[]) {
           
        FormLogin objetoLogin = new FormLogin();
        //  Activar FlatLaf ANTES de crear la ventana
        try {
            FlatMacLightLaf.setup(); // Estilo tipo macOS claro
            UIManager.put("Button.arc", 15); // redondear botones globalmente
            UIManager.put("TextComponent.arc", 15);
            UIManager.put("Component.arc", 15); // redondear componentes como JTextField
        } catch (Exception ex) {
            System.err.println("No se pudo aplicar FlatLaf: " + ex.getMessage());
        }

        java.awt.EventQueue.invokeLater(() -> new FormLogin().setVisible(true));
    }
}
