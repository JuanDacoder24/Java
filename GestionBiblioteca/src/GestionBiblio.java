import java.io.Serial;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class GestionBiblio {

    @Serial
    private static final long serialVersionUID = 941122121348359747L;

    private String cif;
    private String direccion;
    private LocalDate fechaAlta;

    private List<Libro> librosRegistrados;
    private List<Cliente> clientesRegistrados;

    public GestionBiblio(String cif, String direccion) {
        this.cif = cif;
        this.direccion = direccion;
        this.fechaAlta = LocalDate.now();
        this.librosRegistrados = new LinkedList<>();
        this.clientesRegistrados = new LinkedList<>();
    }

    public String getCif() {
        return cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public List<Libro> getLibrosRegistrados() {
        return librosRegistrados;
    }

    public List<Cliente> getClientesRegistrados() {
        return clientesRegistrados;
    }

    public void devolverLibro(Cliente cliente, Libro libro) {
        if (cliente == null || libro == null) {
            return;
        }
        // solo procede si el cliente tiene ese libro en su lista
        if (clientesRegistrados.contains(cliente) && cliente.getLibros().contains(libro)) {
            cliente.removeLibro(libro);
            libro.setDisponible(true);
        }
    }

    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            return;
        }
        if (!clientesRegistrados.contains(cliente)) {
            clientesRegistrados.add(cliente);
        }
    }

    public void registrarLibro(Libro libro) {
        if (libro == null) {
            return;
        }
        if (!librosRegistrados.contains(libro)) {
            librosRegistrados.add(libro);
        }
    }

    public void darDeBajaCliente(Cliente cliente) {
        if (cliente == null) {
            return;
        }
        if (clientesRegistrados.contains(cliente)) {
            // antes de eliminarlo, devolvemos todos sus libros prestados
            for (Libro l : new LinkedList<>(cliente.getLibros())) {
                devolverLibro(cliente, l);
            }
            clientesRegistrados.remove(cliente);
        }
    }

    public void alquilarLibro(Cliente cliente, Libro libro) {
        if (cliente == null || libro == null) {
            return;
        }
        if (!clientesRegistrados.contains(cliente) || !librosRegistrados.contains(libro)) {
            return;
        }
        if (!libro.isDisponible()) {
            return; // ya está prestado
        }
        // marcar prestado y añadir al cliente
        libro.setDisponible(false);
        cliente.addLibro(libro);
    }

}
