package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.PresupuestoMensual;
import pe.edu.upc.textilconnect.repositories.IPresupuestoMensualRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.IPresupuestoMensualService;

import java.util.List;

@Service
public class PresupuestoMensualServiceImplement implements IPresupuestoMensualService {

    @Autowired
    private IPresupuestoMensualRepository presupuestoMensualRepository;
    @Override
    public List<PresupuestoMensual> list() {
        return  presupuestoMensualRepository.findAll();
    }

    @Override
    public void insert(PresupuestoMensual presupuestoMensual) {
        presupuestoMensualRepository.save(presupuestoMensual);
    }
}
