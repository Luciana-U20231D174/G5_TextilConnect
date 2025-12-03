package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "nombreUsuario", length = 100, nullable = false)
    private String nombreUsuario;

    @Column(name = "emailUsuario", length = 100, nullable = false, unique = true)
    private String emailUsuario;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @Column(name = "telefonoUsuario", length = 20, nullable = false)
    private String telefonoUsuario;

    @Column(name = "direccionUsuario", length = 200, nullable = false)
    private String direccionUsuario;

    @Column(name = "fechaRegistroUsuario", nullable = false)
    private LocalDate fechaRegistroUsuario;

    private Boolean enabled;

    @Column(name = "promedioCalificacion", nullable = false, precision = 12, scale = 2)
    private BigDecimal promedioCalificacion = BigDecimal.ZERO;

    @Column(name = "totalCalificacion", nullable = false)
    private int totalCalificacion = 0;

    @Column(name = "fotoUrl", length = 500, nullable = true)
    private String fotoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;

    public Usuario() {}

    @PrePersist
    public void prePersist() {
        if (promedioCalificacion == null) {
            promedioCalificacion = BigDecimal.ZERO;
        }
        // totalCalificacion ya está en 0 por defecto
    }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getEmailUsuario() { return emailUsuario; }
    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getTelefonoUsuario() { return telefonoUsuario; }
    public void setTelefonoUsuario(String telefonoUsuario) { this.telefonoUsuario = telefonoUsuario; }

    public String getDireccionUsuario() { return direccionUsuario; }
    public void setDireccionUsuario(String direccionUsuario) { this.direccionUsuario = direccionUsuario; }

    public LocalDate getFechaRegistroUsuario() { return fechaRegistroUsuario; }
    public void setFechaRegistroUsuario(LocalDate fechaRegistroUsuario) { this.fechaRegistroUsuario = fechaRegistroUsuario; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public BigDecimal getPromedioCalificacion() { return promedioCalificacion; }
    public void setPromedioCalificacion(BigDecimal promedioCalificacion) { this.promedioCalificacion = promedioCalificacion; }

    public int getTotalCalificacion() { return totalCalificacion; }
    public void setTotalCalificacion(int totalCalificacion) { this.totalCalificacion = totalCalificacion; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}