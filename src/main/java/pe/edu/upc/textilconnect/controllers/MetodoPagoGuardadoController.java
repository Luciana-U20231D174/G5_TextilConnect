package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.MetodoPagoGuardadoDTOInsert;
import pe.edu.upc.textilconnect.dtos.MetodoPagoGuardadoDTOList;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOInsert;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOList;
import pe.edu.upc.textilconnect.entities.MetodoPagoGuardado;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IMetodoPagoGuardadoService;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metodospagosguardados")
public class MetodoPagoGuardadoController {

    @Autowired
    private IMetodoPagoGuardadoService metodoPagoGuardadoService;

    @PostMapping
    public void insertar(@RequestBody MetodoPagoGuardadoDTOInsert mpgdto) {
        ModelMapper m = new ModelMapper();
        MetodoPagoGuardado mpg = (MetodoPagoGuardado)m.map(mpgdto, MetodoPagoGuardado.class);
        this.metodoPagoGuardadoService.insert(mpg);
    }

    @GetMapping({"/usuarios"})
    public List<MetodoPagoGuardadoDTOList> listar() {
        return this.metodoPagoGuardadoService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (MetodoPagoGuardadoDTOList)m.map(y, MetodoPagoGuardadoDTOList.class);
        }).collect(Collectors.toList());
    }
}
