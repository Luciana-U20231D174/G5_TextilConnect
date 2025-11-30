package pe.edu.upc.textilconnect.dtos;

import java.time.LocalDate;

public class ProyectoListDTO {

    private int idProyecto;
    private String tituloProyecto;
    private String descripcionProyecto;
    private String urlProyecto;
    private String visibleProyecto;
    private LocalDate fechaCreacion;

    // estos son SOLO nombres, no las entidades
    private String tipoProyecto;  // nombre del tipo de proyecto
    private String usuario;       // nombre del usuario

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

    public String getTipoProyecto() {
        return tipoProyecto;
    }

    public void setTipoProyecto(String tipoProyecto) {
        this.tipoProyecto = tipoProyecto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
