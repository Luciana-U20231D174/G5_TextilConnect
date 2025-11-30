package pe.edu.upc.textilconnect.dtos;

import pe.edu.upc.textilconnect.entities.Proyecto;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.time.LocalDateTime;

public class ComentarioProyectoDTO {

    private int idComentarioProyecto;
    private String comentarioProyecto;
    private LocalDateTime fechaComentario;

    // Objetos mínimos para enviar id + nombre
    private Proyecto proyecto;
    private Usuario usuario;

    public int getIdComentarioProyecto() {
        return idComentarioProyecto;
    }

    public void setIdComentarioProyecto(int idComentarioProyecto) {
        this.idComentarioProyecto = idComentarioProyecto;
    }

    public String getComentarioProyecto() {
        return comentarioProyecto;
    }

    public void setComentarioProyecto(String comentarioProyecto) {
        this.comentarioProyecto = comentarioProyecto;
    }

    public LocalDateTime getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(LocalDateTime fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
