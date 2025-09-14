package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Entrega;

@Repository
    public interface IEntregaRepository extends JpaRepository<Entrega, Integer> {
}
