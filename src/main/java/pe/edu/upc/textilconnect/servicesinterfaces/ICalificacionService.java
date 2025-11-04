package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Calificacion;
import pe.edu.upc.textilconnect.entities.ComentarioProyecto;

import java.util.List;

public interface ICalificacionService {
    List<Calificacion> list();
    public void insert(Calificacion calificacion);
    public Calificacion listId(int id);
    public void delete(int id);
    public void update(Calificacion calificacion);
}
