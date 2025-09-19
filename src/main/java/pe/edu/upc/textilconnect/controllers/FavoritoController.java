package pe.edu.upc.textilconnect.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Favorito favorito = favoritoService.listId(id);
        if (favorito == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        favoritoService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody FavoritoDTO fdto) {
        ModelMapper m = new ModelMapper();
        Favorito favorito = m.map(fdto, Favorito.class);

        Favorito existente = favoritoService.listId(favorito.getIdFavorito());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + favorito.getIdFavorito());
        }
        favoritoService.update(favorito);
        return ResponseEntity.ok("Registro con ID " + favorito.getIdFavorito() + " modificado correctamente.");
    }
}
