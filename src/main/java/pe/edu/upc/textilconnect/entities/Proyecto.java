package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name="Proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProyecto;

    @Column(name = "tituloProyecto",length = 100, nullable = false)
    private String tituloProyecto;

    @Column(name = "descripcionProyecto" ,length = 100, nullable = false)
    private String descripcionProyecto;

    @Column(name = "urlProyecto",length = 100, nullable = false)
    private String urlProyecto;

    @Column(name = "visibleProyecto",length = 100, nullable = false)
    private String visibleProyecto;

    @Column(name = "fechaCreacion", nullable = false)
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idTipoProyecto")
    private TipoProyecto tipoProyecto;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Proyecto() {
    }

    public Proyecto(int idProyecto, String tituloProyecto, String descripcionProyecto, String urlProyecto, String visibleProyecto, LocalDate fechaCreacion, TipoProyecto tipoProyecto, Usuario usuario) {
        this.idProyecto = idProyecto;
        this.tituloProyecto = tituloProyecto;
        this.descripcionProyecto = descripcionProyecto;
        this.urlProyecto = urlProyecto;
        this.visibleProyecto = visibleProyecto;
        this.fechaCreacion = fechaCreacion;
        this.tipoProyecto = tipoProyecto;
        this.usuario = usuario;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getTituloProyecto() {
        return tituloProyecto;
    }

    public void setTituloProyecto(String tituloProyecto) {
        this.tituloProyecto = tituloProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public String getUrlProyecto() {
        return urlProyecto;
    }

    public void setUrlProyecto(String urlProyecto) {
        this.urlProyecto = urlProyecto;
    }

    public String getVisibleProyecto() {
        return visibleProyecto;
    }

    public void setVisibleProyecto(String visibleProyecto) {
        this.visibleProyecto = visibleProyecto;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TipoProyecto getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(TipoProyecto tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
