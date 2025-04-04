package com.decroly;

public class TipoProducto {

    private int idProducto;
    private String nombreTipo;
    
    public TipoProducto(int idProducto, String nombre) {
        this.idProducto = idProducto;
        this.nombreTipo = nombre;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombre) {
        this.nombreTipo = nombre;
    }


}
