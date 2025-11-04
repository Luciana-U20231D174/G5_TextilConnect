package pe.edu.upc.textilconnect.servicesimplements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Rol;
import pe.edu.upc.textilconnect.repositories.IRolRepository;

import java.util.List;

@Service
public class RolServiceImplement implements IRolService {
    @Autowired
    private IRolRepository rS;

    @Override
    public List<Rol> list(){return rS.findAll();}

    @Override
    public void insert(Rol rol){rS.save(rol);}

    @Override
    public Rol listId(int id) {
        return rS.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(int id) {
        rS.deleteRolById(id);
    }

    @Override
    public void update(Rol rol) {
        rS.save(rol);
    }

    @Override
    public Rol listId(Integer id) {
        return rS.findById(id).orElse(null);
    }


}