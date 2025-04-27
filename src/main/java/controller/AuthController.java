package controller;

import model.Usuario;
import security.JwtUtil;
import service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.save(usuario);
        System.out.println("Usuario registrado con ID: " + nuevoUsuario.getId());
        return nuevoUsuario;
    }


    @PostMapping("/login")
    public String login(@RequestBody Usuario loginRequest) {
        Usuario usuario = usuarioService.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            return jwtUtil.generateToken(usuario.getUsername());
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }
}
