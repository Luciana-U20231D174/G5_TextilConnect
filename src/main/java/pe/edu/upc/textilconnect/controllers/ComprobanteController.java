package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ComprobanteDTO;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOInsert;
import pe.edu.upc.textilconnect.dtos.UsuarioDTOList;
import pe.edu.upc.textilconnect.entities.Comprobante;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.IComprobanteService;
import pe.edu.upc.textilconnect.servicesinterfaces.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comprobantes")
public class ComprobanteController {

    @Autowired
    private IComprobanteService comprobanteService;

    @PostMapping
    public void insertar(@RequestBody ComprobanteDTO cdto) {
        ModelMapper m = new ModelMapper();
        Comprobante c = (Comprobante)m.map(cdto, Comprobante.class);
        this.comprobanteService.insert(c);
    }

    @GetMapping
    public List<ComprobanteDTO> listar() {
        return this.comprobanteService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (ComprobanteDTO)m.map(y, ComprobanteDTO.class);
        }).collect(Collectors.toList());
    }
}
