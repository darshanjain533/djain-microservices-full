#
# Build stage
#
FROM maven:3.8.3-openjdk-17  AS build
COPY src ./src
COPY pom.xml .
RUN mvn -f ./pom.xml clean package

#
# Package stage
#
FROM openjdk:17-oracle
COPY --from=build ./target/spring-eureka.jar /usr/local/lib/spring-eureka.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/usr/local/lib/spring-eureka.jar"]