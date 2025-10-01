package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Proyecto;

import java.util.List;

@Repository
public interface IProyectoRepository extends JpaRepository<Proyecto,Integer> {

        @Query("SELECT p FROM Proyecto p WHERE LOWER(p.tituloProyecto) LIKE LOWER(CONCAT('%', :titulo, '%'))")
        List<Proyecto> buscarTitloPy(@Param("titulo") String titulo);

        @Query("SELECT p FROM Proyecto p WHERE p.usuario.idUsuario = :idUsuario")
        List<Proyecto> buscarUsuarioPy(@Param("idUsuario") Integer idUsuario);
}
