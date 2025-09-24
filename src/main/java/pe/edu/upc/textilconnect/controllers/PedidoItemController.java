package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.PedidoItemDTO;
import pe.edu.upc.textilconnect.entities.PedidoItem;
import pe.edu.upc.textilconnect.servicesinterfaces.IPedidoItemService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidositems")
public class PedidoItemController {

    @Autowired
    private IPedidoItemService pedidoItemService;

    @PostMapping
    public void insertar(@RequestBody PedidoItemDTO oidto) {
        ModelMapper m = new ModelMapper();
        PedidoItem oi = (PedidoItem)m.map(oidto, PedidoItem.class);
        this.pedidoItemService.insert(oi);
    }

    @GetMapping
    public List<PedidoItemDTO> listar() {
        return this.pedidoItemService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (PedidoItemDTO)m.map(y, PedidoItemDTO.class);
        }).collect(Collectors.toList());
    }
}
