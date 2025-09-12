package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.TipoDocumento;

import java.util.List;

@Repository
public interface ITipoDocumentoRepository extends JpaRepository<TipoDocumento,Integer> {
    @Query("Select tp from TipoDocumento tp where tp.nombre like %:nombre%")
    public List<TipoDocumento> buscarR(@Param("nombre") String nombre);
}
