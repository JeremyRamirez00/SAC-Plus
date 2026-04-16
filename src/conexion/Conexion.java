package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConexion() {
        Connection conexion = null;
        System.out.println("Conectado a BD...");

        System.out.println("URL: " + URL);
        System.out.println("USER: " + USER);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }

        return conexion;
    }

}