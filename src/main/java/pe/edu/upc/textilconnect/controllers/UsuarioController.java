package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOInsert;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOList;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOAdminUpdate;
import pe.edu.upc.textilconnect.entities.Rol;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UsuarioController {
    @Autowired
    private IUsuarioService uS;

    // ---------- LISTAR (tabla) ----------
    @PreAuthorize("permitAll()")
    @GetMapping
    public List<UsuarioDTOList> listar() {
        return uS.list().stream().map(u -> {
            UsuarioDTOList dto = new UsuarioDTOList();
            dto.setIdUsuario(u.getIdUsuario());
            dto.setNombreUsuario(u.getNombreUsuario());
            dto.setEmailUsuario(u.getEmailUsuario());
            dto.setUsername(u.getUsername());
            dto.setTelefonoUsuario(u.getTelefonoUsuario());
            dto.setNombreRol(u.getRol() != null ? u.getRol().getNombreRol() : null);
            dto.setPromedioCalificacion(u.getPromedioCalificacion());
            dto.setTotalCalificacion(u.getTotalCalificacion());
            return dto;
        }).collect(Collectors.toList());
    }

    // ---------- INSERTAR ----------
    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody UsuarioDTOInsert dto) {
        try {
            if (dto.getIdRol() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El rol es obligatorio");
            }

            ModelMapper m = new ModelMapper();
            Usuario u = m.map(dto, Usuario.class);
            Rol r = new Rol();
            r.setIdRol(dto.getIdRol());
            u.setRol(r);

            uS.insert(u);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflicto de datos: " + e.getMostSpecificCause().getMessage());
        }
    }

    // ---------- LISTAR POR ID (para EDICIÓN ADMIN) ----------
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario u = uS.listId(id);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe usuario con ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        UsuarioDTOAdminUpdate dto = m.map(u, UsuarioDTOAdminUpdate.class);
        if (u.getRol() != null) dto.setIdRol(u.getRol().getIdRol());

        return ResponseEntity.ok(dto);
    }

    // ---------- ELIMINAR (lógico) ----------
    @PreAuthorize("permitAll()")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int id) {
        try {
            uS.delete(id);
            return ResponseEntity.ok("Usuario " + id + " eliminado/deshabilitado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar usuario con ID " + id + ": " + e.getMessage());
        }
    }

    // ---------- MODIFICAR (ADMIN, sin password) ----------
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody UsuarioDTOAdminUpdate dto) {
        try {
            Usuario existe = uS.listId(dto.getIdUsuario());
            if (existe == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No existe usuario con ID: " + dto.getIdUsuario());
            }

            if (dto.getIdRol() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El rol es obligatorio");
            }

            ModelMapper m = new ModelMapper();
            Usuario u = m.map(dto, Usuario.class);
            Rol r = new Rol();
            r.setIdRol(dto.getIdRol());
            u.setRol(r);

            uS.update(u);
            return ResponseEntity.ok("Usuario con ID " + dto.getIdUsuario() + " modificado correctamente.");
        } catch (DataIntegrityViolationException e) {
            String detalle = (e.getMostSpecificCause() != null ? e.getMostSpecificCause().getMessage() : e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflicto de datos en BD: " + detalle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al modificar: " + e.getMessage());
        }
    }
}