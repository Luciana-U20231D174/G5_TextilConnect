package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ComentarioProyectoDTO;
import pe.edu.upc.textilconnect.dtos.ComentarioProyectoRequestDTO;
import pe.edu.upc.textilconnect.dtos.ComentarioProyectoResponseDTO;
import pe.edu.upc.textilconnect.entities.ComentarioProyecto;
import pe.edu.upc.textilconnect.entities.Proyecto;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IComentarioProyectoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comentariosproyectos")
public class ComentarioProyectoController {
    @Autowired
    private IComentarioProyectoService comentarioProyectoService;

    @PostMapping
    public ResponseEntity<ComentarioProyectoResponseDTO> insertar(
            @RequestBody @jakarta.validation.Valid ComentarioProyectoRequestDTO dto) {

        ComentarioProyecto c = new ComentarioProyecto();
        c.setComentarioProyecto(dto.getComentarioProyecto());

        // asociar por ID sin traer entidades completas de BD
        Proyecto p = new Proyecto(); p.setIdProyecto(dto.getIdProyecto());
        Usuario u = new Usuario();  u.setIdUsuario(dto.getIdUsuario());
        c.setProyecto(p);
        c.setUsuario(u);

        comentarioProyectoService.insert(c);

        // armar respuesta plana
        ComentarioProyectoResponseDTO out = new ComentarioProyectoResponseDTO();
        out.setIdComentarioProyecto(c.getIdComentarioProyecto());
        out.setComentarioProyecto(c.getComentarioProyecto());
        out.setFechaComentario(c.getFechaComentario());
        out.setIdProyecto(dto.getIdProyecto());
        out.setIdUsuario(dto.getIdUsuario());

        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable Integer id,
                                            @RequestBody @jakarta.validation.Valid ComentarioProyectoRequestDTO dto) {

        ComentarioProyecto existente = comentarioProyectoService.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        existente.setComentarioProyecto(dto.getComentarioProyecto());

        Proyecto p = new Proyecto(); p.setIdProyecto(dto.getIdProyecto());
        Usuario  u = new Usuario();  u.setIdUsuario(dto.getIdUsuario());
        existente.setProyecto(p);
        existente.setUsuario(u);

        comentarioProyectoService.update(existente);

        return ResponseEntity.ok("Registro con ID " + id + " modificado correctamente.");
    }



    @GetMapping
    public List<ComentarioProyectoResponseDTO> listar() {
        return this.comentarioProyectoService.list().stream().map((c) -> {
            ComentarioProyectoResponseDTO dto = new ComentarioProyectoResponseDTO();
            dto.setIdComentarioProyecto(c.getIdComentarioProyecto());
            dto.setComentarioProyecto(c.getComentarioProyecto());
            dto.setFechaComentario(c.getFechaComentario());
            dto.setIdProyecto(c.getProyecto().getIdProyecto());
            dto.setIdUsuario(c.getUsuario().getIdUsuario());
            return dto;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        ComentarioProyecto comentarioProyecto = comentarioProyectoService.listId(id);
        if (comentarioProyecto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        comentarioProyectoService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ComentarioProyectoDTO cpdto) {
        ModelMapper m = new ModelMapper();
        ComentarioProyecto comentarioProyecto = m.map(cpdto, ComentarioProyecto.class);

        ComentarioProyecto existente = comentarioProyectoService.listId(comentarioProyecto.getIdComentarioProyecto());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + comentarioProyecto.getIdComentarioProyecto());
        }
        comentarioProyectoService.update(comentarioProyecto);
        return ResponseEntity.ok("Registro con ID " + comentarioProyecto.getComentarioProyecto() + " modificado correctamente.");
    }

    @GetMapping("/proyecto/{idProyecto}")
    public ResponseEntity<?> listarPorProyecto(@PathVariable("idProyecto") int idProyecto) {
        List<ComentarioProyecto> lista = comentarioProyectoService.listarPorProyecto(idProyecto);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron comentarios para el proyecto con ID: " + idProyecto);
        }
        List<ComentarioProyectoResponseDTO> listaDTO = lista.stream().map((c) -> {
            ComentarioProyectoResponseDTO dto = new ComentarioProyectoResponseDTO();
            dto.setIdComentarioProyecto(c.getIdComentarioProyecto());
            dto.setComentarioProyecto(c.getComentarioProyecto());
            dto.setFechaComentario(c.getFechaComentario());
            dto.setIdProyecto(c.getProyecto().getIdProyecto());
            dto.setIdUsuario(c.getUsuario().getIdUsuario());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/proyecto/{idProyecto}/count")
    public ResponseEntity<?> contarPorProyecto(@PathVariable("idProyecto") int idProyecto) {
        int total = comentarioProyectoService.contarPorProyecto(idProyecto);
        return ResponseEntity.ok("El proyecto con ID " + idProyecto + " tiene " + total + " comentario(s).");
    }
}
