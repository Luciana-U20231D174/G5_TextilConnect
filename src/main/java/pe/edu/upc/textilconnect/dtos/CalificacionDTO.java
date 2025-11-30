package pe.edu.upc.textilconnect.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pe.edu.upc.textilconnect.entities.Pedido;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.time.LocalDate;

public class CalificacionDTO {
    private int idCalificacion;
    private int estrellas;
    private String comentario;
    private LocalDate fechaCalificacion;

    @JsonIgnoreProperties({"vendedor","comprador","metodoPago"})
    private Pedido pedido;

    @JsonIgnoreProperties({
            "roles","password","telefonoUsuario","direccionUsuario","fechaRegistroUsuario",
            "promedioCalificacion","totalCalificacion","enabled","username","emailUsuario"
    })
    private Usuario calificador;

    @JsonIgnoreProperties({
            "roles","password","telefonoUsuario","direccionUsuario","fechaRegistroUsuario",
            "promedioCalificacion","totalCalificacion","enabled","username","emailUsuario"
    })
    private Usuario calificado;

    public CalificacionDTO() {}

    public int getIdCalificacion() { return idCalificacion; }
    public void setIdCalificacion(int idCalificacion) { this.idCalificacion = idCalificacion; }

    public int getEstrellas() { return estrellas; }
    public void setEstrellas(int estrellas) { this.estrellas = estrellas; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDate getFechaCalificacion() { return fechaCalificacion; }
    public void setFechaCalificacion(LocalDate fechaCalificacion) { this.fechaCalificacion = fechaCalificacion; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Usuario getCalificador() { return calificador; }
    public void setCalificador(Usuario calificador) { this.calificador = calificador; }

    public Usuario getCalificado() { return calificado; }
    public void setCalificado(Usuario calificado) { this.calificado = calificado; }
}