package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @Column(name = "estadoPedido", length = 20, nullable = false)
    private String estadoPedido;

    @Column(name = "fechaCreacionPedido", nullable = false)
    private LocalDate fechaCreacionPedido;

    @Column(name = "fechaPagoPedido", nullable = false)
    private LocalDate fechaPagoPedido;

    @Column(name = "totalPedido", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPedido;

    @ManyToOne
    @JoinColumn(name = "idVendedor")
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(name = "idComprador")
    private Usuario comprador;

    @ManyToOne
    @JoinColumn(name = "idMetodoPago")
    private MetodoPago metodoPago;

    public Pedido() {
    }

    public Pedido(int idPedido, String estadoPedido, LocalDate fechaCreacionPedido, LocalDate fechaPagoPedido, BigDecimal totalPedido, Usuario vendedor, Usuario comprador, MetodoPago metodoPago) {
        this.idPedido = idPedido;
        this.estadoPedido = estadoPedido;
        this.fechaCreacionPedido = fechaCreacionPedido;
        this.fechaPagoPedido = fechaPagoPedido;
        this.totalPedido = totalPedido;
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.metodoPago = metodoPago;
    }

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

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
}
