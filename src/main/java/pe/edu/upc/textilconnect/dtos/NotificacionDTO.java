package pe.edu.upc.textilconnect.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.time.LocalDate;

public class NotificacionDTO {
    private int idNotificacion;
    private String tipoNotificacion;
    private String mensajeNotificacion;
    private LocalDate fechaNotificacion;

    @JsonIgnoreProperties({
            "roles","password","telefonoUsuario","direccionUsuario","fechaRegistroUsuario",
            "promedioCalificacion","totalCalificacion","enabled","username","emailUsuario"
    })
    private Usuario usuario;

    public NotificacionDTO() {}

    public int getIdNotificacion() { return idNotificacion; }
    public void setIdNotificacion(int idNotificacion) { this.idNotificacion = idNotificacion; }

    public String getTipoNotificacion() { return tipoNotificacion; }
    public void setTipoNotificacion(String tipoNotificacion) { this.tipoNotificacion = tipoNotificacion; }

    public String getMensajeNotificacion() { return mensajeNotificacion; }
    public void setMensajeNotificacion(String mensajeNotificacion) { this.mensajeNotificacion = mensajeNotificacion; }

    public LocalDate getFechaNotificacion() { return fechaNotificacion; }
    public void setFechaNotificacion(LocalDate fechaNotificacion) { this.fechaNotificacion = fechaNotificacion; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
