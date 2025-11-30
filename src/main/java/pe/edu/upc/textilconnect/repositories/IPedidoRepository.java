package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Pedido;

import java.time.LocalDate;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("select sum(p.totalPedido) from Pedido p where p.fechaCreacionPedido = :fechaCreacionPedido")
    Double sumarTotalPorFecha(@Param("fechaCreacionPedido") LocalDate fecha);

}
