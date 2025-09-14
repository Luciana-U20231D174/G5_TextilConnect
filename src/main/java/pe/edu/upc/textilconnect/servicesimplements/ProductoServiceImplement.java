package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Producto;
import pe.edu.upc.textilconnect.repositories.IProductoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IProductoService;

import java.util.List;

@Service
public class ProductoServiceImplement implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Producto> list() {
        return productoRepository.findAll();
    }

    @Override
    public void insert(Producto producto) {
        productoRepository.save(producto);
    }
}
