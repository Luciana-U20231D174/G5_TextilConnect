package pe.edu.upc.textilconnect.dtos;

import java.time.LocalDate;

public class UsuarioDTOInsert {
    private Integer idUsuario;
    private String nombreUsuario;
    private String emailUsuario;
    private String username;
    private String password;
    private String telefonoUsuario;
    private String direccionUsuario;
    private LocalDate fechaRegistroUsuario;
    private Boolean enabled;

    // id del rol elegido en el combo de Angular
    private Integer idRol;

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

    public Integer getIdRol() { return idRol; }
    public void setIdRol(Integer idRol) { this.idRol = idRol; }
}
