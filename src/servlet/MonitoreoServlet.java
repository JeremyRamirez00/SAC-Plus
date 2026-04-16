package servlet;

import dao.MonitoreoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/guardar")
public class MonitoreoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String equipo = request.getParameter("equipo");
        String observaciones = request.getParameter("observaciones");
        String estado = request.getParameter("estado");

        System.out.println("LLEGO AL SERVLET GUARDAR ");

        MonitoreoDAO dao = new MonitoreoDAO();
        dao.guardar(nombre, fecha, equipo, observaciones, estado);

        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.sendRedirect("dashboard.html");
    }
}