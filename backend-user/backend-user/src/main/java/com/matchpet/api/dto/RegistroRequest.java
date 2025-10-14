package com.matchpet.api.dto;

import lombok.Data;

// @Data: De nuevo, usamos Lombok para que nos genere automáticamente
// los getters, setters y otros métodos útiles.
// Esta clase no lleva anotaciones de @Entity o @Table porque no es una tabla,
// es solo un contenedor temporal para los datos que viajan por la red.
@Data
public class RegistroRequest {

    // Estos son los campos que esperamos recibir del formulario de registro del frontend.
    private String nombreCompleto;
    private String email;
    // Recibimos la contraseña en texto plano aquí.
    // Nunca la guardaremos así, pero necesitamos recibirla para poder hashearla.
    private String password;
    private String telefono;

}