package com.matchpet.backend_user.controller;

import com.matchpet.backend_user.dto.UserProfileResponse;
import com.matchpet.backend_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/user") // ¡Nueva URL base! No es /api/auth
@RequiredArgsConstructor
@Tag(name = "2. Usuario", description = "Endpoints de gestión de perfil de usuario") // Nuevo grupo
public class UserController {

    private final UserService userService;

    @Operation(summary = "Obtiene el perfil del usuario autenticado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil obtenido exitosamente"),
            @ApiResponse(responseCode = "403", description = "No autorizado (Token no enviado o inválido)")
    })
    @SecurityRequirement(name = "bearerAuth")

    /**
     * Endpoint para OBTENER el perfil del usuario autenticado.
     * URL: GET http://localhost:8080/api/user/profile
     * ¡Este endpoint está protegido!
     * (Porque no empieza con /api/auth/)
     */
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfile() {
        // La lógica del servicio (que usa SecurityContextHolder)
        // se encarga de averiguar "quién soy yo".
        return ResponseEntity.ok(userService.getUserProfile());
    }
}