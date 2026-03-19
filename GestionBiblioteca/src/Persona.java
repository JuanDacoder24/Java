import java.io.Serial;

public abstract  class Persona {

    @Serial
    private static final long serialVersionUID = 9223372036854775807L;    

    protected int id;
    protected String nombre;
    protected String dni;

    public Persona(int id, String nombre, String dni ) {
    
    
    this.id = id;
        this.nombre = nombre;
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Persona [id=" + id + ", nombre=" + nombre + ", dni=" + dni + "]";
    }
    
   
}
