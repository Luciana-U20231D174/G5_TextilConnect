package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.EntregaDTO;
import pe.edu.upc.textilconnect.dtos.ProductoDTO;
import pe.edu.upc.textilconnect.entities.Entrega;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.servicesinterfaces.IEntregaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
    @Autowired
    private IEntregaService entregaService;

    @PostMapping
    public void insertar(@RequestBody EntregaDTO edto) {
        ModelMapper m = new ModelMapper();
        Entrega e = (Entrega) m.map(edto, Entrega.class);
        this.entregaService.insert(e);
    }

    @GetMapping
    public List<EntregaDTO> listar() {
        return this.entregaService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (EntregaDTO)m.map(y, EntregaDTO.class);
        }).collect(Collectors.toList());
    }
}
