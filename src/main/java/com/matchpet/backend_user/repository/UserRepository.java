package com.matchpet.backend_user.repository;

import com.matchpet.backend_user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    /**
     * ¡Magia de Spring Data JPA!
     * Solo por nombrar este método, Spring entiende que debe
     * ejecutar: "SELECT * FROM Usuarios WHERE email = ?"
     *
     * Lo usaremos para el login y para verificar si un email ya existe.
     * Usamos Optional<> porque puede que no encuentre al usuario.
     */
    Optional<UserModel> findByEmail(String email);
}