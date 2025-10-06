package pe.edu.upc.textilconnect.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.CalificacionRequestDTO;
import pe.edu.upc.textilconnect.dtos.CalificacionResponseDTO;
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

    // POST: crear calificación
    @PostMapping
    public ResponseEntity<CalificacionResponseDTO> insertar(
            @RequestBody @Valid CalificacionRequestDTO dto) {

        Calificacion c = new Calificacion();
        c.setEstrellas(dto.getEstrellas());
        c.setComentario(dto.getComentario());

        c.setFechaCalificacion(java.time.LocalDate.now());

        // Asociar por ID, sin cargar entidades completas
        Pedido p = new Pedido(); p.setIdPedido(dto.getIdPedido());
        Usuario calificador = new Usuario(); calificador.setIdUsuario(dto.getIdCalificador());
        Usuario calificado  = new Usuario();  calificado.setIdUsuario(dto.getIdCalificado());

        c.setPedido(p);
        c.setCalificador(calificador);
        c.setCalificado(calificado);

        calificacionService.insert(c);

        // Respuesta plana (como en ComentarioProyecto)
        CalificacionResponseDTO out = new CalificacionResponseDTO();
        out.setIdCalificacion(c.getIdCalificacion());
        out.setEstrellas(c.getEstrellas());
        out.setComentario(c.getComentario());
        out.setFechaCalificacion(c.getFechaCalificacion());
        out.setIdPedido(dto.getIdPedido());
        out.setIdCalificador(dto.getIdCalificador());
        out.setIdCalificado(dto.getIdCalificado());

        return ResponseEntity.status(HttpStatus.CREATED).body(out);
    }

    // GET: listar todas las calificaciones (respuesta plana)
    @GetMapping
    public List<CalificacionResponseDTO> listar() {
        return calificacionService.list().stream().map(c -> {
            CalificacionResponseDTO dto = new CalificacionResponseDTO();
            dto.setIdCalificacion(c.getIdCalificacion());
            dto.setEstrellas(c.getEstrellas());
            dto.setComentario(c.getComentario());
            dto.setFechaCalificacion(c.getFechaCalificacion());
            dto.setIdPedido(c.getPedido().getIdPedido());
            dto.setIdCalificador(c.getCalificador().getIdUsuario());
            dto.setIdCalificado(c.getCalificado().getIdUsuario());
            return dto;
        }).collect(Collectors.toList());
    }
}
