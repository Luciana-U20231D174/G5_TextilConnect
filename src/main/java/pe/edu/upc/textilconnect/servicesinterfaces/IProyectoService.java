package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Proyecto;

import java.util.List;

public interface IProyectoService {
    List<Proyecto> list();
    public void insert(Proyecto proyecto);
}
