package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<UsuarioDTOList> listar() {
        return this.usuarioService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (UsuarioDTOList)m.map(y, UsuarioDTOList.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioService.listId(id);
        if (usuario == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        UsuarioDTOInsert udto = m.map(usuario, UsuarioDTOInsert.class);
        return ResponseEntity.ok(udto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioService.listId(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        usuarioService.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody UsuarioDTOList udto) {
        ModelMapper m = new ModelMapper();
        Usuario usuario = m.map(udto, Usuario.class);

        Usuario existente = usuarioService.listId(usuario.getIdUsuario());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + usuario.getIdUsuario());
        }
        usuarioService.update(usuario);
        return ResponseEntity.ok("Registro con ID " + usuario.getIdUsuario() + " modificado correctamente.");
    }

    @GetMapping("/bnombres")
    public ResponseEntity<?> buscarNombre(@RequestParam String n) {
        List<Usuario> usuarios = this.usuarioService.buscarxNombre(n);
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios con este nombre: " + n);
        } else {
            List<UsuarioDTOList> listaDTO = usuarios.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (UsuarioDTOList) m.map(x, UsuarioDTOList.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }

    @GetMapping("/bemails")
    public ResponseEntity<?> buscarEmail(@RequestParam String e) {
        List<Usuario> usuarios = this.usuarioService.buscarxEmail(e);
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios con este email: " + e);
        } else {
            List<UsuarioDTOList> listaDTO = usuarios.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (UsuarioDTOList) m.map(x, UsuarioDTOList.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }
}
