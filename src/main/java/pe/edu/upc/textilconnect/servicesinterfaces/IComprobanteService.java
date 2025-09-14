package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Comprobante;

import java.util.List;

public interface IComprobanteService {
    List<Comprobante> list();
    public void insert(Comprobante comprobante);
}
