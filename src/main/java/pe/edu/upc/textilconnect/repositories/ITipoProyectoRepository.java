package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.TipoProyecto;

import java.util.List;

@Repository
public interface ITipoProyectoRepository extends JpaRepository<TipoProyecto, Integer> {
    @Query("Select tipoProyecto from TipoProyecto tipoProyecto where tipoProyecto.nombreTipoProyecto like %:nombre%")
    public List<TipoProyecto> buscarR(@Param("nombre") String nombre);
}
