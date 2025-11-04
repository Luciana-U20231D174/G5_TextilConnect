package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> list();
    public void insert(Usuario usuario);
    public Usuario listId(int id);
    public void delete(int id);
    public void update(Usuario usuario);
    Usuario listId(Integer id);
}
