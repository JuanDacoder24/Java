import java.io.Serial;
import java.util.LinkedList;

public class Trabajador  extends Persona{

    @Serial
    private static final long serialVersionUID = 9223372036854775807L;

    protected String contraseña;

    private LinkedList<Libro> libros;

    public Trabajador(int id, String nombre, String dni, String contraseña) {
    
    super(id, nombre, dni);
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }


    @Override
    public String toString() {
        return "Trabajador [contraseña=" + contraseña + "]";
    }
    
}
