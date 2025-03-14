
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args){
        
        Scanner teclado = new Scanner(System.in);
        String opcion1 = "";
        String opcion2 = "";

        Empresa empresa = new Empresa();

        String nombre;
        String fechaNacimiento;
        String dni;
        String direccion;
        String cif;
 
        System.out.println("Bienvenido");
        System.out.println("Empezaremos creando la empresa");
        System.out.println("Introduce el nombre de la empresa");
        nombre = teclado.nextLine();
        try {
            System.out.print("Introduzca el CIF para el registro de la empresa: ");
            cif = teclado.nextLine().toUpperCase();
    
            //exception personalizada, esta en la parte inferior 
            cifIncorrecto(cif);
            LocalDate fechaFundacion = LocalDate.now();
            empresa = new Empresa(nombre, cif, fechaFundacion);
            System.out.println("Empresa creada correctamente");
        } catch (CifIncorrecto e){
            System.out.println(e.getMessage());
        }
        

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

                            try {
                                System.out.println("Inserta el nombre");
                                nombre = teclado.nextLine();
                                System.out.println("Ingrese la fecha de nacimiento");
                                fechaNacimiento = teclado.nextLine();
                                do {
                                    System.out.print("Introduzca el DNI del director: ");
                                    dni = teclado.nextLine().toUpperCase();
                                    if (setValidDni(dni)) {
                                        System.out.println("DNI válido.");
                                    } else {
                                        System.out.println("DNI incorrecto, inténtelo otra vez.");
                                    }
                                } while (!setValidDni(dni));
                                System.out.println("Ingrese la direccion");
                                direccion = teclado.nextLine();
                                empresa.registrarDirector(nombre, fechaNacimiento, dni, direccion);
                            } catch (NullPointerException e) {
                            }

                            break;

                        case "b":
                           try {
                                System.out.println("Inserta el nombre");
                                nombre = teclado.nextLine();
                                System.out.println("Ingrese la fecha de nacimiento");
                                fechaNacimiento = teclado.nextLine();
                                do {
                                    System.out.print("Introduzca el DNI del gerente: ");
                                    dni = teclado.nextLine().toUpperCase();
                                    if (setValidDni(dni)) {
                                        System.out.println("DNI válido.");
                                    } else {
                                        System.out.println("DNI incorrecto, inténtelo otra vez.");
                                    }
                                } while (!setValidDni(dni));
                                System.out.println("Ingrese la direccion");
                                direccion = teclado.nextLine();
                                empresa.registrarGerente(nombre, fechaNacimiento, dni, direccion);
                           } catch (NullPointerException e) {
                           }
                            break;

                        case "c":

                            try {
                                System.out.println("Inserta el nombre");
                                nombre = teclado.nextLine();
                                System.out.println("Ingrese la fecha de nacimiento");
                                fechaNacimiento = teclado.nextLine();
                                do {
                                    System.out.print("Introduzca el DNI del trabajador: ");
                                    dni = teclado.nextLine().toUpperCase();
                                    if (setValidDni(dni)) {
                                        System.out.println("DNI válido.");
                                    } else {
                                        System.out.println("DNI incorrecto, inténtelo otra vez.");
                                    }
                                } while (!setValidDni(dni));
                                System.out.println("Ingrese la direccion");
                                direccion = teclado.nextLine();
                                empresa.registrarTrabajador(nombre, fechaNacimiento, dni, direccion);
                            } catch (NullPointerException e) {
                            }
                            break;
                        
                        case "d":
                        System.out.println("Saliendo al menu principal");
                            break;

                        default:
                            
                    }
                } while (!opcion2.equalsIgnoreCase("d"));
                    
                case "2":
                System.out.println("Esta es la informacion hasta el momento");
                System.out.println(empresa.toString());
                    break;

                case "3":
                empresa.numTrabajadores();
                    break;

                case "4":
                    
                    break;

                case "5":
                System.out.println("Ingresa el DNI del trabajador que deseas eliminar");
                dni = teclado.nextLine();
                empresa.eliminarTrabajador(dni);
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

    

    public static boolean setValidCif(String cif) {
        Pattern pat = Pattern.compile("[A-Z]{1}[0-9]{8}");
        Matcher mat = pat.matcher(cif);
        return mat.matches();
    }

    public static boolean setValidDni(String dni) {
        Pattern pat = Pattern.compile("[A-Z]{1}[0-9]{8}");
        Matcher mat = pat.matcher(dni);
        return mat.matches();
    }

    //excepcion personalizada
    public static void cifIncorrecto (String cif) throws CifIncorrecto{
    if (setValidCif(cif)) {
        System.out.println("CIF válido.");
    } else {
        System.out.println("CIF incorrecto, inténtelo otra vez.");
        throw new CifIncorrecto(cif);
    }
    }
}
