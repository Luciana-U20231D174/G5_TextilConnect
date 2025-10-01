package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Comprobante;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IComprobanteRepository extends JpaRepository<Comprobante,Integer> {
    @Query("SELECT c FROM Comprobante c WHERE c.pedido.idPedido = :idPedido ORDER BY c.fechaComprobante DESC")
    public List<Comprobante> listarPorPedido(@Param("idPedido") int idPedido);

    @Query("SELECT COUNT(c) FROM Comprobante c WHERE c.pedido.idPedido = :idPedido")
    public int contarPorPedido(@Param("idPedido") int idPedido);

    @Query("SELECT c FROM Comprobante c WHERE c.fechaComprobante BETWEEN :inicio AND :fin")
    List<Comprobante> buscarRangoFechasC(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}
