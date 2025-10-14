# MatchPet - Plataforma de Adopción de Mascotas

Este repositorio contiene el código fuente de los diferentes módulos del proyecto MatchPet, siguiendo una arquitectura de *Backend* Híbrido.

## ⚙️ Arquitectura del Proyecto

| Módulo | Tecnología Principal | Propósito |
| :--- | :--- | :--- |
| **`backend-admin/`** | **Django / DRF** | Gestión de Refugios, Animales, Especies y Administración. |
| **`frontend-web/`** | *Por definir* | Panel de administración y gestión de contenido. |
| **`frontend-movil/`** | *Por definir* | Interfaz para Adoptantes (Usuarios). |

***

## 🚀 Módulo Backend - Administración (`backend-admin/`)

Este módulo está desarrollado con Django REST Framework y es responsable de la lógica de gestión de contenido y administración de la plataforma.

### 🔑 Autenticación Implementada (Sprint-1)

Se implementó el sistema de **Login para Refugios/Administradores** utilizando **JSON Web Tokens (JWT)**.

| Endpoint | Método | Descripción |
| :--- | :--- | :--- |
| `/api/admin/login/` | `POST` | Autentica al Refugio y devuelve los tokens `access` y `refresh`. Requiere permisos `is_staff=True`. |

### 🛠️ Configuración y Ejecución

Para iniciar el servidor de desarrollo, sigue estos pasos:

1.  **Muévete al directorio del Backend:**
    ```bash
    cd backend-admin
    ```

2.  **Activa el Entorno Virtual:**
    ```bash
    source venv/Scripts/activate
    ```
    *(Ajusta el comando de activación si es necesario)*

3.  **Instala las Dependencias:**
    ```bash
    pip install -r requirements.txt
    ```

4.  **Inicia el Servidor:**
    ```bash
    python manage.py runserver
    ```
    El API estará disponible en `http://127.0.0.1:8000/`.

### 📚 Documentación de APIs

La documentación interactiva (Swagger UI) para todos los *endpoints* está disponible en:

👉 **[http://127.0.0.1:8000/api/schema/swagger-ui/](http://127.0.0.1:8000/api/schema/swagger-ui/)**
