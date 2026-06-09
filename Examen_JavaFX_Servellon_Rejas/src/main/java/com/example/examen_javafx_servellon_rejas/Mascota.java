package com.example.examen_javafx_servellon_rejas;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Mascota implements Serializable {

    private String pasaporte;
    private String nombre;
    private LocalDateTime fechaNacimiento;
    private double peso;
    private Tipo tipo;
    private Propietario propietario;

    public Mascota(){

    }

    public Mascota(String pasaporte, String nombre, LocalDateTime fechaNacimiento, double peso, Tipo tipo, Propietario propietario) {
        this.pasaporte = pasaporte;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.tipo = tipo;
        this.propietario = propietario;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "pasaporte='" + pasaporte + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", peso=" + peso +
                ", tipo=" + tipo +
                ", propietario=" + propietario +
                '}';
    }
}
