package pe.edu.upc.textilconnect.dtos;

import pe.edu.upc.textilconnect.entities.Usuario;

import java.time.LocalDate;

public class MetodoPagoGuardadoDTOInsert {
    private int idmetodoPagoGuardado;
    private String aliasMetodoPagoGuardado;
    private String tipoMetodoPagoGuardado;
    private String ultimos4MetodoPagoGuardado;
    private String marcaTarjetaMetodoPagoGuardado;
    private String tokenReferenciaMetodoPagoGuardado;
    private LocalDate vencimientoMetodoPagoGuardado;
    private Boolean activaMetodoPagoGuardado;
    private LocalDate fechaRegistroMetodoPagoGuardado;
    private Usuario usuario;

    public int getIdmetodoPagoGuardado() {
        return idmetodoPagoGuardado;
    }

    public void setIdmetodoPagoGuardado(int idmetodoPagoGuardado) {
        this.idmetodoPagoGuardado = idmetodoPagoGuardado;
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
