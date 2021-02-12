FROM openjdk:8-jdk-alpine
LABEL maintainer="reda.rm148@gmail.com"

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY pom.xml pom.xml
COPY sample.db sample.db
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]