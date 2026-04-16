package servlet;

import conexion.Conexion;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/buscar")
public class BuscarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            String nombre = request.getParameter("nombre");

            Connection con = Conexion.getConexion();

            String sql = "SELECT * FROM monitoreo WHERE nombre_cliente LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");

            ResultSet rs = ps.executeQuery();

            StringBuilder datos = new StringBuilder();

            while (rs.next()) {
                datos.append(rs.getInt("id")).append(",");
                datos.append(rs.getString("nombre_cliente")).append(",");
                datos.append(rs.getString("fecha")).append(",");
                datos.append(rs.getString("equipo")).append(",");
                datos.append(rs.getString("observaciones")).append(",");
                datos.append(rs.getString("estado")).append(";");
            }

            response.setContentType("text/plain;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(datos.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}