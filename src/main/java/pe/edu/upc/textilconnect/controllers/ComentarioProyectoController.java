package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ComentarioProyectoDTO;
import pe.edu.upc.textilconnect.dtos.ComprobanteDTO;
import pe.edu.upc.textilconnect.dtos.MetodoPagoDTO;
import pe.edu.upc.textilconnect.entities.ComentarioProyecto;
import pe.edu.upc.textilconnect.entities.Comprobante;
import pe.edu.upc.textilconnect.entities.MetodoPago;
import pe.edu.upc.textilconnect.servicesinterfaces.IComentarioProyectoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentariosproyectos")
public class ComentarioProyectoController {
    @Autowired
    private IComentarioProyectoService comentarioProyectoService;

    @PostMapping
    public void insertar(@RequestBody ComentarioProyectoDTO cpdto) {
        ModelMapper m = new ModelMapper();
        ComentarioProyecto cp = (ComentarioProyecto) m.map(cpdto, ComentarioProyecto.class);
        this.comentarioProyectoService.insert(cp);
    }

    @GetMapping
    public List<ComentarioProyectoDTO> listar() {
        return this.comentarioProyectoService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (ComentarioProyectoDTO)m.map(y, ComentarioProyectoDTO.class);
        }).collect(Collectors.toList());
    }
}
