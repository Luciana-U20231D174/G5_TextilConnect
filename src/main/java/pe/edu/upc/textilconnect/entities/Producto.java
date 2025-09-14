package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @Column(name = "nombreProducto",length = 100, nullable = false)
    private String nombreProducto;

    @Column(name = "descripcionProducto",length = 100, nullable = false)
    private String descripcionProducto;

    @Column(name = "precioProducto", nullable = false)
    private double precioProducto;

    @Column(name = "stockProducto", nullable = false)
    private int stockProducto;

    @Column(name = "colorProducto",length = 100, nullable = false)
    private String colorProducto;

    @Column(name = "medidaProducto",length = 100, nullable = false)
    private String medidaProducto;

    @Column(name = "categoriaProducto",length = 100, nullable = false)
    private String categoriaProducto;

    @Column(name = "disponibleProducto", nullable = false)
    private Boolean disponibleProducto;

    @Column(name = "urlTipoProducto",length = 100, nullable = false)
    private String urlTipoProducto;

    @ManyToOne
    @JoinColumn(name = "idTipoProducto")
    private TipoProducto tipoProducto;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Producto() {
    }

    public Producto(int idProducto, String nombreProducto, String descripcionProducto, double precioProducto, int stockProducto, String colorProducto, String medidaProducto, String categoriaProducto, Boolean disponibleProducto, String urlTipoProducto, TipoProducto tipoProducto, Usuario usuario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
        this.colorProducto = colorProducto;
        this.medidaProducto = medidaProducto;
        this.categoriaProducto = categoriaProducto;
        this.disponibleProducto = disponibleProducto;
        this.urlTipoProducto = urlTipoProducto;
        this.tipoProducto = tipoProducto;
        this.usuario = usuario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public String getColorProducto() {
        return colorProducto;
    }

    public void setColorProducto(String colorProducto) {
        this.colorProducto = colorProducto;
    }

    public String getMedidaProducto() {
        return medidaProducto;
    }

    public void setMedidaProducto(String medidaProducto) {
        this.medidaProducto = medidaProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public Boolean getDisponibleProducto() {
        return disponibleProducto;
    }

    public void setDisponibleProducto(Boolean disponibleProducto) {
        this.disponibleProducto = disponibleProducto;
    }

    public String getUrlTipoProducto() {
        return urlTipoProducto;
    }

    public void setUrlTipoProducto(String urlTipoProducto) {
        this.urlTipoProducto = urlTipoProducto;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
