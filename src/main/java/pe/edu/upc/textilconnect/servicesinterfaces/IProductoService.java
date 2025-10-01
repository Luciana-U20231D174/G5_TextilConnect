package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.entities.Rol;

import java.math.BigDecimal;
import java.util.List;

public interface IProductoService {
    List<Producto> list();
    public void insert(Producto producto);
    public Producto listId(int id);
    public void delete(int id);
    public void update(Producto producto);
    public List<Producto> buscarxNombre(String nombre);
    public List<Producto> buscarxCategoria(String categoria);
    public List<Producto> buscarxColor(String color);
    List<Producto> buscarxPrecio(BigDecimal min, BigDecimal max);

}
