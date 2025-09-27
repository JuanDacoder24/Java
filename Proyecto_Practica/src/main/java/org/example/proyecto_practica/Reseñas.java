package org.example.proyecto_practica;

import java.time.LocalDate;

public class Reseñas {
    private int id_reseñas;
    private Libros idLibro;
    private Usuario idUsuario;
    private int calificacion;
    private String comentario;
    private LocalDate fecha;

    public Reseñas(int id_reseñas, Libros idLibro, Usuario idUsuario, int calificacion, String comentario, LocalDate fecha) {
        this.id_reseñas = id_reseñas;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public int getId_reseñas() {
        return id_reseñas;
    }

    public void setId_reseñas(int id_reseñas) {
        this.id_reseñas = id_reseñas;
    }

    public Libros getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Libros idLibro) {
        this.idLibro = idLibro;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reseñas{" +
                "id_reseñas=" + id_reseñas +
                ", idLibro=" + idLibro +
                ", idUsuario=" + idUsuario +
                ", calificacion=" + calificacion +
                ", comentario='" + comentario + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
