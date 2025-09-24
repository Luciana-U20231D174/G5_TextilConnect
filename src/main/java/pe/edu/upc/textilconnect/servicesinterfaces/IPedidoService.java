package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Pedido;

import java.util.List;

public interface IPedidoService {
    public List<Pedido> list();
    public void insert(Pedido pedido);
}
