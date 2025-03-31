package com.decroly;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class SQLAccessMercaDaw {

    public List<Producto> mostrarLista(){
        List<Producto> lista = new LinkedList<>();

        String mLista = "select * from productos";
        try (Connection connection = SQLDataManager.getConnection(); Statement statement = connection.createStatement();
        ResultSet dataSet = statement.executeQuery(mLista);) {
            while(dataSet.next()){

                int id = dataSet.getInt(1);
                String referencia = dataSet.getNString(2);
                String nombre = dataSet.getNString(3);
                String descripcion = dataSet.getNString(4);
                int cantidad = dataSet.getInt(5);
                double precio = dataSet.getDouble(6);
                int descuento = dataSet.getInt(7);
                int iva = dataSet.getInt(8);
                boolean aplicarDto = dataSet.getBoolean(9);
                
                Producto p1 = new Producto(id, referencia, nombre, descripcion, cantidad, precio, descuento, iva, aplicarDto);
                lista.add(p1);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

}
