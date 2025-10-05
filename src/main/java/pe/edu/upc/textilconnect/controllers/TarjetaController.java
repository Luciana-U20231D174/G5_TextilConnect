package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.TarjetaDTOInsert;
import pe.edu.upc.textilconnect.dtos.TarjetaDTOList;
import pe.edu.upc.textilconnect.entities.Tarjeta;
import pe.edu.upc.textilconnect.servicesinterfaces.ITarjetaService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

    @Autowired
    private ITarjetaService tarjetaService;

    @PostMapping
    public void insertar(@RequestBody TarjetaDTOInsert mpgdto) {
        ModelMapper m = new ModelMapper();
        Tarjeta mpg = (Tarjeta)m.map(mpgdto, Tarjeta.class);
        this.tarjetaService.insert(mpg);
    }

    @GetMapping()
    public List<TarjetaDTOList> listar() {
        return this.tarjetaService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (TarjetaDTOList)m.map(y, TarjetaDTOList.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Tarjeta met=tarjetaService.listId(id);
        if(met==null){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: "+ id);
        }
        ModelMapper m = new ModelMapper();
        TarjetaDTOInsert mpgd= m.map(met, TarjetaDTOInsert.class);
        return ResponseEntity.ok(mpgd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id)
    {
        Tarjeta meto=tarjetaService.listId(id);
        if (meto==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: "+ id);
        }
        tarjetaService.delete(id);
        return ResponseEntity.ok("Registro con ID " +id+ "eliminado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") Integer id, @RequestBody Map<String, String> request){
        Tarjeta existente=tarjetaService.listId(id);
        if (existente==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontro un registro con el ID: "+id);
        }
        String alias = request.get("aliasTarjeta");
        existente.setAliasTarjeta(alias);
        tarjetaService.update(existente);
        return ResponseEntity.ok("Registro con ID" +id+ "modificado correctamente.");
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable("idUsuario") int idUsuario) {
        List<Tarjeta> tarjetas = tarjetaService.listarxusuario(idUsuario);

        if (tarjetas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario con ID " + idUsuario + " no tiene métodos de pago guardados.");
        }
        List<TarjetaDTOList> listarDto= tarjetas.stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, TarjetaDTOList.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listarDto);
    }

    @GetMapping("/cantidad/{marca}")
    public ResponseEntity<?> contarPorMarca(@PathVariable String marca) {
        int cantidad = tarjetaService.contarxmarca(marca);
        return ResponseEntity.ok("La cantidad de tarjetas " + marca + " es " + cantidad);
    }
}
