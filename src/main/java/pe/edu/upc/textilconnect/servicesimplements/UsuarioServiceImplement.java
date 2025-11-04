package pe.edu.upc.textilconnect.servicesimplements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.repositories.IRolRepository;
import pe.edu.upc.textilconnect.repositories.IUsuarioRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository uS;

    @Autowired
    private IRolRepository rS;

    @Override
    public List<Usuario> list() {
        return uS.findAll();
    }

    @Override
    public void insert(Usuario usuario) {
        uS.save(usuario);
    }

    @Override
    public Usuario listId(int id) {
        return uS.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(int id) {
        rS.deleteByUsuarioId(id); // borra hijos primero
        uS.deleteById(id);
    }

    @Override
    public void update(Usuario usuario) {
        Usuario existente = uS.findById(usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuario.getIdUsuario()));

        // Actualizamos los campos básicos
        existente.setNombreUsuario(usuario.getNombreUsuario());
        existente.setEmailUsuario(usuario.getEmailUsuario());
        existente.setUsername(usuario.getUsername());
        existente.setTelefonoUsuario(usuario.getTelefonoUsuario());
        existente.setDireccionUsuario(usuario.getDireccionUsuario());
        existente.setEnabled(usuario.getEnabled());
        existente.setPromedioCalificacion(usuario.getPromedioCalificacion());
        existente.setTotalCalificacion(usuario.getTotalCalificacion());

        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            usuario.setRoles(existente.getRoles());
        } else {
            existente.setRoles(usuario.getRoles());
        }

        uS.save(existente);
    }

    @Override
    public Usuario listId(Integer id) {
        return uS.findById(id).orElse(null);
    }


}
