package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.TipoProyecto;
import java.util.List;

public interface ITipoProyectoService {
    public List<TipoProyecto> list();
    public void insert(TipoProyecto tipoProyecto);
    public TipoProyecto listId(int id);
    public void delete(int id);
    public void update(TipoProyecto tipoProyecto);
    public List<TipoProyecto> buscarService(String nombre);
}
