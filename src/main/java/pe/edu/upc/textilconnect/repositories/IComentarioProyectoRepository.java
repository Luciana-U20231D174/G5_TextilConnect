package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.ComentarioProyecto;

import java.util.List;

@Repository
public interface IComentarioProyectoRepository extends JpaRepository<ComentarioProyecto, Integer> {

    @Query("SELECT c FROM ComentarioProyecto c WHERE c.proyecto.idProyecto = :idProyecto ORDER BY c.fechaComentario DESC")
    public List<ComentarioProyecto> listarPorProyecto(@Param("idProyecto") int idProyecto);

    @Query("SELECT COUNT(c) FROM ComentarioProyecto c WHERE c.proyecto.idProyecto = :idProyecto")
    public int contarPorProyecto(@Param("idProyecto") int idProyecto);
}
