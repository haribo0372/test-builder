# описываем первый этап с именем builder
FROM maven:3.9.6-amazoncorretto-21 as builder
# в рамках первого этапа копируем необходимые файлы Maven-проекта в директорию /app внутри образа
COPY src /app/src
COPY pom.xml /app
COPY .env /app
# собираем артефакт с помощью Maven
RUN mvn -f /app/pom.xml clean package

# новый этап, который не содержит слои из предыдущего этапа
FROM amazoncorretto:21
# копируем артефакт, собранный на предыдущем этапе, в итоговый чистый образ
COPY --from=builder /app/target/*.jar app.jar
COPY --from=builder /app/.env .

# указываем команду при запуске нового образа
ENTRYPOINT ["java", "-jar", "/app.jar"]