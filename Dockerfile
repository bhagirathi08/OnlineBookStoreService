FROM java:8

RUN rm -rf /src
COPY . /src
WORKDIR /src

RUN set -ex \
  && ./mvnw clean install -DskipTests

CMD ["java", "-jar", "target/onlinebookstore-0.0.1-SNAPSHOT.jar", "--server.port=8080"]
