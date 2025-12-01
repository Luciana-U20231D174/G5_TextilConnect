# ---------- STAGE 1: BUILD ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos el pom primero (cacheo de dependencias)
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Empaquetamos el proyecto (genera el .jar en /app/target)
RUN mvn -q -DskipTests package


# ---------- STAGE 2: RUN ----------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiamos el JAR construido desde el stage anterior
COPY --from=build /app/target/*.jar app.jar

# Para Render es buena práctica usar el PORT que te da la plataforma
# Asegúrate de tener en application.properties:
# server.port=${PORT:8080}
ENV JAVA_OPTS=""

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
