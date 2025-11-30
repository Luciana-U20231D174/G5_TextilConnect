package pe.edu.upc.textilconnect.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ComentarioProyectoDTO;
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

    // ================== INSERTAR ==================
    @PreAuthorize("hasAnyAuthority('VENDEDOR','COMPRADOR')")
    @PostMapping
    public ResponseEntity<ComentarioProyectoDTO> insertar(
            @RequestBody @Valid ComentarioProyectoDTO dto) {

        ComentarioProyecto c = new ComentarioProyecto();
        c.setComentarioProyecto(dto.getComentarioProyecto());

        // Asociación por ID (proyecto y usuario)
        if (dto.getProyecto() != null && dto.getProyecto().getIdProyecto() != 0) {
            Proyecto p = new Proyecto();
            p.setIdProyecto(dto.getProyecto().getIdProyecto());
            c.setProyecto(p);
        }
        if (dto.getUsuario() != null && dto.getUsuario().getIdUsuario() != 0) {
            Usuario u = new Usuario();
            u.setIdUsuario(dto.getUsuario().getIdUsuario());
            c.setUsuario(u);
        }

        comentarioProyectoService.insert(c);

        // DTO de salida (no lo usas mucho en Angular, pero lo dejamos prolijo)
        ComentarioProyectoDTO out = new ComentarioProyectoDTO();
        out.setIdComentarioProyecto(c.getIdComentarioProyecto());
        out.setComentarioProyecto(c.getComentarioProyecto());
        out.setFechaComentario(c.getFechaComentario());

        if (c.getProyecto() != null) {
            Proyecto pOut = new Proyecto();
            pOut.setIdProyecto(c.getProyecto().getIdProyecto());
            pOut.setTituloProyecto(c.getProyecto().getTituloProyecto());
            out.setProyecto(pOut);
        }

        if (c.getUsuario() != null) {
            Usuario uOut = new Usuario();
            uOut.setIdUsuario(c.getUsuario().getIdUsuario());
            uOut.setNombreUsuario(c.getUsuario().getNombreUsuario());
            out.setUsuario(uOut);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

    // ================== MODIFICAR ==================
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','COMPRADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(
            @PathVariable Integer id,
            @RequestBody @Valid ComentarioProyectoDTO dto) {

        ComentarioProyecto existente = comentarioProyectoService.listId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        existente.setComentarioProyecto(dto.getComentarioProyecto());

        if (dto.getProyecto() != null && dto.getProyecto().getIdProyecto() != 0) {
            Proyecto p = new Proyecto();
            p.setIdProyecto(dto.getProyecto().getIdProyecto());
            existente.setProyecto(p);
        }
        if (dto.getUsuario() != null && dto.getUsuario().getIdUsuario() != 0) {
            Usuario u = new Usuario();
            u.setIdUsuario(dto.getUsuario().getIdUsuario());
            existente.setUsuario(u);
        }

        comentarioProyectoService.update(existente);
        return ResponseEntity.ok("Registro con ID " + id + " modificado correctamente.");
    }

    // ================== LISTAR TODOS ==================
    @PermitAll
    @GetMapping
    public List<ComentarioProyectoDTO> listar() {
        return this.comentarioProyectoService.list().stream().map(c -> {
            ComentarioProyectoDTO dto = new ComentarioProyectoDTO();
            dto.setIdComentarioProyecto(c.getIdComentarioProyecto());
            dto.setComentarioProyecto(c.getComentarioProyecto());
            dto.setFechaComentario(c.getFechaComentario());

            // Proyecto con ID + título
            if (c.getProyecto() != null) {
                Proyecto p = new Proyecto();
                p.setIdProyecto(c.getProyecto().getIdProyecto());
                p.setTituloProyecto(c.getProyecto().getTituloProyecto());
                dto.setProyecto(p);
            }

            // Usuario con ID + nombre
            if (c.getUsuario() != null) {
                Usuario u = new Usuario();
                u.setIdUsuario(c.getUsuario().getIdUsuario());
                u.setNombreUsuario(c.getUsuario().getNombreUsuario());
                dto.setUsuario(u);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    // ================== ELIMINAR ==================
    @PreAuthorize("hasAuthority('ADMIN')")
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

    // ================== LISTAR POR PROYECTO ==================
    @PermitAll
    @GetMapping("/proyecto/{idProyecto}")
    public ResponseEntity<?> listarPorProyecto(@PathVariable("idProyecto") int idProyecto) {
        List<ComentarioProyecto> lista = comentarioProyectoService.listarPorProyecto(idProyecto);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron comentarios para el proyecto con ID: " + idProyecto);
        }
        List<ComentarioProyectoDTO> listaDTO = lista.stream().map(c -> {
            ComentarioProyectoDTO dto = new ComentarioProyectoDTO();
            dto.setIdComentarioProyecto(c.getIdComentarioProyecto());
            dto.setComentarioProyecto(c.getComentarioProyecto());
            dto.setFechaComentario(c.getFechaComentario());

            if (c.getProyecto() != null) {
                Proyecto p = new Proyecto();
                p.setIdProyecto(c.getProyecto().getIdProyecto());
                p.setTituloProyecto(c.getProyecto().getTituloProyecto());
                dto.setProyecto(p);
            }

            if (c.getUsuario() != null) {
                Usuario u = new Usuario();
                u.setIdUsuario(c.getUsuario().getIdUsuario());
                u.setNombreUsuario(c.getUsuario().getNombreUsuario());
                dto.setUsuario(u);
            }

            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    // ================== CONTAR POR PROYECTO ==================
    @PermitAll
    @GetMapping("/proyecto/{idProyecto}/count")
    public ResponseEntity<?> contarPorProyecto(@PathVariable("idProyecto") int idProyecto) {
        int total = comentarioProyectoService.contarPorProyecto(idProyecto);
        return ResponseEntity.ok("El proyecto con ID " + idProyecto + " tiene " + total + " comentario(s).");
    }

    @GetMapping("/comentarios/proyectos")
    public ResponseEntity<?> contarComentariosPorProyecto() {
        List<Object[]> lista = comentarioProyectoService.contarComentariosPorProyecto();
        return ResponseEntity.ok(lista);
    }
}
