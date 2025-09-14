package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOInsert;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOList;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping
    public void insertar(@RequestBody UsuarioDTOInsert udto) {
        ModelMapper m = new ModelMapper();
        Usuario u = (Usuario)m.map(udto, Usuario.class);
        this.usuarioService.insert(u);
    }

    @GetMapping({"/roles"})
    public List<UsuarioDTOList> listar() {
        return this.usuarioService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (UsuarioDTOList)m.map(y, UsuarioDTOList.class);
        }).collect(Collectors.toList());
    }
}
