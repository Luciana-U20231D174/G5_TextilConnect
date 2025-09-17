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

    @Override
    public Producto listId(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        productoRepository.deleteById(id);
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public List<Producto> buscarxNombre(String nombre) {
        return productoRepository.buscarNombrePd(nombre);
    }

    @Override
    public List<Producto> buscarxCategoria(String categoria) {
        return productoRepository.buscarCategoriaPd(categoria);
    }

    @Override
    public List<Producto> buscarxColor(String color) {
        return productoRepository.buscarColorPd(color);
    }
}
