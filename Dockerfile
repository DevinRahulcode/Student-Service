# Stage 1: Build
FROM eclipse-temurin:21-jdk-jammy as builder
WORKDIR /workspace

COPY . .
RUN chmod +x ./mvnw
ENV MAVEN_OPTS="-Xmx512m"
RUN ./mvnw clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /workspace/target/student-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
