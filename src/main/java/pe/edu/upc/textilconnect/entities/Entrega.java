package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Entrega")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEntrega;

    @Column(name = "tipoEntrega",length = 100, nullable = false)
    private String tipoEntrega;

    @Column(name = "direccionEntrega",length = 100, nullable = false)
    private String direccionEntrega;

    @Column(name = "latitudEntrega",nullable = false, precision = 10, scale = 2)
    private BigDecimal latitudEntrega;

    @Column(name = "longitudEntrega",nullable = false, precision = 10, scale = 2)
    private BigDecimal longitudEntrega;

    @Column(name = "fechaEntrega",nullable = false)
    private LocalDateTime fechaEntrega;

    @Column(name = "estadoEntrega",length = 100, nullable = false)
    private String estadoEntrega;

    @ManyToOne
    @JoinColumn(name = "idOperacion")
    private Operacion operacion;

    public Entrega() {
    }

    public Entrega(int idEntrega, String tipoEntrega, String direccionEntrega, BigDecimal latitudEntrega, BigDecimal longitudEntrega, LocalDateTime fechaEntrega, String estadoEntrega, Operacion operacion) {
        this.idEntrega = idEntrega;
        this.tipoEntrega = tipoEntrega;
        this.direccionEntrega = direccionEntrega;
        this.latitudEntrega = latitudEntrega;
        this.longitudEntrega = longitudEntrega;
        this.fechaEntrega = fechaEntrega;
        this.estadoEntrega = estadoEntrega;
        this.operacion = operacion;
    }

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

    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }
}
