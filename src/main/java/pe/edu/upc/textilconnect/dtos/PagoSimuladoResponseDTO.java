package pe.edu.upc.textilconnect.dtos;

import java.math.BigDecimal;

public class PagoSimuladoResponseDTO {
    private String idPago;
    private Integer idPedido;
    private BigDecimal monto;
    private String urlPago;
    private String estado; // PENDIENTE, APROBADO, etc.

    public String getIdPago() { return idPago; }
    public void setIdPago(String idPago) { this.idPago = idPago; }

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public String getUrlPago() { return urlPago; }
    public void setUrlPago(String urlPago) { this.urlPago = urlPago; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
