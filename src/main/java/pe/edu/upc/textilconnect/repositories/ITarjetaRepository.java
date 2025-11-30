package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Tarjeta;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ITarjetaRepository extends JpaRepository<Tarjeta,Integer> {
    @Query("SELECT m FROM Tarjeta m WHERE m.usuario.idUsuario = :idUsuario")
    public List<Tarjeta> listarxusuario(int idUsuario);

    @Query("SELECT t.marcaTarjeta, COUNT(t) FROM Tarjeta t GROUP BY t.marcaTarjeta")
    List<Object[]> contarPorTodasLasMarcas();

    @Query("""
       SELECT t
       FROM Tarjeta t
       WHERE t.vencimientoTarjeta BETWEEN :inicio AND :fin
       """)
    List<Tarjeta> listarPorRangoVencimiento(
            @Param("inicio") LocalDate inicio,
            @Param("fin") LocalDate fin
    );

}
