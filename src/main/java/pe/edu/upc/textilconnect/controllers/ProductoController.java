package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ProductoDTO;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.servicesinterfaces.IProductoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @PostMapping
    public void insertar(@RequestBody ProductoDTO pdto) {
        ModelMapper m = new ModelMapper();
        Producto p = (Producto) m.map(pdto, Producto.class);
        this.productoService.insert(p);
    }

    @GetMapping
    public List<ProductoDTO> listar() {
        return this.productoService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (ProductoDTO)m.map(y, ProductoDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Producto producto = productoService.listId(id);
        if (producto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ProductoDTO pddto = m.map(producto, ProductoDTO.class);
        return ResponseEntity.ok(pddto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Producto producto = productoService.listId(id);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        productoService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ProductoDTO pddto) {
        ModelMapper m = new ModelMapper();
        Producto producto = m.map(pddto, Producto.class);

        Producto existente = productoService.listId(producto.getIdProducto());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + producto.getIdProducto());
        }
        productoService.update(producto);
        return ResponseEntity.ok("Registro con ID " + producto.getIdProducto() + " modificado correctamente.");
    }

    @GetMapping({"/bnombres"})
    public ResponseEntity<?> buscarNombre(@RequestParam String n) {
        List<Producto> productos = this.productoService.buscarxNombre(n);
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos con este nombre: " + n);
        } else {
            List<ProductoDTO> listaDTO = productos.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (ProductoDTO) m.map(x, ProductoDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }

    @GetMapping({"/bcategorias"})
    public ResponseEntity<?> buscarCategoria(@RequestParam String ca) {
        List<Producto> productos = this.productoService.buscarxCategoria(ca);
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos con esta categoria: " + ca);
        } else {
            List<ProductoDTO> listaDTO = productos.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (ProductoDTO) m.map(x, ProductoDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }

    @GetMapping({"/bcolores"})
    public ResponseEntity<?> buscarColor(@RequestParam String co) {
        List<Producto> productos = this.productoService.buscarxColor(co);
        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos con este color: " + co);
        } else {
            List<ProductoDTO> listaDTO = productos.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (ProductoDTO) m.map(x, ProductoDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }

    @GetMapping("/bprecio")
    public ResponseEntity<?> buscarPorRangoPrecio(@RequestParam BigDecimal min,
                                                  @RequestParam BigDecimal max) {
        List<Producto> productos = this.productoService.buscarxPrecio(min, max);

        if (productos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron productos en el rango: " + min + " - " + max);
        } else {
            List<ProductoDTO> listaDTO = productos.stream()
                    .map(p -> {
                        ModelMapper m = new ModelMapper();
                        return m.map(p, ProductoDTO.class);
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }

}
