package pe.edu.upc.textilconnect.servicesimplements;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.ComentarioProyecto;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.repositories.IComentarioProyectoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IComentarioProyectoService;
import pe.edu.upc.textilconnect.servicesinterfaces.IComprobanteService;

import java.util.List;

@Service
public class ComentarioProyectoServiceImplement implements IComentarioProyectoService {

    @Autowired
    private IComentarioProyectoRepository comentarioProyectoRepository;

    @Override
    public List<ComentarioProyecto> list() {
        return comentarioProyectoRepository.findAll();
    }

    @Override
    public void insert(ComentarioProyecto comentarioProyecto) {
        comentarioProyectoRepository.save(comentarioProyecto);
    }

    @Override
    public ComentarioProyecto listId(int id) {
        return comentarioProyectoRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        comentarioProyectoRepository.deleteById(id);
    }

    @Override
    public void update(ComentarioProyecto comentarioProyecto) {
        comentarioProyectoRepository.save(comentarioProyecto);
}
}
