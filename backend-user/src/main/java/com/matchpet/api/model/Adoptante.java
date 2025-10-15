package com.matchpet.api.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

// @Data: Anotación de Lombok que crea por nosotros los métodos
// getter, setter, toString(), etc. ¡Nos ahorra mucho código!
@Data
// @Entity: Le dice a Spring que esta clase representa una tabla en la base de datos.
@Entity
// @Table: Especifica exactamente a qué tabla corresponde.
@Table(name = "Adoptantes")
// Implementamos la interfaz UserDetails para integrar nuestra clase con Spring Security.
public class Adoptante implements UserDetails {

    // @Id: Marca este campo como la clave primaria (primary key) de la tabla.
    @Id
    // @GeneratedValue: Le indica a la base de datos que genere este valor automáticamente
    // (es decir, que sea auto-incremental).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adoptanteId;

    // @Column: Permite especificar detalles sobre la columna en la base de datos.
    // Aquí decimos que no puede ser nula (NOT NULL) y que tiene un largo máximo de 255.
    @Column(nullable = false, length = 255)
    private String nombreCompleto;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    // columnDefinition = "TEXT" es útil para campos que pueden ser muy largos, como una contraseña hasheada.
    @Column(nullable = false, columnDefinition = "TEXT")
    private String hashContraseña;

    private String telefono;
    private LocalDate fechaNacimiento;

    @Column(columnDefinition = "TEXT")
    private String direccion;
    private String ciudad;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaCreacionPerfil;


    // --- MÉTODOS REQUERIDOS POR LA INTERFAZ UserDetails ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Para este proyecto no necesitamos roles (como ADMIN, USER), así que devolvemos una lista vacía.
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        // Le dice a Spring Security que el campo de la contraseña en nuestra clase es "hashContraseña".
        return this.hashContraseña;
    }

    @Override
    public String getUsername() {
        // Le dice a Spring Security que el campo que usaremos como "nombre de usuario" es el "email".
        return this.email;
    }

    // Los siguientes métodos los dejamos en 'true' por ahora. Indican que la cuenta está activa.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
