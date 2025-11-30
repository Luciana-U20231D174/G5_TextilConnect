package pe.edu.upc.textilconnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.dtos.JwtRequestDTO;
import pe.edu.upc.textilconnect.dtos.JwtResponseDTO;
import pe.edu.upc.textilconnect.entities.Usuario;
import pe.edu.upc.textilconnect.repositories.IUsuarioRepository;
import pe.edu.upc.textilconnect.securities.JwtTokenUtil;
import pe.edu.upc.textilconnect.servicesimplements.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequestDTO req) {

        // 1. Buscar usuario
        Usuario u = usuarioRepository.findOneByUsername(req.getUsername());
        if (u == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario o contraseña incorrectos");
        }

        // 2. Verificar enabled
        if (u.getEnabled() != null && !u.getEnabled()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario deshabilitado");
        }

        // 3. Comparar password plano vs hash
        boolean ok = passwordEncoder.matches(req.getPassword(), u.getPassword());
        if (!ok) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario o contraseña incorrectos");
        }

        // 4. Generar token
        UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponseDTO(token));
    }
}
