package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.TipoProyectoDTO;
import pe.edu.upc.textilconnect.entities.TipoProyecto;
import pe.edu.upc.textilconnect.servicesinterfaces.ITipoProyectoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tiposproyectos")
@CrossOrigin(origins = "[http://localhost:4200](http://localhost:4200)")
public class TipoProyectoController {
    @Autowired
    private ITipoProyectoService dS;

    // LISTAR (ADMIN, VENDEDOR o ESTUDIANTE)
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping
    public List<TipoProyectoDTO> listar() {
        return this.dS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, TipoProyectoDTO.class);
        }).collect(Collectors.toList());
    }

    // INSERTAR (solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void insertar(@RequestBody TipoProyectoDTO dto) {
        ModelMapper m = new ModelMapper();
        TipoProyecto proy = m.map(dto, TipoProyecto.class);
        this.dS.insert(proy);
    }

    // LISTAR POR ID (ADMIN, VENDEDOR o ESTUDIANTE)
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        TipoProyecto proy = dS.listId(id);
        if (proy == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        TipoProyectoDTO dto = m.map(proy, TipoProyectoDTO.class);
        return ResponseEntity.ok(dto);
    }

    // MODIFICAR (solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody TipoProyectoDTO dto) {
        ModelMapper m = new ModelMapper();
        TipoProyecto proy = m.map(dto, TipoProyecto.class);

        TipoProyecto existente = dS.listId(proy.getIdTipoProyecto());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + proy.getIdTipoProyecto());
        }
        dS.update(proy);
        return ResponseEntity.ok("Registro con ID " + proy.getIdTipoProyecto() + " modificado correctamente.");
    }

    // ELIMINAR (solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
        TipoProyecto proy = dS.listId(id);
        if (proy == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        try {
            dS.delete(id);
            return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar porque está asociado a otros registros.");
        }
    }

    // BÚSQUEDA POR NOMBRE (ADMIN, VENDEDOR o ESTUDIANTE)
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping("/bnombres")
    public ResponseEntity<?> buscar(@RequestParam String n) {
        List<TipoProyecto> tipoProyectos = this.dS.buscarService(n);
        if (tipoProyectos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron proyectos con este nombre: " + n);
        } else {
            List<TipoProyectoDTO> listaDTO = tipoProyectos.stream().map(x -> {
                ModelMapper m = new ModelMapper();
                return m.map(x, TipoProyectoDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }
}
