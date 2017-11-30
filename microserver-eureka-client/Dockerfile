FROM java:8

RUN mkdir /app
WORKDIR /app
ADD eureka-client.jar /app
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/eureka-client.jar", "--spring.profiles.active=docker"]
EXPOSE 8762