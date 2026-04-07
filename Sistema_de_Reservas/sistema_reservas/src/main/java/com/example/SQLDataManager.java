package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDataManager {

    private final String DRIVER;
    private final String URL;
    private final String SCHEMA;
    private final String USUARIO;
    private final String CLAVE;

    public static Connection getConnection(){
        
        Connection con = null;

        SQLDataManager sql = null;

        String DRIVER = "", URL = "", SCHEMA = "", USUARIO = "", CLAVE = "";
        InputStream resource = SQLDataManager.class.getResourceAsStream("/application.dat");
        if (resource == null) {
            System.out.println("No se encontró el archivo application.dat en el classpath.");
            return null;
        }
        try (BufferedReader lector = new BufferedReader(new InputStreamReader(resource))) {

                String linea = lector.readLine();

                while (linea != null) {
                    String[] datos = linea.split(",");
                    if (datos.length < 5) {
                        System.out.println("Formato inválido en application.dat. Debe tener 5 valores separados por comas.");
                        return null;
                    }
                    DRIVER = datos[0];
                    URL = datos[1];
                    SCHEMA = datos[2];
                    USUARIO = datos[3];
                    CLAVE = datos[4];

                    linea = lector.readLine();
                }

                sql = new SQLDataManager(DRIVER, URL, SCHEMA, USUARIO, CLAVE);

            } catch (IOException e) {
                System.out.println("Error al leer el archivo de conexión a la BD. " + e.getMessage());
            }

            try {
                if (sql != null) {
                    Class.forName(sql.getDRIVER());
                    con = DriverManager.getConnection(sql.URL + sql.SCHEMA, sql.USUARIO, sql.CLAVE);
                }

            } catch (ClassNotFoundException e) {
                System.out.println("Error al conectar con el driver " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Fallo al conectar con la base de datos" + e.getMessage());
            }

        return con;
    }

    public SQLDataManager(String DRIVER, String URL, String SCHEMA, String USUARIO, String CLAVE) {
        this.DRIVER = DRIVER;
        this.URL = URL;
        this.SCHEMA = SCHEMA;
        this.USUARIO = USUARIO;
        this.CLAVE = CLAVE;
    }

    public String getDRIVER() {
        return DRIVER;
    }

    public String getURL() {
        return URL;
    }

    public String getSCHEMA() {
        return SCHEMA;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public String getCLAVE() {
        return CLAVE;
    }
    
}
