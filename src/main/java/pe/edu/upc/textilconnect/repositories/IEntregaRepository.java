package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Entrega;

import java.time.LocalDateTime;
import java.util.List;

@Repository
    public interface IEntregaRepository extends JpaRepository<Entrega, Integer> {
    @Query("SELECT e FROM Entrega e WHERE e.estadoEntrega = :estado")
    List<Entrega> buscarEstadoE(@Param("estado") String estado);

    @Query("SELECT e FROM Entrega e WHERE e.tipoEntrega = :tipo")
    List<Entrega> buscarTipoE(@Param("tipo") String tipo);


    @Query("SELECT COUNT(e) FROM Entrega e WHERE e.fechaEntrega BETWEEN :inicio AND :fin")
    Long contarPorRango(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    @Query("SELECT COUNT(e) FROM Entrega e WHERE e.fechaEntrega BETWEEN :inicio AND :fin AND e.estadoEntrega = 'Cancelado'")
    Long contarCanceladasPorRango(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}
