package com.example;

public class PistaFutbol extends Instalaciones{

    protected int jugadores;

    public PistaFutbol(int id, int jugadores, String nombre, double precioHora, Tipo tipo) {
        super(id, nombre, precioHora, tipo);
        this.jugadores = jugadores;
    }

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {
        this.jugadores = jugadores;
    }



}
