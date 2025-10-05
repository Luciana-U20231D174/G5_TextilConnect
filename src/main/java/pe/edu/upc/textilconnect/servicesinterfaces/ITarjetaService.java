package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Tarjeta;

import java.util.List;

public interface ITarjetaService {
    public List<Tarjeta> list();
    public void insert(Tarjeta tarjeta);
    public void delete(int id);
    public void update(Tarjeta tarjeta);
    public Tarjeta listId(int id);
    public List<Tarjeta> listarxusuario(int idUsuario);
    public int contarxmarca(String marca);
}
