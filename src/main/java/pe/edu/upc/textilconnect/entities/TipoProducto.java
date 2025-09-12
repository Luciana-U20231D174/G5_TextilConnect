package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "TipoProducto"
)
public class TipoProducto {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int idTipoProducto;

    @Column(name = "nombreTipoProducto",length = 50,nullable = false)
    private String nombreTipoProducto;

    public TipoProducto() {
    }

    public TipoProducto(int idTipoProducto, String nombreTipoProducto) {
        this.idTipoProducto = idTipoProducto;
        this.nombreTipoProducto = nombreTipoProducto;
    }

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNombreTipoProducto() {
        return nombreTipoProducto;
    }

    public void setNombreTipoProducto(String nombreTipoProducto) {
        this.nombreTipoProducto = nombreTipoProducto;
    }
}
