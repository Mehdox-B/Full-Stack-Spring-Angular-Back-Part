FROM openjdk:17-jdk-alpine
WORKDIR Spring_Boot_App
COPY ./target/Spring-BackSide-0.0.1-SNAPSHOT.jar /Spring_Boot_App
EXPOSE 8081
CMD ["java","-jar","Spring-BackSide-0.0.1-SNAPSHOT.jar"]