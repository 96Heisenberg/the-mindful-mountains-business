# Use OpenJDK 17 slim image as base
FROM openjdk:17-jdk-slim
# Set working directory inside the container
WORKDIR /app
# Expose the default port used by Spring Boot (if applicable)
EXPOSE 8080
# Copy the built JAR file into the container
COPY build/libs/the-mindful-mountains-business-0.0.1-SNAPSHOT.jar app.jar
# Ensure proper execution permissions
RUN chmod +x app.jar
# Use exec form to pass runtime signals correctly
ENTRYPOINT [“java”, “-jar”, “app.jar”]
# Optional: Add JVM options to optimize memory for Cloud Run
CMD [“ — server.port=8080”]