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
    public List<TipoDocumento> list() {return this.dS.findAll();}
    public void insert(TipoDocumento tipoDocumento) {this.dS.save(tipoDocumento);}
}
