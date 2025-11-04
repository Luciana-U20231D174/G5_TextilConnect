package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.PresupuestoMensualDTO;
import pe.edu.upc.textilconnect.entities.PresupuestoMensual;
import pe.edu.upc.textilconnect.servicesinterfaces.IPresupuestoMensualService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/presupuestosmensuales")
public class PresupuestoMensualController {
    @Autowired
    private IPresupuestoMensualService presupuestoMensualService;

    @PostMapping
    public void insertar(@RequestBody PresupuestoMensualDTO pmdto) {
        ModelMapper m = new ModelMapper();
        PresupuestoMensual pm = (PresupuestoMensual) m.map(pmdto, PresupuestoMensual.class);
        this.presupuestoMensualService.insert(pm);
    }

    @GetMapping
    public List<PresupuestoMensualDTO> listar() {
        return this.presupuestoMensualService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (PresupuestoMensualDTO)m.map(y, PresupuestoMensualDTO.class);
        }).collect(Collectors.toList());
    }
}
