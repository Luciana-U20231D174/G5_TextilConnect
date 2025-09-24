package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Tarjeta;
import pe.edu.upc.textilconnect.repositories.ITarjetaRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.ITarjetaService;

import java.util.List;

@Service
public class TarjetaServiceImplement implements ITarjetaService {

    @Autowired
    private ITarjetaRepository tarjetaRepository;


    @Override
    public List<Tarjeta> list() {
        return tarjetaRepository.findAll();
    }

    @Override
    public void insert(Tarjeta tarjeta) {
        tarjetaRepository.save(tarjeta);
    }

    @Override
    public void delete(int id) {
        tarjetaRepository.deleteById(id);
    }

    @Override
    public void update(Tarjeta tarjeta) {
        tarjetaRepository.save(tarjeta);
    }

    @Override
    public Tarjeta listId(int id) {
        return tarjetaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tarjeta> listarxusuario(int idusuario) {
        return tarjetaRepository.listarxusuario(idusuario);
    }

    @Override
    public List<Tarjeta> buscarxmarcaTarjeta(String marca) {
        return tarjetaRepository.buscarxmarcaTarjeta(marca);
    }
}
