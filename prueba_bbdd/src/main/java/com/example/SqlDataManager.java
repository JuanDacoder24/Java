package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDataManager {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA = "almacen";
    private static final String USUARIO = "root";
    private static final String CLAVE = "daw12";

    public static Connection getConnection(){
        Connection connection = null;

        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL + SCHEMA, USUARIO, CLAVE);

        }catch(ClassNotFoundException e){
            System.out.println("Error al acceder al driver: " + e.getMessage());

        } catch(SQLException e){
            System.out.println("Error de sql: " + e.getMessage());
        }

        System.out.println("Se ha establecido la conexion con la base de datos");

        return connection;
    }


}
