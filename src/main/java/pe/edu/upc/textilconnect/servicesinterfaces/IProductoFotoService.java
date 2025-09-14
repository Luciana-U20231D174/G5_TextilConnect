package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.ProductoFoto;

import java.util.List;

public interface IProductoFotoService {
    public List<ProductoFoto> list();
    public void insert (ProductoFoto productoFoto);
}
