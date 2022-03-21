#################################################
# Imagen base para el contenedor de compilación
#################################################
FROM maven:3.8.4-openjdk-17 as builder

# Define el directorio de trabajo donde ejecutar comandos
WORKDIR /project

#Copia el pom para descargar las dependencia y cachear la capa
COPY pom.xml /project/

#Instala las dependencias
RUN mvn -B clean verify

# Copia el código del proyecto
COPY /src /project/src

# Compila el proyecto 
RUN mvn -B -o package

#################################################
# Imagen base para el contenedor de la aplicación
#################################################
FROM openjdk:17-jdk-slim

# Define el directorio de trabajo donde se encuentra el JAR
WORKDIR /usr/src/app/

# Copia el JAR del contenedor de compilación
COPY --from=builder /project/target/*.jar /usr/src/app/

# Indica el puerto que expone el contenedor
EXPOSE 8081

# Comando que se ejecuta al hacer docker run
CMD [ "java", "-jar", "planner-0.0.1-SNAPSHOT.jar" ]