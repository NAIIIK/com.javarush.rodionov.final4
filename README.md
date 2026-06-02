# Итоговый проект 4 модуля - JavaRush University

---

Проект представляет собой Java-приложение на основе **PostgreSQL** и **Redis**, полностью контейнеризованное с помощью **Docker Compose**.

Приложение сравнивает скорость чтения данных из **PostgreSQL** и **Redis**.

---

## Запуск

### 1. Клонировать репозиторий

### 2. Cобрать JAR

`mvn clean package`

### 3. Конфигурировать данные пользователя базы данных.

Отредактируй в `docker-compose.yml`:

* переменные `POSTGRES_USER` и `POSTGRES_PASSWORD` в сервисе `postgres`
* переменные `DB_USER` и `DB_PASSWORD` в сервисе  `app`.

### 4. Собрать и запустить все сервисы

```bash
docker compose up --build
```

Для остановки сервисов

```bash
docker compose down
```

---

Переменные окружения передаются в контейнер приложения через `docker-compose.yml`:

| Переменная | Значение   | Описание |
|------------|------------|----------|
| `DB_NAME` | `final4` | Название базы данных |
| `DB_HOST` | `postgres` | Хост PostgreSQL (имя сервиса) |
| `DB_PORT` | `5432`     | Порт PostgreSQL |
| `DB_USER` | `username` | Пользователь базы данных |
| `DB_PASSWORD` | `password` | Пароль базы данных |
| `REDIS_HOST` | `redis`    | Хост Redis (имя сервиса) |
| `REDIS_PORT` | `6379`     | Порт Redis |