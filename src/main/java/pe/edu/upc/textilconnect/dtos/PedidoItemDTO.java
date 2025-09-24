package pe.edu.upc.textilconnect.dtos;

import pe.edu.upc.textilconnect.entities.Pedido;
import pe.edu.upc.textilconnect.entities.Producto;

import java.math.BigDecimal;

public class PedidoItemDTO {
    private int idPedidoItem;
    private int cantidadPedidoItem;
    private BigDecimal precioPedidoItem;
    private Pedido pedido;
    private Producto producto;

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
