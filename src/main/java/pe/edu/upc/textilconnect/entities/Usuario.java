package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table (name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "nombreUsuario", length = 100, nullable = false)
    private String nombreUsuario;

    @Column(name = "emailUsuario", length = 100, nullable = false, unique = true)
    private String emailUsuario;

    @Column(name = "contrasenaUsuario", length = 255, nullable = false)
    private String contrasenaUsuario;

    @Column(name = "telefonoUsuario", length = 20, nullable = false)
    private String telefonoUsuario;

    @Column(name = "direccionUsuario", length = 200, nullable = false)
    private String direccionUsuario;

    @Column(name = "fechaRegistroUsuario", nullable = false)
    private LocalDate fechaRegistroUsuario;

    @Column(name = "promedioCalificacion", nullable = false, precision = 12, scale = 2)
    private BigDecimal promedioCalificacion;

    @Column(name = "totalCalificacion", nullable = false)
    private int totalCalificacion;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String emailUsuario, String contrasenaUsuario, String telefonoUsuario, String direccionUsuario, LocalDate fechaRegistroUsuario, BigDecimal promedioCalificacion, int totalCalificacion, Rol rol) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.emailUsuario = emailUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.direccionUsuario = direccionUsuario;
        this.fechaRegistroUsuario = fechaRegistroUsuario;
        this.promedioCalificacion = promedioCalificacion;
        this.totalCalificacion = totalCalificacion;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public LocalDate getFechaRegistroUsuario() {
        return fechaRegistroUsuario;
    }

    public void setFechaRegistroUsuario(LocalDate fechaRegistroUsuario) {
        this.fechaRegistroUsuario = fechaRegistroUsuario;
    }

    public BigDecimal getPromedioCalificacion() {
        return promedioCalificacion;
    }

    public void setPromedioCalificacion(BigDecimal promedioCalificacion) {
        this.promedioCalificacion = promedioCalificacion;
    }

    public int getTotalCalificacion() {
        return totalCalificacion;
    }

    public void setTotalCalificacion(int totalCalificacion) {
        this.totalCalificacion = totalCalificacion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
