
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner teclado = new Scanner(System.in);
        Animal animal;
        Cita cita = new Cita();

        String nombre, especie, lugar, motivo, propietario;
        int edad;

        Semana dia;

        String opcion = "";

        do { 
            System.out.println("1. Registrar animal");
            System.out.println("2. Agendar cita");
            System.out.println("3. Mostrar citas");
            System.out.println("4. Mostrar animales registrados");
            System.out.println("4. Salir");
            teclado = new Scanner(System.in);
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Ingrese el nombre del animal: ");
                    nombre = teclado.nextLine();
                    System.out.println("Ingrese la edad del animal: ");
                    edad = Integer.parseInt(teclado.nextLine());
                    System.out.println("Ingrese la especie del animal: ");
                    especie = teclado.nextLine();
                    animal = new Animal(nombre, edad, especie);
                    System.out.println("Animal registrado: " + animal.toString());
                    cita.registrarAnimal(animal);
                    break;
                    
                case "2":
                    System.out.println("Ingrese el nombre del propietario: ");
                    propietario = teclado.nextLine();
                    System.out.println("Ingrese el lugar de la cita: ");
                    lugar = teclado.nextLine();
                    System.out.println("Ingrese el motivo de la cita: ");
                    motivo = teclado.nextLine();
                    System.out.println("Ingrese el dia de la cita (LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO): ");
                    dia = Semana.valueOf(teclado.nextLine().toUpperCase());
                    System.out.println("Ingrese la hora de la cita (YYYY-MM-DDTHH:MM): ");
                    String fechaHora = teclado.nextLine();
                    
                    
                    break;
                case "3":
                    // Mostrar citas
                    break;
                case "4":
                    
                    break;
                case "5":
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion.equalsIgnoreCase("5") == false);
    }
}
