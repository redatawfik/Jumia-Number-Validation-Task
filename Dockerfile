FROM openjdk:8-jdk-alpine
LABEL maintainer="reda.rm148@gmail.com"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY sample.db sample.db
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]