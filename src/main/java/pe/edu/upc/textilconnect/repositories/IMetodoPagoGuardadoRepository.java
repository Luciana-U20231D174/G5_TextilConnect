package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.MetodoPagoGuardado;

@Repository
public interface IMetodoPagoGuardadoRepository extends JpaRepository<MetodoPagoGuardado,Integer> {
}
