package pe.edu.upc.textilconnect.dtos;

import java.time.LocalDate;

public class ProyectoListDTO {
    private String tituloProyecto;
    private String descripcionProyecto;
    private LocalDate fechaCreacion;

    public ProyectoListDTO(String tituloProyecto, String descripcionProyecto, LocalDate fechaCreacion) {
        this.tituloProyecto = tituloProyecto;
        this.descripcionProyecto = descripcionProyecto;
        this.fechaCreacion = fechaCreacion;
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
