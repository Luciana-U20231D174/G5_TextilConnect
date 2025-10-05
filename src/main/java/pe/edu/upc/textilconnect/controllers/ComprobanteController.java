package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.ComprobanteCountDTO;
import pe.edu.upc.textilconnect.dtos.ComprobanteDTO;
import pe.edu.upc.textilconnect.dtos.ComprobanteListDTO;
import pe.edu.upc.textilconnect.entities.Comprobante;
import pe.edu.upc.textilconnect.servicesinterfaces.IComprobanteService;

import java.time.LocalDate;
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
    @GetMapping("/pedido/{idPedido}")
    public List<ComprobanteListDTO> listarPorPedido(@PathVariable int idPedido) {
        return comprobanteService.listarPorOperacionDTO(idPedido);
    }

    @GetMapping("/pedido/{idPedido}/cantidad")
    public ComprobanteCountDTO contarPorPedido(@PathVariable int idPedido) {
        return comprobanteService.contarPorOperacionDTO(idPedido);
    }

    @GetMapping("/brangofechas")
    public ResponseEntity<?> buscarRangoFechas(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        List<Comprobante> comprobantes = comprobanteService.buscarxRangoFechas(inicio, fin);

        if (comprobantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron comprobantes entre " + inicio + " y " + fin);
        } else {
            List<ComprobanteDTO> listaDTO = comprobantes.stream().map((x) -> {
                ModelMapper m = new ModelMapper();
                return (ComprobanteDTO) m.map(x, ComprobanteDTO.class);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaDTO);
        }
    }
}
