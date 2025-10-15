package com.matchpet.api.service;

import com.matchpet.api.dto.AuthResponse;
import com.matchpet.api.dto.LoginRequest;
import com.matchpet.api.dto.RegistroRequest;
import com.matchpet.api.model.Adoptante;
import com.matchpet.api.repository.AdoptanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// @Service: Le indica a Spring que esta clase es un "Servicio". Es donde reside la lógica de negocio.
@Service
public class AuthService {

    // @Autowired: Esto es la "inyección de dependencias". Le pedimos a Spring que nos "inyecte"
    // o nos entregue las herramientas que necesitamos.
    @Autowired
    private AdoptanteRepository adoptanteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // --- DEPENDENCIAS AÑADIDAS PARA EL LOGIN ---
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    // Este es el método que orquestará todo el proceso de registro.
    public Adoptante registrar(RegistroRequest registroRequest) {

        // REGLA DE NEGOCIO 1: Validar que el email no esté ya en uso.
        if (adoptanteRepository.findByEmail(registroRequest.getEmail()).isPresent()) {
            // Si ya existe, lanzamos una excepción para detener el proceso y enviar un mensaje de error.
            throw new IllegalStateException("El correo electrónico ya está registrado.");
        }

        // REGLA DE NEGOCIO 2: Si el email está disponible, creamos un nuevo objeto Adoptante.
        Adoptante nuevoAdoptante = new Adoptante();
        nuevoAdoptante.setNombreCompleto(registroRequest.getNombreCompleto());
        nuevoAdoptante.setEmail(registroRequest.getEmail());
        nuevoAdoptante.setTelefono(registroRequest.getTelefono());

        // REGLA DE NEGOCIO 3 (¡La más importante!): Encriptar la contraseña.
        nuevoAdoptante.setHashContraseña(passwordEncoder.encode(registroRequest.getPassword()));

        nuevoAdoptante.setFechaCreacionPerfil(LocalDateTime.now());

        // REGLA DE NEGOCIO 4: Guardar el nuevo adoptante en la base de datos.
        return adoptanteRepository.save(nuevoAdoptante);
    }

    // --- NUEVO MÉTODO PARA EL LOGIN ---
    public AuthResponse login(LoginRequest loginRequest) {
        // 1. Le pedimos al AuthenticationManager que autentique al usuario.
        // Si las credenciales son incorrectas, aquí se lanzará una excepción y el método fallará.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // 2. Si la autenticación fue exitosa, buscamos los detalles del usuario en nuestra base de datos.
        UserDetails user = adoptanteRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado después de la autenticación."));

        // 3. Usamos nuestro JwtService para generar un token para este usuario.
        String token = jwtService.generateToken(user);

        // 4. Devolvemos el token dentro de nuestro DTO de respuesta.
        return new AuthResponse(token);
    }
}
