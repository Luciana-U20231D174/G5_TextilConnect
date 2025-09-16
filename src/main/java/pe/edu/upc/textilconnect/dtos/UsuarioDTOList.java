package pe.edu.upc.textilconnect.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UsuarioDTOList {
    private String nombreUsuario;
    private String emailUsuario;
    private LocalDate fechaRegistroUsuario;
    private BigDecimal promedioCalifacion;
    private int totalCalificacion;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public LocalDate getFechaRegistroUsuario() {
        return fechaRegistroUsuario;
    }

    public void setFechaRegistroUsuario(LocalDate fechaRegistroUsuario) {
        this.fechaRegistroUsuario = fechaRegistroUsuario;
    }

    public BigDecimal getPromedioCalifacion() {
        return promedioCalifacion;
    }

    public void setPromedioCalifacion(BigDecimal promedioCalifacion) {
        this.promedioCalifacion = promedioCalifacion;
    }

    public int getTotalCalificacion() {
        return totalCalificacion;
    }

    public void setTotalCalificacion(int totalCalificacion) {
        this.totalCalificacion = totalCalificacion;
    }
}
