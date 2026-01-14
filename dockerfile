# ---------- Stage 1: Build ----------
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

# Copy pom.xml and download dependencies (cached layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Stage 2: Runtime ----------
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the jar from the build stage
# Note: Ensure the jar name matches your artifactId in pom.xml
COPY --from=build /app/target/*.jar app.jar

# Cloud Run uses the PORT env var (default 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "app.jar"]