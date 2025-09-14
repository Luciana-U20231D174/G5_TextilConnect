package pe.edu.upc.textilconnect.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Operacion")
public class Operacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOperacion;

    @Column(name = "estadoOperacion", length = 20, nullable = false)
    private String estadoOperacion;

    @Column(name = "fechaCreacionOperacion", nullable = false)
    private LocalDate fechaCreacionOperacion;

    @Column(name = "fechaPagoOperacion", nullable = false)
    private LocalDate fechaPagoOperacion;

    @Column(name = "totalOperacion", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalOperacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idMetodoPago")
    private MetodoPago metodoPago;

    public Operacion() {
    }

    public Operacion(int idOperacion, String estadoOperacion, LocalDate fechaCreacionOperacion, LocalDate fechaPagoOperacion, BigDecimal totalOperacion, Usuario usuario, MetodoPago metodoPago) {
        this.idOperacion = idOperacion;
        this.estadoOperacion = estadoOperacion;
        this.fechaCreacionOperacion = fechaCreacionOperacion;
        this.fechaPagoOperacion = fechaPagoOperacion;
        this.totalOperacion = totalOperacion;
        this.usuario = usuario;
        this.metodoPago = metodoPago;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(String estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    public LocalDate getFechaCreacionOperacion() {
        return fechaCreacionOperacion;
    }

    public void setFechaCreacionOperacion(LocalDate fechaCreacionOperacion) {
        this.fechaCreacionOperacion = fechaCreacionOperacion;
    }

    public LocalDate getFechaPagoOperacion() {
        return fechaPagoOperacion;
    }

    public void setFechaPagoOperacion(LocalDate fechaPagoOperacion) {
        this.fechaPagoOperacion = fechaPagoOperacion;
    }

    public BigDecimal getTotalOperacion() {
        return totalOperacion;
    }

    public void setTotalOperacion(BigDecimal totalOperacion) {
        this.totalOperacion = totalOperacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
}
