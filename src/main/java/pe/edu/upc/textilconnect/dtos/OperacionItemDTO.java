package pe.edu.upc.textilconnect.dtos;

import pe.edu.upc.textilconnect.entities.Operacion;
import pe.edu.upc.textilconnect.entities.Producto;

import java.math.BigDecimal;

public class OperacionItemDTO {
    private int idOperacionItem;
    private int cantidadOperacionItem;
    private BigDecimal precioOperacionItem;
    private Operacion operacion;
    private Producto producto;

    public int getIdOperacionItem() {
        return idOperacionItem;
    }

    public void setIdOperacionItem(int idOperacionItem) {
        this.idOperacionItem = idOperacionItem;
    }

    public int getCantidadOperacionItem() {
        return cantidadOperacionItem;
    }

    public void setCantidadOperacionItem(int cantidadOperacionItem) {
        this.cantidadOperacionItem = cantidadOperacionItem;
    }

    public BigDecimal getPrecioOperacionItem() {
        return precioOperacionItem;
    }

    public void setPrecioOperacionItem(BigDecimal precioOperacionItem) {
        this.precioOperacionItem = precioOperacionItem;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
