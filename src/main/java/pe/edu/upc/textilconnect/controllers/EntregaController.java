package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.EntregaDTO;
import pe.edu.upc.textilconnect.entities.Entrega;
import pe.edu.upc.textilconnect.servicesinterfaces.IEntregaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/entregas")
public class EntregaController {
    @Autowired
    private IEntregaService entregaService;

    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR')")
    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody EntregaDTO edto) {
        edto.setIdEntrega(0);

        if (edto.getFechaEntrega() == null) {
            edto.setFechaEntrega(java.time.LocalDateTime.now());
        }

        if (edto.getPedido() == null || edto.getPedido().getIdPedido() == 0) {
            return ResponseEntity.badRequest().body("Falta pedido.idPedido");
        }

        ModelMapper m = new ModelMapper();
        Entrega e = m.map(edto, Entrega.class);

        entregaService.insert(e);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<EntregaDTO> listar() {
        return this.entregaService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (EntregaDTO) m.map(y, EntregaDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Entrega entrega = entregaService.listId(id);
        if (entrega == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        EntregaDTO edto = m.map(entrega, EntregaDTO.class);
        return ResponseEntity.ok(edto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody EntregaDTO edto) {
        ModelMapper m = new ModelMapper();
        Entrega entrega = m.map(edto, Entrega.class);

        Entrega existente = entregaService.listId(entrega.getIdEntrega());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + entrega.getIdEntrega());
        }
        entregaService.update(entrega);
        return ResponseEntity.ok("Registro con ID " + entrega.getIdEntrega() + " modificado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping({"/bestados"})
    public ResponseEntity<?> buscarEstado(@RequestParam String estado) {
        List<Entrega> entregas = this.entregaService.buscarxEstado(estado);
        if (entregas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron entregas con este estado: " + estado);
        } else {
            List<EntregaDTO> listaDTO = entregas.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (EntregaDTO) m.map(x, EntregaDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping({"/btipos"})
    public ResponseEntity<?> buscarTipo(@RequestParam String tipo) {
        List<Entrega> entregas = this.entregaService.buscarxTipo(tipo);
        if (entregas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron entregas con este estado: " + tipo);
        } else {
            List<EntregaDTO> listaDTO = entregas.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (EntregaDTO) m.map(x, EntregaDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }

    // 🔥 NUEVO: ELIMINAR ENTREGA
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Entrega entrega = entregaService.listId(id);
        if (entrega == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        entregaService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @GetMapping("/canceladas")
    public Map<String, Object> resumenPorRango(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin
    ) {
        LocalDateTime inicioDT = inicio.atStartOfDay();
        LocalDateTime finDT = fin.atTime(23, 59, 59);

        Long totales = entregaService.contarPorRango(inicioDT, finDT);
        Long canceladas = entregaService.contarCanceladasPorRango(inicioDT, finDT);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("totales", totales);
        respuesta.put("canceladas", canceladas);
        respuesta.put("inicio", inicio.toString());
        respuesta.put("fin", fin.toString());

        return respuesta;
    }


}

