// src/main/java/pe/edu/upc/textilconnect/dtos/PedidoItemDTO.java
package pe.edu.upc.textilconnect.dtos;

import java.math.BigDecimal;

public class PedidoItemDTO {
    private int idPedidoItem;
    private int cantidadPedidoItem;
    private BigDecimal precioPedidoItem;

    // relaciones en formato plano
    private Integer idPedido;
    private Integer idProducto;
    private String nombreProducto;

    // NUEVO: URL de imagen
    private String imagenUrl;

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

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
