package com.matchpet.api.config;

import com.matchpet.api.filter.JwtAuthenticationFilter;
import com.matchpet.api.repository.AdoptanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Inyectamos el repositorio para poder buscar usuarios.
    @Autowired
    private AdoptanteRepository adoptanteRepository;

    /**
     * Define la cadena de filtros de seguridad que se aplicará a todas las peticiones HTTP.
     * @param http El objeto HttpSecurity para configurar la seguridad.
     * @param jwtAuthFilter El filtro JWT que se inyecta como parámetro para romper la referencia circular.
     * @return La cadena de filtros de seguridad construida.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Permite el acceso sin autenticación a los endpoints de registro y login.
                        .requestMatchers("/api/auth/**").permitAll()
                        // Requiere autenticación para cualquier otra petición.
                        .anyRequest().authenticated()
                )
                // Le decimos a Spring que use nuestro AuthenticationProvider personalizado.
                .authenticationProvider(authenticationProvider())
                // ¡LA PIEZA CLAVE!
                // Añade nuestro filtro de JWT antes del filtro de autenticación estándar de Spring.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Expone el gestor de autenticación de Spring para que podamos usarlo en nuestro AuthService.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Define el proveedor de autenticación. Une el UserDetailsService (cómo encontrar usuarios)
     * con el PasswordEncoder (cómo verificar contraseñas).
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Define cómo Spring Security debe cargar los detalles de un usuario.
     * En nuestro caso, a través del email desde el repositorio de Adoptantes.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> adoptanteRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + username));
    }

    /**
     * Define el bean para encriptar y verificar contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

