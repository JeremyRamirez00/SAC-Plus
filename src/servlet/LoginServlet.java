package servlet;

import dao.UsuarioDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        System.out.println("ENTRANDO AL DAO ");

        UsuarioDAO dao = new UsuarioDAO();

        boolean valido = dao.validarUsuario(usuario, password);

        if (valido) {
            response.sendRedirect("dashboard.html");
        } else {
            response.getWriter().println("Usuario o contraseña incorrectos");
        }
    }
}