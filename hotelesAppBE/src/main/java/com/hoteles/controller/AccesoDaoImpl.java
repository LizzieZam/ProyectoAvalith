/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.controller;

import com.hoteles.model.Acceso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author lizzi
 */
@Component
public class AccesoDaoImpl implements AccesoDao {

    private DataSource dataSource;
    Connection conn = null;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getAcceso(Acceso newUsuario) {
        Acceso acceso = null;
        String sql = "select * from acceso where username='" + newUsuario.getUsername()
                + "' and password='" + newUsuario.getPassword()+"'";
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                acceso = new Acceso();
                acceso.setUsername(rs.getString("username"));
                acceso.setPassword(rs.getString("password"));

            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return (acceso != null) ? "OK" : "Usuario o Contraseña incorrecto";
    }

    @Override
    public Acceso addUsuario(Acceso newUsuario) {
        Acceso acceso = null;
        String sql = "";
        try {
            conn = dataSource.getConnection();

            sql = "insert into acceso(username,password) values (?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newUsuario.getUsername());
            stmt.setString(2, newUsuario.getPassword());
            stmt.executeUpdate();
            sql = "SELECT * FROM acceso where idAcceso=(select max(h.idAcceso) from acceso h)";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                acceso = new Acceso();
                acceso.setIdAcceso(rs.getInt("idAcceso"));
                acceso.setUsername(rs.getString("username"));
                acceso.setPassword(rs.getString("password"));

            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return acceso;
    }

    @Override
    public Acceso getAccesoById(int id) {
         Acceso acceso = null;
        String sql = "select * from acceso where idAcceso=" + id;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                acceso = new Acceso();
                acceso.setUsername(rs.getString("username"));
                acceso.setPassword(rs.getString("password"));

            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return acceso ;
    }

}
