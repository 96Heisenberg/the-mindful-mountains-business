# Use Java as the base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper / pom and download dependencies first (for caching)
COPY pom.xml ./
RUN apk add --no-cache maven \
&& mvn dependency:go-offline

# Copy application source
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Expose Spring Boot port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/*.jar"]