FROM openjdk:11.0-jdk-slim-stretch
COPY ${JAR_FILE} apiConnection-0.0.1.jar
ENTRYPOINT ["java", "-jar","/apiConnection-0.0.1.jar"]