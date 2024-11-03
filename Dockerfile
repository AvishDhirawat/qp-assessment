FROM ubuntu:latest
LABEL authors="Alpha"

ENTRYPOINT ["top", "-b"]
# Use a base image with JDK installed
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/grocery-booking-api.jar grocery-booking-api.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "grocery-booking-api.jar"]
