package pe.edu.upc.textilconnect.controllers;

import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.PresupuestoMensualDTO;
import pe.edu.upc.textilconnect.entities.PresupuestoMensual;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IPresupuestoMensualService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/presupuestosmensuales")
@CrossOrigin(origins = "http://localhost:4200")
public class PresupuestoMensualController {

    @Autowired
    private IPresupuestoMensualService presupuestoMensualService;

    // ---------- INSERTAR ----------
    @PreAuthorize("hasAnyAuthority('ADMIN','ESTUDIANTE')")
    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody PresupuestoMensualDTO dto) {
        try {
            PresupuestoMensual pm = new PresupuestoMensual();
            pm.setAnioPresupuestoMensual(dto.getAnioPresupuestoMensual());
            pm.setMesPresupuestoMensual(dto.getMesPresupuestoMensual());
            pm.setMontoLimitePresupuestoMensual(dto.getMontoLimitePresupuestoMensual());
            pm.setFechaPresupuestoMensual(dto.getFechaPresupuestoMensual());

            if (dto.getUsuario() != null && dto.getUsuario().getIdUsuario() != 0) {
                Usuario u = new Usuario();
                u.setIdUsuario(dto.getUsuario().getIdUsuario());
                pm.setUsuario(u);
            } else {
                pm.setUsuario(null);
            }

            presupuestoMensualService.insert(pm);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Collections.singletonMap("message", "Presupuesto mensual creado correctamente."));
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            String detalle = ex.getMostSpecificCause() != null
                    ? ex.getMostSpecificCause().getMessage()
                    : ex.getMessage();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error de integridad al crear el presupuesto mensual: " + detalle);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al crear el presupuesto mensual.");
        }
    }

    // ---------- LISTAR TODOS ----------
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<PresupuestoMensualDTO> listar() {
        return presupuestoMensualService.list().stream().map(pm -> {
            PresupuestoMensualDTO dto = new PresupuestoMensualDTO();
            dto.setIdPresupuestoMensual(pm.getIdPresupuestoMensual());
            dto.setAnioPresupuestoMensual(pm.getAnioPresupuestoMensual());
            dto.setMesPresupuestoMensual(pm.getMesPresupuestoMensual());
            dto.setMontoLimitePresupuestoMensual(pm.getMontoLimitePresupuestoMensual());
            dto.setFechaPresupuestoMensual(pm.getFechaPresupuestoMensual());

            if (pm.getUsuario() != null) {
                Usuario u = new Usuario();
                u.setIdUsuario(pm.getUsuario().getIdUsuario());
                u.setNombreUsuario(pm.getUsuario().getNombreUsuario());
                dto.setUsuario(u);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    // ---------- LISTAR POR ID ----------
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        PresupuestoMensual pm = presupuestoMensualService.listId(id);
        if (pm == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un presupuesto mensual con ID " + id);
        }

        PresupuestoMensualDTO dto = new PresupuestoMensualDTO();
        dto.setIdPresupuestoMensual(pm.getIdPresupuestoMensual());
        dto.setAnioPresupuestoMensual(pm.getAnioPresupuestoMensual());
        dto.setMesPresupuestoMensual(pm.getMesPresupuestoMensual());
        dto.setMontoLimitePresupuestoMensual(pm.getMontoLimitePresupuestoMensual());
        dto.setFechaPresupuestoMensual(pm.getFechaPresupuestoMensual());

        if (pm.getUsuario() != null) {
            Usuario u = new Usuario();
            u.setIdUsuario(pm.getUsuario().getIdUsuario());
            u.setNombreUsuario(pm.getUsuario().getNombreUsuario());
            dto.setUsuario(u);
        }

        return ResponseEntity.ok(dto);
    }

    // ---------- MODIFICAR ----------
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody PresupuestoMensualDTO dto) {
        try {
            PresupuestoMensual existente = presupuestoMensualService.listId(dto.getIdPresupuestoMensual());
            if (existente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No existe presupuesto mensual con ID " + dto.getIdPresupuestoMensual());
            }

            existente.setAnioPresupuestoMensual(dto.getAnioPresupuestoMensual());
            existente.setMesPresupuestoMensual(dto.getMesPresupuestoMensual());
            existente.setMontoLimitePresupuestoMensual(dto.getMontoLimitePresupuestoMensual());
            existente.setFechaPresupuestoMensual(dto.getFechaPresupuestoMensual());

            if (dto.getUsuario() != null && dto.getUsuario().getIdUsuario() != 0) {
                Usuario u = new Usuario();
                u.setIdUsuario(dto.getUsuario().getIdUsuario());
                existente.setUsuario(u);
            } else {
                existente.setUsuario(null);
            }

            presupuestoMensualService.update(existente);
            return ResponseEntity.ok("Presupuesto mensual actualizado");
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            String detalle = ex.getMostSpecificCause() != null
                    ? ex.getMostSpecificCause().getMessage()
                    : ex.getMessage();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error de integridad al actualizar el presupuesto mensual: " + detalle);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al actualizar el presupuesto mensual.");
        }
    }

    // ---------- ELIMINAR ----------
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        PresupuestoMensual pm = presupuestoMensualService.listId(id);
        if (pm == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un presupuesto mensual con ID " + id);
        }
        presupuestoMensualService.delete(id);
        return ResponseEntity.ok("Presupuesto mensual eliminado");
    }
}