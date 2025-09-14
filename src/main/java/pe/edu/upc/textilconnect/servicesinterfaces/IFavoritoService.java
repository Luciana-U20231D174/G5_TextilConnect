package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Favorito;

import java.util.List;

public interface IFavoritoService {
    public List<Favorito> list();
    public void insert(Favorito favorito);
}
