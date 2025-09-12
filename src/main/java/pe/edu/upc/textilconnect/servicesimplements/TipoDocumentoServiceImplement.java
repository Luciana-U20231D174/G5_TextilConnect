package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.TipoDocumento;
import pe.edu.upc.textilconnect.repositories.ITipoDocumentoRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.ITipoDocumentoService;

import java.util.List;
@Service
public class TipoDocumentoServiceImplement implements ITipoDocumentoService {

    @Autowired
    private ITipoDocumentoRepository dS;

    @Override
    public List<TipoDocumento> list() {return this.dS.findAll();}

    @Override
    public void insert(TipoDocumento tipoDocumento) {this.dS.save(tipoDocumento);}

    @Override
    public TipoDocumento listId(int id) {
        return dS.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        dS.deleteById(id);
    }

    @Override
    public void update(TipoDocumento tipoDocumento) {
        dS.save(tipoDocumento);
    }

    @Override
    public List<TipoDocumento> buscarService(String nombre) {
        return dS.buscarR(nombre);
    }
}
