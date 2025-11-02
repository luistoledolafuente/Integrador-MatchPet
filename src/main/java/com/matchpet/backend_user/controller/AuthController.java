package com.matchpet.backend_user.controller;

import com.matchpet.backend_user.dto.AuthResponse;
import com.matchpet.backend_user.dto.LoginRequest;
import com.matchpet.backend_user.dto.RegisterRequest;
import com.matchpet.backend_user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping; // <-- Añade este
import java.util.Map; // <-- Añade este

@RestController // 1. Le dice a Spring que esto es un controlador API (devuelve JSON)
@RequestMapping("/api/auth") // 2. URL base para todos los métodos de esta clase
@RequiredArgsConstructor
@Tag(name = "1. Autenticación", description = "Endpoints para Registro y Login") // Grupo de Endpoints
public class AuthController {

    // 3. Inyectamos el "cerebro" (el servicio)
    private final AuthService authService;

    @Operation(summary = "Registra un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente (devuelve token)"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (ej: email mal formado, pass corto)"),
            @ApiResponse(responseCode = "500", description = "Error interno (ej: email ya existe)")
    })

    /**
     * Endpoint para REGISTRAR un nuevo usuario.
     * URL: POST http://localhost:8080/api/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    /**
     * Endpoint para INICIAR SESIÓN.
     * URL: POST http://localhost:8080/api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        // 6. Devolvemos una respuesta HTTP 200 OK con el token
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(
            summary = "Obtener URL para Login con Google (Solo Documentación)",
            description = "¡ESTE ENDPOINT NO SE LLAMA DIRECTAMENTE! \n\n" +
                    "Es solo para documentación. \n\n" +
                    "Para iniciar el login con Google, el frontend debe **redirigir** al usuario (con un link `<a>` o un `window.location.href`) " +
                    "a la URL que se devuelve en la respuesta: `/oauth2/authorization/google`"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve la URL a la que el frontend debe redirigir al usuario")
    })
    @GetMapping("/google-login-url")
    public ResponseEntity<Map<String, String>> getGoogleLoginUrl() {
        // Devolvemos la URL mágica que creó Spring Security
        return ResponseEntity.ok(Map.of("google_login_url", "/oauth2/authorization/google"));
    }
}