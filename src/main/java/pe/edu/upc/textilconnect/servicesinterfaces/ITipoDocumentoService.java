package pe.edu.upc.textilconnect.servicesinterfaces;

import pe.edu.upc.textilconnect.entities.TipoDocumento;

import java.util.List;

public interface ITipoDocumentoService {
    List<TipoDocumento> list();

    void insert(TipoDocumento tipoDocumento);
}
