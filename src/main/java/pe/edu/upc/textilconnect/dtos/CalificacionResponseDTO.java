package pe.edu.upc.textilconnect.dtos;

import java.time.LocalDate;

public class CalificacionResponseDTO {
    private int idCalificacion;
    private int estrellas;
    private String comentario;
    private LocalDate fechaCalificacion;

    private int idPedido;
    private int idCalificador;
    private int idCalificado;

    // Getters & Setters
    public int getIdCalificacion() { return idCalificacion; }
    public void setIdCalificacion(int idCalificacion) { this.idCalificacion = idCalificacion; }

    public int getEstrellas() { return estrellas; }
    public void setEstrellas(int estrellas) { this.estrellas = estrellas; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDate getFechaCalificacion() { return fechaCalificacion; }
    public void setFechaCalificacion(LocalDate fechaCalificacion) { this.fechaCalificacion = fechaCalificacion; }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdCalificador() { return idCalificador; }
    public void setIdCalificador(int idCalificador) { this.idCalificador = idCalificador; }

    public int getIdCalificado() { return idCalificado; }
    public void setIdCalificado(int idCalificado) { this.idCalificado = idCalificado; }
}