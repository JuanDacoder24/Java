package com.dawbank;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SQLAccessDawBank {

    public int registrarMovimiento(Movimiento movimiento) {
        
        int response = -1;
        String sqlStatement = "INSERT INTO movimiento (id, fecha, tipo, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection connection = SQLDataBaseManager.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {
            statement.setInt(1, movimiento.getId());
            statement.setString(2, movimiento.getFecha().toString());
            statement.setString(3, movimiento.getTipo());
            statement.setDouble(4, movimiento.getCantidad());

            response = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } 
        return response;
    }
    

}
