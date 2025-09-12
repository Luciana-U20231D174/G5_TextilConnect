package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "TipoDocumento"
)
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoDocumento;


    @Column(name ="nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcionTipoDocumento", length = 255,  nullable = true)
    private String descripcionTipoDocumento;

    @Column(name = "rucTipoDocumento",  nullable = true)
    private int rucTipoDocumento;

    public TipoDocumento() {
    }

    public TipoDocumento(int idTipoDocumento, String nombre, String descripcionTipoDocumento, int rucTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombre = nombre;
        this.descripcionTipoDocumento = descripcionTipoDocumento;
        this.rucTipoDocumento = rucTipoDocumento;
    }

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
