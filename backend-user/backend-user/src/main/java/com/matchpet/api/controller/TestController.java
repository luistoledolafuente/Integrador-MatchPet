package com.matchpet.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/protegido")
    public ResponseEntity<String> getContenidoProtegido() {
        // Este mensaje solo debería ser visible para usuarios que han iniciado sesión
        // y envían un token válido.
        return ResponseEntity.ok("¡Felicidades! Has accedido a un recurso protegido con tu token.");
    }
}