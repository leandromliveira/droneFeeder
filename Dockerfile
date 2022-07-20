FROM openjdk:11

WORKDIR /opt/spring_boot

COPY /target/*.jar dronefeeder.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8080

CMD java -jar dronefeeder.jar