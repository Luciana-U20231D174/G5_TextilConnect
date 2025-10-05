package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "ComentarioProyecto")
public class ComentarioProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentarioProyecto;

    @Column(name = "comentarioProyecto",length = 50, nullable = false)
    private String comentarioProyecto;

    @CreationTimestamp
    @Column(name = "fechaComentario", nullable = false, updatable = false)
    private LocalDateTime fechaComentario;

    @ManyToOne
    @JoinColumn(name = "idProyecto")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public ComentarioProyecto() {
    }

    public ComentarioProyecto(int idComentarioProyecto, String comentarioProyecto, LocalDateTime fechaComentario, Proyecto proyecto, Usuario usuario) {
        this.idComentarioProyecto = idComentarioProyecto;
        this.comentarioProyecto = comentarioProyecto;
        this.fechaComentario = fechaComentario;
        this.proyecto = proyecto;
        this.usuario = usuario;
    }

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
