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
    
    public int registrarCliente(Cliente cliente) {
        
        int response = -1;
        String sqlStatement = "INSERT INTO cliente (nombre, dni, fecha_nacimiento, telefono, email, direccion) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = SQLDataBaseManager.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlStatement);) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getDni());
            statement.setString(3, cliente.getFechaNacimiento().toString());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5, cliente.getEmail());
            statement.setString(6, cliente.getDireccion());

            response = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } 
        return response;


    }
}
