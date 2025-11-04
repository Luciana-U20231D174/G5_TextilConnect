package pe.edu.upc.textilconnect.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pe.edu.upc.textilconnect.entities.Usuario;

public class RolDTO {
    private Integer idRol;
    private String nombre;

    @JsonIgnoreProperties({
            "roles",
            "username",
            "password",
            "nombreUsuario",
            "emailUsuario",
            "telefonoUsuario",
            "direccionUsuario",
            "fechaRegistroUsuario",
            "enabled",
            "promedioCalificacion",
            "totalCalificacion"
    })
    private Usuario usuario;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
