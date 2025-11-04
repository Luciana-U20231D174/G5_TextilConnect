package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.dtos.ComprobanteCountDTO;
import pe.edu.upc.textilconnect.dtos.ComprobanteListDTO;
import pe.edu.upc.textilconnect.entities.Calificacion;
import pe.edu.upc.textilconnect.entities.Comprobante;

import java.time.LocalDate;
import java.util.List;

public interface IComprobanteService {

    public List<Comprobante> list();
    public void insert(Comprobante comprobante);
    public Comprobante listId(int id);
    public void delete(int id);
    public void update(Comprobante Comprobante);
    public List<ComprobanteListDTO> listarPorOperacionDTO(int idPedido);
    public ComprobanteCountDTO contarPorOperacionDTO(int idPedido);
    List<Comprobante> buscarxRangoFechas(LocalDate inicio, LocalDate fin);

}
