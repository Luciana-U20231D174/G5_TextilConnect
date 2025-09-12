package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.TipoProducto;

import java.util.List;

public interface ITipoProductoService {
    public List<TipoProducto> list();
    public void insert(TipoProducto tipoProducto);
    public TipoProducto listId(int id);
    public void delete(int id);
    public void update(TipoProducto tipoProducto);
    public List<TipoProducto> buscarService(String nombre);
}
