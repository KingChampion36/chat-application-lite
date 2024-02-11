# Use the official OpenJDK 17 image as base image
FROM openjdk:17-jdk-alpine

RUN mvn clean install -DskipTests

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY jarfile/chat-application-lite-1.0.0-SNAPSHOT.jar /app/chat-application-lite-1.0.0-SNAPSHOT.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Specify the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "chat-application-lite-1.0.0-SNAPSHOT.jar"]