package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Operacion;
import pe.edu.upc.textilconnect.repositories.IOperacionRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IOperacionService;

import java.util.List;

@Service
public class OperacionServiceImplement implements IOperacionService {

    @Autowired
    private IOperacionRepository operacionRepository;

    @Override
    public List<Operacion> list() {
        return operacionRepository.findAll();
    }

    @Override
    public void insert(Operacion operacion) {
        operacionRepository.save(operacion);

    }
}
