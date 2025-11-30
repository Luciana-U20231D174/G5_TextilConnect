package pe.edu.upc.textilconnect.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pe.edu.upc.textilconnect.entities.TipoProducto;
import pe.edu.upc.textilconnect.entities.Usuario;

public class ProductoDTO {
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double precioProducto;
    private int stockProducto;
    private String colorProducto;
    private String medidaProducto;
    private String categoriaProducto;
    private Boolean disponibleProducto;
    private String urlTipoProducto;
    private TipoProducto tipoProducto;

    @JsonIgnoreProperties({
            "roles","password","telefonoUsuario","direccionUsuario","fechaRegistroUsuario",
            "promedioCalificacion","totalCalificacion","enabled","username","emailUsuario"
    })
    private Usuario usuario;

    public ProductoDTO() {}

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getDescripcionProducto() { return descripcionProducto; }
    public void setDescripcionProducto(String descripcionProducto) { this.descripcionProducto = descripcionProducto; }

    public double getPrecioProducto() { return precioProducto; }
    public void setPrecioProducto(double precioProducto) { this.precioProducto = precioProducto; }

    public int getStockProducto() { return stockProducto; }
    public void setStockProducto(int stockProducto) { this.stockProducto = stockProducto; }

    public String getColorProducto() { return colorProducto; }
    public void setColorProducto(String colorProducto) { this.colorProducto = colorProducto; }

    public String getMedidaProducto() { return medidaProducto; }
    public void setMedidaProducto(String medidaProducto) { this.medidaProducto = medidaProducto; }

    public String getCategoriaProducto() { return categoriaProducto; }
    public void setCategoriaProducto(String categoriaProducto) { this.categoriaProducto = categoriaProducto; }

    public Boolean getDisponibleProducto() { return disponibleProducto; }
    public void setDisponibleProducto(Boolean disponibleProducto) { this.disponibleProducto = disponibleProducto; }

    public String getUrlTipoProducto() { return urlTipoProducto; }
    public void setUrlTipoProducto(String urlTipoProducto) { this.urlTipoProducto = urlTipoProducto; }

    public TipoProducto getTipoProducto() { return tipoProducto; }
    public void setTipoProducto(TipoProducto tipoProducto) { this.tipoProducto = tipoProducto; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}