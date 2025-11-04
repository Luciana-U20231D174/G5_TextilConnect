package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Comprobante;
import pe.edu.upc.textilconnect.entities.Notificacion;

import java.util.List;

public interface INotificacionService {
    public List<Notificacion> list();
    public void insert(Notificacion notificacion);
    public Notificacion listId(int id);
    public void delete(int id);
    public void update(Notificacion Notificacion);
}
