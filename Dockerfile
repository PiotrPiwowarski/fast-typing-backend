FROM openjdk:17-jdk
COPY target/app.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]