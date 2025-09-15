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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ComentarioProyecto com = comentarioProyectoService.listId(id);
        if (com == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        comentarioProyectoService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ComentarioProyectoDTO cpdto) {
        ModelMapper m = new ModelMapper();
        ComentarioProyecto com = m.map(cpdto, ComentarioProyecto.class);

        ComentarioProyecto existente = comentarioProyectoService.listId(com.getIdComentarioProyecto());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + com.getIdComentarioProyecto());
        }
        comentarioProyectoService.update(com);
        return ResponseEntity.ok("Registro con ID " + com.getIdComentarioProyecto() + " modificado correctamente.");
    }
}
