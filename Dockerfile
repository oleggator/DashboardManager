FROM maven:3.6.0-jdk-12-alpine as build

WORKDIR /root
COPY pom.xml .
COPY src src

RUN mvn package


FROM openjdk:12-alpine

COPY --from=build /root/target/*jar-with-dependencies.jar /root/bundle.jar
WORKDIR /root

CMD ["java", "-jar", "bundle.jar"]