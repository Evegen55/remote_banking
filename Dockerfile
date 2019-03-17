FROM openjdk:11.0.2-jre-slim-stretch
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} remote_banking-3.0-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/remote_banking-3.0-SNAPSHOT.jar"]