package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.MetodoPago;
import pe.edu.upc.textilconnect.repositories.IMetodoPagoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IMetodoPagoService;

import java.util.List;

@Service
public class MetodoPagoServiceImplement implements IMetodoPagoService {
    @Autowired
    private IMetodoPagoRepository dS;

    @Override
    public List<MetodoPago> list() {return this.dS.findAll();}

    @Override
    public void insert(MetodoPago metodoPago) {this.dS.save(metodoPago);}

    @Override
    public MetodoPago listId(int id) {
        return dS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        dS.deleteById(id);
    }

    @Override
    public void update(MetodoPago metodoPago) {
        dS.save(metodoPago);
    }

    @Override
    public List<MetodoPago> buscarService(String nombre) {
        return dS.buscarR(nombre);
    }
}
