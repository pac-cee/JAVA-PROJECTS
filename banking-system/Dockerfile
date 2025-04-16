# Dockerfile for Java Banking System (Swing UI)
FROM eclipse-temurin:24-jdk
RUN apt-get update && \
    apt-get install -y libxext6 libxrender1 libxtst6 libxi6 libfreetype6 libfontconfig1 && \
    rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY target/banking-system-0.0.1-SNAPSHOT.jar app.jar
ENV DISPLAY=:0
CMD ["java", "-jar", "app.jar"]
