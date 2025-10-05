package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.*;
import pe.edu.upc.textilconnect.entities.Proyecto;
import pe.edu.upc.textilconnect.servicesinterfaces.IProyectoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    @Autowired
    private IProyectoService proyectoService;

    @PostMapping
    public void insertar(@RequestBody ProyectoDTO pdto) {
        ModelMapper m = new ModelMapper();
        Proyecto p = (Proyecto)m.map(pdto, Proyecto.class);
        this.proyectoService.insert(p);
    }

    @GetMapping
    public List<ProyectoDTO> listar() {
        return this.proyectoService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (ProyectoDTO)m.map(y, ProyectoDTO.class);
        }).collect(Collectors.toList());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Proyecto proyecto = proyectoService.listId(id);
        if (proyecto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        proyectoService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody ProyectoDTO pdto) {
        ModelMapper m = new ModelMapper();
        Proyecto proyecto = m.map(pdto, Proyecto.class);

        Proyecto existente = proyectoService.listId(proyecto.getIdProyecto());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + proyecto.getIdProyecto());
        }
        proyectoService.update(proyecto);
        return ResponseEntity.ok("Registro con ID " + proyecto.getIdProyecto() + " modificado correctamente.");

    }
    @GetMapping("/btitulo")
    public ResponseEntity<?> buscarTitulo(@RequestParam String titulo) {
        List<Proyecto> proyectos = proyectoService.buscarxTitulo(titulo);

        if (proyectos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron proyectos con el título: " + titulo);
        } else {
            List<ProyectoDTOList> listaDTO = proyectos.stream()
                    .map(p -> {
                        ModelMapper m = new ModelMapper();
                        return m.map(p, ProyectoDTOList.class);
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> buscarUsuario(@PathVariable("idUsuario") int idUsuario) {
        List<Proyecto> proyectos = proyectoService.buscarxUsuario(idUsuario);

        if (proyectos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario con ID " + idUsuario + " no tiene proyectos guardados.");
        }
        List<ProyectoDTOList> listarDto= proyectos.stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, ProyectoDTOList.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listarDto);
    }
}
