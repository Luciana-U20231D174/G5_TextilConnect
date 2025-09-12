package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.TipoProducto;

import java.util.List;

@Repository
public interface ITipoProductoRepository extends JpaRepository<TipoProducto, Integer> {
    @Query("Select tipoProducto from TipoProducto tipoProducto where tipoProducto.nombreTipoProducto like %:nombre%")
    public List<TipoProducto> buscarR(@Param("nombre") String nombre);
}
