package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.entities.Proyecto;

import java.util.List;

public interface IProyectoService {
    List<Proyecto> list();
    public void insert(Proyecto proyecto);
    Proyecto listId(int id);
    public void delete(int id);
    public void update(Proyecto Proyecto);
    public List<Proyecto> buscarxTitulo(String titulo);
    public List<Proyecto> buscarxUsuario(Integer idUsuario);
}
