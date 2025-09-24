package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.PedidoDTO;
import pe.edu.upc.textilconnect.entities.Pedido;
import pe.edu.upc.textilconnect.servicesinterfaces.IPedidoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private IPedidoService pedidoService;

    @PostMapping
    public void insertar(@RequestBody PedidoDTO odto) {
        ModelMapper m = new ModelMapper();
        Pedido o = (Pedido)m.map(odto, Pedido.class);
        this.pedidoService.insert(o);
    }

    @GetMapping
    public List<PedidoDTO> listar() {
        return this.pedidoService.list().stream().map((y) -> {
            ModelMapper m = new ModelMapper();
            return (PedidoDTO)m.map(y, PedidoDTO.class);
        }).collect(Collectors.toList());
    }
}
