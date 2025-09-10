package pe.edu.upc.textilconnect.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.textilconnect.dtos.TipoDocumentoDTO;
import pe.edu.upc.textilconnect.entities.TipoDocumento;
import pe.edu.upc.textilconnect.servicesinterfaces.ITipoDocumentoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/tiposdocumentos"})
public class TipoDocumentoController {
    @Autowired
    private ITipoDocumentoService dS;

    @GetMapping
    public List<TipoDocumentoDTO> listar(){
        return (List)this.dS.list().stream().map((y)->{
            ModelMapper m = new ModelMapper();
            return (TipoDocumentoDTO)m.map(y,TipoDocumentoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(TipoDocumentoDTO dto){
        ModelMapper m =new ModelMapper();
        TipoDocumento td=(TipoDocumento)m.map(dto,TipoDocumento.class);
        this.dS.insert(td);

    }
}
