package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.entities.Proyecto;

import java.util.List;

@Repository
public interface IProyectoRepository extends JpaRepository<Proyecto,Integer> {
    @Query("Select proyecto from Proyecto proyecto where proyecto.tituloProyecto like %:titulo%")
    public List<Proyecto> buscarTituloPy(@Param("titulo") String titulo);
}
