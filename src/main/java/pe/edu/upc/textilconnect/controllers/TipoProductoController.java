package pe.edu.upc.textilconnect.controllers;

import jakarta.annotation.security.PermitAll;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.TipoProductoDTO;
import pe.edu.upc.textilconnect.entities.TipoProducto;
import pe.edu.upc.textilconnect.servicesinterfaces.ITipoProductoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tiposproductos")
public class TipoProductoController {

    @Autowired
    private ITipoProductoService dS;

    @PreAuthorize("permitAll()")
    @GetMapping("/listar")
    public List<TipoProductoDTO> listar() {
        return this.dS.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (TipoProductoDTO) m.map(y, TipoProductoDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public void insertar(@RequestBody TipoProductoDTO dto) {
        ModelMapper m = new ModelMapper();
        TipoProducto prod = (TipoProducto) m.map(dto, TipoProducto.class);
        this.dS.insert(prod);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")  // 👈 IMPORTANTE
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        TipoProducto prod = dS.listId(id);
        if (prod == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        TipoProductoDTO dto = m.map(prod, TipoProductoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("permitAll()")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody TipoProductoDTO dto) {
        ModelMapper m = new ModelMapper();
        TipoProducto prod = m.map(dto, TipoProducto.class);

        TipoProducto existente = dS.listId(prod.getIdTipoProducto());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + prod.getIdTipoProducto());
        }
        dS.update(prod);
        return ResponseEntity.ok("Registro con ID " + prod.getIdTipoProducto() + " modificado correctamente.");
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Integer id) {
        TipoProducto tipo = dS.listId(id);

        if (tipo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        try {
            dS.delete(id);
            return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar porque está asociado a productos.");
        }
    }
}

