FROM openjdk:17-jdk-alpine
COPY target/outboxpattern-0.0.1-SNAPSHOT.jar outboxpattern-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java", "-jar", "outboxpattern-0.0.1-SNAPSHOT.jar" ]
