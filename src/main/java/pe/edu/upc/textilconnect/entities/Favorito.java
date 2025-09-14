package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Favorito")
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFavorito;

    @Column(name = "fechaFavorito", nullable = false)
    private LocalDate fechaFavorito;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "idProyecto")
    private Proyecto proyecto;

    public Favorito() {
    }

    public Favorito(int idFavorito, LocalDate fechaFavorito, Usuario usuario, Producto producto, Proyecto proyecto) {
        this.idFavorito = idFavorito;
        this.fechaFavorito = fechaFavorito;
        this.usuario = usuario;
        this.producto = producto;
        this.proyecto = proyecto;
    }

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
