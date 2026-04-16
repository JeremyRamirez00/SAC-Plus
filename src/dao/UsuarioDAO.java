package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public boolean validarUsuario(String username, String password) {

        boolean valido = false;

        try {
            Connection con = Conexion.getConexion();

            String sql = "SELECT * FROM usuario";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String u = rs.getString("username");
                String p = rs.getString("password");

                if (u != null && p != null) {

                    u = u.trim();
                    p = p.trim();

                    if (u.equals(username.trim()) && p.equals(password.trim())) {
                        valido = true;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return valido;
    }
}