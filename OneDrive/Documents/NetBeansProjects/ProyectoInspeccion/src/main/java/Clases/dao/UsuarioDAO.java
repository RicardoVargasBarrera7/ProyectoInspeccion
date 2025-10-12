package Clases.dao;

import Clases.db.CConexion;
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
        String sql = "INSERT INTO Usuarios (id_usuario, num_identificacion, nombres, apellidos, direccion, telefono, correo_electronico, ingreso_usuario, ingreso_contrasenia, nro_registro_ica, tarjeta_profesional, id_cargo) " +
             "VALUES (SEQ_USUARIOS.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);

        ps.setString(1, usuario.getNumIdentificacion());
        ps.setString(2, usuario.getNombres());
        ps.setString(3, usuario.getApellidos());
        ps.setString(4, usuario.getDireccion());
        ps.setString(5, usuario.getTelefono());
        ps.setString(6, usuario.getCorreoElectronico());
        ps.setString(7, usuario.getIngresoUsuario());
        ps.setString(8, usuario.getIngresoContrasenia());
        ps.setString(9, usuario.getNroRegistroICA());
        ps.setString(10, usuario.getTarjetaProfesional());
        ps.setString(11, usuario.getIdCargo());

        int resultado = ps.executeUpdate();
        ps.close();
        return resultado > 0;

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al insertar usuario: " + e.getMessage());
        return false;
    }
}


    // READ - Listar todos
    public List<Usuarios> listarTodos() {
        List<Usuarios> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Usuarios ORDER BY id_usuario";
            Statement st = conexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setIdUsuario(rs.getString("id_usuario"));
                u.setNumIdentificacion(rs.getString("num_identificacion"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setDireccion(rs.getString("direccion"));
                u.setTelefono(rs.getString("telefono"));
                u.setCorreoElectronico(rs.getString("correo_electronico"));
                u.setIngresoUsuario(rs.getString("ingreso_usuario"));
                u.setIngresoContrasenia(rs.getString("ingreso_contrasenia"));
                u.setNroRegistroICA(rs.getString("nro_registro_ica"));
                u.setTarjetaProfesional(rs.getString("tarjeta_profesional"));
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

    // UPDATE
    public boolean actualizar(Usuarios usuario) {
        try {
            String sql = "UPDATE Usuarios SET numIdentificacion=?, nombres=?, apellidos=?, direccion=?, telefono=?, correoElectronico=?, ingresoUsuario=?, ingresoContrasenia=?, nroRegistroICA=?, tarjetaProfesional=?, idCargo=? WHERE idUsuario=?";
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);

            ps.setString(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getNumIdentificacion());
            ps.setString(3, usuario.getNombres());
            ps.setString(4, usuario.getApellidos());
            ps.setString(5, usuario.getDireccion());
            ps.setString(6, usuario.getTelefono());
            ps.setString(7, usuario.getCorreoElectronico());
            ps.setString(8, usuario.getIngresoUsuario());
            ps.setString(9, usuario.getIngresoContrasenia());
            ps.setString(10, usuario.getNroRegistroICA());
            ps.setString(11, usuario.getTarjetaProfesional());
            ps.setString(12, usuario.getIdCargo());

            int resultado = ps.executeUpdate();
            ps.close();
            return resultado > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // DELETE
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

    public String generarSiguienteId() {
        try {
            String sql = "SELECT MAX(TO_NUMBER(id_usuario)) FROM Usuarios";
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
        return "1"; // si no hay registros, el primero será "1"
    }

}
