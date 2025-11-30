package pe.edu.upc.textilconnect.controllers;

import jakarta.annotation.security.PermitAll;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ProductoFotoDTO;
import pe.edu.upc.textilconnect.entities.ProductoFoto;
import pe.edu.upc.textilconnect.servicesinterfaces.IProductoFotoService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productosfotos")
public class ProductoFotoController {

    @Autowired
    private IProductoFotoService productoFotoService;

    // INSERTAR
    @PreAuthorize("hasAuthority('VENDEDOR')")
    @PostMapping
    public ResponseEntity<Void> insertar(@RequestBody ProductoFotoDTO dto) {
        ModelMapper m = new ModelMapper();
        ProductoFoto pf = m.map(dto, ProductoFoto.class);

        if (pf.getFechaSubidaProductoFoto() == null) {
            pf.setFechaSubidaProductoFoto(LocalDate.now());
        }

        productoFotoService.insert(pf);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // LISTAR TODOS
    @PermitAll
    @GetMapping
    public List<ProductoFotoDTO> listar() {
        return productoFotoService.list().stream().map(pf -> {
            ModelMapper m = new ModelMapper();
            return m.map(pf, ProductoFotoDTO.class);
        }).collect(Collectors.toList());
    }

    // LISTAR POR ID
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        ProductoFoto pf = productoFotoService.listId(id);
        if (pf == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ProductoFotoDTO dto = m.map(pf, ProductoFotoDTO.class);
        return ResponseEntity.ok(dto);
    }

    // MODIFICAR
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ProductoFotoDTO dto) {
        ModelMapper m = new ModelMapper();
        ProductoFoto pf = m.map(dto, ProductoFoto.class);

        ProductoFoto existente = productoFotoService.listId(pf.getIdProductoFoto());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + pf.getIdProductoFoto());
        }

        // si no mandan fecha, mantenemos la actual
        if (pf.getFechaSubidaProductoFoto() == null) {
            pf.setFechaSubidaProductoFoto(existente.getFechaSubidaProductoFoto());
        }

        productoFotoService.update(pf);
        return ResponseEntity.ok("Registro con ID " + pf.getIdProductoFoto() + " modificado correctamente.");
    }

    // ELIMINAR
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ProductoFoto pf = productoFotoService.listId(id);
        if (pf == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        productoFotoService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
}
