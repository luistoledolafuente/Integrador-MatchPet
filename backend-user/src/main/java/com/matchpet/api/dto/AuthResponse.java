package com.matchpet.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Lombok: crea un constructor con todos los argumentos.
public class AuthResponse {
    private String token;
}