
FROM  openjdk:11
VOLUME /tmp
COPY build/libs/builders-* app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker", "-jar", "-Djava.net.preferIPv4Stack=true","/app.jar"]