package com.decroly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDataBaseManager {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "Taxis";
    private static final String USER = "root";
    private static final String PASS = "daw12";

    public static Connection getConnection(){
        Connection conexion = null;

        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL + SCHEMA, USER, PASS);

        } catch (ClassNotFoundException e) {
            System.out.println("Error de acceso al driver: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
        
        return conexion;
    }

}
