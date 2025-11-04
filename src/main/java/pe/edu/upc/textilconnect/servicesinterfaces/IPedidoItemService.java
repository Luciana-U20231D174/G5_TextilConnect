package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Notificacion;
import pe.edu.upc.textilconnect.entities.PedidoItem;

import java.util.List;

public interface IPedidoItemService {
    public List<PedidoItem> list();
    public void insert(PedidoItem pedidoItem);
    public PedidoItem listId(int id);
    public void delete(int id);
    public void update(PedidoItem PedidoItem);
}
