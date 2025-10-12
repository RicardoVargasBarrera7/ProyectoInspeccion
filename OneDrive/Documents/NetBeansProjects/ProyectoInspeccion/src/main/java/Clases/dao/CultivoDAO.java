/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.dao;

import Clases.BD.CConexion;
import Clases.modelo.Cultivo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CultivoDAO {
    
    private CConexion conexion;
    
    public CultivoDAO() {
        this.conexion = new CConexion();
    }
    
    // CREATE - Insertar cultivo
    public boolean insertar(Cultivo cultivo) {
        try {
            String sql = "INSERT INTO cultivo (id_cultivo, especie, nombre_comun, nombre_variedad, ciclo_cultivo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            
            ps.setString(1, cultivo.getIdCultivo());
            ps.setString(2, cultivo.getEspecie());
            ps.setString(3, cultivo.getNombreComun());
            ps.setString(4, cultivo.getNombreVariedad());
            ps.setString(5, cultivo.getCicloCultivo());
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar cultivo: " + e.getMessage());
            return false;
        }
    }
    
    // READ - Listar todos los cultivos
    public List<Cultivo> listarTodos() {
        List<Cultivo> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cultivo ORDER BY especie";
            Statement st = conexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Cultivo c = new Cultivo();
                c.setIdCultivo(rs.getString("id_cultivo"));
                c.setEspecie(rs.getString("especie"));
                c.setNombreComun(rs.getString("nombre_comun"));
                c.setNombreVariedad(rs.getString("nombre_variedad"));
                c.setCicloCultivo(rs.getString("ciclo_cultivo"));
                lista.add(c);
            }
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar cultivos: " + e.getMessage());
        }
        return lista;
    }
    
    // UPDATE - Actualizar cultivo
    public boolean actualizar(Cultivo cultivo) {
        try {
            String sql = "UPDATE cultivo SET especie=?, nombre_comun=?, nombre_variedad=?, ciclo_cultivo=? WHERE id_cultivo=?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            
            ps.setString(1, cultivo.getEspecie());
            ps.setString(2, cultivo.getNombreComun());
            ps.setString(3, cultivo.getNombreVariedad());
            ps.setString(4, cultivo.getCicloCultivo());
            ps.setString(5, cultivo.getIdCultivo());
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar cultivo: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE - Eliminar cultivo
    public boolean eliminar(String id) {
        try {
            String sql = "DELETE FROM cultivo WHERE id_cultivo = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, id);
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cultivo: " + e.getMessage());
            return false;
        }
    }
    
    // Generar siguiente ID (si no usas la secuencia en SQL directamente)
    public String generarSiguienteId() {
        try {
            String sql = "SELECT MAX(CAST(id_cultivo AS INT)) FROM cultivo";
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
