package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNotificacion;

    @Column(name = "tipoNotificacion", length = 50, nullable = false)
    private String tipoNotificacion;

    @Column(name = "mensajeNotificacion", length = 255, nullable = false)
    private String mensajeNotificacion;

    @Column(name = "fechaNotificacion", nullable = false)
    private LocalDate fechaNotificacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario") // clave foránea
    private Usuario usuario;

    public Notificacion() {
    }

    public Notificacion(int idNotificacion, String tipoNotificacion, String mensajeNotificacion,
                        LocalDate fechaNotificacion, Usuario usuario) {
        this.idNotificacion = idNotificacion;
        this.tipoNotificacion = tipoNotificacion;
        this.mensajeNotificacion = mensajeNotificacion;
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

    public LocalDate getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(LocalDate fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
