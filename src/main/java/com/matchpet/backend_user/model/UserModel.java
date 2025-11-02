package com.matchpet.backend_user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Usuarios")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "hash_contraseña", nullable = false)
    private String hashContrasena;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fecha_creacion_perfil", updatable = false)
    private Timestamp fechaCreacionPerfil;

    @Column(name = "fecha_actualizacion")
    private Timestamp fechaActualizacion;

    @Column(name = "esta_activo", nullable = false)
    private boolean estaActivo;

    // --- Relación Muchos-a-Muchos con Roles ---
    @ManyToMany(fetch = FetchType.EAGER) // EAGER: Traer los roles junto con el usuario
    @JoinTable(
            name = "Usuario_Roles", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "usuario_id"), // Clave de esta entidad
            inverseJoinColumns = @JoinColumn(name = "rol_id") // Clave de la otra entidad
    )
    private Set<RolModel> roles;


    // --- MÉTODOS DE USERDETAILS (¡LA CLAVE DE SECURITY!) ---
    // Spring Security usará estos métodos para autenticar.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Le decimos a Spring que nuestra colección de "roles"
        // es la lista de autoridades.
        return this.roles;
    }

    @Override
    public String getPassword() {
        // Le decimos a Spring dónde está la contraseña ENCRIPTADA.
        return this.hashContrasena;
    }

    @Override
    public String getUsername() {
        // Le decimos a Spring que nuestro "username" es el EMAIL.
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Asumimos que las cuentas nunca expiran
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Asumimos que las cuentas nunca se bloquean
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Asumimos que las credenciales nunca expiran
    }

    @Override
    public boolean isEnabled() {
        // Le decimos a Spring si el usuario está activo o no.
        return this.estaActivo;
    }
}