package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Pedido;
import pe.edu.upc.textilconnect.repositories.IPedidoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IPedidoService;

import java.util.List;

@Service
public class PedidoServiceImplement implements IPedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;


    @Override
    public List<Pedido> list() {
        return pedidoRepository.findAll();
    }

    @Override
    public void insert(Pedido pedido) {
        pedidoRepository.save(pedido);
    }
}
