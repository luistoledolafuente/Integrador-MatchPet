package com.matchpet.api.service;

import com.matchpet.api.dto.AuthResponse;
import com.matchpet.api.dto.LoginRequest;
import com.matchpet.api.dto.RegistroRequest;
import com.matchpet.api.model.Adoptante;
import com.matchpet.api.repository.AdoptanteRepository;
import lombok.RequiredArgsConstructor; // <-- Importar
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor // <-- Usar esta anotación
public class AuthService {

    // Cambiar @Autowired por 'private final'
    private final AdoptanteRepository adoptanteRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public Adoptante registrar(RegistroRequest registroRequest) {
        if (adoptanteRepository.findByEmail(registroRequest.getEmail()).isPresent()) {
            throw new IllegalStateException("El correo electrónico ya está registrado.");
        }
        Adoptante nuevoAdoptante = new Adoptante();
        nuevoAdoptante.setNombreCompleto(registroRequest.getNombreCompleto());
        nuevoAdoptante.setEmail(registroRequest.getEmail());
        nuevoAdoptante.setTelefono(registroRequest.getTelefono());
        nuevoAdoptante.setHashContraseña(passwordEncoder.encode(registroRequest.getPassword()));
        nuevoAdoptante.setFechaCreacionPerfil(LocalDateTime.now());
        return adoptanteRepository.save(nuevoAdoptante);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        UserDetails user = adoptanteRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado."));
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
