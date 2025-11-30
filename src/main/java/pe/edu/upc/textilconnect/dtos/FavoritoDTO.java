package pe.edu.upc.textilconnect.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.entities.Proyecto;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.time.LocalDate;

public class FavoritoDTO {
    private int idFavorito;
    private LocalDate fechaFavorito;

    @JsonIgnoreProperties({
            "roles","password","telefonoUsuario","direccionUsuario","fechaRegistroUsuario",
            "promedioCalificacion","totalCalificacion","enabled","username","emailUsuario",
            "hibernateLazyInitializer","handler"
    })
    private Usuario usuario;

    @JsonIgnoreProperties({
            "usuario","descripcionProducto","stockProducto","colorProducto",
            "medidaProducto","categoriaProducto","disponibleProducto","urlTipoProducto",
            "hibernateLazyInitializer","handler"
    })
    private Producto producto;

    @JsonIgnoreProperties({
            "usuario","descripcionProyecto","urlProyecto","visibleProyecto",
            "hibernateLazyInitializer","handler"
    })
    private Proyecto proyecto;

    public FavoritoDTO() {}

    public int getIdFavorito() { return idFavorito; }
    public void setIdFavorito(int idFavorito) { this.idFavorito = idFavorito; }

    public LocalDate getFechaFavorito() { return fechaFavorito; }
    public void setFechaFavorito(LocalDate fechaFavorito) { this.fechaFavorito = fechaFavorito; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Proyecto getProyecto() { return proyecto; }
    public void setProyecto(Proyecto proyecto) { this.proyecto = proyecto; }
}
