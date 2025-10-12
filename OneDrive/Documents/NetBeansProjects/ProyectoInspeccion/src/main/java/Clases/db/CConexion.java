/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.db;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CConexion {
    Connection conectar;
    
    // Datos de conexión Oracle XE
    String usuario = "proyectofitosanitario";        // tu usuario en Oracle
    String contrasenia = "col2025";  // tu contraseña
    String bd = "XE";              // SID por defecto de Oracle XE
    String ip = "localhost";       
    String puerto = "1521";        // puerto Oracle
    
    // Cadena de conexión para Oracle
    String cadena = "jdbc:oracle:thin:@" + ip + ":" + puerto + ":" + bd;
    
    public Connection estableceConexion() {
        try {
            // Cargar el driver de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Establecer la conexión
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            //JOptionPane.showMessageDialog(null, "Conectado a Oracle XE correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión: " + e.toString());
        }
        return conectar;
    }
}
