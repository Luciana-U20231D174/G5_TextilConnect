package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "MetodoPagoGuardado")
public class MetodoPagoGuardado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMetodoPagoGuardado;

    @Column(name = "aliasMetodoPagoGuardado", length = 50, nullable = false)
    private String aliasMetodoPagoGuardado;

    @Column(name = "tipoMetodoPagoGuardado", length = 20, nullable = false)
    private String tipoMetodoPagoGuardado;

    @Column(name = "ultimos4MetodoPagoGuardado", length = 4, nullable = false)
    private String ultimos4MetodoPagoGuardado;

    @Column(name = "marcaTarjetaMetodoPagoGuardado", length = 50, nullable = false)
    private String marcaTarjetaMetodoPagoGuardado;

    @Column(name = "tokenReferenciaMetodoPagoGuardado", length = 100, nullable = false)
    private String tokenReferenciaMetodoPagoGuardado;

    @Column(name = "vencimientoMetodoPagoGuardado", length = 5, nullable = false)
    private LocalDate vencimientoMetodoPagoGuardado;

    @Column(name = "activaMetodoPagoGuardado", nullable = false)
    private Boolean activaMetodoPagoGuardado;

    @Column(name = "fechaRegistroMetodoPagoGuardado", length = 50, nullable = false)
    private LocalDate fechaRegistroMetodoPagoGuardado;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public MetodoPagoGuardado() {
    }

    public MetodoPagoGuardado(int idMetodoPagoGuardado, String aliasMetodoPagoGuardado, String tipoMetodoPagoGuardado, String ultimos4MetodoPagoGuardado, String marcaTarjetaMetodoPagoGuardado, String tokenReferenciaMetodoPagoGuardado, LocalDate vencimientoMetodoPagoGuardado, Boolean activaMetodoPagoGuardado, LocalDate fechaRegistroMetodoPagoGuardado, Usuario usuario) {
        this.idMetodoPagoGuardado = idMetodoPagoGuardado;
        this.aliasMetodoPagoGuardado = aliasMetodoPagoGuardado;
        this.tipoMetodoPagoGuardado = tipoMetodoPagoGuardado;
        this.ultimos4MetodoPagoGuardado = ultimos4MetodoPagoGuardado;
        this.marcaTarjetaMetodoPagoGuardado = marcaTarjetaMetodoPagoGuardado;
        this.tokenReferenciaMetodoPagoGuardado = tokenReferenciaMetodoPagoGuardado;
        this.vencimientoMetodoPagoGuardado = vencimientoMetodoPagoGuardado;
        this.activaMetodoPagoGuardado = activaMetodoPagoGuardado;
        this.fechaRegistroMetodoPagoGuardado = fechaRegistroMetodoPagoGuardado;
        this.usuario = usuario;
    }

    public int getIdMetodoPagoGuardado() {
        return idMetodoPagoGuardado;
    }

    public void setIdMetodoPagoGuardado(int idMetodoPagoGuardado) {
        this.idMetodoPagoGuardado = idMetodoPagoGuardado;
    }

    public String getAliasMetodoPagoGuardado() {
        return aliasMetodoPagoGuardado;
    }

    public void setAliasMetodoPagoGuardado(String aliasMetodoPagoGuardado) {
        this.aliasMetodoPagoGuardado = aliasMetodoPagoGuardado;
    }

    public String getTipoMetodoPagoGuardado() {
        return tipoMetodoPagoGuardado;
    }

    public void setTipoMetodoPagoGuardado(String tipoMetodoPagoGuardado) {
        this.tipoMetodoPagoGuardado = tipoMetodoPagoGuardado;
    }

    public String getUltimos4MetodoPagoGuardado() {
        return ultimos4MetodoPagoGuardado;
    }

    public void setUltimos4MetodoPagoGuardado(String ultimos4MetodoPagoGuardado) {
        this.ultimos4MetodoPagoGuardado = ultimos4MetodoPagoGuardado;
    }

    public String getMarcaTarjetaMetodoPagoGuardado() {
        return marcaTarjetaMetodoPagoGuardado;
    }

    public void setMarcaTarjetaMetodoPagoGuardado(String marcaTarjetaMetodoPagoGuardado) {
        this.marcaTarjetaMetodoPagoGuardado = marcaTarjetaMetodoPagoGuardado;
    }

    public String getTokenReferenciaMetodoPagoGuardado() {
        return tokenReferenciaMetodoPagoGuardado;
    }

    public void setTokenReferenciaMetodoPagoGuardado(String tokenReferenciaMetodoPagoGuardado) {
        this.tokenReferenciaMetodoPagoGuardado = tokenReferenciaMetodoPagoGuardado;
    }

    public LocalDate getVencimientoMetodoPagoGuardado() {
        return vencimientoMetodoPagoGuardado;
    }

    public void setVencimientoMetodoPagoGuardado(LocalDate vencimientoMetodoPagoGuardado) {
        this.vencimientoMetodoPagoGuardado = vencimientoMetodoPagoGuardado;
    }

    public Boolean getActivaMetodoPagoGuardado() {
        return activaMetodoPagoGuardado;
    }

    public void setActivaMetodoPagoGuardado(Boolean activaMetodoPagoGuardado) {
        this.activaMetodoPagoGuardado = activaMetodoPagoGuardado;
    }

    public LocalDate getFechaRegistroMetodoPagoGuardado() {
        return fechaRegistroMetodoPagoGuardado;
    }

    public void setFechaRegistroMetodoPagoGuardado(LocalDate fechaRegistroMetodoPagoGuardado) {
        this.fechaRegistroMetodoPagoGuardado = fechaRegistroMetodoPagoGuardado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
