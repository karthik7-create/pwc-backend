# ============================================================
# Stage 1: Build the application
# ============================================================
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy Maven wrapper and pom.xml first for dependency caching
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw

# Download dependencies (cached layer — only re-runs when pom.xml changes)
RUN mvn dependency:go-offline -B

# Copy source code and build the JAR (skip tests for faster builds)
COPY src ./src
RUN mvn clean package -DskipTests -B

# ============================================================
# Stage 2: Run the application (lightweight image)
# ============================================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
