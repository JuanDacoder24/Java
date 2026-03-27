package com.example;

public class Instalaciones {

    private int id;
    private String nombre;
    private double precioHora;
    private Tipo tipo;

    public Instalaciones(int id, String nombre, double precioHora, Tipo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.precioHora = precioHora;
        this.tipo = tipo;
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

    public double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(double precioHora) {
        this.precioHora = precioHora;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Instalaciones [id=" + id + ", nombre=" + nombre + ", precioHora=" + precioHora + ", tipo=" + tipo + "]";
    }



}
