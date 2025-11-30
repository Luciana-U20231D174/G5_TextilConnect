// src/main/java/pe/edu/upc/textilconnect/dtos/PresupuestoMensualDTO.java
package pe.edu.upc.textilconnect.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PresupuestoMensualDTO {
    private int idPresupuestoMensual;
    private Short anioPresupuestoMensual;
    private Short mesPresupuestoMensual;
    private BigDecimal montoLimitePresupuestoMensual;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaPresupuestoMensual;

    // Lo mandamos como objeto sencillo (id + nombre)
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
