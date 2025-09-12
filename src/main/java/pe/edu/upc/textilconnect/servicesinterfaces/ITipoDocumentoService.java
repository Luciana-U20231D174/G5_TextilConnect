package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.TipoDocumento;

import java.util.List;

public interface ITipoDocumentoService {
    public List<TipoDocumento> list();
    public void insert(TipoDocumento tipoDocumento);
    public TipoDocumento listId(int id);
    public void delete(int id);
    public void update(TipoDocumento tipoDocumento);
    public List<TipoDocumento> buscarService(String nombre);
}
