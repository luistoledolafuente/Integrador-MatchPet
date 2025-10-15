# Etapa 1: Construcción del proyecto con Maven
# Usamos una imagen oficial de Maven que ya tiene Java 17 instalado para compilar nuestro código.
FROM maven:3.8.5-openjdk-17 AS build

# Establecemos el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Copiamos solo los archivos de configuración de Maven para aprovechar el cache de Docker.
# Si no cambian las dependencias en pom.xml, Docker no volverá a descargarlas.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Descargamos todas las dependencias del proyecto.
RUN mvn dependency:go-offline

# Copiamos todo el código fuente de la aplicación.
COPY src ./src

# Empaquetamos la aplicación en un archivo .jar, saltando las pruebas para acelerar la construcción.
RUN mvn package -DskipTests

# Etapa 2: Ejecución de la aplicación
# Usamos una imagen mucho más ligera que solo tiene lo necesario para correr Java (JRE).
FROM eclipse-temurin:17-jre-jammy

# Establecemos el directorio de trabajo.
WORKDIR /app

# Copiamos el archivo .jar que se generó en la etapa anterior.
# El nombre del .jar debe coincidir con el <artifactId>-<version>.jar de tu pom.xml.
COPY --from=build /app/target/backend-user-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto 8080 para que el exterior pueda comunicarse con la aplicación.
EXPOSE 8080

# El comando que se ejecutará cuando el contenedor arranque.
ENTRYPOINT ["java", "-jar", "app.jar"]