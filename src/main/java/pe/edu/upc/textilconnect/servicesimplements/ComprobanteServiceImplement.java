package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Comprobante;
import pe.edu.upc.textilconnect.repositories.IComprobanteRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IComprobanteService;

import java.util.List;

@Service
public class ComprobanteServiceImplement implements IComprobanteService {

    @Autowired
    private IComprobanteRepository comprobanteRepository;

    @Override
    public List<Comprobante> list() {
        return comprobanteRepository.findAll();
    }

    @Override
    public void insert(Comprobante comprobante) {
        comprobanteRepository.save(comprobante);
    }
}
