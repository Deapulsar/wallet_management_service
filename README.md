# Учебный проект. Сервис по управлению счётами.

## Задание
Напишите приложение, которое по REST принимает запрос вида:

```agsl
POST api/v1/wallet
{
valletId: UUID,
operationType: DEPOSIT or WITHDRAW,
amount: 1000
}
```

После выполнять логику по изменению счета в базе данных, также есть возможность получить баланс кошелька

```agsl
GET api/v1/wallets/{WALLET_UUID}
```

Стек:

java 8-17

Spring 3

Postgresql

Должны быть написаны миграции для базы данных с помощью liquibase

Обратите особое внимание проблемам при работе в конкурентной среде (1000 RPS по
одному кошельку). Ни один запрос не должен быть не обработан (50Х error)

Предусмотрите соблюдение формата ответа для заведомо неверных запросов, когда
кошелька не существует, не валидный json, или недостаточно средств.

Приложение должно запускаться в докер контейнере, база данных тоже, вся система
должна подниматься с помощью docker-compose

Предусмотрите возможность настраивать различные параметры как на стороне
приложения, так и базы данных без пересборки контейнеров.

Эндпоинты должны быть покрыты тестами.

Решенное задание залить на гитхаб, предоставить ссылку

Все возникающие вопросы по заданию решать самостоятельно, по своему
усмотрению

# Запуск проекта

1) Клонировать репозиторий.
2) Выполнить команду "docker-compose up -d" находясь в корневой папке проекта.

Тестовый набор данных для кошельков(uuid и сумма) можно посмотреть в 
[a link](https://github.com/Deapulsar/wallet_management_service/blob/main/src/main/resources/db/changelog/changeset/data-postgre.sql)

# Работа приложения
После запуска проекта через команду "docker-compose up -d", отправив GET запрос в конечную точку 
http://localhost:8080/api/v1/wallets/*Номер_кошелька* можно получить баланс кошелька. 
Некорректный формат ввода номера кошелька вернёт статус 400 с ошибкой "Bad Request". 
Если кошелёк отсутсвует в базе помимо статуса вернет оповещение, что кошелёк не найден.
По адресу http://localhost:8080/api/v1/wallet можно отправлять POST запросы с телом JSON вида, описанного в ТЗ,
чтобы изменить баланс кошелька: пополнить(DEPOSIT)/снять(WITHDRAW) на указанную/указанную сумму (amount).
Если сумма снятия превышает баланс на кошельке, запрос вернет статус 400 с ошибкой Bad Request и оповещением о том, что
на счёте не хватает средств. 
Некорректно составленное тело запроса(неправильно написанны наименования полей, формат UUID, имя операции и т.д.)
вернёт "Bad Request".

# Стек
Java 11, Spring Boot 2.5.2, PostgreSQL 17
