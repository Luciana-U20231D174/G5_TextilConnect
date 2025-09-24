package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Comprobante")
public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComprobante;

    @Column(name = "numeroComprobante", length = 50, nullable = false)
    private String numeroComprobante;

    @Column(name = "fechaComprobante", nullable = false)
    private LocalDate fechaComprobante;

    @Column(name = "razonSocialComprobante", length = 150, nullable = false)
    private String razonSocialComprobante;

    @Column(name = "igvComprobante", nullable = false, precision = 12, scale = 2)
    private BigDecimal igvComprobante;

    @Column(name = "totalComprobante", nullable = false,  precision = 12, scale = 2)
    private BigDecimal totalComprobante;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idTipoDocumento")
    private TipoDocumento tipoDocumento;

    public Comprobante() {
    }

    public Comprobante(int idComprobante, String numeroComprobante, LocalDate fechaComprobante, String razonSocialComprobante, BigDecimal igvComprobante, BigDecimal totalComprobante, Pedido pedido, TipoDocumento tipoDocumento) {
        this.idComprobante = idComprobante;
        this.numeroComprobante = numeroComprobante;
        this.fechaComprobante = fechaComprobante;
        this.razonSocialComprobante = razonSocialComprobante;
        this.igvComprobante = igvComprobante;
        this.totalComprobante = totalComprobante;
        this.pedido = pedido;
        this.tipoDocumento = tipoDocumento;
    }

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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
