package com.hoteles.model;

public class Cliente {

    private int idCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String dniCliente;
    private String celularCliente;
    private String ciudadOriCliente;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombreCliente, String apellidoCliente, String dniCliente, String celularCliente, String ciudadOriCliente) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.dniCliente = dniCliente;
        this.celularCliente = celularCliente;
        this.ciudadOriCliente = ciudadOriCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getCiudadOriCliente() {
        return ciudadOriCliente;
    }

    public void setCiudadOriCliente(String ciudadOriCliente) {
        this.ciudadOriCliente = ciudadOriCliente;
    }

}
