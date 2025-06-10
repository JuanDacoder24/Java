package com.example.examen_recuperacion;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Consulta implements Serializable {

    private static final long serialVersionUID = -5879006413461670871L;

    private LocalDateTime fecha;
    private int duracion;
    private String observaciones;
    private Mascota mascota;

    public Consulta(LocalDateTime fecha, int duracion, String observaciones, Mascota mascota) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.observaciones = observaciones;
        this.mascota = mascota;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Mascota getMascota() {
        return mascota;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "fecha=" + fecha +
                ", duracion=" + duracion +
                ", observaciones='" + observaciones +
                ", mascota=" + mascota.getNombre() + "-" + mascota.getPasaporte() +
                ", propietario=" + mascota.getPropietario().getNombre() + "-" + mascota.getPropietario().getDni() +
                '}';
    }
}
