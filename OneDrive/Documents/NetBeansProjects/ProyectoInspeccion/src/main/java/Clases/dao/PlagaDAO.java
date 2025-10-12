/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.dao;

import Clases.db.CConexion;
import Clases.modelo.Plaga;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PlagaDAO {
    
    private CConexion conexion;
    
    public PlagaDAO() {
        this.conexion = new CConexion();
    }
    
    // CREATE - Insertar plaga
    public boolean insertar(Plaga plaga) {
        try {
            String sql = "INSERT INTO plaga (id_plaga, especie, nombre_comun, id_cultivo) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            
            ps.setString(1, plaga.getIdPlaga());
            ps.setString(2, plaga.getEspecie());
            ps.setString(3, plaga.getNombreComun());
            ps.setString(4, plaga.getIdCultivo());
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar plaga: " + e.getMessage());
            return false;
        }
    }
    
    // READ - Listar todas las plagas
    public List<Plaga> listarTodos() {
        List<Plaga> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM plaga ORDER BY nombre_comun";
            Statement st = conexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Plaga p = new Plaga();
                p.setIdPlaga(rs.getString("id_plaga"));
                p.setEspecie(rs.getString("especie"));
                p.setNombreComun(rs.getString("nombre_comun"));
                p.setIdCultivo(rs.getString("id_cultivo"));
                lista.add(p);
            }
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar plagas: " + e.getMessage());
        }
        return lista;
    }
    
    // UPDATE - Actualizar plaga
    public boolean actualizar(Plaga plaga) {
        try {
            String sql = "UPDATE plaga SET especie = ?, nombre_comun = ?, id_cultivo = ? WHERE id_plaga = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            
            ps.setString(1, plaga.getEspecie());
            ps.setString(2, plaga.getNombreComun());
            ps.setString(3, plaga.getIdCultivo());
            ps.setString(4, plaga.getIdPlaga());
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar plaga: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE - Eliminar plaga
    public boolean eliminar(String id) {
        try {
            String sql = "DELETE FROM plaga WHERE id_plaga = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, id);
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar plaga: " + e.getMessage());
            return false;
        }
    }
    
    // Generar siguiente ID (si no usas secuencia)
    public String generarSiguienteId() {
        try {
            String sql = "SELECT MAX(CAST(id_plaga AS INT)) FROM plaga";
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
}
