package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Rol;

import java.util.List;

@Repository
public interface IRolRepository extends JpaRepository<Rol,Integer> {
    @Query("Select rol from Rol rol where rol.nombreRol like %:nombre%")
    public List<Rol> buscarR(@Param("nombre") String nombre);
}