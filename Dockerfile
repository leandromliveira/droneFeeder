# FROM openjdk:11.0-jdk as build-image
# RUN mkdir -p /app/source
# COPY . /app/source
# WORKDIR /app/source
# RUN ./mvnw clean package

# FROM openjdk:11.0-jre
# COPY --from=build-image /app/source/target/*.jar /app/app.jar
# EXPOSE 8888
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]

FROM maven:3.8.6-jdk-11
WORKDIR .
COPY . .
# RUN mvn clean install
CMD mvn spring-boot:run

# FROM openjdk:11
# ADD target/teste-0.0.1-SNAPSHOT.jar teste-0.0.1-SNAPSHOT.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "teste-0.0.1-SNAPSHOT.jar"]
