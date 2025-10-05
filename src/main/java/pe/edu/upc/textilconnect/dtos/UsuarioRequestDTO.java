package pe.edu.upc.textilconnect.dtos;

import jakarta.validation.constraints.*;
import java.util.List;

public class UsuarioRequestDTO {

    @NotBlank @Size(max = 100)
    private String nombreUsuario;

    @NotBlank @Email @Size(max = 100)
    private String emailUsuario;

    @NotBlank @Size(max = 50)
    private String username;

    @NotBlank @Size(max = 200)
    private String password;

    @NotBlank @Size(max = 20)
    private String telefonoUsuario;

    @NotBlank @Size(max = 200)
    private String direccionUsuario;

    private Boolean estado = true;

    @NotNull
    private List<Integer> rolesIds;

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

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public List<Integer> getRolesIds() { return rolesIds; }
    public void setRolesIds(List<Integer> rolesIds) { this.rolesIds = rolesIds; }
}
