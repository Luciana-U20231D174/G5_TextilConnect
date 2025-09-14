package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="PresupuestoMensual")
public class PresupuestoMensual {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idPresupuestoMensual;

    @Column(name = "anioPresupuestoMensual", nullable = false)
    private Short anioPresupuestoMensual;

    @Column(name = "mesPresupuestoMensual", nullable = false)
    private Short mesPresupuestoMensual;

    @Column(name = "montoLimitePresupuestoMensual", nullable = false, precision = 10, scale = 2)
    private BigDecimal montoLimitePresupuestoMensual;

    @Column(name = "fechaPresupuestoMensual", nullable = false)
    private LocalDate fechaPresupuestoMensual;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public PresupuestoMensual() {
    }

    public PresupuestoMensual(int idPresupuestoMensual, Usuario usuario, LocalDate fechaPresupuestoMensual, Short anioPresupuestoMensual, BigDecimal montoLimitePresupuestoMensual, Short mesPresupuestoMensual) {
        this.idPresupuestoMensual = idPresupuestoMensual;
        this.usuario = usuario;
        this.fechaPresupuestoMensual = fechaPresupuestoMensual;
        this.anioPresupuestoMensual = anioPresupuestoMensual;
        this.montoLimitePresupuestoMensual = montoLimitePresupuestoMensual;
        this.mesPresupuestoMensual = mesPresupuestoMensual;
    }

    public int getIdPresupuestoMensual() {
        return idPresupuestoMensual;
    }

    public void setIdPresupuestoMensual(int idPresupuestoMensual) {
        this.idPresupuestoMensual = idPresupuestoMensual;
    }

    public Short getAnioPresupuestoMensual() {
        return anioPresupuestoMensual;
    }

    public void setAnioPresupuestoMensual(Short anioPresupuestoMensual) {
        this.anioPresupuestoMensual = anioPresupuestoMensual;
    }

    public Short getMesPresupuestoMensual() {
        return mesPresupuestoMensual;
    }

    public void setMesPresupuestoMensual(Short mesPresupuestoMensual) {
        this.mesPresupuestoMensual = mesPresupuestoMensual;
    }

    public BigDecimal getMontoLimitePresupuestoMensual() {
        return montoLimitePresupuestoMensual;
    }

    public void setMontoLimitePresupuestoMensual(BigDecimal montoLimitePresupuestoMensual) {
        this.montoLimitePresupuestoMensual = montoLimitePresupuestoMensual;
    }

    public LocalDate getFechaPresupuestoMensual() {
        return fechaPresupuestoMensual;
    }

    public void setFechaPresupuestoMensual(LocalDate fechaPresupuestoMensual) {
        this.fechaPresupuestoMensual = fechaPresupuestoMensual;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
