package com.matchpet.api.config;

import com.matchpet.api.repository.AdoptanteRepository;
import com.matchpet.api.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.matchpet.api.model.Adoptante;
import java.time.LocalDateTime;

/**
 * Esta clase se ejecuta automáticamente una vez que la aplicación ha arrancado.
 * Su propósito es inicializar la base de datos con datos de prueba si es necesario.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final AdoptanteRepository adoptanteRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AdoptanteRepository adoptanteRepository, PasswordEncoder passwordEncoder) {
        this.adoptanteRepository = adoptanteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Verificando la existencia de datos de prueba...");

        // Usamos el email para verificar si el usuario ya existe
        String testUserEmail = "test@matchpet.com";
        if (adoptanteRepository.findByEmail(testUserEmail).isEmpty()) {
            // Si no existe, lo creamos
            Adoptante userDePrueba = new Adoptante();
            userDePrueba.setNombreCompleto("Usuario de Prueba");
            userDePrueba.setEmail(testUserEmail);
            userDePrueba.setHashContraseña(passwordEncoder.encode("password123"));
            userDePrueba.setTelefono("999888777");
            userDePrueba.setFechaCreacionPerfil(LocalDateTime.now());

            adoptanteRepository.save(userDePrueba);
            System.out.println("✅ Usuario de prueba '" + testUserEmail + "' creado exitosamente.");
        } else {
            // Si ya existe, simplemente lo informamos.
            System.out.println("ℹ️  El usuario de prueba '" + testUserEmail + "' ya existía.");
        }
    }
}

