package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.OperacionItem;
import pe.edu.upc.textilconnect.repositories.IOperacionItemRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IOperacionItemService;

import java.util.List;

@Service
public class OperacionItemServiceImplement implements IOperacionItemService {

    @Autowired
    private IOperacionItemRepository operacionItemRepository;

    @Override
    public List<OperacionItem> list() {
        return operacionItemRepository.findAll();
    }

    @Override
    public void insert(OperacionItem operacionItem) {
        operacionItemRepository.save(operacionItem);
    }
}
