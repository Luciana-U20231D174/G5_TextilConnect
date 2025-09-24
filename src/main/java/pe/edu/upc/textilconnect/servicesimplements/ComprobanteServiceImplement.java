package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.dtos.ComprobanteCountDTO;
import pe.edu.upc.textilconnect.dtos.ComprobanteListDTO;
import pe.edu.upc.textilconnect.entities.Comprobante;
import pe.edu.upc.textilconnect.repositories.IComprobanteRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IComprobanteService;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ComprobanteListDTO> listarPorOperacionDTO(int idOperacion) {
        return comprobanteRepository.listarPorOperacion(idOperacion)
                .stream()
                .map(c -> new ComprobanteListDTO(
                        c.getNumeroComprobante(),
                        c.getFechaComprobante(),
                        c.getTotalComprobante()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ComprobanteCountDTO contarPorOperacionDTO(int idOperacion) {
        int total = comprobanteRepository.contarPorOperacion(idOperacion);
        return new ComprobanteCountDTO(total);
    }

}
