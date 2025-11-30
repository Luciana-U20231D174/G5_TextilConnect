package pe.edu.upc.textilconnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ComprobanteCountDTO;
import pe.edu.upc.textilconnect.dtos.ComprobanteDTO;
import pe.edu.upc.textilconnect.entities.Comprobante;
import pe.edu.upc.textilconnect.entities.Pedido;
import pe.edu.upc.textilconnect.entities.TipoDocumento;
import pe.edu.upc.textilconnect.servicesinterfaces.IComprobanteService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comprobantes")
public class ComprobanteController {

    @Autowired
    private IComprobanteService comprobanteService;

    // ---------- INSERTAR ----------
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR')")
    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody ComprobanteDTO dto) {
        Comprobante c = new Comprobante();
        c.setNumeroComprobante(dto.getNumeroComprobante());
        c.setFechaComprobante(dto.getFechaComprobante());
        c.setRazonSocialComprobante(dto.getRazonSocialComprobante());
        c.setIgvComprobante(dto.getIgvComprobante());
        c.setTotalComprobante(dto.getTotalComprobante());

        if (dto.getIdPedido() != null) {
            Pedido p = new Pedido();
            p.setIdPedido(dto.getIdPedido());
            c.setPedido(p);
        }
        if (dto.getIdTipoDocumento() != null) {
            TipoDocumento td = new TipoDocumento();
            td.setIdTipoDocumento(dto.getIdTipoDocumento());
            c.setTipoDocumento(td);
        }

        comprobanteService.insert(c);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comprobante creado");
    }

    // ---------- LISTAR TODOS ----------
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<ComprobanteDTO> listar() {
        return comprobanteService.list().stream().map(c -> {
            ComprobanteDTO dto = new ComprobanteDTO();
            dto.setIdComprobante(c.getIdComprobante());
            dto.setNumeroComprobante(c.getNumeroComprobante());
            dto.setFechaComprobante(c.getFechaComprobante());
            dto.setRazonSocialComprobante(c.getRazonSocialComprobante());
            dto.setIgvComprobante(c.getIgvComprobante());
            dto.setTotalComprobante(c.getTotalComprobante());

            if (c.getPedido() != null) {
                dto.setIdPedido(c.getPedido().getIdPedido());
            }
            if (c.getTipoDocumento() != null) {
                dto.setIdTipoDocumento(c.getTipoDocumento().getIdTipoDocumento());
                dto.setNombreTipoDocumento(c.getTipoDocumento().getNombre());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    // ---------- LISTAR POR ID (para editar) ----------
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Comprobante c = comprobanteService.listId(id);
        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un comprobante con ID " + id);
        }

        ComprobanteDTO dto = new ComprobanteDTO();
        dto.setIdComprobante(c.getIdComprobante());
        dto.setNumeroComprobante(c.getNumeroComprobante());
        dto.setFechaComprobante(c.getFechaComprobante());
        dto.setRazonSocialComprobante(c.getRazonSocialComprobante());
        dto.setIgvComprobante(c.getIgvComprobante());
        dto.setTotalComprobante(c.getTotalComprobante());

        if (c.getPedido() != null) {
            dto.setIdPedido(c.getPedido().getIdPedido());
        }
        if (c.getTipoDocumento() != null) {
            dto.setIdTipoDocumento(c.getTipoDocumento().getIdTipoDocumento());
            dto.setNombreTipoDocumento(c.getTipoDocumento().getNombre());
        }

        return ResponseEntity.ok(dto);
    }

    // ---------- UPDATE ----------
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ComprobanteDTO dto) {
        Comprobante existente = comprobanteService.listId(dto.getIdComprobante());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe comprobante con ID " + dto.getIdComprobante());
        }

        existente.setNumeroComprobante(dto.getNumeroComprobante());
        existente.setFechaComprobante(dto.getFechaComprobante());
        existente.setRazonSocialComprobante(dto.getRazonSocialComprobante());
        existente.setIgvComprobante(dto.getIgvComprobante());
        existente.setTotalComprobante(dto.getTotalComprobante());

        if (dto.getIdPedido() != null) {
            Pedido p = new Pedido();
            p.setIdPedido(dto.getIdPedido());
            existente.setPedido(p);
        } else {
            existente.setPedido(null);
        }

        if (dto.getIdTipoDocumento() != null) {
            TipoDocumento td = new TipoDocumento();
            td.setIdTipoDocumento(dto.getIdTipoDocumento());
            existente.setTipoDocumento(td);
        } else {
            existente.setTipoDocumento(null);
        }

        comprobanteService.update(existente);
        return ResponseEntity.ok("Comprobante actualizado");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Comprobante c = comprobanteService.listId(id);
        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un comprobante con ID " + id);
        }

        comprobanteService.delete(id);
        return ResponseEntity.ok("Comprobante eliminado");
    }

    @GetMapping("/igvtotal")
    public Map<String, Object> obtenerIgvPorFecha(@RequestParam String fecha) {

        LocalDate fechaParsed = LocalDate.parse(fecha);

        Double total = comprobanteService.sumarIgvPorFecha(fechaParsed);

        Map<String, Object> response = new HashMap<>();
        response.put("fecha", fechaParsed);
        response.put("totalIgv", total);

        return response;
    }

    @GetMapping("/rango-fechas")
    public ResponseEntity<List<Map<String, Object>>> listarPorRangoFecha(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {

        List<Comprobante> comprobantes = comprobanteService.buscarxRangoFechas(inicio, fin);

        List<Map<String, Object>> lista = new ArrayList<>();

        for (Comprobante c : comprobantes) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", c.getIdComprobante());
            item.put("fecha", c.getFechaComprobante());
            item.put("username", c.getPedido().getVendedor().getUsername()); // Solo username
            lista.add(item);
        }

        return ResponseEntity.ok(lista);
    }

}

