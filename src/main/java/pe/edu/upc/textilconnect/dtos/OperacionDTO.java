package pe.edu.upc.textilconnect.dtos;

import pe.edu.upc.textilconnect.entities.MetodoPago;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OperacionDTO {
    private int idOperacion;
    private String estadoOperacion;
    private LocalDate fechaCreacionOperacion;
    private LocalDate fechaPagoOperacion;
    private BigDecimal totalOperacion;
    private Usuario comprador;
    private Usuario vendedor;
    private MetodoPago metodoPago;

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(String estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    public LocalDate getFechaCreacionOperacion() {
        return fechaCreacionOperacion;
    }

    public void setFechaCreacionOperacion(LocalDate fechaCreacionOperacion) {
        this.fechaCreacionOperacion = fechaCreacionOperacion;
    }

    public LocalDate getFechaPagoOperacion() {
        return fechaPagoOperacion;
    }

    public void setFechaPagoOperacion(LocalDate fechaPagoOperacion) {
        this.fechaPagoOperacion = fechaPagoOperacion;
    }

    public BigDecimal getTotalOperacion() {
        return totalOperacion;
    }

    public void setTotalOperacion(BigDecimal totalOperacion) {
        this.totalOperacion = totalOperacion;
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
