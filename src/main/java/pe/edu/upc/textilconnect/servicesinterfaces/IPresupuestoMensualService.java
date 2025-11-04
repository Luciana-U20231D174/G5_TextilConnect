package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Notificacion;
import pe.edu.upc.textilconnect.entities.PresupuestoMensual;

import java.util.List;

public interface IPresupuestoMensualService {
    public List<PresupuestoMensual> list();
    public void insert(PresupuestoMensual presupuestoMensual);
    public PresupuestoMensual listId(int id);
    public void delete(int id);
    public void update(PresupuestoMensual PresupuestoMensual);
}
