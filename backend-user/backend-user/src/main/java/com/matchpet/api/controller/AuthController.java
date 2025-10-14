package com.matchpet.api.controller;

import com.matchpet.api.dto.AuthResponse;
import com.matchpet.api.dto.LoginRequest;
import com.matchpet.api.dto.RegistroRequest;
import com.matchpet.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroRequest registroRequest) {
        try {
            authService.registrar(registroRequest);
            return new ResponseEntity<>("¡Usuario registrado exitosamente!", HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // --- NUEVO ENDPOINT PARA EL LOGIN ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 1. Llamamos al método login de nuestro servicio.
            AuthResponse authResponse = authService.login(loginRequest);
            // 2. Si el login es exitoso, devolvemos una respuesta OK (200) con el token en el cuerpo.
            return ResponseEntity.ok(authResponse);
        } catch (AuthenticationException e) {
            // 3. Si Spring Security no pudo autenticar al usuario (contraseña incorrecta),
            // capturamos la excepción y devolvemos un error 401 (Unauthorized).
            return new ResponseEntity<>("Email o contraseña inválidos.", HttpStatus.UNAUTHORIZED);
        }
    }
}