# ---------------------------------------------
# docker build -t petstore:latest
# ---------------------------------------------
# Start with a base image containing Java runtime
FROM  openjdk:17-jdk-slim

# 
RUN mkdir /deployment
WORKDIR /deployment

RUN apt-get update && \
    apt-get install -y inetutils-ping

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=petstore.jar
ARG JAR_FILE_PATH=target/${JAR_FILE}
ARG ENTRYPOINT_JAR_FILE_PATH=/${JAR_FILE}

# Add the application's jar to the container
ADD ${JAR_FILE_PATH} ${JAR_FILE}

# Run the jar file
ENTRYPOINT ["java","-jar","petstore.jar"]
