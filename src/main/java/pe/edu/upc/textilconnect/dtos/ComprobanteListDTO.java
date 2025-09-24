package pe.edu.upc.textilconnect.dtos;
import java.math.BigDecimal;
import java.time.LocalDate;
public class ComprobanteListDTO {
    private String numeroComprobante;
    private LocalDate fechaComprobante;
    private BigDecimal totalComprobante;

    public ComprobanteListDTO(String numeroComprobante, LocalDate fechaComprobante, BigDecimal totalComprobante) {
        this.numeroComprobante = numeroComprobante;
        this.fechaComprobante = fechaComprobante;
        this.totalComprobante = totalComprobante;
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

    public BigDecimal getTotalComprobante() {
        return totalComprobante;
    }

    public void setTotalComprobante(BigDecimal totalComprobante) {
        this.totalComprobante = totalComprobante;
    }
}

