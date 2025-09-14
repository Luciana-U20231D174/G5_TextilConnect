package pe.edu.upc.textilconnect.dtos;


import pe.edu.upc.textilconnect.entities.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PresupuestoMensualDTO {
    private int idPresupuestoMensual;
    private Short anioPresupuestoMensual;
    private Short mesPresupuestoMensual;
    private BigDecimal montoLimitePresupuestoMensual;
    private LocalDate fechaPresupuestoMensual;
    private Usuario usuario;

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
