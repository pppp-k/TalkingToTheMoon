FROM openjdk:15
VOLUME /tmp
ADD boot-web-0.0.1.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]