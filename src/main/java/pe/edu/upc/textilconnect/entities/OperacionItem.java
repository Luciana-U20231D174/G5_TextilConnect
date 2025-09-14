package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "OperacionItem")
public class OperacionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOperacionItem;

    @Column(name = "cantidadOperacionItem", length = 20, nullable = false)
    private int cantidadOperacionItem;

    @Column(name = "precioOperacionItem", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioOperacionItem;

    @ManyToOne
    @JoinColumn(name = "idOperacion")
    private Operacion operacion;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    public OperacionItem() {
    }

    public OperacionItem(int idOperacionItem, int cantidadOperacionItem, BigDecimal precioOperacionItem, Operacion operacion, Producto producto) {
        this.idOperacionItem = idOperacionItem;
        this.cantidadOperacionItem = cantidadOperacionItem;
        this.precioOperacionItem = precioOperacionItem;
        this.operacion = operacion;
        this.producto = producto;
    }

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
