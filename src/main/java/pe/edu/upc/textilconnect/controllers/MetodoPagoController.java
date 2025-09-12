package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private IMetodoPagoService dS;

    @GetMapping
    public List<MetodoPagoDTO> listar() {
        return this.dS.list().stream().map((y) -> {
            ModelMapper m =new ModelMapper();
            return (MetodoPagoDTO)m.map(y, MetodoPagoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody MetodoPagoDTO dto) {
        ModelMapper m =new ModelMapper();
        MetodoPago meto = (MetodoPago)m.map(dto,MetodoPago.class);
        this.dS.insert(meto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        MetodoPago meto = dS.listId(id);
        if (meto == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        MetodoPagoDTO dto = m.map(meto, MetodoPagoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        MetodoPago meto = dS.listId(id);
        if (meto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        dS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody MetodoPagoDTO dto) {
        ModelMapper m = new ModelMapper();
        MetodoPago meto = m.map(dto, MetodoPago.class);

        MetodoPago existente = dS.listId(meto.getIdMetodoPago());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + meto.getIdMetodoPago());
        }
        dS.update(meto);
        return ResponseEntity.ok("Registro con ID " + meto.getIdMetodoPago() + " modificado correctamente.");
    }
}
