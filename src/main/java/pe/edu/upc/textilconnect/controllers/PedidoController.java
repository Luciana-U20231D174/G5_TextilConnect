package pe.edu.upc.textilconnect.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.PedidoDTO;
import pe.edu.upc.textilconnect.entities.Pedido;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.entities.MetodoPago;
import pe.edu.upc.textilconnect.servicesinterfaces.IPedidoService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private IPedidoService pS;

    // 🔹 INSERTAR
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','COMPRADOR')")
    @PostMapping
    public ResponseEntity<Void> insertar(@RequestBody @Valid PedidoDTO dto) {
        Pedido p = new Pedido();
        p.setEstadoPedido(dto.getEstadoPedido());
        p.setFechaCreacionPedido(dto.getFechaCreacionPedido());
        p.setFechaPagoPedido(dto.getFechaPagoPedido());
        p.setTotalPedido(dto.getTotalPedido());

        if (dto.getVendedor() != null) {
            Usuario v = new Usuario();
            v.setIdUsuario(dto.getVendedor().getIdUsuario());
            p.setVendedor(v);
        }

        if (dto.getComprador() != null) {
            Usuario c = new Usuario();
            c.setIdUsuario(dto.getComprador().getIdUsuario());
            p.setComprador(c);
        }

        if (dto.getMetodoPago() != null) {
            MetodoPago m = new MetodoPago();
            m.setIdMetodoPago(dto.getMetodoPago().getIdMetodoPago());
            p.setMetodoPago(m);
        }

        pS.insert(p);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 🔹 LISTAR TODOS (lo que te está dando 500)
    @PermitAll
    @GetMapping
    public List<PedidoDTO> listar() {
        return pS.list().stream().map(p -> {
            PedidoDTO dto = new PedidoDTO();
            dto.setIdPedido(p.getIdPedido());
            dto.setEstadoPedido(p.getEstadoPedido());
            dto.setFechaCreacionPedido(p.getFechaCreacionPedido());
            dto.setFechaPagoPedido(p.getFechaPagoPedido());
            dto.setTotalPedido(p.getTotalPedido());

            // Vendedor
            if (p.getVendedor() != null) {
                Usuario v = new Usuario();
                v.setIdUsuario(p.getVendedor().getIdUsuario());
                v.setNombreUsuario(p.getVendedor().getNombreUsuario());
                dto.setVendedor(v);
            }

            // Comprador
            if (p.getComprador() != null) {
                Usuario c = new Usuario();
                c.setIdUsuario(p.getComprador().getIdUsuario());
                c.setNombreUsuario(p.getComprador().getNombreUsuario());
                dto.setComprador(c);
            }

            // Método de pago
            if (p.getMetodoPago() != null) {
                MetodoPago m = new MetodoPago();
                m.setIdMetodoPago(p.getMetodoPago().getIdMetodoPago());
                m.setNombreMetodoPago(p.getMetodoPago().getNombreMetodoPago());
                dto.setMetodoPago(m);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    // 🔹 LISTAR POR ID (para el editar en Angular)
    @PermitAll
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Pedido p = pS.listId(id);
        if (p == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        PedidoDTO dto = new PedidoDTO();
        dto.setIdPedido(p.getIdPedido());
        dto.setEstadoPedido(p.getEstadoPedido());
        dto.setFechaCreacionPedido(p.getFechaCreacionPedido());
        dto.setFechaPagoPedido(p.getFechaPagoPedido());
        dto.setTotalPedido(p.getTotalPedido());

        if (p.getVendedor() != null) {
            Usuario v = new Usuario();
            v.setIdUsuario(p.getVendedor().getIdUsuario());
            v.setNombreUsuario(p.getVendedor().getNombreUsuario());
            dto.setVendedor(v);
        }

        if (p.getComprador() != null) {
            Usuario c = new Usuario();
            c.setIdUsuario(p.getComprador().getIdUsuario());
            c.setNombreUsuario(p.getComprador().getNombreUsuario());
            dto.setComprador(c);
        }

        if (p.getMetodoPago() != null) {
            MetodoPago m = new MetodoPago();
            m.setIdMetodoPago(p.getMetodoPago().getIdMetodoPago());
            m.setNombreMetodoPago(p.getMetodoPago().getNombreMetodoPago());
            dto.setMetodoPago(m);
        }

        return ResponseEntity.ok(dto);
    }

    // 🔹 MODIFICAR
    @PermitAll
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody @Valid PedidoDTO dto) {
        Pedido existente = pS.listId(dto.getIdPedido());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + dto.getIdPedido());
        }

        existente.setEstadoPedido(dto.getEstadoPedido());
        existente.setFechaCreacionPedido(dto.getFechaCreacionPedido());
        existente.setFechaPagoPedido(dto.getFechaPagoPedido());
        existente.setTotalPedido(dto.getTotalPedido());

        if (dto.getVendedor() != null) {
            Usuario v = new Usuario();
            v.setIdUsuario(dto.getVendedor().getIdUsuario());
            existente.setVendedor(v);
        }

        if (dto.getComprador() != null) {
            Usuario c = new Usuario();
            c.setIdUsuario(dto.getComprador().getIdUsuario());
            existente.setComprador(c);
        }

        if (dto.getMetodoPago() != null) {
            MetodoPago m = new MetodoPago();
            m.setIdMetodoPago(dto.getMetodoPago().getIdMetodoPago());
            existente.setMetodoPago(m);
        }

        pS.update(existente);
        return ResponseEntity.ok("Registro con ID " + dto.getIdPedido() + " modificado correctamente.");
    }

    // 🔹 ELIMINAR
    @PermitAll
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Pedido pedido = pS.listId(id);
        if (pedido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        try {
            pS.delete(id);  // normalmente hace repository.deleteById(id);
            return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
        } catch (DataIntegrityViolationException ex) {
            // Aquí cae cuando hay comprobantes / items / etc. que usan ese pedido
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el pedido porque tiene información relacionada " +
                            "(comprobantes, items u otros registros).");
        }
    }

    @GetMapping("/total")
    public Map<String, Object> sumarPrecioTotalPorFecha(@RequestParam String fecha) {

        LocalDate fechaParsed = LocalDate.parse(fecha);

        Double total = pS.sumarPrecioTotalPorFecha(fechaParsed);

        Map<String, Object> response = new HashMap<>();
        response.put("fecha", fechaParsed);
        response.put("total", total);

        return response;
    }

}
