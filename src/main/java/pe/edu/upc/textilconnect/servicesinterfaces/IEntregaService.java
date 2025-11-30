// pe.edu.upc.textilconnect.servicesinterfaces.IEntregaService
package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Entrega;

import java.time.LocalDateTime;
import java.util.List;

public interface IEntregaService {
    List<Entrega> list();
    void insert(Entrega entrega);
    Entrega listId(int id);
    void update(Entrega entrega);

    void delete(int id);                    // 👈 FALTABAAAA

    List<Entrega> buscarxEstado(String estado);
    List<Entrega> buscarxTipo(String tipo);
    Long contarCanceladasPorRango(LocalDateTime inicio, LocalDateTime fin);
    Long contarPorRango(LocalDateTime inicio, LocalDateTime fin);

}
