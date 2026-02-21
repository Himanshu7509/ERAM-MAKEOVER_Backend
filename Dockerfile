# -------- BUILD STAGE --------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy Maven wrapper files first (better caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Give execute permission
RUN chmod +x mvnw

# Download dependencies first
RUN ./mvnw dependency:go-offline

# Copy project source
COPY src src

# Build jar
RUN ./mvnw clean package -DskipTests


# -------- RUN STAGE --------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Railway dynamic port
EXPOSE 8080

# Start application
ENTRYPOINT ["java","-jar","app.jar"]