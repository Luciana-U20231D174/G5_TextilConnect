package pe.edu.upc.textilconnect.dtos;

import pe.edu.upc.textilconnect.entities.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EntregaDTO {
    private int idEntrega;
    private String tipoEntrega;
    private String direccionEntrega;
    private BigDecimal latitudEntrega;
    private BigDecimal longitudEntrega;
    private LocalDateTime fechaEntrega;
    private String estadoEntrega;
    private Pedido pedido;

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public BigDecimal getLatitudEntrega() {
        return latitudEntrega;
    }

    public void setLatitudEntrega(BigDecimal latitudEntrega) {
        this.latitudEntrega = latitudEntrega;
    }

    public BigDecimal getLongitudEntrega() {
        return longitudEntrega;
    }

    public void setLongitudEntrega(BigDecimal longitudEntrega) {
        this.longitudEntrega = longitudEntrega;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
