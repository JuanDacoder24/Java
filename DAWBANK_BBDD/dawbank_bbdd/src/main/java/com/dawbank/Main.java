package com.dawbank;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        System.out.println("***Bienvenido a TuBank***");
        String iban;

        do {
            System.out.println("Introduce una IBAN");
            iban = teclado.nextLine().toUpperCase();
        }while (!setValidIban(iban));
 
        System.out.println("Ingresa nombre del titular:");
        String nombre = teclado.nextLine();

        System.out.println("Ingresa DNI del titular:");
        String dni = teclado.nextLine();

        System.out.println("Ingresa fecha de nacimiento (YYYY-MM-DD):");
        LocalDate fechaNacimiento = null;
        
        while (fechaNacimiento == null) {
            try {
                fechaNacimiento = LocalDate.parse(teclado.nextLine());
            } catch (Exception e) {
                System.out.println("Fecha inválida. Inténtalo nuevamente (Formato: YYYY-MM-DD):");
            }
        }

        System.out.println("Ingresa teléfono del titular:");
        String telefono = teclado.nextLine();

        System.out.println("Ingresa email del titular:");
        String email = teclado.nextLine();

        System.out.println("Ingresa dirección del titular:");
        String direccion = teclado.nextLine();

        Cliente titular = new Cliente(nombre, dni, java.sql.Date.valueOf(fechaNacimiento), telefono, email, direccion);

        CuentaBancaria cuenta = new CuentaBancaria(iban, titular);

        String opcion = "";

        do { 
            System.out.println("1 - Datos de la cuenta");
            System.out.println("2 - IBAN");
            System.out.println("3 - Titular");
            System.out.println("4 - Saldo");
            System.out.println("5 - Ingreso");
            System.out.println("6 - Retirada");
            System.out.println("7 - Movimientos");
            System.out.println("8 - Salir");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println("Titular: " + cuenta.getTitular() +" | "+ "IBAN: " + cuenta.getIban() +" | "+ "Saldo: " + cuenta.getSaldo());
                    System.out.println("------------------------------------------------------------------------------");
                    break;

                case "2":
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println("El numero del IBAN es: " + cuenta.getIban());
                    System.out.println("------------------------------------------------------------------------------");
                    break;

                case "3":
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println("El titular de la cuenta es: " + cuenta.getTitular());
                    System.out.println("------------------------------------------------------------------------------");
                    break;  

                case "4":
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println("El saldo de la cuenta es: " + cuenta.getSaldo());
                    System.out.println("------------------------------------------------------------------------------");
                    break;

                case "5":
                    try {
                        System.out.println("Escriba la cantidad que desee ingresar.");
                        teclado = new Scanner(System.in);
                        double cantidad = teclado.nextDouble();
                        cuenta.ingresarMovimientos(cantidad);
                    } catch (CuentaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "6":
                    try {
                        System.out.println("Escriba la cantidad que desee retirar.");
                        teclado = new Scanner(System.in);
                        double cantidad = teclado.nextDouble();
                        cuenta.retirarMovimientos(cantidad);
                    } catch (CuentaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "7":
                    cuenta.mostrarMovimiento();
                    break;

                case "8":
                    System.out.println("Saliendo del banco, HASTA PRONTO");
                    break;

                default:
                    System.out.println("Introduzca la alternativa correcta");
            }
        } while (!opcion.equalsIgnoreCase("8"));
    }

    public static boolean setValidIban(String iban) {
        Pattern pat = Pattern.compile("[A-Z]{2}[0-9]{22}");
        Matcher mat = pat.matcher(iban);
        return mat.matches();
        
    }
}
