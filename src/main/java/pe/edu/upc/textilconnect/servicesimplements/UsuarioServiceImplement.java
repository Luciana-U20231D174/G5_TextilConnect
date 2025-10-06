package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.repositories.IUsuarioRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> list() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public void insert(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario listId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarxNombre(String nombre) {
        return usuarioRepository.buscarNombreU(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarxEmail(String email) {
        return usuarioRepository.buscarEmailU(email);
    }
}
