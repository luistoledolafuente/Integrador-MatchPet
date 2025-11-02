package com.matchpet.backend_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication

@OpenAPIDefinition(info = @Info(title = "MatchPet API", version = "1.0", description = "API de Backend para Usuarios"))
@SecurityScheme(
        name = "bearerAuth", // Un nombre para referenciarlo
        description = "Token JWT (pega tu token aquí)",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER // El token va en el Header
)
public class BackendUserApplication {


	public static void main(String[] args) {
		SpringApplication.run(BackendUserApplication.class, args);
	}

}
