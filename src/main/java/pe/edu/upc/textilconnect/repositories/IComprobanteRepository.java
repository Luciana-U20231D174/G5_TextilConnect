package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.textilconnect.entities.Comprobante;

import java.time.LocalDate;
import java.util.List;

public interface IComprobanteRepository extends JpaRepository<Comprobante, Integer> {

    @Query("FROM Comprobante c WHERE c.pedido.idPedido = :idPedido")
    List<Comprobante> listarPorOperacion(@Param("idPedido") int idPedido);

    @Query("SELECT COUNT(c) FROM Comprobante c WHERE c.pedido.idPedido = :idPedido")
    Long contarPorOperacion(@Param("idPedido") int idPedido);

    @Query("FROM Comprobante c WHERE c.fechaComprobante BETWEEN :inicio AND :fin")
    List<Comprobante> buscarxRangoFechas(@Param("inicio") LocalDate inicio,
                                         @Param("fin") LocalDate fin);

    @Query("SELECT SUM(c.igvComprobante) FROM Comprobante c WHERE c.fechaComprobante = :fechaComprobante")
    Double sumarIgvPorFecha(@Param("fechaComprobante") LocalDate fecha);

}
