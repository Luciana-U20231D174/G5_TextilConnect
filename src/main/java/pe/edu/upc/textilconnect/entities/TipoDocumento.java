package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "TipoDocumento"
)
public class TipoDocumento {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int idTipoDocumento;

    @Column(name = "codigoTipoDocumento", length = 5, nullable = false)
    private String codigoTipoDocumento;

    @Column(name ="nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcionTipoDocumento", length = 255,  nullable = true)
    private String descripcionTipoDocumento;

    @Column(name = "rucTipoDocumento", length = 11,  nullable = true)
    private int rucTipoDocumento;

    public TipoDocumento() {
    }

    public TipoDocumento(int idTipoDocumento, int rucTipoDocumento, String descripcionTipoDocumento, String nombre, String codigoTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        this.rucTipoDocumento = rucTipoDocumento;
        this.descripcionTipoDocumento = descripcionTipoDocumento;
        this.nombre = nombre;
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public int getRucTipoDocumento() {
        return rucTipoDocumento;
    }

    public void setRucTipoDocumento(int rucTipoDocumento) {
        this.rucTipoDocumento = rucTipoDocumento;
    }

    public String getDescripcionTipoDocumento() {
        return descripcionTipoDocumento;
    }

    public void setDescripcionTipoDocumento(String descripcionTipoDocumento) {
        this.descripcionTipoDocumento = descripcionTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(String codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }
}
