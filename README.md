# Backend de Usuarios - MatchPet 🐾

Este es el servicio de backend para la autenticación y gestión de usuarios del proyecto MatchPet. Implementa un sistema de autenticación JWT (Tokens) "sin estado" (stateless).

## Historias de Usuario Completadas

* ✔️ **HU-01:** Registro con correo y contraseña.
* ✔️ **HU-02:** Inicio de sesión con correo y contraseña.
* ✔️ **HU-03:** Inicio de sesión y registro automático con Google (OAuth2).

---

## 🚀 Cómo Empezar (Setup Local)

Sigue estos 4 pasos para correr el proyecto en tu máquina local.

### 1. Prerrequisitos
* Java 17 (o superior)
* Apache Maven
* MySQL 8 (o un servidor de MySQL)

### 2. Base de Datos
1.  Abre tu gestor de MySQL (Workbench, DBeaver, etc.).
2.  Crea una nueva base de datos (schema) llamada: `db_matchpet`
3.  Ejecuta el script SQL (`database.sql` o similar) para crear las tablas (`Usuarios`, `Roles`, `Usuario_Roles`).
4.  **¡Crítico!** Asegúrate de insertar los roles base:
    ```sql
    INSERT INTO Roles (nombre_rol) VALUES ('Adoptante');
    ```

### 3. Configuración de Secretos (¡Importante!)
Este proyecto usa un sistema de perfiles para manejar los secretos. La configuración compartida (Google, JWT) ya está en `application-dev.properties` (que está en Git).

Tú **solo necesitas** configurar tu contraseña de base de datos local:

1.  Ve a `src/main/resources/`.
2.  Busca el archivo `application-local.properties.example` (es un molde).
3.  **Crea una copia** de ese archivo en la misma carpeta.
4.  Renombra la copia a: `application-local.properties` (Este archivo es ignorado por Git y es solo tuyo).
5.  Abre el nuevo `application-local.properties` y pon tu contraseña de MySQL:
    ```properties
    spring.datasource.password=TU_PASSWORD_DE_MYSQL_AQUI
    ```

### 4. Correr la Aplicación
1.  Abre el proyecto en tu IDE (IntelliJ, VSCode).
2.  Espera a que Maven descargue las dependencias.
3.  ¡Corre la clase `BackendUserApplication.java`!

El servidor estará activo en `http://localhost:8080`.

---

## 📄 Documentación de la API (Swagger)

Una vez que la aplicación esté corriendo, puedes ver **toda la documentación interactiva** de la API aquí:

➡️ **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

### ¿Cómo probar endpoints protegidos?
1.  Usa `POST /api/auth/login` para obtener un token.
2.  En la página de Swagger, haz clic en el botón verde **"Authorize"** 🔒.
3.  Escribe `Bearer ` (con un espacio) seguido de tu token y haz clic en "Authorize".
4.  ¡Ahora puedes probar todos los endpoints protegidos (como `GET /api/user/profile`)!

---

## Endpoints Principales

Consulta `swagger-ui.html` para ver todos los detalles, DTOs y respuestas.

### Autenticación
* `POST /api/auth/register`: Registro de nuevo usuario.
* `POST /api/auth/login`: Login con correo y contraseña.

### Login con Google (Flujo Especial)
El login con Google no es un endpoint de API que se llama, es un **flujo de redirección**.
1.  Tu frontend debe **redirigir** al usuario a la URL mágica de Spring Security.
2.  Para saber cuál es esa URL, puedes consultar nuestro endpoint de documentación en Swagger: `GET /api/auth/google-login-url`.
3.  La URL que debe usar el frontend es: `GET /oauth2/authorization/google`.
4.  Después del éxito, el backend redirigirá al frontend a `(tu-url-frontend)/login-success?token=...` (Esto se configura en el backend).

### Perfil (Protegido - Requiere Bearer Token)
* `GET /api/user/profile`: Obtiene la información del usuario autenticado.