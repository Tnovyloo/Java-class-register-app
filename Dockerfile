FROM amazoncorretto:22
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
# Run the Java application
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]