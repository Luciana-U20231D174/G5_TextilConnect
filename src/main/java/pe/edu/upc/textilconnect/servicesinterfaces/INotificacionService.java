package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Notificacion;

import java.util.List;

public interface INotificacionService {
    List<Notificacion> list();
    public void insert(Notificacion notificacion);
}
