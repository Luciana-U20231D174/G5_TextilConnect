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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JwtUsuarioDetailsService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repo.findOneByUsername(username);

        if (u == null) {
            throw new UsernameNotFoundException(String.format("Usuario no encontrado para el usuario %s", username));
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        if (u.getRol() != null && u.getRol().getNombreRol() != null) {
            String r = u.getRol().getNombreRol();
            if (!r.startsWith("ROLE_")) {
                r = "ROLE_" + r.toUpperCase();
            }
            authorities.add(new SimpleGrantedAuthority(r));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new org.springframework.security.core.userdetails.User(
                (u.getEmailUsuario() != null ? u.getEmailUsuario() : u.getNombreUsuario()),
                u.getContrasenaUsuario(), // Asegúrate que esté en BCrypt
                true,   // enabled
                true,   // accountNonExpired
                true,   // credentialsNonExpired
                true,   // accountNonLocked
                authorities
        );

    }
}
