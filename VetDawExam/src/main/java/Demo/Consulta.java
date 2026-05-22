package Demo;

import java.io.Serializable;
import java.time.LocalDate;

public class Consulta implements Serializable {
    private LocalDate fecha;
    private int duracion;
    private String observaciones;
    private Mascota mascota;

    // Constructor principal
    public Consulta(LocalDate fecha, int duracion, String observaciones) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.observaciones = observaciones;
    }

    // Constructor alternativo con mascota
    public Consulta(LocalDate fecha, int duracion, String observaciones, Mascota mascota) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.observaciones = observaciones;
        this.mascota = mascota;
    }

    // Constructor copia
    public Consulta(Consulta consulta) {
        this.fecha = consulta.getFecha();
        this.duracion = consulta.getDuracion();
        this.observaciones = consulta.getObservaciones();
        this.mascota = consulta.getMascota();
    }

    // Getters y Setters
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "fecha=" + fecha +
                ", duracion=" + duracion +
                ", observaciones='" + observaciones + '\'' +
                ", mascota=" + (mascota != null ? mascota.getNombre() : "null") +
                '}';
    }
}