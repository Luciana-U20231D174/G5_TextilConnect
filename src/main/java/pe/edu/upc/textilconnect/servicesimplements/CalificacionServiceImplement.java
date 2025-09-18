package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Calificacion;
import pe.edu.upc.textilconnect.repositories.ICalificacionRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.ICalificacionService;

import java.util.List;

@Service
public class CalificacionServiceImplement implements ICalificacionService {

    @Autowired
    private ICalificacionRepository calificacionRepository;

    @Override
    public List<Calificacion> list() {
        return calificacionRepository.findAll();
    }

    @Override
    public void insert(Calificacion calificacion) {
        calificacionRepository.save(calificacion);
    }

}
