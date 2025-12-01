package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ProyectoDTO;
import pe.edu.upc.textilconnect.dtos.ProyectoListDTO;
import pe.edu.upc.textilconnect.entities.Proyecto;
import pe.edu.upc.textilconnect.servicesinterfaces.IProyectoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    @Autowired
    private IProyectoService proyectoService;

    // INSERTAR
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @PostMapping
    public void insertar(@RequestBody ProyectoDTO pdto) {
        ModelMapper m = new ModelMapper();
        Proyecto p = m.map(pdto, Proyecto.class);
        this.proyectoService.insert(p);
    }

    // LISTAR TODOS
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping
    public List<ProyectoListDTO> listar() {
        return this.proyectoService.list().stream().map(p -> {
            ProyectoListDTO dto = new ProyectoListDTO();
            dto.setIdProyecto(p.getIdProyecto());
            dto.setTituloProyecto(p.getTituloProyecto());
            dto.setDescripcionProyecto(p.getDescripcionProyecto());
            dto.setUrlProyecto(p.getUrlProyecto());
            dto.setVisibleProyecto(p.getVisibleProyecto());
            dto.setFechaCreacion(p.getFechaCreacion());

            if (p.getTipoProyecto() != null) {
                dto.setTipoProyecto(p.getTipoProyecto().getNombreTipoProyecto());
            }
            if (p.getUsuario() != null) {
                dto.setUsuario(p.getUsuario().getNombreUsuario());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    // LISTAR POR ID
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Proyecto p = proyectoService.listId(id);
        if (p == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("idProyecto", p.getIdProyecto());
        map.put("tituloProyecto", p.getTituloProyecto());
        map.put("descripcionProyecto", p.getDescripcionProyecto());
        map.put("urlProyecto", p.getUrlProyecto());
        map.put("visibleProyecto", p.getVisibleProyecto());
        map.put("fechaCreacion", p.getFechaCreacion());

        if (p.getTipoProyecto() != null) {
            map.put("idTipoProyecto", p.getTipoProyecto().getIdTipoProyecto());
        }
        if (p.getUsuario() != null) {
            map.put("idUsuario", p.getUsuario().getIdUsuario());
        }
        return ResponseEntity.ok(map);
    }

    // ELIMINAR
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
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

    // MODIFICAR
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
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

    // BUSCAR POR TÍTULO
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping("/btitulo")
    public ResponseEntity<?> buscarTitulo(@RequestParam String titulo) {
        List<Proyecto> proyectos = proyectoService.buscarxTitulo(titulo);
        if (proyectos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron proyectos con el título: " + titulo);
        }
        List<ProyectoListDTO> listaDTO = proyectos.stream().map(p -> {
            ProyectoListDTO dto = new ProyectoListDTO();
            dto.setIdProyecto(p.getIdProyecto());
            dto.setTituloProyecto(p.getTituloProyecto());
            dto.setDescripcionProyecto(p.getDescripcionProyecto());
            dto.setUrlProyecto(p.getUrlProyecto());
            dto.setVisibleProyecto(p.getVisibleProyecto());
            dto.setFechaCreacion(p.getFechaCreacion());
            if (p.getTipoProyecto() != null) {
                dto.setTipoProyecto(p.getTipoProyecto().getNombreTipoProyecto());
            }
            if (p.getUsuario() != null) {
                dto.setUsuario(p.getUsuario().getNombreUsuario());
            }
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    // BUSCAR POR USUARIO
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> buscarUsuario(@PathVariable("idUsuario") int idUsuario) {
        List<Proyecto> proyectos = proyectoService.buscarxUsuario(idUsuario);
        if (proyectos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario con ID " + idUsuario + " no tiene proyectos guardados.");
        }
        List<ProyectoListDTO> listarDto = proyectos.stream().map(p -> {
            ProyectoListDTO dto = new ProyectoListDTO();
            dto.setIdProyecto(p.getIdProyecto());
            dto.setTituloProyecto(p.getTituloProyecto());
            dto.setDescripcionProyecto(p.getDescripcionProyecto());
            dto.setUrlProyecto(p.getUrlProyecto());
            dto.setVisibleProyecto(p.getVisibleProyecto());
            dto.setFechaCreacion(p.getFechaCreacion());
            if (p.getTipoProyecto() != null) {
                dto.setTipoProyecto(p.getTipoProyecto().getNombreTipoProyecto());
            }
            if (p.getUsuario() != null) {
                dto.setUsuario(p.getUsuario().getNombreUsuario());
            }
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listarDto);
    }

    // RANKING DE USUARIOS
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','ESTUDIANTE')")
    @GetMapping("/rankingusuarios")
    public List<Map<String, Object>> listarUsuariosConMasProyectos() {
        List<Object[]> datos = proyectoService.listarUsuariosConMasProyectos();
        List<Map<String, Object>> respuesta = new ArrayList<>();
        for (Object[] fila : datos) {
            Map<String, Object> item = new HashMap<>();
            item.put("username", fila[0]);
            item.put("cantidadProyectos", fila[1]);
            respuesta.add(item);
        }
        return respuesta;
    }
}