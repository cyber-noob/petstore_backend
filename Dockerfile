FROM amazoncorretto:21.0.1-al2023-headless
VOLUME /tmp
EXPOSE 8082
COPY build/libs/petstore-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]