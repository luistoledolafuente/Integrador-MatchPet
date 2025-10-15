package com.matchpet.api.repository;

import com.matchpet.api.model.Adoptante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository: Le indica a Spring que esta interfaz es un Repositorio,
// un componente para el acceso a datos.
@Repository
// JpaRepository<Adoptante, Integer>: Le damos superpoderes a nuestra interfaz.
// Le decimos que va a manejar datos del tipo "Adoptante" (nuestra entidad)
// y que la clave primaria de "Adoptante" es de tipo "Integer" (el adoptanteId).
public interface AdoptanteRepository extends JpaRepository<Adoptante, Integer> {

    // ¡Aquí ocurre la magia de Spring Data JPA!
    // Simplemente al declarar un método con este nombre, Spring automáticamente
    // entiende que queremos buscar en la tabla "Adoptantes" una fila donde
    // la columna "email" coincida con el email que le pasemos.
    // Usamos Optional<> porque puede que el adoptante no exista, y así evitamos errores.
    Optional<Adoptante> findByEmail(String email);
}