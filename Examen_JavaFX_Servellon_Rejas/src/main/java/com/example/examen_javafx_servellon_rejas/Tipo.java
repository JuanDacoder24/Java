package com.example.examen_javafx_servellon_rejas;

import java.io.Serializable;

public class Tipo implements Serializable {

    private int idTipo;
    private String tipo;

    public Tipo(){

    }

    public Tipo(int idTipo, String tipo) {
        this.idTipo = idTipo;
        this.tipo = tipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Tipo{" +
                "idTipo=" + idTipo +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
