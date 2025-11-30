package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByUsername(String username);

    // usado por JwtUserDetailsService y JwtAuthenticationController
    Usuario findOneByUsername(String username);

    @Query("select count(u.username) from Usuario u where u.username = :username")
    int buscarUsername(@Param("username") String nombre);

    // ✅ Listar activos (incluye los antiguos donde enabled está null)
    @Query("SELECT u FROM Usuario u WHERE u.enabled = TRUE OR u.enabled IS NULL")
    List<Usuario> listarActivos();
}
