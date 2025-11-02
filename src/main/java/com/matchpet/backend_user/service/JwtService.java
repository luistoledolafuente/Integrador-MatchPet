package com.matchpet.backend_user.service;

import com.matchpet.backend_user.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // 1. Traemos la llave secreta desde application.properties
    @Value("${jwt.secret}")
    private String SECRET_KEY;


    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // --- Métodos Públicos ---

    /**
     * Genera un token JWT para el usuario.
     */
    public String generateToken(UserDetails userDetails) {
        // Buena práctica: Añadir "claims" (datos extra) al token
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof UserModel) {
            UserModel userModel = (UserModel) userDetails;
            claims.put("userId", userModel.getUsuarioId());
            claims.put("nombre", userModel.getNombreCompleto());
            // Puedes añadir roles aquí si lo deseas
        }

        return buildToken(claims, userDetails, EXPIRATION_TIME);
    }

    /**
     * Extrae el "username" (nuestro email) del token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Valida si el token es correcto y no ha expirado.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // --- Métodos Privados Auxiliares ---

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // Guardamos el email aquí
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Firmamos con la llave
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}