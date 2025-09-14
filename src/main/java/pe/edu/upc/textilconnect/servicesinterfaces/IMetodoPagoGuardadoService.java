package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.MetodoPagoGuardado;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.util.List;

public interface IMetodoPagoGuardadoService {
    List<MetodoPagoGuardado> list();
    public void insert(MetodoPagoGuardado metodoPagoGuardado);
}
