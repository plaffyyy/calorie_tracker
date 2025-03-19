# Calories Tracker

Проект написан на Java 23 с использованием Spring Boot 3.
Для работы требуется БД PostgreSQL.

## Использование
Для запуска приложения требуется установленный и запущенный Docker версии больше 2.
В корне проекта нужно создать файл .env и прописать следующие значения:
- POSTGRES_DB=<Название базы данных>
- POSTGRES_USER=<Ваш пользователь в postgres>
- POSTGRES_PASSWORD=<Пароль этого пользователя>

### Запуск
Для этого потребуется установленные Java 23, Maven, Docker.

- В терминале написать команду:

          docker compose up --build

- Если нужно очистить базу данных при перезапуске, то напишите команду:

          docker-compose down -v
- Если нужно проверить добавление элементов в базе данных, то пишем:

        docker exec -it <POSTGRES_DB> bash
        psql -U <POSTGRES_USER> -d <POSTGRES_DB>

- Потом вводим пароль вашего пользователя и пишем необходимые SQL запросы



### API документация
После запуска приложения, документация будет доступна по адресу:
http://localhost:8081/swagger-ui/index.html#/

### Postman коллекция
Ссылка для скачивания Postman коллекции, чтобы протестировать
https://drive.google.com/file/d/1ZW59_nPaqDQAnOvRDM2Kch9ejsKi8X8n/view?usp=sharing