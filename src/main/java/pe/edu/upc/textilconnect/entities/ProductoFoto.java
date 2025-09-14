package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ProductoFoto")
public class ProductoFoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProductoFoto;

    @Column(name = "urlProductoFoto", length = 255, nullable = false)
    private String urlProductoFoto;

    @Column(name = "principalProductoFoto", nullable = false)
    private Boolean principalProductoFoto;

    @Column(name = "fechaSubidaProductoFoto", nullable = false)
    private LocalDate fechaSubidaProductoFoto;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    public ProductoFoto() {
    }

    public ProductoFoto(int idProductoFoto, String urlProductoFoto, Boolean principalProductoFoto, LocalDate fechaSubidaProductoFoto, Producto producto) {
        this.idProductoFoto = idProductoFoto;
        this.urlProductoFoto = urlProductoFoto;
        this.principalProductoFoto = principalProductoFoto;
        this.fechaSubidaProductoFoto = fechaSubidaProductoFoto;
        this.producto = producto;
    }

    public int getIdProductoFoto() {
        return idProductoFoto;
    }

    public void setIdProductoFoto(int idProductoFoto) {
        this.idProductoFoto = idProductoFoto;
    }

    public String getUrlProductoFoto() {
        return urlProductoFoto;
    }

    public void setUrlProductoFoto(String urlProductoFoto) {
        this.urlProductoFoto = urlProductoFoto;
    }

    public Boolean getPrincipalProductoFoto() {
        return principalProductoFoto;
    }

    public void setPrincipalProductoFoto(Boolean principalProductoFoto) {
        this.principalProductoFoto = principalProductoFoto;
    }

    public LocalDate getFechaSubidaProductoFoto() {
        return fechaSubidaProductoFoto;
    }

    public void setFechaSubidaProductoFoto(LocalDate fechaSubidaProductoFoto) {
        this.fechaSubidaProductoFoto = fechaSubidaProductoFoto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
