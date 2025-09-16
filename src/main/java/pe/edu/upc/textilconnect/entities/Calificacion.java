package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Calificacion")

public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCalificacion;

    @Column(name = "estrellas", nullable = false)
    private int estrellas;

    @Column(name = "comentario", length = 100, nullable = false)
    private String comentario;

    @Column(name = "fechaCalificacion", nullable = false)
    private LocalDate fechaCalificacion;

    @ManyToOne
    @JoinColumn(name = "idOperacion")
    private Operacion operacion;

    @ManyToOne
    @JoinColumn(name = "idCalificador")
    private Usuario calificador;

    @ManyToOne
    @JoinColumn(name = "idCalificado")
    private Usuario calificado;

    public Calificacion() {
    }

    public Calificacion(int idCalificacion, int estrellas, String comentario, LocalDate fechaCalificacion, Operacion operacion, Usuario calificador, Usuario calificado) {
        this.idCalificacion = idCalificacion;
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.fechaCalificacion = fechaCalificacion;
        this.operacion = operacion;
        this.calificador = calificador;
        this.calificado = calificado;
    }


}