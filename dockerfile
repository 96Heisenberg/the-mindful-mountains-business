# ---------- Stage 1: Build ----------
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# This shell command finds the fat JAR and renames it
RUN cp target/$(ls target/ | grep -v "original" | grep ".jar" | head -n 1) app.jar

# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "app.jar"]