package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.dtos.ComprobanteCountDTO;
import pe.edu.upc.textilconnect.dtos.ComprobanteListDTO;
import pe.edu.upc.textilconnect.entities.Comprobante;

import java.util.List;

public interface IComprobanteService {

    public List<Comprobante> list();
    public void insert(Comprobante comprobante);

    public List<ComprobanteListDTO> listarPorOperacionDTO(int idOperacion);
    public ComprobanteCountDTO contarPorOperacionDTO(int idOperacion);
}
