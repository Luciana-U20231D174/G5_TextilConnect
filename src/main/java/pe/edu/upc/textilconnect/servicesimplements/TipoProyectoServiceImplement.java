package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.TipoProyecto;
import pe.edu.upc.textilconnect.repositories.ITipoProyectoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.ITipoProyectoService;

import java.util.List;

@Service
public class TipoProyectoServiceImplement implements ITipoProyectoService {
    @Autowired
    private ITipoProyectoRepository dS;

    @Override
    public List<TipoProyecto> list() {return this.dS.findAll();}

    @Override
    public void insert(TipoProyecto tipoProyecto) {this.dS.save(tipoProyecto);}

    @Override
    public TipoProyecto listId(int id) {
        return dS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        dS.deleteById(id);
    }

    @Override
    public void update(TipoProyecto tipoProyecto) {
        dS.save(tipoProyecto);
    }

    @Override
    public List<TipoProyecto> buscarService(String nombre) {
        return dS.buscarR(nombre);
    }
}
