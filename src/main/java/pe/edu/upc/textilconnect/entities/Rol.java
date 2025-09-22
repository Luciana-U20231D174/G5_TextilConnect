package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

@Entity
@Table(
        name = "Rol"
)
public class Rol {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int idRol;

    @Column(name = "nombreRol",length = 50,nullable = false)
    private String nombreRol;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario usuario;

    public Rol() {
    }

    public Rol(int idRol, String nombreRol, Usuario usuario) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.usuario = usuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
