package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        String nombre;

        // esto es para verificar que se conecta a la base de datos correctamente
        if(SQLDataManager.getConnection() == null){
            System.out.println("No se pudo conectar a la base de datos. Asegúrate de que el archivo application.dat esté configurado correctamente.");
            return;
        } else {
            System.out.println("Conexión a la base de datos exitosa.");
        }

        System.out.println("------------ Bienvenido a Santander Sports ------------");

        do {
            System.out.println("1. Registrar usuario");
            System.out.println("2. Reservar pista");
            System.out.println("3. Actualizar pista");
            System.out.println("4. Eliminar reserva");
            System.out.println("5. Actualizar reserva");
            System.out.println("6. Ver reservas");
            System.out.println("7. Ver pistas");
            System.out.println("8. Ver usuarios");
            System.out.println("9. Salir");

            sc = new Scanner(System.in);
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Registrando nuevo usuario...");
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
                    System.out.println("Reservando pista...");
                    mostrarUsuarios();
                    System.out.println("Introduce el id del usuario:");
                    int usuario_id = sc.nextInt();
                    sc.nextLine(); 

                    mostrarPistas();
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
                    System.out.println("Actualizando pista...");
                    mostrarPistas();
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
                    System.out.println("Eliminando reserva...");
                    mostrarReservas();
                    System.out.println("Introduce el ID de la reserva a eliminar:");
                    int reservaId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("¿Estás seguro de que quieres eliminar la reserva con ID " + reservaId + "? (s/n):");
                    String confirmacion = sc.nextLine();
                    if (confirmacion.equalsIgnoreCase("s")) {
                        SQLAccessReserva.deleteReservaById(reservaId);
                        System.out.println("Reserva eliminada con éxito.");
                    } else {
                        System.out.println("Eliminación cancelada.");
                    }
                    break;
                case "5":
                    System.out.println("Editando reserva...");
                    mostrarReservas();
                    System.out.println("Introduce el ID de la reserva a editar:");
                    int reservaIdEditar = sc.nextInt();
                    sc.nextLine();

                    Reservas reservaExistente = SQLAccessReserva.getReservaById(reservaIdEditar);
                    if (reservaExistente == null) {
                        System.out.println("Reserva no encontrada.");
                        break;
                    }

                    System.out.println("Introduce la nueva fecha de la reserva (YYYY-MM-DD):");
                    String nuevaFecha = sc.nextLine();

                    System.out.println("Introduce la nueva hora de inicio de la reserva (HH:MM):");
                    String nuevaHoraInicio = sc.nextLine();

                    System.out.println("Introduce la nueva hora de fin de la reserva (HH:MM):");
                    String nuevaHoraFin = sc.nextLine();

                    System.out.println("Introduce el nuevo estado de la reserva (ACTIVA, CANCELADA):");
                    String nuevoEstadoStr = sc.nextLine();
                    Estado nuevoEstado = Estado.valueOf(nuevoEstadoStr.toUpperCase());

                    LocalDate nuevaFechaReserva = LocalDate.parse(nuevaFecha);
                    LocalDateTime nuevoInicio = LocalDateTime.parse(nuevaFecha + "T" + nuevaHoraInicio + ":00");
                    LocalDateTime nuevoFin = LocalDateTime.parse(nuevaFecha + "T" + nuevaHoraFin + ":00");

                    Reservas reservaActualizar = new Reservas(reservaIdEditar, reservaExistente.getUsuarioId(), reservaExistente.getInstalacionId(), nuevaFechaReserva, nuevoInicio, nuevoFin, nuevoEstado);
                    int resultadoEditar = SQLAccessReserva.updateReserva(reservaActualizar);

                    if (resultadoEditar > 0) {
                        System.out.println("Reserva actualizada con éxito.");
                    } else {
                        System.out.println("Error al actualizar la reserva.");
                    }
                    break;
                case "6":
                    System.out.println("Mostrando reservas...");
                    mostrarReservas();
                    break;
                case "7":
                    System.out.println("Mostrando pistas...");
                    mostrarPistas();
                    break;
                case "8":
                    System.out.println("Mostrando usuarios...");
                    mostrarUsuarios();
                    break;
                case "9":
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del 1 al 9.");
                    break;
            }

        } while (!opcion.equals("9"));

    }

    //estos metodos hacen que la lista sea mas "bonita" a la hora de mostrar en la pantalla, tambien sirve para que el momento de insertar un id sepamos de que id estamos hablando  
    private static void mostrarPistas() {
        System.out.println("\n--- Lista de Pistas Disponibles ---");
        List<Instalaciones> pistas = SQLAccessReserva.getPistas();
        for (Instalaciones pista : pistas) {
            System.out.println("ID: " + pista.getId() + " - Nombre: " + pista.getNombre() + " - Tipo: " + pista.getTipo() + " - Precio/hora: " + pista.getPrecioHora());
        }
        System.out.println("-----------------------------------\n");
    }

    private static void mostrarReservas() {
        System.out.println("\n--- Lista de Reservas ---");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<Reservas> reservas = SQLAccessReserva.getReservas();
        for (Reservas reserva : reservas) {
            String horaInicio = reserva.getHoraInicio().format(timeFormatter);
            String horaFin = reserva.getHoraFin().format(timeFormatter);
            System.out.println("ID: " + reserva.getId() + " - Usuario ID: " + reserva.getUsuarioId() + " - Pista ID: " + reserva.getInstalacionId() + 
                             " - Fecha: " + reserva.getFecha() + " - Inicio: " + horaInicio + " - Fin: " + horaFin + 
                             " - Estado: " + reserva.getEstado());
        }
        System.out.println("-------------------------\n");
    }

    private static void mostrarUsuarios() {
        System.out.println("\n--- Lista de Usuarios ---");
        List<Usuarios> usuarios = SQLAccessReserva.getUsuarios();
        for (Usuarios usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + " - Nombre: " + usuario.getNombre() + " - Email: " + usuario.getEmail());
        }
        System.out.println("-------------------------\n");
    }

}
