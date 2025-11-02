package com.matchpet.backend_user;

// --- Imports de Spring Test ---
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

// --- Imports de tu proyecto ---
import com.matchpet.backend_user.dto.AuthResponse;
import com.matchpet.backend_user.dto.LoginRequest;
import com.matchpet.backend_user.dto.RegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

// --- Imports estáticos (para hacer el código más limpio) ---
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 1. @SpringBootTest: Carga la aplicación completa (Controladores, Servicios, Repos, etc.)
 * para una prueba de integración real.
 *
 * 2. @AutoConfigureMockMvc: Configura el "Postman falso" (MockMvc)
 * para que podamos simular llamadas API.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AuthIntegrationTest {

    // 3. Inyectamos el "Postman falso"
    @Autowired
    private MockMvc mockMvc;

    // 4. Inyectamos el conversor de Java a JSON
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Este único test prueba el flujo completo:
     * HU-01 (Register), HU-02 (Login) y HU-03-related (Get Profile)
     */
    @Test
    public void testFullAuthenticationFlow() throws Exception {

        // --- 0. Preparación ---
        // Creamos un email único para que el test no falle si lo corremos 2 veces
        String uniqueEmail = "test-" + System.currentTimeMillis() + "@matchpet.com";
        String password = "password123";
        String nombre = "Test User Flow";

        // --- 1. Prueba de REGISTRO (HU-01) ---
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(uniqueEmail);
        registerRequest.setPassword(password);
        registerRequest.setNombreCompleto(nombre);
        registerRequest.setTelefono("999888777");

        mockMvc.perform(post("/api/auth/register") // Llama a POST /api/auth/register
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest))) // Envía el DTO como JSON
                .andExpect(status().isOk()) // Esperamos un 200 OK
                .andExpect(jsonPath("$.token").exists()); // Esperamos que devuelva un token


        // --- 2. Prueba de LOGIN (HU-02) ---
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(uniqueEmail);
        loginRequest.setPassword(password);

        // Hacemos la llamada de login y guardamos el resultado
        MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn(); // Guardamos el resultado para extraer el token

        // Extraemos el token del JSON de respuesta
        String jsonResponse = loginResult.getResponse().getContentAsString();
        AuthResponse authResponse = objectMapper.readValue(jsonResponse, AuthResponse.class);
        String token = authResponse.getToken();


        // --- 3. Prueba de ENDPOINT PROTEGIDO (HU-03-Test) ---

        // 3a. Prueba que Falla (sin token)
        mockMvc.perform(get("/api/user/profile")) // Llama a GET /api/user/profile
                .andExpect(status().isUnauthorized()); // <-- ¡CAMBIADO! (Esperamos 401)

        // 3b. Prueba Exitosa (con token)
        mockMvc.perform(get("/api/user/profile")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(uniqueEmail))
                .andExpect(jsonPath("$.nombreCompleto").value(nombre));
    }
}