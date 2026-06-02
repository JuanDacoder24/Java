package com.example.eventapp;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Entrada implements Serializable {

    private String numeroEntrada;
    private LocalDateTime fechaCompra;
    private Asistente asistente;
    private Evento evento;

    public Entrada() {
    }

    public Entrada(String numeroEntrada, LocalDateTime fechaCompra, Asistente asistente, Evento evento) {
        this.numeroEntrada = numeroEntrada;
        this.fechaCompra = fechaCompra;
        this.asistente = asistente;
        this.evento = evento;
    }

    public String getNumeroEntrada() {
        return numeroEntrada;
    }

    public void setNumeroEntrada(String numeroEntrada) {
        this.numeroEntrada = numeroEntrada;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Asistente getAsistente() {
        return asistente;
    }

    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "Entrada{" +
                "numeroEntrada='" + numeroEntrada + '\'' +
                ", fechaCompra=" + fechaCompra +
                ", asistente=" + asistente +
                ", evento=" + evento +
                '}';
    }
}
