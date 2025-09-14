package pe.edu.upc.textilconnect.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.FavoritoDTO;
import pe.edu.upc.textilconnect.entities.Favorito;
import pe.edu.upc.textilconnect.servicesinterfaces.IFavoritoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {
    @Autowired
    private IFavoritoService favoritoService;

    @PostMapping
    public void insertar(@RequestBody FavoritoDTO fdto) {
        ModelMapper m = new ModelMapper();
        Favorito f = (Favorito)m.map(fdto, Favorito.class);
        this.favoritoService.insert(f);
    }

    @GetMapping
    public List<FavoritoDTO> listar() {
        return this.favoritoService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (FavoritoDTO)m.map(y, FavoritoDTO.class);
        }).collect(Collectors.toList());
    }
}
