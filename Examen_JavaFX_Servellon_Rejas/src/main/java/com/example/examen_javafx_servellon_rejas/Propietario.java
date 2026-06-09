package com.example.examen_javafx_servellon_rejas;

import java.io.Serializable;

public class Propietario implements Serializable {

    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direcion;
    private String email;

    public Propietario() {
    }

    public Propietario(String dni, String nombre, String apellido, String telefono, String direcion, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direcion = direcion;
        this.email = email;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDirecion() { return direcion; }
    public void setDirecion(String direccion) { this.direcion = direccion; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Propietario{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direcion + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

