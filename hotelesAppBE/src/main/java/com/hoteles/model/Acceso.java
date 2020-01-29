/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.model;

/**
 *
 * @author lizzi
 */
public class Acceso {

    private int idAcceso;
    private String username;
    private String password;

    public Acceso() {
    }

    public Acceso(int idAcceso, String username, String password) {
        this.idAcceso = idAcceso;
        this.username = username;
        this.password = password;
    }

    public int getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(int idAcceso) {
        this.idAcceso = idAcceso;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
