/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.modelo;

public class Usuarios {
    private String idUsuario;
    private String numIdentificacion;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String ingresoUsuario;
    private String ingresoContrasenia;
    private String nroRegistroICA;
    private String tarjetaProfesional;
    private String idCargo;
    
    // Constructor vacío
    public Usuarios() {
    }
    
    // Constructor completo
    public Usuarios(String idUsuario, String numIdentificacion, String nombres, 
                   String apellidos, String direccion, String telefono, 
                   String correoElectronico, String ingresoUsuario, 
                   String ingresoContrasenia, String nroRegistroICA,
                   String tarjetaProfesional, String idCargo) {
        this.idUsuario = idUsuario;
        this.numIdentificacion = numIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.ingresoUsuario = ingresoUsuario;
        this.ingresoContrasenia = ingresoContrasenia;
        this.nroRegistroICA = nroRegistroICA;
        this.tarjetaProfesional = tarjetaProfesional;
        this.idCargo = idCargo;
    }

    // Getters y Setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getIngresoUsuario() {
        return ingresoUsuario;
    }

    public void setIngresoUsuario(String ingresoUsuario) {
        this.ingresoUsuario = ingresoUsuario;
    }

    public String getIngresoContrasenia() {
        return ingresoContrasenia;
    }

    public void setIngresoContrasenia(String ingresoContrasenia) {
        this.ingresoContrasenia = ingresoContrasenia;
    }

    public String getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(String idCargo) {
        this.idCargo = idCargo;
    }
    
    public String getNroRegistroICA() {
        return nroRegistroICA;
    }

    public void setNroRegistroICA (String nroRegistroICA) {
        this.nroRegistroICA = nroRegistroICA;
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional (String tarjetaProfesional) {
        this.tarjetaProfesional = tarjetaProfesional;
    }
    
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}