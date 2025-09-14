package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.MetodoPagoGuardado;

import java.util.List;

public interface IMetodoPagoGuardadoService {
    public List<MetodoPagoGuardado> list();
    public void insert(MetodoPagoGuardado metodoPagoGuardado);
}
