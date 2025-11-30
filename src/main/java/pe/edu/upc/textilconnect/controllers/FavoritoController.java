package pe.edu.upc.textilconnect.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.FavoritoDTO;
import pe.edu.upc.textilconnect.entities.Favorito;
import pe.edu.upc.textilconnect.servicesinterfaces.IFavoritoService;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.entities.Proyecto;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {
    @Autowired
    private IFavoritoService favoritoService;

    @PreAuthorize("hasAuthority('COMPRADOR')")
    @PostMapping
    public void insertar(@RequestBody FavoritoDTO fdto) {
        ModelMapper m = new ModelMapper();
        Favorito f = (Favorito)m.map(fdto, Favorito.class);
        this.favoritoService.insert(f);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','COMPRADOR')")
    @GetMapping
    public List<FavoritoDTO> listar() {
        return favoritoService.list().stream().map(f -> {
            FavoritoDTO dto = new FavoritoDTO();
            dto.setIdFavorito(f.getIdFavorito());
            dto.setFechaFavorito(f.getFechaFavorito());

            // USUARIO (solo id + nombre)
            if (f.getUsuario() != null) {
                Usuario u = new Usuario();
                u.setIdUsuario(f.getUsuario().getIdUsuario());
                u.setNombreUsuario(f.getUsuario().getNombreUsuario());
                dto.setUsuario(u);
            }

            // PRODUCTO (solo id + nombre)
            if (f.getProducto() != null) {
                Producto p = new Producto();
                p.setIdProducto(f.getProducto().getIdProducto());
                p.setNombreProducto(f.getProducto().getNombreProducto());
                dto.setProducto(p);
            }

            // PROYECTO (solo id + nombre)
            if (f.getProyecto() != null) {
                Proyecto pr = new Proyecto();
                pr.setIdProyecto(f.getProyecto().getIdProyecto());
                pr.setTituloProyecto(f.getProyecto().getTituloProyecto());
                dto.setProyecto(pr);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','COMPRADOR')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','COMPRADOR')")
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
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','COMPRADOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Favorito f = favoritoService.listId(id);
        if (f == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        FavoritoDTO dto = new FavoritoDTO();
        dto.setIdFavorito(f.getIdFavorito());
        dto.setFechaFavorito(f.getFechaFavorito());

        if (f.getUsuario() != null) {
            Usuario u = new Usuario();
            u.setIdUsuario(f.getUsuario().getIdUsuario());
            u.setNombreUsuario(f.getUsuario().getNombreUsuario());
            dto.setUsuario(u);
        }

        if (f.getProducto() != null) {
            Producto p = new Producto();
            p.setIdProducto(f.getProducto().getIdProducto());
            p.setNombreProducto(f.getProducto().getNombreProducto());
            dto.setProducto(p);
        }

        if (f.getProyecto() != null) {
            Proyecto pr = new Proyecto();
            pr.setIdProyecto(f.getProyecto().getIdProyecto());
            pr.setTituloProyecto(f.getProyecto().getTituloProyecto());
            dto.setProyecto(pr);
        }

        return ResponseEntity.ok(dto);
    }

}
