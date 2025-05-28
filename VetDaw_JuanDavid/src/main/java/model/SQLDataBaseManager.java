package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDataBaseManager {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "VetDaw";
    private static final String USUARIO = "root";
    private static final String CLAVE = "daw12";


    public static Connection getConnection(){
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL+SCHEMA, USUARIO, CLAVE);

        } catch (ClassNotFoundException e) {
            System.out.println("Error de acceso al driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }

        return connection;
    }

    public static void testConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexión a la base de datos exitosa.");
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al probar la conexión: " + e.getMessage());
        }
    }

}
