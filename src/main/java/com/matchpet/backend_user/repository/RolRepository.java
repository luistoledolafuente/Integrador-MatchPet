package com.matchpet.backend_user.repository;

import com.matchpet.backend_user.model.RolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolModel, Integer> {

    /**
     * Igual que antes, esto crea la consulta:
     * "SELECT * FROM Roles WHERE nombre_rol = ?"
     *
     * Lo usaremos para buscar el rol "Adoptante" al registrar un usuario.
     */
    Optional<RolModel> findByNombreRol(String nombreRol);
}