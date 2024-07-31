# Usa una imagen base de Maven para construir la aplicación
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copia el archivo pom.xml y el código fuente
COPY pom.xml .
COPY src ./src

# Compila el proyecto
RUN mvn clean install

# Usa una imagen de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia el JAR generado desde la etapa de construcción
COPY --from=build /app/target/mokito-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080
EXPOSE 8080

# Define el comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
