package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Rol;
import pe.edu.upc.textilconnect.repositories.IRolRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IRolService;

import java.util.List;

@Service
public class RolServiceImplement implements IRolService {
    @Autowired
    private IRolRepository dS;

    @Override
    public List<Rol> list() {return this.dS.findAll();}

    @Override
    public void insert(Rol rol) {this.dS.save(rol);}

    @Override
    public Rol listId(int id) {
        return dS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        dS.deleteById(id);
    }

    @Override
    public void update(Rol rol) {
        dS.save(rol);
    }

    @Override
    public List<Rol> buscarService(String nombre) {
        return dS.buscarR(nombre);
    }
}
