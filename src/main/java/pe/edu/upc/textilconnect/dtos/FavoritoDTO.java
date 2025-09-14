package pe.edu.upc.textilconnect.dtos;

import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.entities.Proyecto;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.time.LocalDate;

public class FavoritoDTO {
    private int idFavorito;
    private LocalDate fechaFavorito;
    private Usuario usuario;
    private Producto producto;
    private Proyecto proyecto;

    public int getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(int idFavorito) {
        this.idFavorito = idFavorito;
    }

    public LocalDate getFechaFavorito() {
        return fechaFavorito;
    }

    public void setFechaFavorito(LocalDate fechaFavorito) {
        this.fechaFavorito = fechaFavorito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
