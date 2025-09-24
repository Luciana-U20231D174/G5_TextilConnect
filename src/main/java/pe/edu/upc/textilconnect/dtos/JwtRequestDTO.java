package pe.edu.upc.textilconnect.dtos;

import java.io.Serializable;

public class JwtRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombreUsuario;
    private String contraseniaUsuario;
    public JwtRequestDTO() {
        super();

    }
    public JwtRequestDTO(String nombreUsuario, String contraseniaUsuario) {
        super();
        this.nombreUsuario = nombreUsuario;
        this.contraseniaUsuario = contraseniaUsuario;
    }
public static long getSerialVersionUID() {return serialVersionUID;}
    public String getNombreUsuario() {return nombreUsuario;}
    public String getContraseniaUsuario() {return contraseniaUsuario;}
    public void setNombreUsuario(String nombreUsuario) {this.nombreUsuario = nombreUsuario;}
    public void setContraseniaUsuario(String contraseniaUsuario) {this.contraseniaUsuario = contraseniaUsuario;}
}
