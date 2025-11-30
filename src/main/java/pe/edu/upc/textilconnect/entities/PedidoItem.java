// src/main/java/pe/edu/upc/textilconnect/entities/PedidoItem.java
package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PedidoItem")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedidoItem;

    @Column(name = "cantidadPedidoItem", length = 20, nullable = false)
    private int cantidadPedidoItem;

    @Column(name = "precioPedidoItem", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioPedidoItem;

    // NUEVO: URL de imagen
    @Column(name = "imagenUrl", length = 500, nullable = true)
    private String imagenUrl;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    public PedidoItem() {
    }

    public PedidoItem(int idPedidoItem,
                      int cantidadPedidoItem,
                      BigDecimal precioPedidoItem,
                      String imagenUrl,
                      Pedido pedido,
                      Producto producto) {
        this.idPedidoItem = idPedidoItem;
        this.cantidadPedidoItem = cantidadPedidoItem;
        this.precioPedidoItem = precioPedidoItem;
        this.imagenUrl = imagenUrl;
        this.pedido = pedido;
        this.producto = producto;
    }

    public int getIdPedidoItem() {
        return idPedidoItem;
    }

    public void setIdPedidoItem(int idPedidoItem) {
        this.idPedidoItem = idPedidoItem;
    }

    public int getCantidadPedidoItem() {
        return cantidadPedidoItem;
    }

    public void setCantidadPedidoItem(int cantidadPedidoItem) {
        this.cantidadPedidoItem = cantidadPedidoItem;
    }

    public BigDecimal getPrecioPedidoItem() {
        return precioPedidoItem;
    }

    public void setPrecioPedidoItem(BigDecimal precioPedidoItem) {
        this.precioPedidoItem = precioPedidoItem;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}