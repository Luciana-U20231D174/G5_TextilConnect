package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Operacion;

@Repository
public interface IOperacionRepository extends JpaRepository<Operacion, Integer> {
}
