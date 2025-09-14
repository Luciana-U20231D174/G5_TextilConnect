package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.OperacionItem;

import java.util.List;

public interface IOperacionItemService {
    List<OperacionItem> list();
    public void insert(OperacionItem operacionItem);
}
