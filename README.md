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
