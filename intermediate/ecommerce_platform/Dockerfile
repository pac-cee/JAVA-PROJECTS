FROM openjdk:11-jdk-slim

WORKDIR /app

COPY target/ecommerce-platform-1.0.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"] 