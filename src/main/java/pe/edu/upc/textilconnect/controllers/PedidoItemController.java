// src/main/java/pe/edu/upc/textilconnect/controllers/PedidoItemController.java
package pe.edu.upc.textilconnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.PedidoItemDTO;
import pe.edu.upc.textilconnect.entities.Pedido;
import pe.edu.upc.textilconnect.entities.PedidoItem;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.servicesinterfaces.IPedidoItemService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidositems")
public class PedidoItemController {

    @Autowired
    private IPedidoItemService pedidoItemService;

    // 🔹 INSERTAR
    @PreAuthorize("hasAuthority('COMPRADOR')")
    @PostMapping
    public ResponseEntity<String> insertar(@RequestBody PedidoItemDTO dto) {
        PedidoItem pi = new PedidoItem();
        pi.setCantidadPedidoItem(dto.getCantidadPedidoItem());
        pi.setPrecioPedidoItem(dto.getPrecioPedidoItem());
        pi.setImagenUrl(dto.getImagenUrl()); // 👈 NUEVO

        if (dto.getIdPedido() != null) {
            Pedido p = new Pedido();
            p.setIdPedido(dto.getIdPedido());
            pi.setPedido(p);
        }

        if (dto.getIdProducto() != null) {
            Producto prod = new Producto();
            prod.setIdProducto(dto.getIdProducto());
            pi.setProducto(prod);
        }

        pedidoItemService.insert(pi);
        return ResponseEntity.status(HttpStatus.CREATED).body("PedidoItem creado");
    }

    // 🔹 LISTAR TODOS
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<PedidoItemDTO> listar() {
        return pedidoItemService.list().stream().map(pi -> {
            PedidoItemDTO dto = new PedidoItemDTO();
            dto.setIdPedidoItem(pi.getIdPedidoItem());
            dto.setCantidadPedidoItem(pi.getCantidadPedidoItem());
            dto.setPrecioPedidoItem(pi.getPrecioPedidoItem());

            if (pi.getPedido() != null) {
                dto.setIdPedido(pi.getPedido().getIdPedido());
            }

            if (pi.getProducto() != null) {
                dto.setIdProducto(pi.getProducto().getIdProducto());
                dto.setNombreProducto(pi.getProducto().getNombreProducto());
            }

            dto.setImagenUrl(pi.getImagenUrl()); // 👈 NUEVO

            return dto;
        }).collect(Collectors.toList());
    }

    // 🔹 LISTAR POR ID (para EDITAR)
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        PedidoItem pi = pedidoItemService.listId(id);
        if (pi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe PedidoItem con ID " + id);
        }

        PedidoItemDTO dto = new PedidoItemDTO();
        dto.setIdPedidoItem(pi.getIdPedidoItem());
        dto.setCantidadPedidoItem(pi.getCantidadPedidoItem());
        dto.setPrecioPedidoItem(pi.getPrecioPedidoItem());

        if (pi.getPedido() != null) {
            dto.setIdPedido(pi.getPedido().getIdPedido());
        }
        if (pi.getProducto() != null) {
            dto.setIdProducto(pi.getProducto().getIdProducto());
            dto.setNombreProducto(pi.getProducto().getNombreProducto());
        }

        dto.setImagenUrl(pi.getImagenUrl()); // 👈 NUEVO

        return ResponseEntity.ok(dto);
    }

    // 🔹 MODIFICAR
    @PreAuthorize("hasAuthority('COMPRADOR')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody PedidoItemDTO dto) {
        PedidoItem existente = pedidoItemService.listId(dto.getIdPedidoItem());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe PedidoItem con ID " + dto.getIdPedidoItem());
        }

        existente.setCantidadPedidoItem(dto.getCantidadPedidoItem());
        existente.setPrecioPedidoItem(dto.getPrecioPedidoItem());
        existente.setImagenUrl(dto.getImagenUrl()); // 👈 NUEVO

        if (dto.getIdPedido() != null) {
            Pedido p = new Pedido();
            p.setIdPedido(dto.getIdPedido());
            existente.setPedido(p);
        } else {
            existente.setPedido(null);
        }

        if (dto.getIdProducto() != null) {
            Producto prod = new Producto();
            prod.setIdProducto(dto.getIdProducto());
            existente.setProducto(prod);
        } else {
            existente.setProducto(null);
        }

        pedidoItemService.update(existente);
        return ResponseEntity.ok("PedidoItem actualizado");
    }

    // 🔹 ELIMINAR (igual que ya lo tienes)
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        PedidoItem pi = pedidoItemService.listId(id);
        if (pi == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe PedidoItem con ID " + id);
        }
        pedidoItemService.delete(id);
        return ResponseEntity.ok("PedidoItem con ID " + id + " eliminado correctamente.");
    }
}