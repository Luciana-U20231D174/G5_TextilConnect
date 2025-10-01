package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Entrega;
import pe.edu.upc.textilconnect.entities.Producto;

import java.util.List;

public interface IEntregaService {
    List<Entrega> list();
    public void insert(Entrega entrega);
    public Entrega listId(int id);
    public void update(Entrega entrega);
    public List<Entrega> buscarxEstado(String estado);
    List<Entrega> buscarxTipo(String tipo);

}
