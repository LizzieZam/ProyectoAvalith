package com.hoteles.controller;

import com.hoteles.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteDaoImpl implements ClienteDao {

    private DataSource dataSource;
    Connection conn = null;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "select * from cliente order by apellidoCliente,nombreCliente";
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombreCliente(rs.getString("nombreCliente"));
                cliente.setApellidoCliente(rs.getString("apellidoCliente"));
                cliente.setDniCliente(rs.getString("dniCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));
                cliente.setCiudadOriCliente(rs.getString("ciudadOriCliente"));
                clientes.add(cliente);
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
        return clientes;
    }

    @Override
    public List<Cliente> getAllClientes(String filter) {
        List<Cliente> clientees = new ArrayList<>();
        String sql = "select * from cliente where numCamas=" + Integer.valueOf(filter)
                + " or descripcion like '%" + filter + "%'"
                + " or estado like '%" + filter + "%'"
                + " or precio = " + Double.valueOf(filter) + " order by estado,numCamas";
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombreCliente(rs.getString("nombreCliente"));
                cliente.setApellidoCliente(rs.getString("apellidoCliente"));
                cliente.setDniCliente(rs.getString("dniCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));
                cliente.setCiudadOriCliente(rs.getString("ciudadOriCliente"));
                clientees.add(cliente);
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
        return clientees;
    }

    @Override
    public Cliente getClienteById(int id) {
        Cliente cliente = null;
        String sql = "select * from cliente where idCliente=" + id;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombreCliente(rs.getString("nombreCliente"));
                cliente.setApellidoCliente(rs.getString("apellidoCliente"));
                cliente.setDniCliente(rs.getString("dniCliente"));
                cliente.setCelularCliente(rs.getString("celularCliente"));
                cliente.setCiudadOriCliente(rs.getString("ciudadOriCliente"));
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
        return cliente;
    }

    @Override
    public Cliente addCliente(Cliente newCliente) {
        Cliente cliente = null;
        String sql = "";
        try {
            conn = dataSource.getConnection();
            if (newCliente.getIdCliente() == 0) {
                sql = "insert into cliente(nombreCliente,apellidoCliente,dniCliente,celularCliente,ciudadOriCliente) values (?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, newCliente.getNombreCliente().toUpperCase());
                stmt.setString(2, newCliente.getApellidoCliente().toUpperCase());
                stmt.setString(3, newCliente.getDniCliente().toUpperCase());
                stmt.setString(4, newCliente.getCelularCliente().toUpperCase());
                stmt.setString(5, newCliente.getCiudadOriCliente().toUpperCase());
                stmt.executeUpdate();
                sql = "SELECT * FROM cliente where idCliente=(select max(h.idCliente) from cliente h)";
                stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setNombreCliente(rs.getString("nombreCliente"));
                    cliente.setApellidoCliente(rs.getString("apellidoCliente"));
                    cliente.setDniCliente(rs.getString("dniCliente"));
                    cliente.setCelularCliente(rs.getString("celularCliente"));
                    cliente.setCiudadOriCliente(rs.getString("ciudadOriCliente"));
                }
                rs.close();
                stmt.close();
                return cliente;
            } else {
                sql = "update cliente set descripcion=?,numCamas=?,estado=?,precio=? where idCliente=" + newCliente.getIdCliente();
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, newCliente.getNombreCliente().toUpperCase());
                stmt.setString(2, newCliente.getApellidoCliente().toUpperCase());
                stmt.setString(3, newCliente.getDniCliente().toUpperCase());
                stmt.setString(4, newCliente.getCelularCliente().toUpperCase());
                stmt.setString(5, newCliente.getCiudadOriCliente().toUpperCase());
                stmt.executeUpdate();
                stmt.close();
                return newCliente;
            }
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

    }

}
