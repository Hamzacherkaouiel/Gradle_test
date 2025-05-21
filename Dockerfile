# 🧱 Étape 1 : Build dans une image avec Gradle
FROM gradle:8.4.0-jdk21 AS builder

WORKDIR /app
COPY . .
RUN gradle build -x test

# 🚀 Étape 2 : Exécuter avec distroless
FROM gcr.io/distroless/java21
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["app.jar"]
