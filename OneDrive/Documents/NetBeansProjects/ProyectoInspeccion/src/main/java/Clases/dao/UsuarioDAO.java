package Clases.dao;

import Clases.db.CConexion;
import Clases.modelo.Usuarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    private final CConexion conexion;

    public UsuarioDAO() {
        this.conexion = new CConexion();
    }

    // ✅ CREATE - Insertar usuario (no se modifica)
    public boolean insertar(Usuarios usuario) {
        try {
            String sql = "INSERT INTO Usuarios (id_usuario, num_identificacion, nombres, apellidos, direccion, telefono, correo_electronico, ingreso_usuario, ingreso_contrasenia, nro_registro_ica, tarjeta_profesional, id_cargo) "
                    + "VALUES (SEQ_USUARIOS.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

    // ✅ READ - Listar usuarios
    public List<Usuarios> listarTodos() {
        List<Usuarios> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios ORDER BY id_usuario";

        try (Connection con = conexion.estableceConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

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
                u.setIdCargo(rs.getString("id_cargo"));
                lista.add(u);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    // ✅ UPDATE - Actualizar usuario
    public boolean actualizar(Usuarios usuario) {
        String sql = "UPDATE Usuarios SET num_identificacion=?, nombres=?, apellidos=?, direccion=?, telefono=?, correo_electronico=?, ingreso_usuario=?, ingreso_contrasenia=?, nro_registro_ica=?, tarjeta_profesional=?, id_cargo=? WHERE id_usuario=?";

        try (Connection con = conexion.estableceConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

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
            ps.setString(12, usuario.getIdUsuario());

            int resultado = ps.executeUpdate();
            return resultado > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // ✅ DELETE - Eliminar usuario
    public boolean eliminar(String id) {
        String sql = "DELETE FROM Usuarios WHERE id_usuario = ?";

        try (Connection con = conexion.estableceConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            int resultado = ps.executeUpdate();
            return resultado > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    // ✅ READ - Buscar usuario por ID
    public Usuarios obtenerPorId(String idUsuario) {
        Usuarios usuario = null;
        String sql = "SELECT * FROM Usuarios WHERE id_usuario = ?";

        try (Connection con = conexion.estableceConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuarios();
                usuario.setIdUsuario(rs.getString("id_usuario"));
                usuario.setNumIdentificacion(rs.getString("num_identificacion"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setCorreoElectronico(rs.getString("correo_electronico"));
                usuario.setIngresoUsuario(rs.getString("ingreso_usuario"));
                usuario.setIngresoContrasenia(rs.getString("ingreso_contrasenia"));
                usuario.setNroRegistroICA(rs.getString("nro_registro_ica"));
                usuario.setTarjetaProfesional(rs.getString("tarjeta_profesional"));
                usuario.setIdCargo(rs.getString("id_cargo"));
            }
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener usuario: " + e.getMessage());
        }
        return usuario;
    }

    // ✅ GENERADOR DE ID
    public String generarSiguienteId() {
        String sql = "SELECT MAX(TO_NUMBER(id_usuario)) FROM Usuarios";
        try (Connection con = conexion.estableceConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                int ultimoNumero = rs.getInt(1);
                return String.valueOf(ultimoNumero + 1);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al generar ID: " + e.getMessage());
        }
        return "1"; // si no hay registros, el primero será "1"
    }
}
