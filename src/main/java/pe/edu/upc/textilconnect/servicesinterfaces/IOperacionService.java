package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Operacion;

import java.util.List;

public interface IOperacionService {
    List<Operacion> list();
    public void insert(Operacion operacion);
}
