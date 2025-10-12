/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.dao;

import Clases.BD.CConexion;
import Clases.modelo.Usuarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    private CConexion conexion;
    
    public UsuarioDAO() {
        this.conexion = new CConexion();
    }
    
    // CREATE - Insertar usuario
    public boolean insertar(Usuarios usuario) {
        try {
            String sql = "INSERT INTO Usuarios VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            
            ps.setString(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getNumeroIdentificacion());
            ps.setString(3, usuario.getNombres());
            ps.setString(4, usuario.getApellidos());
            ps.setString(5, usuario.getDireccion());
            ps.setString(6, usuario.getTelefono());
            ps.setString(7, usuario.getCorreoElectronico());
            ps.setString(8, usuario.getIngresoUsuario());
            ps.setString(9, usuario.getIngresoContrasenia());
            ps.setString(10, usuario.getIdCargo());
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }
    
    // READ - Listar todos los usuarios
    public List<Usuarios> listarTodos() {
        List<Usuarios> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Usuarios ORDER BY idUsuario";
            Statement st = conexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setIdUsuario(rs.getString("idUsuario"));
                u.setNumeroIdentificacion(rs.getString("numeroIdentificacion"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setDireccion(rs.getString("direccion"));
                u.setTelefono(rs.getString("telefono"));
                u.setCorreoElectronico(rs.getString("correoElectronico"));
                u.setIngresoUsuario(rs.getString("ingresoUsuario"));
                u.setIngresoContrasenia(rs.getString("ingresoContrasenia"));
                u.setIdCargo(rs.getString("idCargo"));
                lista.add(u);
            }
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }
    
    // READ - Listar por cargo
    public List<Usuarios> listarPorCargo(String idCargo) {
        List<Usuarios> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Usuarios WHERE idCargo = ? ORDER BY nombres";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, idCargo);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setIdUsuario(rs.getString("idUsuario"));
                u.setNumeroIdentificacion(rs.getString("numeroIdentificacion"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setDireccion(rs.getString("direccion"));
                u.setTelefono(rs.getString("telefono"));
                u.setCorreoElectronico(rs.getString("correoElectronico"));
                u.setIngresoUsuario(rs.getString("ingresoUsuario"));
                u.setIdCargo(rs.getString("idCargo"));
                lista.add(u);
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar por cargo: " + e.getMessage());
        }
        return lista;
    }
    
    // READ - Buscar por ID
    public Usuarios buscarPorId(String id) {
        Usuarios usuario = null;
        try {
            String sql = "SELECT * FROM Usuarios WHERE idUsuario = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                usuario = new Usuarios();
                usuario.setIdUsuario(rs.getString("idUsuario"));
                usuario.setNumeroIdentificacion(rs.getString("numeroIdentificacion"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreoElectronico(rs.getString("correoElectronico"));
                usuario.setIngresoUsuario(rs.getString("ingresoUsuario"));
                usuario.setIdCargo(rs.getString("idCargo"));
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar usuario: " + e.getMessage());
        }
        return usuario;
    }
    
    // UPDATE - Actualizar usuario
    public boolean actualizar(Usuarios usuario) {
        try {
            String sql = "UPDATE Usuarios SET numeroIdentificacion=?, nombres=?, apellidos=?, " +
                        "direccion=?, telefono=?, correoElectronico=?, ingresoUsuario=?, " +
                        "idCargo=? WHERE idUsuario=?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            
            ps.setString(1, usuario.getNumeroIdentificacion());
            ps.setString(2, usuario.getNombres());
            ps.setString(3, usuario.getApellidos());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getCorreoElectronico());
            ps.setString(7, usuario.getIngresoUsuario());
            ps.setString(8, usuario.getIdCargo());
            ps.setString(9, usuario.getIdUsuario());
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE - Eliminar usuario
    public boolean eliminar(String id) {
        try {
            String sql = "DELETE FROM Usuarios WHERE idUsuario = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, id);
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
    
    // Generar siguiente ID
    public String generarSiguienteId() {
        try {
            String sql = "SELECT MAX(TO_NUMBER(SUBSTR(idUsuario,2))) FROM Usuarios";
            Statement st = conexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()) {
                int ultimoNumero = rs.getInt(1);
                return "U" + String.format("%03d", ultimoNumero + 1);
            }
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar ID: " + e.getMessage());
        }
        return "U001";
    }
}