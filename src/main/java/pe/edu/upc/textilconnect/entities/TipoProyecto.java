package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "TipoProyecto"
)
public class TipoProyecto {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int idTipoProyecto;

    @Column(name = "nombreTipoProyecto",length = 50,nullable = false)
    private String nombreTipoProyecto;

    public TipoProyecto() {
    }

    public TipoProyecto(int idTipoProyecto, String nombreTipoProyecto) {
        this.idTipoProyecto = idTipoProyecto;
        this.nombreTipoProyecto = nombreTipoProyecto;
    }

    public int getIdTipoProyecto() {
        return idTipoProyecto;
    }

    public void setIdTipoProyecto(int idTipoProyecto) {
        this.idTipoProyecto = idTipoProyecto;
    }

    public String getNombreTipoProyecto() {
        return nombreTipoProyecto;
    }

    public void setNombreTipoProyecto(String nombreTipoProyecto) {
        this.nombreTipoProyecto = nombreTipoProyecto;
    }
}
