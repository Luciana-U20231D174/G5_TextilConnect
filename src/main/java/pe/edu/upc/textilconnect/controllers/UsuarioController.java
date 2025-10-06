package pe.edu.upc.textilconnect.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOInsert;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOList;
import pe.edu.upc.textilconnect.entities.Rol;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager em;

    // POST: crear usuario
    @PostMapping
    public ResponseEntity<UsuarioDTOList> insertar(@RequestBody UsuarioDTOInsert udto) {
        Usuario u = new Usuario();

        u.setNombreUsuario(udto.getNombreUsuario());
        u.setEmailUsuario(udto.getEmailUsuario());
        u.setUsername(udto.getUsername());

        // Determinar contraseña (password o contrasenaUsuario)
        String rawPassword = (udto.getPassword() != null && !udto.getPassword().isBlank())
                ? udto.getPassword()
                : udto.getContrasenaUsuario();
        if (rawPassword == null || rawPassword.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        u.setPassword(passwordEncoder.encode(rawPassword));

        u.setTelefonoUsuario(udto.getTelefonoUsuario());
        u.setDireccionUsuario(udto.getDireccionUsuario());
        u.setFechaRegistroUsuario(
                udto.getFechaRegistroUsuario() != null ? udto.getFechaRegistroUsuario() : java.time.LocalDate.now()
        );
        u.setPromedioCalificacion(java.math.BigDecimal.ZERO);
        u.setTotalCalificacion(0);
        u.setEstado(udto.getEstado() != null ? udto.getEstado() : Boolean.TRUE);

        // CLAVE: adjuntar roles existentes (managed) con getReference
        if (udto.getRolesIds() != null && !udto.getRolesIds().isEmpty()) {
            List<Rol> roles = udto.getRolesIds().stream()
                    .map(id -> em.getReference(Rol.class, id))
                    .toList();
            u.setRoles(roles);
        }

        usuarioService.insert(u);

        UsuarioDTOList resp = new UsuarioDTOList();
        resp.setNombreUsuario(u.getNombreUsuario());
        resp.setEmailUsuario(u.getEmailUsuario());
        resp.setFechaRegistroUsuario(u.getFechaRegistroUsuario());
        resp.setPromedioCalifacion(u.getPromedioCalificacion());
        resp.setTotalCalificacion(u.getTotalCalificacion());
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    // GET: listar
    @GetMapping
    public List<UsuarioDTOList> listar() {
        return usuarioService.list().stream().map(u -> {
            UsuarioDTOList dto = new UsuarioDTOList();
            dto.setNombreUsuario(u.getNombreUsuario());
            dto.setEmailUsuario(u.getEmailUsuario());
            dto.setFechaRegistroUsuario(u.getFechaRegistroUsuario());
            dto.setPromedioCalifacion(u.getPromedioCalificacion());
            dto.setTotalCalificacion(u.getTotalCalificacion());
            return dto;
        }).collect(Collectors.toList());
    }

    // GET: por id
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario u = usuarioService.listId(id);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        UsuarioDTOList dto = new UsuarioDTOList();
        dto.setNombreUsuario(u.getNombreUsuario());
        dto.setEmailUsuario(u.getEmailUsuario());
        dto.setFechaRegistroUsuario(u.getFechaRegistroUsuario());
        dto.setPromedioCalifacion(u.getPromedioCalificacion());
        dto.setTotalCalificacion(u.getTotalCalificacion());
        return ResponseEntity.ok(dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Usuario u = usuarioService.listId(id);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        usuarioService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    // PUT (simple, sin tocar password ni roles)
    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") Integer id, @RequestBody UsuarioDTOInsert udto) {
        Usuario existente = usuarioService.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        existente.setNombreUsuario(udto.getNombreUsuario());
        existente.setEmailUsuario(udto.getEmailUsuario());
        existente.setTelefonoUsuario(udto.getTelefonoUsuario());
        existente.setDireccionUsuario(udto.getDireccionUsuario());
        if (udto.getEstado() != null) existente.setEstado(udto.getEstado());

        usuarioService.update(existente);
        return ResponseEntity.ok("Usuario con ID " + id + " modificado correctamente.");
    }
}
