package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Comprobante;

import java.util.List;

@Repository
public interface IComprobanteRepository extends JpaRepository<Comprobante,Integer> {
    @Query("SELECT c FROM Comprobante c WHERE c.operacion.idOperacion = :idOperacion ORDER BY c.fechaComprobante DESC")
    public List<Comprobante> listarPorOperacion(@Param("idOperacion") int idOperacion);

    @Query("SELECT COUNT(c) FROM Comprobante c WHERE c.operacion.idOperacion = :idOperacion")
    public int contarPorOperacion(@Param("idOperacion") int idOperacion);

}
