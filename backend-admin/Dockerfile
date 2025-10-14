# backend-admin/Dockerfile

# 1. Imagen base de Python
FROM python:3.12-slim

# 2. Establecer variables de entorno
ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1


# 3. INSTALAR DEPENDENCIAS DE COMPILACIÓN Y MARIADB/MYSQL
RUN apt-get update \
    && apt-get install -y \
        pkg-config \
        libmariadb-dev \
        gcc \
    && rm -rf /var/lib/apt/lists/*
# 4. Establecer el directorio de trabajo dentro del contenedor
WORKDIR /usr/src/app

# 5. Copiar los archivos de dependencias y instalar
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# 6. Copiar el resto del código del proyecto
COPY . .

# 7. Definir el comando de inicio
CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]