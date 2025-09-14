package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.OperacionDTO;
import pe.edu.upc.textilconnect.entities.Operacion;
import pe.edu.upc.textilconnect.servicesinterfaces.IOperacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/operaciones")
public class OperacionController {

    @Autowired
    private IOperacionService operacionService;

    @PostMapping
    public void insertar(@RequestBody OperacionDTO odto) {
        ModelMapper m = new ModelMapper();
        Operacion o = (Operacion)m.map(odto, Operacion.class);
        this.operacionService.insert(o);
    }

    @GetMapping
    public List<OperacionDTO> listar() {
        return this.operacionService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (OperacionDTO)m.map(y, OperacionDTO.class);
        }).collect(Collectors.toList());
    }
}
