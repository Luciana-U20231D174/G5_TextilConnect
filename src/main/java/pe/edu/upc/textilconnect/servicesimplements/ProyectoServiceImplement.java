package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Proyecto;
import pe.edu.upc.textilconnect.repositories.IProyectoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IProyectoService;


import java.util.List;

@Service
public class ProyectoServiceImplement implements IProyectoService {
    @Autowired
    private IProyectoRepository proyectoRepository;

    @Override
    public List<Proyecto> list() {
        return proyectoRepository.findAll();
    }

    @Override
    public void insert(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    @Override
    public Proyecto listId(int id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        proyectoRepository.deleteById(id);

    }

    @Override
    public void update(Proyecto Proyecto) {
        proyectoRepository.save(Proyecto);
    }

}
