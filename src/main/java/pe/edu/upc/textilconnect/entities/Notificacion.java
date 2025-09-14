package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotificacion;

    @Column(name = "tipoNotificacion", nullable = false)
    private String tipoNotificacion;

    @Column(name = "mensajeNotificacion",length = 200, nullable = false)
    private String mensajeNotificacion;

    @Column(name = "estadoNotificacion", length = 20, nullable = false)
    private String estadoNotificacion;

    @Column(name = "fechaNotificacion", nullable = false)
    private LocalDateTime fechaNotificacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Notificacion() {
    }

    public Notificacion(int idNotificacion, String tipoNotificacion, String mensajeNotificacion, String estadoNotificacion, LocalDateTime fechaNotificacion, Usuario usuario) {
        this.idNotificacion = idNotificacion;
        this.tipoNotificacion = tipoNotificacion;
        this.mensajeNotificacion = mensajeNotificacion;
        this.estadoNotificacion = estadoNotificacion;
        this.fechaNotificacion = fechaNotificacion;
        this.usuario = usuario;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public String getMensajeNotificacion() {
        return mensajeNotificacion;
    }

    public void setMensajeNotificacion(String mensajeNotificacion) {
        this.mensajeNotificacion = mensajeNotificacion;
    }

    public String getEstadoNotificacion() {
        return estadoNotificacion;
    }

    public void setEstadoNotificacion(String estadoNotificacion) {
        this.estadoNotificacion = estadoNotificacion;
    }

    public LocalDateTime getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(LocalDateTime fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
