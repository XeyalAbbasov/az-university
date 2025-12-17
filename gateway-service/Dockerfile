# Stage 1:Build
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2:Run
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar gateway-service.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]



