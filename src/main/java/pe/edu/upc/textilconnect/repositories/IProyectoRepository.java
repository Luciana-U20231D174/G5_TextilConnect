package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Proyecto;

import java.util.List;

@Repository
public interface IProyectoRepository extends JpaRepository<Proyecto,Integer> {
    @Query("SELECT p FROM Proyecto p WHERE p.usuario.idUsuario = :idUsuario AND p.visibleProyecto = 'SI' ORDER BY p.fechaCreacion DESC")
    public List<Proyecto> listarProyectosVisiblesPorUsuario(@Param("idUsuario") int idUsuario);
    @Query("SELECT COUNT(p) FROM Proyecto p WHERE p.tipoProyecto.idTipoProyecto = :idTipoProyecto")
    public int contarProyectosPorTipo(@Param("idTipoProyecto") int idTipoProyecto);
}
