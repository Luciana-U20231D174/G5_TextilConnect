package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ProductoFotoDTO;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOInsert;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOList;
import pe.edu.upc.textilconnect.entities.ProductoFoto;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IProductoFotoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productosfotos")
public class ProductoFotoController {

    @Autowired
    private IProductoFotoService productoFotoService;

    @PostMapping
    public void insertar(@RequestBody ProductoFotoDTO pfdto) {
        ModelMapper m = new ModelMapper();
        ProductoFoto pf = (ProductoFoto)m.map(pfdto, ProductoFoto.class);
        this.productoFotoService.insert(pf);
    }

    @GetMapping
    public List<ProductoFotoDTO> listar() {
        return this.productoFotoService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (ProductoFotoDTO)m.map(y, ProductoFotoDTO.class);
        }).collect(Collectors.toList());
    }
}
