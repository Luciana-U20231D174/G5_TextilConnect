package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Calificacion;

import java.util.List;

public interface ICalificacionService {
    List<Calificacion> list();
    public void insert(Calificacion calificacion);
}
