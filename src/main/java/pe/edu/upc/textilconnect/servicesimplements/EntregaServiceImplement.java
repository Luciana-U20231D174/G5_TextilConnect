package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Entrega;
import pe.edu.upc.textilconnect.repositories.IEntregaRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IEntregaService;

import java.util.List;

@Service
public class EntregaServiceImplement implements IEntregaService {

    @Autowired
    private IEntregaRepository entregaRepository;

    @Override
    public List<Entrega> list() {
        return entregaRepository.findAll();
    }

    @Override
    public void insert(Entrega entrega) {
        entregaRepository.save(entrega);
    }

    @Override
    public Entrega listId(int id) {
        return entregaRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Entrega entrega) {
        entregaRepository.save(entrega);
    }

    @Override
    public List<Entrega> buscarxEstado(String estado) {
        return entregaRepository.buscarEstadoE(estado);
    }

    @Override
    public List<Entrega> buscarxTipo(String tipo) {
        return entregaRepository.buscarTipoE(tipo);
    }
}
