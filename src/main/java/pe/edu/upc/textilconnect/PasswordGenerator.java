package pe.edu.upc.textilconnect;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        String rawPassword = "admin123"; // la contraseña en texto plano
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        System.out.println("Contraseña encriptada: " + encodedPassword);
    }
}
