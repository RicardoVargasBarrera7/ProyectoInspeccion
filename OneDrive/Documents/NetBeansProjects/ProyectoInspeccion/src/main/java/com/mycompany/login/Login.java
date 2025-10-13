/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login;


import Clases.libreria.FormLogin;
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
