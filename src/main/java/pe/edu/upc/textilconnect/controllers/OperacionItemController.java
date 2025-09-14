package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.OperacionItemDTO;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOInsert;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOList;
import pe.edu.upc.textilconnect.entities.OperacionItem;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IOperacionItemService;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/operacionesitems")
public class OperacionItemController {

    @Autowired
    private IOperacionItemService operacionItemService;

    @PostMapping
    public void insertar(@RequestBody OperacionItemDTO oidto) {
        ModelMapper m = new ModelMapper();
        OperacionItem oi = (OperacionItem)m.map(oidto, OperacionItem.class);
        this.operacionItemService.insert(oi);
    }

    @GetMapping
    public List<OperacionItemDTO> listar() {
        return this.operacionItemService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (OperacionItemDTO)m.map(y, OperacionItemDTO.class);
        }).collect(Collectors.toList());
    }
}
