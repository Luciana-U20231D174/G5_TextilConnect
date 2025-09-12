package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.MetodoPago;
import java.util.List;

@Repository
public interface IMetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    @Query("Select metodoPago from MetodoPago metodoPago where metodoPago.nombreMetodoPago like %:nombre%")
    public List<MetodoPago> buscarR(@Param("nombre") String nombre);
}
