package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.TipoDocumentoDTO;
import pe.edu.upc.textilconnect.entities.TipoDocumento;
import pe.edu.upc.textilconnect.servicesinterfaces.ITipoDocumentoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tiposdocumentos")
@CrossOrigin(origins = "[http://localhost:4200](http://localhost:4200)")
public class TipoDocumentoController {
    @Autowired
    private ITipoDocumentoService dS;

    // LISTAR (ADMIN, VENDEDOR o ESTUDIANTE)
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping("/listar")
    public List<TipoDocumentoDTO> listar() {
        return this.dS.list().stream().map(y -> {
            ModelMapper m = new ModelMapper();
            return m.map(y, TipoDocumentoDTO.class);
        }).collect(Collectors.toList());
    }

    // INSERTAR (solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void insertar(@RequestBody TipoDocumentoDTO dto) {
        ModelMapper m = new ModelMapper();
        TipoDocumento td = m.map(dto, TipoDocumento.class);
        this.dS.insert(td);
    }

    // LISTAR POR ID (ADMIN, VENDEDOR o ESTUDIANTE)
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        TipoDocumento tip = dS.listId(id);
        if (tip == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        TipoDocumentoDTO dto = m.map(tip, TipoDocumentoDTO.class);
        return ResponseEntity.ok(dto);
    }

    // ELIMINAR (solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        TipoDocumento td = dS.listId(id);
        if (td == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        dS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    // MODIFICAR (solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody TipoDocumentoDTO dto) {
        ModelMapper m = new ModelMapper();
        TipoDocumento tp = m.map(dto, TipoDocumento.class);

        TipoDocumento existente = dS.listId(tp.getIdTipoDocumento());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + tp.getIdTipoDocumento());
        }
        dS.update(tp);
        return ResponseEntity.ok("Registro con ID " + tp.getIdTipoDocumento() + " modificado correctamente.");
    }

    // BÚSQUEDA POR NOMBRE (ADMIN, VENDEDOR o ESTUDIANTE)
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping("/bnombres")
    public ResponseEntity<?> buscar(@RequestParam String n) {
        List<TipoDocumento> tipoDocumentos = this.dS.buscarService(n);
        if (tipoDocumentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron documentos con este nombre: " + n);
        } else {
            List<TipoDocumentoDTO> listaDTO = tipoDocumentos.stream().map(x -> {
                ModelMapper m = new ModelMapper();
                return m.map(x, TipoDocumentoDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }
}
