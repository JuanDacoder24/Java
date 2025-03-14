
import java.time.LocalDate;
import java.util.LinkedList;

public class Empresa {
    private String nombre;
    private String cif;
    private LocalDate fechaFundacion;
    private int contador;

    LinkedList<Trabajador> trabajadores;

    //creo un constructor vacio para iniciarlo en la clase principal
    public Empresa() {
        this.trabajadores = new LinkedList<>();
        this.contador = 0;
    }

    public Empresa(String nombre, String cif, LocalDate fechaFundacion, LinkedList<Trabajador> trabajadores) {
        this.nombre = nombre;
        this.cif = cif;
        this.fechaFundacion = fechaFundacion;
        this.trabajadores = trabajadores;
    }

    public Empresa(String nombre, String cif, LocalDate fechaFundacion) {
        this.nombre = nombre;
        this.cif = cif;
        this.fechaFundacion = LocalDate.now();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(LocalDate fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public LinkedList<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(LinkedList<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }

    public boolean registrarTrabajador(String nombre, String fechaNacimiento, String dni, String direccion){
        for (Trabajador Trabajador : trabajadores) {
            if(Trabajador.getDni().equals(dni)){
                System.out.println("Este trabajador ya existe");
                return false;
            }
        }
        Trabajador nvTrabajador = new Trabajador(nombre, fechaNacimiento, dni, direccion);
            trabajadores.add(nvTrabajador);
            System.out.println("Trabajador registrado correctamente");
            contador++;
            return true;
    }

    
    public boolean  registrarGerente(String nombre, String fechaNacimiento, String dni, String direccion){
        for (Trabajador Gerente : trabajadores) {
            if(Gerente.getDni().equals(dni)){
                System.out.println("Este trabajador ya existe");
                return false;
            }
        }
        Trabajador nvGerente = new Trabajador(nombre, fechaNacimiento, dni, direccion);
            trabajadores.add(nvGerente);
            System.out.println("Gerente registrado correctamente");
            contador++;
            return true;
    }                                                                                                             

    public boolean  registrarDirector(String nombre, String fechaNacimiento, String dni, String direccion){
        for (Trabajador Director : trabajadores) {
            if(Director.getDni().equals(dni)){
                System.out.println("Este trabajador ya existe");
                return false;
            }
        }
        Trabajador nvDirector = new Trabajador(nombre, fechaNacimiento, dni, direccion);
            trabajadores.add(nvDirector);
            System.out.println("Director registrado correctamente");
            contador++;
            return true;
    }    
    

    public boolean  eliminarTrabajador(String dni){
        for (Trabajador eliminarTrabajador : trabajadores) {
            if(eliminarTrabajador.getDni().equals(dni)){
                trabajadores.remove(eliminarTrabajador);
                return true;
            }else{
                System.out.println("Trabajador no encontrado");
            }
        }
        return false;
    }

    public void numTrabajadores(){
        if(trabajadores.isEmpty()){
            System.out.println("No hay trabajadores en la empresa");
            return;
        }
        for (Trabajador trabajador : trabajadores) {
            System.out.println("Nombre: " + trabajador.getNombre() + "DNI: " + trabajador.getDni());
        }
    }

    @Override
    public String toString() {
        return "Empresa [nombre=" + nombre + ", cif=" + cif + ", fechaFundacion=" + fechaFundacion + ", trabajadores="
                + trabajadores + "]";
    }

    
}
