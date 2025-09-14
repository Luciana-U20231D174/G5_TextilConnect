package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.PresupuestoMensual;

import java.util.List;

public interface IPresupuestoMensualService {
    public List<PresupuestoMensual> list();
    public void insert(PresupuestoMensual presupuestoMensual);
}
