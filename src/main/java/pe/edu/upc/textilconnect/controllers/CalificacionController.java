package pe.edu.upc.textilconnect.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.CalificacionDTO;
import pe.edu.upc.textilconnect.entities.Calificacion;
import pe.edu.upc.textilconnect.entities.Pedido;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.ICalificacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private ICalificacionService calificacionService;

    // ---------- INSERTAR ----------
    // Solo VENDEDOR y COMPRADOR pueden insertar
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @PostMapping
    public ResponseEntity<CalificacionDTO> insertar(@RequestBody @Valid CalificacionDTO dto) {
        Calificacion c = new Calificacion();
        c.setEstrellas(dto.getEstrellas());
        c.setComentario(dto.getComentario());
        c.setFechaCalificacion(java.time.LocalDate.now());

        if (dto.getPedido() != null && dto.getPedido().getIdPedido() != 0) {
            Pedido p = new Pedido();
            p.setIdPedido(dto.getPedido().getIdPedido());
            c.setPedido(p);
        }
        if (dto.getCalificador() != null && dto.getCalificador().getIdUsuario() != null) {
            Usuario u = new Usuario();
            u.setIdUsuario(dto.getCalificador().getIdUsuario());
            c.setCalificador(u);
        }
        if (dto.getCalificado() != null && dto.getCalificado().getIdUsuario() != null) {
            Usuario u2 = new Usuario();
            u2.setIdUsuario(dto.getCalificado().getIdUsuario());
            c.setCalificado(u2);
        }

        calificacionService.insert(c);

        CalificacionDTO out = new CalificacionDTO();
        out.setIdCalificacion(c.getIdCalificacion());
        out.setEstrellas(c.getEstrellas());
        out.setComentario(c.getComentario());
        out.setFechaCalificacion(c.getFechaCalificacion());

        Pedido pOut = new Pedido();
        pOut.setIdPedido(c.getPedido().getIdPedido());
        out.setPedido(pOut);

        Usuario califOut = new Usuario();
        califOut.setIdUsuario(c.getCalificador().getIdUsuario());
        out.setCalificador(califOut);

        Usuario calificadoOut = new Usuario();
        calificadoOut.setIdUsuario(c.getCalificado().getIdUsuario());
        out.setCalificado(calificadoOut);

        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

    // ---------- LISTAR TODOS ----------
    // Según tu resumen: ADMIN puede listar
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<CalificacionDTO> listar() {
        return calificacionService.list().stream().map(c -> {
            CalificacionDTO dto = new CalificacionDTO();
            dto.setIdCalificacion(c.getIdCalificacion());
            dto.setEstrellas(c.getEstrellas());
            dto.setComentario(c.getComentario());
            dto.setFechaCalificacion(c.getFechaCalificacion());

            if (c.getPedido() != null) {
                Pedido p = new Pedido();
                p.setIdPedido(c.getPedido().getIdPedido());
                dto.setPedido(p);
            }

            if (c.getCalificador() != null) {
                Usuario u1 = new Usuario();
                u1.setIdUsuario(c.getCalificador().getIdUsuario());
                u1.setNombreUsuario(c.getCalificador().getNombreUsuario());
                dto.setCalificador(u1);
            }

            if (c.getCalificado() != null) {
                Usuario u2 = new Usuario();
                u2.setIdUsuario(c.getCalificado().getIdUsuario());
                u2.setNombreUsuario(c.getCalificado().getNombreUsuario());
                dto.setCalificado(u2);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    // 🔹 LISTAR POR ID (para EDITAR)
    // También solo ADMIN según tu tabla (listar/consultar)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Calificacion c = calificacionService.listId(id);
        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una calificación con ID " + id);
        }

        CalificacionDTO dto = new CalificacionDTO();
        dto.setIdCalificacion(c.getIdCalificacion());
        dto.setEstrellas(c.getEstrellas());
        dto.setComentario(c.getComentario());
        dto.setFechaCalificacion(c.getFechaCalificacion());

        if (c.getPedido() != null) {
            Pedido p = new Pedido();
            p.setIdPedido(c.getPedido().getIdPedido());
            dto.setPedido(p);
        }

        if (c.getCalificador() != null) {
            Usuario u1 = new Usuario();
            u1.setIdUsuario(c.getCalificador().getIdUsuario());
            dto.setCalificador(u1);
        }

        if (c.getCalificado() != null) {
            Usuario u2 = new Usuario();
            u2.setIdUsuario(c.getCalificado().getIdUsuario());
            dto.setCalificado(u2);
        }

        return ResponseEntity.ok(dto);
    }

    // 🔹 MODIFICAR
    // Solo VENDEDOR y COMPRADOR pueden modificar
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody CalificacionDTO dto) {

        Calificacion existente = calificacionService.listId(dto.getIdCalificacion());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una calificación con ID " + dto.getIdCalificacion());
        }

        existente.setEstrellas(dto.getEstrellas());
        existente.setComentario(dto.getComentario());
        existente.setFechaCalificacion(dto.getFechaCalificacion());

        if (dto.getPedido() != null && dto.getPedido().getIdPedido() != 0) {
            Pedido p = new Pedido();
            p.setIdPedido(dto.getPedido().getIdPedido());
            existente.setPedido(p);
        }

        if (dto.getCalificador() != null && dto.getCalificador().getIdUsuario() != null) {
            Usuario u1 = new Usuario();
            u1.setIdUsuario(dto.getCalificador().getIdUsuario());
            existente.setCalificador(u1);
        }

        if (dto.getCalificado() != null && dto.getCalificado().getIdUsuario() != null) {
            Usuario u2 = new Usuario();
            u2.setIdUsuario(dto.getCalificado().getIdUsuario());
            existente.setCalificado(u2);
        }

        calificacionService.update(existente);
        return ResponseEntity.ok("Calificación actualizada");
    }

    // 🔹 ELIMINAR
    // Según tu resumen: solo ADMIN elimina calificaciones
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Calificacion c = calificacionService.listId(id);
        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una calificación con ID " + id);
        }
        calificacionService.delete(id);
        return ResponseEntity.ok("Calificación eliminada");
    }
}
