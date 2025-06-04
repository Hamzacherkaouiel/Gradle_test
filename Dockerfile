FROM openjdk:21
RUN groupadd -r app && useradd -r -g app app
USER app
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
