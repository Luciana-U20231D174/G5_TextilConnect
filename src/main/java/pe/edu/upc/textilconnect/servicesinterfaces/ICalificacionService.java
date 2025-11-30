package pe.edu.upc.textilconnect.servicesinterfaces;// pe.edu.upc.textilconnect.servicesinterfaces.ICalificacionService
import pe.edu.upc.textilconnect.entities.Calificacion;
import java.util.List;

public interface ICalificacionService {
    List<Calificacion> list();
    void insert(Calificacion calificacion);
    Calificacion listId(int id);
    void delete(int id);
    void update(Calificacion calificacion);
}
