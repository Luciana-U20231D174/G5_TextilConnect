package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.Tarjeta;

import java.time.LocalDate;
import java.util.List;

public interface ITarjetaService {
    public List<Tarjeta> list();
    public void insert(Tarjeta tarjeta);
    public void delete(int id);
    public void update(Tarjeta tarjeta);
    public Tarjeta listId(int id);
    public List<Object[]> contarPorTodasLasMarcas();

    List<Tarjeta> listarxusuario(int idUsuario);

    public List<Tarjeta> listarPorRangoVencimiento(LocalDate inicio, LocalDate fin);

}
