/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.modelo;

public class Plaga {

    private String idPlaga;
    private String especie;
    private String nombreComun;
    private String idCultivo; // 🔹 Relación foránea con la tabla cultivo
    
    // Constructor vacío
    public Plaga() {
    }

    // Constructor completo
    public Plaga(String idPlaga, String especie, String nombreComun, String idCultivo) {
        this.idPlaga = idPlaga;
        this.especie = especie;
        this.nombreComun = nombreComun;
        this.idCultivo = idCultivo;
    }

    // Getters y Setters
    public String getIdPlaga() {
        return idPlaga;
    }

    public void setIdPlaga(String idPlaga) {
        this.idPlaga = idPlaga;
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

    public String getIdCultivo() {
        return idCultivo;
    }

    public void setIdCultivo(String idCultivo) {
        this.idCultivo = idCultivo;
    }

    @Override
    public String toString() {
        return nombreComun + " (" + especie + ")";
    }
}
