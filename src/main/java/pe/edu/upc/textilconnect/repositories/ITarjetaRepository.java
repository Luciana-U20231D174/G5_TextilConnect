package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Tarjeta;

import java.util.List;

@Repository
public interface ITarjetaRepository extends JpaRepository<Tarjeta,Integer> {
    @Query("SELECT m FROM Tarjeta m WHERE m.usuario.idUsuario = :idUsuario")
    public List<Tarjeta> listarxusuario(int idUsuario);

    @Query("Select m FROM Tarjeta m WHERE m.marcaTarjeta like %:marca%")
    public List<Tarjeta> buscarxmarcaTarjeta(@Param("marcatarjeta") String marca);
}
