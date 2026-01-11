FROM openjdk:17
EXPOSE 8080
ADD target/springboot-maven-deploy.jar springboot-maven-deploy.jar
ENTRYPOINT ["java", "-jar", "springboot-maven-deploy.jar"]