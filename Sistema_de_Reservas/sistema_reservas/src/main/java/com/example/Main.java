package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opcion = "";

        String nombre;

        // esto es para verificar que se conecta a la base de datos correctamente
        System.out.println("Probando conexion a la base de datos..." + SQLDataManager.getConnection());

        do {
            System.out.println("1. Registrar usuario");
            System.out.println("2. Reservar pista");
            System.out.println("3. Actualizar pista");
            System.out.println("4. Eliminar reserva");
            System.out.println("5. Ver reservas");
            System.out.println("6. Ver pistas");
            System.out.println("7. Ver usuarios");
            System.out.println("8. Salir");

            sc = new Scanner(System.in);
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Introduce el nombre del usuario:");
                    nombre = sc.nextLine();

                    System.out.println("Introduce el email del usuario:");
                    String email = sc.nextLine();

                    System.out.println("Introduce la contraseña del usuario:");
                    String password = sc.nextLine();

                    Usuarios usuario = new Usuarios(0, nombre, email, password);
                    SQLAccessReserva.registarUsuario(usuario);
                    break;

                case "2":
                    System.out.println("Introduce el id del usuario:");
                    int usuario_id = sc.nextInt();
                    sc.nextLine(); 

                    System.out.println("Introduce el id de la pista:");
                    int pista_id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Introduce la fecha de la reserva (YYYY-MM-DD):");
                    String fecha = sc.nextLine();

                    System.out.println("Introduce la hora de inicio de la reserva (HH:MM):");
                    String hora_inicio = sc.nextLine();

                    System.out.println("Introduce la hora de fin de la reserva (HH:MM):");
                    String hora_fin = sc.nextLine();

                    LocalDate fechaReserva = LocalDate.parse(fecha);
                    LocalDateTime inicio = LocalDateTime.parse(fecha + "T" + hora_inicio + ":00");
                    LocalDateTime fin = LocalDateTime.parse(fecha + "T" + hora_fin + ":00");

                    if (SQLAccessReserva.isPistaDisponible(pista_id, fechaReserva, inicio, fin)) {
                        Reservas reserva = new Reservas(0, usuario_id, pista_id, fechaReserva, inicio, fin, Estado.ACTIVA);
                        int resultado = SQLAccessReserva.reservarPistaConVerificacion(reserva);

                        if (resultado > 0) {
                            System.out.println("Reserva realizada con éxito");
                        } else if (resultado == -2) {
                            System.out.println("No se pudo realizar la reserva: la pista no está disponible");
                        } else {
                            System.out.println("Error al realizar la reserva");
                        }
                    } else {
                        System.out.println("La pista no está disponible en el horario seleccionado");
                    }

                    break;
                case "3":
                    System.out.println("Introduce el ID de la pista a actualizar:");
                    int pistaId = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Introduce el nuevo nombre de la pista:");
                    nombre = sc.nextLine();

                    System.out.println("Introduce el nuevo precio por hora de la pista:");
                    double precioHora = sc.nextDouble();
                    sc.nextLine(); 
                    
                    System.out.println("Introduce el nuevo tipo de la pista (PADEL, TENIS, FUTBOL):");
                    String tipoStr = sc.nextLine();
                    Tipo tipo = Tipo.valueOf(tipoStr.toUpperCase());
                    
                    Instalaciones pistaActualizar = new Instalaciones(pistaId, nombre, precioHora, tipo);
                    SQLAccessReserva.updatePista(pistaActualizar);
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

                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del 1 al 8.");
                    break;
            }

        } while (!opcion.equals("8"));

    }
}
