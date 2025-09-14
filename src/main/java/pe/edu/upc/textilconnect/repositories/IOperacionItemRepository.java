package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.OperacionItem;

@Repository
public interface IOperacionItemRepository extends JpaRepository<OperacionItem,Integer> {
}
