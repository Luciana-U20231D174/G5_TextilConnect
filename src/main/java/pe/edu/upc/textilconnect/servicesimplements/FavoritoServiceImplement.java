package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Favorito;
import pe.edu.upc.textilconnect.repositories.IFavoritoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IFavoritoService;

import java.util.List;

@Service
public class FavoritoServiceImplement implements IFavoritoService {

    @Autowired
    private IFavoritoRepository favoritoRepository;

    @Override
    public List<Favorito> list() {
        return favoritoRepository.findAll();
    }

    @Override
    public void insert(Favorito favorito) {
        favoritoRepository.save(favorito);
    }

    @Override
    public Favorito listId(int id) {
        return favoritoRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        favoritoRepository.deleteById(id);
    }

    @Override
    public void update(Favorito favorito) {
        favoritoRepository.save(favorito);
    }
}
