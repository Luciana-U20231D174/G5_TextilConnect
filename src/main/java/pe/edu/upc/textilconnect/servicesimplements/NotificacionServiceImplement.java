package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Notificacion;
import pe.edu.upc.textilconnect.repositories.INotificacionRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.INotificacionService;

import java.util.List;

@Service
public class NotificacionServiceImplement implements INotificacionService {

    @Autowired
    private INotificacionRepository notificacionRepository;

    @Override
    public List<Notificacion> list() {
        return notificacionRepository.findAll();
    }

    @Override
    public void insert(Notificacion notificacion) {
        notificacionRepository.save(notificacion);
    }

    @Override
    public Notificacion listId(int id) {
        return notificacionRepository.findById(id).orElse(new Notificacion());
    }

    @Override
    public void delete(int id) {notificacionRepository.deleteById(id);}

    @Override
    public void update(Notificacion Notificacion) { notificacionRepository.save(Notificacion);}
}
