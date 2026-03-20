package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String opcion = "";

        //esto es para verificar que se conecta a la base de datos correctamente
        SqlDataManager sql;
        System.out.println("Probando conexion a la base de datos..." + SqlDataManager.getConnection());

        do { 
            System.out.println("1. Mostrar todos los Productos en el Inventario.");
            System.out.println("2. Buscar producto por referencia.");
            System.out.println("3. Buscar productos por tipo.");
            System.out.println("4. Buscar producto por cantidad.");
            System.out.println("5. Insertar un nuevo producto (no permitir referencias repetidas).");
            System.out.println("6. Eliminar Producto por referencia.");
            System.out.println("7. Actualizar producto (descripción, cantidad, precio, descuento, AplicarDto).");
            System.out.println("8. Insertar un nuevo tipo de producto.");
            System.out.println("9. Salir.");

            sc = new Scanner(System.in);
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    
                    break;

                case "2":
                    break;

                case "3":
                    break;

                case "4":
                    break;

                case "5":
                    break;

                case "6":             
                    break;

                case "7":
                    break;

                case "8":
                    break;
                    
                case "9":
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    throw new AssertionError();
            }
            
        } while (!opcion.equalsIgnoreCase("9"));

    }
}