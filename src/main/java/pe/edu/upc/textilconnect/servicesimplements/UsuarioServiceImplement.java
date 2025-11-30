package pe.edu.upc.textilconnect.servicesimplements;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Rol;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> list() {
        return uS.listarActivos();
    }

    @Override
    public void insert(Usuario usuario) {
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }
        String passwordEncriptado = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptado);

        if (usuario.getRol() != null && usuario.getRol().getIdRol() != null) {
            Rol rol = rS.findById(usuario.getRol().getIdRol())
                    .orElseThrow(() -> new RuntimeException(
                            "Rol no encontrado con ID: " + usuario.getRol().getIdRol()));
            usuario.setRol(rol);
        }

        uS.save(usuario);
    }

    @Override
    public Usuario listId(int id) {
        return uS.findById(id).orElse(null);
    }

    // 💡 Borrado lógico: enabled = false
    @Override
    @Transactional
    public void delete(int id) {
        Usuario usuario = uS.findById(id).orElse(null);

        if (usuario != null) {
            usuario.setEnabled(false);   // 👈 borrado lógico
            uS.save(usuario);
            System.out.println("Usuario " + id + " deshabilitado (borrado lógico).");
        } else {
            System.out.println("Intento de eliminar usuario inexistente con ID: " + id);
        }
    }

    //    @Override
//    @Transactional
//    public void update(Usuario usuarioConDatos) {
//        Usuario existente = uS.findById(usuarioConDatos.getIdUsuario())
//                .orElseThrow(() -> new RuntimeException(
//                        "Usuario no encontrado con ID: " + usuarioConDatos.getIdUsuario()));
//
//        existente.setNombreUsuario(usuarioConDatos.getNombreUsuario());
//        existente.setEmailUsuario(usuarioConDatos.getEmailUsuario());
//        existente.setUsername(usuarioConDatos.getUsername());
//        existente.setTelefonoUsuario(usuarioConDatos.getTelefonoUsuario());
//        existente.setDireccionUsuario(usuarioConDatos.getDireccionUsuario());
//        existente.setFechaRegistroUsuario(usuarioConDatos.getFechaRegistroUsuario());
//        existente.setEnabled(usuarioConDatos.getEnabled());
//        existente.setPromedioCalificacion(usuarioConDatos.getPromedioCalificacion());
//        existente.setTotalCalificacion(usuarioConDatos.getTotalCalificacion());
//
//        // ✅ Solo encriptar si viene contraseña nueva
//        if (usuarioConDatos.getPassword() != null && !usuarioConDatos.getPassword().isBlank()) {
//            String hash = passwordEncoder.encode(usuarioConDatos.getPassword());
//            existente.setPassword(hash);
//        }
//
//        if (usuarioConDatos.getRol() != null && usuarioConDatos.getRol().getIdRol() != null) {
//            Rol rol = rS.findById(usuarioConDatos.getRol().getIdRol())
//                    .orElseThrow(() -> new RuntimeException(
//                            "Rol no encontrado con ID: " + usuarioConDatos.getRol().getIdRol()));
//            existente.setRol(rol);
//        }
//
//        uS.save(existente);
//    }
    @Override
    @Transactional
    public void update(Usuario usuarioConDatos) {
        Usuario existente = uS.findById(usuarioConDatos.getIdUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "Usuario no encontrado con ID: " + usuarioConDatos.getIdUsuario()));

        // ✅ Solo actualizamos datos NO sensibles
        existente.setNombreUsuario(usuarioConDatos.getNombreUsuario());
        existente.setEmailUsuario(usuarioConDatos.getEmailUsuario());
        existente.setUsername(usuarioConDatos.getUsername());
        existente.setTelefonoUsuario(usuarioConDatos.getTelefonoUsuario());
        existente.setDireccionUsuario(usuarioConDatos.getDireccionUsuario());
        existente.setFechaRegistroUsuario(usuarioConDatos.getFechaRegistroUsuario());
        existente.setEnabled(usuarioConDatos.getEnabled());
        existente.setPromedioCalificacion(usuarioConDatos.getPromedioCalificacion());
        existente.setTotalCalificacion(usuarioConDatos.getTotalCalificacion());

        // ❌ OJO: NO hay nada de password aquí.
        //    La contraseña solo se define en insert(), nunca en update().

        if (usuarioConDatos.getRol() != null && usuarioConDatos.getRol().getIdRol() != null) {
            Rol rol = rS.findById(usuarioConDatos.getRol().getIdRol())
                    .orElseThrow(() -> new RuntimeException(
                            "Rol no encontrado con ID: " + usuarioConDatos.getRol().getIdRol()));
            existente.setRol(rol);
        }

        uS.save(existente);
    }
}
