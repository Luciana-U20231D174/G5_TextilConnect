package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Calificacion;

@Repository
public interface ICalificacionRepository extends JpaRepository<Calificacion,Integer> {
    @Query("SELECT COALESCE(AVG(c.estrellas), 0) " +
            "FROM Calificacion c " +
            "WHERE c.calificado.idUsuario = :idUsuario")
    Double obtenerPromedioCalificacion(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT COUNT(c) " +
            "FROM Calificacion c " +
            "WHERE c.calificado.idUsuario = :idUsuario")
    int obtenerTotalCalificacion(@Param("idUsuario") Integer idUsuario);
}
