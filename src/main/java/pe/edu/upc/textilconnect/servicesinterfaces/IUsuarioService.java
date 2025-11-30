package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> list();
    void insert(Usuario usuario);
    Usuario listId(int id);    // ✅ SOLO ESTE
    void delete(int id);
    void update(Usuario usuario);
}
