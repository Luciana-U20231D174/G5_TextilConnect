package pe.edu.upc.textilconnect.controllers;

import jakarta.annotation.security.PermitAll;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.MetodoPagoDTO;
import pe.edu.upc.textilconnect.entities.MetodoPago;
import pe.edu.upc.textilconnect.servicesinterfaces.IMetodoPagoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metodospagos")
public class MetodoPagoController {
    @Autowired
    private IMetodoPagoService metodoPagoService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<MetodoPagoDTO> listar() {
        return this.metodoPagoService.list().stream().map((y) -> {
            ModelMapper m =new ModelMapper();
            return (MetodoPagoDTO)m.map(y, MetodoPagoDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void insertar(@RequestBody MetodoPagoDTO dto) {
        ModelMapper m =new ModelMapper();
        MetodoPago meto = (MetodoPago)m.map(dto,MetodoPago.class);
        this.metodoPagoService.insert(meto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        MetodoPago meto = metodoPagoService.listId(id);
        if (meto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        MetodoPagoDTO dto = m.map(meto, MetodoPagoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        MetodoPago meto = metodoPagoService.listId(id);
        if (meto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        metodoPagoService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody MetodoPagoDTO dto) {
        ModelMapper m = new ModelMapper();
        MetodoPago meto = m.map(dto, MetodoPago.class);

        MetodoPago existente = metodoPagoService.listId(meto.getIdMetodoPago());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + meto.getIdMetodoPago());
        }
        metodoPagoService.update(meto);
        return ResponseEntity.ok("Registro con ID " + meto.getIdMetodoPago() + " modificado correctamente.");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping({"/bnombres"})
    public ResponseEntity<?> buscar(@RequestParam String n) {
        List<MetodoPago> metodoPagos = this.metodoPagoService.buscarService(n);
        if (metodoPagos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron metodos de pago con este nombre: " + n);
        } else {
            List<MetodoPagoDTO> listaDTO = metodoPagos.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (MetodoPagoDTO) m.map(x, MetodoPagoDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }
}
