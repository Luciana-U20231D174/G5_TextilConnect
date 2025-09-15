package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.ProductoFoto;
import pe.edu.upc.textilconnect.repositories.IProductoFotoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IProductoFotoService;

import java.util.List;

@Service
public class ProductoFotoServiceImplement implements IProductoFotoService {

    @Autowired
    private IProductoFotoRepository productoFotoRepository;

    @Override
    public List<ProductoFoto> list() {
        return productoFotoRepository.findAll();
    }

    @Override
    public void insert(ProductoFoto productoFoto) {
        productoFotoRepository.save(productoFoto);
    }
}
