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

    public List<Rol> list() {return this.dS.findAll();}

    public void insert(Rol rol) {this.dS.save(rol);}
}
