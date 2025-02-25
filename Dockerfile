FROM amazoncorretto:21.0.4-alpine3.18
ARG JAR_FILE=target/library-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
CMD apt-get update -y
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]
