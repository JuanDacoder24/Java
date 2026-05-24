package org.example.demo;


import java.time.LocalDateTime;

public class Consulta {

    private int idConsulta;
    private LocalDateTime fecha;
    private int duracion;
    private String observaciones;
    private Mascota mascota;

    public Consulta() {
    }

    public Consulta(int idConsulta, LocalDateTime fecha, int duracion, String observaciones, Mascota mascota) {
        this.idConsulta = idConsulta;
        this.fecha = fecha;
        this.duracion = duracion;
        this.observaciones = observaciones;
        this.mascota = mascota;
    }

    public int getIdConsulta() { return idConsulta; }
    public void setIdConsulta(int idConsulta) { this.idConsulta = idConsulta; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public Mascota getMascota() { return mascota; }
    public void setMascota(Mascota mascota) { this.mascota = mascota; }

    @Override
    public String toString() {
        return "Consulta{" +
                "idConsulta=" + idConsulta +
                ", fecha=" + fecha +
                ", duracion=" + duracion +
                ", observaciones='" + observaciones + '\'' +
                ", mascota=" + mascota +
                '}';
    }
}

