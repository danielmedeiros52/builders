FROM gradle:latest AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test
RUN gradle bootJar


FROM  openjdk:11
VOLUME /tmp
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker", "-jar", "-Djava.net.preferIPv4Stack=true","/app.jar"]