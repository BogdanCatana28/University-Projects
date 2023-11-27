FROM  openjdk:17-jdk-slim

ADD target/ds-2020-0.0.1-SNAPSHOT.jar /users-backend.jar

EXPOSE 8080

CMD java $JAVA_OPTIONS -Dserver.port=8080 -jar /users-backend.jar