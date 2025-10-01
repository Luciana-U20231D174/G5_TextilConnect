package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.PedidoItem;

import java.util.List;

public interface IPedidoItemService {
    public List<PedidoItem> list();
    public void insert(PedidoItem pedidoItem);
}
