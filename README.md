<<<<<<< HEAD
# 🐾 MatchPet - Plataforma de Adopción de Mascotas

Este proyecto gestiona la adopción de mascotas con una arquitectura **Backend Híbrida**.

---

## ⚙️ Arquitectura del Proyecto

| Módulo | Tecnología | Propósito |
|--------|-------------|-----------|
| **backend-admin/** | Django / DRF | Gestión de refugios, animales y especies |
| **frontend-web/** | Por definir | Panel de administración |
| **frontend-movil/** | Por definir | Interfaz para adoptantes |

---

## 🚀 Backend (backend-admin)

Desarrollado con **Django REST Framework**, encargado de la lógica y administración del sistema.

### 🔑 Autenticación (Sprint 1)

Se implementó login con **JWT** para Refugios/Administradores.

| Endpoint | Método | Descripción |
|-----------|---------|--------------|
| `/api/admin/login/` | POST | Devuelve tokens `access` y `refresh` |

---

## 🛠️ Configuración con Docker

### Requisitos
- Docker Desktop / Engine  
- Docker Compose  

### 1️⃣ Iniciar entorno
```bash
docker compose up --build -d
```

### 2️⃣ Crear superusuario
``` bash
docker compose exec backend_admin python manage.py createsuperuser
```
### 🌐 Acceso
API Principal: http://127.0.0.1:8000/

Swagger UI: http://127.0.0.1:8000/api/schema/swagger-ui/

### 🛑 Detener entorno
``` bash
docker compose down
``` 
=======
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
>>>>>>> Moya
