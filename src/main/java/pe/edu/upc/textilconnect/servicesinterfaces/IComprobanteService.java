package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.dtos.ComprobanteCountDTO;
import pe.edu.upc.textilconnect.entities.Comprobante;

import java.time.LocalDate;
import java.util.List;

public interface IComprobanteService {

    void insert(Comprobante c);

    List<Comprobante> list();

    Comprobante listId(int id);

    void delete(int id);

    void update(Comprobante c);

    List<Comprobante> listarPorOperacion(int idPedido);

    ComprobanteCountDTO contarPorOperacionDTO(int idPedido);

    List<Comprobante> buscarxRangoFechas(LocalDate inicio, LocalDate fin);

    Double sumarIgvPorFecha(LocalDate fecha);

}
