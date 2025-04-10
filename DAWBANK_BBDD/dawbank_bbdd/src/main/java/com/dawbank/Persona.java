package com.dawbank;

import java.util.Date;

public class Persona {
    protected String nombre;
    protected String dni;
    protected Date fechaNacimiento;
    
    public Persona(String nombre, String dni, Date fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + "]";
    }
}

