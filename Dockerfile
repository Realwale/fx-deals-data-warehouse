FROM openjdk:17-jdk-slim
COPY target/*.jar fx-deal-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "fx-deal-service.jar"]