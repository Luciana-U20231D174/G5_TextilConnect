package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.PedidoItem;
import pe.edu.upc.textilconnect.repositories.IPedidoItemRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IPedidoItemService;

import java.util.List;

@Service
public class PedidoItemServiceImplement implements IPedidoItemService {

    @Autowired
    private IPedidoItemRepository pedidoItemRepository;


    @Override
    public List<PedidoItem> list() {
        return pedidoItemRepository.findAll();
    }

    @Override
    public void insert(PedidoItem pedidoItem) {
        pedidoItemRepository.save(pedidoItem);
    }
}
