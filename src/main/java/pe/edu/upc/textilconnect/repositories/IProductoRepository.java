package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Producto;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto,Integer> {
    @Query("Select producto from Producto producto where producto.nombreProducto like %:nombre%")
    public List<Producto> buscarNombrePd(@Param("nombre") String nombre);

    @Query("Select producto from Producto producto where producto.categoriaProducto like %:categoria%")
    public List<Producto> buscarCategoriaPd(@Param("categoria") String categoria);

    @Query("Select producto from Producto producto where producto.colorProducto like %:color%")
    public List<Producto> buscarColorPd(@Param("color") String color);

    @Query("SELECT p FROM Producto p WHERE p.precioProducto BETWEEN :min AND :max")
    List<Producto> buscarPrecioPd(@Param("min") BigDecimal min,
                                        @Param("max") BigDecimal max);
}
