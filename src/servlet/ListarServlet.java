package servlet;

import conexion.Conexion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/listar")
public class ListarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Connection con = Conexion.getConexion();

            String sql = "SELECT * FROM monitoreo";
            PreparedStatement ps = con.prepareStatement(sql);
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