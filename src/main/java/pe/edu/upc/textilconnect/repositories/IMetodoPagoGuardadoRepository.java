package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.MetodoPagoGuardado;

import java.util.List;

@Repository
public interface IMetodoPagoGuardadoRepository extends JpaRepository<MetodoPagoGuardado,Integer> {
    @Query("SELECT m FROM MetodoPagoGuardado m WHERE m.usuario.idUsuario = :idUsuario")
    public List<MetodoPagoGuardado> listarxusuario(int idUsuario);

    @Query("Select m FROM MetodoPagoGuardado m WHERE m.marcaTarjetaMetodoPagoGuardado like %:marca%")
    public List<MetodoPagoGuardado> buscarxmarcaTarjeta(@Param("marcatarjeta") String marca);
}
