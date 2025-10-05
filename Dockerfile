FROM amazoncorretto:17-alpine-jdk
WORKDIR /app

COPY target/TextilConnect-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]