FROM bellsoft/liberica-openjre-alpine:17
ARG JAR_FILE=/target/ru.usachev.interview.services.text-tasks-service-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

