package pe.edu.upc.textilconnect.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ComprobanteDTO {
    private int idComprobante;
    private String numeroComprobante;
    private LocalDate fechaComprobante;
    private String razonSocialComprobante;
    private BigDecimal igvComprobante;
    private BigDecimal totalComprobante;

    // 👇 relaciones en formato simple
    private Integer idPedido;
    private Integer idTipoDocumento;
    private String nombreTipoDocumento;

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public LocalDate getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(LocalDate fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }

    public String getRazonSocialComprobante() {
        return razonSocialComprobante;
    }

    public void setRazonSocialComprobante(String razonSocialComprobante) {
        this.razonSocialComprobante = razonSocialComprobante;
    }

    public BigDecimal getIgvComprobante() {
        return igvComprobante;
    }

    public void setIgvComprobante(BigDecimal igvComprobante) {
        this.igvComprobante = igvComprobante;
    }

    public BigDecimal getTotalComprobante() {
        return totalComprobante;
    }

    public void setTotalComprobante(BigDecimal totalComprobante) {
        this.totalComprobante = totalComprobante;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombreTipoDocumento() {
        return nombreTipoDocumento;
    }

    public void setNombreTipoDocumento(String nombreTipoDocumento) {
        this.nombreTipoDocumento = nombreTipoDocumento;
    }
}
