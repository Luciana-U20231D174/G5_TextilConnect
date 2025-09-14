package pe.edu.upc.textilconnect.dtos;

import pe.edu.upc.textilconnect.entities.Operacion;
import pe.edu.upc.textilconnect.entities.TipoDocumento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ComprobanteDTO {
    private int idComprobante;
    private String numeroComprobante;
    private LocalDate fechaComprobante;
    private String razonSocialComprobante;
    private BigDecimal igvComprobante;
    private BigDecimal totalComprobante;
    private Operacion operacion;
    private TipoDocumento tipoDocumento;

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

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
