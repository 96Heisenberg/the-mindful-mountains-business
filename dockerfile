# ---------- Stage 1: Build ----------
# Using Eclipse Temurin for the build stage
FROM maven:3.8.8-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Stage 2: Runtime ----------
# Using the lightweight JRE image for production
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Cloud Run requirement
EXPOSE 8080

# Use optimal JVM flags for container environments
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=8080", "-jar", "app.jar"]