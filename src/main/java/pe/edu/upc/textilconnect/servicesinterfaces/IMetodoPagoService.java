package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.MetodoPago;

import java.util.List;

public interface IMetodoPagoService {
    public List<MetodoPago> list();
    public void insert(MetodoPago metodoPago);
    public MetodoPago listId(int id);
    public void delete(int id);
    public void update(MetodoPago metodoPago);
    public List<MetodoPago> buscarService(String nombre);
}
