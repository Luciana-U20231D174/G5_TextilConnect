package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.MetodoPagoGuardadoDTOInsert;
import pe.edu.upc.textilconnect.dtos.MetodoPagoGuardadoDTOList;
import pe.edu.upc.textilconnect.entities.MetodoPago;
import pe.edu.upc.textilconnect.entities.MetodoPagoGuardado;
import pe.edu.upc.textilconnect.servicesinterfaces.IMetodoPagoGuardadoService;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        MetodoPagoGuardado met=metodoPagoGuardadoService.listId(id);
        if(met==null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: "+ id);
        }
        ModelMapper m = new ModelMapper();
        MetodoPagoGuardadoDTOInsert mpgd= m.map(met, MetodoPagoGuardadoDTOInsert.class);
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

    @PutMapping
    public ResponseEntity<String> modificar(@RequestBody MetodoPagoGuardadoDTOList mdto){
        ModelMapper m = new ModelMapper();
        MetodoPagoGuardado meto=m.map(mdto, MetodoPagoGuardado.class);

        MetodoPagoGuardado existente=metodoPagoGuardadoService.listId(meto.getIdMetodoPagoGuardado());
        if (existente==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro un registro con el ID: "+meto.getIdMetodoPagoGuardado());
        }
        metodoPagoGuardadoService.update(meto);
        return ResponseEntity.ok("Registro con ID" +meto.getIdMetodoPagoGuardado() + "modificado correctamente.");
    }
}
