/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.modelo;

public class Predio {
    
    private String idPredio;
    private String numPredial;
    private String nomPredio;
    private String idVereda;
    private String direccion;
    private String cx;
    private String cy;
    private double areaTotal;
    private String idUsuario;
    private String nroRegistroIca;
    
    // Constructor vacío
    public Predio() {
    }

    // Constructor completo
    public Predio(String idPredio, String numPredial, String nomPredio, String idVereda,
                  String direccion, String cx, String cy, double areaTotal,
                  String idUsuario, String nroRegistroIca) {
        this.idPredio = idPredio;
        this.numPredial = numPredial;
        this.nomPredio = nomPredio;
        this.idVereda = idVereda;
        this.direccion = direccion;
        this.cx = cx;
        this.cy = cy;
        this.areaTotal = areaTotal;
        this.idUsuario = idUsuario;
        this.nroRegistroIca = nroRegistroIca;
    }

    // Getters y Setters
    public String getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(String idPredio) {
        this.idPredio = idPredio;
    }

    public String getNumPredial() {
        return numPredial;
    }

    public void setNumPredial(String numPredial) {
        this.numPredial = numPredial;
    }

    public String getNomPredio() {
        return nomPredio;
    }

    public void setNomPredio(String nomPredio) {
        this.nomPredio = nomPredio;
    }

    public String getIdVereda() {
        return idVereda;
    }

    public void setIdVereda(String idVereda) {
        this.idVereda = idVereda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public String getCy() {
        return cy;
    }

    public void setCy(String cy) {
        this.cy = cy;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNroRegistroIca() {
        return nroRegistroIca;
    }

    public void setNroRegistroIca(String nroRegistroIca) {
        this.nroRegistroIca = nroRegistroIca;
    }

    @Override
    public String toString() {
        return nomPredio + " (" + numPredial + ")";
    }
}
