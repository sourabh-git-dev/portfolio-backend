# Step 1: Use Maven to build the JAR
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Use a slim JDK image for running
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/portfolio-backend-0.0.1-SNAPSHOT.jar app.jar

# Run the jar
ENTRYPOINT ["java","-jar","app.jar"]
