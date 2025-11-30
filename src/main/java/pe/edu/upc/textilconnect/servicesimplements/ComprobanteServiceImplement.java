package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.dtos.ComprobanteCountDTO;
import pe.edu.upc.textilconnect.entities.Comprobante;
import pe.edu.upc.textilconnect.repositories.IComprobanteRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IComprobanteService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteServiceImplement implements IComprobanteService {

    @Autowired
    private IComprobanteRepository cR;

    @Override
    public void insert(Comprobante c) {
        cR.save(c);
    }

    @Override
    public List<Comprobante> list() {
        return cR.findAll();
    }

    @Override
    public Comprobante listId(int id) {
        Optional<Comprobante> opt = cR.findById(id);
        return opt.orElse(null);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public void update(Comprobante c) {
        cR.save(c);
    }

    @Override
    public List<Comprobante> listarPorOperacion(int idPedido) {
        return cR.listarPorOperacion(idPedido);
    }

    @Override
    public ComprobanteCountDTO contarPorOperacionDTO(int idPedido) {
        Long cantidad = cR.contarPorOperacion(idPedido);
        if (cantidad == null) cantidad = 0L;

        ComprobanteCountDTO dto = new ComprobanteCountDTO();
        dto.setIdPedido(idPedido);
        dto.setCantidadComprobantes(cantidad);
        return dto;
    }

    @Override
    public List<Comprobante> buscarxRangoFechas(LocalDate inicio, LocalDate fin) {
        return cR.buscarxRangoFechas(inicio, fin);
    }

    @Override
    public Double sumarIgvPorFecha(LocalDate fecha) {
        Double resultado = cR.sumarIgvPorFecha(fecha);
        if (resultado == null) {
            return 0.0;
        }
        return resultado;
    }
}
