FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/acpoker-api.jar docker-acpoker-api.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /docker-acpoker-api.jar