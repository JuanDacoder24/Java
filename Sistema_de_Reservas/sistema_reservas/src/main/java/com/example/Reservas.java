package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservas {

    private int id;
    private int usuarioId;
    private int instalacionId;
    private LocalDate fecha;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Estado estado;
    
    public Reservas(int id, int usuarioId, int instalacionId, LocalDate fecha, LocalDateTime horaInicio,
            LocalDateTime horaFin, Estado estado) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.instalacionId = instalacionId;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getInstalacionId() {
        return instalacionId;
    }

    public void setInstalacionId(int instalacionId) {
        this.instalacionId = instalacionId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "\n Reservas [id=" + id + ", usuarioId=" + usuarioId + ", instalacionId=" + instalacionId + ", fecha="
                + fecha + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", estado=" + estado + "]";
    }



}
