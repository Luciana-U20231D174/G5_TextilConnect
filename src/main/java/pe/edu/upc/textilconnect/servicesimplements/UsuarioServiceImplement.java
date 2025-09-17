package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.repositories.IUsuarioRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    @Override
    public void insert(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario listId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void update(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> buscarxNombre(String nombre) {
        return usuarioRepository.buscarNombreU(nombre);
    }

    @Override
    public List<Usuario> buscarxEmail(String email) {
        return usuarioRepository.buscarEmailU(email);
    }


}