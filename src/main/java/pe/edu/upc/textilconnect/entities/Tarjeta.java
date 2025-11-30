package pe.edu.upc.textilconnect.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Tarjeta")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTarjeta;

    @Column(name = "aliasTarjeta", length = 50, nullable = false)
    private String aliasTarjeta;

    @Column(name = "tipoTarjeta", length = 20, nullable = false)
    private String tipoTarjeta;

    @Column(name = "ultimos4Tarjeta", length = 4, nullable = false)
    private String ultimos4Tarjeta;

    @Column(name = "marcaTarjeta", length = 50, nullable = false)
    private String marcaTarjeta;

    @Column(name = "tokenReferenciaTarjeta", length = 100, nullable = false)
    private String tokenReferenciaTarjeta;

    @Column(name = "vencimientoTarjeta", length = 5, nullable = false)
    private LocalDate vencimientoTarjeta;

    @Column(name = "activaTarjeta", nullable = false)
    private Boolean activaTarjeta;

    @Column(name = "fechaRegistroTarjeta", length = 50, nullable = false)
    private LocalDate fechaRegistroTarjeta;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;


    public Tarjeta() {
    }

    public Tarjeta(int idTarjeta, String aliasTarjeta, String tipoTarjeta, String ultimos4Tarjeta, String marcaTarjeta, String tokenReferenciaTarjeta, LocalDate vencimientoTarjeta, Boolean activaTarjeta, LocalDate fechaRegistroTarjeta, Usuario usuario) {
        this.idTarjeta = idTarjeta;
        this.aliasTarjeta = aliasTarjeta;
        this.tipoTarjeta = tipoTarjeta;
        this.ultimos4Tarjeta = ultimos4Tarjeta;
        this.marcaTarjeta = marcaTarjeta;
        this.tokenReferenciaTarjeta = tokenReferenciaTarjeta;
        this.vencimientoTarjeta = vencimientoTarjeta;
        this.activaTarjeta = activaTarjeta;
        this.fechaRegistroTarjeta = fechaRegistroTarjeta;
        this.usuario = usuario;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getAliasTarjeta() {
        return aliasTarjeta;
    }

    public void setAliasTarjeta(String aliasTarjeta) {
        this.aliasTarjeta = aliasTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getUltimos4Tarjeta() {
        return ultimos4Tarjeta;
    }

    public void setUltimos4Tarjeta(String ultimos4Tarjeta) {
        this.ultimos4Tarjeta = ultimos4Tarjeta;
    }

    public String getMarcaTarjeta() {
        return marcaTarjeta;
    }

    public void setMarcaTarjeta(String marcaTarjeta) {
        this.marcaTarjeta = marcaTarjeta;
    }

    public String getTokenReferenciaTarjeta() {
        return tokenReferenciaTarjeta;
    }

    public void setTokenReferenciaTarjeta(String tokenReferenciaTarjeta) {
        this.tokenReferenciaTarjeta = tokenReferenciaTarjeta;
    }

    public LocalDate getVencimientoTarjeta() {
        return vencimientoTarjeta;
    }

    public void setVencimientoTarjeta(LocalDate vencimientoTarjeta) {
        this.vencimientoTarjeta = vencimientoTarjeta;
    }

    public Boolean getActivaTarjeta() {
        return activaTarjeta;
    }

    public void setActivaTarjeta(Boolean activaTarjeta) {
        this.activaTarjeta = activaTarjeta;
    }

    public LocalDate getFechaRegistroTarjeta() {
        return fechaRegistroTarjeta;
    }

    public void setFechaRegistroTarjeta(LocalDate fechaRegistroTarjeta) {
        this.fechaRegistroTarjeta = fechaRegistroTarjeta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
