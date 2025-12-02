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

        Usuario u = usuarioRepository.findOneByUsername(username);

        if (u == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // 🔹 Sacamos el nombre del rol de forma segura
        String baseRol = Optional.ofNullable(u.getRol())
                .map(r -> r.getNombreRol())  // "ADMIN", "VENDEDOR", "ESTUDIANTE"
                .orElse("ESTUDIANTE");       // por defecto, si no tuviera rol

        // 🔹 Spring Security trabaja sin "ROLE_" si no lo estás utilizando
        GrantedAuthority authority = new SimpleGrantedAuthority(baseRol);

        // 🔹 enabled por defecto true si viene null
        boolean enabled = (u.getEnabled() == null) || u.getEnabled();

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
                enabled,
                true,   // accountNonExpired
                true,   // credentialsNonExpired
                true,   // accountNonLocked
                Collections.singletonList(authority)
        );
    }
}