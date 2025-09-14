package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Entrega;

import java.util.List;

public interface IEntregaService {
    List<Entrega> list();
    public void insert(Entrega entrega);
}
