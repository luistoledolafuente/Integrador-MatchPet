package com.matchpet.backend_user.service;

import com.matchpet.backend_user.dto.AuthResponse;
import com.matchpet.backend_user.dto.LoginRequest;
import com.matchpet.backend_user.dto.RegisterRequest;

/**
 * Este es el "contrato" de nuestro servicio de autenticación.
 * Define QUÉ se puede hacer, pero no CÓMO se hace.
 */
public interface AuthService {
    /**
     * Registra un nuevo usuario en el sistema.
     * @param request El DTO con los datos del nuevo usuario.
     * @return Una respuesta con el token JWT.
     */
    AuthResponse register(RegisterRequest request);

    /**
     * Autentica un usuario existente.
     * @param request El DTO con el email y la contraseña.
     * @return Una respuesta con el token JWT si la autenticación es exitosa.
     */
    AuthResponse login(LoginRequest request);
}