FROM maven:3.9-eclipse-temurin-23 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
COPY db/init /app/db/init
RUN mvn clean package -DskipTests

FROM openjdk:23

WORKDIR /app

COPY --from=build /app/target/system-0.0.1-SNAPSHOT.jar app.jar

COPY --from=build /app/db/init /app/db/init

ENTRYPOINT ["java", "-jar", "app.jar"]