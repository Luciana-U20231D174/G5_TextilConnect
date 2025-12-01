package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.RolDTO;
import pe.edu.upc.textilconnect.entities.Rol;
import pe.edu.upc.textilconnect.servicesinterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "[http://localhost:4200](http://localhost:4200)")
public class RolController {
    @Autowired
    private IRolService rS;

    // LISTAR TODOS (solo ADMIN)
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RolDTO> listar() {
        return rS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, RolDTO.class);
        }).collect(Collectors.toList());
    }

    // INSERTAR (solo ADMIN)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> insertar(@RequestBody RolDTO dto) {
        try {
            ModelMapper m = new ModelMapper();
            Rol r = m.map(dto, Rol.class);
            r.setIdRol(null);
            rS.insert(r);
            return ResponseEntity.status(HttpStatus.CREATED).body("Rol creado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un rol con el nombre: " + dto.getNombreRol());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al registrar el rol.");
        }
    }

    // BUSCAR POR ID (solo ADMIN)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Rol r = rS.listId(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        RolDTO dto = m.map(r, RolDTO.class);
        return ResponseEntity.ok(dto);
    }

    // ELIMINAR (solo ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        try {
            Rol r = rS.listId(id);
            if (r == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No existe un registro con el ID: " + id);
            }
            rS.delete(id);
            return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el rol porque está asociado a otros registros.");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al eliminar el rol.");
        }
    }

    // MODIFICAR (solo ADMIN)
    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody RolDTO dto) {
        try {
            ModelMapper m = new ModelMapper();
            Rol r = m.map(dto, Rol.class);

            Rol existente = rS.listId(r.getIdRol());
            if (existente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se puede modificar. No existe un registro con el ID: " + r.getIdRol());
            }

            rS.update(r);
            return ResponseEntity.ok("Registro con ID " + r.getIdRol() + " modificado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un rol con el nombre: " + dto.getNombreRol());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al modificar el rol.");
        }
    }
}
