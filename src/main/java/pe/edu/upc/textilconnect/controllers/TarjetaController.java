package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.TarjetaDTO;
import pe.edu.upc.textilconnect.entities.Tarjeta;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.servicesinterfaces.ITarjetaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

    @Autowired
    private ITarjetaService tarjetaService;

    @PreAuthorize("hasAnyAuthority('VENDEDOR','COMPRADOR')")
    @PostMapping
    public void insertar(@RequestBody TarjetaDTO mpgdto) {
        ModelMapper m = new ModelMapper();
        Tarjeta mpg = (Tarjeta)m.map(mpgdto, Tarjeta.class);
        this.tarjetaService.insert(mpg);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<TarjetaDTO> listar() {
        return tarjetaService.list().stream().map(t -> {
            TarjetaDTO dto = new TarjetaDTO();
            dto.setIdTarjeta(t.getIdTarjeta());
            dto.setAliasTarjeta(t.getAliasTarjeta());
            dto.setTipoTarjeta(t.getTipoTarjeta());
            dto.setUltimos4Tarjeta(t.getUltimos4Tarjeta());
            dto.setMarcaTarjeta(t.getMarcaTarjeta());
            dto.setTokenReferenciaTarjeta(t.getTokenReferenciaTarjeta());
            dto.setVencimientoTarjeta(t.getVencimientoTarjeta());
            dto.setActivaTarjeta(t.getActivaTarjeta());
            dto.setFechaRegistroTarjeta(t.getFechaRegistroTarjeta());

            // Usuario solo con id + nombre
            if (t.getUsuario() != null) {
                Usuario u = new Usuario();
                u.setIdUsuario(t.getUsuario().getIdUsuario());
                u.setNombreUsuario(t.getUsuario().getNombreUsuario());
                dto.setUsuario(u);
            }

            return dto;
        }).collect(Collectors.toList());
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','COMPRADOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Tarjeta t = tarjetaService.listId(id);

        if (t == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        TarjetaDTO dto = new TarjetaDTO();
        dto.setIdTarjeta(t.getIdTarjeta());
        dto.setAliasTarjeta(t.getAliasTarjeta());
        dto.setTipoTarjeta(t.getTipoTarjeta());
        dto.setUltimos4Tarjeta(t.getUltimos4Tarjeta());
        dto.setMarcaTarjeta(t.getMarcaTarjeta());
        dto.setTokenReferenciaTarjeta(t.getTokenReferenciaTarjeta());
        dto.setVencimientoTarjeta(t.getVencimientoTarjeta());
        dto.setActivaTarjeta(t.getActivaTarjeta());
        dto.setFechaRegistroTarjeta(t.getFechaRegistroTarjeta());

        if (t.getUsuario() != null) {
            Usuario u = new Usuario();
            u.setIdUsuario(t.getUsuario().getIdUsuario());
            u.setNombreUsuario(t.getUsuario().getNombreUsuario());
            dto.setUsuario(u);
        }

        return ResponseEntity.ok(dto);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','COMPRADOR')")
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

    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','COMPRADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable("id") Integer id, @RequestBody TarjetaDTO dto){
        Tarjeta existente = tarjetaService.listId(id);

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "No se encontró un registro con el ID: " + id));
        }

        existente.setAliasTarjeta(dto.getAliasTarjeta());
        existente.setTipoTarjeta(dto.getTipoTarjeta());
        existente.setUltimos4Tarjeta(dto.getUltimos4Tarjeta());
        existente.setMarcaTarjeta(dto.getMarcaTarjeta());
        existente.setTokenReferenciaTarjeta(dto.getTokenReferenciaTarjeta());
        existente.setActivaTarjeta(dto.getActivaTarjeta());
        existente.setVencimientoTarjeta(dto.getVencimientoTarjeta());
        existente.setFechaRegistroTarjeta(dto.getFechaRegistroTarjeta());

        if (dto.getUsuario() != null) {
            Usuario u = new Usuario();
            u.setIdUsuario(dto.getUsuario().getIdUsuario());
            existente.setUsuario(u);
        }

        tarjetaService.update(existente);

        return ResponseEntity.ok(Map.of("message", "Tarjeta actualizada correctamente"));
    }



    @PreAuthorize("hasAnyAuthority('ADMIN','VENDEDOR','COMPRADOR')")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable("idUsuario") int idUsuario) {
        List<Tarjeta> tarjetas = tarjetaService.listarxusuario(idUsuario);

        if (tarjetas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El usuario con ID " + idUsuario + " no tiene métodos de pago guardados.");
        }
        List<TarjetaDTO> listarDto= tarjetas.stream().map(x->{
            ModelMapper m = new ModelMapper();
            return m.map(x, TarjetaDTO.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(listarDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/cantidad/marcas")
    public ResponseEntity<?> contarPorTodasLasMarcas() {
        List<Object[]> lista = tarjetaService.contarPorTodasLasMarcas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/vencimiento")
    public List<TarjetaDTO> listarTarjetasPorRango(LocalDate inicio, LocalDate fin) {

        List<Tarjeta> tarjetas = tarjetaService.listarPorRangoVencimiento(inicio, fin);

        List<TarjetaDTO> lista = new ArrayList<>();

        for (Tarjeta t : tarjetas) {
            TarjetaDTO dto = new TarjetaDTO();
            dto.setIdTarjeta(t.getIdTarjeta());
            dto.setUltimos4Tarjeta(t.getUltimos4Tarjeta());
            dto.setVencimientoTarjeta(t.getVencimientoTarjeta());
            lista.add(dto);
        }

        return lista;
    }

}
