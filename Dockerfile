FROM maven:3.8.6-jdk-11 as build-image
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN mvn clean install
CMD mvn spring-boot:run

FROM openjdk:11.0-jre
COPY --from=build-image /app/source/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
