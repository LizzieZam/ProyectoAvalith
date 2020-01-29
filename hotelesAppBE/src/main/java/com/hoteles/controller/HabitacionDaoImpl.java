package com.hoteles.controller;

import com.hoteles.model.Habitacion;
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
public class HabitacionDaoImpl implements HabitacionDao {

    private DataSource dataSource;
    Connection conn = null;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Habitacion> getAllHabitacions() {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "select * from habitacion order by estado,numCamas";
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setNumCamas(rs.getInt("numCamas"));
                habitacion.setDescripcion(rs.getString("descripcion"));
                habitacion.setEstado(rs.getString("estado"));
                habitacion.setPrecio(rs.getDouble("precio"));
                habitaciones.add(habitacion);
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
        return habitaciones;
    }
    @Override
    public List<Habitacion> getAllHabitacions(String filter) {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "select * from habitacion where numCamas=" + Integer.valueOf(filter)
                    + " or descripcion like '%" + filter + "%'"
                    + " or estado like '%" + filter + "%'"
                    + " or precio = " + Double.valueOf(filter) +" order by estado,numCamas";
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setNumCamas(rs.getInt("numCamas"));
                habitacion.setDescripcion(rs.getString("descripcion"));
                habitacion.setEstado(rs.getString("estado"));
                habitacion.setPrecio(rs.getDouble("precio"));
                habitaciones.add(habitacion);
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
        return habitaciones;
    }

    @Override
    public Habitacion getHabitacionById(int id) {
        Habitacion habitacion = null;
        String sql = "select * from habitacion where idHabitacion=" + id;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                habitacion = new Habitacion();
                habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                habitacion.setNumCamas(rs.getInt("numCamas"));
                habitacion.setDescripcion(rs.getString("descripcion"));
                habitacion.setEstado(rs.getString("estado"));
                habitacion.setPrecio(rs.getDouble("precio"));
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
        return habitacion;
    }

    @Override
    public Habitacion addHabitacion(Habitacion newHabitacion) {
        Habitacion habitacion = null;
        String sql = "";
        try {
            conn = dataSource.getConnection();
            if (newHabitacion.getIdHabitacion()==0) {
                sql = "insert into habitacion(descripcion,numCamas,estado,precio) values (?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, newHabitacion.getDescripcion().toUpperCase());
                stmt.setInt(2, newHabitacion.getNumCamas());
                stmt.setString(3, newHabitacion.getEstado().toUpperCase());
                stmt.setDouble(4, newHabitacion.getPrecio());
                stmt.executeUpdate();
                sql = "SELECT * FROM habitacion where idHabitacion=(select max(h.idHabitacion) from habitacion h)";
                 stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    habitacion = new Habitacion();
                    habitacion.setIdHabitacion(rs.getInt("idHabitacion"));
                    habitacion.setNumCamas(rs.getInt("numCamas"));
                    habitacion.setDescripcion(rs.getString("descripcion"));
                    habitacion.setEstado(rs.getString("estado"));
                    habitacion.setPrecio(rs.getDouble("precio"));
                }
                rs.close();
                stmt.close();
                return habitacion;
            } else {
                sql = "update habitacion set descripcion=?,numCamas=?,estado=?,precio=? where idHabitacion=" + newHabitacion.getIdHabitacion();
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, newHabitacion.getDescripcion().toUpperCase());
                stmt.setInt(2, newHabitacion.getNumCamas());
                stmt.setString(3, newHabitacion.getEstado().toUpperCase());
                stmt.setDouble(4, newHabitacion.getPrecio());
                stmt.executeUpdate();
                stmt.close();
                return newHabitacion;
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
