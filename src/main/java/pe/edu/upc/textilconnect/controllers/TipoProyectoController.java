package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.TipoProyectoDTO;
import pe.edu.upc.textilconnect.entities.TipoProyecto;
import pe.edu.upc.textilconnect.servicesinterfaces.ITipoProyectoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tiposproyectos")
public class TipoProyectoController {
    @Autowired
    private ITipoProyectoService dS;

    @GetMapping
    public List<TipoProyectoDTO> listar() {
        return this.dS.list().stream().map((y) -> {
            ModelMapper m =new ModelMapper();
            return (TipoProyectoDTO)m.map(y, TipoProyectoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody TipoProyectoDTO dto) {
        ModelMapper m =new ModelMapper();
        TipoProyecto proy = (TipoProyecto)m.map(dto,TipoProyecto.class);
        this.dS.insert(proy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        TipoProyecto proy = dS.listId(id);
        if (proy == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        TipoProyectoDTO dto = m.map(proy, TipoProyectoDTO.class);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        TipoProyecto proy = dS.listId(id);
        if (proy == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        dS.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody TipoProyectoDTO dto) {
        ModelMapper m = new ModelMapper();
        TipoProyecto proy = m.map(dto, TipoProyecto.class);

        TipoProyecto existente = dS.listId(proy.getIdTipoProyecto());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + proy.getIdTipoProyecto());
        }
        dS.update(proy);
        return ResponseEntity.ok("Registro con ID " + proy.getIdTipoProyecto() + " modificado correctamente.");
    }
}
