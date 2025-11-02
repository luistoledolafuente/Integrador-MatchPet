package com.matchpet.backend_user.config;

import com.matchpet.backend_user.model.RolModel;
import com.matchpet.backend_user.model.UserModel;
import com.matchpet.backend_user.repository.RolRepository;
import com.matchpet.backend_user.repository.UserRepository;
import com.matchpet.backend_user.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder; // ¡Importante!
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;
import java.util.UUID; // Para la contraseña aleatoria

@Component // ¡Marcamos como un Bean para que Spring lo encuentre!
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    // --- Herramientas que necesitamos ---
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder; // Para crear un hash de contraseña


    // Esta es la URL de tu Frontend (React/Angular/Vue)

    //descomenta para habiliadar frontend y configurar las properties
    //@Value("${frontend.url}")
    //private String frontendUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // 1. Obtenemos los datos del usuario de Google
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String nombre = (String) attributes.get("name");
        // String fotoUrl = (String) attributes.get("picture"); // También puedes guardar la foto

        // 2. Buscamos al usuario en NUESTRA BD.
        //    Si no existe, lo registramos (con el método de abajo)
        UserModel user = userRepository.findByEmail(email)
                .orElseGet(() -> registerNewGoogleUser(email, nombre));

        // 3. Generamos NUESTRO PROPIO token JWT
        //    (Como UserModel implementa UserDetails, esto funciona)
        String token = jwtService.generateToken(user);

        // 4. ¡LA MAGIA! Redirigimos al usuario DE VUELTA AL FRONTEND,
        //    pasándole nuestro token JWT en la URL.

        //configuracion de frontend
        //String redirectUrl = frontendUrl + "/login-success?token=" + token;
        //response.sendRedirect(redirectUrl);


        //prueba solo backend
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
        response.getWriter().flush(); // Asegura que se envíe la respuesta
    }

    /**
     * Método privado para crear un nuevo usuario si no existe
     */
    private UserModel registerNewGoogleUser(String email, String nombre) {

        // 1. Buscamos el rol "Adoptante"
        RolModel defaultRole = rolRepository.findByNombreRol("Adoptante")
                .orElseThrow(() -> new RuntimeException("Error: Rol 'Adoptante' no encontrado."));

        // 2. Creamos una contraseña aleatoria (este usuario solo usará Google)
        String randomPassword = UUID.randomUUID().toString();

        // 3. Construimos el nuevo usuario
        UserModel newUser = UserModel.builder()
                .email(email)
                .nombreCompleto(nombre)
                .hashContrasena(passwordEncoder.encode(randomPassword)) // Guardamos el hash
                .roles(Set.of(defaultRole))
                .estaActivo(true)
                .fechaCreacionPerfil(new Timestamp(System.currentTimeMillis()))
                .fechaActualizacion(new Timestamp(System.currentTimeMillis()))
                // .setFotoUrl(fotoUrl) // Si decides guardarla
                .build();

        // 4. Guardamos en la BD y lo devolvemos
        return userRepository.save(newUser);
    }
}