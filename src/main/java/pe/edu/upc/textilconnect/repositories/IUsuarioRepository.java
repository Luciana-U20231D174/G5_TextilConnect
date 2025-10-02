package pe.edu.upc.textilconnect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.textilconnect.entities.Usuario;

import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {
    public Usuario findOneByUsername(String username);

    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario LIKE CONCAT('%', :nombre, '%')")
    List<Usuario> buscarNombreU(@Param("nombre") String nombre);


    @Query("SELECT u FROM Usuario u WHERE u.emailUsuario LIKE CONCAT('%', :email, '%')")
    List<Usuario> buscarEmailU(@Param("email") String email);

}
