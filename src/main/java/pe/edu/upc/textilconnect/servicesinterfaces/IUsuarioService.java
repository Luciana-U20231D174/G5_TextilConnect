package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> list();
    public void insert(Usuario usuario);
}
