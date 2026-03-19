import java.io.Serial;
import java.util.LinkedList;

public class Cliente extends Persona{
    
    @Serial
    private static final long serialVersionUID = 9223372036854775807L;

    protected String email;
    protected String telefono;
    protected double edad;

    private LinkedList<Libro> libros;

    public Cliente(int id, String nombre, String dni, String email, String telefono, double edad) {
        super(id, nombre, dni);
        this.email = email;
        this.telefono = telefono;
        this.edad = edad;

        // initialize the list of borrowed books
        this.libros = new LinkedList<>();
    }

    /**
     * Devuelve la lista de libros que tiene actualmente en préstamo el cliente.
     */
    public LinkedList<Libro> getLibros() {
        return libros;
    }

    /**
     * Añade un libro al historial de préstamos del cliente.
     */
    public void addLibro(Libro libro) {
        if (libro != null) {
            libros.add(libro);
        }
    }

    /**
     * Elimina un libro de los préstamos del cliente.
     */
    public void removeLibro(Libro libro) {
        libros.remove(libro);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Cliente [email=" + email + ", telefono=" + telefono + ", edad=" + edad + "]";
    }
}
