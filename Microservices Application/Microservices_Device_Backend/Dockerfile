FROM  openjdk:17-jdk-slim

ADD target/ds-2020-0.0.1-SNAPSHOT.jar /devices-backend.jar

EXPOSE 8081

CMD java $JAVA_OPTIONS -Dserver.port=8081 -jar /devices-backend.jar