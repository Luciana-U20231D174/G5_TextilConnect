package pe.edu.upc.textilconnect.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class UsuarioResponseDTO {
    private int idUsuario;
    private String nombreUsuario;
    private String emailUsuario;
    private String username;
    private String telefonoUsuario;
    private String direccionUsuario;
    private LocalDate fechaRegistroUsuario;
    private BigDecimal promedioCalificacion;
    private int totalCalificacion;
    private Boolean estado;
    private List<String> roles;

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getEmailUsuario() { return emailUsuario; }
    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTelefonoUsuario() { return telefonoUsuario; }
    public void setTelefonoUsuario(String telefonoUsuario) { this.telefonoUsuario = telefonoUsuario; }

    public String getDireccionUsuario() { return direccionUsuario; }
    public void setDireccionUsuario(String direccionUsuario) { this.direccionUsuario = direccionUsuario; }

    public LocalDate getFechaRegistroUsuario() { return fechaRegistroUsuario; }
    public void setFechaRegistroUsuario(LocalDate fechaRegistroUsuario) { this.fechaRegistroUsuario = fechaRegistroUsuario; }

    public BigDecimal getPromedioCalificacion() { return promedioCalificacion; }
    public void setPromedioCalificacion(BigDecimal promedioCalificacion) { this.promedioCalificacion = promedioCalificacion; }

    public int getTotalCalificacion() { return totalCalificacion; }
    public void setTotalCalificacion(int totalCalificacion) { this.totalCalificacion = totalCalificacion; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}
