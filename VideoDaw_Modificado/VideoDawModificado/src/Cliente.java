import java.time.LocalDate;

public class Cliente {
private String dni;
    private String nombre;
    private String numSocio;
    private String direccion;
    private LocalDate fechaNacimiento;
    private LocalDate fechaBaja;
    private Pelicula [] PeliculasAlquiladas;
    private String validDni;

    public Cliente(String dni, String nombre, String numSocio, String direccion, LocalDate fechaNacimiento,
        LocalDate fechaBaja) {
        this.dni = dni;
        this.nombre = nombre;
        this.numSocio = numSocio;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.PeliculasAlquiladas = new Pelicula[100];
    }

    public Cliente(Pelicula[] PeliculasAlquiladas) {
        this.PeliculasAlquiladas = PeliculasAlquiladas;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumSocio() {
        return numSocio;
    }

    public void setNumSocio(String numSocio) {
        this.numSocio = numSocio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
}

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String mostrarInfoCliente(){
        String infoCliente = String.format("Informacion del cliente - Nombre: %s, DNI: %s", 
        this.nombre, this.dni);
        return infoCliente;
    }

    public String mostrarPeliculasAlquiladas(){
        return mostrarPeliculasAlquiladas();
    }
}
