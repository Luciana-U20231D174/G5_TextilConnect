package pe.edu.upc.textilconnect.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.repositories.IUsuarioRepository;

import java.util.Collections;
import java.util.Optional;

@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
<<<<<<< HEAD

=======
        // ⬇️ Usamos tu username para buscar
>>>>>>> 65504113ce5914de7bf62653f24d3d5cbc3e874c
        Usuario u = usuarioRepository.findOneByUsername(username);

        if (u == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

<<<<<<< HEAD
        // 🔹 Sacamos el nombre del rol de forma segura
        String baseRol = Optional.ofNullable(u.getRol())
                .map(r -> r.getNombreRol())  // "ADMIN", "VENDEDOR", "ESTUDIANTE"
                .orElse("ESTUDIANTE");       // por defecto, si no tuviera rol

        // 🔹 Spring Security trabaja por convención con "ROLE_..."
        GrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + baseRol);

        // 🔹 enabled por defecto true si viene null
        boolean enabled = (u.getEnabled() == null) || u.getEnabled();

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
=======
        // Nombre del rol en tu BD, ej "ADMIN", "VENDEDOR", "ESTUDIANTE"
        String baseRol = (u.getRol() != null && u.getRol().getNombreRol() != null)
                ? u.getRol().getNombreRol()
                : "ESTUDIANTE";

        // Spring Security trabaja bien con "ROLE_X"
        GrantedAuthority authority =
                new SimpleGrantedAuthority(u.getRol().getNombreRol());

        boolean enabled = (u.getEnabled() == null) ? true : u.getEnabled();

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),        // username que usas para login
                u.getPassword(),        // hash BCRYPT en BD
>>>>>>> 65504113ce5914de7bf62653f24d3d5cbc3e874c
                enabled,
                true,   // accountNonExpired
                true,   // credentialsNonExpired
                true,   // accountNonLocked
                Collections.singletonList(authority)
        );
    }
}
