package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.NotificacionDTO;
import pe.edu.upc.textilconnect.entities.Notificacion;
import pe.edu.upc.textilconnect.servicesinterfaces.INotificacionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class NotificacionController {

    @Autowired
    private INotificacionService notificacionService;

    @PostMapping
    public void insertar(@RequestBody NotificacionDTO ndto) {
        ModelMapper m = new ModelMapper();
        Notificacion n = (Notificacion)m.map(ndto, Notificacion.class);
        this.notificacionService.insert(n);
    }

    @GetMapping()
    public List<NotificacionDTO> listar() {
        return this.notificacionService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (NotificacionDTO)m.map(y, NotificacionDTO.class);
        }).collect(Collectors.toList());
    }
}
