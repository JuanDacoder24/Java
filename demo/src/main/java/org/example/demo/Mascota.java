package org.example.demo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Mascota implements Serializable {

    private String pasaporte;
    private String nombre;
    private double peso;
    private LocalDateTime fechaNacimiento;
    private Propietario propietario;
    private Tipo tipo;

    public Mascota() {
    }

    public Mascota(String pasaporte, String nombre, double peso, LocalDateTime fechaNacimiento,
                   Propietario propietario, Tipo tipo) {
        this.pasaporte = pasaporte;
        this.nombre = nombre;
        this.peso = peso;
        this.fechaNacimiento = fechaNacimiento;
        this.propietario = propietario;
        this.tipo = tipo;
    }

    public String getPasaporte() { return pasaporte; }
    public void setPasaporte(String pasaporte) { this.pasaporte = pasaporte; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public LocalDateTime getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDateTime fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public Propietario getPropietario() { return propietario; }
    public void setPropietario(Propietario propietario) { this.propietario = propietario; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return "Mascota{" +
                "pasaporte='" + pasaporte + '\'' +
                ", nombre='" + nombre + '\'' +
                ", peso=" + peso +
                ", fechaNacimiento=" + fechaNacimiento +
                ", propietario=" + propietario +
                ", tipo=" + tipo +
                '}';
    }
}
