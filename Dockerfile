#Dockerfile
FROM openjdk:11

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY gradlew.bat .
COPY settings.gradle .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew bootJar

RUN cp -a build/libs/*.jar /app.jar
# Make port 80 available to the world outside this container
EXPOSE 8070

# The application's jar file

# Add the application's jar to the container


# Run the jar file
# spring.profiles.active 는 빌드 환경에 받도록 수정
# ENTRYPOINT ["nohup", "java", "-jar", "/app.jar" "&"]
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/app.jar"]
