package com.matchpet.backend_user.service.Imp;

import com.matchpet.backend_user.dto.UserProfileResponse;
import com.matchpet.backend_user.model.UserModel;
import com.matchpet.backend_user.repository.UserRepository;
import com.matchpet.backend_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    // Necesitamos el repositorio para buscar los datos frescos del usuario
    private final UserRepository userRepository;

    /**
     * Obtiene el perfil del usuario que está "logeado" en este momento.
     */
    @Override
    public UserProfileResponse getUserProfile() {

        // 1. Obtenemos el "principal" (el objeto UserDetails) del contexto de seguridad.
        // ¡Nuestro JwtAuthenticationFilter puso esto aquí!
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 2. Extraemos el email (que es nuestro "username" en el UserDetails)
        String userEmail = userDetails.getUsername();

        // 3. (Mejor Práctica) Volvemos a buscar al usuario en la BD.
        // Esto asegura que devolvemos los datos más FRESCOS,
        // por si su perfil se actualizó en otra petición.
        UserModel user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en el contexto de seguridad"));

        // 4. Mapeamos la entidad UserModel al DTO de respuesta (la "caja" segura)
        return UserProfileResponse.builder()
                .usuarioId(user.getUsuarioId())
                .email(user.getEmail())
                .nombreCompleto(user.getNombreCompleto())
                .telefono(user.getTelefono())
                .build();
    }
}