# Etapa 1: Construcción (usa una imagen base de Node para instalar y construir)
FROM node:18-alpine AS builder
WORKDIR /app

# Copia los archivos de configuración
COPY package.json package-lock.json ./
# Instala las dependencias
RUN npm install

# Copia el código fuente
COPY . .

# Construye la aplicación de React. Esto crea la carpeta 'dist' (o 'build' si usas CRA)
# Asumimos 'npm run build' crea la carpeta 'dist'
RUN npm run build 

# Etapa 2: Servir (usa NGINX para servir los archivos estáticos de forma eficiente)
FROM nginx:alpine
# Copia los archivos estáticos del paso anterior al directorio de NGINX
COPY --from=builder /app/dist /usr/share/nginx/html

# Puerto por defecto de NGINX
EXPOSE 80 
# NOTA: En docker-compose mapearemos 80 a 3001
