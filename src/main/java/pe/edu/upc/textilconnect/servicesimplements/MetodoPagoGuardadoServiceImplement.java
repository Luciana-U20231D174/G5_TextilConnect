package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.MetodoPagoGuardado;
import pe.edu.upc.textilconnect.repositories.IMetodoPagoGuardadoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IMetodoPagoGuardadoService;

import java.util.List;

@Service
public class MetodoPagoGuardadoServiceImplement implements IMetodoPagoGuardadoService {

    @Autowired
    private IMetodoPagoGuardadoRepository metodoPagoGuardadoRepository;

    @Override
    public List<MetodoPagoGuardado> list() {
        return metodoPagoGuardadoRepository.findAll();
    }

    @Override
    public void insert(MetodoPagoGuardado metodoPagoGuardado) {
        metodoPagoGuardadoRepository.save(metodoPagoGuardado);
    }

    @Override
    public void delete(int id) {
        metodoPagoGuardadoRepository.deleteById(id);
    }

    @Override
    public void update(MetodoPagoGuardado metodoPagoGuardado) {
        metodoPagoGuardadoRepository.save(metodoPagoGuardado);
    }

    @Override
    public MetodoPagoGuardado listId(int id) {
        return metodoPagoGuardadoRepository.findById(id).orElse(null);
    }

    @Override
    public List<MetodoPagoGuardado> listarxusuario(int idusuario) {
        return metodoPagoGuardadoRepository.listarxusuario(idusuario);
    }

    @Override
    public List<MetodoPagoGuardado> buscarxmarcaTarjeta(String marca) {
        return metodoPagoGuardadoRepository.buscarxmarcaTarjeta(marca);
    }

}
