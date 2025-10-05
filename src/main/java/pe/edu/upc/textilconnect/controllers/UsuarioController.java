package pe.edu.upc.textilconnect.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOInsert;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOList;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;
import pe.edu.upc.textilconnect.dtos.UsuarioRequestDTO;
import pe.edu.upc.textilconnect.dtos.UsuarioResponseDTO;
import pe.edu.upc.textilconnect.entities.Rol;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;


    @PostMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<UsuarioResponseDTO> insertar(
            @RequestBody @Valid UsuarioRequestDTO dto) {   // ← aquí sí está el nombre: dto

        Usuario u = new Usuario();
        u.setNombreUsuario(dto.getNombreUsuario());        // ← sin coma extra
        u.setEmailUsuario(dto.getEmailUsuario());
        u.setUsername(dto.getUsername());
        u.setPassword(dto.getPassword());                  // encripta en el service si aplica
        u.setTelefonoUsuario(dto.getTelefonoUsuario());
        u.setDireccionUsuario(dto.getDireccionUsuario());
        u.setEstado(dto.getEstado());

        // rolesIds -> List<Rol> (solo ids)
        if (dto.getRolesIds() != null) {
            List<Rol> roles = dto.getRolesIds().stream()
                    .map(id -> { Rol r = new Rol(); r.setIdRol(id); return r; })
                    // .toList() // si usas Java 16+
                    .collect(java.util.stream.Collectors.toList()); // Java 8–15
            u.setRoles(roles);
        }

        usuarioService.insert(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(u));
    }


    @GetMapping
    public List<UsuarioDTOList> listar() {
        return this.usuarioService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (UsuarioDTOList)m.map(y, UsuarioDTOList.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.listId(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        return ResponseEntity.ok(toResponse(usuario));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioService.listId(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        usuarioService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> modificar(@PathVariable Integer id,
                                       @RequestBody @Valid UsuarioRequestDTO dto) {
        Usuario existente = usuarioService.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + id);
        }

        existente.setNombreUsuario(dto.getNombreUsuario());
        existente.setEmailUsuario(dto.getEmailUsuario());
        existente.setUsername(dto.getUsername());
        existente.setPassword(dto.getPassword());
        existente.setTelefonoUsuario(dto.getTelefonoUsuario());
        existente.setDireccionUsuario(dto.getDireccionUsuario());
        existente.setEstado(dto.getEstado());

        if (dto.getRolesIds() != null) {
            List<Rol> roles = dto.getRolesIds().stream().map(rid -> {
                        Rol r = new Rol();
                        r.setIdRol(rid);
                        return r;
                    })
                    // Si usas Java 8–15:
                    .collect(Collectors.toList());
            // Si usas Java 16+, puedes usar .toList() en lugar de collect
            existente.setRoles(roles);
        }

        usuarioService.update(existente);
        return ResponseEntity.ok("Registro con ID " + id + " modificado correctamente.");
    }


    @GetMapping("/bnombres")
    public ResponseEntity<?> buscarNombre(@RequestParam String n) {
        List<Usuario> usuarios = this.usuarioService.buscarxNombre(n);
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios con este nombre: " + n);
        } else {
            List<UsuarioDTOList> listaDTO = usuarios.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (UsuarioDTOList) m.map(x, UsuarioDTOList.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }

    @GetMapping("/bemails")
    public ResponseEntity<?> buscarEmail(@RequestParam String e) {
        List<Usuario> usuarios = this.usuarioService.buscarxEmail(e);
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios con este email: " + e);
        } else {
            List<UsuarioDTOList> listaDTO = usuarios.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (UsuarioDTOList) m.map(x, UsuarioDTOList.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }
    private UsuarioResponseDTO toResponse(Usuario u) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setIdUsuario(u.getIdUsuario());
        dto.setNombreUsuario(u.getNombreUsuario());
        dto.setEmailUsuario(u.getEmailUsuario());
        dto.setUsername(u.getUsername());
        dto.setTelefonoUsuario(u.getTelefonoUsuario());
        dto.setDireccionUsuario(u.getDireccionUsuario());
        dto.setFechaRegistroUsuario(u.getFechaRegistroUsuario()); // @CreationTimestamp
        dto.setPromedioCalificacion(u.getPromedioCalificacion());
        dto.setTotalCalificacion(u.getTotalCalificacion());
        dto.setEstado(u.getEstado());
        dto.setRoles(u.getRoles() == null ? List.of()
                : u.getRoles().stream().map(Rol::getNombreRol).collect(Collectors.toList()));
        return dto;
    }

}
