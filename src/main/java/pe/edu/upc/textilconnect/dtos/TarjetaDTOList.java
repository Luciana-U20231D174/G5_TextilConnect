package pe.edu.upc.textilconnect.dtos;


import java.time.LocalDate;

public class TarjetaDTOList {
    private String aliasTarjeta;
    private String tipoTarjeta;
    private String ultimos4Tarjeta;
    private String marcaTarjeta;
    private Boolean activaTarjeta;
    private LocalDate fechaRegistroTarjeta;

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
}
