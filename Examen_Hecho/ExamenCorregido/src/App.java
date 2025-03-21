import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner teclado = new Scanner(System.in);
        String opcion1 = "";
        String opcion2 = "";

        String nombre;
        String fechaNacimiento;
        String dni;
        String direccion;
        String cif;

        String nombreEmpresa;
        LocalDate fFundacion;
 
        System.out.println("Bienvenido");
        System.out.println("Empezaremos creando la empresa");
        System.out.println("Introduce el nombre de la empresa");
        nombre = teclado.nextLine();
        

        do { 
            System.out.println("1. Registrar trabajador en empresa");
            System.out.println("2. Mostrar informacion general de la empresa");
            System.out.println("3. Mostrar el número de trabajadores actuales y el organigrama de la empresa");
            System.out.println("4. Mostrar información de un departamento");
            System.out.println("5. Eliminar trabajador de la empresa");
            System.out.println("6. Agenda Director");
            System.out.println("7. Salir de la aplicacion");
            teclado = new Scanner(System.in);
            opcion1 = teclado.nextLine();

            switch (opcion1) {
                case "1":
                do { 
                    System.out.println("A quien deseas registrar");
                    System.out.println("a. Registrar director");
                    System.out.println("b. Registrar GerenteDep");
                    System.out.println("c. Registrar Trabajador");
                    System.out.println("d. Salir al menu principal");
                    teclado = new Scanner(System.in);
                    opcion2 = teclado.nextLine();

                    switch (opcion2) {
                        case "a":
                        System.out.println("Ingresar el nombre");
                        nombre = teclado.nextLine();
                            break;

                        case "b":
                        System.out.println("Ingresar el nombre");
                        nombre = teclado.nextLine();
                            break;

                        case "c":
                        System.out.println("Ingresar el nombre");
                        nombre = teclado.nextLine();
                            break;
                        
                        case "d":
                        System.out.println("Saliendo al menu principal");
                            break;

                        default:
                            
                    }
                } while (!opcion2.equalsIgnoreCase("d"));
                    
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
                System.out.println("Saliendo del menu");
                    break;
                default:
                System.out.println("Ingrese el numero correcto");
                    
            }
        } while (!opcion1.equals("7"));

    }
}
