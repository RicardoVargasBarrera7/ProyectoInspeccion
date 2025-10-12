/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.BD;

import Clases.dao.CargoDAO;
import Clases.modelo.Cargo;
import com.mycompany.login.FormLogin;
import com.mycompany.login.FormMenuPrincipal;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CLogin {
    
    public void validaUsuario(JTextField usuario, JPasswordField contrasenia, FormLogin formLogin) {
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            
            CConexion objetoConexion = new CConexion();
            CargoDAO cargoDAO = new CargoDAO();
            
            // ✅ Consulta ajustada a la nueva estructura SQL (todo en minúsculas)
            String consulta = "SELECT id_usuario, nombres, apellidos, id_cargo " +
                              "FROM usuarios " +
                              "WHERE ingreso_usuario = ? AND ingreso_contrasenia = ?";
            
            ps = objetoConexion.estableceConexion().prepareStatement(consulta);
            
            String contra = String.valueOf(contrasenia.getPassword());
            
            ps.setString(1, usuario.getText());
            ps.setString(2, contra);
           
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String idUsuario = rs.getString("id_usuario");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String idCargo = rs.getString("id_cargo");
                
                // ✅ Obtener el nombre del cargo actualizado desde CargoDAO
                Cargo cargo = cargoDAO.buscarPorId(idCargo);
                String nombreCargo = (cargo != null) ? cargo.getNomCargo() : "Sin cargo";
                
                String nombreCompleto = nombres + " " + apellidos;
                
                JOptionPane.showMessageDialog(null, 
                    "¡Bienvenido " + nombreCompleto + "!\nCargo: " + nombreCargo,
                    "Login Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);
                
                // ✅ Abrir menú principal con los datos del usuario
                FormMenuPrincipal objetoMenu = new FormMenuPrincipal(idUsuario, nombreCompleto, nombreCargo);
                objetoMenu.setVisible(true);
                
                // Cerrar formulario de login
                formLogin.dispose();
                
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Usuario o contraseña incorrectos.\nIntente nuevamente.",
                    "Error de Login",
                    JOptionPane.ERROR_MESSAGE);
                
                // Limpiar campos
                usuario.setText("");
                contrasenia.setText("");
                usuario.requestFocus();
            }
            
            // Cerrar conexiones
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error al conectar con la base de datos:\n" + e.toString(),
                "Error de Conexión",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
