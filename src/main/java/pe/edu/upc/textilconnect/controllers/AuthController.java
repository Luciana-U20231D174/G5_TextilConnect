package pe.edu.upc.textilconnect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.textilconnect.securities.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200") // permite llamadas desde tu Angular
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // DTO para recibir datos de login
    public static class LoginRequest {
        public String username;
        public String password;
    }

    // DTO para devolver el token
    public static class LoginResponse {
        public String token;
        public LoginResponse(String token) { this.token = token; }
    }

    // Endpoint POST /auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Autenticar usuario con AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username, request.password)
        );

        // Si todo sale bien, generar el token JWT
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);

        // Devolver token al frontend
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
