package pe.edu.upc.textilconnect.dtos;

import java.time.LocalDateTime;

public class ComentarioProyectoResponseDTO {
    private int idComentarioProyecto;
    private String comentarioProyecto;
    private LocalDateTime fechaComentario;
    private int idProyecto;
    private int idUsuario;

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

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
