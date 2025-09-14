package pe.edu.upc.textilconnect.dtos;

import pe.edu.upc.textilconnect.entities.TipoProyecto;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.time.LocalDate;

public class ProyectoDTO {
    private int idProyecto;
    private String tituloProyecto;
    private String descripcionProyecto;
    private String urlProyecto;
    private String visibleProyecto;
    private LocalDate fechaCreacion;
    private TipoProyecto tipoProyecto;
    private Usuario usuario;

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
