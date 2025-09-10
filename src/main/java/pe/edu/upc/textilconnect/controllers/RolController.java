package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.RolDTO;
import pe.edu.upc.textilconnect.entities.Rol;
import pe.edu.upc.textilconnect.servicesinterfaces.IRolService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/roles"})
public class RolController {
    @Autowired
    private IRolService dS;

    @GetMapping
    public List<RolDTO> listar() {
        return (List)this.dS.list().stream().map((y) -> {
            ModelMapper m =new ModelMapper();
            return (RolDTO)m.map(y, RolDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody RolDTO dto) {
        ModelMapper m =new ModelMapper();
        Rol r = (Rol)m.map(dto,Rol.class);
        this.dS.insert(r);
    }
}
