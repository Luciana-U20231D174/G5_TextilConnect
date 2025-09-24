package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

    @Query("Select usuario from Usuario usuario where usuario.nombreUsuario like %:nombre%")
    public List<Usuario> buscarNombreU(@Param("nombre") String nombre);

    @Query("Select usuario from Usuario usuario where usuario.emailUsuario like %:email%")
    public List<Usuario> buscarEmailU(@Param("email") String email);
}
