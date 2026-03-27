package com.example;

public class PistaPadel extends Instalaciones {

    protected boolean cristal;

    public PistaPadel(int id, boolean cristal, String nombre, double precioHora, Tipo tipo) {
        super(id, nombre, precioHora, tipo);
        this.cristal = cristal;
    }

    public boolean isCristal() {
        return cristal;
    }

    public void setCristal(boolean cristal) {
        this.cristal = cristal;
    }

}
