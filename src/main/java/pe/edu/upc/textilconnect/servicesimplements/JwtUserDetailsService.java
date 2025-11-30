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

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = usuarioRepository.findOneByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        GrantedAuthority authority =
                new SimpleGrantedAuthority(u.getRol().getNombreRol());

        // devolvemos el usuario de Spring con el HASH BCRYPT tal cual
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),                           // hash bcrypt de BD
                u.getEnabled() == null ? true : u.getEnabled(), // enabled
                true,
                true,
                true,
                Collections.singletonList(authority)
        );
    }
}
