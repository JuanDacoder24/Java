package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class SQLAccessAlmacen {

    public static List<String> getProductByReferencia(){
        List<String> product = new LinkedList<>();

        String sqlProductReferencia = "SELECT referencia FROM productos ";

        try (Connection connection = SqlDataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlProductReferencia)){

            while (resultSet.next()) { 
                product.add(resultSet.getNString(1));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    public static List<Producto> getProducts(){
        List<Producto> product = new LinkedList<>();

        String sqlProducts = "SELECT * FROM productos";

        try (Connection connection = SqlDataManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlProducts)){

                while (resultSet.next()) { 
                    int id = resultSet.getInt(1);
                    String referencia = resultSet.getNString(2);
                    String nombre = resultSet.getNString(3);
                    String descripcion = resultSet.getNString(4);
                    int tipo_id = resultSet.getInt(5);
                    int cantidad = resultSet.getInt(6);
                    double precio = resultSet.getDouble(7);
                    double descuento = resultSet.getDouble(8);
                    double iva = resultSet.getDouble(9);
                    boolean aplicar_dto = resultSet.getBoolean(10);

                    //corregir 
                    //product.add(new Producto(id, referencia, nombre, descripcion, tipo_id, cantidad, precio, descuento, iva, aplicar_dto));
                }
            
        } catch (SQLException  e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return product;
    }

    public static int deleteProductById(int id) {

        int elements = -1;

        String sqlDeleteProduct = "DELETE FROM productos WHERE id = ?";

        try (Connection connection = SqlDataManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteProduct)) {

            preparedStatement.setInt(1, id);
            elements = preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("error al eliminar producto" + e.getMessage());
        }
        return elements;
    }

    public static int insertProduct(Producto producto){

        int response = -1;

        String sqlInsertProduct = "INSERT INTO productos (referencia, nombre, descripcion, tipo_id, cantidad, precio, descuento, iva, aplicar_dto) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = SqlDataManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertProduct)){
            preparedStatement.setString(1, producto.getReferencia());
            preparedStatement.setString(2, producto.getNombre());
            preparedStatement.setString(3, producto.getDescripcion());
            preparedStatement.setInt(4, producto.getTipo().getId());
            preparedStatement.setInt(5, producto.getCantidad());
            preparedStatement.setDouble(6, producto.getPrecio());
            preparedStatement.setDouble(7, producto.getDescuento());
            preparedStatement.setDouble(8, producto.getIva());
            preparedStatement.setBoolean(9, producto.isAplicarDto());

            response = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al insertar producto" + e.getMessage());
        }
        return response;
    }

    public static int updateProduct(Producto producto){

        int response = -1;

        String sqlUpdateProduct = "UPDATE productos SET descripcion = ?, cantidad = ?, precio = ?, descuento = ?, aplicar_dto = ? WHERE id = ?";

        try(Connection connection = SqlDataManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateProduct)){
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setInt(2, producto.getCantidad());
            preparedStatement.setDouble(3, producto.getPrecio());
            preparedStatement.setInt(4, producto.getDescuento());
            preparedStatement.setBoolean(5, producto.isAplicarDto());

            preparedStatement.setInt(6, producto.getId());

            response = preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("error al actualizar producto" + e.getMessage());
        }
        return response;
    }

}