package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<TipoProductoDTO> listar() {
        return this.dS.list().stream().map((y) -> {
            ModelMapper m =new ModelMapper();
            return (TipoProductoDTO)m.map(y, TipoProductoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody TipoProductoDTO dto) {
        ModelMapper m =new ModelMapper();
        TipoProducto prod = (TipoProducto)m.map(dto,TipoProducto.class);
        this.dS.insert(prod);
    }

    @GetMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        TipoProducto prod = dS.listId(id);
        if (prod == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        dS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

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

    @GetMapping({"/bnombres"})
    public ResponseEntity<?> buscar(@RequestParam String n) {
        List<TipoProducto> tipoProductos = this.dS.buscarService(n);
        if (tipoProductos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos con este nombre: " + n);
        } else {
            List<TipoProductoDTO> listaDTO = tipoProductos.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (TipoProductoDTO)m.map(x, TipoProductoDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }
}
