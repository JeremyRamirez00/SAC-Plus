package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MonitoreoDAO {

    public void guardar(String nombre, String fecha, String equipo, String observaciones, String estado) {

        try {
            Connection con = Conexion.getConexion();

            String sql = "INSERT INTO monitoreo(nombre_cliente, fecha, equipo, observaciones, estado) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, fecha);
            ps.setString(3, equipo);
            ps.setString(4, observaciones);
            ps.setString(5, estado);

            ps.executeUpdate();

            System.out.println("REGISTRO GUARDADO");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet listar() {

        try {
            Connection con = Conexion.getConexion();
            String sql = "SELECT * FROM monitoreo";

            PreparedStatement ps = con.prepareStatement(sql);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}