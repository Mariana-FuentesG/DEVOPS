# syntax=docker/dockerfile:1
FROM maven:3.9-eclipse-temurin-25 AS build 
WORKDIR /app
COPY pom.xml ./
RUN for attempt in 1 2 3; do \
        mvn -B -q dependency:go-offline && exit 0; \
        echo "Reintentando descarga de dependencias ($attempt/3)"; \
        sleep 5; \
    done; \
    exit 1
COPY src ./src
RUN mvn -B -q package -DskipTests

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
RUN addgroup -S app \
    && adduser -S -G app app
USER app
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=5s --start-period=40s --retries=3 \
    CMD wget -q -O /dev/null http://127.0.0.1:8080/ || exit 1
ENTRYPOINT ["java", "-jar", "app.jar"]
