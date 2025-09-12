package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.TipoProducto;
import pe.edu.upc.textilconnect.repositories.ITipoProductoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.ITipoProductoService;

import java.util.List;

@Service
public class TipoProductoServiceImplement implements ITipoProductoService {
    @Autowired
    private ITipoProductoRepository dS;

    @Override
    public List<TipoProducto> list() {return this.dS.findAll();}

    @Override
    public void insert(TipoProducto tipoProducto) {this.dS.save(tipoProducto);}

    @Override
    public TipoProducto listId(int id) {
        return dS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        dS.deleteById(id);
    }

    @Override
    public void update(TipoProducto tipoProducto) {
        dS.save(tipoProducto);
    }

    @Override
    public List<TipoProducto> buscarService(String nombre) {
        return dS.buscarR(nombre);
    }
}
