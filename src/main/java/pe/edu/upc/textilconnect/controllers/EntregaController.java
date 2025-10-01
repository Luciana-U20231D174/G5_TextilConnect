package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.EntregaDTO;
import pe.edu.upc.textilconnect.dtos.ProductoDTO;
import pe.edu.upc.textilconnect.entities.Entrega;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.servicesinterfaces.IEntregaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
    @Autowired
    private IEntregaService entregaService;

    @PostMapping
    public void insertar(@RequestBody EntregaDTO edto) {
        ModelMapper m = new ModelMapper();
        Entrega e = (Entrega) m.map(edto, Entrega.class);
        this.entregaService.insert(e);
    }

    @GetMapping
    public List<EntregaDTO> listar() {
        return this.entregaService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (EntregaDTO)m.map(y, EntregaDTO.class);
        }).collect(Collectors.toList());
    }

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

    @GetMapping({"/bestados"})
    public ResponseEntity<?> buscarEstado(@RequestParam String estado) {
        List<Entrega> entregas = this.entregaService.buscarxEstado(estado);
        if (entregas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron entregas con este estado: " + estado);
        } else {
            List<EntregaDTO> listaDTO = entregas.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (EntregaDTO) m.map(x, EntregaDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }

    @GetMapping({"/btipos"})
    public ResponseEntity<?> buscarTipo(@RequestParam String tipo) {
        List<Entrega> entregas = this.entregaService.buscarxTipo(tipo);
        if (entregas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron entregas con este estado: " + tipo);
        } else {
            List<EntregaDTO> listaDTO = entregas.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (EntregaDTO) m.map(x, EntregaDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }
}
