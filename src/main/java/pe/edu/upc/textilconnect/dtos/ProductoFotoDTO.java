package pe.edu.upc.textilconnect.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pe.edu.upc.textilconnect.entities.Producto;

import java.time.LocalDate;

public class ProductoFotoDTO {
    private int idProductoFoto;
    private String urlProductoFoto;
    private Boolean principalProductoFoto;
    private LocalDate fechaSubidaProductoFoto;

    @JsonIgnoreProperties({
            "usuario","descripcionProducto","stockProducto","colorProducto",
            "medidaProducto","categoriaProducto","disponibleProducto","urlTipoProducto"
    })
    private Producto producto;

    public ProductoFotoDTO() {}

    public int getIdProductoFoto() { return idProductoFoto; }
    public void setIdProductoFoto(int idProductoFoto) { this.idProductoFoto = idProductoFoto; }

    public String getUrlProductoFoto() { return urlProductoFoto; }
    public void setUrlProductoFoto(String urlProductoFoto) { this.urlProductoFoto = urlProductoFoto; }

    public Boolean getPrincipalProductoFoto() { return principalProductoFoto; }
    public void setPrincipalProductoFoto(Boolean principalProductoFoto) { this.principalProductoFoto = principalProductoFoto; }

    public LocalDate getFechaSubidaProductoFoto() { return fechaSubidaProductoFoto; }
    public void setFechaSubidaProductoFoto(LocalDate fechaSubidaProductoFoto) { this.fechaSubidaProductoFoto = fechaSubidaProductoFoto; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
}