package servlet;

import conexion.Conexion;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/actualizar")
public class ActualizarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String fecha = request.getParameter("fecha");
            String equipo = request.getParameter("equipo");
            String observaciones = request.getParameter("observaciones");
            String estado = request.getParameter("estado");

            Connection con = Conexion.getConexion();

            String sql = "UPDATE monitoreo SET nombre_cliente=?, fecha=?, equipo=?, observaciones=?, estado=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, fecha);
            ps.setString(3, equipo);
            ps.setString(4, observaciones);
            ps.setString(5, estado);
            ps.setInt(6, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}