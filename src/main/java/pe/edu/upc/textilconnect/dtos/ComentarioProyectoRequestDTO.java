package pe.edu.upc.textilconnect.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ComentarioProyectoRequestDTO {

    @NotBlank
    @Size(max = 500) // ajusta si quieres 1000 o TEXT en la entity
    private String comentarioProyecto;

    @NotNull
    private Integer idProyecto;

    @NotNull
    private Integer idUsuario;

    // getters & setters
    public String getComentarioProyecto() { return comentarioProyecto; }
    public void setComentarioProyecto(String comentarioProyecto) { this.comentarioProyecto = comentarioProyecto; }

    public Integer getIdProyecto() { return idProyecto; }
    public void setIdProyecto(Integer idProyecto) { this.idProyecto = idProyecto; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
}
