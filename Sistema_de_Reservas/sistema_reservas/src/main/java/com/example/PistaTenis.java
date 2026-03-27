package com.example;

public class PistaTenis extends Instalaciones {

    protected String superficie;

    public PistaTenis(int id, String superficie, String nombre, double precioHora, Tipo tipo) {
        super(id, nombre, precioHora, tipo);
        this.superficie = superficie;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

}
