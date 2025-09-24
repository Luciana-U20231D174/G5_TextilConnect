package pe.edu.upc.textilconnect.dtos;

import pe.edu.upc.textilconnect.entities.MetodoPago;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoDTO {
    private int idPedido;
    private String estadoPedido;
    private LocalDate fechaCreacionPedido;
    private LocalDate fechaPagoPedido;
    private BigDecimal totalPedido;
    private Usuario comprador;
    private Usuario vendedor;
    private MetodoPago metodoPago;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public LocalDate getFechaCreacionPedido() {
        return fechaCreacionPedido;
    }

    public void setFechaCreacionPedido(LocalDate fechaCreacionPedido) {
        this.fechaCreacionPedido = fechaCreacionPedido;
    }

    public LocalDate getFechaPagoPedido() {
        return fechaPagoPedido;
    }

    public void setFechaPagoPedido(LocalDate fechaPagoPedido) {
        this.fechaPagoPedido = fechaPagoPedido;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
}
