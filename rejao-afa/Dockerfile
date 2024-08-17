FROM openjdk:21-jdk-slim

WORKDIR /app

COPY . .

RUN chmod a+x ./gradlew

CMD ["./gradlew", "build"]