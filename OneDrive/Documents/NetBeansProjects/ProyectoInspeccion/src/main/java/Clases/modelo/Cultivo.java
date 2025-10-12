/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.modelo;

public class Cultivo {

    private String idCultivo;
    private String especie;
    private String nombreComun;
    private String nombreVariedad;
    private String cicloCultivo;
    
    // Constructor vacío
    public Cultivo() {
    }

    // Constructor completo
    public Cultivo(String idCultivo, String especie, String nombreComun, String nombreVariedad, String cicloCultivo) {
        this.idCultivo = idCultivo;
        this.especie = especie;
        this.nombreComun = nombreComun;
        this.nombreVariedad = nombreVariedad;
        this.cicloCultivo = cicloCultivo;
    }

    // Getters y Setters
    public String getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(String idCultivo) {
        this.idCultivo = idCultivo;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getNombreVariedad() {
        return nombreVariedad;
    }

    public void setNombreVariedad(String nombreVariedad) {
        this.nombreVariedad = nombreVariedad;
    }

    public String getCicloCultivo() {
        return cicloCultivo;
    }

    public void setCicloCultivo(String cicloCultivo) {
        this.cicloCultivo = cicloCultivo;
    }

    @Override
    public String toString() {
        return especie + " - " + nombreVariedad;
    }
}
