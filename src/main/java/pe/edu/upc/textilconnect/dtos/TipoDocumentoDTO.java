package pe.edu.upc.textilconnect.dtos;

public class TipoDocumentoDTO {
    private int idTipoDocumento;
    private String nombre;
    private String descripcionTipoDocumento;
    private int rucTipoDocumento;

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionTipoDocumento() {
        return descripcionTipoDocumento;
    }

    public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
        this.descripcionTipoDocumento = descripcionTipoDocumento;
    }

    public int getRucTipoDocumento() {
        return rucTipoDocumento;
    }

    public void setRucTipoDocumento(int rucTipoDocumento) {
        this.rucTipoDocumento = rucTipoDocumento;
    }
}
