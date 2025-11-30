package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.textilconnect.entities.Rol;

public interface IRolRepository extends JpaRepository<Rol,Integer> {

    @Modifying
    @Query(value = "DELETE FROM rol WHERE id_rol = :id", nativeQuery = true)
    void deleteRolById(@Param("id") Integer id);
}
