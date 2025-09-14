package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ProductoDTO;
import pe.edu.upc.textilconnect.dtos.ProyectoDTO;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.entities.Proyecto;
import pe.edu.upc.textilconnect.servicesinterfaces.IProductoService;

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
}
