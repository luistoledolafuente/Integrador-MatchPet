package com.matchpet.backend_user.service.Imp;

import com.matchpet.backend_user.dto.AuthResponse;
import com.matchpet.backend_user.dto.LoginRequest;
import com.matchpet.backend_user.dto.RegisterRequest;
import com.matchpet.backend_user.model.RolModel;
import com.matchpet.backend_user.model.UserModel;
import com.matchpet.backend_user.repository.RolRepository;
import com.matchpet.backend_user.repository.UserRepository;
import com.matchpet.backend_user.service.AuthService;
import com.matchpet.backend_user.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;

@Service
@RequiredArgsConstructor // ¡La magia de Lombok! Inyecta todas las dependencias 'final'
public class AuthServiceImpl implements AuthService {

    // --- Herramientas (Inyectadas por Lombok) ---
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    /**
     * Lógica de REGISTRO
     */
    @Override
    public AuthResponse register(RegisterRequest request) {

        // 1. Validar si el email ya existe
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        // 2. Buscar el rol por defecto (ej: "Adoptante")
        // ¡Asegúrate de que este rol exista en tu BD (con tu script SQL)!
        RolModel defaultRole = rolRepository.findByNombreRol("Adoptante")
                .orElseThrow(() -> new RuntimeException("Error: Rol 'Adoptante' no encontrado."));

        // 3. Crear el nuevo usuario
        UserModel user = UserModel.builder()
                .email(request.getEmail())
                .hashContrasena(passwordEncoder.encode(request.getPassword())) // ¡Encriptamos!
                .nombreCompleto(request.getNombreCompleto())
                .telefono(request.getTelefono())
                .roles(Set.of(defaultRole)) // Asignamos el rol
                .estaActivo(true)
                .fechaCreacionPerfil(new Timestamp(System.currentTimeMillis()))
                .fechaActualizacion(new Timestamp(System.currentTimeMillis()))
                .build();

        // 4. Guardar en la base de datos
        userRepository.save(user);

        // 5. Generar el token para el usuario recién registrado
        // 'user' es un UserDetails válido (porque UserModel lo implementa)
        String token = jwtService.generateToken(user);

        // 6. Devolver la respuesta
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    /**
     * Lógica de LOGIN
     */
    @Override
    public AuthResponse login(LoginRequest request) {

        // 1. Validar las credenciales
        // Esta línea es la que llama a tu UserDetailsServiceImpl y PasswordEncoder
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Si llegamos aquí, la autenticación fue exitosa.
        // Buscamos al usuario (que sabemos que existe) para generar el token
        UserModel user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(); // No necesitamos orElseThrow, ya fue validado

        // 3. Generar el token
        String token = jwtService.generateToken(user);

        // 4. Devolver la respuesta
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}