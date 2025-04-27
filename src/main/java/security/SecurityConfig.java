package security; // ⚡ Asegúrate que el package coincide con tu estructura (o usa com.harrypotter.api.security si quieres mejor organización)

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 🔥 IMPORTANTE: No sesiones, solo tokens
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/h2-console/**").permitAll() // Liberar login, registro y consola H2
                        .anyRequest().authenticated() // Proteger todo lo demás
                )
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // Permitir H2 Console
                .formLogin(form -> form.disable()) // 🔥 Desactivar formulario de login de Spring
                .httpBasic(httpBasic -> httpBasic.disable()) // 🔥 Desactivar autenticación básica
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Añadir filtro JWT antes del de usuario y contraseña
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Cifrado seguro de contraseñas
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
