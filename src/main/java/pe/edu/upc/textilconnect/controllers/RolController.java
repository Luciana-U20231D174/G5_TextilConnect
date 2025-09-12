package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private IRolService dS;

    @GetMapping
    public List<RolDTO> listar() {
        return this.dS.list().stream().map((y) -> {
            ModelMapper m =new ModelMapper();
            return (RolDTO)m.map(y, RolDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody RolDTO dto) {
        ModelMapper m =new ModelMapper();
        Rol r = (Rol)m.map(dto,Rol.class);
        this.dS.insert(r);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Rol rol = dS.listId(id);
        if (rol == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        RolDTO dto = m.map(rol, RolDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Rol r = dS.listId(id);
        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        dS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody RolDTO dto) {
        ModelMapper m = new ModelMapper();
        Rol rol = m.map(dto, Rol.class);

        Rol existente = dS.listId(rol.getIdRol());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + rol.getIdRol());
        }
        dS.update(rol);
        return ResponseEntity.ok("Registro con ID " + rol.getIdRol() + " modificado correctamente.");
    }
}