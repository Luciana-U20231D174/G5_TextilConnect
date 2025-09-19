package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.MetodoPagoGuardado;

import java.util.List;

public interface IMetodoPagoGuardadoService {
    public List<MetodoPagoGuardado> list();
    public void insert(MetodoPagoGuardado metodoPagoGuardado);
    public void delete(int id);
    public void update(MetodoPagoGuardado metodoPagoGuardado );
    public MetodoPagoGuardado listId(int id);
    public List<MetodoPagoGuardado> listarxusuario(int idusuario);
    public List<MetodoPagoGuardado> buscarxmarcaTarjeta(String marca);
}
