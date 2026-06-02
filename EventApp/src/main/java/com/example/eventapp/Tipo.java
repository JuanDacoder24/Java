package com.example.eventapp;

import java.io.Serializable;

public class Tipo implements Serializable {

    private String tipo;

    public Tipo() {
    }

    public Tipo( String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return tipo;
    }


}
