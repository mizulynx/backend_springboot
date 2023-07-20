

# Use the official Maven image as the build environment
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project file to the container
COPY pom.xml .

RUN curl -fsSLO https://get.docker.com/builds/Linux/x86_64/docker-17.04.0-ce.tgz \
  && tar xzvf docker-17.04.0-ce.tgz \
  && mv docker/docker /usr/local/bin \
  && rm -r docker docker-17.04.0-ce.tgz

# Download the project dependencies to cache them
RUN mvn dependency:go-offline -B

# Copy the entire source code to the container
COPY src/ ./src/

# Build the Spring Boot application
RUN mvn package -DskipTests

# Create a new Docker image based on the JRE image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Spring Boot JAR file from the build environment to the container
COPY --from=build /app/target/app-*.jar ./app.jar

# Expose the port your Spring Boot app is listening on
EXPOSE 8080

# Command to run your application
ENTRYPOINT ["java", "-jar", "./app.jar"]
