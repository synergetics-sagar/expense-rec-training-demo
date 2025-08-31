# Use official openjdk-21 runtime as base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the packaged .jar file into the container
COPY target/expense-app.jar app.jar

# Expose application port
EXPOSE 9090

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]


