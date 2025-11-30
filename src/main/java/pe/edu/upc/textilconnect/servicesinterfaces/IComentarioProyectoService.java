package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.ComentarioProyecto;
import pe.edu.upc.textilconnect.entities.Producto;

import java.util.List;

public interface IComentarioProyectoService {
    List<ComentarioProyecto> list();
    public void insert(ComentarioProyecto comentarioProyecto);
    public ComentarioProyecto listId(int id);
    public void delete(int id);
    public void update(ComentarioProyecto comentarioProyecto);
    public List<ComentarioProyecto> listarPorProyecto(int idProyecto);
    public int contarPorProyecto(int idProyecto);
    public List<Object[]> contarComentariosPorProyecto();
}