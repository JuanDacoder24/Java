package com.example.eventapp;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Evento implements Serializable {

    private String codigoInterno;
    private String titulo;
    private LocalDateTime fechaHora;
    private int aforoMaximo;
    private double precioEntrada;
    private Asistente organizador;


    public Evento() {
    }

    public Evento(String codigoInterno, String titulo, LocalDateTime fechaHora, int aforoMaximo, double precioEntrada, Asistente organizador) {
        this.codigoInterno = codigoInterno;
        this.titulo = titulo;
        this.fechaHora = fechaHora;
        this.aforoMaximo = aforoMaximo;
        this.precioEntrada = precioEntrada;
        this.organizador = organizador;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public double getPrecioEntrada() {
        return precioEntrada;
    }

    public void setPrecioEntrada(double precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    public Asistente getOrganizador() {
        return organizador;
    }

    public void setOrganizador(Asistente organizador) {
        this.organizador = organizador;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "codigoInterno='" + codigoInterno + '\'' +
                ", titulo='" + titulo + '\'' +
                ", fechaHora=" + fechaHora +
                ", aforoMaximo=" + aforoMaximo +
                ", precioEntrada=" + precioEntrada +
                ", organizador=" + organizador +
                '}';
    }
}
