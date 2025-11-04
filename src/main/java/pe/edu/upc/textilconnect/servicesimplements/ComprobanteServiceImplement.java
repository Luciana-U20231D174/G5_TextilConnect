package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.dtos.ComprobanteCountDTO;
import pe.edu.upc.textilconnect.dtos.ComprobanteListDTO;
import pe.edu.upc.textilconnect.entities.Comprobante;
import pe.edu.upc.textilconnect.repositories.IComprobanteRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IComprobanteService;

import java.time.LocalDate;
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
    public void insert(Comprobante comprobante) {comprobanteRepository.save(comprobante);
    }

    @Override
    public Comprobante listId(int id) {
        return comprobanteRepository.findById(id).orElse(new Comprobante());
    }

    @Override
    public void delete(int id) { comprobanteRepository.deleteById(id);

    }

    @Override
    public void update(Comprobante Comprobante) { comprobanteRepository.save(Comprobante);}

    @Override
    public List<ComprobanteListDTO> listarPorOperacionDTO(int idPedido) {
        return comprobanteRepository.listarPorPedido(idPedido)
                .stream()
                .map(c -> new ComprobanteListDTO(
                        c.getNumeroComprobante(),
                        c.getFechaComprobante(),
                        c.getTotalComprobante()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ComprobanteCountDTO contarPorOperacionDTO(int idPedido) {
        int total = comprobanteRepository.contarPorPedido(idPedido);
        return new ComprobanteCountDTO(total);
    }

    @Override
    public List<Comprobante> buscarxRangoFechas(LocalDate inicio, LocalDate fin) {
        return comprobanteRepository.buscarRangoFechasC(inicio, fin);
    }
}