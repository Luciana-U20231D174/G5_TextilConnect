package pe.edu.upc.textilconnect.servicesimplements;// pe.edu.upc.textilconnect.servicesimplements.CalificacionServiceImplement
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Calificacion;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.repositories.ICalificacionRepository;
import pe.edu.upc.textilconnect.repositories.IUsuarioRepository;
import pe.edu.upc.textilconnect.servicesinterfaces.ICalificacionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class CalificacionServiceImplement implements ICalificacionService {

    @Autowired
    private ICalificacionRepository cR;

    @Autowired
    private IUsuarioRepository uR;

    @Override
    public List<Calificacion> list() {
        return cR.findAll();
    }

    @Override
    public void insert(Calificacion calificacion) {
        // Si no te mandan fecha desde el front, la pones tú
        if (calificacion.getFechaCalificacion() == null) {
            calificacion.setFechaCalificacion(LocalDate.now());
        }

        cR.save(calificacion);

        // Recalcular stats del usuario calificado
        if (calificacion.getCalificado() != null &&
                calificacion.getCalificado().getIdUsuario() != null) {
            actualizarCalificacionesUsuario(calificacion.getCalificado().getIdUsuario());
        }
    }

    @Override
    public Calificacion listId(int id) {
        return cR.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        Calificacion cal = cR.findById(id).orElse(null);
        if (cal != null) {
            Integer idUsuarioCalificado = null;
            if (cal.getCalificado() != null) {
                idUsuarioCalificado = cal.getCalificado().getIdUsuario();
            }

            cR.deleteById(id);

            // Recalcular stats del usuario calificado si lo teníamos
            if (idUsuarioCalificado != null) {
                actualizarCalificacionesUsuario(idUsuarioCalificado);
            }
        }
    }

    @Override
    public void update(Calificacion calificacion) {
        // Traer la calificación original por si cambian el usuario calificado
        Calificacion original = cR.findById(calificacion.getIdCalificacion()).orElse(null);

        cR.save(calificacion);

        // Recalcular para el usuario original (si existía)
        if (original != null && original.getCalificado() != null &&
                original.getCalificado().getIdUsuario() != null) {
            actualizarCalificacionesUsuario(original.getCalificado().getIdUsuario());
        }

        // Y también para el nuevo usuario calificado (si lo cambiaron)
        if (calificacion.getCalificado() != null &&
                calificacion.getCalificado().getIdUsuario() != null) {
            actualizarCalificacionesUsuario(calificacion.getCalificado().getIdUsuario());
        }
    }

    // 🔥 Este método hace que promedioCalificacion y totalCalificacion
    //     dependan SIEMPRE de la tabla Calificacion
    private void actualizarCalificacionesUsuario(Integer idUsuario) {
        // Sacamos promedio y total desde la tabla Calificacion
        Double promedio = cR.obtenerPromedioCalificacion(idUsuario);
        int total = cR.obtenerTotalCalificacion(idUsuario);

        Usuario usuario = uR.findById(idUsuario).orElse(null);
        if (usuario == null) {
            return;
        }

        BigDecimal promedioBD = BigDecimal
                .valueOf(promedio != null ? promedio : 0.0)
                .setScale(2, RoundingMode.HALF_UP);

        usuario.setPromedioCalificacion(promedioBD);
        usuario.setTotalCalificacion(total);

        uR.save(usuario);
    }
}
