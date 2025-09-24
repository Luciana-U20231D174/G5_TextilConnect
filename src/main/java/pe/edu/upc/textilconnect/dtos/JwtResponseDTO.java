package pe.edu.upc.textilconnect.dtos;

import java.io.Serializable;

public class JwtResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String jwttoken;

    public String getJwttoken() {return jwttoken;}

    public JwtResponseDTO(String jwttoken) {
        super();
        this.jwttoken = jwttoken;}
}
