package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Producto;

import java.util.List;

public interface IProductoService {
    List<Producto> list();
    public void insert(Producto producto);
}
