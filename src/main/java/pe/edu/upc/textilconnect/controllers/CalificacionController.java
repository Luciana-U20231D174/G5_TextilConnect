package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.CalificacionDTO;
import pe.edu.upc.textilconnect.dtos.OperacionDTO;
import pe.edu.upc.textilconnect.entities.Calificacion;
import pe.edu.upc.textilconnect.entities.Operacion;
import pe.edu.upc.textilconnect.servicesinterfaces.ICalificacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {
    @Autowired
    private ICalificacionService calificacionService;

    @PostMapping
    public void insertar(@RequestBody CalificacionDTO cdto) {
        ModelMapper m = new ModelMapper();
        Calificacion c = (Calificacion)m.map(cdto, Calificacion.class);
        this.calificacionService.insert(c);
    }

    @GetMapping
    public List<CalificacionDTO> listar() {
        return this.calificacionService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (CalificacionDTO)m.map(y, CalificacionDTO.class);
        }).collect(Collectors.toList());
    }
}
