package pe.edu.upc.textilconnect.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.time.LocalDate;

public class TarjetaDTO {
    private int idTarjeta;
    private String aliasTarjeta;
    private String tipoTarjeta;
    private String ultimos4Tarjeta;
    private String marcaTarjeta;
    private String tokenReferenciaTarjeta;
    private LocalDate vencimientoTarjeta;
    private Boolean activaTarjeta;
    private LocalDate fechaRegistroTarjeta;

    @JsonIgnoreProperties({
            "roles","password","telefonoUsuario","direccionUsuario","fechaRegistroUsuario",
            "promedioCalificacion","totalCalificacion","enabled","username","emailUsuario",
            "hibernateLazyInitializer","handler"
    })
    private Usuario usuario;

    public TarjetaDTO() {}

    public int getIdTarjeta() { return idTarjeta; }
    public void setIdTarjeta(int idTarjeta) { this.idTarjeta = idTarjeta; }

    public String getAliasTarjeta() { return aliasTarjeta; }
    public void setAliasTarjeta(String aliasTarjeta) { this.aliasTarjeta = aliasTarjeta; }

    public String getTipoTarjeta() { return tipoTarjeta; }
    public void setTipoTarjeta(String tipoTarjeta) { this.tipoTarjeta = tipoTarjeta; }

    public String getUltimos4Tarjeta() { return ultimos4Tarjeta; }
    public void setUltimos4Tarjeta(String ultimos4Tarjeta) { this.ultimos4Tarjeta = ultimos4Tarjeta; }

    public String getMarcaTarjeta() { return marcaTarjeta; }
    public void setMarcaTarjeta(String marcaTarjeta) { this.marcaTarjeta = marcaTarjeta; }

    public String getTokenReferenciaTarjeta() { return tokenReferenciaTarjeta; }
    public void setTokenReferenciaTarjeta(String tokenReferenciaTarjeta) { this.tokenReferenciaTarjeta = tokenReferenciaTarjeta; }

    public LocalDate getVencimientoTarjeta() { return vencimientoTarjeta; }
    public void setVencimientoTarjeta(LocalDate vencimientoTarjeta) { this.vencimientoTarjeta = vencimientoTarjeta; }

    public Boolean getActivaTarjeta() { return activaTarjeta; }
    public void setActivaTarjeta(Boolean activaTarjeta) { this.activaTarjeta = activaTarjeta; }

    public LocalDate getFechaRegistroTarjeta() { return fechaRegistroTarjeta; }
    public void setFechaRegistroTarjeta(LocalDate fechaRegistroTarjeta) { this.fechaRegistroTarjeta = fechaRegistroTarjeta; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
