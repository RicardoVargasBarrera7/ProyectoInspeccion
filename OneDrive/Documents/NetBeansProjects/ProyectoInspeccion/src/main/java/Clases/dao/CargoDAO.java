/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.dao;

import Clases.db.CConexion;
import Clases.modelo.Cargo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CargoDAO {
    
    private CConexion conexion;
    
    public CargoDAO() {
        this.conexion = new CConexion();
    }
    
    // CREATE - Insertar cargo
    public boolean insertar(Cargo cargo) {
        try {
            String sql = "INSERT INTO cargo (id_cargo, nom_cargo, descripcion) VALUES (?, ?, ?)";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            
            ps.setString(1, cargo.getIdCargo());
            ps.setString(2, cargo.getNomCargo());
            ps.setString(3, cargo.getDescripcion());
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar cargo: " + e.getMessage());
            return false;
        }
    }
    
    // READ - Listar todos los cargos
    public List<Cargo> listarTodos() {
        List<Cargo> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cargo ORDER BY nom_cargo";
            Statement st = conexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Cargo c = new Cargo();
                c.setIdCargo(rs.getString("id_cargo"));
                c.setNomCargo(rs.getString("nom_cargo"));
                c.setDescripcion(rs.getString("descripcion"));
                lista.add(c);
            }
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar cargos: " + e.getMessage());
        }
        return lista;
    }
    
    // READ - Buscar por ID
    public Cargo buscarPorId(String id) {
        Cargo cargo = null;
        try {
            String sql = "SELECT * FROM cargo WHERE id_cargo = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                cargo = new Cargo();
                cargo.setIdCargo(rs.getString("id_cargo"));
                cargo.setNomCargo(rs.getString("nom_cargo"));
                cargo.setDescripcion(rs.getString("descripcion"));
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar cargo: " + e.getMessage());
        }
        return cargo;
    }
    
    // READ - Buscar por nombre
    public Cargo buscarPorNombre(String nombre) {
        Cargo cargo = null;
        try {
            String sql = "SELECT * FROM cargo WHERE nom_cargo = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                cargo = new Cargo();
                cargo.setIdCargo(rs.getString("id_cargo"));
                cargo.setNomCargo(rs.getString("nom_cargo"));
                cargo.setDescripcion(rs.getString("descripcion"));
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar cargo: " + e.getMessage());
        }
        return cargo;
    }
    
    // UPDATE - Actualizar cargo
    public boolean actualizar(Cargo cargo) {
        try {
            String sql = "UPDATE cargo SET nom_cargo = ?, descripcion = ? WHERE id_cargo = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            
            ps.setString(1, cargo.getNomCargo());
            ps.setString(2, cargo.getDescripcion());
            ps.setString(3, cargo.getIdCargo());
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar cargo: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE - Eliminar cargo
    public boolean eliminar(String id) {
        try {
            String sql = "DELETE FROM cargo WHERE id_cargo = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, id);
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cargo: " + e.getMessage());
            return false;
        }
    }
    
    // Generar siguiente ID (opcional si no usas secuencia automática)
    public String generarSiguienteId() {
        try {
            String sql = "SELECT MAX(CAST(id_cargo AS INT)) FROM cargo";
            Statement st = conexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()) {
                int ultimoNumero = rs.getInt(1);
                return String.valueOf(ultimoNumero + 1);
            }
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar ID: " + e.getMessage());
        }
        return "1";
    }
    
    // Método útil para ComboBox - Obtener solo los nombres
    public List<String> obtenerNombresCargos() {
        List<String> nombres = new ArrayList<>();
        try {
            String sql = "SELECT nom_cargo FROM cargo ORDER BY nom_cargo";
            Statement st = conexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                nombres.add(rs.getString("nom_cargo"));
            }
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener nombres de cargos: " + e.getMessage());
        }
        return nombres;
    }
}
