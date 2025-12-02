package pe.edu.upc.textilconnect.controllers;

import jakarta.annotation.security.PermitAll;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ProductoDTO;
import pe.edu.upc.textilconnect.dtos.ProductoListDTO;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.servicesinterfaces.IProductoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    // 🔹 INSERTAR (solo ADMIN)
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR')")
    @PostMapping
    public void insertar(@RequestBody ProductoDTO pdto) {
        ModelMapper m = new ModelMapper();
        Producto p = m.map(pdto, Producto.class);
        this.productoService.insert(p);
    }

    // 🔹 LISTAR TODOS (permitido para todos)
    @PermitAll
    @GetMapping
    public List<ProductoListDTO> listar() {
        return this.productoService.list().stream().map(producto -> {
            ProductoListDTO dto = new ProductoListDTO();
            dto.setIdProducto(producto.getIdProducto());
            dto.setNombreProducto(producto.getNombreProducto());
            dto.setPrecioProducto(producto.getPrecioProducto());
            dto.setStockProducto(producto.getStockProducto());
            if (producto.getTipoProducto() != null) {
                dto.setNombreTipoProducto(producto.getTipoProducto().getNombreTipoProducto());
            }
            if (producto.getUsuario() != null) {
                dto.setNombreUsuario(producto.getUsuario().getNombreUsuario());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    // 🔹 LISTAR POR ID
    @PermitAll
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Producto producto = productoService.listId(id);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        java.util.Map<String, Object> resp = new java.util.HashMap<>();
        resp.put("idProducto", producto.getIdProducto());
        resp.put("nombreProducto", producto.getNombreProducto());
        resp.put("descripcionProducto", producto.getDescripcionProducto());
        resp.put("precioProducto", producto.getPrecioProducto());
        resp.put("stockProducto", producto.getStockProducto());
        resp.put("colorProducto", producto.getColorProducto());
        resp.put("medidaProducto", producto.getMedidaProducto());
        resp.put("categoriaProducto", producto.getCategoriaProducto());
        resp.put("disponibleProducto", producto.getDisponibleProducto());
        resp.put("urlTipoProducto", producto.getUrlTipoProducto());
        resp.put("idTipoProducto", producto.getTipoProducto() != null ? producto.getTipoProducto().getIdTipoProducto() : null);
        resp.put("idUsuario", producto.getUsuario() != null ? producto.getUsuario().getIdUsuario() : null);
        return ResponseEntity.ok(resp);
    }

    // 🔹 MODIFICAR (solo ADMIN)
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR')")
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

    // 🔹 ELIMINAR (solo ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
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

    // 🔹 BUSQUEDAS (permitido para todos)
    @PermitAll
    @GetMapping({"/bnombres"})
    public ResponseEntity<?> buscarNombre(@RequestParam String n) {
        List<Producto> productos = this.productoService.buscarxNombre(n);
        if (productos.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontraron productos con este nombre: " + n);
        List<ProductoDTO> listaDTO = productos.stream().map(x -> new ModelMapper().map(x, ProductoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @PermitAll
    @GetMapping({"/bcategorias"})
    public ResponseEntity<?> buscarCategoria(@RequestParam String ca) {
        List<Producto> productos = this.productoService.buscarxCategoria(ca);
        if (productos.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontraron productos con esta categoria: " + ca);
        List<ProductoDTO> listaDTO = productos.stream().map(x -> new ModelMapper().map(x, ProductoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @PermitAll
    @GetMapping({"/bcolores"})
    public ResponseEntity<?> buscarColor(@RequestParam String co) {
        List<Producto> productos = this.productoService.buscarxColor(co);
        if (productos.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontraron productos con este color: " + co);
        List<ProductoDTO> listaDTO = productos.stream().map(x -> new ModelMapper().map(x, ProductoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @PermitAll
    @GetMapping("/bprecio")
    public ResponseEntity<?> buscarPorRangoPrecio(@RequestParam double min, @RequestParam double max) {
        List<Producto> productos = this.productoService.buscarxPrecio(min, max);
        if (productos.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontraron productos en el rango: " + min + " - " + max);
        List<ProductoListDTO> listaDTO = productos.stream().map(p -> new ModelMapper().map(p, ProductoListDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }
}
