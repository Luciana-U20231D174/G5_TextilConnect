package pe.edu.upc.textilconnect.dtos;

public class ProductoListDTO {
    private int idProducto;
    private String nombreProducto;
    private double precioProducto;
    private int stockProducto;
    private String nombreTipoProducto;
    private String nombreUsuario;

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

    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }
    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
