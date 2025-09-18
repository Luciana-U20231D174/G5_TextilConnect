package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.MetodoPagoGuardadoDTOInsert;
import pe.edu.upc.textilconnect.dtos.MetodoPagoGuardadoDTOList;
import pe.edu.upc.textilconnect.entities.MetodoPago;
import pe.edu.upc.textilconnect.entities.MetodoPagoGuardado;
import pe.edu.upc.textilconnect.servicesinterfaces.IMetodoPagoGuardadoService;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        MetodoPagoGuardado met=metodoPagoGuardadoService.listId(id);
        if(met==null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: "+ id);
        }
        ModelMapper m = new ModelMapper();
        MetodoPagoGuardadoDTOList mpgd= m.map(met, MetodoPagoGuardadoDTOList.class);
        return ResponseEntity.ok(mpgd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id)
    {
        MetodoPagoGuardado meto=metodoPagoGuardadoService.listId(id);
        if (meto==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: "+ id);
        }
        metodoPagoGuardadoService.delete(id);
        return ResponseEntity.ok("Registro con ID " +id+ "eliminado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") Integer id, @RequestBody Map<String, String> request){
        MetodoPagoGuardado existente=metodoPagoGuardadoService.listId(id);
        if (existente==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro un registro con el ID: "+id);
        }
        String alias = request.get("aliasMetodoPagoGuardado");
        existente.setAliasMetodoPagoGuardado(alias);
        metodoPagoGuardadoService.update(existente);
        return ResponseEntity.ok("Registro con ID" +id+ "modificado correctamente.");
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable("idUsuario") int idUsuario) {
        List<MetodoPagoGuardado> metodoPagoGuardados = metodoPagoGuardadoService.listarxusuario(idUsuario);

        if (metodoPagoGuardados.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario con ID " + idUsuario + " no tiene métodos de pago guardados.");
        }
        List<MetodoPagoGuardadoDTOList> listarDto= metodoPagoGuardados.stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, MetodoPagoGuardadoDTOList.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listarDto);
    }
}
