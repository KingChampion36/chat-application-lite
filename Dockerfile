FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install maven -y
COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY --from=build /target/chat-application-lite-1.0.0-SNAPSHOT.jar chat-app.jar

ENTRYPOINT ["java", "-jar", "chat-app.jar"]