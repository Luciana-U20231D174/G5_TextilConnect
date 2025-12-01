// src/main/java/pe/edu/upc/textilconnect/controllers/NotificacionController.java
package pe.edu.upc.textilconnect.controllers;

import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.NotificacionDTO;
import pe.edu.upc.textilconnect.entities.Notificacion;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.INotificacionService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notificaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificacionController {

    @Autowired
    private INotificacionService nS;

    // ===========================
    //       LISTAR TODOS
    // ===========================
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    public List<NotificacionDTO> listar() {
        return nS.list().stream().map(n -> {
            NotificacionDTO dto = new NotificacionDTO();
            dto.setIdNotificacion(n.getIdNotificacion());
            dto.setTipoNotificacion(n.getTipoNotificacion());
            dto.setMensajeNotificacion(n.getMensajeNotificacion());
            dto.setFechaNotificacion(n.getFechaNotificacion());

            if (n.getUsuario() != null) {
                Usuario u = new Usuario();
                u.setIdUsuario(n.getUsuario().getIdUsuario());
                u.setNombreUsuario(n.getUsuario().getNombreUsuario());
                dto.setUsuario(u);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    // ===========================
    //       INSERTAR
    // ===========================
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> insertar(@RequestBody NotificacionDTO dto) {
        try {
            Notificacion n = new Notificacion();
            n.setTipoNotificacion(dto.getTipoNotificacion());
            n.setMensajeNotificacion(dto.getMensajeNotificacion());

            LocalDate fecha = dto.getFechaNotificacion() != null
                    ? dto.getFechaNotificacion()
                    : LocalDate.now();
            n.setFechaNotificacion(fecha);

            if (dto.getUsuario() != null && dto.getUsuario().getIdUsuario() != 0) {
                Usuario u = new Usuario();
                u.setIdUsuario(dto.getUsuario().getIdUsuario());
                n.setUsuario(u);
            }

            nS.insert(n);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Notificación creada correctamente.");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al registrar la notificación.");
        }
    }

    // ===========================
    //       LISTAR POR ID
    // ===========================
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Notificacion n = nS.listId(id);
        if (n == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una notificación con el ID: " + id);
        }

        NotificacionDTO dto = new NotificacionDTO();
        dto.setIdNotificacion(n.getIdNotificacion());
        dto.setTipoNotificacion(n.getTipoNotificacion());
        dto.setMensajeNotificacion(n.getMensajeNotificacion());
        dto.setFechaNotificacion(n.getFechaNotificacion());

        if (n.getUsuario() != null) {
            Usuario u = new Usuario();
            u.setIdUsuario(n.getUsuario().getIdUsuario());
            u.setNombreUsuario(n.getUsuario().getNombreUsuario());
            dto.setUsuario(u);
        }

        return ResponseEntity.ok(dto);
    }

    // ===========================
    //          MODIFICAR
    // ===========================
    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody NotificacionDTO dto) {
        try {
            Notificacion existente = nS.listId(dto.getIdNotificacion());
            if (existente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No existe notificación con ID: " + dto.getIdNotificacion());
            }

            existente.setTipoNotificacion(dto.getTipoNotificacion());
            existente.setMensajeNotificacion(dto.getMensajeNotificacion());

            LocalDate fecha = dto.getFechaNotificacion() != null
                    ? dto.getFechaNotificacion()
                    : existente.getFechaNotificacion();
            existente.setFechaNotificacion(fecha);

            if (dto.getUsuario() != null && dto.getUsuario().getIdUsuario() != 0) {
                Usuario u = new Usuario();
                u.setIdUsuario(dto.getUsuario().getIdUsuario());
                existente.setUsuario(u);
            } else {
                existente.setUsuario(null);
            }

            nS.update(existente);
            return ResponseEntity.ok("Notificación actualizada correctamente.");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al modificar la notificación.");
        }
    }

    // ===========================
    //          ELIMINAR
    // ===========================
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Notificacion n = nS.listId(id);
        if (n == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe notificación con ID: " + id);
        }
        nS.delete(id);
        return ResponseEntity.ok("Notificación eliminada correctamente.");
    }
}
