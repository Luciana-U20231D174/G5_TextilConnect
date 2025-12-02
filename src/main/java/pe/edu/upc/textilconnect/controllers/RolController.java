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
public class RolController {
    @Autowired
    private IRolService rolService;

    // listar → ADMIN, VENDEDOR, ESTUDIANTE
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping
    public List<RolDTO> listar() {
        return rolService.list().stream().map(rol -> {
            ModelMapper m = new ModelMapper();
            return m.map(rol, RolDTO.class);
        }).collect(Collectors.toList());
    }

    // insertar → solo ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody RolDTO dto) {
        ModelMapper m = new ModelMapper();
        Rol r = m.map(dto, Rol.class);
        rolService.insert(r);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Rol registrado correctamente.");
    }

    // eliminar → solo ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Rol rol = rolService.listId(id);
        if (rol == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        rolService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    // listar por id → ADMIN, VENDEDOR, ESTUDIANTE
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Rol rol = rolService.listId(id);
        if (rol == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        RolDTO dto = m.map(rol, RolDTO.class);
        return ResponseEntity.ok(dto);
    }

    // modificar → solo ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RolDTO dto) {
        ModelMapper m = new ModelMapper();
        Rol rol = m.map(dto, Rol.class);

        Rol existente = rolService.listId(rol.getIdRol());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + rol.getIdRol());
        }

        rolService.update(rol);
        return ResponseEntity.ok("Rol con ID " + rol.getIdRol() + " modificado correctamente.");
    }
}

