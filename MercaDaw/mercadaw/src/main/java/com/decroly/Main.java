package com.decroly;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        SQLAccessMercaDaw miMercaDaw = new SQLAccessMercaDaw();
        List<Producto> listas = miMercaDaw.mostrarLista();
        
        Scanner teclado = new Scanner(System.in);
        String opcion = "";

        do { 
            System.out.println("****BIENVENIDO A MERCADAW****");
            System.out.println("1. Mostrar todos los Productos en el Inventario.");
            System.out.println("2. Buscar producto por referencia.");
            System.out.println("3. Buscar productos por tipo.");
            System.out.println("4. Buscar producto por cantidad.");
            System.out.println("5. Insertar un nuevo producto (no permitir referencias repetidas)");
            System.out.println("6. Eliminar Producto por referencia.");
            System.out.println("7. Actualizar producto");
            System.out.println("8. Insertar un nuevo tipo de producto.");
            System.out.println("9. Salir");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    for (Producto mn : listas) {
                        System.out.println(mn);
                    }
                    break;
                
                case "2":
                    System.out.println("Introduce la referencia del producto:");
                    String referencia = teclado.nextLine(); 
                    Producto producto = miMercaDaw.buscarProductoPorRef(referencia); 
                    if (producto != null) {
                        System.out.println("Producto encontrado: " + producto.getNombre());
                    } else {
                        System.out.println("No se encontró ningún producto con esa referencia.");
                    }
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
                System.out.println("Saliendo del programa, HASTA PRONTO");
                    break;

                default:
                    System.out.println("Por favor pulsar la tecla correcta");
            }

        } while (!opcion.equalsIgnoreCase("9"));

    }
    public static boolean validRef(String referencia) {
        Pattern pat = Pattern.compile("[A-Z]{3}[0-9]{3}");
        Matcher mat = pat.matcher(referencia);
        return mat.matches();
    }
}