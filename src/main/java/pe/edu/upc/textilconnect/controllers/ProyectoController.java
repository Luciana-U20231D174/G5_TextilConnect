package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ProyectoDTO;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOInsert;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOList;
import pe.edu.upc.textilconnect.entities.Proyecto;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IProyectoService;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;

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
}
