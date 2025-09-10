package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Rol;

import java.util.List;

public interface IRolService {
    List<Rol> list();

    void insert(Rol rol);
}
