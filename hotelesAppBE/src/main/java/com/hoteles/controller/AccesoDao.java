/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.controller;

import com.hoteles.model.Acceso;

/**
 *
 * @author lizzi
 */
public interface AccesoDao {
    
    public String getAcceso(Acceso newUsuario);
    public Acceso addUsuario(Acceso newUsuario);
    public Acceso getAccesoById(int id);
}
