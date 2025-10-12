/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.dao;

import Clases.db.CConexion;
import Clases.modelo.Predio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PredioDAO {
    
    private CConexion conexion;
    
    public PredioDAO() {
        this.conexion = new CConexion();
    }
    
    // CREATE - Insertar predio
    public boolean insertar(Predio predio) {
        try {
            String sql = "INSERT INTO predio (id_predio, num_predial, nom_predio, id_vereda, direccion, cx, cy, area_total, id_usuario, nro_registro_ica) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            
            ps.setString(1, predio.getIdPredio());
            ps.setString(2, predio.getNumPredial());
            ps.setString(3, predio.getNomPredio());
            ps.setString(4, predio.getIdVereda());
            ps.setString(5, predio.getDireccion());
            ps.setString(6, predio.getCx());
            ps.setString(7, predio.getCy());
            ps.setDouble(8, predio.getAreaTotal());
            ps.setString(9, predio.getIdUsuario());
            ps.setString(10, predio.getNroRegistroIca());
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar predio: " + e.getMessage());
            return false;
        }
    }
    
    // READ - Listar todos los predios
    public List<Predio> listarTodos() {
        List<Predio> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM predio ORDER BY nom_predio";
            Statement st = conexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Predio p = new Predio();
                p.setIdPredio(rs.getString("id_predio"));
                p.setNumPredial(rs.getString("num_predial"));
                p.setNomPredio(rs.getString("nom_predio"));
                p.setIdVereda(rs.getString("id_vereda"));
                p.setDireccion(rs.getString("direccion"));
                p.setCx(rs.getString("cx"));
                p.setCy(rs.getString("cy"));
                p.setAreaTotal(rs.getDouble("area_total"));
                p.setIdUsuario(rs.getString("id_usuario"));
                p.setNroRegistroIca(rs.getString("nro_registro_ica"));
                lista.add(p);
            }
            rs.close();
            st.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar predios: " + e.getMessage());
        }
        return lista;
    }
    
    // READ - Listar por usuario (propietario)
    public List<Predio> listarPorUsuario(String idUsuario) {
        List<Predio> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM predio WHERE id_usuario = ? ORDER BY nom_predio";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Predio p = new Predio();
                p.setIdPredio(rs.getString("id_predio"));
                p.setNumPredial(rs.getString("num_predial"));
                p.setNomPredio(rs.getString("nom_predio"));
                p.setIdVereda(rs.getString("id_vereda"));
                p.setDireccion(rs.getString("direccion"));
                p.setCx(rs.getString("cx"));
                p.setCy(rs.getString("cy"));
                p.setAreaTotal(rs.getDouble("area_total"));
                p.setIdUsuario(rs.getString("id_usuario"));
                p.setNroRegistroIca(rs.getString("nro_registro_ica"));
                lista.add(p);
            }
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar predios por usuario: " + e.getMessage());
        }
        return lista;
    }
    
    // UPDATE - Actualizar predio
    public boolean actualizar(Predio predio) {
        try {
            String sql = "UPDATE predio SET num_predial = ?, nom_predio = ?, id_vereda = ?, direccion = ?, "
                       + "cx = ?, cy = ?, area_total = ?, id_usuario = ?, nro_registro_ica = ? "
                       + "WHERE id_predio = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            
            ps.setString(1, predio.getNumPredial());
            ps.setString(2, predio.getNomPredio());
            ps.setString(3, predio.getIdVereda());
            ps.setString(4, predio.getDireccion());
            ps.setString(5, predio.getCx());
            ps.setString(6, predio.getCy());
            ps.setDouble(7, predio.getAreaTotal());
            ps.setString(8, predio.getIdUsuario());
            ps.setString(9, predio.getNroRegistroIca());
            ps.setString(10, predio.getIdPredio());
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar predio: " + e.getMessage());
            return false;
        }
    }
    
    // DELETE - Eliminar predio
    public boolean eliminar(String id) {
        try {
            String sql = "DELETE FROM predio WHERE id_predio = ?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, id);
            
            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar predio: " + e.getMessage());
            return false;
        }
    }
    
    // Generar siguiente ID (si no se usa la secuencia directamente)
    public String generarSiguienteId() {
        try {
            String sql = "SELECT MAX(CAST(id_predio AS INT)) FROM predio";
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
