package pe.edu.upc.textilconnect.dtos;

public class CalificacionRequestDTO {
    private int estrellas;

    private String comentario;

    private Integer idPedido;

    private Integer idCalificador; // quien califica

    private Integer idCalificado;  // quien recibe la calificación

    // Getters & Setters
    public int getEstrellas() { return estrellas; }
    public void setEstrellas(int estrellas) { this.estrellas = estrellas; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public Integer getIdCalificador() { return idCalificador; }
    public void setIdCalificador(Integer idCalificador) { this.idCalificador = idCalificador; }

    public Integer getIdCalificado() { return idCalificado; }
    public void setIdCalificado(Integer idCalificado) { this.idCalificado = idCalificado; }
}