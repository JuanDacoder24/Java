package Demo;

import java.io.Serializable;

public class Propietario implements Serializable {

    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private String direccion;
    private String email;

    public Propietario(String nombre, String apellidos, String dni, String telefono, String direccion, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

    public Propietario(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                        ", apellidos='" + apellidos + '\'' +
                        ", dni='" + dni + '\'' +
                        ", telefono='" + telefono + '\'' +
                        ", direccion='" + direccion + '\'' +
                        ", email='" + email ;
    }
}
