package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opcion = "";

        // esto es para verificar que se conecta a la base de datos correctamente
        System.out.println("Probando conexion a la base de datos..." + SQLDataManager.getConnection());   
        
        do { 
            System.out.println("1. Registrar usuario");
            System.out.println();
        } while (!opcion.equalsIgnoreCase(""));

        System.out.println(SQLAccessReserva
                .getReservas()
                .toString());
        
    }
}